/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.controladores;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import snu.controladores.exceptions.NonexistentEntityException;
import snu.entidades.Integrante;

/**
 * Classe que controla o gerenciamento da persistência dos integrantes
 *
 * @author Washington Luis
 */
public class IntegranteJpaController implements Serializable {

    public IntegranteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Integrante integrante) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(integrante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Integrante integrante) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            integrante = em.merge(integrante);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = integrante.getId();
                if (findIntegrante(id) == null) {
                    throw new NonexistentEntityException("The integrante with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Integrante integrante;
            try {
                integrante = em.getReference(Integrante.class, id);
                integrante.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The integrante with id " + id + " no longer exists.", enfe);
            }
            em.remove(integrante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Integrante> findIntegranteEntities() {
        return findIntegranteEntities(true, -1, -1);
    }

    public List<Integrante> findIntegranteEntities(int maxResults, int firstResult) {
        return findIntegranteEntities(false, maxResults, firstResult);
    }

    private List<Integrante> findIntegranteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Integrante.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Integrante findIntegrante(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Integrante.class, id);
        } finally {
            em.close();
        }
    }

    public int getIntegranteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Integrante> rt = cq.from(Integrante.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
