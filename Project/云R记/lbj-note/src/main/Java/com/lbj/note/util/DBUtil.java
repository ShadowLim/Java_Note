package com.lbj.note.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


/**
 * @author lbj
 * @create 2022-01-03-15:16
 * @description: 数据库的工具类
 *  1、得到数据库连接
 *  2、关闭资源
 */
public class DBUtil {
    // 得到配置文件对象
    private static Properties properties =  new Properties();

    // 静态代码块加载配置文件
    static {
        try {
            // 加载配置文件（输入流）
            InputStream in = DBUtil.class.getClassLoader().getResourceAsStream("db.properties");
            // 通过 load()方法将输入流的内容加载到配置文件对象中
            properties.load(in);
            // 通过配置文件对象的 getProperty()方法获得驱动名，并加载驱动
            Class.forName(properties.getProperty("jdbcName"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * description: 获取数据库连接
     * create time: 2022-01-03 16:35
     * @param
     * @return java.sql.Connection
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // 得到数据库连接的相关信息
            String dbUrl = properties.getProperty("dbUrl");
            String dbName = properties.getProperty("dbName");
            String dbPwd = properties.getProperty("dbPwd");
            // 得到数据库连接
            conn = DriverManager.getConnection(dbUrl, dbName, dbPwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  conn;
    }

    /**
     * description: 关闭资源
     * create time: 2022-01-03 16:41
     * @Param resultSet
     * @Param preparedStatement
     * @Param conn
     * @return void
     */
    public static void close(ResultSet resultSet, PreparedStatement preparedStatement,
                             Connection conn) {
        try {
            // 判断资源是否为空，不为空则关闭
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
