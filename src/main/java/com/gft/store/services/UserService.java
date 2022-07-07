package com.gft.store.services;

import java.util.List;
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
        return repository.findByEmail(username).orElseThrow(() -> new UserNotFoundException("User Not found!"));
    }

    public List<UserModel> getAll() {
        return repository.findAll();
    }

    public UserModel getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not found!"));
    }

}
