package com.climax.client.domain.dto;

import jakarta.persistence.Column;
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

}
