package cn.ye.core;

import cn.ye.model.fileModle;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static cn.ye.core.Create.*;
/**
 * 更新：重命名文件(或者目录)、移动复制文件（单层目录、双层目录、保持原本目录树）
 */
public class Update {
    private static ArrayList<File> failCre;

    /**
     * 仅仅支持根据规则进行重命名
     * 不支持多级目录的重命名，因为这种需求很少
     * @param fileSrc
     * @param filterCondition
     * @return
     */
    public static ArrayList<File> renameFile(String fileSrc,  String rule, String... filterCondition) {
        ArrayList<fileModle> fileModles = Read.getFileModles(fileSrc, false, filterCondition);

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

        String[] split = rule.split(regE);
        int length = split.length;
        int listSize = list.size();
        //进行多个文件或者目录的创建
        for (fileModle fileModle : fileModles) {
            StringBuilder builder = new StringBuilder();
            //获取规则字符串的拼接
            getRuleBuild(list, split, length, listSize, builder);

            File fileMsg = fileModle.getFileMsg();
            fileMsg.renameTo(new File(fileMsg.getParentFile(), builder.toString()));
        }


        return null;
    }


}
