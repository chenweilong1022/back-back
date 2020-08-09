package com.ozygod.model.zdgame.vo;

import cn.hutool.core.collection.CollUtil;
import lombok.Data;

import java.util.List;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-08-09 15:54
 */
@Data
public class AgentTreeVo {


    private String nickname;
    private Long showId;

    private List<AgentTreeVo> childrens;

    private String label;


    private Integer count;

    private Integer teamCount;

    public Integer getCount() {
        if (CollUtil.isEmpty(childrens)) {
            return 0;
        }
        return this.childrens.size();
    }

    public Integer getTeamCount() {
        if (CollUtil.isEmpty(childrens)) {
            return 0;
        }
        return this.getCount() + this.childrens.stream().mapToInt(agentTreeVo -> agentTreeVo.getTeamCount()).sum();
    }

    public String getLabel() {
        return String.format("%s-(%s)",this.nickname,this.showId);
    }
}
