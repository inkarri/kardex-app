package ec.com.kardex.client.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class PedidoVO implements Serializable {
    private static final long serialVersionUID = -5232209287645573848L;

    private Integer tipoPagoPk;

    private String username;

    private List<DetallePedidoVO> detallePedido;

}
