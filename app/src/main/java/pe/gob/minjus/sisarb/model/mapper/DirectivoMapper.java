package pe.gob.minjus.sisarb.model.mapper;

import pe.gob.minjus.sisarb.model.domain.Directivo;
import pe.gob.minjus.sisarb.model.request.DirectivoBusquedaRequest;

import java.util.List;

public interface DirectivoMapper {


    List<Directivo> listBusqueda(DirectivoBusquedaRequest request);
    Integer listBusquedaTotal (DirectivoBusquedaRequest request);
    Directivo findById(Integer id);
    Integer insert(Directivo request);
    Integer update(Directivo request);

    Integer deleteById(Integer id);
}
