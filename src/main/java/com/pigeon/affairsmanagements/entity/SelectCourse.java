package com.pigeon.affairsmanagements.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SelectCourse {
    Integer id;
    String courseId;
    String userId;
    Double totalScore;
}
