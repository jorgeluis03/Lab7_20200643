package com.example.lab7_20200643.controllers;

import com.example.lab7_20200643.entity.Pagos;
import com.example.lab7_20200643.repository.PagosRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin
@RequestMapping("/pagos")
public class PagosController {

    final PagosRepository pagosRepository;

    public PagosController(PagosRepository pagosRepository) {
        this.pagosRepository = pagosRepository;
    }

    @GetMapping("/listarPagos")
    public ResponseEntity<HashMap<String, Object>> listarPagos(){
        HashMap<String,Object> responseMap = new HashMap<>();
        responseMap.put("Pagos",pagosRepository.findAll());
        return ResponseEntity.ok(responseMap);
    }
    @PostMapping("/registrarPagos")
    public ResponseEntity<HashMap<String,Object>> registrarPagos(@RequestBody Pagos pago){
        HashMap<String,Object> responseMap = new HashMap<>();
        pagosRepository.save(pago);
        responseMap.put("id creado",pago.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMap);
    }
}
