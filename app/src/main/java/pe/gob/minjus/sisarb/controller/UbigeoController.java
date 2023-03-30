package pe.gob.minjus.sisarb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.minjus.sisarb.model.domain.UbigeoItem;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.UbigeoService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/ubigeo")
public class UbigeoController {


    @Autowired
    UbigeoService ubigeoService;

    @GetMapping("{origen:reniec|sunat}/departamentos")
    public ResponseEntity<Respuesta<List<UbigeoItem>>> obtenerDepartamentos(@Valid  @PathVariable String origen){
    	return obtieneUbigeos(origen, null);
    }
    
    @GetMapping("{origen:reniec|sunat}/departamentos/{ubigeoId}/provincias")
    public ResponseEntity<Respuesta<List<UbigeoItem>>> obtieneProvinciasPorDepartamento(@Valid  @PathVariable String origen, @PathVariable Long ubigeoId){
    	return obtieneUbigeos(origen, ubigeoId);
    }
    
    @GetMapping("{origen:reniec|sunat}/provincias/{ubigeoId}/distritos")
    public ResponseEntity<Respuesta<List<UbigeoItem>>> obtieneDistritosPorProvincia(@Valid  @PathVariable String origen, @PathVariable Long ubigeoId){
        return obtieneUbigeos(origen, ubigeoId);
    }
    
    @GetMapping("{origen:reniec|sunat}/{ubigeo}")
    public ResponseEntity<Respuesta<List<UbigeoItem>>> obtieneUbigeoSegunOrigen (@Valid  @PathVariable String origen, @PathVariable Long ubigeo){
        return  obtieneUbigeos(origen, ubigeo);
    }

    @GetMapping("{origen:reniec|sunat}/por-id/{ubigeo}")
    public ResponseEntity<Respuesta<UbigeoItem>> obtenerUbigeoPorId (@Valid  @PathVariable String origen, @PathVariable Long ubigeo){
        return  obtieneUbigeo(origen, ubigeo);
    }
    
    
	private ResponseEntity<Respuesta<List<UbigeoItem>>> obtieneUbigeos(String origen, Long ubigeoPadreId) {
		return  ubigeoService.obtieneUbigeos(origen, ubigeoPadreId);
	}

    private ResponseEntity<Respuesta<UbigeoItem>> obtieneUbigeo(String origen, Long ubigeoId) {
        return  ubigeoService.obtenerUbigeoPorId(origen, ubigeoId);
    }
}
