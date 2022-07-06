package com.gft.store.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gft.store.exceptions.ClientNotFoundException;
import com.gft.store.models.entities.Client;
import com.gft.store.repositories.ClientRepository;
import com.gft.store.services.iservices.IService;

@Service
public class ClientService implements IService<Client> {

    @Autowired
    private ClientRepository repository;

    @Override
    public List<Client> getAll() {
        return repository.findAll();
    }

    @Override
    public Client getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client not found!"));
    }

    @Override
    public Client save(Client obj) {
        return repository.save(obj);
    }

    @Override
    public Client updateById(Long id, Client obj) {
        var clientToUpdate = getById(id);

        clientToUpdate.setClient_name(obj.getClient_name());
        clientToUpdate.setCpf(obj.getCpf());
        clientToUpdate.setEmail(obj.getEmail());
        clientToUpdate.setPhone(obj.getPhone());
        clientToUpdate.setAddress(obj.getAddress());

        return repository.save(clientToUpdate);
    }

    @Override
    public void deleteById(Long id) {
        getById(id);

        repository.deleteById(id);
    }

}
