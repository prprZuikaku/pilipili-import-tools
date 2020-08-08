package com.zuikaku.pilipili.dao;

import com.zuikaku.pilipili.pojo.Album;
import com.zuikaku.pilipili.tool.JDBCUtils;

import java.sql.*;

/**
 * @program: pilipili-web-ImportTool->AlbumDAO
 * @description: ${description}
 * @author: ty
 * @create: 2020-08-07 13:37
 **/
public class AlbumDAO {
    private static AlbumDAO instance;

    public static AlbumDAO getInstance() {
        if (instance == null) {
            instance = new AlbumDAO();
        }
        return instance;
    }

    /**
     * 新增album记录，并返回主键ID
     *
     * @param album
     * @return
     */
    public long addAlbumByAlbum(Album album) {
        Connection connection = JDBCUtils.connection;
        String insertSQL = "INSERT INTO t_album(`name`,create_date,cover_path,folder_path) VALUES(?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(insertSQL,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, album.getName());
            preparedStatement.setDate(2, new Date(album.getCreateDate().getTime()));
            preparedStatement.setString(3,album.getCoverPath());
            preparedStatement.setString(4,album.getFolderPath());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();//获得该记录自增生成的主键ID；
            if (rs.next()) {
                return rs.getLong(1);
            }
            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return -1;
    }

    /**
     * 更新本子的封面和目录字段
     *
     * @param album
     * @return
     */
    public boolean updateCoverAndFolderById(Album album) {
        Connection connection = JDBCUtils.connection;
        String updateSql = "UPDATE  t_album SET cover_path = ?,folder_path = ? WHERE album_id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setString(1, album.getCoverPath());
            preparedStatement.setString(2, album.getFolderPath());
            preparedStatement.setLong(3, album.getAlbumId());
            int res = preparedStatement.executeUpdate();
            preparedStatement.close();
            if (res > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean deleteAlbumByPK(long albumId) {
        Connection connection = JDBCUtils.connection;
        String deleteSql = "DELETE FROM t_album WHERE album_id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.setLong(1, albumId);
            int res = preparedStatement.executeUpdate();
            preparedStatement.close();
            if (res > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
