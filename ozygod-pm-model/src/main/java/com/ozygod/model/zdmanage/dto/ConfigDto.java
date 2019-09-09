package com.ozygod.model.zdmanage.dto;

import com.ozygod.base.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2019-03-12
 */
@Data
@ApiModel("配置管理查询类")
public class ConfigDto extends BaseDto {
    @ApiModelProperty("帐号类型")
    private Byte type;
    @ApiModelProperty("状态")
    private Integer state;
    @ApiModelProperty("排序方向，0：asc，1:desc")
    private Integer sortDirection;
    @ApiModelProperty("配置项编码")
    private String code;
    @ApiModelProperty("启用状态")
    private Integer enabled;
}
