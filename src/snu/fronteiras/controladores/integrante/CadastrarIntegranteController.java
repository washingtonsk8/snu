/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores.integrante;

import javafx.scene.control.DatePicker;
import java.net.URL;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import snu.util.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import snu.controladores.IntegranteJpaController;
import snu.entidades.integrante.FuncaoIntegrante;
import snu.entidades.integrante.Integrante;
import snu.entidades.integrante.Sexo;
import snu.fronteiras.controladores.FXMLDocumentController;
import snu.geral.TipoPagina;
import snu.util.BotoesImagemUtil;
import snu.util.DataUtil;
import snu.util.EfeitosUtil;
import snu.util.RegexUtil;
import snu.util.StringUtil;

/**
 * Classe controladora do FXML
 *
 * @author Washington Luis
 */
public class CadastrarIntegranteController implements Initializable {

    @FXML
    private Label lblNome;
    @FXML
    private Font x1;
    @FXML
    private TextField fldNome;
    @FXML
    private Label lblSexo;
    @FXML
    private RadioButton radioFeminino;
    @FXML
    private RadioButton radioMasculino;
    @FXML
    private Label lblDataNascimento;
    @FXML
    private AnchorPane contentCadastrarIntegrante;
    @FXML
    private Label lblIdade;
    @FXML
    private Label lblEndereco;
    @FXML
    private TextField fldEndereco;
    @FXML
    private Label lblTelefoneResidencial;
    @FXML
    private TextField fldTelefoneResidencial;
    @FXML
    private Label lblTelefoneCelular;
    @FXML
    private TextField fldTelefoneCelular;
    @FXML
    private Label lblTelefoneComercial;
    @FXML
    private TextField fldTelefoneComercial;
    @FXML
    private Font x2;
    @FXML
    private Label lblIdadeMinisterial;
    @FXML
    private Label lblDataEntrada;
    @FXML
    private Label lblFuncaoPrincipal;
    @FXML
    private Label lblFuncaoSecundaria;
    @FXML
    private ComboBox<FuncaoIntegrante> comboFuncaoPrincipal;
    @FXML
    private ComboBox<FuncaoIntegrante> comboFuncaoSecundaria;
    @FXML
    private Button btnSalvar;
    @FXML
    private Label lblEmail;
    @FXML
    private TextField fldEmail;
    @FXML
    private Font x3;
    @FXML
    private Button btnLimpar;
    @FXML
    private Label lblCadastrarIntegrante;
    @FXML
    private Font x4;
    @FXML
    private ImageView imgInicio;
    @FXML
    private DatePicker dpDataNascimento;
    @FXML
    private DatePicker dpDataEntrada;
    @FXML
    private Label lblMinisterio;
    @FXML
    private TextField fldMinisterio;

    private Integrante integranteRow;

    private FXMLDocumentController controladorPrincipal;

    private final ObservableList<FuncaoIntegrante> funcoesIntegrante = FXCollections.observableArrayList(FuncaoIntegrante.values());

