package com.ozygod.model.zdlog.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-17 14:52
 */
@Data
@Accessors(chain = true)
public class TblRecordAccountOnlinePlayingStatictisVo {

    /**
     * 在线数
     */
    @ApiModelProperty(required=false,value="在线数")
    private Integer onlineNumber;
}
