package ec.com.kardex.client.dao;

import ec.com.kardex.client.dto.DetallePedidoDTO;

import java.util.List;

public interface DetallePedidoDao {

    void crearDetallesPedido(List<DetallePedidoDTO> detallesPedido);

    List<DetallePedidoDTO> obtenerDetallesComprasPorUsuario(String userName);

}
