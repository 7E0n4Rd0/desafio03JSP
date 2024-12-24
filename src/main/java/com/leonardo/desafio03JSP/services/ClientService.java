package com.leonardo.desafio03JSP.services;

import com.leonardo.desafio03JSP.DTO.ClientDTO;
import com.leonardo.desafio03JSP.entities.Client;
import com.leonardo.desafio03JSP.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public List<ClientDTO> findAll(){
        List<ClientDTO> result = repository.findAll().stream().map(x -> new ClientDTO(x)).toList();
        return result;
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
        ClientDTO dto = new ClientDTO(repository.findById(id).get());
        return dto;
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto){
        Client entity = new Client();
        copyClientDtoToClient(entity, dto);
        entity = repository.save(entity);
        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto){
        Client entity = repository.getReferenceById(id);
        copyClientDtoToClient(entity, dto);
        entity = repository.save(entity);
        return new ClientDTO(entity);
    }


    private void copyClientDtoToClient(Client entity, ClientDTO dto) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }


}
