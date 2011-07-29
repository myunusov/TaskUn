package org.maxur.taskun.view.pages.user;

import images.ImagesScope;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.feedback.ComponentFeedbackMessageFilter;
import org.apache.wicket.markup.html.PackageResource;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.maxur.taskun.view.behavior.DefaultFocusBehavior;
import org.maxur.taskun.view.behavior.DynamicUpdateBehavior;
import org.maxur.taskun.view.behavior.ErrorHighlightBehavior;
import org.maxur.taskun.view.behavior.Jsr303FormValidator;
import org.maxur.taskun.view.components.CommandButton;
import org.maxur.taskun.view.model.Command;
import org.maxur.taskun.view.model.UserBean;

/**
 * Panel for displaying the contents of user form.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/28/11
 */
public class LogonPanel extends Panel {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 7058621083873444576L;

    public LogonPanel(
            final String id,
            final UserBean bean,
            final Command<UserBean> onSubmit,
            final Command<UserBean> onClose
    ) {
        super(id, bean);

        final CompoundPropertyModel<UserBean> model = new CompoundPropertyModel<UserBean>(bean.getObject());

        final Form<UserBean> form = new TaskunStatelessForm<UserBean>("user_form", model, UserBean.class);
        form.setOutputMarkupId(true);
        add(form);
        final FeedbackPanel feedback = new FeedbackPanel("feedback", new ComponentFeedbackMessageFilter(form));
        add(feedback.setOutputMarkupId(true));

        final TextField<String> userName = new TextField<String>("userName");
        form.add(userName);

        final PasswordTextField password = new PasswordTextField("password");
        form.add(password);

        final Image image = new Image("key_img", PackageResource.get(ImagesScope.class, "Key.png"));
        add(image);

        final AjaxButton logon = new CommandButton<UserBean>(
                "user_submit", form, onSubmit, bean
        ) {
            @Override
            protected void onError(final AjaxRequestTarget target, final Form<?> form) {
                target.addComponent(feedback);
                target.addComponent(userName);
                target.addComponent(password);
            }
        };
        add(logon);

        final Form fakeForm = new Form("fake_form");
        add(fakeForm);
        final AjaxButton cancel = new CommandButton<UserBean>(
                "user_cancel", fakeForm, onClose, bean
        );
        cancel.setDefaultFormProcessing(false);
        add(cancel);

    }
}


class TaskunStatelessForm<T> extends StatelessForm<T> {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 570532221624657729L;

    private final Class<T> clazz;

    public TaskunStatelessForm(final String id, final IModel<T> model, final Class<T> clazz) {
        super(id, model);
        this.clazz = clazz;
    }

    public void onBeforeRender() {
        super.onBeforeRender();
        ErrorHighlightBehavior.addToAllComponent(this);
        DynamicUpdateBehavior.addToAllComponent(this);
        Jsr303FormValidator.<T>addToAllComponent(this, this.clazz);
        DefaultFocusBehavior.addToAll(this);
    }

    public void onError() {
        super.onError();
        DefaultFocusBehavior.setToFirstError(this);
    }

}
