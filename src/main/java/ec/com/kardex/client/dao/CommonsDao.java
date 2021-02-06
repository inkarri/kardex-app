package ec.com.kardex.client.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CommonsDao {

    @PersistenceContext
    protected EntityManager em;

}
