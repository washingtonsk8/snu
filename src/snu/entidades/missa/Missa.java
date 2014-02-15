/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.entidades.missa;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import snu.entidades.musica.Musica;

/**
 * Entidade responsável por manter dados de uma missa
 *
 * @author Washington Luis
 */
@Entity
public class Missa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    @Column(name = "data_acontecimento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataAcontecimento;

    @ManyToMany
    @JoinTable(
            name = "missas_musicas",
            joinColumns = {
                @JoinColumn(name = "missa_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                @JoinColumn(name = "musica_id", referencedColumnName = "id")})
    private Set<Musica> musicasUtilizadas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataAcontecimento() {
        return dataAcontecimento;
    }

    public void setDataAcontecimento(Date dataAcontecimento) {
        this.dataAcontecimento = dataAcontecimento;
    }

    public Set<Musica> getMusicasUtilizadas() {
        return musicasUtilizadas;
    }

    public void setMusicasUtilizadas(Set<Musica> musicasUtilizadas) {
        this.musicasUtilizadas = musicasUtilizadas;
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
        if (!(object instanceof Missa)) {
            return false;
        }
        Missa other = (Missa) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "Missa{" + "id=" + id + ", nome=" + nome + ", dataAcontecimento=" + dataAcontecimento + ", musicasUtilizadas=" + musicasUtilizadas + '}';
    }

}
