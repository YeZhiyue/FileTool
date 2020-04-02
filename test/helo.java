import cn.ye.core.Create;
import cn.ye.core.Read;
import cn.ye.core.Update;
import cn.ye.model.fileModle;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class helo {
    private String fileSrc = "D:\\helo\\MarkDownTool";
//    private String fileSrc = "F:\\A_JavaPro\\FilteTool";
    private String fileOut = "D:\\helo";
    private String[] filterCondition = {"java", "xml"};

    @Test
    public void other() {
        String rule = "-\\/:*?\"<>";
        String regex = "\\\\|-|/|:|\\*|\\?|\\<|\\>";
        rule = rule.replaceAll(regex, "y");
        System.out.println(new File(fileSrc).getParent());
        System.out.println(new File(fileSrc).getParentFile().getAbsolutePath());
    }

    @Test
    public void read() {
        ArrayList<fileModle> fileModles = Read.getFileModles(fileSrc,  false, filterCondition);
        for (fileModle fileModle : fileModles) {
            if (fileModle.isIs_dir()) {

                System.out.println(fileModle);
            } else {

            }
        }
    }

    @Test
    public void create() {
        String[] strArrays = {"1", "￥$2", "5"};
        for (File dirOrFileByString : Create.createDirOrFileByStrings(fileOut, strArrays, false)) {
            System.out.println("文件创建失败，前检查文件名-->"+dirOrFileByString.getAbsolutePath());
        }
        for (fileModle fileModle : Read.getFileModles()) {
            System.out.println(fileModle);
        }

    }

    @Test public void create2() {

        Create.createDirOrFileByRule(fileOut, "yezhiyue^88999$4^z$tt^yy:mm$", true, 3);
    }
    @Test public void delete() { }
    @Test
    public void update() {
        Update.renameFile(fileSrc, "666e^88999$4^z$tt^yy:mm$", null);

    }
}
