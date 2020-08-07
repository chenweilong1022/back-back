//package com.ozygod;
//
//import cn.hutool.core.io.file.FileWriter;
//import cn.hutool.core.util.RandomUtil;
//import com.lu.jsn.JRandomNameTool;
//import sun.nio.ch.IOUtil;
//
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * @author chenweilong
// * @email 1433471850@qq.coma
// * @date 2020-08-07 10:23
// */
//public class Test {
//
//    public static void main(String[] args) {
//
//
////        String simpleName = JRandomNameTool.getSimpleName();
////        System.out.println(simpleName);
//
//        Set<String> stringSet = new HashSet<>();
//
////        CREATE TABLE `tbl_robot` (
////  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
////  `nickname` varchar(100) NOT NULL,
////  `sex` tinyint(1) DEFAULT '1',
////  `imageid` varchar(255) NOT NULL,
////  `busy` tinyint(1) DEFAULT '0',
////  PRIMARY KEY (`id`)
////) ENGINE=InnoDB AUTO_INCREMENT=101285 DEFAULT CHARSET=utf8;
//
//
//        FileWriter fileWriter = new FileWriter("/Users/chenweilong/Downloads/robot.sql");
//        String sql ="INSERT INTO tbl_robot (nickname,sex,imageid) values ('%s',%s,%s);\n";
//
//        for (int i = 0; i < 100000; i++) {
//            String simpleName = JRandomNameTool.getSimpleName();
//            if (stringSet.contains(simpleName)) {
//                i--;
//            }else {
//                int i1 = RandomUtil.randomInt(0, 2);
//                int i2 = RandomUtil.randomInt(0, 3);
//                stringSet.add(simpleName);
//                String format = String.format(sql, simpleName, i1, i2);
//                fileWriter.append(format);
//            }
//        }
//
//
//
//    }
//}
