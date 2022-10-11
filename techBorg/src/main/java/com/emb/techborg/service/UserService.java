package com.emb.techborg.service;

import java.util.List;

import com.emb.techborg.model.User;

public interface UserService {
	
    public void saveUser(User user);
    public List<Object> isUserPresent(User user);
}
