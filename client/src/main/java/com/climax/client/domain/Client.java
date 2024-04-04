package com.climax.client.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "clients")
@Getter
@Setter
@ToString
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identifiant")
    private String identifiant;

    @Column(name = "nom_client")
    private String nom_client;

    @Column(name = "prenom_client")
    private String prenom_client;

    @Column(name="profession_client")
    private String profession_client;

    @Column(name="client_revenu")
    private String client_revenu;

    @Column(name="create_dt")
    private Date create_dt;

}
