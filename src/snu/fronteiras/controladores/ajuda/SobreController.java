/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores.ajuda;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import snu.controladores.SNU;

/**
 * Classe controladora do FXML
 *
 * @author Washington Luis
 */
public class SobreController implements Initializable {

    @FXML
    private Label lblLogoSistema;
    @FXML
    private Label lblVersao;
    @FXML
    private Font x1;
    @FXML
    private Label lblResultadoVersao;

    private void initComponents() {
        this.lblResultadoVersao.setText(SNU.configuracoesSistema.getVersao().toString());
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
}
