/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.entidades.musica;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import snu.dto.EntidadeTom;
import snu.entidades.integrante.Integrante;

/**
 * Classe que define um ID para associação entre integrante e música
 *
 * @author Washington Luis
 */
@Embeddable
public class AssociacaoIntegranteMusicaId implements Serializable {

    @ManyToOne
    private Integrante integrante;

    @ManyToOne(cascade = CascadeType.ALL)
    private EntidadeTom tom;

    @ManyToOne
    private Musica musica;

    public Integrante getIntegrante() {
        return integrante;
    }

    public void setIntegrante(Integrante integrante) {
        this.integrante = integrante;
    }

    public EntidadeTom getTom() {
        return tom;
    }

    public void setTom(EntidadeTom tom) {
        this.tom = tom;
    }
    
    public Musica getMusica() {
        return musica;
    }

    public void setMusica(Musica musica) {
        this.musica = musica;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.integrante);
        hash = 83 * hash + Objects.hashCode(this.tom);
        hash = 83 * hash + Objects.hashCode(this.musica);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AssociacaoIntegranteMusicaId other = (AssociacaoIntegranteMusicaId) obj;
        if (!Objects.equals(this.integrante, other.integrante)) {
            return false;
        }
        if (this.tom != other.tom) {
            return false;
        }
        if (!Objects.equals(this.musica, other.musica)) {
            return false;
        }
        return true;
    }

}
