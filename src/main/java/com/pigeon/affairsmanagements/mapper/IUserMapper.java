package com.pigeon.affairsmanagements.mapper;

import com.pigeon.affairsmanagements.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface IUserMapper {

    User getPasswordByUserId(String userName);

    User getUserNameByUserId(String userId);

    Integer registerNewUser(User userInfo);

    Integer addNewUser(User userInfo);

    // 教师删除学生
    void deleteUserInUserTable(User user);

    // 更新个人信息
    Integer updateUserInUserTable (User user);

    //删除选课人员
    Integer deleteUserInSelectCourseTable(User user);

    // 添加选课人员
    Integer addUserToSelectCourseTable(HashMap<String, String> userInfo);

    //删除选课人员
    Integer deleteUserFromSelectCourseTable(HashMap<String, String> userInfo);



}
