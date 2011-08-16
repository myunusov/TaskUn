package org.maxur.taskun.domain.validators;

import org.maxur.taskun.domain.SecurityService;
import org.maxur.taskun.domain.user.User;
import org.springframework.stereotype.Component;

@Component
public class SecurityServiceStub implements SecurityService {

    public SecurityServiceStub() {
    }

    @Override
    public boolean authentication(User user) {
        return null != user.getPassword() && user.getPassword().equals(user.getUserName());
    }
}