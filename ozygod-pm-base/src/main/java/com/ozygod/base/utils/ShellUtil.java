package com.ozygod.base.utils;

import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-01-09 15:24
 */
@Slf4j
public class ShellUtil {


    /**
     * 获取文件行数
     * @return
     */
    public static long getFileLinesNumber(String result,String fileUrl) {
        long number = 0L;
        if (StrUtil.isNotBlank(result)) {
            String replace = result.replace(fileUrl, "").trim();
            number = Long.valueOf(replace);
        }
        return number;
    }


    /**
     * 获取文件行数
     * @return
     */
    public static long getFileLinesNumber(String fileUrl) {
        String command = String.format("wc -l %s", fileUrl);
        return getFileLinesNumber(run(command),fileUrl);
    }



    public static List<String> getFileAppointLines(String result) {

        return null;
    }


    /**
     * 根据指定行查找指定文件
     * @param start 开始
     * @param end 结束
     * @param fileUrl 文件
     * @param monitorKeyword 查找字符串
     * @return
     */
    public static String getFileAppointLines(long start,long end,String fileUrl,String monitorKeyword) {
        String sed = String.format("sed -n '%s,%sp' %s | grep -n -E '%s'",start,end,fileUrl,monitorKeyword);
        String result = run(sed);
        return result;
    }


//    /**
//     * 根据指定行查找指定文件
//     * @param start 开始
//     * @param end 结束
//     * @param fileUrl 文件
//     * @param monitorKeyword 查找字符串
//     * @return
//     */
//    public static List<SedMatchingVo> getFileAppointLinesError(long start,long end,String fileUrl,String monitorKeyword) {
//        String fileAppointLines = getFileAppointLines(start, end, fileUrl, monitorKeyword);
//        String[] fileAppointLinessplit = fileAppointLines.split("\n");
//
//        List<SedMatchingVo> sedMatchingVos = new ArrayList<>();
//
//        for (String s : fileAppointLinessplit) {
//            int i = s.indexOf(":");
//            if (i == -1) {
//                continue;
//            }
//            String setLinesNumber = s.substring(0, i);
//            String logString = s.substring(i + 1, s.length());
//
//            SedMatchingVo sedMatchingVo = new SedMatchingVo();
//            sedMatchingVo.setLinesNumber(Long.valueOf(setLinesNumber) + start);
//            sedMatchingVo.setLogString(logString);
//            sedMatchingVos.add(sedMatchingVo);
//        }
//        return sedMatchingVos;
//    }



    /**
     * 执行shell命令
     * @param command
     * @return
     */
    public static String run(String command) {
        String[] cmd = {
                "/bin/sh",
                "-c",
                command
        };
        Process p = null;
        try {
            p = Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("command is {}",command);
        String result = RuntimeUtil.getResult(p);
        log.info("result = {}",result);
        RuntimeUtil.destroy(p);
        return result;
    }


}
