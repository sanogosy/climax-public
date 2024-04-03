package com.climax.client.service.impl;

import com.climax.client.domain.Fichier;
import com.climax.client.domain.dto.FichierDTO;
import com.climax.client.repository.FileRepository;
import com.climax.client.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public String upload(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        String fileUrl = "";

        try{
            Path fileStorageLocation = Paths.get("../climax");
            Path targetLocation = fileStorageLocation.resolve(fileName);

            if(!Files.exists(targetLocation)) {
                Files.createDirectories(targetLocation);
            }

            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            fileUrl = targetLocation.toString();

            Fichier fichier = new Fichier();
            fichier.setUrl(fileUrl);
            fichier.setTitre(fileName);
            fichier.setStatut("NEW");

            fileRepository.save(fichier);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return fileUrl;
    }

    @Override
    public FichierDTO findByTitre(String name) {
        FichierDTO fichierDTO = new FichierDTO();
        if(fileRepository.findByTitre(name).isPresent()){
            fichierDTO.setUrl(fileRepository.findByTitre(name).get().getUrl());
            fichierDTO.setTitre(fileRepository.findByTitre(name).get().getTitre());
        }
        return fichierDTO;
    }
}
