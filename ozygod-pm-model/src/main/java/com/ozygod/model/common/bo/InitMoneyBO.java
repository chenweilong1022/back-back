package com.ozygod.model.common.bo;

import com.ozygod.base.bo.BaseBO;
import lombok.Data;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/11
 */
@Data
public class InitMoneyBO extends BaseBO {
    /**
     * 进⼊入房间携带⾦金金币-最小值
     */
    private Integer min;
    /**
     * 进⼊入房间携带⾦金金币-最大值
     */
    private Integer max;
}
