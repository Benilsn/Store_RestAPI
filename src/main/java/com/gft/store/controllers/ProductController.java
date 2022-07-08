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
import com.gft.store.exceptions.ProductNotFoundException;
import com.gft.store.models.dtos.ProductDTO;
import com.gft.store.models.entities.Product;
import com.gft.store.services.ProductService;

@RestController
@PreAuthorize(value = "hasAuthority('ADMIN')")
@RequestMapping("/v1/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/")
    public ResponseEntity<?> getAll(@PageableDefault Pageable pageable) {

        var products = service.getAll(pageable)
                .stream()
                .map(b -> mapper.map(b, ProductDTO.class))
                .collect(Collectors.toList());

        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return new ResponseEntity<ProductDTO>(mapper.map(service.getById(id), ProductDTO.class), HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Validated
    @PostMapping("/")
    public ResponseEntity<ProductDTO> save(@RequestBody ProductDTO productDTO) {

        service.save(mapper.map(productDTO, Product.class));

        return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.CREATED);
    }

    @Validated
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        try {
            var productToUpdate = service.updateById(id, mapper.map(productDTO, Product.class));
            return new ResponseEntity<ProductDTO>(mapper.map(productToUpdate, ProductDTO.class), HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {

        try {
            service.deleteById(id);
            return ResponseEntity.ok("Product with id:" + id + " deleted!");

        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
