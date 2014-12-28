/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import snu.util.Dialogs;
import snu.util.EfeitosUtil;

/**
 * Classe que controla a tela principal do programa
 *
 * @author Washington Luis
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private AnchorPane contentAnchorPane;

    /**
     * Fábrica Singleton do sistema
     */
    private static FXMLDocumentController instancia;

    //Inicializando o Logger
    private static final Logger log = Logger.getLogger(FXMLDocumentController.class.getName());

    public void iniciarPaginaInicial() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/Home.fxml"));
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException ex) {
            log.error("Erro ao carregar tela inicial", ex);
            Dialogs.showErrorDialog(getStage(), "Erro ao carregar tela inicial."
                    + "\nFavor entrar em contato com o Administrador.", "Erro!", "Erro", ex);
        }

        //Limpa o conteúdo anterior e carrega a página
        this.contentAnchorPane.getChildren().clear();
        this.contentAnchorPane.getChildren().add(root);
        EfeitosUtil.rodarEfeitoCarregamentoFadeIn(root);
    }

    private void initComponents() {
        instancia = this;
        iniciarPaginaInicial();
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

    public Stage getStage() {
        return (Stage) this.contentAnchorPane.getScene().getWindow();
    }

    /**
     * Obtém a instância Singleton
     *
     * @return
     */
    public static FXMLDocumentController getInstancia() {
        return instancia;
    }

}
