package org.maxur.taskun.view.pages.user;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.model.IComponentAssignedModel;
import org.apache.wicket.model.ResourceModel;
import org.maxur.taskun.view.components.BeanWindow;
import org.maxur.taskun.view.model.BatchCommand;
import org.maxur.taskun.view.model.BeanFactory;
import org.maxur.taskun.view.model.Command;
import org.maxur.taskun.view.model.CommandRepository;
import org.maxur.taskun.view.model.UserBean;


/**
 * @author Maxim Yunusov
 * @version 1.0 7/28/11
 */
public class LogonWindow extends ModalWindow implements BeanWindow<UserBean> {

    private static final long serialVersionUID = -4929252976052158981L;

    private static final String PAGE_MAP_NAME = "user_editor";

    private static final String COOKIE_NAME = "user_editor";

    private static final int MINIMAL_HEIGHT = 145;

    private static final int MINIMAL_WIDTH = 350;

    private final CommandRepository commands;

    public LogonWindow(final String id, final CommandRepository commands) {
        super(id);
        this.commands = commands;
        setPageMapName(PAGE_MAP_NAME);
        setCookieName(COOKIE_NAME);
        setMinimalHeight(MINIMAL_HEIGHT);
        setMinimalWidth(MINIMAL_WIDTH);
    }

    @Override
    public void show(final AjaxRequestTarget target) {
        throw new UnsupportedOperationException("Method show(AjaxRequestTarget, EmployeeBean) must be used");
    }

    @Override
    public void show(final AjaxRequestTarget target, final UserBean model) {
        doShow(target, model, new ResourceModel("title.edit"));
    }

    @Override
    public void show(final AjaxRequestTarget target, final BeanFactory<UserBean> factory) {
        doShow(target, factory.getObject(), new ResourceModel("title.create"));
    }

    private void doShow(
            final AjaxRequestTarget target,
            final UserBean model,
            final IComponentAssignedModel<String> title
    ) {
        final Command<UserBean> close = new Command<UserBean>() {
            @Override
            public void execute(final AjaxRequestTarget target, final UserBean model) {
                close(target);
            }
        };

        commands.persist("user.submit", new UserLogonCommand());

        final Command<UserBean> submit
                = new BatchCommand<UserBean>(commands.<UserBean>reserve("user.submit"), close);

        final LogonPanel panel = new LogonPanel(getContentId(), model, submit, close);
        setContent(panel);
        title.wrapOnAssignment(panel);
        setTitle(title);
        super.show(target);
    }

    private static class UserLogonCommand extends Command<UserBean> {

        private static final long serialVersionUID = -642230153599081916L;

        @Override
        public void execute(final AjaxRequestTarget target, final UserBean model) {
        }
    }


}
