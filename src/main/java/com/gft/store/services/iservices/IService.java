package com.gft.store.services.iservices;

import java.util.List;

public interface IService<T> {

    public List<T> getAll();

    public T getById(Long id);

    public T save(T obj);

    public T updateById(Long id, T obj);

    public void deleteById(Long id);
}
