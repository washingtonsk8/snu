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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import snu.controladores.exceptions.NonexistentEntityException;
import snu.entidades.missa.Missa;

/**
 *
 * @author Washington Luis
 */
public class MissaJpaController implements Serializable {

    public MissaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Missa missa) {
        if (missa.getMusicasUtilizadas() == null) {
            missa.setMusicasUtilizadas(new HashSet<Musica>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Musica> attachedMusicasUtilizadas = new HashSet<Musica>();
            for (Musica musicasUtilizadasMusicaToAttach : missa.getMusicasUtilizadas()) {
                musicasUtilizadasMusicaToAttach = em.getReference(musicasUtilizadasMusicaToAttach.getClass(), musicasUtilizadasMusicaToAttach.getId());
                attachedMusicasUtilizadas.add(musicasUtilizadasMusicaToAttach);
            }
            missa.setMusicasUtilizadas(attachedMusicasUtilizadas);
            em.persist(missa);
            for (Musica musicasUtilizadasMusica : missa.getMusicasUtilizadas()) {
                musicasUtilizadasMusica.getMissasPresente().add(missa);
                musicasUtilizadasMusica = em.merge(musicasUtilizadasMusica);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Missa missa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Missa persistentMissa = em.find(Missa.class, missa.getId());
            Set<Musica> musicasUtilizadasOld = persistentMissa.getMusicasUtilizadas();
            Set<Musica> musicasUtilizadasNew = missa.getMusicasUtilizadas();
            Set<Musica> attachedMusicasUtilizadasNew = new HashSet<Musica>();
            for (Musica musicasUtilizadasNewMusicaToAttach : musicasUtilizadasNew) {
                musicasUtilizadasNewMusicaToAttach = em.getReference(musicasUtilizadasNewMusicaToAttach.getClass(), musicasUtilizadasNewMusicaToAttach.getId());
                attachedMusicasUtilizadasNew.add(musicasUtilizadasNewMusicaToAttach);
            }
            musicasUtilizadasNew = attachedMusicasUtilizadasNew;
            missa.setMusicasUtilizadas(musicasUtilizadasNew);
            missa = em.merge(missa);
            for (Musica musicasUtilizadasOldMusica : musicasUtilizadasOld) {
                if (!musicasUtilizadasNew.contains(musicasUtilizadasOldMusica)) {
                    musicasUtilizadasOldMusica.getMissasPresente().remove(missa);
                    musicasUtilizadasOldMusica = em.merge(musicasUtilizadasOldMusica);
                }
            }
            for (Musica musicasUtilizadasNewMusica : musicasUtilizadasNew) {
                if (!musicasUtilizadasOld.contains(musicasUtilizadasNewMusica)) {
                    musicasUtilizadasNewMusica.getMissasPresente().add(missa);
                    musicasUtilizadasNewMusica = em.merge(musicasUtilizadasNewMusica);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = missa.getId();
                if (findMissa(id) == null) {
                    throw new NonexistentEntityException("The missa with id " + id + " no longer exists.");
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
            Missa missa;
            try {
                missa = em.getReference(Missa.class, id);
                missa.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The missa with id " + id + " no longer exists.", enfe);
            }
            Set<Musica> musicasUtilizadas = missa.getMusicasUtilizadas();
            for (Musica musicasUtilizadasMusica : musicasUtilizadas) {
                musicasUtilizadasMusica.getMissasPresente().remove(missa);
                musicasUtilizadasMusica = em.merge(musicasUtilizadasMusica);
            }
            em.remove(missa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Missa> findMissaEntities() {
        return findMissaEntities(true, -1, -1);
    }

    public List<Missa> findMissaEntities(int maxResults, int firstResult) {
        return findMissaEntities(false, maxResults, firstResult);
    }

    private List<Missa> findMissaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Missa.class));
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

    public Missa findMissa(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Missa.class, id);
        } finally {
            em.close();
        }
    }

    public int getMissaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Missa> rt = cq.from(Missa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
