package pe.gob.minjus.sisarb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.gob.minjus.sisarb.enums.AuditTipoEnum;
import pe.gob.minjus.sisarb.exception.EntityNotFoundException;
import pe.gob.minjus.sisarb.model.domain.Arbitro;
import pe.gob.minjus.sisarb.model.domain.Auditoria;
import pe.gob.minjus.sisarb.model.mapper.ArbitroMapper;
import pe.gob.minjus.sisarb.model.request.ArbitroBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.ArbitroService;
import pe.gob.minjus.sisarb.service.AuditoriaService;
import pe.gob.minjus.sisarb.utils.Constantes;
import pe.gob.minjus.sisarb.utils.JsonConvert;

import java.util.List;

@Slf4j
@Service
public class ArbitroServiceImpl implements ArbitroService {
    @Autowired
    ArbitroMapper mapper;

    @Autowired
    AuditoriaService auditoriaService;

    @Override
    public ResponseEntity<Respuesta<List<Arbitro>>> listBusqueda(ArbitroBusquedaRequest request) {
        Respuesta<List<Arbitro>> response = new Respuesta<>();
        try {
            List<Arbitro> list = mapper.listBusqueda(request);
            Integer total = mapper.listBusquedaTotal(request);
            response.setData(list);
            response.setTotalRegistros(total);
            response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(DataAccessException e) {
            log.error(e.getMessage());
            response.setMensaje(e.getMostSpecificCause().toString());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Respuesta<Arbitro>> insert(Arbitro request)  {
        Respuesta<Arbitro> response = new Respuesta<>();
        try {
            mapper.insert(request);
            Arbitro objSave = mapper.findById(request.getArbitroId());
            auditoriaService.insert(new Auditoria(Arbitro.TABLE_NAME,
                    (request.getArbitroId()==null)?null:request.getArbitroId().toString(),
                    request.getAuditUsuarioCreacion(), AuditTipoEnum.REGISTRAR.name(),
                    null, JsonConvert.objectToJsonString(objSave)));
            response.setData(objSave);
            response.setMensaje(Constantes.MSJ_CRUD_REGISTRO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e) {
            log.error(e.getMessage());
            response.setMensaje(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Respuesta<Arbitro>> update(Arbitro request)  {
        Respuesta<Arbitro> response = new Respuesta<>();
        try {
            Arbitro objAu = getById(request.getArbitroId());
            mapper.update(request);
            Arbitro objUpdate = mapper.findById(request.getArbitroId());
            auditoriaService.insert(new Auditoria(Arbitro.TABLE_NAME,
                    (request.getArbitroId()==null)?null:request.getArbitroId().toString(),
                    request.getAuditUsuarioModifica(),
                    AuditTipoEnum.MODIFICAR.name(),
                    JsonConvert.objectToJsonString(objAu), JsonConvert.objectToJsonString(objUpdate)));
            response.setData(objUpdate);
            response.setMensaje(Constantes.MSJ_CRUD_MODIFICAR);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e) {
            log.error(e.getMessage());
            response.setMensaje(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Respuesta<Arbitro>> findById(Arbitro request)  {
        Respuesta<Arbitro> response = new Respuesta<>();
        try {
            Arbitro objGet = getById(request.getArbitroId());
            response.setData(objGet);
            response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e) {
            log.error(e.getMessage());
            response.setMensaje(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public  ResponseEntity<Respuesta<Arbitro>> deleteById(Arbitro request) {
        Respuesta<Arbitro> response = new Respuesta<>();
        try {
            Arbitro objGet = getById(request.getArbitroId());
            mapper.deleteById(objGet.getArbitroId());
            response.setData(null);
            response.setMensaje(Constantes.MSJ_CRUD_ELIMINAR);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e) {
            log.error(e.getMessage());
            response.setMensaje(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    Arbitro getById(Integer id){
        Arbitro objBusq =  mapper.findById(id);
        if(objBusq==null){
            throw new EntityNotFoundException("NO EXISTE EL REGISTRO CON ID: "+id);
        }
        return objBusq;
    }
}
