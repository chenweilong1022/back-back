package com.ozygod.model.zdmanage.bo;

import com.ozygod.model.zdmanage.entity.MenuEntity;
import lombok.Data;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/18
 */
@Data
public class MenuBO extends MenuEntity {
    private Integer roleId;
    private Integer whoId;
    private String menuIds;
}
