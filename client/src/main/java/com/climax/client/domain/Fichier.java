package com.climax.client.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "fichier")
@Getter
@Setter
@ToString
public class Fichier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titre")
    private String titre;

    @Column(name = "url")
    private String url;

    @Column(name="statut")
    private String statut;

    @Column(name = "deleted", columnDefinition = "boolean default false")
    private boolean deleted;

}
