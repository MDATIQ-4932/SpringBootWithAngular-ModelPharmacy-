package com.Atiq.Springproject.Service;


import com.Atiq.Springproject.Repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service


public class UserService implements UserDetailsService {


    @Autowired
    private IUserRepository iUserRepository;




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return iUserRepository.findByEmail(username)
                .orElseThrow(()->new UsernameNotFoundException("User Not Found With this email address"));
    } ;
    }

