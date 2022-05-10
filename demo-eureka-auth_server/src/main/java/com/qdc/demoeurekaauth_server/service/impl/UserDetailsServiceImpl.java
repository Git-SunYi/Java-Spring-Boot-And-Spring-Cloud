package com.qdc.demoeurekaauth_server.service.impl;

import com.qdc.demoeurekaauth_server.pojo.Role;
import com.qdc.demoeurekaauth_server.pojo.User;
import com.qdc.demoeurekaauth_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUser(username);
//        System.out.println(user);
        if(user == null || user.getId()<1){
            throw new UsernameNotFoundException("Username not found："+username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),
                true,true,true,true,getGrantedAuthorities(user));
    }
    private Collection<? extends GrantedAuthority> getGrantedAuthorities(User user) {
//        Set<Role> role1 = new HashSet<>();
//        role1.add(new Role(1,"admin"));
//        user.setRoles(role1);
        HashSet<GrantedAuthority> authorities = new HashSet<>();
        for (Role role:user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
        }
        return authorities;
    }
}
