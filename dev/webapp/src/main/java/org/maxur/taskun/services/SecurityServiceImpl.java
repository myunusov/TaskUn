package org.maxur.taskun.services;

import org.maxur.taskun.domain.SecurityService;
import org.maxur.taskun.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityServiceImpl implements SecurityService {

    private final AuthenticationManager manager;

    @Autowired
    public SecurityServiceImpl(@Qualifier(value = "authenticationManager") final AuthenticationManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean authentication(User user) {
        //return null != user.getPassword() && user.getPassword().equals(user.getUserName());
        try {
            Authentication request = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
            Authentication result = this.manager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);
            return true;
        } catch (AuthenticationException e) {
            return false;
        }
    }
}