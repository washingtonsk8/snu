/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import snu.fronteiras.controladores.ajuda.AjudaController;
import snu.fronteiras.controladores.configuracoes.ConfiguracoesController;
import snu.util.BotoesImagemUtil;
import snu.util.Dialogs;
import snu.util.EfeitosUtil;

/**
 * Classe controladora do FXML
 *
 * @author Washington Luis
 */
public class HomeController implements Initializable {

    @FXML
    private AnchorPane contentHome;
    @FXML
    private ImageView logoSistema;
    @FXML
    private ImageView iconeIntegrantes;
    @FXML
    private ImageView iconeMusicas;
    @FXML
    private ImageView iconeMissas;
    @FXML
    private ImageView iconeConfiguracoes;
    @FXML
    private ImageView iconeAutores;

    /**
     * Fábrica Singleton do sistema
     */
    private static HomeController instancia;

    //Inicializando o Logger
    private static final Logger log = Logger.getLogger(HomeController.class.getName());
    @FXML
    private ImageView iconeAjuda;

    private void initComponents() {
        instancia = this;

        this.logoSistema.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logoSistema.setEffect(EfeitosUtil.getEfeitoCeuAzul());
            }
        });

        this.logoSistema.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logoSistema.setEffect(null);
            }
        });

        BotoesImagemUtil.definirComportamentoTelaInicial(this.iconeIntegrantes);
        BotoesImagemUtil.definirComportamentoTelaInicial(this.iconeMusicas);
        BotoesImagemUtil.definirComportamentoTelaInicial(this.iconeMissas);
        BotoesImagemUtil.definirComportamentoTelaInicial(this.iconeConfiguracoes);
        BotoesImagemUtil.definirComportamentoTelaInicial(this.iconeAutores);
        BotoesImagemUtil.definirComportamentoTelaInicial(this.iconeAjuda);
    }

    /**
     * Inicializa as ações do controlador
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initComponents();
    }

    @FXML
    private void onClickFromIconeIntegrantes(MouseEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/integrante/PesquisarIntegrante.fxml"));

        try {
            Parent root = (Parent) fxmlLoader.load();

            //Limpa o conteúdo anterior e carrega a página
            this.contentHome.getChildren().clear();
            this.contentHome.getChildren().add(root);
            EfeitosUtil.rodarEfeitoCarregamentoFadeIn(root);
        } catch (IOException ex) {
            log.error("Erro ao carregar tela de Pesquisa de Integrante", ex);
            Dialogs.showErrorDialog(getStage(),
                    "Erro de processamento interno.\nFavor entrar em contato com o Administrador.", "Erro interno!", "Erro", ex);
        }
    }

    @FXML
    private void onClickFromIconeMusicas(MouseEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/musica/PesquisarMusica.fxml"));

        try {
            Parent root = (Parent) fxmlLoader.load();

            //Limpa o conteúdo anterior e carrega a página
            this.contentHome.getChildren().clear();
            this.contentHome.getChildren().add(root);
            EfeitosUtil.rodarEfeitoCarregamentoFadeIn(root);
        } catch (IOException ex) {
            log.error("Erro ao carregar tela de Pesquisa de Músicas", ex);
            Dialogs.showErrorDialog(getStage(),
                    "Erro de processamento interno.\nFavor entrar em contato com o Administrador.", "Erro interno!", "Erro", ex);
        }
    }

    @FXML
    private void onClickFromIconeAutores(MouseEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/musica/GerenciarAutores.fxml"));

        try {
            Parent root = (Parent) fxmlLoader.load();

            //Limpa o conteúdo anterior e carrega a página
            this.contentHome.getChildren().clear();
            this.contentHome.getChildren().add(root);
            EfeitosUtil.rodarEfeitoCarregamentoFadeIn(root);
        } catch (IOException ex) {
            log.error("Erro ao carregar tela de Gerência de Autores", ex);
            Dialogs.showErrorDialog(getStage(), "Erro ao carregar tela de Gerência de Autores."
                    + "\nFavor entrar em contato com o Administrador.",
                    "Erro!", "Erro", ex);
        }
    }

    @FXML
    private void onClickFromIconeMissas(MouseEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/missa/PesquisarMissa.fxml"));

        try {
            Parent root = (Parent) fxmlLoader.load();

            //Limpa o conteúdo anterior e carrega a página
            this.contentHome.getChildren().clear();
            this.contentHome.getChildren().add(root);
            EfeitosUtil.rodarEfeitoCarregamentoFadeIn(root);
        } catch (IOException ex) {
            log.error("Erro ao carregar tela de Pesquisa de Missa", ex);
            Dialogs.showErrorDialog(getStage(),
                    "Erro de processamento interno.\nFavor entrar em contato com o Administrador.", "Erro interno!", "Erro", ex);
        }
    }

    @FXML
    private void onClickFromIconeConfiguracoes(MouseEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/configuracoes/Configuracoes.fxml"));

        try {
            Parent root = (Parent) fxmlLoader.load();

            ConfiguracoesController configuracoesController = fxmlLoader.getController();

            //Limpa o conteúdo anterior e carrega a página
            configuracoesController.initData(this);
            this.contentHome.getChildren().add(root);
            EfeitosUtil.rodarEfeitoCarregamentoTranslate(root);
        } catch (IOException ex) {
            log.error("Erro ao carregar tela de Configurações", ex);
            Dialogs.showErrorDialog(getStage(),
                    "Erro de processamento interno.\nFavor entrar em contato com o Administrador.", "Erro interno!", "Erro", ex);
        }
    }

    @FXML
    private void onClickFromIconeAjuda(MouseEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/ajuda/Ajuda.fxml"));

        try {
            Parent root = (Parent) fxmlLoader.load();

            AjudaController ajudaController = fxmlLoader.getController();

            //Limpa o conteúdo anterior e carrega a página
            ajudaController.initData(this);
            this.contentHome.getChildren().add(root);
            EfeitosUtil.rodarEfeitoCarregamentoTranslate(root);
        } catch (IOException ex) {
            log.error("Erro ao carregar tela de Ajuda", ex);
            Dialogs.showErrorDialog(getStage(),
                    "Erro de processamento interno.\nFavor entrar em contato com o Administrador.", "Erro interno!", "Erro", ex);
        }
    }

    public Stage getStage() {
        return (Stage) this.contentHome.getScene().getWindow();
    }

    /**
     * Obtém a instância Singleton
     *
     * @return
     */
    public static HomeController getInstancia() {
        return instancia;
    }

    public AnchorPane getContent() {
        return this.contentHome;
    }
}
