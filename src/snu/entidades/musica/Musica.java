/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.entidades.musica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Classe que define uma música na visão do ministério
 *
 * @author Washington Luis
 */
@Entity
public class Musica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String autor;
    private String titulo;
    
    @ElementCollection(targetClass = String.class)
    private List<String> leiturasAssociadas;
    
    @Enumerated(EnumType.STRING)
    private Tom tom;
    
    @Enumerated(EnumType.STRING)
    private Afinacao afinacao;
    
    @ElementCollection(targetClass = TipoMusica.class)
    @Enumerated(EnumType.STRING)
    private List<TipoMusica> tipos;
    
    @OneToMany(mappedBy = "musica", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AssociacaoIntegranteMusica> associacoes; 
    
    private String conteudo;   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getLeiturasAssociadas() {
        return leiturasAssociadas;
    }

    public void setLeiturasAssociadas(List<String> leiturasAssociadas) {
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

    public List<TipoMusica> getTipos() {
        return tipos;
    }

    public void setTipos(List<TipoMusica> tipos) {
        this.tipos = tipos;
    }

    public List<AssociacaoIntegranteMusica> getAssociacoes() {
        return associacoes;
    }

    public void setAssociacoes(List<AssociacaoIntegranteMusica> associacoes) {
        this.associacoes = associacoes;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
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
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Musica{" + "id=" + id + ", autor=" + autor + ", titulo=" + titulo + ", leiturasAssociadas=" + leiturasAssociadas + ", tom=" + tom + ", afinacao=" + afinacao + ", tipos=" + tipos + '}';
    }
}
