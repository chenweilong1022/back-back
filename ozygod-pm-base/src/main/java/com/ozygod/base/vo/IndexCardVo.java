package com.ozygod.base.vo;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-16 16:04
 */
@Data
@Accessors(chain = true)
public class IndexCardVo {

    private String title;
    private String value;
    private String icon;


    public String getColor() {
        String s = "#000000,#FF0000,#00FF00,#0000FF,#FFFF00,#00FFFF,#FF00FF,#C0C0C0,#FFFFFF";
        String[] split = StrUtil.split(s, ",");
        return split[RandomUtil.randomInt(0,split.length - 1)];
    }

    public List<Integer> getData() {
        List<Integer> data = new ArrayList<>(7);
        for (int i = 0; i < 7; i++) {
            data.add(RandomUtil.randomInt(1,120));
        }
        return data;
    }
}
