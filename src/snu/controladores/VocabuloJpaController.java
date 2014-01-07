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
import snu.bd.GerenciadorDeEntidades;
import snu.bd.exceptions.NonexistentEntityException;
import snu.entidades.musica.indexador.Vocabulo;

/**
 *
 * @author Washington Luis
 */
public class VocabuloJpaController implements Serializable {

    /**
     * Fábrica Singleton do sistema
     */
    private final EntityManagerFactory emf = GerenciadorDeEntidades.getInstancia().getFabrica();
    private static VocabuloJpaController instancia;

    /**
     * Construtor da classe Singleton
     */
    private VocabuloJpaController() {
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vocabulo vocabulo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(vocabulo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vocabulo vocabulo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            vocabulo = em.merge(vocabulo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = vocabulo.getId();
                if (findVocabulo(id) == null) {
                    throw new NonexistentEntityException("The vocabulo with id " + id + " no longer exists.");
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
            Vocabulo vocabulo;
            try {
                vocabulo = em.getReference(Vocabulo.class, id);
                vocabulo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vocabulo with id " + id + " no longer exists.", enfe);
            }
            em.remove(vocabulo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vocabulo> findVocabularioEntities() {
        return findVocabuloEntities(true, -1, -1);
    }

    public List<Vocabulo> findVocabuloEntities(int maxResults, int firstResult) {
        return findVocabuloEntities(false, maxResults, firstResult);
    }

    private List<Vocabulo> findVocabuloEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vocabulo.class));
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

    public Vocabulo findVocabulo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vocabulo.class, id);
        } finally {
            em.close();
        }
    }

    public Vocabulo findVocabuloByToken(String token) {
        Vocabulo resultado;
        Query query;
        String sql;
        EntityManager em = getEntityManager();

        sql = "SELECT v FROM Vocabulo v WHERE token = :token";

        em.getTransaction().begin();
        query = em.createQuery(sql);
        query.setParameter("token", token);

        List<Vocabulo> listaResultante = query.getResultList();
        resultado = listaResultante.isEmpty()? null : listaResultante.get(0);
        em.getTransaction().commit();
        
        return resultado;
    }

    public int getVocabuloCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vocabulo> rt = cq.from(Vocabulo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    /**
     * Obtém a instância Singleton
     *
     * @return
     */
    public static VocabuloJpaController getInstancia() {
        if (instancia == null) {
            instancia = new VocabuloJpaController();
        }
        return instancia;
    }
}
