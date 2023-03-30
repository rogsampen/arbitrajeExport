package pe.gob.minjus.sisarb.model.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class GenericDomain {

    //Atributos de auditoria

    private String auditUsuarioCreacion;
    private Date auditFechaCreacion;
    private String auditUsuarioModifica;
    private Date auditFechaModifica;

    private String auditFechaCreacionFormat;
    private String auditFechaModificaFormat;
}
