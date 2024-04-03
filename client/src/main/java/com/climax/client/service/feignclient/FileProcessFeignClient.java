package com.climax.client.service.feignclient;

import com.climax.client.domain.Fichier;
import com.climax.client.domain.dto.ClientDTO;
import com.climax.client.domain.dto.FichierDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("fileprocess")
//@FeignClient (
//        configuration = FeignSimpleEncoderConfig.class
//)
public interface FileProcessFeignClient {

//    @PostMapping("/file-name")
    @RequestMapping(method = RequestMethod.POST, value = "file-name", consumes = "application/json")
    List<ClientDTO> processFile(@RequestBody FichierDTO fichierDTO);

    @RequestMapping(method = RequestMethod.POST, value = "fileprocess", consumes = "application/json")
    Fichier getFichierDetail(
            @RequestHeader("climax-correlation-id") String correlationid,
            @RequestBody Fichier fichier
    );

}
