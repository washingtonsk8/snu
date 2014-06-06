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

    C("C", ""), C_SUSTENIDO("C#", "Db"), D("D", ""), D_SUSTENIDO("D#", "Eb"), E("E", ""),
    F("F", ""), F_SUSTENIDO("F#", "Gb"), G("G", ""), G_SUSTENIDO("G#", "Ab"), A("A", ""),
    A_SUSTENIDO("A#", "Bb"), B("B", "");

    private final String valor;
    private final String valorSecundario;

    private Tom(String valor, String valorSecundario) {
        this.valor = valor;
        this.valorSecundario = valorSecundario;
    }

    public String getValor() {
        return valor;
    }

    public String getValorSecundario() {
        return valorSecundario;
    }
        
    @Override
    public String toString() {
        return valor;
    }
}
