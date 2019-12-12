package com.ozygod.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @title: 响应码枚举
 * @description:
 * @author: Joey
 * @date: Created in 16:41 2018/8/29 0029.
 */
@Getter
@AllArgsConstructor
public enum ResponseCode {

    S000(0, "请求成功"),
    L001(5, "用户未登录"),
    L002(6, "用户不存在"),
    L003(7, "用户名或密码错误"),
    L004(8, "旧密码验证失败"),
    V001(10, "版本号错误"),
    Q001(15, "查询失败"),
    P001(20, "参数缺失"),
    P002(21, "参数格式错误"),
    P003(22, "数据校验不通过"),
    I001(30, "数据插入失败"),
    I002(31, "数据插入时发生错误"),
    I003(32, "数据已存在"),
    S001(35, "数据保存失败"),
    U001(40, "数据更新失败"),
    U002(41, "数据更新时发生错误"),
    D001(45, "数据删除失败"),
    R001(50, "启动失败"),
    R002(51, "重启失败"),
    T001(60, "停止失败"),
    T002(61, "清空失败"),
    Z001(65, "赠送失败"),
    E500(500, "自定义异常");

    private int code;
    private String title;

}
