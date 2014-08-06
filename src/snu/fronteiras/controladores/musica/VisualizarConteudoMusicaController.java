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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import snu.entidades.musica.Musica;
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
    private Button btnVoltar;
    @FXML
    private Font x1;
    @FXML
    private AnchorPane contentVisualizarConteudoMusica;
    @FXML
    private Label lblIntroducao;
    @FXML
    private TextField fldIntroducao;

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
    private void onActionFromBtnVoltar(ActionEvent event) {
        final Parent root = this.controladorOrigem.getContentVisualizarMusica();

        //Limpa o conteúdo anterior e carrega a página
        AnchorPane pai = ((AnchorPane) this.contentVisualizarConteudoMusica.getParent());
        pai.getChildren().clear();
        pai.getChildren().add(root);
        EfeitosUtil.rodarEfeitoCarregamento(root);
    }
}
