package com.example.lab7_20200643.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "creditos")
public class Creditos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String monto;
    @ManyToOne
    @JoinColumn(name = "usuarios_id")
    private Usuarios usuario;
    private LocalDateTime fecha;
    private double interes;
}
