package pe.gob.minjus.sisarb.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class SubEtapaArbitral extends GenericDomain{
    private Integer subEtapaArbitralId;

    @NotNull(message = "Debe seleccionar una etapa arbitral")
    private Integer etapaArbitralId;

    @NotEmpty(message = "El nombre es obligatorio")
    @Size( max = 50,message = "La longitud máxima del nombre es de 50 caracteres")
    private String nombre;

    @NotNull(message = "El orden de la sub etapa arbitral es obligatorio")
    @Min(value = 1,message =  "El valor mínimo para el orden es 1")
    private Integer orden;

    @JsonIgnore
    public String getNameTable(){
        return "MAE_SUB_ETAPA_ARBITRAL";
    }
}
