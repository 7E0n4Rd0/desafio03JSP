package com.leonardo.desafio03JSP.repositories;

import com.leonardo.desafio03JSP.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
