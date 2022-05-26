package com.pigeon.affairsmanagements.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ScheduleDetail {
    String courseId;
    String courseName;
    Date courseDate;
}
