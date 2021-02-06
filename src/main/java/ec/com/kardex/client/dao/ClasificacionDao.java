package ec.com.kardex.client.dao;

import ec.com.kardex.client.dto.ClasificacionDTO;

import java.util.List;

public interface ClasificacionDao {

    void crearClasificacion(ClasificacionDTO clasificacion);

    List<ClasificacionDTO> obtenerClasificaciones();

}
