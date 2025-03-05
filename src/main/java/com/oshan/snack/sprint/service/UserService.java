package com.oshan.snack.sprint.service;

import com.oshan.snack.sprint.models.User;

public interface UserService {
    public User findUserByJwtToken(String jwt) throws Exception;
    public User findUserByEmail (String email) throws Exception;
}
