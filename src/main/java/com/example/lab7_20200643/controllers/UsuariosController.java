package com.example.lab7_20200643.controllers;

import com.example.lab7_20200643.entity.Usuarios;
import com.example.lab7_20200643.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UsuariosController {
    final UsuarioRepository usuarioRepository;
    public UsuariosController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping(value = "/listar",produces = MediaType.APPLICATION_JSON_VALUE+"; charset=utf-8")
    public ResponseEntity<HashMap<String,Object>> listar(){
        HashMap<String,Object> responseMap = new HashMap<>();
        responseMap.put("Usuario",usuarioRepository.findAll());
        return ResponseEntity.ok(responseMap);
    }
    @PostMapping("/crear")
    public ResponseEntity<HashMap<String,Object>> crear(@RequestBody Usuarios usuario){
        HashMap<String,Object> responseMap= new HashMap<>();
        usuarioRepository.save(usuario);
        responseMap.put("Id creado",usuario.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMap);
    }

}
