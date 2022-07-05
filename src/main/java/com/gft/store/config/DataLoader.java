package com.gft.store.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.gft.store.models.entities.Role;
import com.gft.store.models.entities.UserModel;
import com.gft.store.repositories.RoleRepository;
import com.gft.store.repositories.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        final Role ADMIN = roleRepository.findById(1l).get();
        final Role USER = roleRepository.findById(2l).get();

        UserModel admin = new UserModel("benilson@gft.com", encoder.encode("123"), "Benilson de SM", ADMIN);

        // userRepository.save(admin);

    }

}
