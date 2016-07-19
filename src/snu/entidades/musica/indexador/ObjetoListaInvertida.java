/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.entidades.musica.indexador;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entidade que mantém dados para lista invertida
 *
 * @author Washington Luis
 */
@Entity
@Table(name = "documentomusica_listainvertida")
public class ObjetoListaInvertida implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vocabulo_id")
    private Vocabulo vocabulo;

    @Column(name = "musica_id")
    private Long idMusica;

    @Column(name = "documentomusica_id")
    private Long idDocumentoMusica;

    @Column(name = "frequencia_token")
    private Integer frequenciaToken;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vocabulo getVocabulo() {
        return vocabulo;
    }

    public void setVocabulo(Vocabulo vocabulo) {
        this.vocabulo = vocabulo;
    }

    public Long getIdMusica() {
        return idMusica;
    }

    public void setIdMusica(Long idMusica) {
        this.idMusica = idMusica;
    }

    public Long getIdDocumentoMusica() {
        return idDocumentoMusica;
    }

    public void setIdDocumentoMusica(Long idDocumentoMusica) {
        this.idDocumentoMusica = idDocumentoMusica;
    }

    public Integer getFrequenciaToken() {
        return frequenciaToken;
    }

    public void setFrequenciaToken(Integer frequenciaToken) {
        this.frequenciaToken = frequenciaToken;
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
        if (!(object instanceof ObjetoListaInvertida)) {
            return false;
        }
        ObjetoListaInvertida other = (ObjetoListaInvertida) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "ObjetoListaInvertida{" + "id=" + id + ", vocabulo=" + vocabulo + ", idMusica=" + idMusica + ", idDocumentoMusica=" + idDocumentoMusica + ", frequenciaToken=" + frequenciaToken + '}';
    }
}
