package ec.com.kardex.client.dao.impl;

import com.querydsl.jpa.impl.JPAQuery;
import ec.com.kardex.client.dao.CommonsDao;
import ec.com.kardex.client.dao.PersonaDao;
import ec.com.kardex.client.dto.PersonaDTO;
import org.springframework.stereotype.Repository;

import static ec.com.kardex.client.dto.QPersonaDTO.personaDTO;
import static ec.com.kardex.client.dto.QUsuarioDTO.usuarioDTO;

@Repository
public class PersonaDaoImpl extends CommonsDao implements PersonaDao {

    @Override
    public void crearPersona(PersonaDTO persona) {
        em.persist(persona);
    }

    @Override
    public Integer obtenerCodigoPersonaPorNombreUsuario(String userName) {
        JPAQuery<Integer> query = new JPAQuery<>(em);
        return query.from(personaDTO)
                .innerJoin(personaDTO.usuario, usuarioDTO)
                .where(usuarioDTO.nombreUsuario.eq(userName))
                .select(personaDTO.personaPk)
                .fetchOne();
    }

}
