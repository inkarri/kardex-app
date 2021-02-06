package ec.com.kardex.client.dao.impl;

import com.querydsl.jpa.impl.JPAQuery;
import ec.com.kardex.client.dao.ClasificacionDao;
import ec.com.kardex.client.dao.CommonsDao;
import ec.com.kardex.client.dto.ClasificacionDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

import static ec.com.kardex.client.dto.QClasificacionDTO.clasificacionDTO;

@Repository
public class ClasificacionDaoImpl extends CommonsDao implements ClasificacionDao {

    @Override
    public void crearClasificacion(ClasificacionDTO clasificacion) {
        em.persist(clasificacion);
    }

    @Override
    public List<ClasificacionDTO> obtenerClasificaciones() {
        JPAQuery<ClasificacionDTO> query = new JPAQuery<>(em);
        return query.from(clasificacionDTO)
                .where(clasificacionDTO.estado.eq(Boolean.TRUE))
                .fetch();
    }

}
