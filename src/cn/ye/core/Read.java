package cn.ye.core;

import cn.ye.data_structure.QueueLinked;
import cn.ye.model.fileModle;
import cn.ye.model.orderFileQueue;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

public class Read {

    //为了使得CRUD的每一次操作仅仅只保留一份文件数据，设置其为全局变量
    private static ArrayList<fileModle> fileModles;

    public static ArrayList<fileModle> getFileModles() {
        return fileModles;
    }

    /**
     * 这里我们最核心的就是区分目录的层级逻辑的实现。首先需要两个标志位记录目录文件所在层级，和文件和目录归属关系标志位。这样我们就有了文件和目录的关联数据。
     * <p>
     * 这里我们的目录层级从0开始，0表示我们的目录源头，其中文件和目录的关联标志也是0
     *
     * @param fileSrc         读入文件源
     * @param mutiDir         是否多层目录遍历
     * @param filterCondition 过滤条件
     * @return 文件数据集合
     */
    public static ArrayList<fileModle> getFileModles(String fileSrc, boolean mutiDir, String... filterCondition) {
        //如果没有目录信息或者目录为空或者目录不存在，直接返回null
        File file = new File(fileSrc);
        if (fileSrc == null || fileSrc == "" || !file.exists() || file.list() == null) {
            return null;
        }
        //创建fileModles准备存放文件数据,并且刷新使得原有数据失效
        fileModles = new ArrayList<>();
        //进行正则表达式编译
        StringBuilder builder = new StringBuilder();

        //如果过滤条件为空，那么直接不进行过滤
        if (filterCondition == null) {
            filterCondition = new String[]{};
        }
        //进行正则过滤条件的拼接
        for (int i = 0; i < filterCondition.length; i++) {
            if (i == filterCondition.length - 1) {
                builder.append(filterCondition[i]);
            } else {
                builder.append(filterCondition[i]).append("|");
            }
        }
        //正则表达式的编译
        Pattern compile = Pattern.compile(builder.toString(), Pattern.CANON_EQ);
        //目录文件关联标记
        int flagNum = 0;
        //队列实现目录层级划分，并且定制数据模块接口
        QueueLinked<orderFileQueue> orderFileQueueQueueLinked = new QueueLinked<>();
        //进行文件过滤
        File[] files = getFiles(file, compile);
        //如果一级目录下是空，直接返回null
        if (files == null) {
            return null;
        }
        //一些文件信息参数的获取
        String absolutePath;
        String fileName;
        int alength;
        int nlength;
        //获取起始坐标
        int beginIndex = fileSrc.length();
        //进行遍历
        for (File f : files) {
            absolutePath = f.getAbsolutePath();
            fileName = f.getName();
            alength = absolutePath.length();
            nlength = fileName.length();

            if (f.isDirectory()) {
                //如果是目录，我们需要设置其层级和标志位
                orderFileQueueQueueLinked.enqueue(new orderFileQueue(f, 1, ++flagNum));

                fileModles.add(new fileModle(1, flagNum, true, absolutePath.substring(beginIndex, alength - nlength - 1), f.getName(), f));
            } else {
                //如果是文件，我们需要设置其层级和其关联文件标志位
                fileModles.add(new fileModle(0, 0, false, absolutePath.substring(beginIndex, alength - nlength - 1), f.getName(), f));
            }
        }

        //如果不进行多层目录过滤，直接跳过本环节
        if (mutiDir == false) {
            return fileModles;
        }
        beginIndex++;

        //创建队列，准备进行入队操作
        while (!orderFileQueueQueueLinked.isEmpty()) {
            orderFileQueue next = orderFileQueueQueueLinked.dequeue();
            int fileFlagNum = next.getFlagNum();
            int hir = next.getHir();
            files = getFiles(next.getFile(), compile);
            if (files != null) {
                for (File f : files) {
                    absolutePath = f.getAbsolutePath();
                    fileName = f.getName();
                    alength = absolutePath.length();
                    nlength = fileName.length();

                    //同样的标志位和层级设置，仍然需要进行层级和标志位设立，其逻辑依据当前目录给出的信息
                    if (f.isDirectory()) {
                        orderFileQueueQueueLinked.enqueue(new orderFileQueue(f, hir + 1, ++flagNum));
                        fileModles.add(new fileModle(hir + 1, flagNum, true, absolutePath.substring(beginIndex, alength - nlength - 1), f.getName(), f));
                    } else {
                        fileModles.add(new fileModle(hir + 1, fileFlagNum, false, absolutePath.substring(beginIndex, alength - nlength - 1), f.getName(), f));
                    }
                }
            }
        }
        return fileModles;
    }

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
}
