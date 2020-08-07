package com.zuikaku.pilipili.tool;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * c3p0连接池
 * @program: pilipili-web-ImportTool->C3P0DataSource
 * @description: ${description}
 * @author: ty
 * @create: 2020-08-07 10:25
 **/
public class C3P0DataSource {
    //c3p0数据源
    private static ComboPooledDataSource dataSource;
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

        dataSource=new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(driverClassName);//设置驱动
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        dataSource.setJdbcUrl(URL);//设置URL
        dataSource.setUser(username);//数据库用户名
        dataSource.setPassword(password);//数据库密码
        //连接池设置
        dataSource.setInitialPoolSize(5);//初始连接池中连接的个数
        dataSource.setMaxPoolSize(20);//设置最大连接数，超出后将会等待。不会再创建
        dataSource.setMinPoolSize(3);//设置最小空闲连接，即保证至少有几个可用。可用连接数至少为这么多
        //dataSource.setMaxIdleTime(60000);//设置最大空闲时间，时间到后自动释放归还没有使用的连接（单位毫秒）
    }

    /**
     * 得到连接
     * @return
     */
    public static Connection getConnection()
    {
        try {
            Connection connection=dataSource.getConnection();
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 关闭（归还）连接 -（c3p0处理关闭）并不是真正的关闭，实际上是释放
     * c3p0有最大空闲时间机制，如果连接太久没用，会自动归还，不用调用这个函数，最大空闲时间可设置
     * @param connection
     */
    public static void closeConnection(Connection connection)
    {
        try {
            if(connection!=null)
                connection.close();
        } catch (SQLException e) {
            System.out.println("c3p0关闭连接时发生异常"+e);
        }
    }
}