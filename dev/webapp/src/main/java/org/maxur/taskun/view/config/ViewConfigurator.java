package org.maxur.taskun.view.config;

import org.apache.wicket.markup.html.WebPage;
import org.maxur.taskun.view.WicketSessionLocaleMessageInterpolator;
import org.maxur.taskun.view.model.MenuItems;
import org.maxur.taskun.view.pages.ExamplePage;
import org.maxur.taskun.view.pages.employee.HomePage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.MessageInterpolator;
import javax.validation.Validator;

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
        final MenuItems result = new MenuItems(3);
        result.add("menu.item.main", HomePage.class, true);
        result.add("menu.item.task", ExamplePage.class);
        result.add("menu.item.self", ExamplePage.class);
        return result;
    }

    @Bean
    public Validator validator() {
/*        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();*/
        final LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
        validatorFactoryBean.setMessageInterpolator(new WicketSessionLocaleMessageInterpolator());
        return validatorFactoryBean;
    }

    @Bean
    public MessageInterpolator messageInterpolator() {
        return new WicketSessionLocaleMessageInterpolator();
    }


}


