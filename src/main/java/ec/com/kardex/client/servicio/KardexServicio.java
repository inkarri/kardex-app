package ec.com.kardex.client.servicio;

import ec.com.kardex.client.dao.TipoPagoDao;
import ec.com.kardex.client.dto.ArticuloDTO;
import ec.com.kardex.client.dto.ClasificacionDTO;
import ec.com.kardex.client.dto.DetallePedidoDTO;
import ec.com.kardex.client.dto.PedidoDTO;
import ec.com.kardex.client.dto.PersonaDTO;
import ec.com.kardex.client.dto.UsuarioDTO;
import ec.com.kardex.client.vo.PedidoVO;

import java.util.List;

public interface KardexServicio {

    List<ArticuloDTO> obtenerArticulos();

    void crearArticulos(List<ArticuloDTO> articulos);

    ArticuloDTO insertarArticulo(ArticuloDTO articulo);

    void crearClasificacion(ClasificacionDTO clasificacion);

    void crearsuario(UsuarioDTO usuario);

    void crearPedido(PedidoDTO pedido);

    void crearDetallesPedido(List<DetallePedidoDTO> detallesPedido);

    List<ClasificacionDTO> obtenerClasificaciones();

    void crearPersona(PersonaDTO persona);

    List<TipoPagoDao> obtenerTiposPago();

    List<ArticuloDTO> obtenerArticulosConExistencia();

    void comprar(PedidoVO pedido);

    List<DetallePedidoDTO> obtenerDetallesComprasPorUsuario(String userName);

    List<PedidoDTO> obtenerPedidosPorUsuario(String userName);

}
