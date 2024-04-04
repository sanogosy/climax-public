package com.climax.fileprocess.service.impl;

import com.climax.fileprocess.domain.Client;
import com.climax.fileprocess.domain.dto.ClientDTO;
import com.climax.fileprocess.repository.ClientRepository;
import com.climax.fileprocess.service.IClientService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.climax.fileprocess.utils.JsonFileFormat.uploadJsonFileFromResource;

@Service
public class ClientServiceImpl implements IClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<ClientDTO> saveAllClient(String fileName) {

        return readJsonFile(fileName);
    }

    public List<ClientDTO> readJsonFile(String fileName){
        ObjectMapper mapper = new ObjectMapper();
        List<ClientDTO> clientDTOList = new ArrayList<>();
        TypeReference<List<Client>> typeReference = new TypeReference<List<Client>>(){};
//        InputStream inputStream = TypeReference.class.getResourceAsStream("../climax/" + fileName);

        try {
            InputStream inputStream = new FileInputStream(uploadJsonFileFromResource(fileName));
            List<Client> clientList = mapper.readValue(inputStream, typeReference);
//            List<Client> clientList = mapper.readValue(inputStream, new TypeReference<List<Client>>(){});
//            List<Client> clientList = mapper.readValue(inputStream, new TypeReference<List<Client>>(){});
            clientRepository.saveAll(clientList).forEach(client -> {
                ClientDTO clientDTO = new ClientDTO();

                clientDTO.setId(clientDTO.getId());
                clientDTO.setNom_client(clientDTO.getNom_client());
                clientDTO.setProfession_client(clientDTO.getPrenom_client());
                clientDTO.setIdentifiant(clientDTO.getIdentifiant());
                clientDTO.setClient_revenu(clientDTO.getClient_revenu());
                clientDTO.setCreate_dt(clientDTO.getCreate_dt());

                clientDTOList.add(clientDTO);

            });
            System.out.println("Customers Saved!");
        } catch (IOException e){
            System.out.println("Unable to save Customers: " + e.getMessage());
        }

        return clientDTOList;
    }

    @Override
    public ClientDTO saveClient(ClientDTO clientDTO) {
        return null;
    }
}
