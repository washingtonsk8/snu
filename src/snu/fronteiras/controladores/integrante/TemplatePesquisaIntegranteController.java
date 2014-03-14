/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores.integrante;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
import org.apache.log4j.Logger;
import snu.controladores.IntegranteJpaController;
import snu.exceptions.NonexistentEntityException;
import snu.dto.ParametrosPesquisaIntegrante;
import snu.entidades.integrante.FuncaoIntegrante;
import snu.entidades.integrante.Integrante;
import snu.entidades.integrante.Sexo;
import snu.fronteiras.controladores.FXMLDocumentController;
import snu.geral.TipoPagina;
import snu.util.EfeitosUtil;

/**
 * Classe controladora do FXML
 *
 * @author Washington Luis
 */
public class TemplatePesquisaIntegranteController implements Initializable {

    @FXML
    private AnchorPane contentTemplatePesquisaIntegrante;
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
    private Font x2;
    @FXML
    private TableView<Integrante> tblIntegrantes;
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
    private Label lblTituloPagina;
    @FXML
    private Button btnLimpar;

    private ObservableList<Integrante> integrantes = FXCollections.observableArrayList();

    private final ObservableList<FuncaoIntegrante> funcoesIntegrante = FXCollections.observableArrayList(FuncaoIntegrante.values());

    private TipoPagina tipoPagina;

    //Inicializando o Logger
    private static final Logger log = Logger.getLogger(TemplatePesquisaIntegranteController.class.getName());

    private void initComponents() {

        this.tipoPagina = TipoPagina.PESQUISA_VISUALIZACAO_DADOS;
        this.clnNome.setCellValueFactory(new PropertyValueFactory<Integrante, String>("nome"));
        this.clnTelefoneResidencial.setCellValueFactory(new PropertyValueFactory<Integrante, String>("telefoneResidencial"));
        this.clnTelefoneCelular.setCellValueFactory(new PropertyValueFactory<Integrante, String>("telefoneCelular"));
        this.clnEmail.setCellValueFactory(new PropertyValueFactory<Integrante, String>("email"));
        this.clnFuncaoPrincipal.setCellValueFactory(new PropertyValueFactory<Integrante, String>("funcaoPrimaria"));

        this.comboFuncaoPrincipal.setItems(funcoesIntegrante);
        this.comboFuncaoPrincipal.getItems().remove(FuncaoIntegrante.NENHUMA);
    }

