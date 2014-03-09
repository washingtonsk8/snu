/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.controladores.indexador;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.apache.lucene.util.Version;
import snu.entidades.musica.Musica;
import snu.util.StringUtil;

/**
 * Controlador da Indexação
 *
 * @author Washington Luis
 */
public class IndexadorController {

    private static IndexadorController instancia;

    private final BrazilianAnalyzer brazilianAnalyzer;

    protected static final String[] STOP_WORDS = {"a", "agora", "ainda", "alguém", "algum",
        "alguma", "algumas", "alguns", "ampla", "amplas", "amplo", "amplos", "ante",
        "antes", "ao", "aos", "após", "aquela", "aquelas", "aquele", "aqueles", "aquilo",
        "as", "até", "através", "cada", "coisa", "coisas", "com", "como", "contra",
        "contudo", "da", "daquele", "daqueles", "das", "de", "dela", "delas", "dele",
        "deles", "depois", "dessa", "dessas", "desse", "desses", "desta", "destas",
        "deste", "deste", "destes", "deve", "devem", "devendo", "dever", "deverá",
        "deverão", "deveria", "deveriam", "devia", "deviam", "disse", "disso",
        "disto", "dito", "diz", "dizem", "do", "dos", "e", "é", "ela", "elas", "ele",
        "eles", "em", "enquanto", "entre", "era", "essa", "essas", "esse", "esses",
        "esta", "está", "estamos", "estão", "estas", "estava", "estavam", "estávamos",
        "este", "estes", "estou", "eu", "fazendo", "fazer", "feita", "feitas",
        "feito", "feitos", "foi", "for", "foram", "fosse", "fossem", "grande",
        "grandes", "há", "isso", "isto", "já", "la", "lá", "lhe", "lhes", "lo",
        "mas", "me", "mesma", "mesmas", "mesmo", "mesmos", "meu", "meus", "minha",
        "minhas", "muita", "muitas", "muito", "muitos", "na", "não", "nas", "nem",
        "nenhum", "nessa", "nessas", "nesta", "nestas", "ninguém", "no", "nos", "nós",
        "nossa", "nossas", "nosso", "nossos", "num", "numa", "nunca", "o", "os", "ou",
        "outra", "outras", "outro", "outros", "para", "pela", "pelas", "pelo", "pelos",
        "pequena", "pequenas", "pequeno", "pequenos", "per", "perante", "pode",
        "pude", "podendo", "poder", "poderia", "poderiam", "podia", "podiam", "pois",
        "por", "porém", "porque", "posso", "pouca", "poucas", "pouco", "poucos",
        "primeiro", "primeiros", "própria", "próprias", "próprio", "próprios", "quais",
        "qual", "quando", "quanto", "quantos", "que", "quem", "são", "se", "seja",
        "sejam", "sem", "sempre", "sendo", "será", "serão", "seu", "seus", "si", "sido",
        "só", "sob", "sobre", "sua", "suas", "talvez", "também", "tampouco", "te",
        "tem", "tendo", "tenha", "ter", "teu", "teus", "ti", "tido", "tinha",
        "tinham", "toda", "todas", "todavia", "todo", "todos", "tu", "tua", "tuas",
        "tudo", "última", "últimas", "último", "últimos", "um", "uma", "umas",
        "uns", "vendo", "ver", "vez", "vindo", "vir", "vos", "vós"};

    private IndexadorController() {//TODO: Estudar uso de eliminação de stop words
        this.brazilianAnalyzer = new BrazilianAnalyzer(Version.LUCENE_35);//, IndexadorController.STOP_WORDS);
    }

    /**
     * Realiza o pré-processamento padrão na string de entrada
     *
     * @param entrada
     * @return
     */
    public String preProcessar(String entrada) {
        //TODO: Remover da indexação tudo o que tiver entre parentesis.
        String saida;
        saida = StringUtil.somenteLetras(entrada, false);//Retira pontuação e (espaços --> false)
        saida = StringUtil.removerAcentos(saida);//Retira acentuação
        return saida.toLowerCase();//Passa tudo para minúsculo
    }

    /**
     * Realiza a indexação de uma Música
     *
     * @param musica
     * @throws java.lang.Exception
     */
    public void indexar(Musica musica) throws Exception {
        //Faz o parsing e salva a música
        new Parser().parse(musica);
    }

    public BrazilianAnalyzer getBrazilianAnalyzer() {
        return this.brazilianAnalyzer;
    }

    /**
     * Obtém a instância Singleton
     *
     * @return
     */
    public static IndexadorController getInstancia() {
        if (instancia == null) {
            instancia = new IndexadorController();
        }
        return instancia;
    }
}
