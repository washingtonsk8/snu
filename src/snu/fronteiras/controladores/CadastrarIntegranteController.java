/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores;

import eu.schudt.javafx.controls.calendar.DatePicker;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.InputMethodRequests;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import snu.controladores.IntegranteJpaController;
import snu.entidades.FuncaoIntegrante;
import snu.entidades.Integrante;
import snu.entidades.Sexo;
import snu.util.DataUtil;

/**
 * FXML Controller class
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

    private DatePicker dpDataNascimento;

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

    private DatePicker dpDataEntrada;

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

    private Integrante integranteRow;
    @FXML
    private Label lblEmail;
    @FXML
    private TextField fldEmail;

    private final ObservableList<FuncaoIntegrante> funcoesIntegrante = FXCollections.observableArrayList(
            FuncaoIntegrante.BAIXISTA, FuncaoIntegrante.CANTOR, FuncaoIntegrante.GUITARRISTA_BASE,
            FuncaoIntegrante.GUITARRISTA_SOLO, FuncaoIntegrante.TECLADISTA,
            FuncaoIntegrante.VIOLINISTA, FuncaoIntegrante.VIOLONISTA);

    private void initComponents() {
        final ToggleGroup grupo = new ToggleGroup();
        
        this.radioFeminino.setToggleGroup(grupo);
        this.radioMasculino.setToggleGroup(grupo);
        
        this.fldNome.setPromptText("Nome Completo do Integrante");
        this.fldEndereco.setPromptText("Nome da Rua, N° - Bairro, Cidade");
        this.fldTelefoneResidencial.setPromptText("(__) ____-____");
        this.fldTelefoneCelular.setPromptText("(__) ____-____");
        this.fldTelefoneComercial.setPromptText("(__) ____-____");

        //Adicionando a lista no combo
        this.comboFuncaoPrincipal.setItems(this.funcoesIntegrante);
        this.comboFuncaoSecundaria.setItems(this.funcoesIntegrante);

        //Formatando o DatePicker de Nascimento
        this.dpDataNascimento = DataUtil.getDatePicker();
        this.dpDataNascimento.setLayoutX(185);
        this.dpDataNascimento.setLayoutY(160);
        this.dpDataNascimento.setMaxWidth(150);
        this.dpDataNascimento.setPromptText("DD/MM/AAAA");

        this.dpDataNascimento.selectedDateProperty().addListener(new ChangeListener<Date>() {
            @Override
            public void changed(ObservableValue<? extends Date> observable, Date valorAntigo, Date novaDataNascimento) {
                lblIdade.setText(DataUtil.getIdade(novaDataNascimento) + " anos");
            }
        });
        this.contentCadastrarIntegrante.getChildren().add(this.dpDataNascimento);

        //Formatando o DatePicker de Entrada
        this.dpDataEntrada = DataUtil.getDatePicker();
        this.dpDataEntrada.setLayoutX(185);
        this.dpDataEntrada.setLayoutY(400);
        this.dpDataEntrada.setMaxWidth(150);
        this.dpDataEntrada.setPromptText("DD/MM/AAAA");

        this.dpDataEntrada.selectedDateProperty().addListener(new ChangeListener<Date>() {
            @Override
            public void changed(ObservableValue<? extends Date> observable, Date valorAntigo, Date novaDataEntrada) {
                lblIdadeMinisterial.setText(DataUtil.getIdade(novaDataEntrada) + " anos de ministério");
            }
        });
        this.contentCadastrarIntegrante.getChildren().add(this.dpDataEntrada);

        //Define a existência de um novo cadastro
        this.integranteRow = new Integrante();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initComponents();
    }

    @FXML
    private void onMouseClickFromLblNome(MouseEvent event) {
        this.fldNome.requestFocus();
    }

    @FXML
    private void onActionFromFldNome(ActionEvent event) {
    }

    @FXML
    private void onMouseClickFromLblSexo(MouseEvent event) {
        this.radioFeminino.requestFocus();
    }

    @FXML
    private void onActionFromRadioFeminino(ActionEvent event) {
    }

    @FXML
    private void onActionFromRadioMasculino(ActionEvent event) {
    }

    @FXML
    private void onMouseClickFromDataNascimento(MouseEvent event) {
        this.dpDataNascimento.requestFocus();
    }

    @FXML
    private void onMouseClickFromLblEndereco(MouseEvent event) {
        this.fldEndereco.requestFocus();
    }

    @FXML
    private void onActionFromFldEndereco(ActionEvent event) {
    }

    @FXML
    private void onMouseClickFromLblTelefoneResidencial(MouseEvent event) {
        this.fldTelefoneResidencial.requestFocus();
    }

    @FXML
    private void onActionFromFldTelefoneResidencial(ActionEvent event) {
    }

    @FXML
    private void onMouseClickFromLblTelefoneCelular(MouseEvent event) {
        this.fldTelefoneCelular.requestFocus();
    }

    @FXML
    private void onActionFromFldTelefoneCelular(ActionEvent event) {
    }

    @FXML
    private void onMouseClickFromLblTelefoneComercial(MouseEvent event) {
        this.fldTelefoneComercial.requestFocus();
    }

    @FXML
    private void onActionFromFldTelefoneComercial(ActionEvent event) {
    }

    @FXML
    private void onMouseClickFromLblEmail(MouseEvent event) {
        this.fldEmail.requestFocus();
    }

    @FXML
    private void onActionFromFldEmail(ActionEvent event) {
    }

    @FXML
    private void onMouseClickFromIdadeMinisterial(MouseEvent event) {

    }

    @FXML
    private void onMouseClickFromDataEntrada(MouseEvent event) {
        this.dpDataEntrada.requestFocus();
    }

    @FXML
    private void onMouseClickFromLblFuncaoPrincipal(MouseEvent event) {
        this.comboFuncaoPrincipal.requestFocus();
    }

    @FXML
    private void onMouseClickFromLblFuncaoSecundaria(MouseEvent event) {
        this.comboFuncaoSecundaria.requestFocus();
    }

    @FXML
    private void onActionFromComboFuncaoPrincipal(ActionEvent event) {
    }

    @FXML
    private void onActionFromComboFuncaoSecundaria(ActionEvent event) {
    }

    @FXML
    private void onActionFromBtnSalvar(ActionEvent event) {
        this.integranteRow.setNome(this.fldNome.getText());
        this.integranteRow.setDataNascimento(this.dpDataNascimento.getSelectedDate());
        this.integranteRow.setEmail(this.fldEmail.getText());
        this.integranteRow.setDataEntrada(this.dpDataEntrada.getSelectedDate());
        this.integranteRow.setSexo(this.radioFeminino.isSelected() ? Sexo.FEMININO : Sexo.MASCULINO);
        this.integranteRow.setTelefoneCelular(this.fldTelefoneCelular.getText());
        this.integranteRow.setTelefoneComercial(this.fldTelefoneComercial.getText());
        this.integranteRow.setTelefoneResidencial(this.fldTelefoneResidencial.getText());
        this.integranteRow.setEndereco(this.fldEndereco.getText());
        this.integranteRow.setFuncaoPrimaria(this.comboFuncaoPrincipal.getValue());
        this.integranteRow.setFuncaoSecundaria(this.comboFuncaoSecundaria.getValue());
                
        //Persistindo no banco
        IntegranteJpaController.getInstance().create(integranteRow);
        System.out.println(integranteRow);
    }

    @FXML
    private void onMouseClickFromContentCadastrarIntegrante(MouseEvent event) {
        this.contentCadastrarIntegrante.requestFocus();
    }

}
