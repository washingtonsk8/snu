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
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Subquery;
import snu.bd.GerenciadorDeEntidades;
import snu.controladores.exceptions.NonexistentEntityException;
import snu.dto.ParametrosPesquisaMusica;
import snu.entidades.musica.Musica;
import snu.entidades.musica.AssociacaoIntegranteMusica;
import snu.entidades.musica.Autor;
import snu.entidades.musica.Autor_;
import snu.entidades.musica.EntidadeTipoMusica;
import snu.entidades.musica.EntidadeTipoMusica_;
import snu.entidades.musica.LeituraAssociada;
import snu.entidades.musica.LeituraAssociada_;
import snu.entidades.musica.TipoMusica;

/**
 * Classe controladora das atividades de persistência da entidade Música
 *
 * @author Washington Luis
 */
public class MusicaJpaController implements Serializable {

    /**
     * Fábrica Singleton do sistema
     */
    private final EntityManagerFactory emf = GerenciadorDeEntidades.getInstancia().getFabrica();
    private static MusicaJpaController instancia;

    /**
     * Construtor da classe Singleton
     */
    private MusicaJpaController() {
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Musica musica) {
        if (musica.getAssociacoes() == null) {
            musica.setAssociacoes(new ArrayList<AssociacaoIntegranteMusica>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            /*List<AssociacaoIntegranteMusica> attachedAssociacoes = new ArrayList<>();
             for (AssociacaoIntegranteMusica associacoesAssociacaoIntegranteMusicaToAttach : musica.getAssociacoes()) {
             associacoesAssociacaoIntegranteMusicaToAttach = em.getReference(associacoesAssociacaoIntegranteMusicaToAttach.getClass(), associacoesAssociacaoIntegranteMusicaToAttach.getId());
             attachedAssociacoes.add(associacoesAssociacaoIntegranteMusicaToAttach);
             }
             musica.setAssociacoes(attachedAssociacoes);*/
            em.persist(musica);
            /*for (AssociacaoIntegranteMusica associacoesAssociacaoIntegranteMusica : musica.getAssociacoes()) {
             Musica oldMusicaOfAssociacoesAssociacaoIntegranteMusica = associacoesAssociacaoIntegranteMusica.getMusica();
             associacoesAssociacaoIntegranteMusica.setMusica(musica);
             associacoesAssociacaoIntegranteMusica = em.merge(associacoesAssociacaoIntegranteMusica);
             if (oldMusicaOfAssociacoesAssociacaoIntegranteMusica != null) {
             oldMusicaOfAssociacoesAssociacaoIntegranteMusica.getAssociacoes().remove(associacoesAssociacaoIntegranteMusica);
             oldMusicaOfAssociacoesAssociacaoIntegranteMusica = em.merge(oldMusicaOfAssociacoesAssociacaoIntegranteMusica);
             }
             }*/
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Musica musica) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Musica persistentMusica = em.find(Musica.class, musica.getId());
            /*List<AssociacaoIntegranteMusica> associacoesOld = persistentMusica.getAssociacoes();
             List<AssociacaoIntegranteMusica> associacoesNew = musica.getAssociacoes();
             List<AssociacaoIntegranteMusica> attachedAssociacoesNew = new ArrayList<AssociacaoIntegranteMusica>();
             for (AssociacaoIntegranteMusica associacoesNewAssociacaoIntegranteMusicaToAttach : associacoesNew) {
             associacoesNewAssociacaoIntegranteMusicaToAttach = em.getReference(associacoesNewAssociacaoIntegranteMusicaToAttach.getClass(), associacoesNewAssociacaoIntegranteMusicaToAttach.getId());
             attachedAssociacoesNew.add(associacoesNewAssociacaoIntegranteMusicaToAttach);
             }
             associacoesNew = attachedAssociacoesNew;
             musica.setAssociacoes(associacoesNew);*/
            musica = em.merge(musica);
            /* for (AssociacaoIntegranteMusica associacoesOldAssociacaoIntegranteMusica : associacoesOld) {
             if (!associacoesNew.contains(associacoesOldAssociacaoIntegranteMusica)) {
             associacoesOldAssociacaoIntegranteMusica.setMusica(null);
             associacoesOldAssociacaoIntegranteMusica = em.merge(associacoesOldAssociacaoIntegranteMusica);
             }
             }
             for (AssociacaoIntegranteMusica associacoesNewAssociacaoIntegranteMusica : associacoesNew) {
             if (!associacoesOld.contains(associacoesNewAssociacaoIntegranteMusica)) {
             Musica oldMusicaOfAssociacoesNewAssociacaoIntegranteMusica = associacoesNewAssociacaoIntegranteMusica.getMusica();
             associacoesNewAssociacaoIntegranteMusica.setMusica(musica);
             associacoesNewAssociacaoIntegranteMusica = em.merge(associacoesNewAssociacaoIntegranteMusica);
             if (oldMusicaOfAssociacoesNewAssociacaoIntegranteMusica != null && !oldMusicaOfAssociacoesNewAssociacaoIntegranteMusica.equals(musica)) {
             oldMusicaOfAssociacoesNewAssociacaoIntegranteMusica.getAssociacoes().remove(associacoesNewAssociacaoIntegranteMusica);
             oldMusicaOfAssociacoesNewAssociacaoIntegranteMusica = em.merge(oldMusicaOfAssociacoesNewAssociacaoIntegranteMusica);
             }
             }
             }*/
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = musica.getId();
                if (findMusica(id) == null) {
                    throw new NonexistentEntityException("The musica with id " + id + " no longer exists.");
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
            Musica musica;
            try {
                musica = em.getReference(Musica.class, id);
                musica.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The musica with id " + id + " no longer exists.", enfe);
            }
            List<AssociacaoIntegranteMusica> associacoes = musica.getAssociacoes();
            for (AssociacaoIntegranteMusica associacoesAssociacaoIntegranteMusica : associacoes) {
                associacoesAssociacaoIntegranteMusica.setMusica(null);
                associacoesAssociacaoIntegranteMusica = em.merge(associacoesAssociacaoIntegranteMusica);
            }
            em.remove(musica);
            
            //Remove as indexações
            ObjetoListaInvertidaJpaController.getInstancia().destroyByMusicaId(id);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Musica> findMusicaEntities() {
        return findMusicaEntities(true, -1, -1);
    }

    public List<Musica> findMusicaEntities(int maxResults, int firstResult) {
        return findMusicaEntities(false, maxResults, firstResult);
    }

    private List<Musica> findMusicaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Musica.class));
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

    public Musica findMusica(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Musica.class, id);
        } finally {
            em.close();
        }
    }

    public int getMusicaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Musica> rt = cq.from(Musica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Musica> findMusicasByParametrosPesquisa(ParametrosPesquisaMusica parametrosPesquisa) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Musica> cq = cb.createQuery(Musica.class);
        Root<Musica> musica = cq.from(Musica.class);

        List<Predicate> predicados = new ArrayList<>();

        if (!parametrosPesquisa.getNomeAutor().isEmpty()) {
            Join<Musica, Autor> autor = musica.join("autor", JoinType.LEFT);
            predicados.add(cb.like(cb.lower(autor.get(Autor_.nome)), "%" + parametrosPesquisa.getNomeAutor().toLowerCase() + "%"));
        }
        if (!parametrosPesquisa.getTitulo().isEmpty()) {
            predicados.add(cb.like(cb.lower(musica.<String>get("titulo")), "%" + parametrosPesquisa.getTitulo().toLowerCase() + "%"));
        }
        if (!parametrosPesquisa.getDescricaoLeiturasAssociadas().isEmpty()) {
            Join<Musica, LeituraAssociada> leiturasAssociadas = musica.join("leiturasAssociadas", JoinType.LEFT);
            predicados.add(cb.like(cb.lower(leiturasAssociadas.get(LeituraAssociada_.descricao)), "%" + parametrosPesquisa.getDescricaoLeiturasAssociadas().toLowerCase() + "%"));
        }
        if (!parametrosPesquisa.getTipos().isEmpty()) {
            Join<Musica, EntidadeTipoMusica> tiposMusica = musica.join("tipos", JoinType.LEFT);
            for (TipoMusica tipoMusica : parametrosPesquisa.getTipos()) {
                Subquery<Musica> subquery = cq.subquery(Musica.class);
                Root<EntidadeTipoMusica> entidadesTipoMusica = subquery.from(EntidadeTipoMusica.class);
                subquery.select(entidadesTipoMusica.<Musica>get("musica")).
                        where(cb.equal(entidadesTipoMusica.get(EntidadeTipoMusica_.valor), tipoMusica));
                predicados.add(cb.in(tiposMusica.get("musica")).value(subquery));
            }
        }

        Predicate[] arrayPredicados = new Predicate[predicados.size()];
        cq.select(musica).distinct(true).where(predicados.toArray(arrayPredicados));

        return em.createQuery(cq).getResultList();
    }

    /**
     * Obtém a instância Singleton
     *
     * @return
     */
    public static MusicaJpaController getInstancia() {
        if (instancia == null) {
            instancia = new MusicaJpaController();
        }
        return instancia;
    }
}
