package pe.gob.minjus.sisarb.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Directivo extends GenericDomain {
    private Integer directivoId;

    @NotEmpty(message = "El nombre del directivo es obligatorio")
    @Size( min = 3,message = "La longitud mínima del nombre es de 3 caracteres")
    @Size( max = 30,message = "La longitud máxima del nombre es de 30 caracteres")
    private String nombre;

    @NotEmpty(message = "El nombre del directivo es obligatorio")
    @Size( min = 3,message = "La longitud mínima del apellido paterno es de 3 caracteres")
    @Size( max = 15,message = "La longitud máxima del apellido paterno es de 15 caracteres")
    private String apellidoPaterno;

    private String apellidoMaterno;

    @NotEmpty(message = "El telefono del directivo es obligatorio")
    @Size( max = 15,message = "La longitud máxima del teléfono es de 15 caracteres")
    private String telefono;

    @NotEmpty(message = "El correo electrónico del directivo es obligatorio")
    @Size( max = 40,message = "La longitud máxima del correo es de 40 caracteres")
    private String email;

    @NotEmpty(message = "La dirección del directivo es obligatorio")
    @Size( max = 150,message = "La longitud máxima de la direccion es de 150 caracteres")
    private String direccion;

    @JsonIgnore
    public String getTableName(){
        return "MAE_DIRECTIVO";
    }
}
