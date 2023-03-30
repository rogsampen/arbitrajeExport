package pe.gob.minjus.sisarb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.minjus.sisarb.model.domain.Parametro;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.ParametroService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/parametro")
public class ParametroController {

    @Autowired
    ParametroService parametroService;

    @GetMapping("listAll")
    public ResponseEntity<Respuesta<List<Parametro>>> listParametros(){
        return parametroService.listarParametros();
    }
    
    @PostMapping("insert")
    public ResponseEntity<Respuesta<Parametro>> insertParametro(@Valid @RequestBody  Parametro request){
        return parametroService.insertParametros(request);
    }
    
    @PutMapping("update")
    public ResponseEntity<Respuesta<Parametro>> updateParametro(@Valid @RequestBody  Parametro request) {
        return parametroService.updateParametros(request);
    }
    
    @DeleteMapping("delete")
    public ResponseEntity<Respuesta<Parametro>> eliminarParametro(@RequestBody  Parametro request){
        return parametroService.deleteParametros(request);
    }
       
}
