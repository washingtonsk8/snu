/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.dto;

import snu.entidades.musica.Autor;

/**
 * Possui o autor e a quantidade músicas de sua autoria
 *
 * @author Washington Luis
 */
public class QuantidadeAutoriaDTO {

    private Autor autor;
    private Integer quantidadeMusicasDeAutoria;

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Integer getQuantidadeMusicasDeAutoria() {
        return quantidadeMusicasDeAutoria;
    }

    public void setQuantidadeMusicasDeAutoria(Integer quantidadeMusicasDeAutoria) {
        this.quantidadeMusicasDeAutoria = quantidadeMusicasDeAutoria;
    }
}
