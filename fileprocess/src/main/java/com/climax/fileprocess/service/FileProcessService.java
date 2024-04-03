package com.climax.fileprocess.service;

import com.climax.fileprocess.domain.dto.ClientDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface FileProcessService {

    List<ClientDTO> readMultipartFileAndSaveClient(MultipartFile file);
    List<ClientDTO> readFileAndSaveClient(File file);

}
