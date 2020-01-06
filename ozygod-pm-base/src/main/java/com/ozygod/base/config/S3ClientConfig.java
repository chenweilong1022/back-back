package com.ozygod.base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

/**
 * s3客户端配置
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-01-06 10:47
 */
@Configuration
public class S3ClientConfig {


    @Autowired
    private AwsConfig awsConfig;

    @Bean
    public S3Client s3Client() {
        Region region = Region.AP_NORTHEAST_1;
        AwsBasicCredentials awsBasicCredentials = AwsBasicCredentials.create(
                awsConfig.getAccessKeyId(),
                awsConfig.getSecretAccessKey()
        );
        S3Client s3Client = S3Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(awsBasicCredentials))
                .region(region)
                .build();
        return s3Client;
    }


}
