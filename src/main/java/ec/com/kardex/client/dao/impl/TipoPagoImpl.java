package ec.com.kardex.client.dao.impl;

import com.querydsl.jpa.impl.JPAQuery;
import ec.com.kardex.client.dao.CommonsDao;
import ec.com.kardex.client.dao.TipoPagoDao;
import org.springframework.stereotype.Repository;

import java.util.List;

import static ec.com.kardex.client.dto.QTipoPagoDTO.tipoPagoDTO;

@Repository
public class TipoPagoImpl extends CommonsDao implements TipoPagoDao {

    @Override
    public List<TipoPagoDao> obtenerTiposPago() {
        JPAQuery<TipoPagoDao> query = new JPAQuery<>(em);
        return query.from(tipoPagoDTO)
                .where(tipoPagoDTO.estado.eq(Boolean.TRUE))
                .fetch();
    }

}
