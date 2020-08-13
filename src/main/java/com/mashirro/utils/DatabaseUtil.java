package com.mashirro.utils;


import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
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
    private static final ThreadLocal<Connection> CONNECTION_HOLDER = new ThreadLocal<>();
    private static final BasicDataSource DATA_SOURCE = new BasicDataSource();


    static {
        Properties properties = PropsUtil.loadProps("jdbc.properties");
        driver = properties.getProperty("jdbc.driver");
        url = properties.getProperty("jdbc.url");
        username = properties.getProperty("jdbc.username");
        password = properties.getProperty("jdbc.password");
        DATA_SOURCE.setDriverClassName(driver);
        DATA_SOURCE.setUrl(url);
        DATA_SOURCE.setUsername(username);
        DATA_SOURCE.setPassword(password);
    }


    /**
     * 获取数据库连接
     *
     * @return
     */
    public static Connection getConnection() {
//        Connection coon = null;
//        try {
//            coon = DriverManager.getConnection(url, username, password);
//        } catch (SQLException e) {
//            logger.error("获取数据库连接出现异常!", e);
//            e.printStackTrace();
//        }
//        return coon;
        Connection coon = CONNECTION_HOLDER.get();
        if (coon == null) {
            try {
                //coon = DriverManager.getConnection(url, username, password);
                coon = DATA_SOURCE.getConnection();
            } catch (SQLException e) {
                logger.error("获取数据库连接出现异常!", e);
                e.printStackTrace();
            } finally {
                CONNECTION_HOLDER.set(coon);
            }
        }
        return coon;
    }

    /**
     * 关闭数据库连接
     *
     * @param connection
     */
//    public static void closeConnection(Connection connection) {
//        if (connection != null) {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                logger.error("关闭数据库连接出现异常!", e);
//                e.printStackTrace();
//            }
//        }
//    }

    /**
     * 关闭数据库连接
     */
//    public static void closeConnection() {
//        Connection coon = CONNECTION_HOLDER.get();
//        if (coon != null) {
//            try {
//                coon.close();
//            } catch (SQLException e) {
//                logger.error("关闭数据库连接出现异常!", e);
//                e.printStackTrace();
//            } finally {
//                CONNECTION_HOLDER.remove();
//            }
//        }
//    }


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
//    public static <T> List<T> queryEntityList(Connection coon, Class<T> entityClass, String sql, Object... params) {
//        List<T> entityList = new ArrayList<>();
//        try {
//            entityList = QUERY_RUNNER.query(coon, sql, new BeanListHandler<T>(entityClass), params);
//        } catch (SQLException e) {
//            logger.error("查询实体列表出错!", e);
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        } finally {
//            closeConnection(coon);
//        }
//        return entityList;
//    }

    /**
     * 查询实体列表
     *
     * @param entityClass 实体Class对象
     * @param sql         sql语句
     * @param params      参数
     * @param <T>         泛型
     * @return
     */
    public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params) {
        List<T> entityList = new ArrayList<>();
        try {
            Connection coon = getConnection();
            entityList = QUERY_RUNNER.query(coon, sql, new BeanListHandler<T>(entityClass), params);
        } catch (SQLException e) {
            logger.error("查询实体列表出错!", e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return entityList;
    }
}
