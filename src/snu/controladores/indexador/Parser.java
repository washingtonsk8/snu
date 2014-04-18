/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.controladores.indexador;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import snu.controladores.DocumentoMusicaJpaController;
import snu.controladores.VocabuloJpaController;
import snu.entidades.musica.DocumentoMusica;
import snu.entidades.musica.Musica;
import snu.entidades.musica.indexador.ObjetoListaInvertida;
import snu.entidades.musica.indexador.Vocabulo;
import snu.util.MusicaUtil;

/**
 * Classe que realiza o parsing no documento
 *
 * @author Washington Luis
 */
public class Parser {

    /**
     * Realiza o parsing na Música (busca as palavras, extrai o radical e as
     * armazena)
     *
     * @param musica
     * @throws java.lang.Exception
     */
    public void parse(Musica musica) throws Exception {
        IndexadorController indexadorController = IndexadorController.getInstancia();
        String conteudoMusica = musica.getDocumentoMusica().getConteudo();
        //CODE REVIEW: Melhor usar o HasAlgo
        if (conteudoMusica != null) {
            List<String> tokens = tokenizeString(indexadorController.getBrazilianAnalyzer(),
                    indexadorController.preProcessar(MusicaUtil.removerAcordes(conteudoMusica)));

            int quantidadeTokens = 0;

            /**
             * Caso não apareça nenhum novo, a frequência máxima de um token em
             * um documento é 1
             */
            int frequenciaMaximaToken = tokens.isEmpty() ? 0 : 1;

            HashMap<String, Integer> vocabuloAnalisado = new HashMap();
            for (String token : tokens) {
                if (vocabuloAnalisado.get(token) == null) {
                    vocabuloAnalisado.put(token, 1);
                } else {
                    Integer frequenciaToken = vocabuloAnalisado.get(token);
                    vocabuloAnalisado.put(token, ++frequenciaToken);
                    if (frequenciaToken > frequenciaMaximaToken) {
                        frequenciaMaximaToken = frequenciaToken;
                    }
                }
                quantidadeTokens++;
            }

            VocabuloJpaController vocabuloController = VocabuloJpaController.getInstancia();
            for (Map.Entry<String, Integer> entradaToken : vocabuloAnalisado.entrySet()) {
                Vocabulo vocabulo = vocabuloController.findVocabuloByToken(entradaToken.getKey());
                if (vocabulo == null) {
                    ObjetoListaInvertida objetoListaInvertida = new ObjetoListaInvertida();
                    vocabulo = new Vocabulo();

                    objetoListaInvertida.setVocabulo(vocabulo);
                    objetoListaInvertida.setIdMusica(musica.getId());
                    objetoListaInvertida.setIdDocumentoMusica(musica.getDocumentoMusica().getId());
                    objetoListaInvertida.setFrequenciaToken(entradaToken.getValue());
                    vocabulo.setToken(entradaToken.getKey());
                    vocabulo.getListaInvertida().add(objetoListaInvertida);

                    //Persiste no banco
                    vocabuloController.create(vocabulo);
                } else {
                    ObjetoListaInvertida objetoListaInvertida = new ObjetoListaInvertida();
                    objetoListaInvertida.setVocabulo(vocabulo);
                    objetoListaInvertida.setIdMusica(musica.getId());
                    objetoListaInvertida.setIdDocumentoMusica(musica.getDocumentoMusica().getId());
                    objetoListaInvertida.setFrequenciaToken(entradaToken.getValue());
                    vocabulo.getListaInvertida().add(objetoListaInvertida);
                    //Atualiza no banco
                    vocabuloController.edit(vocabulo);
                }
            }

            DocumentoMusica documentoMusica = musica.getDocumentoMusica();
            documentoMusica.setQuantidadeTokens(quantidadeTokens);
            documentoMusica.setFrequenciaMaximaToken(frequenciaMaximaToken);
            DocumentoMusicaJpaController.getInstancia().edit(documentoMusica);
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
}
