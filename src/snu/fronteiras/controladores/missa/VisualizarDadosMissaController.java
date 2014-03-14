/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores.missa;

import eu.schudt.javafx.controls.calendar.DatePicker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Window;
import javafx.util.Callback;
import javafx.util.Duration;
import snu.controladores.MissaJpaController;
import snu.dto.ParametrosPesquisaMissa;
import snu.entidades.missa.Missa;
import snu.fronteiras.controladores.FXMLDocumentController;
import snu.util.DataUtil;
import snu.util.EfeitosUtil;
import snu.util.StringUtil;

/**
 * Classe controladora do FXML
 *
 * @author Washington Luis
 */
public class VisualizarDadosMissaController implements Initializable {

    @FXML
    private AnchorPane contentVisualizarDadosMissa;
    @FXML
    private Label lblVisualizarDadosMissa;
    @FXML
    private TextField fldNomeMissa;
    @FXML
    private TableView<Missa> tblMissas;
    @FXML
    private TableColumn<Missa, String> clnNomeMissa;
    @FXML
    private TableColumn<Missa, String> clnDataAcontecimento;
    @FXML
    private Button btnPesquisar;
    @FXML
    private Font x1;
    @FXML
    private Label lblNomeMissa;
    @FXML
    private Font x2;
    @FXML
    private Label lblDataAcontecimentoMissa;

    private DatePicker dpDataMissa;

    private ObservableList<Missa> missas = FXCollections.observableArrayList();

    private Popup popup;
    @FXML
    private Button btnLimpar;

    private void initComponents() {
        this.popup = new Popup();
        this.popup.setAutoHide(true);

        //Formatando o DatePicker de Acontecimento
        this.dpDataMissa = DataUtil.getDatePicker();
        this.dpDataMissa.setLayoutX(200);
        this.dpDataMissa.setLayoutY(90);
        this.dpDataMissa.setMaxWidth(150);
        this.dpDataMissa.setPromptText("DD/MM/AAAA");
        this.contentVisualizarDadosMissa.getChildren().add(this.dpDataMissa);

        this.clnNomeMissa.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Missa, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Missa, String> associacao) {
                String nomeMissa = associacao.getValue().getNome();
                return new SimpleStringProperty(StringUtil.hasAlgo(nomeMissa) ? nomeMissa : "Indefinido");
            }
        });

        this.clnDataAcontecimento.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Missa, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Missa, String> associacao) {
                return new SimpleStringProperty(DataUtil.formatarData(associacao.getValue().getDataAcontecimento()));
            }
        });
    }

    private void pesquisarPorParametros() {
        ParametrosPesquisaMissa parametrosPesquisa = new ParametrosPesquisaMissa();

        parametrosPesquisa.setNomeMissa(this.fldNomeMissa.getText());
        parametrosPesquisa.setDataAcontecimento(this.dpDataMissa.getSelectedDate());

        this.missas = FXCollections.observableArrayList(MissaJpaController.getInstancia().
                findByParametrosPesquisa(parametrosPesquisa));
        this.tblMissas.setItems(this.missas);
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
    private void onMouseClickedFromLblNomeMissa(MouseEvent event) {
        this.fldNomeMissa.requestFocus();
    }

    @FXML
    private void onMouseClickedFromLblDataAcontecimentoMissa(MouseEvent event) {
        this.dpDataMissa.requestFocus();
    }

    @FXML
    private void onMouseClickedFromContentVisualizarDadosMissa(MouseEvent event) {
        this.contentVisualizarDadosMissa.requestFocus();
    }

    @FXML
    private void onActionFromFldNomeMissa(ActionEvent event) {
        pesquisarPorParametros();
    }

    @FXML
    private void onMouseClickedFromTblMissas(MouseEvent event) {
        if (event.getClickCount() == 2) {
            Missa missaSelecionada = this.tblMissas.getSelectionModel().getSelectedItem();

            if (StringUtil.hasAlgo(missaSelecionada.getDescricaoEmail())) {
                TextArea areaApresentacaoMissa = new TextArea(missaSelecionada.getDescricaoEmail());
                areaApresentacaoMissa.setMinWidth(500);
                areaApresentacaoMissa.setPrefHeight(350);
                areaApresentacaoMissa.setEditable(false);
                areaApresentacaoMissa.setEffect(EfeitosUtil.getEfeitoGeral());
                Window proprietaria = ((Node) (event.getSource())).getScene().getWindow();

                this.popup.getContent().clear();
                this.popup.getContent().add(areaApresentacaoMissa);

                FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.6), areaApresentacaoMissa);
                fadeIn.setFromValue(0);
                fadeIn.setToValue(1);
                fadeIn.playFromStart();

                this.popup.show(this.tblMissas, proprietaria.getX() + 150, proprietaria.getY() + 250);
            } else {
                Dialogs.showWarningDialog(FXMLDocumentController.getInstancia().getStage(),
                        "A descrição de e-mail desta Missa está vazia.", "Descrição Vazia!", "Aviso");
            }
        }
    }

    @FXML
    private void onActionFromBtnLimpar(ActionEvent event) {
        this.fldNomeMissa.setText(null);
        this.dpDataMissa.setSelectedDate(null);
    }

    @FXML
    private void onActionFromBtnPesquisar(ActionEvent event) {
        pesquisarPorParametros();
    }

    public void atualizar() {
        pesquisarPorParametros();
    }
}
