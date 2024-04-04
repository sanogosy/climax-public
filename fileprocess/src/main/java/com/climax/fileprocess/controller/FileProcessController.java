package com.climax.fileprocess.controller;

import com.climax.fileprocess.domain.Fichier;
import com.climax.fileprocess.domain.dto.ClientDTO;
import com.climax.fileprocess.domain.dto.FichierDTO;
import com.climax.fileprocess.repository.FileProcessRepository;
import com.climax.fileprocess.service.FileProcessService;
import com.climax.fileprocess.service.IClientService;
import com.climax.fileprocess.utils.CsvFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.climax.fileprocess.utils.CsvFormat.getExtensionByApacheCommonLib;
import static com.climax.fileprocess.utils.JsonFileFormat.uploadJsonFileFromResource;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/fileprocess")
public class FileProcessController {

    @Autowired
    private FileProcessService fileProcessService;

    @Autowired
    private IClientService iClientService;

    @Autowired
    private FileProcessRepository fileProcessRepository;

    @CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET})
    @GetMapping(value = "/get-files")
    public ResponseEntity<List<FichierDTO>> getAllFichier(){
        List<Fichier> fichierList = fileProcessRepository.findAll();
        List<FichierDTO> fichierDTOList = new ArrayList<>();
        fichierList.forEach(fichier -> {
            FichierDTO fichierDTO = new FichierDTO();
            fichierDTO.setId(fichier.getId());
            fichierDTO.setTitre(fichier.getTitre());
            fichierDTO.setUrl(fichier.getUrl());

            fichierDTOList.add(fichierDTO);
        });
        return ResponseEntity.ok(fichierDTOList);
    }

    @CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.POST})
    @PostMapping(value = "/process")
    public List<ClientDTO> processFile(@RequestParam("fileName") String fileName) throws IOException {
        List<ClientDTO> clientDTOList = new ArrayList<>();
        if(CsvFormat.uploadCsvFileFromResource(fileName) != null) {
            clientDTOList = fileProcessService.readFileAndSaveClient(
                    CsvFormat.uploadCsvFileFromResource(fileName)
            );
        }
        else if(uploadJsonFileFromResource(fileName) != null){
            clientDTOList = iClientService.saveAllClient(fileName);
        }
        else if(getExtensionByApacheCommonLib(fileName).equals("txt")){
            clientDTOList = fileProcessService.readTxtFileAndProcess(
                    CsvFormat.uploadTxtFileFromResource(fileName)
            );
        }
        return clientDTOList;
    }

}
