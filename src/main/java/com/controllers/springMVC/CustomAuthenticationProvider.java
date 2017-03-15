package com.controllers.springMVC;

/**
 * Created by admin on 15.03.2017.
 */
import java.util.Collection;

import com.common.exceptions.MyException;
import com.models.pojo.User;
import com.services.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{

    @Autowired
    private UserServiceInterface userService;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        User user = null;
        try {
            user = userService.authorize(username, password);
        } catch (MyException e) {
            e.printStackTrace();
        }

        if (user == null || !user.getNickName().equalsIgnoreCase(username)) {
            throw new BadCredentialsException("Username not found.");
        }

        if (!password.equals(user.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }

        String authorities = user.getRole();

        return new UsernamePasswordAuthenticationToken(user, password);

    }

    public boolean supports(Class<?> arg0) {
        return true;
    }

}