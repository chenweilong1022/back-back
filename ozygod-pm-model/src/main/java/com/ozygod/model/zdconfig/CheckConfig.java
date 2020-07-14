package com.ozygod.model.zdconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-07-14 14:04
 */
@Component
@ConfigurationProperties(prefix="check")
public class CheckConfig {

    private Boolean whitelist;
    private Boolean googlecode;

    public Boolean getWhitelist() {
        return whitelist;
    }

    public void setWhitelist(Boolean whitelist) {
        this.whitelist = whitelist;
    }

    public Boolean getGooglecode() {
        return googlecode;
    }

    public void setGooglecode(Boolean googlecode) {
        this.googlecode = googlecode;
    }
}
