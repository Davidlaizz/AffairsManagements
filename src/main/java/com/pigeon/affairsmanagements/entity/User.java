package com.pigeon.affairsmanagements.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    String userId ="";
    String userName ="";
    String roleId ="";
    String password ="";
    String majorId ="";
    String gender ="";
    Integer age;
    String phoneNumber = "";
    String className ="";
    String grade ="";

    public User(String _userId, String _password)
    {
        userId = _userId;
        password = _password;
    }


}
