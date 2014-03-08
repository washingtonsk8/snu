/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.entidades.integrante;

/**
 * Enumerável que define o sexo do integrante
 *
 * @author Washington Luis
 */
public enum Sexo {

    FEMININO("Feminino"), MASCULINO("Masculino");

    private final String valor;

    private Sexo(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return valor;
    }
}
