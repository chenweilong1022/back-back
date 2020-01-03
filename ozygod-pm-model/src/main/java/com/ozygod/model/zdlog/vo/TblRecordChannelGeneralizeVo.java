package com.ozygod.model.zdlog.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ozygod.model.zdlog.entity.TblRecordChannelGeneralizeEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 每日渠道推广统计
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-01-02 13:54:15
 */
@Data
@TableName("tbl_record_channel_generalize")
@ApiModel("每日渠道推广统计")
@Accessors(chain = true)
public class TblRecordChannelGeneralizeVo implements Serializable {
	private static final long serialVersionUID = 1L;

	private TblRecordChannelGeneralizeTotalVo tblRecordChannelGeneralizeTotalVo;

	private List<TblRecordChannelGeneralizeEntity> tblRecordChannelGeneralizeEntities;

}
