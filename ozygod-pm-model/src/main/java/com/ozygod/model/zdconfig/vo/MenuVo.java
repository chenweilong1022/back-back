package com.ozygod.model.zdconfig.vo;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-02-03 11:28
 */
@Data
public class MenuVo {

    @JSONField(ordinal = 1)
    private String path;
    @JSONField(ordinal = 2)
    private String redirect;
    @JSONField(ordinal = 3)
    private String component;
    @JSONField(ordinal = 4)
    private String name;
    @JSONField(ordinal = 5)
    private boolean alwaysShow;
    @JSONField(ordinal = 6)
    private MenuMetaVo meta;
    @JSONField(ordinal = 7)
    private List<MenuVo> children;


    public void add(MenuVo menuVo) {
        if (CollUtil.isEmpty(children)) {
            children = new ArrayList<>();
        }

        children.add(menuVo);
    }

    public String addString1(String str) {
        return "/" + str;
    }


    public String addString(String str) {
        return "#" + str +  "#";
    }

    public void setComponent(String component) {
        this.component = addString(component);
    }

    public void setPath(String path) {
        this.path = addString((path));
    }

    public void setRedirect(String redirect) {
        this.redirect = addString(redirect);
    }

    public void setName(String name) {
        this.name = addString(name);
    }
}
