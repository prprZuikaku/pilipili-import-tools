package com.zuikaku.pilipili.dao;

import com.zuikaku.pilipili.pojo.Album;
import com.zuikaku.pilipili.pojo.Picture;
import com.zuikaku.pilipili.tool.C3P0DataSource;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 图片dao
 * @program: pilipili-web-ImportTool->PictureDAO
 * @description: ${description}
 * @author: ty
 * @create: 2020-08-07 14:46
 **/
public class PictureDAO {
    private static PictureDAO instance;
    public static PictureDAO getInstance(){
        if(instance==null){
            instance=new PictureDAO();
        }
        return instance;
    }

    /**
     * 添加图片记录
     * @param picture
     * @return
     */
    public boolean addPictureByPicture(Picture picture){
        Connection connection= C3P0DataSource.getConnection();
        PreparedStatement preparedStatement=null;
        String insertSQL="INSERT INTO t_picture(`name`,picture_path,album_id,sort_no,create_date) VALUES (?,?,?,?,?)";
        try {
            preparedStatement=connection.prepareStatement(insertSQL);
            preparedStatement.setString(1,picture.getName());
            preparedStatement.setString(2,picture.getPicturePath());
            preparedStatement.setLong(3,picture.getAlbumId());
            preparedStatement.setInt(4,picture.getSortNo());
            preparedStatement.setDate(5,new Date(picture.getCreateDate().getTime()));
            int res = preparedStatement.executeUpdate();
            preparedStatement.close();
            C3P0DataSource.closeConnection(connection);
            if(res>0){
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
