package pe.gob.minjus.sisarb.model.domain;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Especialidad extends GenericDomain{

    private Integer especialidadId;
    private String nombre;
}
