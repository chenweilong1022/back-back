package com.ozygod.model.zdconfig.vo.game;

import lombok.Data;

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

    private int xianWin;
    private int zhuangWin;
    private int equal;
}
