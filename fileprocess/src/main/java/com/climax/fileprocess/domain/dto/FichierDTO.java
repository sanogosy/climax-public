package com.climax.fileprocess.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FichierDTO {

    private Long id;

    private String titre;

    private String url;

    private String statut;

    private boolean deleted;

}
