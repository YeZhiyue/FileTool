import cn.ye.core.Create;
import cn.ye.core.Delete;
import cn.ye.core.Read;
import cn.ye.core.Update;
import cn.ye.model.fileModle;
import org.junit.After;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CRUD {
    private String fileSrc = "D:\\helo\\666e889994ztt20 40";
    private String fileOut = "D:\\helo";
    private String[] filterCondition = {"Update", "Create"};
//    private String[] filterCondition = {"java"};

    @Test
    public void other() {
        String rule = "-\\/:*?\"<>";
        String regex = "\\\\|-|/|:|\\*|\\?|\\<|\\>";
        rule = rule.replaceAll(regex, "y");
//        int index = fileSrc.indexOf();
//        System.out.println(index);
    }

    @Test
    public void read() {
        ArrayList<fileModle> fileModles = Read.getFileModles(fileSrc, true, filterCondition);
    }

    @Test
    public void create() {
        String[] strArrays = {"1", "￥$2", "5"};
        for (File dirOrFileByString : Create.createDirOrFileByStrings(fileOut, strArrays, false)) {
            System.out.println("重命名失败的文件-->" + dirOrFileByString.getAbsolutePath());
        }
    }

    @Test
    public void create2() {
        ArrayList<File> failCre = Create.createDirOrFileByRule(fileOut, "yezhiyue^88999$4^z$tt^yy:mm$", true, 3);
        for (File file : failCre) {
            System.out.println("重命名失败的文件-->" + file);
        }
    }

    @Test
    public void update1() {
        ArrayList<File> files = Update.renameFile(fileSrc, "666e^88999$4^z$tt^yy:mm$", true, null);
        for (File file : files) {
            System.out.println("重命名失败的文件-->" + file);
        }
    }

    @Test
    public void update2() {
        ArrayList<File> files = Update.removeFile(fileSrc, fileOut, 3, filterCondition);
        if (files == null) {
            return;
        }
        for (File file : files) {
            System.out.println("复制失败的文件-->" + file);
        }
    }

    @Test
    public void delete() {
        ArrayList<File> files = Delete.deleteFileOrDir(fileSrc, false, filterCondition);
        for (File file : files) {
            System.out.println("删除失败的文件-->" + file);
        }

    }

    @After
    public void printer() {
        for (fileModle fileModle : Read.getFileModles()) {
            System.out.println(fileModle);
        }
    }
}
