package ec.com.kardex.client.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class AuditoriaDTO implements Serializable {

    private static final long serialVersionUID = 3754455288524636478L;

    @Column
    protected boolean estado;

    @Column(name = "FECHACREACION")
    protected Date fechaCreacion;

}
