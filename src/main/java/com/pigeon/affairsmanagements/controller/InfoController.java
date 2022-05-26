package com.pigeon.affairsmanagements.controller;

import com.pigeon.affairsmanagements.entity.ScheduleDetail;
import com.pigeon.affairsmanagements.entity.User;
import com.pigeon.affairsmanagements.service.InfoService;
import com.pigeon.affairsmanagements.service.UserService;
import com.pigeon.affairsmanagements.utils.Result;
import com.pigeon.affairsmanagements.utils.Status;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/info")
public class InfoController {
    @Resource
    InfoService infoService = new InfoService();
    @Resource
    UserService userService = new UserService();

//    String currentUserId = "";

    // 返回首页用户信息
    @ResponseBody
    @PostMapping(value = "/currentUser", consumes = "application/json", produces = "application/json")
    public HashMap<String, Object> getUserIndexInfo(@RequestBody Map<String, String> req, HttpSession session)
    {
        // @RequestBody
        // @PathVariable
        String currentUserId = (String) session.getAttribute("user");
//        String userId = req.get("userId");
        return infoService.getIndexInfo(currentUserId);

    }

    // 获取单门课程的课程表
    @ResponseBody
    @PostMapping(value = "/courseSchedule", consumes = "application/json", produces = "application/json")
    public ArrayList<ScheduleDetail> getCourseSchedule(@RequestBody Map<String, String> req)
    {
        //@RequestBody
        // @PathVariable
        String userId = req.get("userid");
        return infoService.getTeacherCoursesSchedule(userId);

    }

    @ResponseBody
    @PostMapping(value = "/find", consumes = "application/json", produces = "application/json")
    public ArrayList<User> queryUserInfo(@RequestBody User req)
    {
        //@RequestBody
        // @PathVariable

        return infoService.queryUserInfo(req);

    }

    // 删除操作接口
    @ResponseBody
    @PostMapping(value = "/del", consumes = "application/json", produces = "application/json")
    public Result<String> deleteUser(@RequestBody User req)
    {
        //@RequestBody
        // @PathVariable
        infoService.deleteUser(req);
        try {
            User res = userService.findUserNameByUserId(req.getUserId());
            return new Result<String>(Status.ERROR, Status.ERROR.getMsg());


        }catch(Exception e)
        {
            e.printStackTrace();
            return new Result<String>(Status.SUCCESS, Status.SUCCESS.getMsg());
        }
    }

    @ResponseBody
    @PostMapping(value="/edit", consumes = "application/json", produces = "application/json")
    public Result<String> updateUser(@RequestBody User req)
    {
        if(infoService.updateUserInfo(req) > 0)
        {
            return new Result<String>(Status.SUCCESS, Status.SUCCESS.getMsg());
        }
        else
        {
            return new Result<String>(Status.SUCCESS, Status.SUCCESS.getMsg());
        }
    }

}
