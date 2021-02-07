package ec.com.kardex.client.servicio.impl;

import com.querydsl.core.QueryException;
import ec.com.kardex.client.dao.ArticuloDao;
import ec.com.kardex.client.dao.ClasificacionDao;
import ec.com.kardex.client.dao.DetallePedidoDao;
import ec.com.kardex.client.dao.PedidoDao;
import ec.com.kardex.client.dao.PersonaDao;
import ec.com.kardex.client.dao.TipoPagoDao;
import ec.com.kardex.client.dao.UsuarioDao;
import ec.com.kardex.client.dto.ArticuloDTO;
import ec.com.kardex.client.dto.AuditoriaDTO;
import ec.com.kardex.client.dto.ClasificacionDTO;
import ec.com.kardex.client.dto.DetallePedidoDTO;
import ec.com.kardex.client.dto.PedidoDTO;
import ec.com.kardex.client.dto.PersonaDTO;
import ec.com.kardex.client.dto.UsuarioDTO;
import ec.com.kardex.client.exception.KardexExcepction;
import ec.com.kardex.client.servicio.KardexServicio;
import ec.com.kardex.client.vo.DetallePedidoVO;
import ec.com.kardex.client.vo.PedidoVO;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.groupingBy;

@Transactional
@Service
@Slf4j
public class KardexServicioImpl implements KardexServicio {

    @Autowired
    private ArticuloDao articuloDao;

    @Autowired
    private PedidoDao pedidoDao;

    @Autowired
    private ClasificacionDao clasificacionDao;

    @Autowired
    private DetallePedidoDao detallePedidoDao;

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private TipoPagoDao tipoPagoDao;

    @Autowired
    private PersonaDao personaDao;

    @Override
    public List<ArticuloDTO> obtenerArticulos() {
        try {
            return articuloDao.obtenerArticulos();
        } catch (QueryException e) {
            throw new KardexExcepction("No fue posble obtener articulos registrados", e);
        }
    }

    @Override
    public void crearArticulos(List<ArticuloDTO> articulos) {
        try {
            if (articulos == null || articulos.isEmpty()) {
                log.info("Sin datos para regsistro.");
                return;
            }
            articuloDao.crearArticulos(articulos);
        } catch (HibernateException e) {
            throw new KardexExcepction("", e);
        }
    }

    @Override
    public ArticuloDTO insertarArticulo(ArticuloDTO articulo) {
        try {
            if (articulo == null) {
                throw new KardexExcepction("Los datos del articulo son obligatorios para crear el registro");
            }
            agregarDatosAuditoria(Collections.singletonList(articulo));
            return articuloDao.insertarArticulo(articulo);
        } catch (HibernateException e) {
            throw new KardexExcepction("No fue posible registrar el articulo", e);
        }
    }

    @Override
    public void crearClasificacion(ClasificacionDTO clasificacion) {
        try {
            if (clasificacion == null) {
                throw new KardexExcepction("Los datos del clasificacion son obligatorios para crear el registro");
            }
            clasificacionDao.crearClasificacion(clasificacion);
        } catch (HibernateException e) {
            throw new KardexExcepction("No fue posible registrar la clasificacion", e);
        }
    }

    @Override
    public void crearsuario(UsuarioDTO usuario) {
        try {
            if (usuario == null) {
                throw new KardexExcepction("Los datos del usuario son obligatorios para crear el registro");
            }
            usuarioDao.crearsuario(usuario);
        } catch (HibernateException e) {
            throw new KardexExcepction("No fue posible registrar el usuario", e);
        }
    }

    @Override
    public void crearPedido(PedidoDTO pedido) {
        try {
            if (pedido == null) {
                throw new KardexExcepction("Los datos del pedido son obligatorios para crear el registro");
            }
            pedidoDao.crearPedido(pedido);
        } catch (HibernateException e) {
            throw new KardexExcepction("No fue posible registrar el pedido", e);
        }
    }

    @Override
    public void crearDetallesPedido(List<DetallePedidoDTO> detallesPedido) {
        try {
            if (detallesPedido == null || detallesPedido.isEmpty()) {
                log.info("Sin datos de pedidos para registrar.");
                return;
            }
            detallePedidoDao.crearDetallesPedido(detallesPedido);
        } catch (HibernateException e) {
            throw new KardexExcepction("No fue posible registrar los detalles del pedido", e);
        }
    }

    @Override
    public List<ClasificacionDTO> obtenerClasificaciones() {
        try {
            return clasificacionDao.obtenerClasificaciones();
        } catch (QueryException e) {
            throw new KardexExcepction("No fue posible obtener las clasificaciones", e);
        }
    }

    @Override
    public void crearPersona(PersonaDTO persona) {
        try {
            if (persona == null) {
                throw new KardexExcepction("Los datos de la persona son obligatorios para crear el registro");
            }
            personaDao.crearPersona(persona);
        } catch (HibernateException e) {
            throw new KardexExcepction("No fue posible registrar datos de la persona", e);
        }
    }

    @Override
    public List<TipoPagoDao> obtenerTiposPago() {
        try {
            return tipoPagoDao.obtenerTiposPago();
        } catch (QueryException e) {
            throw new KardexExcepction("No fue posible obtener las clasificaciones", e);
        }
    }

