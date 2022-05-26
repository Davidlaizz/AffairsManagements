package com.pigeon.affairsmanagements.service;

import com.pigeon.affairsmanagements.entity.ScheduleDetail;
import com.pigeon.affairsmanagements.entity.User;
import com.pigeon.affairsmanagements.mapper.IInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class InfoService {

    @Resource
    IInfoMapper infoMapper;
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

    public ArrayList<User> queryUserInfo(User userFuzzyInfo)
    {
        return infoMapper.queryUserInfo(userFuzzyInfo);
    }

    public void deleteUser(User user)
    {
        infoMapper.deleteUser(user);

    }

    //update user information
    @Transactional
    public Integer updateUserInfo(User user)
    {
        return infoMapper.updateUser(user);
    }
}
