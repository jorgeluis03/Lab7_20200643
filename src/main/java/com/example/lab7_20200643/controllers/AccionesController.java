package com.example.lab7_20200643.controllers;

import com.example.lab7_20200643.entity.Acciones;
import com.example.lab7_20200643.repository.AccionesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/acciones")
public class AccionesController {
    final AccionesRepository accionesRepository;

    public AccionesController(AccionesRepository accionesRepository) {
        this.accionesRepository = accionesRepository;
    }

    @GetMapping("/listar")
    public List<Acciones> lista(){
        return accionesRepository.findAll();
    }
    @PostMapping("/save")
    public ResponseEntity<HashMap<String, Object>> save (@RequestBody Acciones acciones){
        HashMap<String,Object> responseMap = new HashMap<>();
        accionesRepository.save(acciones);
        responseMap.put("Id accion creada",acciones.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMap);
    }

}
