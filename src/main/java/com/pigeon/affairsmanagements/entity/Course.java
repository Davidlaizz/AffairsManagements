package com.pigeon.affairsmanagements.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Course {
    String courseId;
    String courseName;
    String teacherId;
    String courseType;
    Double courseScore;
}
