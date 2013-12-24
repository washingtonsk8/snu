/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import snu.controladores.IntegranteJpaController;
import snu.dto.ParametrosPesquisaIntegrante;
import snu.entidades.FuncaoIntegrante;
import snu.entidades.Integrante;

/**
 * FXML Controller class
 *
 * @author Washington Luis
 */
public class VisualizarDadosIntegranteController implements Initializable {

    @FXML
    private AnchorPane contentVisualizarDadosIntegrante;
    @FXML
    private Label lblNome;
    @FXML
    private Font x1;
    @FXML
    private TextField fldNome;
    @FXML
    private Label lblFuncaoPrincipal;
    @FXML
    private ComboBox<FuncaoIntegrante> comboFuncaoPrincipal;
    @FXML
    private Button btnPesquisar;

    private Effect invalidEffect = new DropShadow(BlurType.GAUSSIAN, Color.RED, 15, 0.0, 0, 0);
    private Effect validEffect = new DropShadow(BlurType.GAUSSIAN, Color.GREEN, 15, 0.0, 0, 0);
    @FXML
    private TableColumn<Integrante, String> clnNome;
    @FXML
    private TableColumn<Integrante, String> clnTelefoneResidencial;
    @FXML
    private TableColumn<Integrante, String> clnTelefoneCelular;
    @FXML
    private TableColumn<Integrante, String> clnEmail;
    @FXML
    private TableColumn<Integrante, String> clnFuncaoPrincipal;
    @FXML
    private TableView<Integrante> tblIntegrantes;

    private ObservableList<Integrante> integrantes = FXCollections.observableArrayList();

    private final ObservableList<FuncaoIntegrante> funcoesIntegrante = FXCollections.observableArrayList(
            FuncaoIntegrante.BAIXISTA, FuncaoIntegrante.CANTOR, FuncaoIntegrante.GUITARRISTA_BASE,
            FuncaoIntegrante.GUITARRISTA_SOLO, FuncaoIntegrante.TECLADISTA,
            FuncaoIntegrante.VIOLINISTA, FuncaoIntegrante.VIOLONISTA);

    private void initComponents() {

        this.tblIntegrantes.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean valorAntigo, Boolean novoValor) {
                if (!novoValor) {//Perda de foco
                    
                }
            }
        });
        this.clnNome.setCellValueFactory(new PropertyValueFactory<Integrante, String>("nome"));
        this.clnTelefoneResidencial.setCellValueFactory(new PropertyValueFactory<Integrante, String>("telefoneResidencial"));
        this.clnTelefoneCelular.setCellValueFactory(new PropertyValueFactory<Integrante, String>("telefoneCelular"));
        this.clnEmail.setCellValueFactory(new PropertyValueFactory<Integrante, String>("email"));
        this.clnFuncaoPrincipal.setCellValueFactory(new PropertyValueFactory<Integrante, String>("funcaoPrimaria"));

        this.comboFuncaoPrincipal.setItems(funcoesIntegrante);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initComponents();
    }

    private void carregarVisualizacaoIntegrante(Integrante integranteSelecionado) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/VisualizarIntegrante.fxml"));

        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        VisualizarIntegranteController visualizarIntegranteController = fxmlLoader.getController();

        //Limpa o conteúdo anterior e carrega a página
        AnchorPane pai = ((AnchorPane) this.contentVisualizarDadosIntegrante.getParent());
        visualizarIntegranteController.initData(integranteSelecionado, this.contentVisualizarDadosIntegrante);
        pai.getChildren().clear();
        pai.getChildren().add(root);
    }

    @FXML
    private void onMouseClickedFromLblNome(MouseEvent event) {
        this.fldNome.requestFocus();
    }

    @FXML
    private void onActionFromFldNome(ActionEvent event) {
    }

    @FXML
    private void onKeyTypedFromFldNome(KeyEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblFuncaoPrincipal(MouseEvent event) {
        this.comboFuncaoPrincipal.requestFocus();
    }

    @FXML
    private void onActionFromComboFuncaoPrincipal(ActionEvent event) {
    }

    @FXML
    private void onActionFromBtnPesquisar(ActionEvent event) {
        ParametrosPesquisaIntegrante parametrosPesquisa = new ParametrosPesquisaIntegrante();
        IntegranteJpaController integranteController = IntegranteJpaController.getInstancia();

        parametrosPesquisa.setNome(this.fldNome.getText());
        parametrosPesquisa.setFuncaoPrimaria(this.comboFuncaoPrincipal.getValue());

        integrantes = FXCollections.observableArrayList(integranteController.findByParametrosPesquisa(parametrosPesquisa));
        tblIntegrantes.setItems(integrantes);
    }

    @FXML
    private void onMouseClickedFromContentVisualizarDadosIntegrante(MouseEvent event) {
        this.contentVisualizarDadosIntegrante.requestFocus();
    }

    @FXML
    private void onMouseClickedFromTblIntegrantes(MouseEvent event) {
        Integrante integranteSelecionado = this.tblIntegrantes.getSelectionModel().getSelectedItem();
        if (event.getClickCount() == 2 && integranteSelecionado != null) {
            carregarVisualizacaoIntegrante(integranteSelecionado);
        }
    }

}
