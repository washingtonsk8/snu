/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import snu.fronteiras.controladores.integrante.PesquisarIntegranteController;
import snu.geral.TipoPagina;
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

    /**
     * Fábrica Singleton do sistema
     */
    private static HomeController instancia;

    //Inicializando o Logger
    private static final Logger log = Logger.getLogger(HomeController.class.getName());

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
            EfeitosUtil.rodarEfeitoCarregamentoFade(root);
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
            EfeitosUtil.rodarEfeitoCarregamentoFade(root);
        } catch (IOException ex) {
            log.error("Erro ao carregar tela de Pesquisa de Músicas", ex);
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
}
