package com.ozygod.model.zdmanage.bo;

import com.ozygod.model.zdmanage.entity.RoleEntity;
import lombok.Data;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/10/4
 */
@Data
public class RoleBO extends RoleEntity {
    private Integer managerId;
    private String channelId;
}
