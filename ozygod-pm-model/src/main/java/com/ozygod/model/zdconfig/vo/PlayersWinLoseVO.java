package com.ozygod.model.zdconfig.vo;

import com.ozygod.base.annotations.MoneyField;
import com.ozygod.base.enums.MoneyFormatType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-08-05 18:09
 */
@Data
@Accessors(chain = true)
public class PlayersWinLoseVO {

    @MoneyField(moneyFormatTypes = {MoneyFormatType.DIV})
    private BigDecimal win = BigDecimal.ZERO;

    @MoneyField(moneyFormatTypes = {MoneyFormatType.DIV})
    private BigDecimal lose = BigDecimal.ZERO;

    @MoneyField(moneyFormatTypes = {MoneyFormatType.DIV})
    private BigDecimal winningMoney;

    @MoneyField(moneyFormatTypes = {MoneyFormatType.DIV})
    private BigDecimal flowVolume;
}
