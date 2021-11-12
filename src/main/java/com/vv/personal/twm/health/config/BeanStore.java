package com.vv.personal.twm.health.config;

import com.vv.personal.twm.health.auth.Authorizer;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

/**
 * @author Vivek
 * @since 29/10/21
 */
@Configuration
public class BeanStore {

    @Bean
    @Scope("prototype")
    public StopWatch procureStopWatch() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        return stopWatch;
    }

    @Bean
    public Authorizer authorizer() {
        return new Authorizer(new Pbkdf2PasswordEncoder());
    }
}