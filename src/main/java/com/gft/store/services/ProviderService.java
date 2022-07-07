package com.gft.store.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.gft.store.exceptions.ProviderNotFoundException;
import com.gft.store.models.entities.Provider;
import com.gft.store.repositories.ProviderRepository;
import com.gft.store.services.iservices.IService;

@Service
public class ProviderService implements IService<Provider> {

    @Autowired
    private ProviderRepository repository;

    @Override
    public Page<Provider> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Provider getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ProviderNotFoundException("Provider not found!"));
    }

    @Override
    public Provider save(Provider obj) {
        return repository.save(obj);
    }

    @Override
    public Provider updateById(Long id, Provider obj) {
        var providerToUpdate = getById(id);

        providerToUpdate.setCnpj(obj.getCnpj());
        providerToUpdate.setProvider_name(obj.getProvider_name());
        providerToUpdate.setPhone(obj.getPhone());
        providerToUpdate.setEmail(obj.getEmail());
        providerToUpdate.setAddress(obj.getAddress());

        return repository.save(providerToUpdate);
    }

    @Override
    public void deleteById(Long id) {
        getById(id);

        repository.deleteById(id);
    }

}
