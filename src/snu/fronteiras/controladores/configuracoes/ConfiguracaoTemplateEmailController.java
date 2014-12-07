/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores.configuracoes;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import org.apache.log4j.Logger;
import snu.controladores.ConfiguracoesSistemaJpaController;
import snu.controladores.SNU;
import snu.fronteiras.controladores.FXMLDocumentController;
import snu.fronteiras.controladores.HomeController;
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
    private ImageView iconeSalvar;

    //Inicializando o Logger
    private static final Logger log = Logger.getLogger(ConfiguracaoTemplateEmailController.class.getName());

    private void initComponents() {
        BotoesImagemUtil.definirComportamento(this.imgInicio);
        this.areaTemplateEmail.setText(SNU.configuracoesSistema.getTemplateDescricaoEmail());
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
}
