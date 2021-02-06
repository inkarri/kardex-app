package ec.com.kardex.client.dao.impl;

import ec.com.kardex.client.dao.CommonsDao;
import ec.com.kardex.client.dao.DetallePedidoDao;
import ec.com.kardex.client.dto.DetallePedidoDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DetallePedidoDaoImpl extends CommonsDao implements DetallePedidoDao {

    @Override
    public void crearDetallesPedido(List<DetallePedidoDTO> detallesPedido) {
        em.clear();
        detallesPedido.forEach(em::persist);
        em.flush();
        em.clear();
    }

}
