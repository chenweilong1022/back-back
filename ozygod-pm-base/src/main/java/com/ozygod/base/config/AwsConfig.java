package com.ozygod.base.config;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-01-06 10:39
 */
@Data
@Accessors(chain = true)
@ConfigurationProperties(prefix="aws")
public class AwsConfig {

    private String accessKeyId;

    private String secretAccessKey;

}
