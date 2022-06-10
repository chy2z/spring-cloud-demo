package org.chy.carorder.config.propertysource;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chy
 * @Title:
 * @Description:
 * @date 2022/6/8 23:33
 */

/**
 * spring cloud 支持
 *
 * link: org.springframework.cloud.bootstrap.config.PropertySourceBootstrapConfiguration
 *
 * 自定义json格式文件属性解析器
 * @author admin
 */
@Order(0)
public class CusJsonPropertySourceLocator implements PropertySourceLocator {
    private final static String DEFAULT_LOCATION = "classpath:cus.json";

    @Override
    public PropertySource<?> locate(Environment environment) {
        // 读取classpath下的my.json解析
        ResourceLoader resourceLoader = new DefaultResourceLoader(getClass().getClassLoader());
        Resource defResource = resourceLoader.getResource(DEFAULT_LOCATION);
        if (defResource == null) {
            return null;
        }
        return new MapPropertySource("cusJson", mapPropertySource(defResource));
    }

    /**
     * Resource转Map
     *
     * @param resource
     * @return
     */
    private Map<String, Object> mapPropertySource(Resource resource) {
        Map<String, Object> result = new HashMap<>();
        // 获取json格式的Map
        Map<String, Object> fileMap = JSONObject.parseObject(readFile(resource), Map.class);
        // 组装嵌套json
        processorMap("", result, fileMap);
        return result;
    }

    private void processorMap(String prefix, Map<String, Object> result, Map<String, Object> fileMap) {
        if (prefix.length() > 0) {
            prefix += ".";
        }
        for (Map.Entry<String, Object> entrySet : fileMap.entrySet()) {
            if (entrySet.getValue() instanceof Map) {
                processorMap(prefix + entrySet.getKey(), result, (Map<String, Object>) entrySet.getValue());
            } else {
                result.put(prefix + entrySet.getKey(), entrySet.getValue());
            }
        }
    }

    /**
     * JSON格式
     *
     * @param resource
     * @return
     */
    private String readFile(Resource resource) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(resource.getFile());
            return IoUtil.readUtf8(fileInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
