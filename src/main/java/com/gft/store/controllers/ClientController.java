package com.gft.store.controllers;

import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gft.store.exceptions.ClientNotFoundException;
import com.gft.store.models.dtos.ClientDTO;
import com.gft.store.models.entities.Client;
import com.gft.store.services.ClientService;

@RestController
@PreAuthorize(value = "hasAuthority('ADMIN')")
@RequestMapping("/v1/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/")
    public ResponseEntity<?> getAll(@PageableDefault Pageable pageable) {

        var clients = service.getAll(pageable)
                .stream()
                .map(b -> mapper.map(b, ClientDTO.class))
                .collect(Collectors.toList());

        if (clients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok().body(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return new ResponseEntity<ClientDTO>(mapper.map(service.getById(id), ClientDTO.class), HttpStatus.OK);
        } catch (ClientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Validated
    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody ClientDTO clientDTO) {

        service.save(mapper.map(clientDTO, Client.class));
        return new ResponseEntity<ClientDTO>(clientDTO, HttpStatus.CREATED);

    }

    @Validated
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody ClientDTO productDTO) {
        try {
            var clientToUpdate = service.updateById(id, mapper.map(productDTO, Client.class));
            return new ResponseEntity<ClientDTO>(mapper.map(clientToUpdate, ClientDTO.class), HttpStatus.OK);
        } catch (ClientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {

        try {
            service.deleteById(id);
            return ResponseEntity.ok("Client with id:" + id + " deleted!");

        } catch (ClientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}