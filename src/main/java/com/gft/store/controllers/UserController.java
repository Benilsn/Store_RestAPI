package com.gft.store.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gft.store.models.dtos.UserModelDTO;
import com.gft.store.services.UserService;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/")
    public ResponseEntity<List<UserModelDTO>> getAll() {

        List<UserModelDTO> users = service.getAll().stream()
                .map(u -> mapper.map(u, UserModelDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(users);
    }
}
