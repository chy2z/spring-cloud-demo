package org.chy.carorder.config.propertysource;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chy
 * @Title:
 * @Description:
 * @date 2022/6/8 22:32
 */

/**
 * 自定义解析 application.json
 */
public class CusJsonPropertySourceLoader implements PropertySourceLoader {

    @Override
    public List<PropertySource<?>> load(String name, Resource resource) throws IOException {
        return Collections.singletonList(new MapPropertySource("applicationJson", mapPropertySource(resource)));
    }

    @Override
    public String[] getFileExtensions() {
        return new String[]{"json"};
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