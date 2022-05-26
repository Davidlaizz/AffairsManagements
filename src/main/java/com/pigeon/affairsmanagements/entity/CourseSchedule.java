package com.pigeon.affairsmanagements.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class CourseSchedule {
    Integer id;
    String teacherId;
    String courseId;
    Date courseDate;
}
