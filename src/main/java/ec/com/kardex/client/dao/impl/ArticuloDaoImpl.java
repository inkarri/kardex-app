package ec.com.kardex.client.dao.impl;

import com.querydsl.jpa.impl.JPAQuery;
import ec.com.kardex.client.dao.ArticuloDao;
import ec.com.kardex.client.dao.CommonsDao;
import ec.com.kardex.client.dto.ArticuloDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

import static ec.com.kardex.client.dto.QArticuloDTO.articuloDTO;

@Repository
public class ArticuloDaoImpl extends CommonsDao implements ArticuloDao {

    @Override
    public ArticuloDTO insertarArticulo(ArticuloDTO articulo) {
        em.persist(articulo);
        return articulo;
    }

    @Override
    public List<ArticuloDTO> obtenerArticulos() {
        JPAQuery<ArticuloDTO> query = new JPAQuery<>(em);
        return query.from(articuloDTO)
                .where(articuloDTO.estado.eq(Boolean.TRUE))
                .orderBy(articuloDTO.fechaCreacion.desc())
                .fetch();
    }

    @Override
    public void crearArticulos(List<ArticuloDTO> articulos) {
        em.clear();
        articulos.forEach(em::persist);
        em.flush();
        em.clear();
    }

    @Override
    public List<ArticuloDTO> obtenerArticulosConExistencia() {
        JPAQuery<ArticuloDTO> query = new JPAQuery<>(em);
        return query.from(articuloDTO)
                .where(articuloDTO.estado.eq(Boolean.TRUE),
                        articuloDTO.existencia.gt(0))
                .orderBy(articuloDTO.codigoBarras.desc())
                .fetch();
    }

}
