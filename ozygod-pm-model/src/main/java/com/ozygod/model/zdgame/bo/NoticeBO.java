package com.ozygod.model.zdgame.bo;

import com.ozygod.model.zdgame.entity.NoticeEntity;
import lombok.Data;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/29
 */
@Data
public class NoticeBO extends NoticeEntity {
    private String channelName;
    private Integer managerId;
    private String channel;
    private String noticeType;
}
