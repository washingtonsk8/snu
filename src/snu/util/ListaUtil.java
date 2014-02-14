/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe para auxiliar operações em lista
 *
 * @author Washington Luis
 */
public class ListaUtil {

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
    
    public static List<String> converterListaParaListaDeStrings(List<?> lista){
        List<String> resposta = new ArrayList<>();
        for (Object elemento : lista) {
            resposta.add(elemento.toString());
        }
        return resposta;
    }
}
