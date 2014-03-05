/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.util;

import java.text.Normalizer;

/**
 * CUtilitário para realizar operações com strings
 *
 * @author Washington Luis
 */
public class StringUtil {

    public static final String VAZIA = "";

    /**
     * Verifica se a string passada está vazia. (strings nulas são aceitas)
     *
     * @param string
     * @return
     */
    public static boolean isVazia(String string) {
        if (string == null) {
            return true;
        }
        return string.equals(VAZIA);
    }

    /**
     * Verifica se a string passada contém algo
     *
     * @param string
     * @return
     */
    public static boolean hasAlgo(String string) {
        return !isVazia(string);
    }

    /**
     * Remove os acentos da string passada
     *
     * @param entrada
     * @return
     */
    public static String removerAcentos(String entrada) {
        return Normalizer.normalize(entrada, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    /**
     * Limpa a string passada de forma a conter somente letras
     *
     * @param entrada
     * @param manterDelimitadores
     * @return
     */
    public static String somenteLetras(String entrada, boolean manterDelimitadores) {
        return manterDelimitadores ? entrada.replaceAll("(?!\\s)\\P{L}", " ")
                : entrada.replaceAll("\\P{L}", " ");
    }
}
