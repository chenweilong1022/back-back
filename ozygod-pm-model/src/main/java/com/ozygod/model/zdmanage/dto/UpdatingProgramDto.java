package com.ozygod.model.zdmanage.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-02-18 11:18
 */
@Data
@Accessors(chain = true)
public class UpdatingProgramDto {

    private String catalog;
    private String url;
    private String[] params;
}
