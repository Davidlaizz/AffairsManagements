package com.pigeon.affairsmanagements.mapper;

import com.pigeon.affairsmanagements.entity.Course;
import com.pigeon.affairsmanagements.entity.ScheduleDetail;
import com.pigeon.affairsmanagements.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface IInfoMapper {
    HashMap<String,Object> getIndexUserInfo(String userId);

    ArrayList<ScheduleDetail> getTeacherCourseSchedule(String courseId);

    ArrayList<User> fuzzyQueryUserInfo(User user);

    User getExactUserInfo(String userId);

    // Get course id by posted course name
    Course getCourseIdByName(Course courseInfo);

    //Get user id list by querying select_course table
    ArrayList<User> getStudentIdInSelectCourseTable(Course info);

    // Get student selected course
    ArrayList<Course> getStudentSelectedCourse(User userInfo);

}
