package com.example.lab7_20200643.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "acciones")
public class Acciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double monto;
    private LocalDateTime fecha;
    @ManyToOne
    @JoinColumn(name = "usuarios_id")
    private Usuarios usuario;
}
