package com.example.lab7_20200643.controllers;

import com.example.lab7_20200643.entity.Solicitudes;
import com.example.lab7_20200643.repository.SolicitudRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/solicitudes")
public class SolicitudController {
    final SolicitudRepository solicitudRepository;

    public SolicitudController(SolicitudRepository solicitudRepository) {
        this.solicitudRepository = solicitudRepository;
    }

    @GetMapping("/listar")
    public List<Solicitudes> lista(){
        return solicitudRepository.findAll();
    }
    @PostMapping("/registro")
    public ResponseEntity<HashMap<String,Object>> registro(@RequestBody Solicitudes solicitud){
        HashMap<String,Object> responseMap= new HashMap<>();
        solicitudRepository.save(solicitud);
        responseMap.put("Monto solicitado", solicitud.getSolicitud_monto());
        responseMap.put("Id",solicitud.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMap);
    }

    @PutMapping("/aprobarSolicitud")
    public ResponseEntity<HashMap<String,Object>> aprobarSolicitud(@RequestParam("idSolicitud") Integer idSolicitud){
        HashMap<String,Object> responseMap = new HashMap<>();
        if(idSolicitud > 0 && idSolicitud!=null){
            Optional<Solicitudes> opt = solicitudRepository.findById(idSolicitud);
            if(opt.isPresent()){
                Solicitudes solicitudFrm = opt.get();
                if(solicitudFrm.getSolicitud_estado().isEmpty() ){
                    solicitudFrm.setSolicitud_estado("aprobado");
                    solicitudRepository.save(solicitudFrm);
                    responseMap.put("id solicitud se acaba de atender",solicitudFrm.getId());
                    return ResponseEntity.ok(responseMap);
                }else {
                    responseMap.put("Solicitud ya atendida",solicitudFrm.getId());
                    return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(responseMap);
                }
            }else {
                responseMap.put("Estado","error");
                responseMap.put("msg","El id no existe");
                return ResponseEntity.badRequest().body(responseMap);
            }
        }else {
            responseMap.put("Estado", "error");
            responseMap.put("msg","debe ingresar un id");
            return ResponseEntity.badRequest().body(responseMap);
        }
    }
    @PutMapping("/denegar")
    public ResponseEntity<HashMap<String,Object>> denegar(@RequestParam("idSolicitud") Integer idSolicitud){
        HashMap<String,Object> responseMap = new HashMap<>();
        if(idSolicitud!=null && idSolicitud>0){
            Optional<Solicitudes> opt = solicitudRepository.findById(idSolicitud);
            if (opt.isPresent()){
                Solicitudes solicitud = opt.get();
                if(solicitud.getSolicitud_estado()==null){
                    solicitud.setSolicitud_estado("denegado");
                    solicitudRepository.save(solicitud);
                    responseMap.put("id solicitud se acaba de denegar",solicitud.getId());
                    return ResponseEntity.ok(responseMap);

                }else {
                    responseMap.put("Estado","error");
                    responseMap.put("Solicitud ya atendida",solicitud.getId());
                    return ResponseEntity.badRequest().body(responseMap);
                }

            }else {
                responseMap.put("msg","No se encontro el ID");
                return ResponseEntity.badRequest().body(responseMap);
            }

        }else {
            responseMap.put("Estado","error");
            responseMap.put("msg","Debe ingresar un ID");
            return ResponseEntity.badRequest().body(responseMap);
        }
    }
    @DeleteMapping("/borrar")
    public ResponseEntity<HashMap<String,Object>> borrar(@RequestParam("idSolicitud") Integer idSolicitud){
        HashMap<String,Object> responseMap = new HashMap<>();
        if(idSolicitud!=null && idSolicitud>0){
            Optional<Solicitudes> opt = solicitudRepository.findById(idSolicitud);
            if (opt.isPresent()){
                Solicitudes solicitud = opt.get();
                if(solicitud.getSolicitud_estado().equals("denegado")){
                    solicitudRepository.deleteById(solicitud.getId());
                    responseMap.put("id solicitud borrada",solicitud.getId());
                    return ResponseEntity.ok(responseMap);

                }else {
                    responseMap.put("Estado","error");
                    responseMap.put("Solicitud ya atendida",solicitud.getId());
                    return ResponseEntity.badRequest().body(responseMap);
                }

            }else {
                responseMap.put("msg","No se encontro el ID");
                return ResponseEntity.badRequest().body(responseMap);
            }

        }else {
            responseMap.put("Estado","error");
            responseMap.put("msg","Debe ingresar un ID");
            return ResponseEntity.badRequest().body(responseMap);
        }
    }



    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap<String, Object>> gestionExcepcion(HttpServletRequest request){
        HashMap<String,Object> responseMap = new HashMap<>();
        if(request.getMethod().equals("POST") || request.getMethod().equals("PUT")){
            responseMap.put("Estado","error");
            responseMap.put("msg","Debe enviar una solicitud");
        }
        return ResponseEntity.badRequest().body(responseMap);   //enviar el error 400

    }
}
