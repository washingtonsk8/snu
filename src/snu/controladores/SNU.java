/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.controladores;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.log4j.Logger;
import snu.bd.GerenciadorDeEntidades;
import snu.entidades.configuracoes.ConfiguracoesSistema;
import snu.fronteiras.controladores.HomeController;
import snu.util.Dialogs;
import snu.util.StringUtil;

/**
 * Classe que inicializa tudo.
 *
 * @author Washington Luis
 */
public class SNU extends Application {

    private static final String VERSAO = "2.0 BETA";

    private static ConfiguracoesSistemaJpaController configuracoesSistemaController;

    //Realizando o carregamento das configurações padrões do sistema
    public static ConfiguracoesSistema configuracoesSistema;

    //Inicializando o Logger
    private static final Logger log = Logger.getLogger(SNU.class.getName());

    /**
     * Inicia a aplicação
     *
     * @param stage
     */
    @Override
    @SuppressWarnings({"BroadCatchBlock", "TooBroadCatch"})
    public void start(Stage stage) {
        log.info("Iniciando a aplicação...");

        try {
            //Inicializando o Gerenciador de Entidades!
            GerenciadorDeEntidades.getInstancia();

            configuracoesSistemaController = ConfiguracoesSistemaJpaController.getInstancia();
            configuracoesSistema = configuracoesSistemaController.findConfiguracoesSistema();

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
            } else if (StringUtil.diferentes(configuracoesSistema.getVersao(), VERSAO)) {
                //Atualiza a versão caso ainda não seja a atual
                configuracoesSistema.setVersao(VERSAO);
                configuracoesSistemaController.edit(configuracoesSistema);
            }

            Parent root = FXMLLoader.load(getClass().getResource("/snu/fronteiras/visao/FXMLDocument.fxml"));

            Scene scene = new Scene(root);
            stage.setTitle("Sistema Nova Unção");
            stage.getIcons().add(new Image("/snu/fronteiras/images/icons/iconeSistema.png"));
            stage.setScene(scene);

            //PS.: Não alterar o tamanho definido
            stage.setMaxWidth(806.9);
            stage.setMaxHeight(629.9);

            stage.setResizable(false);

            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    Dialogs.DialogResponse resposta;
                    resposta = Dialogs.showConfirmDialog(
                            HomeController.getInstancia().getStage(),
                            "Deseja realmente sair do sistema?",
                            "Confirmação", "Confirmação");
                    if (resposta != Dialogs.DialogResponse.YES) {
                        event.consume();
                    } else {
                        log.info("Finalizando a aplicação...");
                    }
                }
            });

            stage.show();
            stage.toFront();
        } catch (IOException ex) {
            log.error("Erro ao carregar tela principal", ex);
            Dialogs.showErrorDialog(stage, "Erro ao carregar tela principal."
                    + "\nFavor entrar em contato com o Administrador.",
                    "Erro!", "Erro", ex);

        } catch (Exception ex) {
            log.error("Erro ao iniciar a unidade de persistencia (SNUPU)", ex);
            Dialogs.showErrorDialog(stage,
                    "Erro ao iniciar a aplicação."
                    + "\nEntre em contato com o administrador.", "Erro!", "Erro", ex);
        }

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
