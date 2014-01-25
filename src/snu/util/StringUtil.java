/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.util;

import java.text.Normalizer;

/**
 * Classe utilitária para realizar operações em Strings
 *
 * @author Washington Luis
 */
public class StringUtil {

    public static final String VAZIA = "";

    public static boolean isVazia(String string) {
        return string.equals(VAZIA);
    }

    public static boolean hasAlgo(String string) {
        return !isVazia(string);
    }

    public static String removerAcentos(String entrada) {
        return Normalizer.normalize(entrada, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
    
    public static String somenteLetras(String entrada, boolean manterDelimitadores){
        return manterDelimitadores? entrada.replaceAll("(?!\\s)\\P{L}", " ") 
                : entrada.replaceAll("\\P{L}", " ");
    }
}
