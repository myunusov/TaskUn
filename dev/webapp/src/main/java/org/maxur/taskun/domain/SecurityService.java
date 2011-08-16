package org.maxur.taskun.domain;

import org.maxur.taskun.domain.user.User;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/16/11
 */
public interface SecurityService {

    boolean authentication(User user);

}
