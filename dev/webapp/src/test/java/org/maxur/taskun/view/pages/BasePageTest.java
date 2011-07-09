package org.maxur.taskun.view.pages;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.maxur.taskun.view.TaskunApplication;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>08.07.11</pre>
 */
public class BasePageTest {

    private WicketTester tester;

    @Before
    public void setUp() {
        tester = new WicketTester(new TaskunApplication());
        tester.startPage(BasePage.class);
    }

    @Test
    public void testBasePageBasicRender() {
        tester.assertRenderedPage(BasePage.class);
    }

    @Test
    public void testBasePageComponents() {
        // assert rendered field components
        tester.assertComponent("menu", MenuPanel.class);
        tester.assertComponent("footer", FooterPanel.class);
        tester.assertComponent("header", HeaderPanel.class);

        // assert rendered label components
        tester.assertLabel("title", "ТаскУН: Управление задачами");
    }

/*    @Test
//TODO MY Make test on click
    public void testOnClickAction() {
        // click link and render
        tester.clickLink("menu:menu_items:menu_item");
        tester.assertRenderedPage(ExamplePage.class);
        tester.assertLabel("nextPageMessage", "Hello!");
    }*/


/*
    public void testOnClickAction() {
            tester.startPage(MyPage.class);

            // click link and render
            tester.clickLink("nextPage");

            tester.assertRenderedPage(NextPage.class);
            tester.assertLabel("nextPageMessage", "Hello!");
        }

    public void testNextPageRender() {
            // provide page instance source for WicketTester
            tester.startPage(new TestPageSource() {
                public Page getTestPage() {
                    return new NextPage("Hello!");
                }
            });

            tester.assertRenderedPage(YourPage.class);
            tester.assertLabel("nextPageMessage", "Hello!");

        }

    public void testFormSubmit ()
   {
              // Create the FormTester object
              FormTester ft = tester.newFormTester("myForm");

              // Set the input values on the field elements
              ft.setValue("firstName", "Kumar");
              ft.setValue("lastName", "Nadar");

              // Submit the form once the form is completed
              ft.submit("Submit");

              // Check the rendered page on form submission
              tester.asserRenderedPage(Welcome.class);
              // verify the message on the rendered page
              tester.assertInfoMessage(new String[]{"Welcome to Wicket"});

    }

*/

}
