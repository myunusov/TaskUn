package org.maxur.taskun.view.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.junit.Test;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>08.07.11</pre>
 */
public class BasePageTest extends AbstractPageTest {

    @Override
    protected Class<? extends WebPage> getPageClass() {
        return BasePage.class;
    }

    @Test
    public void testBasePageComponents() {
        tester.assertComponent("menu", MenuPanel.class);
        tester.assertComponent("footer", FooterPanel.class);
        tester.assertComponent("header", HeaderPanel.class);
        tester.assertComponent("title.application", Label.class);
    }

/*  TODO
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
