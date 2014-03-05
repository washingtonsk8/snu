/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.util;

import snu.entidades.musica.Tom;

/**
 * Utilitário para realizar operações com Música
 *
 * @author Washington Luis
 */
public class MusicaUtil {

    /**
     * Detecta os acordes de um dado conteúdo de entrada e os anota com '@'
     *
     * @param conteudoEntrada
     * @return
     */
    public static String detectarAcordes(String conteudoEntrada) {
        if (conteudoEntrada == null) {
            return null;
        }
        return conteudoEntrada.replaceAll(RegexUtil.getRegexAcordes(), "@$0");
    }

    /**
     * Detecta e remove todos os acordes de um dado conteúdo de entrada
     *
     * @param conteudoEntrada
     * @return
     */
    public static String removerAcordes(String conteudoEntrada) {
        if (conteudoEntrada == null) {
            return null;
        }
        return detectarAcordes(conteudoEntrada).replaceAll("@[\\S]*", "");
    }

    /**
     * Deixa o conteúdo preparado para impressão, removendo dele os '@'
     *
     * @param conteudoEntrada
     * @return
     */
    public static String limparParaImpressao(String conteudoEntrada) {
        if (conteudoEntrada == null) {
            return null;
        }
        return conteudoEntrada.replaceAll("@", "");
    }

    /**
     * Converte os acordes de um dado conteúdo de entrada para o
     * tomParaConversao a partir do tomOriginal
     *
     * @param conteudoEntrada
     * @param tomOriginal
     * @param tomParaConversao
     * @return
     */
    public static String converterTom(String conteudoEntrada, Tom tomOriginal, Tom tomParaConversao) {        
        int contador = 0;
        Tom[] tons = Tom.values();
        String conteudoSaida = "";
        /**
         * Quantidade de tons de diferença contando de meio em meio tom Soma-se
         * o tamanho do vetor para que se possa fazer a operação de MOD
         */
        int diferenca = tomParaConversao.ordinal() - tomOriginal.ordinal() + tons.length;

        //Necessário para verificar se existe um próximo caractere a ser lido
        int tamanhoVerificador = conteudoEntrada.length() - 1;
        char caractereAtual;

        /**
         * Varrendo arquivo de música para conversão Método cagado de fazer :/
         */
        while (contador < conteudoEntrada.length()) {
            caractereAtual = conteudoEntrada.charAt(contador);
            if (caractereAtual != '@' && caractereAtual != '/') {
                conteudoSaida += conteudoEntrada.charAt(contador++);
            } else {
                conteudoSaida += caractereAtual;
                caractereAtual = conteudoEntrada.charAt(++contador);
                switch (caractereAtual) {
                    case 'A':
                        if (contador != tamanhoVerificador && conteudoEntrada.charAt(contador + 1) == '#') {
                            conteudoSaida += tons[(Tom.A_SUSTENIDO.ordinal() + diferenca) % tons.length].toString();
                            contador++;
                        } else {
                            conteudoSaida += tons[(Tom.A.ordinal() + diferenca) % tons.length].toString();
                        }
                        break;
                    case 'B':
                        conteudoSaida += tons[(Tom.B.ordinal() + diferenca) % tons.length].toString();
                        break;
                    case 'C':
                        if (contador != tamanhoVerificador && conteudoEntrada.charAt(contador + 1) == '#') {
                            conteudoSaida += tons[(Tom.C_SUSTENIDO.ordinal() + diferenca) % tons.length].toString();
                            contador++;
                        } else {
                            conteudoSaida += tons[(Tom.C.ordinal() + diferenca) % tons.length].toString();
                        }
                        break;
                    case 'D':
                        if (contador != tamanhoVerificador && conteudoEntrada.charAt(contador + 1) == '#') {
                            conteudoSaida += tons[(Tom.D_SUSTENIDO.ordinal() + diferenca) % tons.length].toString();
                            contador++;
                        } else {
                            conteudoSaida += tons[(Tom.D.ordinal() + diferenca) % tons.length].toString();
                        }
                        break;
                    case 'E':
                        conteudoSaida += tons[(Tom.E.ordinal() + diferenca) % tons.length].toString();
                        break;
                    case 'F':
                        if (contador != tamanhoVerificador && conteudoEntrada.charAt(contador + 1) == '#') {
                            conteudoSaida += tons[(Tom.F_SUSTENIDO.ordinal() + diferenca) % tons.length].toString();
                            contador++;
                        } else {
                            conteudoSaida += tons[(Tom.F.ordinal() + diferenca) % tons.length].toString();
                        }
                        break;
                    case 'G':
                        if (contador != tamanhoVerificador && conteudoEntrada.charAt(contador + 1) == '#') {
                            conteudoSaida += tons[(Tom.G_SUSTENIDO.ordinal() + diferenca) % tons.length].toString();
                            contador++;
                        } else {
                            conteudoSaida += tons[(Tom.G.ordinal() + diferenca) % tons.length].toString();
                        }
                        break;
                    default:
                        conteudoSaida += conteudoEntrada.charAt(contador);
                        break;
                }
                contador++;
            }
        }
        return conteudoSaida.replaceAll("#b", "");//Remove as irregularidades e retorna
    }
}
