/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.controladores;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import snu.entidades.musica.indexador.ObjetoListaInvertida;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import snu.bd.GerenciadorDeEntidades;
import snu.exceptions.NonexistentEntityException;
import snu.entidades.musica.indexador.Vocabulo;

/**
 * Classe que controla todas as conexões com o banco da entidade Vocabulo
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
        if (vocabulo.getListaInvertida() == null) {
            vocabulo.setListaInvertida(new ArrayList<ObjetoListaInvertida>());
        }
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
            List<ObjetoListaInvertida> listaInvertida = vocabulo.getListaInvertida();
            for (ObjetoListaInvertida listaInvertidaObjetoListaInvertida : listaInvertida) {
                listaInvertidaObjetoListaInvertida.setVocabulo(null);
                listaInvertidaObjetoListaInvertida = em.merge(listaInvertidaObjetoListaInvertida);
            }
            em.remove(vocabulo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vocabulo> findVocabuloEntities() {
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

    /**
     * Encontra vocábulos dado um token de pesquisa
     *
     * @param token
     * @return
     */
    public Vocabulo findVocabuloByToken(String token) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Vocabulo> cq = cb.createQuery(Vocabulo.class);
        Root<Vocabulo> vocabulo = cq.from(Vocabulo.class);
        cq.select(vocabulo).distinct(true).where(cb.equal(vocabulo.get("token"), token));
        List<Vocabulo> vocabulos = em.createQuery(cq).getResultList();
        return vocabulos.isEmpty() ? null : vocabulos.get(0);
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
