package pe.gob.minjus.sisarb.model.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Login {
	private int id;
    private String user;
    private String clave;
}
