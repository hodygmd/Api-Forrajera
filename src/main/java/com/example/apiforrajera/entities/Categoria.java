package com.example.apiforrajera.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "id_funcion")
    private Funcion id_funcion;
    @Column(name = "status")
    private Byte status;
}
