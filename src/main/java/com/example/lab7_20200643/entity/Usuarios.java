package com.example.lab7_20200643.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "usuarios")
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String apellido;
    private String correo;
    private String username;
    private String password;
    @Column(name = "estado_logico")
    private String estadologico;
    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;
    private LocalDateTime fecha_registro;

}
