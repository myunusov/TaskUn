package org.maxur.taskun.view;

import org.apache.wicket.Session;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/26/11
 */
@Component(value = "webLocaleInterpolator")
public class WicketSessionLocaleMessageInterpolator extends ResourceBundleMessageInterpolator {

    @Override
    public String interpolate(String message, Context context) {
        Locale locale = Session.get().getLocale();
        return super.interpolate(message, context, locale);
    }

    @Override
    public String interpolate(String message, Context context, Locale locale) {
//        Locale locale1 = Session.get().getLocale();
        //TODO must be l11n
        return super.interpolate(message, context, locale);
    }


}

