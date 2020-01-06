package com.ozygod.api.web;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import com.ozygod.base.bo.ResponseBO;
import com.ozygod.base.config.AwsConfig;
import com.ozygod.base.utils.Constant;
import com.ozygod.base.validator.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.http.SdkHttpResponse;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2019-01-02
 */
@Slf4j
@RequestMapping("/api/s3/files")
@RestController
//@Api(value = "/api/s3/files", description = "亚马逊的存储服务")
public class AwsS3FileApiController implements Serializable {

    private static final long serialVersionUID = -6987791929197226491L;

    @Value("${files_url}")
    private String filesUrl;

    // 图片原图
    private static final String PREFIX_IMAGE = "images";
    // 图片缩略图
    private static final String PREFIX_THUMB = "thumb";
    @Autowired
    private S3Client s3Client;
    @Autowired
    private AwsConfig awsConfig;

    /**
     * 上传图片
     * @param file
     * @return
     */
    @RequestMapping(value = "/upload/images", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO uploadImage(@RequestParam("image") MultipartFile file) throws IOException {

        RequestBody requestBody = null;
        try {
            requestBody = RequestBody.fromBytes(IoUtil.readBytes(file.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**
         * 上传文件路径
         */
        String originalFilename = file.getOriginalFilename();
        String date = DateUtil.format(new Date(), "yyyyMMdd");
        String url = PREFIX_IMAGE + File.separator + date + File.separator + originalFilename;

        PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(awsConfig.getBucket()).key(url)
                .acl("public-read")
                .build();



        /**
         * 上传到s3桶里
         */
        PutObjectResponse putObjectResponse = s3Client.putObject(putObjectRequest, requestBody);

        SdkHttpResponse sdkHttpResponse = putObjectResponse.sdkHttpResponse();


        Assert.isTrue(!sdkHttpResponse.isSuccessful(),"上传失败");

        StringJoiner responseStringJoiner = new StringJoiner("","https://", ".amazonaws.com/" + url);
        responseStringJoiner.add(awsConfig.getBucket());
        responseStringJoiner.add(".s3-");
        responseStringJoiner.add(awsConfig.getRegion().id());

        String responseUrl = responseStringJoiner.toString();

        ResponseBO responseBO = new ResponseBO();
        Map<String, String> resultMap = new HashMap<>();

        resultMap.put("downloadUrl", responseUrl);
        resultMap.put("showUrl", responseUrl);
        resultMap.put("thumbUrl", responseUrl);
        responseBO.setData(resultMap);
        return responseBO;
    }
}
