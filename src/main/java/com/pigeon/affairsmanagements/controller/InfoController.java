package com.pigeon.affairsmanagements.controller;

import com.pigeon.affairsmanagements.entity.Course;
import com.pigeon.affairsmanagements.entity.ScheduleDetail;
import com.pigeon.affairsmanagements.entity.User;
import com.pigeon.affairsmanagements.service.InfoService;
import com.pigeon.affairsmanagements.service.UserService;
import com.pigeon.affairsmanagements.utils.Result;
import com.pigeon.affairsmanagements.utils.Status;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

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

        User currentUser = infoService.queryExactUserInfo(currentUserId);

        if(currentUser.getMajorId().equals("") ) {
            HashMap<String, Object> resHashmap = new HashMap<>(5);
            resHashmap.put("userName", currentUser.getUserName());
            resHashmap.put("userId", currentUser.getUserId());
            resHashmap.put("gender", currentUser.getGender());
            resHashmap.put("phoneNumber", currentUser.getPhoneNumber());
            resHashmap.put("age", currentUser.getAge());

            return resHashmap;
        }
//        String userId = req.get("userId");
        else
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

        return infoService.fuzzyQueryUserInfo(req);

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

    @ResponseBody
    @PostMapping(value = "/classT", consumes = "application/json", produces = "application/json")
    public ArrayList<User> getStudentListSelectedCourse(@RequestBody Course courseInfo)
    {
        return infoService.getStudentsInfoSelectCourse(courseInfo);
    }

/*    @ResponseBody
    @PostMapping(value = "/classDelStudent", consumes = "application/json", produces = "application/json")
    public Result<String> deleteStudentInSelectCourse(@RequestBody User userInfo)
    {
        if(userService.deleteUserInSelectCourse(userInfo))
            return new Result<String>(Status.SUCCESS, Status.SUCCESS.getMsg());
        else
            return new Result<String>(Status.SUCCESS, Status.SUCCESS.getMsg());
    }*/
    // "专业英语","操作系统","近世代数","普通地质学","信号与系统"

    @ResponseBody
    @PostMapping(value = "/classShowStudent", consumes = "application/json", produces = "application/json")
    public HashMap<String, Boolean> getStudentSelectedClass(@RequestBody User userInfo)
    {
        var courseArrayList = infoService.getCourseInfoSelectedByStudent(userInfo);
        HashMap<String, Boolean> res = new HashMap<>();
        ArrayList<String> totalCourses = new ArrayList<String>(List.of(new String[]{"专业英语", "操作系统", "近世代数", "普通地质学", "信号与系统"}));
        ArrayList<String> courseNameList = new ArrayList<>();
        for (Course course:courseArrayList) {
            courseNameList.add(course.getCourseName());
        }
        for(String course : totalCourses)
        {
            res.put(course, courseNameList.contains(course));
        }

        return res;
    }

    @ResponseBody
    @PostMapping(value = "/classChoose", consumes = "application/json", produces = "application/json")
    public Result<String> addStudentToSelectCourse(@RequestBody HashMap<String, String> userSelectCourseInfo)
    {
        String className = userSelectCourseInfo.get("courseName");
        String userId = userSelectCourseInfo.get("userId");
        User userInfo = infoService.queryExactUserInfo(userId);
        if(userInfo == null) return new Result<String>(Status.ERROR, Status.ERROR.getMsg());

        Course courseInfo = new Course("", className,"","",0.0);
        Course courseRes = infoService.getCourseIdByName(courseInfo);
        if(courseRes == null) return new Result<String>(Status.ERROR, Status.ERROR.getMsg());
        courseInfo.setCourseId(courseRes.getCourseId());

        HashMap<String, String> sqlArgs = new HashMap<>();
        sqlArgs.put("userId", userId);
        sqlArgs.put("courseId", courseInfo.getCourseId());
        if(userService.addUserToSelectCourse(sqlArgs))
            return new Result<String>(Status.SUCCESS, Status.SUCCESS.getMsg());
        else
            return new Result<String>(Status.ERROR, Status.ERROR.getMsg());
    }

    @ResponseBody
    @PostMapping(value = {"/classDel","/classDelStudent"}, consumes = "application/json", produces = "application/json")
    public Result<String> deleteStudentFromSelectCourse(@RequestBody HashMap<String, String> userSelectCourseInfo)
    {
/*        String className = userSelectCourseInfo.get("className");
        String userId = userSelectCourseInfo.get("userId");
        User userInfo = infoService.queryExactUserInfo(userId);
        if(userInfo == null) return new Result<String>(Status.ERROR, Status.ERROR.getMsg());


        Course courseInfo = new Course("", className,"","",0.0);
        Course courseRes = infoService.getCourseIdByName(courseInfo);
        if(courseRes == null) return new Result<String>(Status.ERROR, Status.ERROR.getMsg());
        courseInfo.setCourseId(courseRes.getCourseId());*/
        String className = userSelectCourseInfo.get("courseName");

        Course courseInfo = new Course("", className,"","",0.0);
        Course courseRes = infoService.getCourseIdByName(courseInfo);
        if(courseRes == null) return new Result<String>(Status.ERROR, Status.ERROR.getMsg());

        HashMap<String, String> sqlArgs = new HashMap<>();
        sqlArgs.put("userId", userSelectCourseInfo.get("userId"));
        sqlArgs.put("courseId", courseRes.getCourseId());

        if(userService.deleteUserFromSelectCourse(sqlArgs))
            return new Result<String>(Status.SUCCESS, Status.SUCCESS.getMsg());
        else
            return new Result<String>(Status.ERROR, Status.ERROR.getMsg());
    }

}
