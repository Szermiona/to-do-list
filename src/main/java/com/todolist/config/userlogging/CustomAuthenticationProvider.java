package com.todolist.config.userlogging;

import com.todolist.entity.UserDAO;
import com.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDAO byUserName = userRepository.findByUserName(authentication.getName());
        if (byUserName == null) {
            throw new BadCredentialsException("Invalid userName or password");
        } else if (!byUserName.isActive()) {
            throw new BadCredentialsException("User is not active");
        } else {
            return super.authenticate(authentication);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
