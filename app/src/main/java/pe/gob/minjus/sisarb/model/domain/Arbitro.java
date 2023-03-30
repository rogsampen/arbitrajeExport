package pe.gob.minjus.sisarb.model.domain;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Arbitro extends GenericDomain {

    private Integer arbitroId;
    @NotNull(message = "Debe seleccionar una especialidad")
    private Integer especialidadId;
    @NotNull(message = "Debe seleccionar un tipo documento")
    private Integer tipoDocumentoId;
    @NotNull(message = "Debe seleccionar una declaración interes")
    private Integer declaracionInteresId;
    @NotNull(message = "Debe seleccionar una sanción")
    private Integer sancionId;
    private Integer ubigeoId;
    @NotEmpty(message = "El nombre del directivo es obligatorio")
    @Size( min = 3,message = "La longitud mínima del apellido paterno es de 3 caracteres")
    @Size( max = 15,message = "La longitud máxima del apellido paterno es de 15 caracteres")
    private String apellidoPaterno;
    private String apellidoMaterno;

    @NotEmpty(message = "El nombre del directivo es obligatorio")
    @Size( min = 3,message = "La longitud mínima del nombre es de 3 caracteres")
    @Size( max = 20,message = "La longitud máxima del nombre es de 20 caracteres")
    private String nombre;

    @NotEmpty(message = "La experiencia arbitraje es obligatorio")
    private String experienciaArbitraje;

    @NotEmpty(message = "El tipo es obligatorio")
    private String tipo;

    //Campos adicionales para mostrar las descripciones o nombres de las llaves
    private String especialidadNombre;
    private String tipoDocumentoNombre;
    private String declaracionInteresNombre;
    private String sancionNombre;

    public static final String TABLE_NAME ="TRS_ARBITRO";


}
