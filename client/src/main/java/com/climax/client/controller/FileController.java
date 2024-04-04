package com.climax.client.controller;

import com.climax.client.domain.Client;
import com.climax.client.domain.dto.ClientDTO;
import com.climax.client.domain.dto.FichierDTO;
import com.climax.client.repository.ClientRepository;
import com.climax.client.service.FileService;
import com.climax.client.service.feignclient.FileProcessFeignClient;
import com.climax.client.service.impl.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(value = "/api")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    FileProcessFeignClient fileProcessFeignClient;

    @Autowired
    ClientRepository clientRepository;

    public FileController() {
    }

    @GetMapping("/get-clients")
    public ResponseEntity<List<ClientDTO>> getAllFichier(){
        List<Client> clientList = clientRepository.findAll();
        List<ClientDTO> clientDTOList = new ArrayList<>();
        clientList.forEach(client -> {
            ClientDTO clientDto = new ClientDTO();
            clientDto.setId(client.getId());
            clientDto.setNom_client(client.getNom_client());
            clientDto.setPrenom_client(client.getPrenom_client());
            clientDto.setProfession_client(client.getProfession_client());
            clientDto.setClient_revenu(client.getClient_revenu());
            clientDto.setIdentifiant(client.getIdentifiant());

            clientDTOList.add(clientDto);
        });
        return ResponseEntity.ok(clientDTOList);
    }

    @PostMapping(value="/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadFile (
            @RequestParam("fileName") String fileName,
            @RequestParam("file") MultipartFile file
    ){

        String fileUrl = "";

        if(file != null) {
            if(fileService.findByTitre(fileName).getTitre() == null) {
                fileUrl = fileService.upload(file);
                System.out.println(">>>>>>>>>>>>>>>>>>>>Not Exists");
            }
            else{
                System.out.println(">>>>>>>>>>>>>>>>>>>>Already Exists");
            }
        }

        return ResponseEntity.ok().body(fileUrl);
    }

    @PostMapping(value="/save-client", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity extractClientAndSave (
            @RequestBody FichierDTO fichierDTO
    ){

        List<ClientDTO> clientDTOList = fileProcessFeignClient.processFile(fichierDTO);

        return ResponseEntity.ok().body(clientDTOList);
    }

}
