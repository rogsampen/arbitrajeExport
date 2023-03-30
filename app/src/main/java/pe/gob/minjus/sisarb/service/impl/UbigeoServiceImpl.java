package pe.gob.minjus.sisarb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.minjus.sisarb.model.domain.UbigeoItem;
import pe.gob.minjus.sisarb.model.mapper.UbigeoMapper;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.UbigeoService;

import java.util.List;

@Slf4j
@Service
public class UbigeoServiceImpl implements UbigeoService {

    @Autowired
    UbigeoMapper ubigeoMapper;


    @Transactional
    @Override
    public ResponseEntity<Respuesta<List<UbigeoItem>>> obtieneUbigeos(String origen, Long ubigeoPadreId) {
        Respuesta<List<UbigeoItem>> response = new Respuesta<>();
        try {
            List<UbigeoItem> list = ubigeoMapper.getUbigeoList(origen, ubigeoPadreId);
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

    @Override
    public ResponseEntity<Respuesta<UbigeoItem>> obtenerUbigeoPorId(String origen, Long ubigeoId) {
        Respuesta<UbigeoItem> response = new Respuesta<>();
        try {

            UbigeoItem list = ubigeoMapper.getUbigeoPorId(origen, ubigeoId);
            response.setData(list);
            response.setTotalRegistros(1);
            response.setMensaje("Se encontraron resultados");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(DataAccessException e) {
            log.error(e.getMessage());
            response.setMensaje(e.getMostSpecificCause().toString());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
