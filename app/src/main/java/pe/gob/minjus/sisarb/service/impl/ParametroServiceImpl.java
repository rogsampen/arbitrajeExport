package pe.gob.minjus.sisarb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.minjus.sisarb.enums.AuditTipoEnum;
import pe.gob.minjus.sisarb.exception.EntityNotFoundException;
import pe.gob.minjus.sisarb.exception.EntityValidationCustom;
import pe.gob.minjus.sisarb.model.domain.Auditoria;
import pe.gob.minjus.sisarb.model.domain.Parametro;
import pe.gob.minjus.sisarb.model.mapper.ParametroMapper;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.AuditoriaService;
import pe.gob.minjus.sisarb.service.ParametroService;
import pe.gob.minjus.sisarb.utils.Constantes;
import pe.gob.minjus.sisarb.utils.JsonConvert;

import java.util.List;

@Slf4j
@Service
public class ParametroServiceImpl implements ParametroService {

    @Autowired
    ParametroMapper parametroMapper;
    
    @Autowired
    AuditoriaService auditoriaService;

    @Transactional
    @Override
    public ResponseEntity<Respuesta<List<Parametro>>> listarParametros() {
        Respuesta<List<Parametro>> response = new Respuesta<>();
        try {
            List<Parametro> list = parametroMapper.listParametro();
            response.setData(list);
            response.setTotalRegistros(list.size());
            response.setMensaje("Se encontraron resultados");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(DataAccessException e) {
            log.error(e.getMessage());
            response.setMensaje(e.getMostSpecificCause().toString());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Transactional
    @Override
    public ResponseEntity<Respuesta<Parametro>> insertParametros(Parametro request) {
        Respuesta<Parametro> response = new Respuesta<>();
        try {
        
        	Parametro parametro = parametroMapper.findByName(request);
        	
        	if(parametro==null) {
                parametroMapper.insert(request);
                Parametro objSave = getById(request.getParametroId());
                auditoriaService.insert(new Auditoria(request.getNameTable(),
                        (request.getParametroId() == null) ? null : request.getParametroId().toString(),
                        request.getAuditUsuarioCreacion(), AuditTipoEnum.REGISTRAR.name(),
                        null, JsonConvert.objectToJsonString(objSave)));
                response.setData(objSave);
                response.setMensaje(Constantes.MSJ_CRUD_REGISTRO);                
                return new ResponseEntity<>(response, HttpStatus.OK);        		
        	}else {
                throw new EntityValidationCustom(Constantes.MSJ_VALIDACION_REGISTRO_REPETIDO);     		
        	}        
	    }catch(Exception e) {
	        log.error(e.getMessage());
	        response.setMensaje(e.getMessage());
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }        
    }
    
    Parametro getById(Integer id) {
        Parametro objBusq = parametroMapper.findById(id);
        if (objBusq == null) {
            throw new EntityNotFoundException(Constantes.MSJ_VALIDACION_NO_EXISTE_REGISTRO_ID + id);
        }
        return objBusq;
    }    
    
    
    @Override
    public ResponseEntity<Respuesta<Parametro>> updateParametros(Parametro request)  {
        Respuesta<Parametro> response = new Respuesta<>();
        try {
        	
        	Parametro objAu = getById(request.getParametroId());
        	
       	 if(!request.getValor().toUpperCase().trim().equals(objAu.getValor().toUpperCase().trim())){
       	 
             Parametro list = parametroMapper.findByName(request);
             if(list==null) {
                 parametroMapper.update(request);
                 Parametro objSave = getById(request.getParametroId());
                 auditoriaService.insert(new Auditoria(request.getNameTable(),
                         (request.getParametroId() == null) ? null : request.getParametroId().toString(),
                         request.getAuditUsuarioModifica(),
                         AuditTipoEnum.MODIFICAR.name(),
                         JsonConvert.objectToJsonString(objAu), JsonConvert.objectToJsonString(objSave)));                 
                 response.setData(objSave);
                 response.setMensaje(Constantes.MSJ_CRUD_MODIFICAR);
                 return new ResponseEntity<>(response, HttpStatus.OK);
             }else {
                 throw new EntityValidationCustom(Constantes.MSJ_VALIDACION_REGISTRO_REPETIDO);              	
             }       		 
       	 
       	 } else {
             parametroMapper.update(request);
             Parametro objSave = getById(request.getParametroId());
             auditoriaService.insert(new Auditoria(request.getNameTable(),
                     (request.getParametroId() == null) ? null : request.getParametroId().toString(),
                     request.getAuditUsuarioModifica(),
                     AuditTipoEnum.MODIFICAR.name(),
                     JsonConvert.objectToJsonString(objAu), JsonConvert.objectToJsonString(objSave)));    
             response.setData(objSave);
             response.setMensaje(Constantes.MSJ_CRUD_MODIFICAR);
             return new ResponseEntity<>(response, HttpStatus.OK); 
       	 }        	      

        }catch(DataAccessException e) {
            log.error(e.getMessage());
            response.setMensaje(e.getMostSpecificCause().toString());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    public ResponseEntity<Respuesta<Parametro>> deleteParametros(Parametro request) {
        Respuesta<Parametro> response = new Respuesta<>();
        try {     	
            Parametro objSave = getById(request.getParametroId());   
            parametroMapper.delete(request.getParametroId());         
            auditoriaService.insert(new Auditoria(objSave.getNameTable(),
                    (objSave.getParametroId() == null) ? null : objSave.getParametroId().toString(),
                    request.getAuditUsuarioModifica(),
                    AuditTipoEnum.ELIMINAR.name(),
                    JsonConvert.objectToJsonString(objSave), null));                        
            response.setData(null);
            response.setMensaje(Constantes.MSJ_CRUD_ELIMINAR);
            return new ResponseEntity<>(response, HttpStatus.OK);            
        }catch(DataAccessException e) {
            log.error(e.getMessage());
            response.setMensaje(e.getMostSpecificCause().toString());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
