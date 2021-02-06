package ec.com.kardex.client.servicio.impl;

import com.querydsl.core.QueryException;
import ec.com.kardex.client.dao.ArticuloDao;
import ec.com.kardex.client.dto.ArticuloDTO;
import ec.com.kardex.client.exception.KardexExcepction;
import ec.com.kardex.client.servicio.KardexServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class KardexServicioImpl implements KardexServicio {

    @Autowired
    private ArticuloDao articuloDao;

    @Override
    public List<ArticuloDTO> obtenerArticulos() {
        try {
            return articuloDao.obtenerArticulos();
        } catch (QueryException e) {
            throw new KardexExcepction("No fue posble obtener articulos registrados", e);
        }
    }
}
