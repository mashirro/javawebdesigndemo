package com.mashirro.service;


import com.mashirro.pojo.User;
import com.mashirro.utils.DatabaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 用户服务层
 */
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

//    public List<User> getUserList(User user) {
//        Connection coon = null;
//        List<User> userList = new ArrayList<>();
//        try {
//            String sql = "select * from user";
//            coon = DatabaseUtil.getConnection();
////            PreparedStatement ps = coon.prepareStatement(sql);
////            ResultSet rs = ps.executeQuery();
////            while (rs.next()) {
////                User u = new User();
////                u.setId(rs.getString("id"));
////                u.setUserName(rs.getString("user_name"));
////                u.setTelephone(rs.getString("telephone"));
////                userList.add(u);
////            }
//            userList = DatabaseUtil.queryEntityList(coon, User.class, sql);
//        } catch (Exception e) {
//            logger.error("用户列表查询出现异常!", e);
//            e.printStackTrace();
//        } finally {
//            DatabaseUtil.closeConnection(coon);
//        }
//        return userList;
//    }


    /**
     * 获取用户列表
     * @param user
     * @return
     */
    public List<User> getUserList(User user) {
        String sql = "select * from user";
        return DatabaseUtil.queryEntityList(User.class, sql);
    }
}
