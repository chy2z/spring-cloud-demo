package org.chy.account.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * nacos 配置中心入口: http://127.0.0.1:8848/nacos/
 */
@Configuration
@RefreshScope
public class AppConfig {

    @Value(value = "${appName:default}")
    public String appName;

    @Value(value = "${appVersion:default}")
    public String appVersion;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AppConfig{");
        sb.append("appName='").append(appName).append('\'');
        sb.append(", appVersion='").append(appVersion).append('\'');
        sb.append('}');
        return sb.toString();
    }
}