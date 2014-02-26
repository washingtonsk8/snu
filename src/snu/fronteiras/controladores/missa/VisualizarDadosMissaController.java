/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores.missa;

import eu.schudt.javafx.controls.calendar.DatePicker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.util.Callback;
import snu.controladores.MissaJpaController;
import snu.dto.ParametrosPesquisaMissa;
import snu.entidades.integrante.Integrante;
import snu.entidades.missa.Missa;
import snu.util.DataUtil;

/**
 * FXML Controller class
 *
 * @author Washington Luis
 */
public class VisualizarDadosMissaController implements Initializable {

    @FXML
    private AnchorPane contentVisualizarDadosMissa;
    @FXML
    private Label lblVisualizarDadosMissa;
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
    @FXML
    private TextField fldNomeMissa;

    private DatePicker dpDataMissa;

    private ObservableList<Missa> missas = FXCollections.observableArrayList();

    private void initComponents() {
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
                return new SimpleStringProperty(associacao.getValue().getNome());
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
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initComponents();
    }
    
    @FXML
    private void onMouseClickedFromTblMissas(MouseEvent event) {
        if(event.getClickCount() == 2){
            
        }
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
    private void onActionFromBtnPesquisar(ActionEvent event) {
        pesquisarPorParametros();
    }

}
