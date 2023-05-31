package com.example.lab7_20200643.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "solicitudes")
public class Solicitudes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String solicitud_producto;
    private double solicitud_monto;
    private LocalDateTime solicitud_fecha;
    @ManyToOne
    @JoinColumn(name = "usuarios_id")
    private Usuarios usuario;
    private String solicitud_estado;
}
