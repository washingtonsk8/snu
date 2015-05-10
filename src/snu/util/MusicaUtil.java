/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import snu.controladores.ConfiguracoesSistemaJpaController;
import snu.entidades.configuracoes.ConfiguracoesSistema;
import snu.entidades.musica.Musica;
import snu.entidades.musica.Tom;

/**
 * Utilitário para realizar operações com Música
 *
 * @author Washington Luis
 */
public class MusicaUtil {

    private static final Integer LINHAS_POR_PAGINA = 31;

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
        StringBuilder conteudoSaida = new StringBuilder();
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
                conteudoSaida.append(conteudoEntrada.charAt(contador++));
            } else {
                conteudoSaida.append(caractereAtual);
                caractereAtual = conteudoEntrada.charAt(++contador);
                switch (caractereAtual) {
                    case 'A':
                        if (contador != tamanhoVerificador && conteudoEntrada.charAt(contador + 1) == '#') {
                            conteudoSaida.append(tons[(Tom.A_SUSTENIDO.ordinal() + diferenca) % tons.length]);
                            contador++;
                        } else {
                            conteudoSaida.append(tons[(Tom.A.ordinal() + diferenca) % tons.length]);
                        }
                        break;
                    case 'B':
                        conteudoSaida.append(tons[(Tom.B.ordinal() + diferenca) % tons.length]);
                        break;
                    case 'C':
                        if (contador != tamanhoVerificador && conteudoEntrada.charAt(contador + 1) == '#') {
                            conteudoSaida.append(tons[(Tom.C_SUSTENIDO.ordinal() + diferenca) % tons.length]);
                            contador++;
                        } else {
                            conteudoSaida.append(tons[(Tom.C.ordinal() + diferenca) % tons.length]);
                        }
                        break;
                    case 'D':
                        if (contador != tamanhoVerificador && conteudoEntrada.charAt(contador + 1) == '#') {
                            conteudoSaida.append(tons[(Tom.D_SUSTENIDO.ordinal() + diferenca) % tons.length]);
                            contador++;
                        } else {
                            conteudoSaida.append(tons[(Tom.D.ordinal() + diferenca) % tons.length]);
                        }
                        break;
                    case 'E':
                        conteudoSaida.append(tons[(Tom.E.ordinal() + diferenca) % tons.length]);
                        break;
                    case 'F':
                        if (contador != tamanhoVerificador && conteudoEntrada.charAt(contador + 1) == '#') {
                            conteudoSaida.append(tons[(Tom.F_SUSTENIDO.ordinal() + diferenca) % tons.length]);
                            contador++;
                        } else {
                            conteudoSaida.append(tons[(Tom.F.ordinal() + diferenca) % tons.length]);
                        }
                        break;
                    case 'G':
                        if (contador != tamanhoVerificador && conteudoEntrada.charAt(contador + 1) == '#') {
                            conteudoSaida.append(tons[(Tom.G_SUSTENIDO.ordinal() + diferenca) % tons.length]);
                            contador++;
                        } else {
                            conteudoSaida.append(tons[(Tom.G.ordinal() + diferenca) % tons.length]);
                        }
                        break;
                    default:
                        conteudoSaida.append(conteudoEntrada.charAt(contador));
                        break;
                }
                contador++;
            }
        }
        //Remove as irregularidades e retorna
        return trocarPelasPreferencias(removerIrregularidades(conteudoSaida.toString()));
    }

    private static String removerIrregularidades(String entrada) {
        return entrada.replaceAll("#b", "")
                .replaceAll("Cb", "B")
                .replaceAll("Fb", "E");
    }

    private static String trocarPelasPreferencias(String entrada) {
        ConfiguracoesSistema configuracoesSistema
                = ConfiguracoesSistemaJpaController.getInstancia().findConfiguracoesSistema();
        if (configuracoesSistema.isPreferenciaCsusDbem()) {
            entrada = entrada.replaceAll("C#", "Db");
        }
        if (configuracoesSistema.isPreferenciaDsusEbem()) {
            entrada = entrada.replaceAll("D#", "Eb");
        }
        if (configuracoesSistema.isPreferenciaFsusGbem()) {
            entrada = entrada.replaceAll("F#", "Gb");
        }
        if (configuracoesSistema.isPreferenciaGsusAbem()) {
            entrada = entrada.replaceAll("G#", "Ab");
        }
        if (configuracoesSistema.isPreferenciaAsusBbem()) {
            entrada = entrada.replaceAll("A#", "Bb");
        }
        return entrada;
    }

    public static int contarPaginas(String conteudoMusica) {
        if (conteudoMusica == null) {
            return 1;
        }

        Pattern pattern = Pattern.compile("\n");

        Matcher matcher = pattern.matcher(conteudoMusica);
        //.find() checks for all occurrances
        int contagem = 0;
        //you will see that only 2 are the matchine string
        while (matcher.find()) {
            contagem++;
        }
        return contagem / LINHAS_POR_PAGINA + 1;
    }

    public static boolean isConteudoIdentico(Musica musica, String introducao, String conteudo) {
        String introducaoMusica = musica.getDocumentoMusica().getIntroducao();
        String conteudoMusica = musica.getDocumentoMusica().getConteudo();
        boolean introducaoIdentica = introducaoMusica != null ? introducaoMusica.equals(introducao) : introducao == null;
        boolean conteudoIdentico = conteudoMusica != null ? conteudoMusica.equals(conteudo) : conteudo == null;
        return introducaoIdentica && conteudoIdentico;
    }

    /**
     * Detecta as anomalias de uma música. Anomalias são detecções de acordes
     * feitas de modo errado fazendo com que palavras normais sejam consideradas
     * acordes.
     *
     * @param conteudoMusica
     * @return
     */
    public static boolean hasAnomalias(String conteudoMusica) {
        String[] linhas = conteudoMusica.split("\n");
        Pattern p = Pattern.compile("[a-zA-Z]");

        for (String linha : linhas) {
            if (!linha.contains("@")) {
                continue;
            }
            String[] palavras = linha.split(" ");
            int i = 0;
            boolean linhaDeAcordes = true;

            /*
             Se a linha conter pelo menos uma palavra que não seja um acorde,
             provavelmente essa linha não é uma linha composta de acordes.
             */
            while (i < palavras.length && linhaDeAcordes) {
                Matcher m = p.matcher(palavras[i]);
                if(StringUtil.hasAlgo(palavras[i])){
                    if (m.find() && !palavras[i].contains("@")) {
                        return true;
                    }                    
                }
                i++;
            }
        }
        return false;
    }
}
