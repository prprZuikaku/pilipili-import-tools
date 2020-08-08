package com.zuikaku.pilipili;

import com.zuikaku.pilipili.dao.AlbumDAO;
import com.zuikaku.pilipili.dao.PictureDAO;
import com.zuikaku.pilipili.pojo.Album;
import com.zuikaku.pilipili.pojo.Picture;
import com.zuikaku.pilipili.tool.C3P0DataSource;
import com.zuikaku.pilipili.tool.ZuikakuTool;

import java.io.File;
import java.util.*;

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
        String outputRoot =rb.getString("output.root.folder");
        C3P0DataSource.init();//初始化连接池
        List<File> folderList = new ArrayList<File>();//原目录下的所有子目录列表
        ZuikakuTool.findAllFolderByRootFolder(new File(inputRoot),folderList );
        System.out.println("---发现本子数："+folderList.size()+"本---");
        int folderCounter=1;
        for (File folder :folderList){
            System.out.println("---现在处理第"+folderCounter+"个本子："+folder.getName()+"---");
            // 获取源文件夹信息（先创建Album记录获得该记录主键ID）
            Album album=new Album();
            album.setName(folder.getName());
            album.setCreateDate(new Date());
            album.setCoverPath("no-path");
            album.setFolderPath(UUID.randomUUID().toString());
            long albumId = AlbumDAO.getInstance().addAlbumByAlbum(album);
            // 在outRoot下创建子文件夹（名为生成主键的名字）
            if(albumId==-1)
            {
                System.err.println("添加album记录失败,源文件夹为:"+album.getName());
                return;
            }
            album.setAlbumId(albumId);//主键注入回去
            File targetAlbumFolder=new File(outputRoot+"/"+albumId);
            if(!targetAlbumFolder.mkdir())
            {
                System.err.println("创建目标本子文件夹出错，路径："+targetAlbumFolder.getAbsolutePath());
                return;
            }
            album.setFolderPath("/album/"+albumId);//路径字段注入回去
            // 获取源文件夹下的图片文件，由于已经排序，直接用循环码进行复制到out对应文件，然后创建图片记录
            List<File> originPictureFileList = ZuikakuTool.findAllPictureByFolder(folder);
            int pictureCounter=1;//图片名称计数器（也是排序号）
            for (File picture:originPictureFileList
                 ) {
                String fileSuffix=picture.getName().substring(picture.getName().lastIndexOf("."));
                File targetFile=new File(outputRoot+"/"+albumId+"/"+pictureCounter+fileSuffix);
                boolean isOk= ZuikakuTool.copy(picture,targetFile);
                if(!isOk){
                    System.err.println("复制文件时发生错误，信息：源文件:"+picture.getAbsolutePath()+" 目标文件:"+targetFile.getAbsolutePath());
                    return;
                }
                Picture pic=new Picture();
                pic.setName(picture.getName());
                pic.setAlbumId(albumId);
                pic.setPicturePath("/album/"+albumId+"/"+pictureCounter+fileSuffix);
                pic.setSortNo(pictureCounter);
                pic.setCreateDate(new Date());
                boolean isOk2= PictureDAO.getInstance().addPictureByPicture(pic);
                if(!isOk2){
                    System.err.println("创建图片记录失败");
                    return;
                }
                pictureCounter++;

            }
            album.setCoverPath("/album/"+albumId+"/1"+originPictureFileList.get(0));
            // 最后更新album记录，更新 目录 和 封面文件 两个字段
            boolean isOk3= AlbumDAO.getInstance().updateCoverAndFolderById(album);
            if(!isOk3){
                System.err.println("更新本子记录失败");
                return;
            }
            System.out.println("---处理完毕第"+folderCounter+"个本子："+folder.getName()+"---");
            folderCounter++;
        }
    }
}
