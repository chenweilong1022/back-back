package com.ozygod.model.zdmanage.bo;

import com.ozygod.model.zdmanage.entity.RobotMenuEntity;
import lombok.Data;

import java.util.List;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/10
 */
@Data
public class RobotMenuBO extends RobotMenuEntity {
    private String status;
    private List<RobotMenuBO> children;
}
