//package com.ozygod.base.enums;
//
//import com.ozygod.base.utils.EnumUtil;
//import lombok.Getter;
//
///**
// * 登录注册渠道类型枚举
// * @author chenweilong
// * @email 1433471850@qq.com
// * @date 2019/6/17
// */
//@Getter
//public enum AccountLoginRegisterType implements BaseEnum {
//
//    GUEST(1,"guest"),
//    OTHER(2,"非guest"),
//    IOS(3,"ios"),
//    ANDROID(4,"andorid");
////    FIVE(5,"充值金额(元)"),
////    SIX(6,"总充值成功率"),
////    SEVEN(7,"兑换金额(元)"),
////    EIGHT(8,"今日兑换率");
//
//
//    private Integer key;
//    private String value;
//
//    private String icon;
//
//    public static final String DESCRIBE = "1:不是 2:是 ";
//
//
//    /**
//     * 内部接口,实现该接口可获得中文描述
//     */
//    public interface IndexCardStr {
//
//        Integer getAccountLoginRegisterType();
//
//
//        default String getAccountLoginRegisterTypeStr() {
//            return EnumUtil.queryValueByKey(getAccountLoginRegisterType(), AccountLoginRegisterType.values());
//        }
//    }
//
//    AccountLoginRegisterType(Integer key, String value) {
//        this.key = key;
//        this.value = value;
//    }
//
//}
