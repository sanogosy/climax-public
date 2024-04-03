package com.climax.client.domain.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class ClientDTO {

    private Long id;
    private String identifiant;

    private String nom_client;

    private String prenom_client;

    private String profession_client;

    private String client_revenu;

    private Date create_dt;

//    private boolean deleted;

}
