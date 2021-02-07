package ec.com.kardex.client.dao.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import ec.com.kardex.client.dao.CommonsDao;
import ec.com.kardex.client.dao.DetallePedidoDao;
import ec.com.kardex.client.dto.ArticuloDTO;
import ec.com.kardex.client.dto.ClasificacionDTO;
import ec.com.kardex.client.dto.DetallePedidoDTO;
import static ec.com.kardex.client.dto.QDetallePedidoDTO.detallePedidoDTO;

import static ec.com.kardex.client.dto.QPedidoDTO.pedidoDTO;

import static ec.com.kardex.client.dto.QPersonaDTO.personaDTO;

import static ec.com.kardex.client.dto.QUsuarioDTO.usuarioDTO;

import static ec.com.kardex.client.dto.QTipoPagoDTO.tipoPagoDTO;

import static ec.com.kardex.client.dto.QArticuloDTO.articuloDTO;

import static ec.com.kardex.client.dto.QClasificacionDTO.clasificacionDTO;

import ec.com.kardex.client.dto.PedidoDTO;
import ec.com.kardex.client.dto.PersonaDTO;
import ec.com.kardex.client.dto.TipoPagoDTO;
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

    @Override
    public List<DetallePedidoDTO> obtenerDetallesComprasPorUsuario(String userName) {
        JPAQuery<DetallePedidoDTO> query = new JPAQuery<>(em);
        return query.from(detallePedidoDTO)
                .innerJoin(detallePedidoDTO.pedido, pedidoDTO)
                .innerJoin(pedidoDTO.persona, personaDTO)
                .innerJoin(personaDTO.usuario, usuarioDTO)
                .innerJoin(pedidoDTO.tipoPago, tipoPagoDTO)
                .innerJoin(detallePedidoDTO.articulo, articuloDTO)
                .innerJoin(articuloDTO.clasificacion, clasificacionDTO)
                .where(usuarioDTO.nombreUsuario.eq(userName),
                        detallePedidoDTO.estado.eq(Boolean.TRUE),
                        pedidoDTO.estado.eq(Boolean.TRUE),
                        articuloDTO.estado.eq(Boolean.TRUE))
                .select(Projections.bean(DetallePedidoDTO.class,
                        detallePedidoDTO.dpedidoDetallePk,
                        detallePedidoDTO.pedidoPk,
                        detallePedidoDTO.cantidad,
                        Projections.bean(PedidoDTO.class,
                                pedidoDTO.codigoPedido,
                                pedidoDTO.pedidoPk,
                                pedidoDTO.totalPedido,
                                pedidoDTO.fechaCreacion,
                                Projections.bean(TipoPagoDTO.class,
                                        tipoPagoDTO.descripcionPago)
                                        .as(pedidoDTO.tipoPago),
                                Projections.bean(PersonaDTO.class,
                                        personaDTO.nombres,
                                        personaDTO.apellidos)
                                        .as(pedidoDTO.persona))
                                .as(detallePedidoDTO.pedido),
                        Projections.bean(ArticuloDTO.class,
                                articuloDTO.codigoBarras,
                                articuloDTO.descripcion,
                                Projections.bean(ClasificacionDTO.class,
                                        clasificacionDTO.nombre)
                                        .as(articuloDTO.clasificacion))
                                .as(detallePedidoDTO.articulo)))
                .fetch();
    }

}
