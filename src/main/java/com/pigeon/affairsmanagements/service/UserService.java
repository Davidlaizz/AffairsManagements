package com.pigeon.affairsmanagements.service;

import com.pigeon.affairsmanagements.entity.User;
import com.pigeon.affairsmanagements.mapper.IUserMapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    IUserMapper mapper ;

    public User findUserNameByUserId(String userId) throws Exception
    {
        String userName = mapper.getUserNameByUserId(userId);
        if(userName == null || userName.equals(""))
        {
            throw new Exception("Cannot find user!");
        }
        return new User("",userName,"","","","",0,"","","");
    }
    public User findPasswordByUserId(String userId) throws Exception  {
        String password = mapper.getPasswordByUserId(userId);
        if(password==null)
            throw new Exception("User password wrong!");
        return new User(userId, password);

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
}
