package com.controllers.Filtres;

/**
 * Created by admin on 15.03.2017.
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
@Component
public class CustomAuthenticationProvider implements UserDetailsService{

    @Autowired
    private UserServiceInterface userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userService.getUserByName(username);
            System.out.println(user);
            List<GrantedAuthority> list = new ArrayList<>();
            list.add(new SimpleGrantedAuthority(user.getRole()));
            UserDetails userDetails = new org.springframework.security.core.userdetails.User(username, user.getPassword(),
                    true, true, true, true, list);
            return userDetails;
        } catch (MyException e) {
            e.printStackTrace();
            return null;
        }
    }
}