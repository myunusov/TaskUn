package org.maxur.taskun.view.pages;

import org.apache.wicket.markup.html.WebPage;
import org.maxur.taskun.view.model.MenuItems;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring configuration for Web Application.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/13/11
 */
@Configuration
public class ViewConfigurator {

    /**
     * Home Page Class bean.
     *
     * @return the Home Page Class bean.
     */
    @Bean
    public Class<? extends WebPage> homePage() {
        return HomePage.class;
    }

    /**
     * Create Menu Items as Bean.
     *
     * @return List of Menu Items as MenuItems class.
     */
    @Bean
    public MenuItems menuItems() {
        //TODO MY must be exclude string constants
        final MenuItems result = new MenuItems(3);
        result.add("Главная", HomePage.class, true);
        result.add("Блог", ExamplePage.class);
        result.add("О программе", ExamplePage.class);
        return result;
    }
}


