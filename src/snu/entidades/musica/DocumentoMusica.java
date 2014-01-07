/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.entidades.musica;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Washington Luis
 */
@Entity
public class DocumentoMusica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "LONGTEXT")
    private String conteudo;

    @Column(name = "quantidade_tokens")
    private Integer quantidadeTokens;

    @Column(name = "frequencia_maxima_token")
    private Integer frequenciaMaximaToken;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Integer getQuantidadeTokens() {
        return quantidadeTokens;
    }

    public void setQuantidadeTokens(Integer quantidadeTokens) {
        this.quantidadeTokens = quantidadeTokens;
    }

    public Integer getFrequenciaMaximaToken() {
        return frequenciaMaximaToken;
    }

    public void setFrequenciaMaximaToken(Integer frequenciaMaximaToken) {
        this.frequenciaMaximaToken = frequenciaMaximaToken;
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
        if (!(object instanceof DocumentoMusica)) {
            return false;
        }
        DocumentoMusica other = (DocumentoMusica) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "snu.entidades.musica.DocumentoMusica[ id=" + id + " ]";
    }

}
