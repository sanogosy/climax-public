package com.climax.fileprocess.service;

import com.climax.fileprocess.domain.Client;
import com.climax.fileprocess.domain.dto.ClientDTO;

import java.util.List;

public interface IClientService {

    List<ClientDTO> saveAllClient(String fileName);
    ClientDTO saveClient(ClientDTO clientDTO);

}
