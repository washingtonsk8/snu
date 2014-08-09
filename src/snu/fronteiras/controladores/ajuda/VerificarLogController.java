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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import snu.util.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.apache.log4j.Logger;
import snu.fronteiras.controladores.FXMLDocumentController;
import snu.util.BotoesImagemUtil;

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

    //Inicializando o Logger
    private static final Logger log = Logger.getLogger(VerificarLogController.class.getName());

    private void initComponents() {
        BotoesImagemUtil.definirComportamento(this.imgInicio);
        
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
            Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(),
                    "Erro ao abrir ou ler o arquivo de Log.\nFavor entrar em contato com o Administrador.",
                    "Erro!", "Erro", ex);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                log.error("Erro ao fechar o arquivo de Log", ex);
                Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(),
                        "Erro ao fechar o arquivo de Log.\nFavor entrar em contato com o Administrador.",
                        "Erro!", "Erro", ex);
            }
        }
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
}
