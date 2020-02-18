package com.ozygod.account.web;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;
import com.ozygod.base.bo.ResponseBO;
import com.ozygod.base.validator.Assert;
import com.ozygod.model.zdmanage.dto.ListFileNamesDto;
import com.ozygod.model.zdmanage.dto.UpdatingProgramDto;
import com.ozygod.model.zdmanage.service.UpdatingProgramService;
import com.ozygod.model.zdmanage.vo.ListFileNamesVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 更新程序controller
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-02-17 17:16
 */
@RestController
@RequestMapping("updatingprogram")
@Slf4j
public class UpdatingProgramController {

    @Value("${files_url}")
    private String filesUrl;
    @Autowired
    private UpdatingProgramService updatingProgramService;

    public static void main(String[] args) {
        List<String> strings = FileUtil.listFileNames("/Users/chenweilong/Desktop/upload/logs/ozygod-pm");
        System.out.println(strings);
    }


    /**
     * 查询目录下所有文件
     */
    @RequestMapping("/listFileNames")
    public ResponseBO listFileNames(@RequestBody ListFileNamesDto listFileNamesDto) {
        return ResponseBO.data(FileUtil.listFileNames(listFileNamesDto.getUrl()).stream().map(name -> new ListFileNamesVo().setName(name)).collect(Collectors.toList()));
    }

    /**
     * 根据参数更新程序
     */
    @RequestMapping("/updatingprogram")
    public ResponseBO updatingprogram(@RequestBody UpdatingProgramDto updatingProgramDto) {
        Assert.isBlank(updatingProgramDto.getCatalog(),"目录不能为空");
        Assert.isBlank(updatingProgramDto.getUrl(),"脚本文件不能为空");
        //脚本全路径
        String sh =  updatingProgramDto.getUrl();
        StringBuilder sb = new StringBuilder(sh);
        if (ArrayUtil.isNotEmpty(updatingProgramDto.getParams())) {
            String[] params = updatingProgramDto.getParams();
            for (String param : params) {
                sb.append(" ");
                sb.append(param);
            }
        }
        String fullPathLog = filesUrl + File.separator + sb.toString().replace(" ","-") + ".log";
        if (FileUtil.exist(fullPathLog)) {
            FileUtil.del(fullPathLog);
        }
        sb.append(" > ");
        sb.append(fullPathLog);
        log.info(updatingProgramDto.getCatalog() + File.separator + sb.toString());
        updatingProgramService.run(updatingProgramDto.getCatalog() + File.separator + sb.toString());
        return ResponseBO.ok();
    }

    /**
     * 查看更新程序日志
     */
    @RequestMapping("/showLogs")
    public ResponseBO showLogs(@RequestBody UpdatingProgramDto updatingProgramDto) {
        Assert.isBlank(updatingProgramDto.getCatalog(),"目录不能为空");
        Assert.isBlank(updatingProgramDto.getUrl(),"脚本文件不能为空");
        //脚本全路径
        String sh = updatingProgramDto.getUrl();
        StringBuilder sb = new StringBuilder(sh);
        if (ArrayUtil.isNotEmpty(updatingProgramDto.getParams())) {
            String[] params = updatingProgramDto.getParams();
            for (String param : params) {
                sb.append(" ");
                sb.append(param);
            }
        }
        String fullPathLog = filesUrl + File.separator + sb.toString().replace(" ","-") + ".log";
        if (!FileUtil.exist(fullPathLog)) {
            return ResponseBO.data("");
        }
        String s = FileUtil.readString(fullPathLog, "UTF-8");
        return ResponseBO.data(s);
    }



}
