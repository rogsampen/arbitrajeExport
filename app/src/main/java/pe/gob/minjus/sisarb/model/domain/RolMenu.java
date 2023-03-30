package pe.gob.minjus.sisarb.model.domain;

import lombok.*;
import pe.gob.minjus.sisarb.controller.response.MenuResponse;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RolMenu {
	private int idRol;
	private String nombre;
	private boolean estado;
	private boolean principal;
	private List<MenuRol> menu;
}
