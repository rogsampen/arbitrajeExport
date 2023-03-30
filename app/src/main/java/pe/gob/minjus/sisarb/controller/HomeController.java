package pe.gob.minjus.sisarb.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.minjus.sisarb.model.mapper.EtapaArbitralMapper;


@RestController
@RequestMapping("api/home")
public class HomeController {
	

	@Autowired
	EtapaArbitralMapper etapaArbitralMapper;
	
	@GetMapping
	public String saludo() {
		return "hola";
	}

}
