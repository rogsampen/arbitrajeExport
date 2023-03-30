package pe.gob.minjus.sisarb.service;

import org.springframework.http.ResponseEntity;
import pe.gob.minjus.sisarb.model.domain.Arbitro;
import pe.gob.minjus.sisarb.model.request.ArbitroBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;

import java.util.List;

public interface ArbitroService {
    ResponseEntity<Respuesta<List<Arbitro>>> listBusqueda(ArbitroBusquedaRequest request);

    ResponseEntity<Respuesta<Arbitro>> findById(Arbitro request);

    ResponseEntity<Respuesta<Arbitro>> insert(Arbitro request);

    ResponseEntity<Respuesta<Arbitro>> update(Arbitro request);

    ResponseEntity<Respuesta<Arbitro>> deleteById(Arbitro request);
}
