package pe.gob.minjus.sisarb.service;

import org.springframework.http.ResponseEntity;
import pe.gob.minjus.sisarb.model.domain.Especialidad;
import pe.gob.minjus.sisarb.model.response.Respuesta;

import java.util.List;

public interface EspecialidadService {
    ResponseEntity<Respuesta<List<Especialidad>>> list();
}
