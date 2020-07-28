package com.ozygod.s3;

import cn.hutool.core.io.FileUtil;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketConfiguration;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.nio.ByteBuffer;
import java.util.Random;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-01-04 10:17
 */
public class S3Test {


//    public static void main(String[] args) {
////
//        Region region = Region.AP_NORTHEAST_1;
////        S3Client s3 = S3Client.builder().region(region).build();
////
////
////
////        // Put Object
////        PutObjectResponse putObjectResponse = s3.putObject(PutObjectRequest.builder().bucket("bucket1578104563321").key("图片123")
////                        .build(),
////                RequestBody.fromFile(FileUtil.file("/Users/chenweilong/Downloads/images_1578044378980.png"))
////                );
//
//
//        AwsBasicCredentials awsBasicCredentials = AwsBasicCredentials.create(
//                "AKIAVWEF2U46CHYMSIE7",
//                "hI4hBkWGh9rBALzGbdMxn72XxSmBuv9FAOY9cixD");
//
//
//        S3Client s32 = S3Client.builder()
//                .credentialsProvider(StaticCredentialsProvider.create(awsBasicCredentials))
//                .region(region)
//                .build();
//
//        // Put Object
//        PutObjectResponse putObjectResponse = s32.putObject(PutObjectRequest.builder().bucket("bucket1578104563321").key("图片12334")
//                        .build(),
//                RequestBody.fromFile(FileUtil.file("/Users/chenweilong/Downloads/images_1578044378980.png"))
//                );
//
//
//
////        // List buckets
////        ListBucketsRequest listBucketsRequest = ListBucketsRequest.builder().build();
////        ListBucketsResponse listBucketsResponse = s3.listBuckets(listBucketsRequest);
////        listBucketsResponse.buckets().stream().forEach(x -> System.out.println(x.name()));
//
//
//
//    }

    private static ByteBuffer getRandomByteBuffer(int size) {
        byte[] b = new byte[size];
        new Random().nextBytes(b);
        return ByteBuffer.wrap(b);
    }

    private static void createBucketRequest(Region region,S3Client s3) {
        /**
         * 创建存储桶
         */
        String bucket = "bucket" + System.currentTimeMillis();
        System.out.println(bucket);

        CreateBucketRequest createBucketRequest = CreateBucketRequest
                .builder()
                .bucket(bucket)
                .createBucketConfiguration(CreateBucketConfiguration.builder()
                        .locationConstraint(region.id())
                        .build())
                .build();
        s3.createBucket(createBucketRequest);
    }


}
