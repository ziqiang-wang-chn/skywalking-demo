package org.example.api.user;


import org.example.entity.User;

public interface UserApi {
    User getUserById(Long id);

    boolean userLogin(User user);
}