    private void carregarAtualizacaoIntegrante(Integrante integranteSelecionado) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/integrante/AtualizarIntegrante.fxml"));

        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException ex) {
            log.error("Erro ao carregar a tela de Atualização de Integrante", ex);
            Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(),
                    "Erro ao carregar a tela de Atualização de Integrante."
                    + "\nFavor entrar em contato com o Administrador.",
                    "Erro!", "Erro", ex);
        }

        AtualizarIntegranteController atualizarIntegranteController = fxmlLoader.getController();

        //Limpa o conteúdo anterior e carrega a página
        AnchorPane pai = ((AnchorPane) this.contentTemplatePesquisaIntegrante.getParent());
        atualizarIntegranteController.initData(integranteSelecionado, this);
        pai.getChildren().clear();
        pai.getChildren().add(root);
        EfeitosUtil.rodarEfeitoCarregamento(root);
    }

    private void carregarVisualizacaoIntegrante(Integrante integranteSelecionado) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/integrante/VisualizarIntegrante.fxml"));

        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException ex) {
            log.error("Erro ao carregar a tela de Visualização de Integrante", ex);
            Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(),
                    "Erro ao carregar a tela de Visualização de Integrante.\nFavor entrar em contato com o Administrador.",
                    "Erro!", "Erro", ex);
        }

        VisualizarIntegranteController visualizarIntegranteController = fxmlLoader.getController();

        //Limpa o conteúdo anterior e carrega a página
        AnchorPane pai = ((AnchorPane) this.contentTemplatePesquisaIntegrante.getParent());
        visualizarIntegranteController.initData(integranteSelecionado, this);
        pai.getChildren().clear();
        pai.getChildren().add(root);
        EfeitosUtil.rodarEfeitoCarregamento(root);
    }

    private void pesquisarPorParametros() {
        ParametrosPesquisaIntegrante parametrosPesquisa = new ParametrosPesquisaIntegrante();
        IntegranteJpaController integranteController = IntegranteJpaController.getInstancia();

        parametrosPesquisa.setNome(this.fldNome.getText());
        parametrosPesquisa.setFuncaoPrimaria(this.comboFuncaoPrincipal.getValue());

        this.integrantes = FXCollections.observableArrayList(integranteController.findByParametrosPesquisa(parametrosPesquisa));
        this.tblIntegrantes.setItems(this.integrantes);
    }

    private void removerIntegranteSelecionado(Integrante integranteSelecionado) {
        Dialogs.DialogResponse resposta;
        if (integranteSelecionado.getSexo().equals(Sexo.FEMININO)) {
            resposta = Dialogs.showConfirmDialog(FXMLDocumentController.getInstancia().getStage(), "Tem certeza que deseja excluir a Integrante?", "Exclusão de Integrante", "Confirmação");
        } else {
            resposta = Dialogs.showConfirmDialog(FXMLDocumentController.getInstancia().getStage(), "Tem certeza que deseja excluir o Integrante?", "Exclusão de Integrante", "Confirmação");
        }

        if (resposta.equals(Dialogs.DialogResponse.YES)) {
            IntegranteJpaController integranteController = IntegranteJpaController.getInstancia();
            try {
                integranteController.destroy(integranteSelecionado.getId());
                this.integrantes.remove(integranteSelecionado);
            } catch (NonexistentEntityException ex) {
                log.error("Erro ao remover o(a) Integrante", ex);
                Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(),
                        "Erro ao remover o(a) Integrante.\nFavor entrar em contato com o Administrador.",
                        "Erro!", "Erro", ex);
            }
            atualizarTabela();
        }
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
        pesquisarPorParametros();
    }

    @FXML
    private void onKeyTypedFromFldNome(KeyEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblFuncaoPrincipal(MouseEvent event) {
        this.comboFuncaoPrincipal.requestFocus();
    }

    @FXML
    private void onMouseClickedFromContentTemplatePesquisaIntegrante(MouseEvent event) {
        this.contentTemplatePesquisaIntegrante.requestFocus();
    }

    @FXML
    private void onActionFromComboFuncaoPrincipal(ActionEvent event) {
    }

    @FXML
    private void onKeyTypedFromComboFuncaoPrincipal(KeyEvent event) {
        if (event.getCharacter().equals("\n") || event.getCharacter().equals("\r")) {
            pesquisarPorParametros();
        }
    }

    @FXML
    private void onActionFromBtnLimpar(ActionEvent event) {
        this.fldNome.setText(null);
        this.comboFuncaoPrincipal.setValue(null);
    }

    @FXML
    private void onActionFromBtnPesquisar(ActionEvent event) {
        pesquisarPorParametros();
    }

    @FXML
    private void onMouseClickedFromTblIntegrantes(MouseEvent event) {
        this.tblIntegrantes.requestFocus();
        Integrante integranteSelecionado = this.tblIntegrantes.getSelectionModel().getSelectedItem();
        if (event.getClickCount() == 2 && integranteSelecionado != null) {
            switch (this.tipoPagina) {
                case PESQUISA_ATUALIZACAO_DADOS:
                    carregarAtualizacaoIntegrante(integranteSelecionado);
                    break;
                case PESQUISA_VISUALIZACAO_DADOS:
                    carregarVisualizacaoIntegrante(integranteSelecionado);
                    break;
                case PESQUISA_REMOCAO:
                    removerIntegranteSelecionado(integranteSelecionado);
                    break;
                default:
                    Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(), "Erro de processamento interno."
                            + "\nFavor entrar em contato com o administrador.", "Erro interno!", "Erro");
                    break;
            }
        }
    }

    public AnchorPane getContent() {
        return this.contentTemplatePesquisaIntegrante;
    }

    public TipoPagina getTipoPagina() {
        return tipoPagina;
    }

    public void setTipoPagina(TipoPagina tipoPagina) {
        this.tipoPagina = tipoPagina;
        switch (tipoPagina) {
            case PESQUISA_ATUALIZACAO_DADOS:
                this.lblTituloPagina.setText("Atualizar Dados");
                break;
            case PESQUISA_VISUALIZACAO_DADOS:
                this.lblTituloPagina.setText("Visualizar Dados");
                break;
            case PESQUISA_REMOCAO:
                this.lblTituloPagina.setText("Remover Integrante");
                break;
            default:
                Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(), "Erro de processamento interno."
                        + "\nFavor entrar em contato com o administrador.", "Erro interno!", "Erro");
                break;
        }
    }

    public void atualizar() {
        pesquisarPorParametros();
    }

    public void atualizarTabela() {
        final List<Integrante> itens = this.tblIntegrantes.getItems();
        if (itens == null || itens.isEmpty()) {
            return;
        }

        final Integrante item = this.tblIntegrantes.getItems().get(0);
        itens.remove(0);
        try {//Dorme um pouco para visualizarmos as alterações
            Thread.sleep(300);
        } catch (InterruptedException ex) {
            log.info("Erro ao colocar Thread para dormir por 300ms", ex);
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                itens.add(0, item);
            }
        });
    }
}
