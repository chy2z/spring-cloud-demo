package org.chy.guard;

import org.chy.guard.aspect.AccessGuardAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动装配创建bean对象
 *
 * Created by chy on 2022/2/14.
 */
@Configuration
public class GuardAutoConfiguration {

    @Bean
    public AccessContext accessContext() {
        return new AccessContext();
    }

    @Bean
    public AccessGuardAspect accessGuardAspect(UserContext userContext) {
        return new AccessGuardAspect(userContext);
    }
}