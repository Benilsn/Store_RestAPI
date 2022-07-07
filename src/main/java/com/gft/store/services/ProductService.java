package com.gft.store.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.gft.store.exceptions.ProductNotFoundException;
import com.gft.store.models.entities.Product;
import com.gft.store.repositories.ProductRepository;
import com.gft.store.services.iservices.IService;

@Service
public class ProductService implements IService<Product> {

    @Autowired
    private ProductRepository repository;

    @Override
    public Page<Product> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Product getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found!"));
    }

    @Override
    public Product save(Product obj) {
        return repository.save(obj);
    }

    @Override
    public Product updateById(Long id, Product obj) {
        var productToUpdate = getById(id);

        productToUpdate.setProduct_name(obj.getProduct_name());
        productToUpdate.setProduct_description(obj.getProduct_description());
        productToUpdate.setProduct_unity(obj.getProduct_unity());

        return repository.save(productToUpdate);
    }

    @Override
    public void deleteById(Long id) {
        getById(id);

        repository.deleteById(id);
    }

}
