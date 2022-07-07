package com.gft.store.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gft.store.models.entities.Provider;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {

    Page<Provider> findAll(Pageable pageable);

}
