package pe.gob.minjus.sisarb.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.minjus.sisarb.enums.AuditTipoEnum;
import pe.gob.minjus.sisarb.exception.EntityNotFoundException;
import pe.gob.minjus.sisarb.model.domain.Auditoria;
import pe.gob.minjus.sisarb.model.domain.Directivo;
import pe.gob.minjus.sisarb.model.mapper.DirectivoMapper;
import pe.gob.minjus.sisarb.model.request.DirectivoBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.AuditoriaService;
import pe.gob.minjus.sisarb.service.DirectivoService;
import pe.gob.minjus.sisarb.utils.Constantes;
import pe.gob.minjus.sisarb.utils.JsonConvert;

import java.util.List;

@Slf4j
@Service
public class DirectivoServiceImpl implements DirectivoService {

    @Autowired
    DirectivoMapper mapper;

    @Autowired
    AuditoriaService auditoriaService;

    @Override
    public ResponseEntity<Respuesta<List<Directivo>>> listBusqueda(DirectivoBusquedaRequest request) {
        Respuesta<List<Directivo>> response = new Respuesta<>();
        try {
            List<Directivo> list = mapper.listBusqueda(request);
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
    public ResponseEntity<Respuesta<Directivo>> insert(Directivo request)  {
        Respuesta<Directivo> response = new Respuesta<>();
        try {
            mapper.insert(request);
            Directivo objSave = mapper.findById(request.getDirectivoId());
            auditoriaService.insert(new Auditoria(request.getTableName(),
                    (request.getDirectivoId()==null)?null:request.getDirectivoId().toString(),
                    request.getAuditUsuarioCreacion(), AuditTipoEnum.REGISTRAR.name(),
                    null, JsonConvert.objectToJsonString(objSave)) );
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
    public ResponseEntity<Respuesta<Directivo>> update(Directivo request) {
        Respuesta<Directivo> response = new Respuesta<>();
        try {
            Directivo objAu = getById(request.getDirectivoId());
            mapper.update(request);
            Directivo objUpdate = mapper.findById(request.getDirectivoId());
            auditoriaService.insert(new Auditoria(request.getTableName(),
                    (request.getDirectivoId()==null)?null:request.getDirectivoId().toString(),
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
    public ResponseEntity<Respuesta<Directivo>> findById(Directivo request)  {
        Respuesta<Directivo> response = new Respuesta<>();
        try {
            Directivo objGet = getById(request.getDirectivoId());
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
    public  ResponseEntity<Respuesta<Directivo>> deleteById(Directivo request)  {
        Respuesta<Directivo> response = new Respuesta<>();
        try {
            Directivo objGet = getById(request.getDirectivoId());
            mapper.deleteById(objGet.getDirectivoId());
            response.setData(null);
            response.setMensaje(Constantes.MSJ_CRUD_ELIMINAR);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e) {
            log.error(e.getMessage());
            response.setMensaje(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    Directivo getById(Integer id){
        Directivo objBusq =  mapper.findById(id);
        if(objBusq==null){
            throw new EntityNotFoundException("NO EXISTE EL REGISTRO CON ID: "+id);
        }
        return objBusq;
    }
}
