/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores.ajuda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.apache.log4j.Logger;
import snu.fronteiras.controladores.FXMLDocumentController;
import snu.fronteiras.controladores.HomeController;
import snu.util.BotoesImagemUtil;
import snu.util.Dialogs;

/**
 * Classe que controla as ações da apresentação do arquivo de Log
 *
 * @author Washington Luis
 */
public class VerificarLogController implements Initializable {

    @FXML
    private AnchorPane contentVerificarLog;
    @FXML
    private Label lblVerificarLog;
    @FXML
    private TextArea areaArquivoLog;
    @FXML
    private ImageView imgInicio;
    @FXML
    private ImageView imgVoltar;

    //Inicializando o Logger
    private static final Logger log = Logger.getLogger(VerificarLogController.class.getName());

    private AjudaController controladorOrigem;

    private void initComponents() {
        BotoesImagemUtil.definirComportamento(this.imgInicio);
        BotoesImagemUtil.definirComportamento(this.imgVoltar);

        BufferedReader br = null;

        try {
            String linhaAtual;

            br = new BufferedReader(new FileReader("snu.log"));

            while ((linhaAtual = br.readLine()) != null) {
                areaArquivoLog.appendText(linhaAtual);
                areaArquivoLog.appendText("\n");
            }

            //Utilizado se for de preferência ir para o começo do arquivo
            //areaArquivoLog.home();
        } catch (IOException ex) {
            log.error("Erro ao abrir ou ler o arquivo de Log", ex);
            Dialogs.showErrorDialog(HomeController.getInstancia().getStage(),
                    "Erro ao abrir ou ler o arquivo de Log.\nFavor entrar em contato com o Administrador.",
                    "Erro!", "Erro", ex);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                log.error("Erro ao fechar o arquivo de Log", ex);
                Dialogs.showErrorDialog(HomeController.getInstancia().getStage(),
                        "Erro ao fechar o arquivo de Log.\nFavor entrar em contato com o Administrador.",
                        "Erro!", "Erro", ex);
            }
        }
    }

    public void initData(AjudaController controladorOrigem) {
        this.controladorOrigem = controladorOrigem;
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
    private void onMouseClickedFromImgInicio(MouseEvent event) {
        FXMLDocumentController.getInstancia().iniciarPaginaInicial();
    }

    @FXML
    private void onMouseClickedFromContentVisualizarLog(MouseEvent event) {
        this.contentVerificarLog.requestFocus();
    }

    @FXML
    private void onMouseClickedFromImgVoltar(MouseEvent event) {
        //Limpa o conteúdo anterior e carrega a página
        AnchorPane pai = ((AnchorPane) this.contentVerificarLog.getParent());
        ObservableList<Node> filhos = pai.getChildren();

        final FadeTransition ft = new FadeTransition(Duration.millis(500), filhos.get(filhos.size() - 1));
        ft.setFromValue(1.);
        ft.setToValue(0.);
        ft.play();

        ft.setOnFinished(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                filhos.remove(filhos.size() - 1);
            }
        });
    }
}
