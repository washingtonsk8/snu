/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.entidades.integrante;

/**
 * Enumerável que define a função do integrante
 *
 * @author Washington Luis
 */
public enum FuncaoIntegrante {

    CANTOR("Cantor(a)"), BAIXISTA("Baixista"), GUITARRISTA_BASE("Guitarrista Base"),
    GUITARRISTA_SOLO("Guitarrista Solo"), VIOLONISTA("Violonista"),
    TECLADISTA("Tecladista"), VIOLINISTA("Violinista");

    private final String valor;

    private FuncaoIntegrante(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return valor;
    }
}
