package com.gft.store.services.iservices;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IService<T> {

    public Page<T> getAll(Pageable pageable);

    public T getById(Long id);

    public T save(T obj);

    public T updateById(Long id, T obj);

    public void deleteById(Long id);
}
