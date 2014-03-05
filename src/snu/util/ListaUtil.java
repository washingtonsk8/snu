/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Utilitário para realizar operações com lista
 *
 * @author Washington Luis
 */
public class ListaUtil {

    /**
     * Retorna uma string com os componentes da lista separados por ';'
     *
     * @param lista
     * @return
     */
    public static String getListaSeparadaPorPontoVirgula(List<?> lista) {
        if (lista == null) {
            return null;
        }
        if (lista.isEmpty()) {
            return new String();
        }

        String resposta = "";

        for (Object elemento : lista) {
            resposta += elemento + "; ";
        }

        return resposta.substring(0, resposta.length() - 2);
    }

    /**
     * Retorna uma string com os componentes da lista separados por ','
     *
     * @param lista
     * @return
     */
    public static String getListaSeparadaPorVirgula(List<?> lista) {
        if (lista == null) {
            return null;
        }
        if (lista.isEmpty()) {
            return new String();
        }

        String resposta = "";

        for (Object elemento : lista) {
            resposta += elemento + ", ";
        }

        return resposta.substring(0, resposta.length() - 2);
    }

    /**
     * Converte a lista de entrada para uma lista de strings (toString())
     *
     * @param lista
     * @return
     */
    public static List<String> converterListaParaListaDeStrings(List<?> lista) {
        List<String> resposta = new ArrayList<>();
        for (Object elemento : lista) {
            resposta.add(elemento.toString());
        }
        return resposta;
    }
}
