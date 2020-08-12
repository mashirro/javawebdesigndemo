package com.mashirro.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Properties;

/**
 * 数据库操作工具类
 */
public class DatabaseUtil {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseUtil.class);
    private static final String driver;
    private static final String url;
    private static final String username;
    private static final String password;


    static {
        Properties properties = PropsUtil.loadProps("jdbc.properties");
        driver = properties.getProperty("jdbc.driver");
        url = properties.getProperty("jdbc.url");
        username = properties.getProperty("jdbc.username");
        password = properties.getProperty("jdbc.password");

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            logger.error("数据库驱动出现异常!", e);
            e.printStackTrace();
        }
    }


    public static Connection getConnection() {
        Connection coon = null;
        try {
            coon = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            logger.error("获取数据库连接出现异常!", e);
            e.printStackTrace();
        }
        return coon;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("关闭数据库连接出现异常!", e);
                e.printStackTrace();
            }
        }
    }
}
