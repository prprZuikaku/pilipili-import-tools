package com.zuikaku.pilipili.tool;

import com.zuikaku.pilipili.pojo.SortFileDO;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 工具类（文件操作相关）
 *
 * @program: pilipili-web-ImportTool->ZuikakuTool
 * @description: ${description}
 * @author: ty
 * @create: 2020-08-07 10:37
 **/
public class ZuikakuTool {

    /**
     * 获取某一目录下的所有子目录
     *
     * @param rootFolder 根目录
     * @param out        out参数，输出子目录列表
     */
    public static void findAllFolderByRootFolder(File rootFolder, List<File> out) {
        File[] files = rootFolder.listFiles();
        for (File temp : files
        ) {
            if (temp.isDirectory()) {
                out.add(temp);
                findAllFolderByRootFolder(temp, out);
            }
        }
    }

    /**
     * 获取某一目录下的全部图片文件，并全部排序后返回
     *
     * @param folder
     * @return
     */
    public static List<File> findAllPictureByFolder(File folder) {
        File[] fileArray = folder.listFiles(new FileFilter() {
            public boolean accept(File file) {
                String name = file.getName();
                return !file.isDirectory() && (name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png") || name.endsWith(".JPG") || name.endsWith(".JPEG") || name.endsWith(".PNG"));
            }
        });
        //进行排序
        return sortByName(fileArray);

    }


    /**
     * 对文件进行排序，返回文件列表
     *
     * @param fileArray
     * @return
     */
    private static List<File> sortByName(File[] fileArray) {
        List<File> result = new ArrayList<File>();
        List<SortFileDO> numberSortFileDOList = new ArrayList<SortFileDO>();
        List<SortFileDO> strSortFileDOList = new ArrayList<SortFileDO>();
        if (fileArray == null || fileArray.length == 0) {
            return null;
        }
        //筛选出数字文件名和非数字文件名
        for (File temp : fileArray
        ) {
            String fileName = temp.getName().substring(0, temp.getName().lastIndexOf("."));
            try {
                int number = Integer.parseInt(fileName);
                SortFileDO numberFileDO = new SortFileDO();
                numberFileDO.setFile(temp);
                numberFileDO.setNumberName(number);
                numberSortFileDOList.add(numberFileDO);
            } catch (NumberFormatException e) {

                SortFileDO strFileDO = new SortFileDO();
                strFileDO.setFile(temp);
                strFileDO.setNoNumberName(fileName);
                strSortFileDOList.add(strFileDO);
            }
        }
        //进行排序
        Collections.sort(numberSortFileDOList, new NumComparator());
        Collections.sort(strSortFileDOList, new StrComparator());
        //添加到结果列表中
        for (SortFileDO temp : numberSortFileDOList
        ) {
            result.add(temp.getFile());
        }
        for (SortFileDO temp : strSortFileDOList
        ) {
            result.add(temp.getFile());
        }
        return result;
    }

    /**
     * 进行文件拷贝
     * @param source
     * @param target
     * @return
     */
    public static boolean copy(File source, File target) {
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream(source);
            out = new FileOutputStream(target);

            byte[] buffer = new byte[1024];
            int len;

            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("文件复制时发送异常");
            return false;
        } finally {
            try {
                if(in!=null) {
                    in.close();
                }
                if(out!=null)
                {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return true;
    }
}
