package com.zuikaku.pilipili.tool;



import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * c3p0连接池
 * @program: pilipili-web-ImportTool->C3P0DataSource
 * @description: ${description}
 * @author: ty
 * @create: 2020-08-07 10:25
 **/
public class JDBCUtils {
    //数据库连接
    public static Connection connection;
    /**
     * 初始化c3p0环境
     */
    public static void init()
    {
        ResourceBundle rb=ResourceBundle.getBundle("config");
        String URL=rb.getString("jdbc.url");
        String username=rb.getString("jdbc.dbusername");
        String password=rb.getString("jdbc.password");
        String driverClassName=rb.getString("jdbc.driverClassName");
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //2：获得数据库连接
        try {
            connection = DriverManager.getConnection(URL,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}