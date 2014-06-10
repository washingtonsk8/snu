/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.entidades.musica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import snu.entidades.missa.Missa;

/**
 * Entidade que define uma Música na visão do ministério
 *
 * @author Washington Luis
 */
@Entity
public class Musica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    private String nome;

    @OneToMany(mappedBy = "musica", cascade = CascadeType.ALL, orphanRemoval = true,
            targetEntity = LeituraAssociada.class, fetch = FetchType.LAZY)
    private List<LeituraAssociada> leiturasAssociadas;

    @Enumerated(EnumType.STRING)
    private Tom tom;

    @Enumerated(EnumType.STRING)
    private Afinacao afinacao;

    @OneToMany(mappedBy = "musica", cascade = CascadeType.ALL, orphanRemoval = true,
            targetEntity = EntidadeTipoMusica.class, fetch = FetchType.LAZY)
    private List<EntidadeTipoMusica> tipos;

    @OneToMany(mappedBy = "musica", cascade = CascadeType.ALL, orphanRemoval = true,
            targetEntity = AssociacaoIntegranteMusica.class, fetch = FetchType.LAZY)
    private List<AssociacaoIntegranteMusica> associacoes;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = DocumentoMusica.class)
    @JoinColumn(name = "documentomusica_id")
    private DocumentoMusica documentoMusica;

    @Column(name = "link_video")
    private String linkVideo;

    @ManyToMany
    @JoinTable(
            name = "missas_musicas",
            joinColumns = {
                @JoinColumn(name = "missa_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                @JoinColumn(name = "musica_id", referencedColumnName = "id")})
    private Set<Missa> missasPresente;
    
    @Column(nullable=false, columnDefinition="BIT(1) DEFAULT 0")
    private Boolean impressa;

    public Musica() {
        this.associacoes = new ArrayList<>();
        this.leiturasAssociadas = new ArrayList<>();
        this.tipos = new ArrayList<>();
        this.documentoMusica = new DocumentoMusica();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<LeituraAssociada> getLeiturasAssociadas() {
        return leiturasAssociadas;
    }

    public void setLeiturasAssociadas(List<LeituraAssociada> leiturasAssociadas) {
        this.leiturasAssociadas = leiturasAssociadas;
    }

    public Tom getTom() {
        return tom;
    }

    public void setTom(Tom tom) {
        this.tom = tom;
    }

    public Afinacao getAfinacao() {
        return afinacao;
    }

    public void setAfinacao(Afinacao afinacao) {
        this.afinacao = afinacao;
    }

    public String getLinkVideo() {
        return linkVideo;
    }

    public void setLinkVideo(String linkVideo) {
        this.linkVideo = linkVideo;
    }

    public Boolean isImpressa() {
        return impressa;
    }

    public void setImpressa(Boolean impressa) {
        this.impressa = impressa;
    }

    public List<TipoMusica> getTipos() {
        List<TipoMusica> retorno = new ArrayList<>();
        for (EntidadeTipoMusica entidadeTipoMusica : this.tipos) {
            retorno.add(entidadeTipoMusica.getValor());
        }
        return retorno;
    }

    public void setTipos(List<TipoMusica> tipos) {
        this.tipos = new ArrayList<>();
        for (TipoMusica tipoMusica : tipos) {
            EntidadeTipoMusica novaEntidade = new EntidadeTipoMusica();
            novaEntidade.setValor(tipoMusica);
            novaEntidade.setMusica(this);
            this.tipos.add(novaEntidade);
        }
    }

    public void adicionarTipo(TipoMusica tipo) {
        EntidadeTipoMusica novaEntidade = new EntidadeTipoMusica();
        novaEntidade.setValor(tipo);
        novaEntidade.setMusica(this);
        this.tipos.add(novaEntidade);
    }

    public void removerTipo(TipoMusica tipo) {
        for (int indiceRemocao = 0; indiceRemocao < this.tipos.size(); indiceRemocao++) {
            if (this.tipos.get(indiceRemocao).getValor().equals(tipo)) {
                //Remover o indice
                this.tipos.remove(indiceRemocao);

                //Quebrar a iteração
                indiceRemocao = this.tipos.size();
            }
        }
    }

    public List<AssociacaoIntegranteMusica> getAssociacoes() {
        return associacoes;
    }

    public void setAssociacoes(List<AssociacaoIntegranteMusica> associacoes) {
        this.associacoes = associacoes;
    }

    public DocumentoMusica getDocumentoMusica() {
        return documentoMusica;
    }

    public void setDocumentoMusica(DocumentoMusica documentoMusica) {
        this.documentoMusica = documentoMusica;
    }

    public Set<Missa> getMissasPresente() {
        return missasPresente;
    }

    public void setMissasPresente(Set<Missa> missasPresente) {
        this.missasPresente = missasPresente;
    }

    /**
     * Retorna o título de uma música no formato: Autor - Título
     *
     * @return
     */
    public String getTitulo() {
        String nomeAutor = this.autor == null ? "Sem Autor" : this.autor.getNome();
        return nomeAutor + " - " + this.nome;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Musica)) {
            return false;
        }
        Musica other = (Musica) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "Musica{" + "id=" + id + ", autor=" + autor.getNome() + ", titulo=" + nome + ", leiturasAssociadas=" + leiturasAssociadas + ", tom=" + tom + ", afinacao=" + afinacao + ", tipos=" + tipos + '}';
    }
}
