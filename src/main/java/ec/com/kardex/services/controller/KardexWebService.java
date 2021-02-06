package ec.com.kardex.services.controller;

import ec.com.kardex.client.dto.ArticuloDTO;
import ec.com.kardex.client.servicio.KardexServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/obtenerArticulos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
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

}
