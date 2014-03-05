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
import snu.entidades.musica.Musica;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import snu.bd.GerenciadorDeEntidades;
import snu.exceptions.NonexistentEntityException;
import snu.entidades.musica.Autor;

/**
 * Classe que controla todas as conexões com o banco da entidade Autor
 *
 * @author Washington Luis
 */
public class AutorJpaController implements Serializable {

    /**
     * Fábrica Singleton do sistema
     */
    private final EntityManagerFactory emf = GerenciadorDeEntidades.getInstancia().getFabrica();
    private static AutorJpaController instancia;

    /**
     * Construtor da classe Singleton
     */
    public AutorJpaController() {
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Autor autor) {
        if (autor.getMusicasDeAutoria() == null) {
            autor.setMusicasDeAutoria(new ArrayList<Musica>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Musica> attachedMusicasDeAutoria = new ArrayList<>();
            for (Musica musicasDeAutoriaMusicaToAttach : autor.getMusicasDeAutoria()) {
                musicasDeAutoriaMusicaToAttach = em.getReference(musicasDeAutoriaMusicaToAttach.getClass(), musicasDeAutoriaMusicaToAttach.getId());
                attachedMusicasDeAutoria.add(musicasDeAutoriaMusicaToAttach);
            }
            autor.setMusicasDeAutoria(attachedMusicasDeAutoria);
            em.persist(autor);
            for (Musica musicasDeAutoriaMusica : autor.getMusicasDeAutoria()) {
                Autor oldAutorOfMusicasDeAutoriaMusica = musicasDeAutoriaMusica.getAutor();
                musicasDeAutoriaMusica.setAutor(autor);
                musicasDeAutoriaMusica = em.merge(musicasDeAutoriaMusica);
                if (oldAutorOfMusicasDeAutoriaMusica != null) {
                    oldAutorOfMusicasDeAutoriaMusica.getMusicasDeAutoria().remove(musicasDeAutoriaMusica);
                    em.merge(oldAutorOfMusicasDeAutoriaMusica);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Autor autor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            autor = em.merge(autor);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = autor.getId();
                if (findAutor(id) == null) {
                    throw new NonexistentEntityException("The autor with id " + id + " no longer exists.");
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
            Autor autor;
            try {
                autor = em.getReference(Autor.class, id);
                autor.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The autor with id " + id + " no longer exists.", enfe);
            }
            List<Musica> musicasDeAutoria = autor.getMusicasDeAutoria();
            for (Musica musicasDeAutoriaMusica : musicasDeAutoria) {
                musicasDeAutoriaMusica.setAutor(null);
                musicasDeAutoriaMusica = em.merge(musicasDeAutoriaMusica);
            }
            em.remove(autor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Autor> findAutorEntities() {
        return findAutorEntities(true, -1, -1);
    }

    public List<Autor> findAutorEntities(int maxResults, int firstResult) {
        return findAutorEntities(false, maxResults, firstResult);
    }

    private List<Autor> findAutorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Autor.class));
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

    public Autor findAutor(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Autor.class, id);
        } finally {
            em.close();
        }
    }

    public int getAutorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Autor> rt = cq.from(Autor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public int getMusicasAutoriaCount(Long id) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<Autor> musica = cq.from(Musica.class);
            Join<Musica, Autor> autor = musica.join("autor", JoinType.LEFT);
            cq.select(cb.count(musica)).distinct(true).where(cb.equal(autor.get("id"), id));
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
    public static AutorJpaController getInstancia() {
        if (instancia == null) {
            instancia = new AutorJpaController();
        }
        return instancia;
    }
}
