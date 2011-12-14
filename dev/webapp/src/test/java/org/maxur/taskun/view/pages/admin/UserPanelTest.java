package org.maxur.taskun.view.pages.admin;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.jmock.Expectations;
import org.junit.Test;
import org.maxur.taskun.view.commands.CommandRepositoryImpl;
import org.maxur.taskun.view.components.HiddenPagingNavigator;
import org.maxur.taskun.view.model.ModelList;
import org.maxur.taskun.view.model.employee.EmployeeBean;
import org.maxur.taskun.view.model.employee.EmployeeBuilder;
import org.maxur.taskun.view.model.employee.EmployeesGroup;
import org.maxur.taskun.view.pages.AbstractPanelTest;

import java.util.Collections;
import java.util.List;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/16/11
 */
public class UserPanelTest extends AbstractPanelTest {


    private EmployeesGroup group;

    private ModelList<EmployeeBean> list;

    @Override
    protected void start() {
    }


    @Override
    protected Panel makeTestPanel(String panelId) {
        final CommandRepositoryImpl dummyCommandRepository = new CommandRepositoryImpl();
        return new UserPanel(panelId, group, dummyCommandRepository);
    }


    @Test
    public void testWicketPanel() throws Exception {
        context.checking(makeExpectations(0));
        super.start();
        tester.assertNoErrorMessage();
        tester.assertNoInfoMessage();
    }

    @Test
    public void testUserView() throws Exception {
        context.checking(makeExpectations(0));
        super.start();
        tester.assertComponent("panel:employees", UserPanel.UserView.class);
    }

    @Test
    public void testInvisibleNavigator() {
        context.checking(makeExpectations(1));
        super.start();
        tester.assertInvisible("panel:navigator");
    }

    @Test
    public void testNavigator() {
        context.checking(makeExpectations(100));
        super.start();
        tester.assertComponent("panel:navigator", HiddenPagingNavigator.class);
    }


    private Expectations makeExpectations(final int count) {
        group = context.mock(EmployeesGroup.class);
        final EmployeeBean employeeBean = EmployeeBuilder.makeEmployeeBean();
        final List<EmployeeBean> employeeBeans = Collections.nCopies(count, employeeBean);
        list = new ModelList<EmployeeBean>(employeeBeans);
        return new Expectations() {{
            allowing(group).getObject();
            will(returnValue(list));
            allowing(group).iterator(0, 15);
            will(returnValue(list.subList(0, 15 > count ? count : 15).iterator()));
            allowing(group).iterator(0, 1);
            if (count > 0) {
                will(returnValue(list.subList(0, 1).iterator()));
            } else {
                will(returnValue(Collections.EMPTY_LIST));
            }
            allowing(group).model(employeeBean);
            will(returnValue(new Model(employeeBean)));
            ignoring(group);
        }};
    }

}

