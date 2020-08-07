package com.zuikaku.pilipili;

import com.zuikaku.pilipili.tool.ZuikakuTool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @program: pilipili-web-ImportTool->Main
 * @description: ${description}
 * @author: ty
 * @create: 2020-08-07 10:14
 **/
public class Main {
    public static void main(String[] args) {
        ResourceBundle rb=ResourceBundle.getBundle("config");
        String inputRoot =rb.getString("input.root.folder");
        String outputRoot =rb.getString("output.root.foler");
        List<File> folderList = new ArrayList<File>();//原目录下的所有子目录列表
        ZuikakuTool.findAllFolderByRootFolder(new File(inputRoot),folderList );
        for (File folder :folderList){
            //todo 获取源文件夹信息（先创建Album记录获得该记录主键ID）

            //todo 在outRoot下创建子文件夹（名为生成主键的名字）

            //todo 获取源文件夹下的图片文件，由于已经排序，直接用循环码进行复制到out对应文件，然后创建图片记录

            //todo 最后更新album记录，更新 目录 和 封面文件 两个字段
        }
    }
}
