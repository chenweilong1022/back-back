package com.ozygod.model.common.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-07-25 22:36
 */
@Data
@ApiModel("胜率控制dto")
public class WinRateControlDTO {

    private Double timeInterval;
    private Double releaseLucky;
    private Double shareLucky;
    private Double releaseProbability;
    private Double shareProbability;
}
