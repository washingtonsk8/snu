/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores;

import com.sun.deploy.util.FXLoader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

/**
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
    private Menu menuIntegrante;
    @FXML
    private MenuItem itemCadastrarIntegrante;
    @FXML
    private MenuItem itemAtualizarIntegrante;
    @FXML
    private MenuItem itemPesquisarIntegrante;
    @FXML
    private MenuItem itemRemoverIntegrante;
    @FXML
    private Menu menuMusica;
    @FXML
    private MenuItem itemCriarDocumento;
    @FXML
    private MenuItem itemInserirMusica;
    @FXML
    private MenuItem itemPesquisarMusica;
    @FXML
    private MenuItem itemAtualizarMusica;
    @FXML
    private MenuItem itemAssociarMusica;
    @FXML
    private MenuItem itemSobre;

    private void initComponents() {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("" + url + " " + rb);
        initComponents();
    }
    
    @FXML
    private void onActionFromItemCadastrarIntegrante(ActionEvent event) {
    }

    @FXML
    private void onActionFromItemAtualizarIntegrante(ActionEvent event) {
    }

    @FXML
    private void onActionFromItemPesquisarIntegrante(ActionEvent event) {
    }

    @FXML
    private void onActionFromItemRemoverIntegrante(ActionEvent event) {
    }

    @FXML
    private void onActionFromItemCriarDocumento(ActionEvent event) {
    }

    @FXML
    private void onActionFromItemInserirMusica(ActionEvent event) {
    }

    @FXML
    private void onActionFromPesquisarMusica(ActionEvent event) {
    }

    @FXML
    private void onActionFromItemAtualizarMusica(ActionEvent event) {
    }

    @FXML
    private void onActionFromItemAssociarMusica(ActionEvent event) {
    }

    @FXML
    private void onActionFromItemSobre(ActionEvent event) {
    }
}
