package org.maxur.taskun.view.components;

import org.apache.wicket.markup.html.form.IChoiceRenderer;

import java.util.Map;

/**
* @author Maxim Yunusov
* @version 1.0 7/27/11
*/
public class MapRenderer<T> implements IChoiceRenderer<T> {

    private static final long serialVersionUID = 720387192651494080L;

    Map<T, String> map;

    public MapRenderer(final Map<T, String> map) {
        this.map = map;
    }

    @Override
    public Object getDisplayValue(T object) {
        return map.get(object);
    }

    @Override
    public String getIdValue(T object, int index) {
        return object.toString();
    }
}
