package org.maxur.taskun.view.components;

import images.ImagesScope;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.PackageResource;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.model.Model;
import org.maxur.taskun.view.model.AjaxObserver;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/27/11
 */
public class UpdatedImage extends Image implements AjaxObserver {

    private static final long serialVersionUID = 4616885386978793784L;

    private final Model<String> model;

    public UpdatedImage(final String id, final Model<String> model) {
        super(id, PackageResource.get(ImagesScope.class, model.getObject()));
        this.model = model;
        update();
    }

    @Override
    public void update(AjaxRequestTarget target) {
        update();
        target.addComponent(this);
    }

    @Override
    public Component get() {
        return this;
    }

    private void update() {
        final PackageResource resource = PackageResource.get(ImagesScope.class, model.getObject());
        setImageResource(resource);
        add(new SimpleAttributeModifier("src", resource.getAbsolutePath()));
    }


}
