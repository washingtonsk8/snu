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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import snu.entidades.musica.DocumentoMusica;
import snu.entidades.musica.Musica;
import snu.fronteiras.interfaces.ControladorDeConteudoInterface;
import snu.util.MusicaUtil;

/**
 * FXML Controller class
 *
 * @author Washington Luis
 */
public class EscreverMusicaController implements Initializable {

    @FXML
    private AnchorPane contentEscreverMusica;
    @FXML
    private Label lblEscreverMusica;
    @FXML
    private TextArea areaEscreverMusica;
    @FXML
    private Button btnOk;
    @FXML
    private Font x1;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnDetectarAcordes;

    private ControladorDeConteudoInterface controladorOrigem;

    private Musica musica;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initData(Musica musica, ControladorDeConteudoInterface controladorOrigem) {
        this.musica = musica;
        this.controladorOrigem = controladorOrigem;

        this.areaEscreverMusica.setText(this.musica.getDocumentoMusica().getConteudo());
    }

    @FXML
    private void onActionFromBtnDetectarAcordes(ActionEvent event) {
        //TODO: Remover BUG de recursão de detecção
        String conteudoMusica = this.areaEscreverMusica.getText();
        String conteudoMusicaComAcordesDetectados = MusicaUtil.detectarAcordes(conteudoMusica);
        this.areaEscreverMusica.setText(conteudoMusicaComAcordesDetectados);
    }

    @FXML
    private void onActionFromBtnOk(ActionEvent event) {
        this.musica.getDocumentoMusica().setConteudo(this.areaEscreverMusica.getText());

        //Limpa o conteúdo anterior e carrega a página
        AnchorPane pai = ((AnchorPane) this.contentEscreverMusica.getParent());
        pai.getChildren().clear();
        pai.getChildren().add(this.controladorOrigem.getContentPane());
    }

    @FXML
    private void onActionFromBtnCancelar(ActionEvent event) {
        //Limpa o conteúdo anterior e carrega a página
        AnchorPane pai = ((AnchorPane) this.contentEscreverMusica.getParent());
        pai.getChildren().clear();
        pai.getChildren().add(this.controladorOrigem.getContentPane());
    }

}
