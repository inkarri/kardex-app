package ec.com.kardex.client.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DetallePedidoVO implements Serializable {

    private static final long serialVersionUID = -8443056748026830864L;

    private Integer articuloPk;

    private Integer cantidad;

    private Double precio;

    private Integer existencia;

}
