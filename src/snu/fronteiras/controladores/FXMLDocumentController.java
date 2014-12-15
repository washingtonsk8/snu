/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import net.lingala.zip4j.exception.ZipException;
import org.apache.log4j.Logger;
import snu.bd.BD;
import snu.fronteiras.controladores.geral.ProgressoController;
import snu.util.Dialogs;
import snu.util.EfeitosUtil;
import snu.util.SeletorArquivosUtil;

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
    @FXML
    private MenuBar menubar;
    @FXML
    private MenuItem itemSobre;
    @FXML
    private Menu menuMissa;
    @FXML
    private Menu menuConfiguracoes;
    @FXML
    private Menu menuBackup;
    @FXML
    private MenuItem itemImportarDados;
    @FXML
    private MenuItem itemExportarDados;
    @FXML
    private MenuItem itemTemplateEmail;
    @FXML
    private Menu menuBancoDados;
    @FXML
    private MenuItem itemEscolherDiretorioBancoDados;
    @FXML
    private MenuItem itemLimparDadosMissa;
    @FXML
    private MenuItem itemVerificarLog;
    @FXML
    private MenuItem itemDefinirPreferenciaTons;
    @FXML
    private Menu menuConfiguracoesMissa;

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
    
    private void onShowingFromMenuHome(Event event) {
        event.consume();
        iniciarPaginaInicial();
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
