package com.project.courses.bookbar.security;

import com.project.courses.bookbar.security.jwt.JwtUser;
import com.project.courses.bookbar.security.jwt.JwtUserFactory;
import com.project.courses.bookbar.entity.User;
import com.project.courses.bookbar.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
    User user = userService.findByUsername(username);
    if(user==null){
        throw new UsernameNotFoundException("User with username : " +username+ " not found");
    }
        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("IN loadUserByUsername - user with username: {} successfully loaded", username);
        return jwtUser;
    }
}
