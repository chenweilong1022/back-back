package com.ozygod.base.bo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/21
 */
@Data
@ApiModel("数据总数通用业务类")
public class TotalCountBO extends BaseBO {
    private Long totalCount;
}
