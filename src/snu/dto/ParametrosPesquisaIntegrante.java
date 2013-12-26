/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.dto;

import snu.entidades.integrante.FuncaoIntegrante;

/**
 * Classe utilizada para realizar pesquisas no banco
 *
 * @author Washington Luis
 */
public class ParametrosPesquisaIntegrante {
    private String nome;
    private FuncaoIntegrante funcaoPrimaria;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public FuncaoIntegrante getFuncaoPrimaria() {
        return funcaoPrimaria;
    }

    public void setFuncaoPrimaria(FuncaoIntegrante funcaoPrimaria) {
        this.funcaoPrimaria = funcaoPrimaria;
    }    
}
