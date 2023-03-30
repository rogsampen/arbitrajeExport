package pe.gob.minjus.sisarb.model.mapper;

import pe.gob.minjus.sisarb.model.domain.Parametro;
import pe.gob.minjus.sisarb.model.request.ParametroRequest;
import java.util.List;

public interface ParametroMapper {

    List<Parametro> listParametro();
    Integer listParametroTotal (ParametroRequest request);
    void insert(Parametro request);
    void update(Parametro request);
    void delete(int id);
    Parametro find(Parametro request);
    Parametro findByName(Parametro request);
    Parametro findById(Integer id);
    
}
