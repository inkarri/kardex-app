package ec.com.kardex.client.servicio;

import ec.com.kardex.client.dto.ArticuloDTO;

import java.util.List;

public interface KardexServicio {

    List<ArticuloDTO> obtenerArticulos();

}
