package cn.ye.core;

import cn.ye.model.fileModle;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Create {

    private static ArrayList<File> failCre;
    /**
     * 根据固定的字符串进行文件或者目录的创建
     *
     * @param fileOut 文件创建目的地
     * @param strings 输入的规则字符串，要求以换行符\n分隔
     * @param isMkDir 选择创建目录或者文件
     * @return 返回创建失败的文件或目录
     */
    public static ArrayList<File> createDirOrFileByStrings(String fileOut, String[] strings, boolean isMkDir) {
        if (strings == null || strings[0].equals("")) {
            return null;
        }
        failCre = new ArrayList<>();
        for (String filePath : strings) {
            File file = new File(fileOut + "\\\\" + filePath);
            if (isMkDir) {
                if (!file.mkdirs()) {
                    failCre.add(file);
                }
            } else {
                try {
                    if (!file.createNewFile()) {
                        failCre.add(file);
                    }
                } catch (IOException e) {
                    failCre.add(file);
                    e.printStackTrace();
                }
            }
        }

        //最后读取创建的目录
        Read.getFileModles(fileOut, true, null);
        return failCre;
    }

    /**
     * 根据固定的规则进行字符串的创建指定数量的目录
     *
     * @param fileOut 文件输出位置
     * @param rule 用户定义命名文件规则
     * @param isMkDir 选择创建目录或者文件
     * @param dirNum 创建的数量
     * @return 返回创建失败的文件或目录
     */
    public static ArrayList<File> createDirOrFileByRule(String fileOut, String rule, boolean isMkDir, int dirNum) {
        //初始条件筛选
        if (dirNum < 1 || rule == null || rule.equals("")) {
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

        String[] split = rule.split(regE);
        int length = split.length;
        int listSize = list.size();
        //进行多个文件或者目录的创建
        for (int len = 0; len < dirNum; len++) {
            StringBuilder builder = new StringBuilder();
            //获取规则字符串的拼接
            getRuleBuild(list, split, length, listSize, builder);

            File file = null;
            if (isMkDir) {
                file = new File(fileOut + "\\\\" + builder.toString());
                if (!file.mkdirs()) {
                    failCre.add(file);
                }
            } else {
                try {
                    file = new File(fileOut + "\\\\" + builder.toString());
                    if (!file.createNewFile()) {
                        failCre.add(file);
                    }
                } catch (IOException e) {
                    failCre.add(file);
                    e.printStackTrace();
                }
            }
        }
        ArrayList<File> failCre = new ArrayList<>();
        Read.getFileModles(fileOut, true, null);
        return null;
    }

    /**
     * 获取规则存储集合，里面有整形规则，日期规则，字母规则
     *
     * @param rule
     * @param regE
     * @return
     */
    public static List getRuleList(String rule, String regE) {
        Matcher matcher = Pattern.compile(regE).matcher(rule);

        //由于我们需要根据顺序进行匹配，采用如下遍历方式
        List list = new ArrayList();
        while (matcher.find()) {
            String group = matcher.group();
            int length = group.length();
            group = group.substring(1, length - 1);
            //根据条件进行关键数据的存储，主要是用户特制的字符
            if (Pattern.matches("\\d{1,6}", group)) {
                list.add(Integer.parseInt(group));
            } else if (Pattern.matches("[a-z A-Z]", group)) {
                list.add(group.charAt(0));
            } else if (Pattern.matches("y{0,4}.*M{0,2}.*d{0,2}.*H{0,2}.*m{0,2}s{0,2}.*", group)) {
                list.add(new SimpleDateFormat(group).format(new Date()));
            }
        }
        return list;
    }

    /**
     * 获取根据规则构建的字符串
     *
     * @param list
     * @param split
     * @param length
     * @param listSize
     * @param builder
     */
    public static void getRuleBuild(List list, String[] split, int length, int listSize, StringBuilder builder) {
        for (int x = 0; x < length; x++) {
            builder.append(split[x]);
            if (listSize > x) {
                Object o = list.get(x);
                if (o instanceof Integer) {
                    Integer n = (Integer) o;
                    builder.append(n);
                    list.set(x, ++n);
                } else if (o instanceof Character) {
                    Character c = (Character) o;
                    builder.append(c);
                    if (c < 65 || (c > 89 && c < 97)) {
                        c = 64;
                    } else if (c > 121) {
                        c = 96;
                    }
                    list.set(x, ++c);
                } else {
                    builder.append(o);
                }
            }

        }
    }


   }
