package com.gft.store.controllers;

import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import com.gft.store.exceptions.BranchNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import com.gft.store.models.dtos.BranchDTO;
import com.gft.store.models.entities.Branch;
import com.gft.store.services.BranchService;

@RestController
@PreAuthorize(value = "hasAuthority('ADMIN')")
@RequestMapping("/v1/branches")
public class BranchController {

    @Autowired
    private BranchService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/")
    public ResponseEntity<?> getAll(@PageableDefault Pageable pageable) {

        var branches = service.getAll(pageable)
                .stream()
                .map(b -> mapper.map(b, BranchDTO.class))
                .collect(Collectors.toList());

        if (branches.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok().body(branches);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return new ResponseEntity<BranchDTO>(mapper.map(service.getById(id), BranchDTO.class), HttpStatus.OK);
        } catch (BranchNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Validated
    @PostMapping("/")
    public ResponseEntity<BranchDTO> save(@RequestBody BranchDTO branchDTO) {

        service.save(mapper.map(branchDTO, Branch.class));

        return new ResponseEntity<BranchDTO>(branchDTO, HttpStatus.CREATED);
    }

    @Validated
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody BranchDTO branchDTO) {
        try {
            var branchToUpdate = service.updateById(id, mapper.map(branchDTO, Branch.class));
            return new ResponseEntity<BranchDTO>(mapper.map(branchToUpdate, BranchDTO.class), HttpStatus.OK);
        } catch (BranchNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {

        try {
            service.deleteById(id);
            return ResponseEntity.ok("Branch with id:" + id + " deleted!");

        } catch (BranchNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
