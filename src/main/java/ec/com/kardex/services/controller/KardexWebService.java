package ec.com.kardex.services.controller;

import ec.com.kardex.client.dao.TipoPagoDao;
import ec.com.kardex.client.dto.ArticuloDTO;
import ec.com.kardex.client.dto.ClasificacionDTO;
import ec.com.kardex.client.dto.DetallePedidoDTO;
import ec.com.kardex.client.dto.PedidoDTO;
import ec.com.kardex.client.dto.PersonaDTO;
import ec.com.kardex.client.dto.UsuarioDTO;
import ec.com.kardex.client.exception.KardexExcepction;
import ec.com.kardex.client.servicio.KardexServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@Scope(value = "request")
@RequestMapping(value = "/kardex")
public class KardexWebService {

    private static final String REPONSE_TYPE = "restyp";

    @Autowired
    private KardexServicio kardexServicio;

    @GetMapping(value = "/obtenerArticulos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ArticuloDTO>> obtenerArticulos() {
        HttpHeaders responseHeaders = new HttpHeaders();
        List<ArticuloDTO> articulos = Collections.emptyList();
        try {
            articulos = kardexServicio.obtenerArticulos();
            responseHeaders.set(REPONSE_TYPE, "SUCCESS");
        } catch (RuntimeException e) {
            responseHeaders.set(REPONSE_TYPE, "ERROR");
            responseHeaders.set("mes", e.getMessage());
        }
        return ResponseEntity.ok().headers(responseHeaders).body(articulos);
    }

    @PostMapping(value = "/crearArticulos")
    public ResponseEntity<Void> crearArticulos(@RequestBody List<ArticuloDTO> articulos) {
        HttpHeaders responseHeaders = new HttpHeaders();
        try {
            kardexServicio.crearArticulos(articulos);
            responseHeaders.set(REPONSE_TYPE, "SUCCESS");
        } catch (KardexExcepction e) {
            responseHeaders.set(REPONSE_TYPE, "ERROR");
            responseHeaders.set("mes", e.getMessage());
        }
        return ResponseEntity.ok().headers(responseHeaders).build();
    }

    @PostMapping(value = "/insertarArticulo")
    public ResponseEntity<Void> insertarArticulo(@RequestBody ArticuloDTO articulo) {
        HttpHeaders responseHeaders = new HttpHeaders();
        try {
            kardexServicio.insertarArticulo(articulo);
            responseHeaders.set(REPONSE_TYPE, "SUCCESS");
        } catch (KardexExcepction e) {
            responseHeaders.set(REPONSE_TYPE, "ERROR");
            responseHeaders.set("mes", e.getMessage());
        }
        return ResponseEntity.ok().headers(responseHeaders).build();
    }

    @PostMapping(value = "/crearClasificacion")
    public ResponseEntity<Void> crearClasificacion(@RequestBody ClasificacionDTO clasificacion) {
        HttpHeaders responseHeaders = new HttpHeaders();
        try {
            kardexServicio.crearClasificacion(clasificacion);
            responseHeaders.set(REPONSE_TYPE, "SUCCESS");
        } catch (KardexExcepction e) {
            responseHeaders.set(REPONSE_TYPE, "ERROR");
            responseHeaders.set("mes", e.getMessage());
        }
        return ResponseEntity.ok().headers(responseHeaders).build();
    }

    @PostMapping(value = "/crearsuario")
    public ResponseEntity<Void> crearsuario(@RequestBody UsuarioDTO usuario) {
        HttpHeaders responseHeaders = new HttpHeaders();
        try {
            kardexServicio.crearsuario(usuario);
            responseHeaders.set(REPONSE_TYPE, "SUCCESS");
        } catch (KardexExcepction e) {
            responseHeaders.set(REPONSE_TYPE, "ERROR");
            responseHeaders.set("mes", e.getMessage());
        }
        return ResponseEntity.ok().headers(responseHeaders).build();
    }

    @PostMapping(value = "/crearPedido")
    public ResponseEntity<Void> crearPedido(@RequestBody PedidoDTO pedido) {
        HttpHeaders responseHeaders = new HttpHeaders();
        try {
            kardexServicio.crearPedido(pedido);
            responseHeaders.set(REPONSE_TYPE, "SUCCESS");
        } catch (KardexExcepction e) {
            responseHeaders.set(REPONSE_TYPE, "ERROR");
            responseHeaders.set("mes", e.getMessage());
        }
        return ResponseEntity.ok().headers(responseHeaders).build();
    }

    @PostMapping(value = "/crearDetallesPedido")
    public ResponseEntity<Void> crearDetallesPedido(@RequestBody List<DetallePedidoDTO> detallesPedido) {
        HttpHeaders responseHeaders = new HttpHeaders();
        try {
            kardexServicio.crearDetallesPedido(detallesPedido);
            responseHeaders.set(REPONSE_TYPE, "SUCCESS");
        } catch (KardexExcepction e) {
            responseHeaders.set(REPONSE_TYPE, "ERROR");
            responseHeaders.set("mes", e.getMessage());
        }
        return ResponseEntity.ok().headers(responseHeaders).build();
    }

    @GetMapping(value = "/obtenerClasificaciones", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClasificacionDTO>> obtenerClasificaciones() {
        HttpHeaders responseHeaders = new HttpHeaders();
        List<ClasificacionDTO> clasificaciones = Collections.emptyList();
        try {
            clasificaciones = kardexServicio.obtenerClasificaciones();
            responseHeaders.set(REPONSE_TYPE, "SUCCESS");
        } catch (RuntimeException e) {
            responseHeaders.set(REPONSE_TYPE, "ERROR");
            responseHeaders.set("mes", e.getMessage());
        }
        return ResponseEntity.ok().headers(responseHeaders).body(clasificaciones);
    }

    @PostMapping(value = "/crearPersona")
    public ResponseEntity<Void> crearPersona(@RequestBody PersonaDTO persona) {
        HttpHeaders responseHeaders = new HttpHeaders();
        try {
            kardexServicio.crearPersona(persona);
            responseHeaders.set(REPONSE_TYPE, "SUCCESS");
        } catch (KardexExcepction e) {
            responseHeaders.set(REPONSE_TYPE, "ERROR");
            responseHeaders.set("mes", e.getMessage());
        }
        return ResponseEntity.ok().headers(responseHeaders).build();
    }


    @GetMapping(value = "/obtenerTiposPago", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TipoPagoDao>> obtenerTiposPago() {
        HttpHeaders responseHeaders = new HttpHeaders();
        List<TipoPagoDao> tipos = Collections.emptyList();
        try {
            tipos = kardexServicio.obtenerTiposPago();
            responseHeaders.set(REPONSE_TYPE, "SUCCESS");
        } catch (RuntimeException e) {
            responseHeaders.set(REPONSE_TYPE, "ERROR");
            responseHeaders.set("mes", e.getMessage());
        }
        return ResponseEntity.ok().headers(responseHeaders).body(tipos);
    }

    @GetMapping(value = "/obtenerArticulosConExistencia", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ArticuloDTO>> obtenerArticulosConExistencia() {
        HttpHeaders responseHeaders = new HttpHeaders();
        List<ArticuloDTO> articulos = Collections.emptyList();
        try {
            articulos = kardexServicio.obtenerArticulosConExistencia();
            responseHeaders.set(REPONSE_TYPE, "SUCCESS");
        } catch (RuntimeException e) {
            responseHeaders.set(REPONSE_TYPE, "ERROR");
            responseHeaders.set("mes", e.getMessage());
        }
        return ResponseEntity.ok().headers(responseHeaders).body(articulos);
    }

}
