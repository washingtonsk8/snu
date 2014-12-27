/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.dto;

import java.util.List;
import snu.entidades.musica.TipoMusica;

/**
 * Define os parâmetros para realizar uma pesquisa por Música
 *
 * @author Washington Luis
 */
public class ParametrosPesquisaMusica {

    private String nomeAutor;
    private String nomeMusica;
    private String descricaoTag;
    private String trecho;
    private Boolean impressa;
    private List<TipoMusica> tipos;

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public String getNomeMusica() {
        return nomeMusica;
    }

    public void setNomeMusica(String nomeMusica) {
        this.nomeMusica = nomeMusica;
    }

    public String getDescricaoTag() {
        return descricaoTag;
    }

    public void setTag(String descricaoTag) {
        this.descricaoTag = descricaoTag;
    }

    public String getTrecho() {
        return trecho;
    }

    public void setTrecho(String trecho) {
        this.trecho = trecho;
    }

    public Boolean isImpressa() {
        return impressa;
    }

    public void setImpressa(Boolean impressa) {
        this.impressa = impressa;
    }

    public List<TipoMusica> getTipos() {
        return tipos;
    }

    public void setTipos(List<TipoMusica> tipos) {
        this.tipos = tipos;
    }
}
