/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.dto;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import snu.entidades.musica.Tom;

/**
 *
 * @author Washington Luis
 */
@Entity
public class EntidadeTom implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated
    private Tom tom;

    public EntidadeTom() {
    }
       
    public EntidadeTom(Tom tom){
        this.tom = tom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tom getTom() {
        return tom;
    }

    public void setTom(Tom tom) {
        this.tom = tom;
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
        if (!(object instanceof EntidadeTom)) {
            return false;
        }
        EntidadeTom other = (EntidadeTom) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntidadeTom{" + "id=" + id + ", tom=" + tom + '}';
    }

}
