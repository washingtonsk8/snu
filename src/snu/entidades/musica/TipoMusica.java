/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.entidades.musica;

/**
 * Enumerável que define o tipo de música para o ministério
 *
 * @author Washington Luis
 */
public enum TipoMusica {

    /**
     * Música de entrada da missa
     */
    ENTRADA("Entrada"),
    /**
     * Música para o ato penitencial
     */
    ATO_PENITENCIAL("Ato Penitencial"),
    /**
     * Música de glória (louvor)
     */
    GLORIA("Glória"),
    /**
     * Música de aclamação ao evangelho
     */
    ACLAMACAO("Aclamação ao Evangelho"),
    /**
     * Música das ofertas
     */
    OFERTORIO("Ofertório"),
    /**
     * Música de paz
     */
    PAZ("Paz"),
    /**
     * Música de santo
     */
    SANTO("Santo"),
    /**
     * Música de comunhão
     */
    COMUNHAO("Comunhão"),
    /**
     * Música de ação de graças
     */
    ACAO_DE_GRACAS("Ação de Graças"),
    /**
     * Música de saída da missa
     */
    FINAL("Final"),
    /**
     * Música de momentos de reflexão
     */
    REFLEXAO("Reflexão"),
    /**
     * Música de louvor (Crisma)
     */
    LOUVOR("Louvor"),
    /**
     * Música para adoração ao santíssimo (Crisma)
     */
    ADORACAO("Adoração"),
    /**
     * Música tocada em uma palestra (Crisma)
     */
    PALESTRA("Palestra"),
    /**
     * Música do tipo resposta
     */
    RESPOSTA("Resposta"),
    /**
     * Música do tipo oração
     */
    ORACAO("Oração"),
    /**
     * Música do tipo especial
     */
    ESPECIAL("Especial"),
    /**
     * Música que não se enquadra em nenhuma opção acima
     */
    OUTRA("Outra");

    private final String valor;

    private TipoMusica(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return valor;
    }
}
