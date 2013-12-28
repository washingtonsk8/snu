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

    C("C"), C_SUSTENIDO("C#"), D("D"), D_SUSTENIDO("D#"), E("E"),
    F("F"), F_SUSTENIDO("F#"), G("G"), G_SUSTENIDO("G#"), A("A"),
    A_SUSTENIDO("A#"), B("B");

    private final String valor;

    private Tom(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return valor;
    }
}
