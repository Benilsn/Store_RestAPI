package com.gft.store.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.gft.store.exceptions.BranchNotFoundException;
import com.gft.store.models.entities.Branch;
import com.gft.store.repositories.BranchRepository;
import com.gft.store.services.iservices.IService;

@Service
public class BranchService implements IService<Branch> {

    @Autowired
    private BranchRepository repository;

    @Override
    public Page<Branch> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Branch getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new BranchNotFoundException("Branch not found!"));
    }

    @Override
    public Branch save(Branch obj) {
        return repository.save(obj);
    }

    @Override
    public Branch updateById(Long id, Branch obj) {
        var branchToUpdate = getById(id);

        branchToUpdate.setBranch_name(obj.getBranch_name());
        branchToUpdate.setAddress(obj.getAddress());

        return repository.save(branchToUpdate);
    }

    @Override
    public void deleteById(Long id) {
        getById(id);

        repository.deleteById(id);
    }

}
