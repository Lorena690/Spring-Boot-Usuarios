package com.cursojava.curso.controllers.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "usuarios")
@ToString @EqualsAndHashCode
public class Usuario {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "email")
    private String email;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "password")
    private String password;


}
