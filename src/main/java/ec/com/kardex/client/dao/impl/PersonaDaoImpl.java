package ec.com.kardex.client.dao.impl;

import ec.com.kardex.client.dao.CommonsDao;
import ec.com.kardex.client.dao.PersonaDao;
import ec.com.kardex.client.dto.PersonaDTO;
import org.springframework.stereotype.Repository;

@Repository
public class PersonaDaoImpl extends CommonsDao implements PersonaDao {

    @Override
    public void crearPersona(PersonaDTO persona) {
        em.persist(persona);
    }

}
