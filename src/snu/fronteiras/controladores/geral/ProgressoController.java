/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores.geral;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Washington Luis
 */
public class ProgressoController implements Initializable {

    @FXML
    private AnchorPane contentProgresso;
    @FXML
    private ProgressBar progressoAcao;
    @FXML
    private Label lblAcao;

    private void initComponents() {
    }

    public void initData(String textoAcao) {
        lblAcao.setText(textoAcao);
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

    public void setProgresso(double progresso) {
        this.progressoAcao.setProgress(progresso);
    }

    public ProgressBar getProgressoAcao() {
        return progressoAcao;
    }

    public void setProgressoAcao(ProgressBar progressoAcao) {
        this.progressoAcao = progressoAcao;
    }

    public Label getLblAcao() {
        return lblAcao;
    }

    public void setLblAcao(Label lblAcao) {
        this.lblAcao = lblAcao;
    }
}
