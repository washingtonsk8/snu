/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.util;

import java.util.List;
import snu.controladores.MusicaJpaController;
import snu.entidades.musica.indexador.ObjetoListaInvertida;
import snu.entidades.musica.indexador.Vocabulo;

/**
 * Utilitário para o processador de consultas
 *
 * @author Washington Luis
 */
public class ProcessadorDeConsultasUtil {

    public static Double calcularPeso(ObjetoListaInvertida objetoListaInvertida, Integer quantidadeDocumentos) {
        Double peso, TF, IDF;
        MusicaJpaController musicaController = MusicaJpaController.getInstancia();

        TF = objetoListaInvertida.getFrequenciaToken().doubleValue()
                / musicaController.findMusica(objetoListaInvertida.getIdMusica())
                .getDocumentoMusica().getFrequenciaMaximaToken().doubleValue();

        IDF = Math.log(quantidadeDocumentos.doubleValue()
                / ((double) objetoListaInvertida.getVocabulo().getListaInvertida().size()))
                / Math.log(2.0);

        peso = TF * IDF;

        return peso;
    }

    public static Double calcularPeso(Vocabulo vocabulo, List<String> tokensConsulta, Integer quantidadeDocumentos) {
        int tmax = 0, tiq = 0;//Ti,q (frequência do termo i na query) e Tmax
        double peso, TF, IDF;

        //Calculando tiq e tmax
        for (String tokenConsulta : tokensConsulta) {
            if (tokenConsulta.equals(vocabulo.getToken())) {
                tiq++;
                if (tiq > tmax) {
                    tmax = tiq;
                }
            }
        }

        TF = (double)tiq / (double)tmax;

        IDF = Math.log(quantidadeDocumentos.doubleValue()
                / ((double) vocabulo.getListaInvertida().size()))
                / Math.log(2.0);

        peso = TF * IDF;
        
        return peso;
    }
    
}
