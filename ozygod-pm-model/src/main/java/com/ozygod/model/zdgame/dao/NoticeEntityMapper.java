package com.ozygod.model.zdgame.dao;

import com.ozygod.model.zdgame.bo.NoticeBO;
import com.ozygod.model.common.dto.PlatformDto;
import com.ozygod.model.zdgame.entity.NoticeEntity;

import java.util.List;

public interface NoticeEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NoticeEntity record);

    int insertSelective(NoticeEntity record);

    NoticeEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NoticeEntity record);

    int updateByPrimaryKey(NoticeEntity record);

    /**
     * 查询公告列表
     * @param dto
     * @return
     */
    List<NoticeBO> listNoticeByQry(PlatformDto dto);

    /**
     * 查询公告列表总数
     * @param dto
     * @return
     */
    int totalNoticeByQry(PlatformDto dto);

    /**
     * 检查是否存在指定条件的公告数
     * @param noticeId
     * @return
     */
    int existNoticeById(Integer noticeId);
}