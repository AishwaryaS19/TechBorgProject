package com.emb.techborg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.emb.techborg.model.User;
import com.emb.techborg.repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.apache.log4j.BasicConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;

    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);
	
	{
		BasicConfigurator.configure();
	}
	
    @Override
    public void saveUser(User user) {
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        log.info("Saving User to database", user.getUsername());
        userRepository.save(user);
    }

    @Override
    public List<Object> isUserPresent(User user) {
        boolean userExists = false;
        String message = null;
        
        Optional<User> existingUserEmail = userRepository.findByEmail(user.getEmail());
        if(existingUserEmail.isPresent()){
            userExists = true;
            log.error("Email Already Exists!");
            message = "Email Already Exists!";
        }
        
        Optional<User> existingUserMobile = userRepository.findByMobile(user.getMobile());
        if(existingUserMobile.isPresent()){
            userExists = true;
            log.error("Mobile Number Already Present!");
            message = "Mobile Number Already Present!";
        }
        
        if (existingUserEmail.isPresent() && existingUserMobile.isPresent()) {
        	log.error("Email and Mobile Number Both Already Present!");
            message = "Email and Mobile Number Both Already Present!";
        }
        
        return Arrays.asList(userExists, message);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(
                ()-> new UsernameNotFoundException(
                        String.format("USER_NOT_FOUND", email)
                ));
    }
}