package com.pigeon.affairsmanagements.service;

import com.pigeon.affairsmanagements.entity.Course;
import com.pigeon.affairsmanagements.entity.ScheduleDetail;
import com.pigeon.affairsmanagements.entity.User;
import com.pigeon.affairsmanagements.mapper.IInfoMapper;
import com.pigeon.affairsmanagements.mapper.IUserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class InfoService {

    @Resource
    IInfoMapper infoMapper;

    @Resource
    IUserMapper userMapper;
    //获取首页用户信息
    public HashMap<String, Object> getIndexInfo(String userId)
    {
        HashMap<String, Object> res = new HashMap<>();
        res = infoMapper.getIndexUserInfo(userId);
        return  res;
    }

    //返回某一门课程的上课时间
    public ArrayList<ScheduleDetail> getTeacherCoursesSchedule(String userId)
    {
        ArrayList<ScheduleDetail> res = new ArrayList<ScheduleDetail>();
        res  = infoMapper.getTeacherCourseSchedule(userId);
        return res;
    }

    public ArrayList<User> fuzzyQueryUserInfo(User userFuzzyInfo)
    {
        return infoMapper.fuzzyQueryUserInfo(userFuzzyInfo);
    }

    public void deleteUser(User user)
    {
        userMapper.deleteUserInUserTable(user);

    }

    //update user information
    @Transactional
    public Integer updateUserInfo(User user)
    {
        return userMapper.updateUserInUserTable(user);
    }

    public User queryExactUserInfo(String userId)
    {
        return infoMapper.getExactUserInfo(userId);
    }

    public ArrayList<User> getStudentsInfoSelectCourse(Course courseName)
    {
        Course res = infoMapper.getCourseIdByName(courseName);
        return  infoMapper.getStudentIdInSelectCourseTable(res);

    }

    public ArrayList<Course> getCourseInfoSelectedByStudent(User userInfo)
    {
        return infoMapper.getStudentSelectedCourse(userInfo);
    }

    public Course getCourseIdByName(Course courseInfo)
    {
        return infoMapper.getCourseIdByName(courseInfo);
    }
}
