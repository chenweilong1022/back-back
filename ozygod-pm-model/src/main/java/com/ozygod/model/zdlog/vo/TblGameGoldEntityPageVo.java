package com.ozygod.model.zdlog.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ozygod.model.zdlog.entity.TblGameGoldEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 金币日志
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-31 13:48:42
 */
@Data
@TableName("tbl_game_gold")
@ApiModel("金币日志")
@Accessors(chain = true)
public class TblGameGoldEntityPageVo implements Serializable {


	private TblGameGoldEntityTotalVo tblGameGoldEntityTotalVo;
	private List<TblGameGoldEntity> tblGameGoldEntities;

}
