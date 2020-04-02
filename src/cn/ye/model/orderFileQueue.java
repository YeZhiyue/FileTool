package cn.ye.model;

import java.io.File;

public class orderFileQueue {
    private File file;
    private int hir;
    private int flagNum;

    public orderFileQueue(File file, int hir, int flagNum) {
        this.file = file;
        this.hir = hir;
        this.flagNum = flagNum;
    }


    public void setFile(File file) {
        this.file = file;
    }

    public void setHir(int hir) {
        this.hir = hir;
    }

    public int getFlagNum() {
        return flagNum;
    }

    public void setFlagNum(int flagNum) {
        this.flagNum = flagNum;
    }

    public File getFile() {
        return file;
    }

    public int getHir() {
        return hir;
    }
}
