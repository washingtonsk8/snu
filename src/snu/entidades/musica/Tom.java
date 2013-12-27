/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.entidades.musica;

/**
 * Enumerável que define o tom da música
 *
 * @author Washington Luis
 */
public enum Tom {

    C("D"), D("D"), E("E"), F("F"), G("G"), A("A"), B("B");

    private final String valor;

    private Tom(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return valor;
    }
}
