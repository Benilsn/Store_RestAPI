package com.gft.store.services;

import java.util.Set;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.gft.store.exceptions.UserNotFoundException;
import com.gft.store.models.entities.UserModel;
import com.gft.store.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserModel user = repository.findByEmail(username)
                .orElseThrow(() -> new UserNotFoundException("User Not found!"));

        return new User(
                user.getEmail(),
                user.getUser_password(),
                Set.of(user.getRole()));

    }

}
