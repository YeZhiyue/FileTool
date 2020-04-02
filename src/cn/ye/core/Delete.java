package cn.ye.core;

import cn.ye.data_structure.QueueLinked;
import cn.ye.data_structure.StackLinked;
import cn.ye.model.fileModle;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class Delete {
    private static ArrayList<File> failCre;

    /**
     * 进行文件的复制移动，可已根据标志位进行复制一级或者多级目录
     *
     * @param fileSrc         设置遍历目录的源头
     * @param delDir          是否删除目录
     * @param filterCondition 过滤条件
     * @return 返回复制失败的文件
     */
    public static ArrayList<File> deleteFileOrDir(String fileSrc, boolean delDir, String... filterCondition) {
        //获取需要进行复制的文件数据
        ArrayList<fileModle> fileModles = Read.getFileModles(fileSrc, true, filterCondition);

        //初始条件筛选
        if (fileModles.size() < 1) {
            return null;
        }

        failCre = new ArrayList<>();
        // 这里我们选择使用栈，后进先出，更加符合目录删除的逻辑
        StackLinked<File> dir = new StackLinked<>();
        //进行遍历删除
        for (fileModle fileModle : fileModles) {
            if (!delDir) {
                //如果不是目录
                if (!fileModle.isIs_dir()) {
                    //如果没有删除成功
                    if (!fileModle.getFileMsg().delete()) {
                        failCre.add(fileModle.getFileMsg());
                    }
                }
            } else {
                //由于目录为空才可以进行删除，所以，我们优先删除其中文件，然后再进行目录的删除
                if (fileModle.isIs_dir()) {
                    dir.push(fileModle.getFileMsg());
                } else {
                    if (!fileModle.getFileMsg().delete()) {
                        failCre.add(fileModle.getFileMsg());
                    }
                }
            }
        }
        //如果用户需要删除目录的话，判断我们的目录队列是否为空，不为空则进行删除
        if (dir != null) {
            File next;
            while (!dir.isEmpty()) {
                next = dir.pop();
                if (!next.delete()) {
                    failCre.add(next);
                }
            }
        }
        return failCre;
    }
}
