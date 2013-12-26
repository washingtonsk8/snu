/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.entidades.musica;

/**
 * Enumerável que define a afinação da música
 *
 * @author Washington Luis
 */
public enum Afinacao {

    EADGBE("E A D G B E"), EbAbDbGbBbEb("Eb Ab Db Gb Bb Eb"), DGCFAD("D G C F A D"),
    DbGbBEAbDb("Db Gb B E Ab Db"), CFBbEbGC("C F Bb Eb G C");

    private final String valor;

    private Afinacao(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Afinacao{" + "valor=" + valor + '}';
    }
}
