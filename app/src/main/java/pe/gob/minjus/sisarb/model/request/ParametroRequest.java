package pe.gob.minjus.sisarb.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParametroRequest {
 
    private String parametroId;
    private String tipoParametroId;
    private String valor;
    private String descripcion;
    private String estado;
    private String auditUsuarioCreacion;
    
}
