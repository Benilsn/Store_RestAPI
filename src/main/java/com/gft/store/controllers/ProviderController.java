package com.gft.store.controllers;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.store.exceptions.ProviderNotFoundException;
import com.gft.store.models.dtos.ProviderDTO;
import com.gft.store.models.entities.Provider;
import com.gft.store.services.ProviderService;

@RestController
@RequestMapping("/v1/providers")
public class ProviderController {

    @Autowired
    private ProviderService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/")
    public ResponseEntity<?> getAll() {

        var providers = service.getAll()
                .stream()
                .map(b -> mapper.map(b, ProviderDTO.class))
                .collect(Collectors.toList());

        if (providers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok().body(providers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return new ResponseEntity<ProviderDTO>(mapper.map(service.getById(id), ProviderDTO.class), HttpStatus.OK);
        } catch (ProviderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Validated
    @PostMapping("/")
    public ResponseEntity<ProviderDTO> save(@RequestBody ProviderDTO ProviderDTO) {

        service.save(mapper.map(ProviderDTO, Provider.class));

        return new ResponseEntity<ProviderDTO>(ProviderDTO, HttpStatus.CREATED);
    }

    @Validated
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody ProviderDTO providerDTO) {
        try {
            var providerToUpdate = service.updateById(id, mapper.map(providerDTO, Provider.class));
            return new ResponseEntity<ProviderDTO>(mapper.map(providerToUpdate, ProviderDTO.class), HttpStatus.OK);
        } catch (ProviderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {

        try {
            service.deleteById(id);
            return ResponseEntity.ok("Provider with id:" + id + " deleted!");

        } catch (ProviderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}