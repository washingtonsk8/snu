/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.controladores;

import java.io.IOException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Subquery;
import snu.bd.GerenciadorDeEntidades;
import snu.exceptions.NonexistentEntityException;
import snu.controladores.indexador.ProcessadorDeConsultas;
import snu.dto.ParametrosPesquisaMusica;
import snu.entidades.musica.Musica;
import snu.entidades.musica.AssociacaoIntegranteMusica;
import snu.entidades.musica.Autor;
import snu.entidades.musica.DocumentoMusica;
import snu.entidades.musica.EntidadeTipoMusica;
import snu.entidades.musica.LeituraAssociada;
import snu.entidades.musica.TipoMusica;
import snu.util.StringUtil;

/**
 * Classe que controla todas as conexões com o banco da entidade Musica
 *
 * @author Washington Luis
 */
public class MusicaJpaController implements Serializable {

    /**
     * Fábrica Singleton do sistema
     */
    private transient final EntityManagerFactory emf = GerenciadorDeEntidades.getInstancia().getFabrica();
    private volatile static MusicaJpaController instancia;

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
            em.persist(musica);
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
            musica = em.merge(musica);
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

    /**
     * Destrói a Música de id especificado e todas as suas palavras indexadas no
     * banco
     *
     * @param id
     * @throws NonexistentEntityException
     */
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
                em.merge(associacoesAssociacaoIntegranteMusica);
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

    /**
     * Encontra uma música a partir do id de documento
     *
     * @param idDocumentoMusica
     * @return
     */
    public Musica findMusicaByIdDocumento(Long idDocumentoMusica) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Musica> cq = cb.createQuery(Musica.class);
        Root<Musica> musica = cq.from(Musica.class);
        Join<Musica, DocumentoMusica> joinDocumentoMusica = musica.join("documentoMusica", JoinType.LEFT);

        cq.select(musica).distinct(true).where(cb.equal(joinDocumentoMusica.get("id"), idDocumentoMusica));
        return em.createQuery(cq).getSingleResult();
    }

    /**
     * Encontra músicas a partir de parâmetros de pesquisa especificados.
     *
     * @param parametrosPesquisa
     * @return
     * @throws java.io.IOException
     */
    public List<Musica> findMusicasByParametrosPesquisa(ParametrosPesquisaMusica parametrosPesquisa) throws IOException {
        if (StringUtil.hasAlgo(parametrosPesquisa.getTrecho())) {
            ProcessadorDeConsultas processadorDeConsultas = new ProcessadorDeConsultas();
            processadorDeConsultas.processar(parametrosPesquisa.getTrecho());
            return processadorDeConsultas.getListaOrdenada();
        }
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Musica> cq = cb.createQuery(Musica.class);
        Root<Musica> musica = cq.from(Musica.class);

        List<Predicate> predicados = new ArrayList<>();

        if (StringUtil.hasAlgo(parametrosPesquisa.getNomeAutor())) {
            Join<Musica, Autor> autor = musica.join("autor", JoinType.LEFT);
            predicados.add(cb.like(cb.lower(autor.<String>get("nome")), "%" + parametrosPesquisa.getNomeAutor().toLowerCase(new Locale("pt", "BR")) + "%"));
        }
        if (StringUtil.hasAlgo(parametrosPesquisa.getNomeMusica())) {
            predicados.add(cb.like(cb.lower(musica.<String>get("nome")), "%" + parametrosPesquisa.getNomeMusica().toLowerCase(new Locale("pt", "BR")) + "%"));
        }
        if (StringUtil.hasAlgo(parametrosPesquisa.getDescricaoLeiturasAssociadas())) {
            Join<Musica, LeituraAssociada> leiturasAssociadas = musica.join("leiturasAssociadas", JoinType.LEFT);
            predicados.add(cb.like(cb.lower(leiturasAssociadas.<String>get("descricao")), "%" + parametrosPesquisa.getDescricaoLeiturasAssociadas().toLowerCase() + "%"));
        }
        if (parametrosPesquisa.getTipos() != null && !parametrosPesquisa.getTipos().isEmpty()) {
            Join<Musica, EntidadeTipoMusica> tiposMusica = musica.join("tipos", JoinType.LEFT);
            for (TipoMusica tipoMusica : parametrosPesquisa.getTipos()) {
                Subquery<Musica> subquery = cq.subquery(Musica.class);
                Root<EntidadeTipoMusica> entidadesTipoMusica = subquery.from(EntidadeTipoMusica.class);
                subquery.select(entidadesTipoMusica.<Musica>get("musica")).
                        where(cb.equal(entidadesTipoMusica.get("valor"), tipoMusica));
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
