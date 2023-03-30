package pe.gob.minjus.sisarb.utils;



public class Constantes {
    private Constantes() {
    }

    public static final String MSJ_CRUD_LISTADO="Se ejecutó la consulta correctamente";
    public static final String MSJ_CRUD_REGISTRO="Se guardó el registro correctamente";
    public static final String MSJ_CRUD_MODIFICAR="Se actualizó el registro correctamente";
    public static final String MSJ_CRUD_ELIMINAR="Se eliminó el registro correctamente";

    public static final String MSJ_VALIDACION_USUARIO_REGISTRO="El usuario de creación es obligatorio";
    public static final String MSJ_VALIDACION_USUARIO_MODIFICA="El usuario que modifica es obligatorio";
    public static final String MSJ_VALIDACION_USUARIO_ELIMINA="El usuario que elimina es obligatorio";
    public static final String MSJ_VALIDACION_NO_EXISTE_REGISTRO_ID="No existe el registro con ID: ";
    public static final String MSJ_VALIDACION_SOLO_CARACTERES_ALFANUMERICOS="No se permiten caracteres especiales ";

    public static final String MSJ_VALIDACION_REGISTRO_REPETIDO="Registro repetido";
    public static final String MSJ_VALIDACION_REGISTRO_RELACIONADO="No se puede eliminar, el registro tiene relación con otro proceso";
    
    public static final int ID_UNO=1;
    public static final String URL_SERVICIO_SEGURIDAD="http://172.17.17.61/sgsi-security-ws/SecurityServiceWS?wsdl";
    public static final int ID_APLICACION_SERVICIO_SEGURIDAD=104;
    public static final String IP_SERVICIO_SEGURIDAD="E8:EA:B5:A1";
    public static final String MAC_SERVICIO_SEGURIDAD="172.29.15.110";


}
