package com.ozygod.model.zdmanage.bo;

import com.ozygod.model.zdmanage.entity.ShareImageEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2019-05-27
 */
@Data
@ApiModel("分享图业务类")
public class ShareImageBO extends ShareImageEntity {
    @ApiModelProperty("管理员id")
    private Integer managerId;
}
