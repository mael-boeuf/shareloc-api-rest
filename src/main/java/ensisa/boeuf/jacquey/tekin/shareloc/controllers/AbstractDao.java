package ensisa.boeuf.jacquey.tekin.shareloc.controllers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;

public class AbstractDao<T> {

    private static final String UNIT_NAME = "ShareLoc_JPA";
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(UNIT_NAME);
    private static EntityManager entityManager = null;

    private Class<T> clazz;

    public AbstractDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    protected EntityManager getEntityManager() {
        if (entityManager == null) {
            entityManager = emf.createEntityManager();
        }
        return entityManager;
    }

    public T create(T entite) {
        final EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(entite);
        em.flush();
        em.getTransaction().commit();
        return entite;
    }

    public void edit(T entite) {
        final EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(entite);
        em.getTransaction().commit();
    }

    public void remove(T entite) {
        final EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.remove(getEntityManager().merge(entite));
        em.getTransaction().commit();
    }

    public T find(Object id) {
        return getEntityManager().find(clazz, id);
    }

    public List<T> findAll() {
        final EntityManager em = getEntityManager();
        final CriteriaQuery<T> criteriaQuery = em.getCriteriaBuilder().createQuery(clazz);
        criteriaQuery.select(criteriaQuery.from(clazz));
        final List<T> results = em.createQuery(criteriaQuery).getResultList();
        if (results == null) {
            return Collections.emptyList();
        }
        return results;
    }

    public long count() {
        final EntityManager em = getEntityManager();
        final CriteriaQuery<Long> cq = em.getCriteriaBuilder().createQuery(Long.class);
        final javax.persistence.criteria.Root<Long> rt = cq.from(Long.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        javax.persistence.Query q = em.createQuery(cq);
        return (Long) q.getSingleResult();
    }

    public T findByTable(String table, String value) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<T> query = criteriaBuilder.createQuery(clazz);
        Root<T> root = query.from(clazz);
        query.where(criteriaBuilder.equal(root.get(table),value));

        List<T> results = getEntityManager().createQuery(query).getResultList();
        if(results.size() > 0) return results.get(0);
        return null;
    }

}
