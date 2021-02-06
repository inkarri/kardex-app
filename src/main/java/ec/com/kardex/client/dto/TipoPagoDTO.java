package ec.com.kardex.client.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "SKTIPOPAGO")
@Getter
@Setter
public class TipoPagoDTO extends AuditoriaDTO implements Serializable {

    private static final long serialVersionUID = -646214666600773268L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tipoPagoPk;

    @Column
    private String descripcionPago;

}
