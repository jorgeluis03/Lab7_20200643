package com.example.lab7_20200643.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "pagos")
public class Pagos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double monto;
    @ManyToOne
    @JoinColumn(name = "usuarios_id")
    private Usuarios usuario;

    private String tipo_pago;
    private LocalDateTime fecha;
    @ManyToOne
    @JoinColumn(name = "creditos_id")
    private Creditos credito;
}
