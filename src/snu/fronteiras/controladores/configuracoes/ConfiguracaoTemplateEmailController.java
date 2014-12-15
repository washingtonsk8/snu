/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores.configuracoes;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.util.Duration;
import org.apache.log4j.Logger;
import snu.controladores.ConfiguracoesSistemaJpaController;
import snu.controladores.SNU;
import snu.fronteiras.controladores.FXMLDocumentController;
import snu.fronteiras.controladores.HomeController;
import snu.fronteiras.controladores.ajuda.AjudaController;
import snu.util.BotoesImagemUtil;
import snu.util.Dialogs;

/**
 * Classe controladora do FXML
 *
 * @author Washington Luis
 */
public class ConfiguracaoTemplateEmailController implements Initializable {

    @FXML
    private AnchorPane contentTemplateEmail;
    @FXML
    private Label lblTemplateEmail;
    @FXML
    private Button btnSalvar;
    @FXML
    private Font x1;
    @FXML
    private Label lblInformacaoFuncionamento;
    @FXML
    private TextArea areaTemplateEmail;
    @FXML
    private Label lblInformacaoTags;
    @FXML
    private ImageView imgInicio;
    @FXML
    private ImageView imgVoltar;
    @FXML
    private ImageView iconeSalvar;

    //Inicializando o Logger
    private static final Logger log = Logger.getLogger(ConfiguracaoTemplateEmailController.class.getName());
    
    private ConfiguracoesController controladorOrigem;

    private void initComponents() {
        BotoesImagemUtil.definirComportamento(this.imgInicio);
        BotoesImagemUtil.definirComportamento(this.imgVoltar);
        
        this.areaTemplateEmail.setText(SNU.configuracoesSistema.getTemplateDescricaoEmail());
    }

    public void initData(ConfiguracoesController controladorOrigem) {
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
    private void onMouseClickedFromContentMontarMissaFinalizacao(MouseEvent event) {
        this.contentTemplateEmail.requestFocus();
    }

    @FXML
    private void onActionFromBtnSalvar(ActionEvent event) {
        SNU.configuracoesSistema.setTemplateDescricaoEmail(this.areaTemplateEmail.getText());
        try {
            ConfiguracoesSistemaJpaController.getInstancia().edit(SNU.configuracoesSistema);
            Dialogs.showInformationDialog(HomeController.getInstancia().getStage(), "O template foi salvo com sucesso!", "Sucesso!", "Informação");
        } catch (Exception ex) {
            log.error("Erro ao salvar template de e-mail", ex);
            Dialogs.showErrorDialog(HomeController.getInstancia().getStage(),
                    "Erro ao salvar o Template de E-mail."
                    + "\nFavor entrar em contato com o Administrador.", "Erro!", "Erro", ex);
        }
    }

    @FXML
    private void onMouseClickedFromImgVoltar(MouseEvent event) {
        //Limpa o conteúdo anterior e carrega a página
        AnchorPane pai = ((AnchorPane) this.contentTemplateEmail.getParent());
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
