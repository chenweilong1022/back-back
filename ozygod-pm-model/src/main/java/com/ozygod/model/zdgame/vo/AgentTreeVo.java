package com.ozygod.model.zdgame.vo;

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

    public String getLabel() {
        return String.format("%s-(%s)",this.nickname,this.showId);
    }
}
