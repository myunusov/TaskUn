package org.maxur.taskun.view;

import org.apache.wicket.Request;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.authorization.strategies.role.Roles;
import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.maxur.taskun.view.config.ViewConfigurator;
import org.maxur.taskun.view.model.MenuItem;
import org.maxur.taskun.view.model.MenuItems;
import org.maxur.taskun.view.model.UserBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;
import java.util.List;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/9/11
 */
public class UserSession extends AuthenticatedWebSession {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -1839738545816232994L;

    @SpringBean(name = "authenticationManager")
    private AuthenticationManager authenticationManager;

    /**
     * Menu items List as MenuItems class.
     */
    //@SpringBean(name="menuItems")
    private MenuItems menuItems;

    @SpringBean(name = "viewConfigurator")
    private ViewConfigurator configurator;


    /**
     * The System User.
     */
    private UserBean user;


    /**
     * The Constructor of UserSession class.
     *
     * @param request The Base class for page request.
     */
    public UserSession(final Request request) {
        super(request);
        injectDependencies();
        ensureDependenciesNotNull();
        if (configurator != null) {
            menuItems = configurator.menuItems();
        }
    }


    private void ensureDependenciesNotNull() {
        if (authenticationManager == null) {
            throw new IllegalStateException("AdminSession requires an authenticationManager.");
        }
    }

    private void injectDependencies() {
        InjectorHolder.getInjector().inject(this);
    }


    /**
     * Gets Menu Items.
     *
     * @return The Menu Items List.
     */
    public List<MenuItem> getMenuItems() {
        return Collections.unmodifiableList(menuItems);
    }

    /**
     * Setter for set Main Menu items.
     *
     * @param items Main Menu items.
     */
    public final void setMenuItems(final MenuItems items) {
        this.menuItems = items;
    }

    /**
     * Gets Current User.
     *
     * @return The current User.
     */
    public UserBean getUser() {
        return user;
    }

    /**
     * Set Current User.
     *
     * @param user The current User
     */
    public void setUser(final UserBean user) {
        this.user = user;
    }


    @Override
    public boolean authenticate(String username, String password) {
        boolean authenticated = false;
        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            authenticated = authentication.isAuthenticated();
        } catch (AuthenticationException e) {
            // logger.warn(format("User '%s' failed to login. Reason: %s", username, e.getMessage()));
            authenticated = false;
        }
        return authenticated;
    }

    @Override
    public Roles getRoles() {
        Roles roles = new Roles();
        getRolesIfSignedIn(roles);
        return roles;
    }

    private void getRolesIfSignedIn(Roles roles) {
        if (isSignedIn()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            addRolesFromAuthentication(roles, authentication);
        }
    }

    private void addRolesFromAuthentication(Roles roles, Authentication authentication) {
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            roles.add(authority.getAuthority());
        }
    }


}
