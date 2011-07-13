package org.maxur.taskun.view.pages;

import org.apache.wicket.markup.html.WebPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/13/11
 */
@Configuration
public class HomePageConfigurator {

    @Bean
    public Class<? extends WebPage> homePage() {
        return HomePage.class;
    }


}
