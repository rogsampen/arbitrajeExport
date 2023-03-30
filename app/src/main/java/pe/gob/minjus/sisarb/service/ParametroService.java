package pe.gob.minjus.sisarb.service;

import org.springframework.http.ResponseEntity;
import pe.gob.minjus.sisarb.model.domain.Parametro;
import pe.gob.minjus.sisarb.model.response.Respuesta;

import java.util.List;

public interface ParametroService {

    ResponseEntity<Respuesta<List<Parametro>>> listarParametros();
    ResponseEntity<Respuesta<Parametro>> insertParametros(Parametro request);
    ResponseEntity<Respuesta<Parametro>> updateParametros(Parametro request);
    ResponseEntity<Respuesta<Parametro>> deleteParametros(Parametro request);
}
