package org.maxur.taskun.services.Impl;

import org.maxur.commons.service.Logger;
import org.maxur.commons.service.impl.BaseLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/21/11
 */
@Configuration
public class ServiceConfiguration {

    /**
     * The Base Application logger.
     * @return The Base Application logger.
     */
    @Bean
    public Logger logger() {
        return BaseLogger.instance();
    }

    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }

}
