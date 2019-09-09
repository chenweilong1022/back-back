package com.ozygod.model.zdmanage.bo;

import com.ozygod.model.zdmanage.entity.ManagerEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/5
 */
@Data
@ApiModel("管理员业务类")
public class ManagerBO extends ManagerEntity {
    private String token;
    private String oldPassword;
    private String newPassword;
    private List<MenuBO> menus;
    private Integer whoId;
    private String roleName;
    @ApiModelProperty("管理员ID")
    private Integer managerId;
}
