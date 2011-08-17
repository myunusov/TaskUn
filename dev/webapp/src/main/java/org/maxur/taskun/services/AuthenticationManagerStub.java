package org.maxur.taskun.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/17/11
 */
@Component("am")
public class AuthenticationManagerStub implements AuthenticationManager {

    static final List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();

    static {
        AUTHORITIES.add(new GrantedAuthorityImpl("ROLE_USER"));
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
    if (authentication.getName().equals(authentication.getCredentials())) {
      return new UsernamePasswordAuthenticationToken(authentication.getName(),
        authentication.getCredentials(), AUTHORITIES);
      }
      throw new BadCredentialsException("Bad Credentials");
    }
}
