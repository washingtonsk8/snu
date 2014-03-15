/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores.integrante;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import snu.entidades.integrante.FuncaoIntegrante;
import snu.entidades.integrante.Integrante;
import snu.util.DataUtil;
import snu.util.EfeitosUtil;
import snu.util.StringUtil;

/**
 * Classe controladora do FXML
 *
 * @author Washington Luis
 */
public class VisualizarIntegranteController implements Initializable {

    @FXML
    private AnchorPane contentVisualizarIntegrante;
    @FXML
    private Label lblNome;
    @FXML
    private Font x1;
    @FXML
    private Label lblSexo;
    @FXML
    private Label lblDataNascimento;
    @FXML
    private Label lblEndereco;
    @FXML
    private Label lblTelefoneResidencial;
    @FXML
    private Label lblTelefoneCelular;
    @FXML
    private Label lblTelefoneComercial;
    @FXML
    private Label lblDataEntrada;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblFuncaoPrincipal;
    @FXML
    private Label lblFuncaoSecundaria;
    @FXML
    private ComboBox<FuncaoIntegrante> comboFuncaoSecundaria;
    @FXML
    private Button btnVoltar;
    @FXML
    private Label lblResultadoNome;
    @FXML
    private Label lblResultadoSexo;
    @FXML
    private Label lblResultadoDataNascimento;
    @FXML
    private Label lblResultadoEndereco;
    @FXML
    private Label lblResultadoTelefoneResidencial;
    @FXML
    private Label lblResultadoTelefoneCelular;
    @FXML
    private Label lblResultadoTelefoneComercial;
    @FXML
    private Label lblResultadoEmail;
    @FXML
    private Label lblResultadoDataEntrada;
    @FXML
    private Label lblResultadoFuncaoPrincipal;
    @FXML
    private Label lblResultadoFuncaoSecundaria;
    @FXML
    private Label lblVisualizarIntegrante;
    @FXML
    private Font x2;

    private Integrante integrante;

    private TemplatePesquisaIntegranteController controladorOrigem;

    public void initData(Integrante integranteSelecionado, TemplatePesquisaIntegranteController controladorOrigem) {
        this.integrante = integranteSelecionado;
        this.controladorOrigem = controladorOrigem;

        this.lblResultadoNome.setText(this.integrante.getNome());
        this.lblResultadoSexo.setText(this.integrante.getSexo().toString());
        this.lblResultadoDataNascimento.setText(DataUtil.formatarData(this.integrante.getDataNascimento()));
        this.lblResultadoEndereco.setText(this.integrante.getEndereco());
        this.lblResultadoEmail.setText(this.integrante.getEmail());
        this.lblResultadoTelefoneResidencial.setText(this.integrante.getTelefoneResidencial());
        this.lblResultadoTelefoneCelular.setText(this.integrante.getTelefoneCelular());
        this.lblResultadoTelefoneComercial.setText(this.integrante.getTelefoneComercial());
        this.lblResultadoDataEntrada.setText(DataUtil.formatarData(this.integrante.getDataEntrada()));
        this.lblResultadoFuncaoPrincipal.setText(this.integrante.getFuncaoPrimaria().toString());

        FuncaoIntegrante funcaoSecundaria = this.integrante.getFuncaoSecundaria();
        this.lblResultadoFuncaoSecundaria.setText(funcaoSecundaria == null ? StringUtil.VAZIA : funcaoSecundaria.toString());
    }

    /**
     * Inicializa as ações do controlador
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void onMouseClickedFromLblNome(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblSexo(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblDataNascimento(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblEndereco(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblTelefoneResidencial(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblTelefoneCelular(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblTelefoneComercial(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblDataEntrada(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblEmail(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblFuncaoPrincipal(MouseEvent event) {
    }

    @FXML
    private void onActionFromComboFuncaoSecundaria(ActionEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblFuncaoSecundaria(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromContentVisualizarIntegrante(MouseEvent event) {
        this.contentVisualizarIntegrante.requestFocus();
    }

    @FXML
    private void onActionFromBtnVoltar(ActionEvent event) {
        final AnchorPane content = this.controladorOrigem.getContent();

        //Limpa o conteúdo anterior e carrega a página
        AnchorPane pai = ((AnchorPane) this.contentVisualizarIntegrante.getParent());
        pai.getChildren().clear();
        pai.getChildren().add(content);
        EfeitosUtil.rodarEfeitoCarregamento(content);
    }
}
