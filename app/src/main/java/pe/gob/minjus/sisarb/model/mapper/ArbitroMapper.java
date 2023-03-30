package pe.gob.minjus.sisarb.model.mapper;

import pe.gob.minjus.sisarb.model.domain.Arbitro;
import pe.gob.minjus.sisarb.model.request.ArbitroBusquedaRequest;

import java.util.List;

public interface ArbitroMapper {
    List<Arbitro> listBusqueda(ArbitroBusquedaRequest request);
    Integer listBusquedaTotal (ArbitroBusquedaRequest request);
    Arbitro findById(Integer id);
    Integer insert(Arbitro request);
    Integer update(Arbitro request);
    Integer deleteById(Integer id);
}
