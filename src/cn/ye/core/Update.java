package cn.ye.core;

import cn.ye.model.fileModle;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static cn.ye.core.Create.*;


public class Update {
    private static ArrayList<File> failCre;
    /**
     * 文件过滤器
     *
     * @param firstDir
     * @param compile
     * @return
     */
    private static File[] getFiles(File firstDir, Pattern compile) {
        return firstDir.listFiles(file ->
                {
                    if (file.isDirectory()) {
                        return true;
                    }
                    if (compile.matcher(file.getName()).find()) {
                        return true;
                    }
                    return false;
                }
        );
    }
    /**
     * 根据规则对目录进行重命名，可以选择针对目录或者文件的重命名。
     * 注意：这里会对一些系统不支持的命名字符进行替换
     *
     * @param fileSrc         设置遍历目录的源头
     * @param rule            用户输入的过滤条件判断
     * @param reDir           判断是重命名目录或者文件
     * @param filterCondition 设置文件过滤条件
     * @return 返回重名名失败的文件
     */
    public static ArrayList<File> renameFile(String fileSrc, String rule, boolean reDir, String... filterCondition) {
        //根据规则进行文件读取
        ArrayList<fileModle> fileModles = Read.getFileModles(fileSrc, false, filterCondition);

        StringBuilder builderFirst = new StringBuilder();
        //进行正则过滤条件的拼接
        for (int i = 0; i < filterCondition.length; i++) {
            if (i == filterCondition.length - 1) {
                builderFirst.append(filterCondition[i]);
            } else {
                builderFirst.append(filterCondition[i]).append("|");
            }
        }
        //正则表达式的编译
        Pattern compile = Pattern.compile(builderFirst.toString(), Pattern.CANON_EQ);
        for (int i = 0; i < fileModles.size(); i++) {
            fileModle fileModle = fileModles.get(i);
            if (fileModle.isIs_dir()) {
                if (!compile.matcher(fileModle.getFileNameOrDirName()).find()) {
                    fileModles.remove(i);
                    i--;
                }
            }
        }

        //初始条件筛选
        if (fileModles.size() < 1 || rule == null || rule.equals("")) {
            return null;
        }
        //进行敏感字符替换
        rule = rule.replaceAll("\\\\|-|/|:|\\*|\\?|\\<|\\>", " ");
        //正则表达式编译
        String regE = "\\^.{1,20}?\\$";
        //根据正则表达式和规则字符串进行规则字段获取
        List list = getRuleList(rule, regE);
        //准备读取处理失败的文件
        failCre = new ArrayList<>();

        //根据规则进行拆分
        String[] split = rule.split(regE);
        int length = split.length;
        int listSize = list.size();
        //进行多个文件或者目录的创建
        for (fileModle fileModle : fileModles) {
            StringBuilder builder = new StringBuilder();
            //获取规则字符串的拼接
            getRuleBuild(list, split, length, listSize, builder);

            File fileMsg = fileModle.getFileMsg();
            //判断是重名文件还是重命名目录
            if (reDir) {
                //如果是目录则进行重命名
                if (fileMsg.isDirectory()) {
                    if (!fileMsg.renameTo(new File(fileMsg.getParentFile(), builder.toString()))) {
                        failCre.add(fileMsg);
                    }
                }
            } else {
                //如果是文件则进行重名名
                if (fileMsg.isFile()) {
                    if (!fileMsg.renameTo(new File(fileMsg.getParentFile(), builder.toString()))) {
                        failCre.add(fileMsg);
                    }
                }
            }
        }
        return failCre;
    }


    /**
     * 进行文件的复制移动，可已根据标志位进行复制一级或者多级目录
     *
     * @param fileSrc         设置遍历目录的源头
     * @param fileOut         文件移动目的地
     * @param dirHibFlag      设置需要几级目录，1 表示不创建额外目录，2 表示创建一级目录，3 表示保持原来的目录树结构
     * @param filterCondition 过滤条件
     * @return 返回复制失败的文件
     */
    public static ArrayList<File> removeFile(String fileSrc, String fileOut, int dirHibFlag, String... filterCondition) {
        //获取需要进行复制的文件数据
        ArrayList<fileModle> fileModles = Read.getFileModles(fileSrc, true, filterCondition);

        //初始条件筛选
        if (fileModles.size() < 1) {
            return null;
        }

        //进行遍历创建
        for (fileModle fileModle : fileModles) {

            //情况一，把文件都放入一个目录下面
            if (dirHibFlag == 1) {
                File fileMsg = fileModle.getFileMsg();
                if (fileMsg.isFile()) {
                    //准备新的路径拼接
                    String newFPath = fileOut + "\\\\" + fileMsg.getName();
                    //进行文件复制
                    copyStream(fileMsg, newFPath);
                }
                //情况二，建造一级目录，并且把相关文件放入对应目录下面
            } else if (dirHibFlag == 2) {
                File fileMsg = fileModle.getFileMsg();
                //创建1级目录
                if (fileModle.getHib() == 1) {
                    //获取路径
                    String newDirP = fileOut + "\\\\" + fileMsg.getName();
                    new File(newDirP).mkdirs();
                } else if (fileMsg.isFile()) {
                    String newFPath;
                    if (fileModle.getHib() == 0) {
                        //准备新的路径拼接
                        newFPath = fileOut + "\\\\" + fileMsg.getName();
                        //进行文件复制
                        copyStream(fileMsg, newFPath);
                    } else {
                        String dirPath = fileModle.getDirPath();
                        String substring;
                        if (dirPath.contains("\\")) {
                            substring = dirPath.substring(0, fileModle.getDirPath().indexOf('\\'));
                        } else {
                            substring = dirPath;
                        }
                        newFPath = fileOut + "\\\\" + substring + "\\\\" + fileMsg.getName();
                        copyStream(fileMsg, newFPath);
                    }
                }
                //情况三，根据原有的目录树建造文件
            } else if (dirHibFlag == 3) {
                File fileMsg = fileModle.getFileMsg();
                //创建1级目录
                if (fileMsg.isDirectory()) {
                    //获取路径
                    String newDirP = fileOut + "\\\\" + fileModle.getDirPath() + "\\\\" + fileMsg.getName();
                    new File(newDirP).mkdirs();
                } else if (fileMsg.isFile()) {
                    String dirPath = fileModle.getDirPath();
                    String newFileP = fileOut + "\\\\" + dirPath + "\\\\" + fileMsg.getName();
                    copyStream(fileMsg, newFileP);
                }
            }
        }
        return failCre;
    }

    /**
     * 进行字节流复制
     *
     * @param fileMsg  需要复制的文件目录
     * @param newFPath 复制目标路径
     */
    private static void copyStream(File fileMsg, String newFPath) {
        try (
                //字节缓冲流
                //输出流
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileMsg));
                //输入流
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(newFPath))) {

            byte[] bytes = new byte[1024 * 8];
            int len = 0;
            while ((len = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
            }
        } catch (FileNotFoundException e) {
            //如果复制失败，那么就存储复制失败的信息
            failCre.add(fileMsg);
            e.printStackTrace();
        } catch (IOException e) {
            failCre.add(fileMsg);
            e.printStackTrace();
        }
    }
}
