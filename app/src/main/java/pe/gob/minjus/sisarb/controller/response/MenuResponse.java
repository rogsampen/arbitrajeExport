package pe.gob.minjus.sisarb.controller.response;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MenuResponse {
	
	private int id;
	private String label;
	private String icon;
	private ArrayList<String> routerLink;
	private List<MenuResponse> items;
	private String url;
	private boolean preventExact;
	

}
