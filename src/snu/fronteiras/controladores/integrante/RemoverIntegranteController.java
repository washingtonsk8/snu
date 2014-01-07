/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores.integrante;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import snu.controladores.IntegranteJpaController;
import snu.controladores.exceptions.NonexistentEntityException;
import snu.dto.ParametrosPesquisaIntegrante;
import snu.entidades.integrante.FuncaoIntegrante;
import snu.entidades.integrante.Integrante;
import snu.entidades.integrante.Sexo;

/**
 * FXML Controller class
 *
 * @author Washington Luis
 */
public class RemoverIntegranteController implements Initializable {

    @FXML
    private AnchorPane contentRemoverIntegrante;
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
    @FXML
    private Button btnRemover;

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
     * Inicializa a classe de controle.
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

        this.integrantes = FXCollections.observableArrayList(integranteController.findByParametrosPesquisa(parametrosPesquisa));
        this.tblIntegrantes.setItems(this.integrantes);
    }

    @FXML
    private void onActionFromBtnRemover(ActionEvent event) {
        Integrante integranteSelecionado = this.tblIntegrantes.getSelectionModel().getSelectedItem();

        if (integranteSelecionado != null) {
            Dialogs.DialogResponse resposta;
            if (integranteSelecionado.getSexo().equals(Sexo.FEMININO)) {
                resposta = Dialogs.showConfirmDialog(null, "Tem certeza que deseja excluir a Integrante?", "Exclusão de Integrante", "Confirmação");
            } else {
                resposta = Dialogs.showConfirmDialog(null, "Tem certeza que deseja excluir o Integrante?", "Exclusão de Integrante", "Confirmação");
            }

            if (resposta.equals(Dialogs.DialogResponse.YES)) {
                IntegranteJpaController integranteController = IntegranteJpaController.getInstancia();
                try {
                    integranteController.destroy(integranteSelecionado.getId());
                    this.integrantes.remove(integranteSelecionado);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(RemoverIntegranteController.class.getName()).log(Level.SEVERE, null, ex);
                }
                atualizarTabela();
            }
        } else {
            Dialogs.showWarningDialog(null, "Favor selecionar um(a) Integrante para a exclusão", "Integrante não selecionado(a)", "Aviso");
        }
    }

    @FXML
    private void onMouseClickedFromContentRemoverIntegrante(MouseEvent event) {
    }

    private void onMouseClickedFromContentVisualizarDadosIntegrante(MouseEvent event) {
        this.contentRemoverIntegrante.requestFocus();
    }

    @FXML
    private void onMouseClickedFromTblIntegrantes(MouseEvent event) {
    }

    private void atualizarTabela() {
        final List<Integrante> itens = this.tblIntegrantes.getItems();
        if (itens == null || itens.isEmpty()) {
            return;
        }

        final Integrante item = this.tblIntegrantes.getItems().get(0);
        itens.remove(0);
        try {//Dorme um pouco para visualizarmos as alterações
            Thread.sleep(300);
        } catch (InterruptedException ex) {
            Logger.getLogger(RemoverIntegranteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                itens.add(0, item);
            }
        });
    }

}
