package org.maxur.taskun.view.pages;

import org.apache.wicket.markup.html.WebPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  Spring configuration for Web Application.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/13/11
 */
@Configuration
public class HomePageConfigurator {

    /**
     * Home Page Class bean.
     * @return the Home Page Class bean.
     */
    @Bean
    public Class<? extends WebPage> homePage() {
        return HomePage.class;
    }


}
