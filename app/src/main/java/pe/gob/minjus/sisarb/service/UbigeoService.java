package pe.gob.minjus.sisarb.service;

import org.springframework.http.ResponseEntity;
import pe.gob.minjus.sisarb.model.domain.UbigeoItem;
import pe.gob.minjus.sisarb.model.response.Respuesta;

import java.util.List;

public interface UbigeoService {

    ResponseEntity<Respuesta<List<UbigeoItem>>> obtieneUbigeos(String origen, Long ubigeoPadreId);

    ResponseEntity<Respuesta<UbigeoItem>> obtenerUbigeoPorId(String origen, Long ubigeoId);

  
}
