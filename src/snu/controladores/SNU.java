/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.controladores;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import snu.bd.GerenciadorDeEntidades;
import snu.entidades.configuracoes.ConfiguracoesSistema;

/**
 * Classe que inicializa tudo.
 *
 * @author Washington Luis
 */
public class SNU extends Application {

    /**
     * Gerenciador de entidades do entidades do sistema
     */
    private static final GerenciadorDeEntidades gerenciadorDeEntidades = GerenciadorDeEntidades.getInstancia();
    private static final Double VERSAO = 1.0;
    public static ConfiguracoesSistema configuracoesSistema;

    /**
     * Inicia a aplicação
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        ConfiguracoesSistemaJpaController configuracoesSistemaController = ConfiguracoesSistemaJpaController.getInstancia();

        if (configuracoesSistemaController.getConfiguracoesSistemaCount() > 0) {
            //Tentativa de carregar as configurações padrão do sistema
            configuracoesSistema = configuracoesSistemaController.findConfiguracoesSistemaByVersao(VERSAO);
        }

        if (configuracoesSistema == null) {
            ConfiguracoesSistema novasConfiguracoesSistema = new ConfiguracoesSistema();
            novasConfiguracoesSistema.setVersao(VERSAO);
            novasConfiguracoesSistema.setTemplateDescricaoEmail("<saudação diária>,\n"
                    + "\n"
                    + "segue abaixo a relação de músicas para a missa do dia <data>.\n"
                    + "\n"
                    + "<músicas>\n"
                    + "\n"
                    + "Atenciosamente,\n"
                    + "\n"
                    + "Ministério de Música Nova Unção.");
            configuracoesSistemaController.create(novasConfiguracoesSistema);
            configuracoesSistema = novasConfiguracoesSistema;
        }

        Parent root = FXMLLoader.load(getClass().getResource("/snu/fronteiras/visao/FXMLDocument.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("Sistema Nova Unção");
        stage.setIconified(true);
        stage.setScene(scene);
        //stage.setMaxWidth(810d);
        //stage.setMaxHeight(625d);
        //stage.setResizable(false);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
