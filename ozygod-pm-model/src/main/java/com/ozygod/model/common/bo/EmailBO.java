package com.ozygod.model.common.bo;

import com.ozygod.base.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2019-05-02
 */
@Data
@ApiModel("邮件业务类")
public class EmailBO extends BaseBO {
    @ApiModelProperty("收件人")
    private String receivers;
    @ApiModelProperty("邮件标题")
    private String title;
    @ApiModelProperty("邮件内容")
    private String content;
    @ApiModelProperty("发送人")
    private String sender;
}
