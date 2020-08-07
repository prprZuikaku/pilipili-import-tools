package com.zuikaku.pilipili.pojo;

import java.io.File;

/**
 * 排序文件数据模型（用于文件排序）
 * @program: pilipili-web-ImportTool->SortFileDO
 * @description: ${description}
 * @author: ty
 * @create: 2020-08-07 11:25
 **/
public class SortFileDO {

    private File file;
    //为非数字文件名（这两个字段其中只有一个有效）
    private String noNumberName;
    //为数字文件名（这两个字段其中只有一个有效）
    private int numberName;

    @Override
    public String toString() {
        return "SortFileDO{" +
                "file=" + file +
                ", noNumberName='" + noNumberName + '\'' +
                ", numberName=" + numberName +
                '}';
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getNoNumberName() {
        return noNumberName;
    }

    public void setNoNumberName(String noNumberName) {
        this.noNumberName = noNumberName;
    }

    public int getNumberName() {
        return numberName;
    }

    public void setNumberName(int numberName) {
        this.numberName = numberName;
    }

    public SortFileDO(File file, String noNumberName, int numberName) {
        this.file = file;
        this.noNumberName = noNumberName;
        this.numberName = numberName;
    }

    public SortFileDO() {
    }
}
