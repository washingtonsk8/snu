/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores.musica;

import com.sun.javafx.scene.control.behavior.TextAreaBehavior;
import com.sun.javafx.scene.control.skin.TextAreaSkin;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import snu.entidades.musica.Musica;
import snu.fronteiras.controladores.FXMLDocumentController;
import snu.util.BotoesImagemUtil;
import snu.util.EfeitosUtil;
import snu.util.MusicaUtil;

/**
 * Classe controladora do FXML
 *
 * @author Washington Luis
 */
public class VisualizarConteudoMusicaController implements Initializable {

    @FXML
    private Label lblVisualizarConteudoMusica;
    @FXML
    private TextArea areaVisualizarMusica;
    @FXML
    private AnchorPane contentVisualizarConteudoMusica;
    @FXML
    private Label lblIntroducao;
    @FXML
    private TextField fldIntroducao;
    @FXML
    private ImageView imgInicio;
    @FXML
    private ImageView imgVoltar;

    private VisualizarMusicaController controladorOrigem;

    private void initComponents() {
        //Desabilita a edição dos campos
        this.fldIntroducao.setEditable(false);
        this.areaVisualizarMusica.setEditable(false);

        this.areaVisualizarMusica.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.TAB) {
                    TextAreaSkin skin = (TextAreaSkin) areaVisualizarMusica.getSkin();
                    if (skin.getBehavior() instanceof TextAreaBehavior) {
                        TextAreaBehavior behavior = (TextAreaBehavior) skin.getBehavior();
                        behavior.callAction("TraverseNext");
                        event.consume();
                    }
                }
            }
        });

        BotoesImagemUtil.definirComportamento(this.imgInicio);
        BotoesImagemUtil.definirComportamento(this.imgVoltar);
    }

    public void initData(Musica musica, VisualizarMusicaController controladorOrigem) {
        this.controladorOrigem = controladorOrigem;
        this.fldIntroducao.setText(MusicaUtil.limparParaImpressao(
                musica.getDocumentoMusica().getIntroducao()));
        this.areaVisualizarMusica.setStyle("-fx-font-size: 12;");
        this.areaVisualizarMusica.setText(MusicaUtil.limparParaImpressao(
                musica.getDocumentoMusica().getConteudo()));

        this.contentVisualizarConteudoMusica.requestFocus();
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
    private void onMouseClickedFromContentVisualizarConteudoMusica(MouseEvent event) {
        this.areaVisualizarMusica.requestFocus();
    }

    @FXML
    private void onMouseClickedFromLblIntroducao(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromImgInicio(MouseEvent event) {
        FXMLDocumentController.getInstancia().iniciarPaginaInicial();
    }

    @FXML
    private void onMouseClickedFromImgVoltar(MouseEvent event) {
        //Carrega a página anterior
        AnchorPane pai = ((AnchorPane) this.contentVisualizarConteudoMusica.getParent());
        EfeitosUtil.rodarEfeitoCarregamentoFadeOut(this.contentVisualizarConteudoMusica, pai.getChildren());
    }
}
