package com.gaspar.imagen.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String nombre;

    byte[] logo;

    @Column(name = "img_url")
    String imgUrl;
}
