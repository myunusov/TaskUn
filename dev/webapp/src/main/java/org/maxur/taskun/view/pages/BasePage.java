package org.maxur.taskun.view.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/5/11
 */
public class BasePage extends WebPage {

    private static final long serialVersionUID = -7735630739445684538L;

    public BasePage() {
        add(new Label("title", "ТаскУН: Управление задачами"));
        add(new Label("app_name", "ТаскУН"));
        add(new Label("app_desc", "простое управление задачами"));
    }
}
