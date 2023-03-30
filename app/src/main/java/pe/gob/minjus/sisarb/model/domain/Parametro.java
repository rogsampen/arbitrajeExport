package pe.gob.minjus.sisarb.model.domain;

import lombok.*;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Parametro extends GenericDomain{
	private Integer parametroId;
	private Integer tipoParametroId;
	private String detalle;
	@NotNull(message = "El campo valor no debe ser vacío")
	private String valor;
	
    @JsonIgnore
    public String getNameTable(){
        return "MAE_PARAMETRO_VALOR";
    }
	
}
