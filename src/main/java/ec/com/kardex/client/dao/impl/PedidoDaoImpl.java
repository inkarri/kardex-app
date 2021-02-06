package ec.com.kardex.client.dao.impl;

import ec.com.kardex.client.dao.CommonsDao;
import ec.com.kardex.client.dao.PedidoDao;
import ec.com.kardex.client.dto.PedidoDTO;
import org.springframework.stereotype.Repository;

@Repository
public class PedidoDaoImpl extends CommonsDao implements PedidoDao {

    @Override
    public void crearPedido(PedidoDTO pedido) {
        em.persist(pedido);
    }

}
