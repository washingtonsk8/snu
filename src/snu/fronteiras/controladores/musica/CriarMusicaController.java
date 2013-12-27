/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snu.fronteiras.controladores.musica;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import snu.entidades.musica.AssociacaoIntegranteMusica;

/**
 * FXML Controller class
 *
 * @author Washington Luis
 */
public class CriarMusicaController implements Initializable {
        private TextArea txtArea;
    @FXML
    private Label lblLeituras;
    @FXML
    private Font x1;
    @FXML
    private Label lblAutor;
    @FXML
    private Label lblTitulo;
    @FXML
    private Label lblTom;
    @FXML
    private Label lblAfinacao;
    @FXML
    private Label lblTipo;
    @FXML
    private Label lblAssociacoes;
    @FXML
    private TableColumn<AssociacaoIntegranteMusica, String> tblAssociacoes;
    @FXML
    private Pane paneTipos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

        private void onActionFromBtnPrint(ActionEvent event) {
        System.out.println("" + txtArea.getText());
    }

    @FXML
    private void onMouseClickedFromLblLeituras(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblAutor(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblTitulo(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblTom(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblAfinacao(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblTipo(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblAssociacoes(MouseEvent event) {
    }
    
}
