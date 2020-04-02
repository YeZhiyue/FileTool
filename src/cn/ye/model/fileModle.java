package cn.ye.model;

import java.io.File;

public class fileModle {
    private int hib;
    private int flagNum;
    private boolean is_dir;
    private String dirPath;
    private String fileNameOrDirName;
    private File fileMsg;

    public fileModle(int hib, int flagNum, boolean is_dir, String dirPath, String fileNameOrDirName, File fileMsg) {
        this.hib = hib;
        this.flagNum = flagNum;
        this.is_dir = is_dir;
        this.dirPath = dirPath;
        this.fileNameOrDirName = fileNameOrDirName;
        this.fileMsg = fileMsg;
    }

    @Override
    public String toString() {
        return "fileModle{" +
                "hib=" + hib +
                ", flagNum=" + flagNum +
                ", is_dir=" + is_dir +
                ", dirPath='" + dirPath + '\'' +
                ", fileNameOrDirName='" + fileNameOrDirName + '\'' +
                ", fileMsg=" + fileMsg +
                '}';
    }

    public int getFlagNum() {
        return flagNum;
    }

    public void setFlagNum(int flagNum) {
        this.flagNum = flagNum;
    }

    public int getHib() {
        return hib;
    }

    public void setHib(int hib) {
        this.hib = hib;
    }

    public boolean isIs_dir() {
        return is_dir;
    }

    public void setIs_dir(boolean is_dir) {
        this.is_dir = is_dir;
    }

    public String getDirPath() {
        return dirPath;
    }

    public void setDirPath(String dirPath) {
        this.dirPath = dirPath;
    }

    public String getFileNameOrDirName() {
        return fileNameOrDirName;
    }

    public void setFileNameOrDirName(String fileNameOrDirName) {
        this.fileNameOrDirName = fileNameOrDirName;
    }

    public File getFileMsg() {
        return fileMsg;
    }

    public void setFileMsg(File fileMsg) {
        this.fileMsg = fileMsg;
    }
}
