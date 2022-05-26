package com.pigeon.affairsmanagements.mapper;

import com.pigeon.affairsmanagements.entity.ScheduleDetail;
import com.pigeon.affairsmanagements.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface IInfoMapper {
    HashMap<String,Object> getIndexUserInfo(String userId);

    ArrayList<ScheduleDetail> getTeacherCourseSchedule(String courseId);

    ArrayList<User> queryUserInfo(User user);

    void deleteUser(User user);

    // Integer addUser(User user);

    Integer updateUser(User user);
}
