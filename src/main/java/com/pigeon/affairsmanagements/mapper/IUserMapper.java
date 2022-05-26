package com.pigeon.affairsmanagements.mapper;

import com.pigeon.affairsmanagements.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserMapper {

    String getPasswordByUserId(String userName);

    String getUserNameByUserId(String userId);

    Integer registerNewUser(User userInfo);

    Integer addNewUser(User userInfo);






}
