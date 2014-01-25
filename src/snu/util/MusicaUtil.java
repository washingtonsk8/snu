/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.util;

/**
 * Utilitário para auxílio no gerenciamento da música
 *
 * @author Washington Luis
 */
public class MusicaUtil {

    public static String detectarAcordes(String conteudoEntrada) {
        return conteudoEntrada.replaceAll(RegexUtil.getRegexAcordes(), "@$0");
    }

    public static String removerAcordes(String conteudoEntrada) {
        return detectarAcordes(conteudoEntrada).replaceAll("@[\\S]*", "");
    }
}
