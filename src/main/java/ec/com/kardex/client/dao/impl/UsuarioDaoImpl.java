package ec.com.kardex.client.dao.impl;

import ec.com.kardex.client.dao.CommonsDao;
import ec.com.kardex.client.dao.UsuarioDao;
import ec.com.kardex.client.dto.UsuarioDTO;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDaoImpl extends CommonsDao implements UsuarioDao {

    @Override
    public void crearsuario(UsuarioDTO usuario) {
        em.persist(usuario);
    }

}
