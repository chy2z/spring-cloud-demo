package org.chy.carorder.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * Json之重写MappingJackson2HttpMessageConverter配置
 */
@Configuration
public class MappingJackson2HttpMessageConverterConfig {

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

        ObjectMapper objectMapper = new ObjectMapper();
        // 反序列化忽略未知属性
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

        // 全局转化Long类型为String,解决Jackson序列化时web精度确实问题
        SimpleModule simpleModule=new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);

        // 设置日期
        //SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //objectMapper.setDateFormat(smt);

        // 设置支持的Content-Type的值
        //List<MediaType> list = new ArrayList<MediaType>();
        //list.add(MediaType.APPLICATION_JSON);
        //list.add(MediaType.APPLICATION_JSON_UTF8);
        //converter.setSupportedMediaTypes(list);

        converter.setObjectMapper(objectMapper);

        return converter;
    }
}
