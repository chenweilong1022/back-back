package com.ozygod.model.zdconfig.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-02-03 11:31
 */
@Data
public class MenuMetaVo {

    @JSONField(ordinal = 1)
    private String title;
    @JSONField(ordinal = 2)
    private String path;
    @JSONField(ordinal = 3)
    private boolean auth;
    @JSONField(ordinal = 4)
    private String icon;

    public String addString(String str) {
        return "#" + str +  "#";
    }

    public String addString1(String str) {
        return "/" + str;
    }


    public void setTitle(String title) {
        this.title = addString(title);
    }

    public void setPath(String path) {
        this.path = addString((path));
    }


    public void setIcon(String icon) {
        this.icon = addString(icon);
    }
}
