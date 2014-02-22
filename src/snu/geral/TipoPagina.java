/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.geral;

/**
 * Define o tipo de página a ser carregada
 *
 * @author Washington Luis
 */
public enum TipoPagina {

    /**
     * Define página de template
     * de pesquisa para atualização
     */
    PESQUISA_ATUALIZACAO_DADOS,

    /**
     * Define página de template
     * de pesquisa para visualização
     */
    PESQUISA_VISUALIZACAO_DADOS,

    /**
     * Define página de template
     * de pesquisa para remoção
     */
    PESQUISA_REMOCAO,
    
    /**
     * Define página de template
     * de pesquisa para geração de impressão
     */
    PESQUISA_GERACAO_IMPRESSAO;
}
