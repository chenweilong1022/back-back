package com.ozygod.base.config;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.regions.Region;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-01-06 10:39
 */
@Data
@Accessors(chain = true)
@Component
@ConfigurationProperties(prefix="aws")
public class AwsConfig {

    private String accessKeyId;

    private String secretAccessKey;

    private String bucket;

    private Region region = Region.AP_NORTHEAST_2;

}
