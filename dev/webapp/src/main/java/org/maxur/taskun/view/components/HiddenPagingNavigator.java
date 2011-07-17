package org.maxur.taskun.view.components;

import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;

/**
 * The Page Navigator which hides on one page state.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/17/11
 */
public class HiddenPagingNavigator extends PagingNavigator {

    private static final long serialVersionUID = -6591732872536950367L;

    private final IPageable pageable;

    public HiddenPagingNavigator(final String id, final IPageable pageable) {
        super(id, pageable);
        this.pageable = pageable;
    }

    @Override
    public boolean isVisible() {
        return pageable.getPageCount() > 1;
    }

}
