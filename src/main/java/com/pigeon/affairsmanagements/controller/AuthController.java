package com.pigeon.affairsmanagements.controller;

import com.pigeon.affairsmanagements.entity.User;
import com.pigeon.affairsmanagements.service.UserService;
import com.pigeon.affairsmanagements.utils.Result;
import com.pigeon.affairsmanagements.utils.Status;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
@RestController
@RequestMapping(value = "/v1/auth/")
public class AuthController {
    @Resource
    UserService uas = new UserService();

    @ResponseBody
    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public Result<String> userLogin(@RequestBody Map<String, Object> req, HttpSession httpSession, HttpServletResponse response)  {
        //在这里查到user嘛

        String userId = (String)req.get("userId");
        String password = (String)req.get("password");

        User user = null ;
        try {
            user = uas.findUserNameByUserId(userId);

        }catch(Exception e){
            e.printStackTrace();
            return new Result<String>(Status.USER_NOT_EXIST, Status.USER_NOT_EXIST.getMsg());
        }
        try {
            user = uas.findPasswordByUserId(userId);
            //Cookie cookie = new Cookie("user", DigestUtils.md5DigestAsHex(user.toString().getBytes(StandardCharsets.UTF_8)));
            //response.addCookie(cookie);

            if(user.getPassword().equals(password)){
                httpSession.setAttribute("user",user.getUserId());
                return new Result<String>(Status.SUCCESS, Status.SUCCESS.getMsg());
            }
        } catch (Exception e) {
            e.printStackTrace();


        }
        return new Result<String>(Status.USER_LOGIN_ERROR, Status.USER_LOGIN_ERROR.getMsg());

    }

    // 用户注册
    @ResponseBody
    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public Result<String> userRegister(@RequestBody User userInfo)
    {
        try
        {
            uas.findUserNameByUserId(userInfo.getUserId());
            return new Result<String>(Status.USER_HAS_EXISTED, "");
        } catch (Exception e) {
            if(uas.registerUser(userInfo))
                return new Result<String>(Status.SUCCESS    , "");
            else
                return new Result<String>(Status.ERROR,"");
        }

    }

    @ResponseBody
    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public Result<String> addStudentUser(@RequestBody User userInfo)
    {
        if (uas.addStudentUser(userInfo)) {

            return new Result<String>(Status.SUCCESS, Status.SUCCESS.getMsg());
        }
        else
        {
            return new Result<String>(Status.SUCCESS, Status.SUCCESS.getMsg());
        }
    }

}
