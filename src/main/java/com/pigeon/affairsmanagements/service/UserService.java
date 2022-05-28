package com.pigeon.affairsmanagements.service;

import com.pigeon.affairsmanagements.entity.User;
import com.pigeon.affairsmanagements.mapper.IUserMapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.annotation.Resource;
import java.util.HashMap;

@Service
public class UserService {

    @Resource
    IUserMapper mapper ;

    public User findUserNameByUserId(String userId) throws Exception
    {
        User res = mapper.getUserNameByUserId(userId);
        if(res == null || res.getUserName().equals(""))
        {
            throw new Exception("Cannot find user!");
        }
        return res;
    }
    public User findPasswordByUserId(String userId) throws Exception  {
        User res = mapper.getPasswordByUserId(userId);
        if(res==null)
            throw new Exception("User password wrong!");
        return res;

    }

    @Transactional
    public boolean registerUser(User user)
    {
        int res = mapper.registerNewUser(user);
        return res != 0;
    }

    @Transactional
    public boolean addStudentUser(User userInfo)
    {
        int res = mapper.addNewUser(userInfo);
        return res != 0;
    }

    @Transactional
    public boolean deleteUserInSelectCourse(User userInfo)
    {
        int res = mapper.deleteUserInSelectCourseTable(userInfo);
        return res != 0;
    }

    @Transactional
    public boolean addUserToSelectCourse(HashMap<String, String> userInfo)
    {
        var res = mapper.addUserToSelectCourseTable(userInfo);
        return res != 0;
    }

    @Transactional
    public boolean deleteUserFromSelectCourse(HashMap<String, String> userInfo)
    {
        var res = mapper.deleteUserFromSelectCourseTable(userInfo);
        return  res != 0;
    }
}
