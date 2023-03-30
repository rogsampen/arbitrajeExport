package pe.gob.minjus.sisarb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.minjus.sisarb.model.domain.Especialidad;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.EspecialidadService;

import java.util.List;

@RestController
@RequestMapping("api/especialidad")
public class EspecialidadController {

    @Autowired
    EspecialidadService service;

    @GetMapping("list")
    public ResponseEntity<Respuesta<List<Especialidad>>> list()  {
        return service.list();
    }
}
