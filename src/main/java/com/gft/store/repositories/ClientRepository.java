package com.gft.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.store.models.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
