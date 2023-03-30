package pe.gob.minjus.sisarb.utils;

import pe.gob.minjus.psm.ws.SecurityClientWS;
import pe.gob.minjus.psm.ws.domain.Menu;
import pe.gob.minjus.psm.ws.domain.ResponseSecurity;
import pe.gob.minjus.psm.ws.domain.Usuario;

public class SeguridadS {
	
    String urlSecurity=Constantes.URL_SERVICIO_SEGURIDAD;
    SecurityClientWS client=new SecurityClientWS(urlSecurity);
    
    public ResponseSecurity<Usuario> conectarServicioSeguridad(Integer aplicacionId, String usuario,String clave, String ip, String mac){
        	return new SecurityClientWS(urlSecurity).login(aplicacionId, usuario, clave, ip, mac);
    }
    
    public ResponseSecurity<Menu> obtenerMenu(Integer aplicacionId, Integer rolId, String usuario){
    	return client.obtenerMenus(aplicacionId, rolId, usuario);
}
    
    public ResponseSecurity<Menu> obtenerMenuAnidado(Integer aplicacionId, Integer rolId, String usuario){
    	return client.obtenerMenusAnidado(aplicacionId, rolId, usuario);
}
    
    
    

}
