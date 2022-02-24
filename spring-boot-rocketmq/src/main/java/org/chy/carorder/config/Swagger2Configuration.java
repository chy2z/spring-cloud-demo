package org.chy.carorder.config;

import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import com.google.common.base.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * Swagger2
 *
 * http://localhost:9090/swagger-ui.html
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration {

  @Bean
  public Docket createRestApi() {
    Predicate<RequestHandler> predicate = new Predicate<RequestHandler>() {
      @Override
      public boolean apply(RequestHandler input) {
        Class<?> declaringClass = input.declaringClass();
        if (declaringClass == BasicErrorController.class)// 排除
        {
          return false;
        }
        if (declaringClass.isAnnotationPresent(RestController.class)) // 被注解的类
        {
          return true;
        }
        if (input.isAnnotatedWith(ResponseBody.class)) // 被注解的方法
        {
          return true;
        }
        return false;
      }
    };
    ParameterBuilder appIdPar = new ParameterBuilder();
    ParameterBuilder tokenPar = new ParameterBuilder();
    ParameterBuilder resourceIdPar = new ParameterBuilder();
    List<Parameter> pars = new ArrayList<Parameter>();
    tokenPar
        .name("X-Auth-Token").description("令牌").modelRef(new ModelRef("string"))
        .parameterType("header").required(false).build();
    appIdPar
        .name("X-Auth-AppId").description("请求端AppId").modelRef(new ModelRef("string"))
        .parameterType("header").required(false).build();
    resourceIdPar
        .name("X-Auth-ResourceId").description("资源Id").modelRef(new ModelRef("string"))
        .parameterType("header").required(false).build();
    pars.add(tokenPar.build());
    pars.add(appIdPar.build());
    pars.add(resourceIdPar.build());
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .useDefaultResponseMessages(false)
        .select()
        .apis(predicate)
        .build()
        .globalOperationParameters(pars);
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("chy")
        .version("1.0.0-SNAPSHOT")
        .build();
  }
}
