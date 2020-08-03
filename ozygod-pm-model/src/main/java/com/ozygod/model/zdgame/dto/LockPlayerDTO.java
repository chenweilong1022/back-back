package com.ozygod.model.zdgame.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-08-03 15:49
 */
@Data
@Accessors(chain = true)
public class LockPlayerDTO {

    /**
     * 开始玩家id
     */
    @ApiModelProperty(value = "开始玩家id")
    private Long start_uid;
    /**
     * 结束玩家id
     */
    @ApiModelProperty(value = "结束玩家id")
    private Long end_uid;
}
