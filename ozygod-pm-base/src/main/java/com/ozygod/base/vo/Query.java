/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.ozygod.base.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ozygod.base.dto.PageParam;
import lombok.Getter;

import java.util.LinkedHashMap;

//import com.baomidou.mybatisplus.plugins.Page;

/**
 * 查询参数
 *
 * @author Mark sunlightcs@gmail.com
 * @since 2.0.0 2017-03-14
 */
@Getter
public class Query<T> extends LinkedHashMap<String, Object> {
	private static final long serialVersionUID = 1L;
    /**
     * mybatis-plus分页参数
     */
    private Page<T> page;
    /**
     * 当前页码
     */
    private int currPage = 1;
    /**
     * 每页条数
     */
    private int limit = 10;

    public Query(PageParam pageParam){
        // 分页参数
        if(pageParam.getPage() != null){
            currPage = pageParam.getPage();
        }
        if(pageParam.getLimit() != null){
            limit = pageParam.getLimit();
        }
        //mybatis-plus分页
        this.page = new Page<>(currPage, limit);
    }
}
