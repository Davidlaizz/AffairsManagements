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
    UserService userService = new UserService();

    @ResponseBody
    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public Result<String> userLogin(@RequestBody Map<String, Object> req, HttpSession httpSession, HttpServletResponse response)  {
        //在这里查到user嘛

        String userId = (String)req.get("userId");
        String password = (String)req.get("password");

        User user = null ;
        try {
            user = userService.findUserNameByUserId(userId);

        }catch(Exception e){
            e.printStackTrace();
            return new Result<String>(Status.USER_NOT_EXIST, Status.USER_NOT_EXIST.getMsg());
        }
        try {
            user = userService.findPasswordByUserId(userId);
            //Cookie cookie = new Cookie("user", DigestUtils.md5DigestAsHex(user.toString().getBytes(StandardCharsets.UTF_8)));
            //response.addCookie(cookie);

            if(user.getPassword().equals(password)){
                httpSession.setAttribute("user",user.getUserId());
                return new Result<String>(Status.SUCCESS, user.getRoleId(),"");
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
            userService.findUserNameByUserId(userInfo.getUserId());
            return new Result<String>(Status.USER_HAS_EXISTED, "");
        } catch (Exception e) {
            if(userService.registerUser(userInfo))
                return new Result<String>(Status.SUCCESS, "");
            else
                return new Result<String>(Status.ERROR,"");
        }

    }

    @ResponseBody
    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public Result<String> addStudentUser(@RequestBody User userInfo)
    {
        if (userService.addStudentUser(userInfo)) {

            return new Result<String>(Status.SUCCESS, Status.SUCCESS.getMsg());
        }
        else
        {
            return new Result<String>(Status.SUCCESS, Status.SUCCESS.getMsg());
        }
    }

    @PostMapping(value = "/logout", consumes = "application/json")
    Result<String> logout(HttpSession session)
    {
        if(session.getAttribute("user")!= null)
        {
            session.setAttribute("user", null) ;
            return new Result<String>(Status.SUCCESS, Status.SUCCESS.getMsg());
        }
        return new Result<String>(Status.ERROR, Status.ERROR.getMsg());
    }

}
