package com.example.lab7_20200643.repository;

import com.example.lab7_20200643.entity.Solicitudes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitudes,Integer> {
}
