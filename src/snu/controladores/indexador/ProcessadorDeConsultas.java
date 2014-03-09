/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.controladores.indexador;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import snu.controladores.MusicaJpaController;
import snu.controladores.VocabuloJpaController;
import snu.dto.Pair;
import snu.entidades.musica.Musica;
import snu.entidades.musica.indexador.ObjetoListaInvertida;
import snu.entidades.musica.indexador.Vocabulo;
import snu.util.ProcessadorDeConsultasUtil;

/**
 * Classe que possui as funções para realizar o processamento de consultas
 *
 * @author Washington Luis
 */
public class ProcessadorDeConsultas {

    private List<Musica> listaOrdenada;

    public ProcessadorDeConsultas() {
        this.listaOrdenada = new ArrayList<>();
    }

    /**
     * Realiza todo o processamento da consulta. Utiliza o Modelo Vetorial para
     * busca por trecho.
     *
     * @param consulta
     * @throws java.io.IOException
     */
    public void processar(String consulta) throws IOException {
        IndexadorController indexadorController = IndexadorController.getInstancia();

        List<String> tokens = tokenizeString(indexadorController.getBrazilianAnalyzer(),
                indexadorController.preProcessar(consulta));

        //Hashing é utilizado para não repetir ids de documentos
        List<Pair<Long, Double>> similaridadesDocumentos = new ArrayList<>();

        //Carrega o controlador        
        VocabuloJpaController vocabuloController = VocabuloJpaController.getInstancia();

        //Verifica a quantidade de músicas presentes no banco
        Integer quantidadeDocumentos = MusicaJpaController.getInstancia().getMusicaCount();

        /*
         Para cada token encontrado, procura-se o vocábulo no banco
         WARNING: Tamanho da lista tokens pode ser diferente da quantidades de 
         tokens realmente utilizados.
         */
        HashMap<Long, List<Pair<String, Double>>> matrizPesos = new HashMap<>();

        //Colocar na matriz de pesos também a consulta (Id do documento da consulta/query 0)
        HashMap<String, Double> pesosConsulta = new HashMap<>();
        for (String token : tokens) {
            Vocabulo vocabulo = vocabuloController.findVocabuloByToken(token);
            if (vocabulo != null) {
                for (ObjetoListaInvertida objetoListInvertida : vocabulo.getListaInvertida()) {
                    Long idDocumentoMusica = objetoListInvertida.getIdDocumentoMusica();
                    if (matrizPesos.containsKey(idDocumentoMusica)) {
                        matrizPesos.get(idDocumentoMusica).add(new Pair<>(token, ProcessadorDeConsultasUtil
                                .calcularPeso(objetoListInvertida, quantidadeDocumentos)));
                    } else {
                        List<Pair<String, Double>> listaInvertidaPesos = new ArrayList<>();
                        listaInvertidaPesos.add(new Pair<>(token, ProcessadorDeConsultasUtil
                                .calcularPeso(objetoListInvertida, quantidadeDocumentos)));
                        matrizPesos.put(idDocumentoMusica, listaInvertidaPesos);
                    }
                }
                //Adiciona token e valor na matriz de pesos
                pesosConsulta.put(token, ProcessadorDeConsultasUtil.calcularPeso(vocabulo, tokens, quantidadeDocumentos));
            }
        }

        //Definindo valor fixo no cálculo para evitar recálculo
        double sqrt_wiq, somatorioWiq = 0.;
        for (Map.Entry<String, Double> entradaWiq : pesosConsulta.entrySet()) {
            somatorioWiq += Math.pow(entradaWiq.getValue().doubleValue(), 2);
        }
        sqrt_wiq = Math.sqrt(somatorioWiq);

        //Cálculos das similaridades MODELO VETORIAL
        for (Map.Entry<Long, List<Pair<String, Double>>> entradaPesos : matrizPesos.entrySet()) {
            double numerador = 0., denominador, somatorioWij = 0., similaridade;
            for (Pair<String, Double> entradaWij : entradaPesos.getValue()) {
                numerador += entradaWij.getSecond() * pesosConsulta.get(entradaWij.getFirst());

                somatorioWij += Math.pow(entradaWij.getSecond(), 2);
            }

            denominador = Math.sqrt(somatorioWij) * sqrt_wiq;

            //Calcula a similaridade
            similaridade = numerador / denominador;
            similaridadesDocumentos.add(new Pair<>(entradaPesos.getKey(), similaridade));
        }

        //Ordena a lista por maior similaridade
        Collections.sort(similaridadesDocumentos);

        this.listaOrdenada = new ArrayList<>();
        MusicaJpaController musicaController = MusicaJpaController.getInstancia();
        for (Pair<Long, Double> par : similaridadesDocumentos) {
            Musica musicaOrdenada = musicaController.findMusicaByIdDocumento(par.getFirst());
            this.listaOrdenada.add(musicaOrdenada);
        }
    }

    /**
     * Realiza a tokenização de uma string (Pega as palavras com split e extrai
     * seu radical)
     *
     * @param analyzer
     * @param string
     * @return
     * @throws IOException
     */
    private List<String> tokenizeString(Analyzer analyzer, String string) throws IOException {
        List<String> result = new ArrayList<>();

        TokenStream stream = analyzer.tokenStream(null, new StringReader(string));
        stream.reset();
        while (stream.incrementToken()) {
            result.add(stream.getAttribute(CharTermAttribute.class).toString());
        }
        return result;
    }

    public List<Musica> getListaOrdenada() {
        return listaOrdenada;
    }

    public void setListaOrdenada(List<Musica> listaOrdenada) {
        this.listaOrdenada = listaOrdenada;
    }
}
