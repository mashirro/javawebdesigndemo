package com.mashirro.utils;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
    private static final QueryRunner QUERY_RUNNER = new QueryRunner();


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


    /**
     * 查询实体列表
     *
     * @param coon        数据库连接
     * @param entityClass 实体Class对象
     * @param sql         sql语句
     * @param params      参数
     * @param <T>         泛型
     * @return
     */
    public static <T> List<T> queryEntityList(Connection coon, Class<T> entityClass, String sql, Object... params) {
        List<T> entityList = new ArrayList<>();
        try {
            entityList = QUERY_RUNNER.query(coon, sql, new BeanListHandler<T>(entityClass), params);
        } catch (SQLException e) {
            logger.error("查询实体列表出错!", e);
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(coon);
        }
        return entityList;
    }
}
