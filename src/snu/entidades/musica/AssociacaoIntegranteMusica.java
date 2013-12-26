/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snu.entidades.musica;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import org.hibernate.annotations.ManyToAny;
import snu.entidades.integrante.Integrante;

/**
 *
 * @author Washington Luis
 */
@Entity
public class AssociacaoIntegranteMusica {
    @EmbeddedId
    public AssociacaoIntegranteMusicaId id;
    
    @MapsId("integrante")
    @ManyToOne
    private Integrante integrante;
    
    @MapsId("tom")
    @ManyToOne
    private Tom tom;
    
    @MapsId("musica")
    @ManyToOne
    private Musica musica;
    
}
