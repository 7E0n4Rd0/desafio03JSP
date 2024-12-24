package com.leonardo.desafio03JSP.services;

import com.leonardo.desafio03JSP.DTO.ClientDTO;
import com.leonardo.desafio03JSP.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional
    public List<ClientDTO> findAll(){
        List<ClientDTO> result = repository.findAll().stream().map(x -> new ClientDTO(x)).toList();
        return result;
    }

    @Transactional
    public ClientDTO findById(Long id){
        ClientDTO dto = new ClientDTO(repository.findById(id).get());
        return dto;
    }


}
