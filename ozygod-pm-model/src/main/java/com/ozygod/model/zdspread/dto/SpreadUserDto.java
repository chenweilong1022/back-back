package com.ozygod.model.zdspread.dto;

import com.ozygod.base.dto.BaseDto;
import lombok.Data;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/22
 */
@Data
public class SpreadUserDto extends BaseDto {
    private String loginName;
    private String loginPwd;
    private Integer spreadUserId;
    private Integer superId;
    private Integer ratio;

}
