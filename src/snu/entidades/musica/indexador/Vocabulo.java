/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.entidades.musica.indexador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Washington Luis
 */
@Entity
@Table(name = "documentomusica_vocabulario")
public class Vocabulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToMany(mappedBy = "vocabulo", cascade = CascadeType.ALL, orphanRemoval = true,
            targetEntity = ObjetoListaInvertida.class, fetch = FetchType.EAGER)
    private List<ObjetoListaInvertida> listaInvertida;

    public Vocabulo() {
        this.listaInvertida = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<ObjetoListaInvertida> getListaInvertida() {
        return listaInvertida;
    }

    public void setListaInvertida(List<ObjetoListaInvertida> listaInvertida) {
        this.listaInvertida = listaInvertida;
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
        if (!(object instanceof Vocabulo)) {
            return false;
        }
        Vocabulo other = (Vocabulo) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

}