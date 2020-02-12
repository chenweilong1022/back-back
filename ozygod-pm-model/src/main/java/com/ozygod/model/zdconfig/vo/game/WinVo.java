package com.ozygod.model.zdconfig.vo.game;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-02-04 09:32
 */
@Data
public class WinVo {
    /**
     * xianWin : 45
     * zhuangWin : 45
     * equal : 10
     */

    private BigDecimal xianWin;
    private BigDecimal zhuangWin;
    private BigDecimal equal;
}