    @Override
    public List<ArticuloDTO> obtenerArticulosConExistencia() {
        try {
            return articuloDao.obtenerArticulosConExistencia();
        } catch (QueryException e) {
            throw new KardexExcepction("No fue posible obtener articulos con existencia.", e);
        }
    }

    @Override
    public void comprar(PedidoVO pedido) {
        validarDatosPedido(pedido);
        Integer personaPk = obtenerCodigoPersonaPorNombreUsuario(pedido.getUsername());
        PedidoDTO nuevoPedido = PedidoDTO.builder()
                .codigoPedido(generarCodigo())
                .personaPk(personaPk)
                .totalPedido(calcularTotalPedido(pedido))
                .tipoPagoPk(pedido.getTipoPagoPk())
                .build();
        agregarDatosAuditoria(Collections.singletonList(nuevoPedido));
        crearPedido(nuevoPedido);
        crearDetallesPedido(generarDetallePedido(pedido, nuevoPedido.getPedidoPk()));
        actualizarExistenciasArticulos(pedido);
    }

    @Override
    public List<DetallePedidoDTO> obtenerDetallesComprasPorUsuario(String userName) {
        try {
            if (userName == null || userName.trim().isEmpty()) {
                throw new KardexExcepction("El nombre de usuario es obligatorio para obtener los pedidos asociados");
            }
            return detallePedidoDao.obtenerDetallesComprasPorUsuario(userName);
        } catch (QueryException e) {
            throw new KardexExcepction("No fue posible obtener datos del pedido", e);
        }
    }

    @Override
    public List<PedidoDTO> obtenerPedidosPorUsuario(String userName) {
        List<DetallePedidoDTO> detallesPedido = obtenerDetallesComprasPorUsuario(userName);
        Map<Integer, List<DetallePedidoDTO>> pedidos = detallesPedido.stream()
                .collect(groupingBy(DetallePedidoDTO::getPedidoPk));
        List<PedidoDTO> pedidosUsuario = new ArrayList<>();
        pedidos.forEach((pedidoPk, detalePedido) -> {
            PedidoDTO pedido = detalePedido.get(0).getPedido();
            detalePedido.forEach(detalle -> detalle.setPedido(null));
            pedido.setDetallesPedio(detalePedido);
            pedidosUsuario.add(pedido);
        });
        return pedidosUsuario;
    }

    private void validarDatosPedido(PedidoVO pedido) {
        if (pedido == null) {
            throw new KardexExcepction("Los datos del pedido son obligatorios para realizar la compra");
        }
    }

    private String generarCodigo() {
        Random random = new Random();
        IntStream intStream = random.ints(10, 1, 7);
        StringBuilder codigo = new StringBuilder();
        intStream.forEach(codigo::append);
        return codigo.toString();
    }

    private Double calcularTotalPedido(PedidoVO pedido) {
        return pedido.getDetallePedido().stream()
                .mapToDouble(articulo -> articulo.getPrecio() * articulo.getCantidad())
                .sum();
    }

    private List<DetallePedidoDTO> generarDetallePedido(PedidoVO pedido, Integer codigoPedido) {
        List<DetallePedidoDTO> detalles = new ArrayList<>();
        pedido.getDetallePedido().forEach(detalle -> detalles.add(DetallePedidoDTO
                .builder()
                .pedidoPk(codigoPedido)
                .cantidad(detalle.getCantidad())
                .articuloPk(detalle.getArticuloPk())
                .build())
        );
        agregarDatosAuditoria(detalles);
        return detalles;
    }

    private Integer obtenerCodigoPersonaPorNombreUsuario(String userName) {
        try {
            if (userName == null || userName.trim().isEmpty()) {
                throw new KardexExcepction("El nombre de usuario es obligatorio");
            }
            return personaDao.obtenerCodigoPersonaPorNombreUsuario(userName);
        } catch (QueryException e) {
            throw new KardexExcepction("No fue posible obtener el codigo de persona", e);
        }
    }

    private void actualizarExistenciasArticulos(PedidoVO pedido) {
        List<ArticuloDTO> articulos = new ArrayList<>();
        pedido.getDetallePedido().forEach(detalle -> articulos.add(ArticuloDTO
                .builder()
                .articuloPk(detalle.getArticuloPk())
                .existencia(detalle.getExistencia() - detalle.getCantidad())
                .build()));
        articulos.forEach(this::actualizaExistencia);
    }

    private void agregarDatosAuditoria(List<? extends AuditoriaDTO> datos) {
        datos.forEach(dato -> {
            dato.setEstado(Boolean.TRUE);
            dato.setFechaCreacion(new Date());
        });
    }

    public void actualizaExistencia(ArticuloDTO articulo) {
        try {
            if (articulo == null) {
                throw new KardexExcepction("Los datos del articulo son obligatorios para realizar la actualizacion.");
            }
            articuloDao.actualizaExistencia(articulo);
        } catch (HibernateException e) {
            throw new KardexExcepction("No fue posible actualizar la existencia de los articulos", e);
        }
    }

}
