package com.leonardo.desafio03JSP.services;

import com.leonardo.desafio03JSP.DTO.ClientDTO;
import com.leonardo.desafio03JSP.entities.Client;
import com.leonardo.desafio03JSP.repositories.ClientRepository;
import com.leonardo.desafio03JSP.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
        ClientDTO dto = new ClientDTO(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!")));
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
        try {
            Client entity = repository.getReferenceById(id);
            copyClientDtoToClient(entity, dto);
            entity = repository.save(entity);
            return new ClientDTO(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Resource Not Found!");
        }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Resource Not Found!");
        }
        repository.deleteById(id);
    }



    private void copyClientDtoToClient(Client entity, ClientDTO dto) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }
}
