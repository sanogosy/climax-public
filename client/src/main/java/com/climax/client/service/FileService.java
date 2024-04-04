package com.climax.client.service;


import com.climax.client.domain.dto.FichierDTO;
import org.springframework.web.multipart.MultipartFile;


public interface FileService {

    String upload(MultipartFile file);
    FichierDTO findByTitre(String name);

}
