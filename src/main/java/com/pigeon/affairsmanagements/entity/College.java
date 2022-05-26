package com.pigeon.affairsmanagements.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class College {
    Integer id;
    String courseId;
    String userId;
    String totalScore;
}