    private void definirAtividadeDeFocoDosCampos() {
        //Campo de e-mail
        this.fldEmail.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean valorAntigo, Boolean novoValor) {
                if (!novoValor) {//Perda de foco
                    if (StringUtil.hasAlgo(fldEmail.getText()) && !RegexUtil.validarEmail(fldEmail.getText())) {
                        fldEmail.setEffect(EfeitosUtil.getEfeitoInvalido());
                    } else {
                        fldEmail.setEffect(null);
                    }
                } else {
                    if (StringUtil.hasAlgo(fldEmail.getText()) && !RegexUtil.validarEmail(fldEmail.getText())) {
                        fldEmail.setEffect(EfeitosUtil.getEfeitoInvalido());
                    } else {
                        fldEmail.setEffect(EfeitosUtil.getEfeitoValido());
                    }
                }
            }
        });

        this.dpDataNascimento.getEditor().textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                LocalDate dataNascimento = dpDataNascimento.getValue();
                if (dataNascimento != null) {
                    lblIdade.setText(DataUtil.getIdade(DataUtil.toDate(dataNascimento)) + " anos");
                }
            }
        });

        this.dpDataEntrada.getEditor().textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                LocalDate dataEntrada = dpDataEntrada.getValue();
                if (dataEntrada != null) {
                    lblIdadeMinisterial.setText(DataUtil.getIdade(DataUtil.toDate(dataEntrada)) + " anos de ministério");
                }
            }
        });
    }

    public void initData(FXMLDocumentController controladorPrincipal) {
        this.controladorPrincipal = controladorPrincipal;
    }

    private void initComponents() {
        final ToggleGroup grupo = new ToggleGroup();

        this.radioFeminino.setToggleGroup(grupo);
        this.radioMasculino.setToggleGroup(grupo);

        this.fldNome.setPromptText("Nome Completo do Integrante");
        this.fldEndereco.setPromptText("Nome da Rua, N° - Bairro, Cidade");
        this.fldEmail.setPromptText("login@exemplo.com");
        this.fldTelefoneResidencial.setPromptText("(__) ____-____");
        this.fldTelefoneCelular.setPromptText("(__) ____-____");
        this.fldTelefoneComercial.setPromptText("(__) ____-____");
        this.fldMinisterio.setPromptText("Nome do Ministério de Música que participa");

        //Adicionando a lista no combo
        this.comboFuncaoPrincipal.setItems(this.funcoesIntegrante);
        this.comboFuncaoSecundaria.setItems(this.funcoesIntegrante);
        this.comboFuncaoPrincipal.getItems().remove(FuncaoIntegrante.NENHUMA);
        this.comboFuncaoSecundaria.setValue(FuncaoIntegrante.NENHUMA);
        this.lblFuncaoSecundaria.setDisable(true);
        this.lblFuncaoSecundaria.setOpacity(0.6);
        this.comboFuncaoSecundaria.setDisable(true);
        this.comboFuncaoSecundaria.setOpacity(0.6);

        BotoesImagemUtil.definirComportamento(this.imgInicio);

        definirAtividadeDeFocoDosCampos();

        //Define a existência de um novo cadastro
        this.integranteRow = new Integrante();
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
    private void onMouseClickedFromLblNome(MouseEvent event) {
        this.fldNome.requestFocus();
    }

    @FXML
    private void onActionFromFldNome(ActionEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblSexo(MouseEvent event) {
        this.radioFeminino.requestFocus();
    }

    @FXML
    private void onActionFromRadioFeminino(ActionEvent event) {
        this.radioFeminino.setEffect(null);
        this.radioMasculino.setEffect(null);
    }

    @FXML
    private void onActionFromRadioMasculino(ActionEvent event) {
        this.radioFeminino.setEffect(null);
        this.radioMasculino.setEffect(null);
    }

    @FXML
    private void onMouseClickedFromLblDataNascimento(MouseEvent event) {
        this.dpDataNascimento.requestFocus();
    }

    @FXML
    private void onMouseClickedFromIdade(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblEndereco(MouseEvent event) {
        this.fldEndereco.requestFocus();
    }

    @FXML
    private void onActionFromFldEndereco(ActionEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblTelefoneResidencial(MouseEvent event) {
        this.fldTelefoneResidencial.requestFocus();
    }

    @FXML
    private void onActionFromFldTelefoneResidencial(ActionEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblTelefoneCelular(MouseEvent event) {
        this.fldTelefoneCelular.requestFocus();
    }

    @FXML
    private void onActionFromFldTelefoneCelular(ActionEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblTelefoneComercial(MouseEvent event) {
        this.fldTelefoneComercial.requestFocus();
    }

    @FXML
    private void onActionFromFldTelefoneComercial(ActionEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblEmail(MouseEvent event) {
        this.fldEmail.requestFocus();
    }

    @FXML
    private void onActionFromFldEmail(ActionEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblDataEntrada(MouseEvent event) {
        this.dpDataEntrada.requestFocus();
    }

    @FXML
    private void onMouseClickedFromIdadeMinisterial(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblFuncaoPrincipal(MouseEvent event) {
        this.comboFuncaoPrincipal.requestFocus();
    }

    @FXML
    private void onMouseClickedFromLblFuncaoSecundaria(MouseEvent event) {
        this.comboFuncaoSecundaria.requestFocus();
    }

    @FXML
    private void onActionFromComboFuncaoPrincipal(ActionEvent event) {
        this.comboFuncaoPrincipal.setEffect(null);
        this.lblFuncaoSecundaria.setDisable(false);
        this.lblFuncaoSecundaria.setOpacity(1);
        this.comboFuncaoSecundaria.setDisable(false);
        this.comboFuncaoSecundaria.setOpacity(1);
    }

    @FXML
    private void onActionFromComboFuncaoSecundaria(ActionEvent event) {
    }

    @FXML
    private void onMouseClickedFromContentCadastrarIntegrante(MouseEvent event) {
        this.contentCadastrarIntegrante.requestFocus();
    }

    @FXML
    private void onKeyTypedFromFldNome(KeyEvent event) {
        this.fldNome.setEffect(null);
    }

    @FXML
    private void onKeyTypedFromFldEndereco(KeyEvent event) {
    }

    @FXML
    private void onKeyReleasedFromFldEmail(KeyEvent event) {
        if (!RegexUtil.validarEmail(this.fldEmail.getText())) {
            this.fldEmail.setEffect(EfeitosUtil.getEfeitoInvalido());
        } else {
            this.fldEmail.setEffect(EfeitosUtil.getEfeitoValido());
        }
    }

    @FXML
    private void onKeyReleasedFromFldTelefoneResidencial(KeyEvent event) {
    }

    @FXML
    private void onKeyReleasedFromFldTelefoneCelular(KeyEvent event) {
    }

    @FXML
    private void onKeyReleasedFromFldTelefoneComercial(KeyEvent event) {
    }

    @FXML
    private void onRequestedFromFldEmail(ContextMenuEvent event) {
        this.fldEmail.setEffect(null);
    }

    @FXML
    private void onActionFromBtnLimpar(ActionEvent event) {
        this.integranteRow = new Integrante();

        this.comboFuncaoPrincipal.setValue(null);
        this.comboFuncaoSecundaria.setValue(FuncaoIntegrante.NENHUMA);
        this.contentCadastrarIntegrante.getChildren().remove(this.dpDataNascimento);
        this.contentCadastrarIntegrante.getChildren().remove(this.dpDataEntrada);
        this.dpDataNascimento.getEditor().clear();
        this.dpDataEntrada.getEditor().clear();
        this.contentCadastrarIntegrante.getChildren().add(this.dpDataNascimento);
        this.contentCadastrarIntegrante.getChildren().add(this.dpDataEntrada);
        this.fldEmail.clear();
        this.fldNome.clear();
        this.fldEndereco.clear();
        this.fldTelefoneCelular.clear();
        this.fldTelefoneComercial.clear();
        this.fldTelefoneResidencial.clear();
        this.fldMinisterio.clear();
        this.radioFeminino.setSelected(false);
        this.radioMasculino.setSelected(false);
        this.lblIdade.setText(StringUtil.VAZIA);
        this.lblIdadeMinisterial.setText(StringUtil.VAZIA);
        this.lblFuncaoSecundaria.setDisable(true);
        this.lblFuncaoSecundaria.setOpacity(0.6);
        this.comboFuncaoSecundaria.setDisable(true);
        this.comboFuncaoSecundaria.setOpacity(0.6);
    }

    private boolean validarCampos() {
        boolean validadeDosCampos = true;
        if (StringUtil.isVazia(this.fldNome.getText())) {
            this.fldNome.setEffect(EfeitosUtil.getEfeitoInvalido());
            validadeDosCampos = false;
        }
        if (!this.radioFeminino.isSelected() && !this.radioMasculino.isSelected()) {
            this.radioFeminino.setEffect(EfeitosUtil.getEfeitoInvalido());
            this.radioMasculino.setEffect(EfeitosUtil.getEfeitoInvalido());
            validadeDosCampos = false;
        }
        if (this.comboFuncaoPrincipal.getValue() == null) {
            this.comboFuncaoPrincipal.setEffect(EfeitosUtil.getEfeitoInvalido());
            validadeDosCampos = false;
        }
        if (StringUtil.hasAlgo(this.fldEmail.getText()) && !RegexUtil.validarEmail(this.fldEmail.getText())) {
            validadeDosCampos = false;
        }
        return validadeDosCampos;
    }

    @FXML
    private void onActionFromDpDataEntrada(ActionEvent event) {
    }

    @FXML
    private void onActionFromDpDataNascimento(ActionEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblMinisterio(MouseEvent event) {
        this.fldMinisterio.requestFocus();
    }

    @FXML
    private void onActionFromFldMinisterio(ActionEvent event) {
    }

    @FXML
    private void onMouseClickedFromImgInicio(MouseEvent event) {
        FXMLDocumentController.getInstancia().iniciarPaginaInicial();
    }

    @FXML
    private void onActionFromBtnSalvar(ActionEvent event) {
        if (validarCampos()) {
            this.integranteRow.setNome(this.fldNome.getText());
            this.integranteRow.setDataNascimento(DataUtil.toDate(this.dpDataNascimento.getValue()));
            this.integranteRow.setEmail(this.fldEmail.getText().toLowerCase(new Locale("pt", "BR")));
            this.integranteRow.setDataEntrada(DataUtil.toDate(this.dpDataEntrada.getValue()));
            this.integranteRow.setSexo(this.radioFeminino.isSelected() ? Sexo.FEMININO : Sexo.MASCULINO);
            this.integranteRow.setTelefoneCelular(this.fldTelefoneCelular.getText());
            this.integranteRow.setTelefoneComercial(this.fldTelefoneComercial.getText());
            this.integranteRow.setTelefoneResidencial(this.fldTelefoneResidencial.getText());
            this.integranteRow.setEndereco(this.fldEndereco.getText());
            this.integranteRow.setFuncaoPrimaria(this.comboFuncaoPrincipal.getValue());
            this.integranteRow.setFuncaoSecundaria(this.comboFuncaoSecundaria.getValue());
            this.integranteRow.setMinisterio(this.fldMinisterio.getText());

            //Persistindo no banco
            IntegranteJpaController.getInstancia().create(this.integranteRow);

            if (this.integranteRow.getSexo().equals(Sexo.FEMININO)) {
                Dialogs.showInformationDialog(FXMLDocumentController.getInstancia().getStage(), "A Integrante foi salva com sucesso!", "Sucesso!", "Informação");
            } else {
                Dialogs.showInformationDialog(FXMLDocumentController.getInstancia().getStage(), "O Integrante foi salvo com sucesso!", "Sucesso!", "Informação");
            }

            TemplatePesquisaIntegranteController templatePesquisaIntegrante
                    = this.controladorPrincipal.getTemplatePesquisaIntegranteLoader().getController();

            templatePesquisaIntegrante.setTipoPagina(TipoPagina.PESQUISA_VISUALIZACAO_DADOS);
            templatePesquisaIntegrante.atualizar();

            final Parent root = (Parent) this.controladorPrincipal.getTemplatePesquisaIntegranteLoader().getRoot();

            //Limpa o conteúdo anterior e carrega a página
            AnchorPane pai = ((AnchorPane) this.contentCadastrarIntegrante.getParent());
            pai.getChildren().clear();
            pai.getChildren().add(root);
            EfeitosUtil.rodarEfeitoCarregamento(root);
        } else {
            Dialogs.showWarningDialog(FXMLDocumentController.getInstancia().getStage(), "Favor corrigir os campos assinalados.", "Campos Inválidos!", "Aviso");
        }
    }
}
