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
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import org.apache.log4j.Logger;
import snu.controladores.IntegranteJpaController;
import snu.dto.ParametrosPesquisaIntegrante;
import snu.entidades.integrante.FuncaoIntegrante;
import snu.entidades.integrante.Integrante;
import snu.entidades.integrante.Sexo;
import snu.exceptions.NonexistentEntityException;
import snu.fronteiras.controladores.FXMLDocumentController;
import snu.fronteiras.controladores.HomeController;
import snu.util.BotoesImagemUtil;
import snu.util.Dialogs;
import snu.util.EfeitosUtil;

/**
 * Classe controladora do FXML
 *
 * @author Washington Luis
 */
public class PesquisarIntegranteController implements Initializable {

    @FXML
    private AnchorPane contentPesquisarIntegrante;
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
    private Button btnLimpar;
    @FXML
    private ImageView imgInicio;
    @FXML
    private ImageView iconeLimpar;
    @FXML
    private ImageView iconePesquisar;
    @FXML
    private Label lblPesquisarIntegrante;
    @FXML
    private ImageView iconeCriarNovo;
    @FXML
    private Button btnCriarNovo;

    private final ObservableList<FuncaoIntegrante> funcoesIntegrante
            = FXCollections.observableArrayList(FuncaoIntegrante.values());

    //Inicializando o Logger
    private static final Logger log = Logger.getLogger(PesquisarIntegranteController.class.getName());

    private void initComponents() {

        this.clnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        this.clnTelefoneResidencial.setCellValueFactory(new PropertyValueFactory<>("telefoneResidencial"));
        this.clnTelefoneCelular.setCellValueFactory(new PropertyValueFactory<>("telefoneCelular"));
        this.clnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        this.clnFuncaoPrincipal.setCellValueFactory(new PropertyValueFactory<>("funcaoPrimaria"));

        this.tblIntegrantes.setRowFactory(
                new Callback<TableView<Integrante>, TableRow<Integrante>>() {
                    @Override
                    public TableRow<Integrante> call(TableView<Integrante> tableView) {
                        final TableRow<Integrante> linha = new TableRow<>();
                        final ContextMenu menuContexto = new ContextMenu();

                        ImageView iconeVisualizar = new ImageView("/snu/fronteiras/images/icons/iconeVisualizar.png");
                        iconeVisualizar.setFitHeight(10.);
                        iconeVisualizar.setFitWidth(15.);
                        MenuItem itemVisualizarIntegrante = new MenuItem("Visualizar", iconeVisualizar);
                        itemVisualizarIntegrante.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
                                carregarVisualizacaoIntegrante(linha.itemProperty().get());
                            }
                        });

                        ImageView iconeAtualizar = new ImageView("/snu/fronteiras/images/icons/iconeEditar.png");
                        iconeAtualizar.setFitHeight(12.);
                        iconeAtualizar.setFitWidth(12.);
                        MenuItem itemAtualizarIntegrante = new MenuItem("Atualizar", iconeAtualizar);
                        itemAtualizarIntegrante.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
                                carregarAtualizacaoIntegrante(linha.itemProperty().get());
                            }
                        });

                        ImageView iconeRemover = new ImageView("/snu/fronteiras/images/icons/iconeRemover.png");
                        iconeRemover.setFitHeight(12.);
                        iconeRemover.setFitWidth(12.);
                        MenuItem itemRemoverIntegrante = new MenuItem("Remover", iconeRemover);
                        itemRemoverIntegrante.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
                                removerIntegranteSelecionado(linha.itemProperty().get());
                            }
                        });

                        menuContexto.getItems().addAll(itemVisualizarIntegrante,
                                itemAtualizarIntegrante, itemRemoverIntegrante);

                        //Seleciona antes de mostrar o menu de contexto
                        menuContexto.setOnShowing(new EventHandler<WindowEvent>() {
                            @Override
                            public void handle(WindowEvent event) {
                                tblIntegrantes.getSelectionModel().select(linha.getItem());
                            }
                        });

                        //Somente exibe o menu para os valores não-nulos
                        linha.contextMenuProperty().bind(
                                Bindings.when(Bindings.isNotNull(linha.itemProperty()))
                                .then(menuContexto)
                                .otherwise((ContextMenu) null));
                        return linha;
                    }
                });

        BotoesImagemUtil.definirComportamento(this.imgInicio);

        this.comboFuncaoPrincipal.setItems(funcoesIntegrante);
        this.comboFuncaoPrincipal.getItems().remove(FuncaoIntegrante.NENHUMA);
    }

    private void carregarCadastroIntegrante() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/integrante/CadastrarIntegrante.fxml"));
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException ex) {
            log.error("Erro ao carregar tela de Cadastro de Integrante", ex);
            Dialogs.showErrorDialog(HomeController.getInstancia().getStage(), "Erro ao carregar tela de Cadastro de Integrante."
                    + "\nFavor entrar em contato com o Administrador.",
                    "Erro!", "Erro", ex);
        }

        CadastrarIntegranteController cadastrarIntegranteController = fxmlLoader.getController();

        //Limpa o conteúdo anterior e carrega a página
        AnchorPane pai = ((AnchorPane) this.contentPesquisarIntegrante.getParent());
        cadastrarIntegranteController.initData(this);
        pai.getChildren().clear();
        pai.getChildren().add(root);
        EfeitosUtil.rodarEfeitoCarregamentoFadeIn(root);
    }

    private void carregarAtualizacaoIntegrante(Integrante integranteSelecionado) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/integrante/AtualizarIntegrante.fxml"));

        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException ex) {
            log.error("Erro ao carregar a tela de Atualização de Integrante", ex);
            Dialogs.showErrorDialog(HomeController.getInstancia().getStage(),
                    "Erro ao carregar a tela de Atualização de Integrante."
                    + "\nFavor entrar em contato com o Administrador.",
                    "Erro!", "Erro", ex);
        }

        AtualizarIntegranteController atualizarIntegranteController = fxmlLoader.getController();

        //Limpa o conteúdo anterior e carrega a página
        AnchorPane pai = ((AnchorPane) this.contentPesquisarIntegrante.getParent());
        atualizarIntegranteController.initData(integranteSelecionado, this);
        pai.getChildren().clear();
        pai.getChildren().add(root);
        EfeitosUtil.rodarEfeitoCarregamentoFadeIn(root);
    }

    private void carregarVisualizacaoIntegrante(Integrante integranteSelecionado) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/integrante/VisualizarIntegrante.fxml"));

        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException ex) {
            log.error("Erro ao carregar a tela de Visualização de Integrante", ex);
            Dialogs.showErrorDialog(HomeController.getInstancia().getStage(),
                    "Erro ao carregar a tela de Visualização de Integrante.\nFavor entrar em contato com o Administrador.",
                    "Erro!", "Erro", ex);
        }

        VisualizarIntegranteController visualizarIntegranteController = fxmlLoader.getController();

        //Limpa o conteúdo anterior e carrega a página
        AnchorPane pai = ((AnchorPane) this.contentPesquisarIntegrante.getParent());
        visualizarIntegranteController.initData(integranteSelecionado, this);
        pai.getChildren().clear();
        pai.getChildren().add(root);
        EfeitosUtil.rodarEfeitoCarregamentoFadeIn(root);
    }

    private void pesquisarPorParametros() {
        ParametrosPesquisaIntegrante parametrosPesquisa = new ParametrosPesquisaIntegrante();
        IntegranteJpaController integranteController = IntegranteJpaController.getInstancia();

        parametrosPesquisa.setNome(this.fldNome.getText());
        parametrosPesquisa.setFuncaoPrimaria(this.comboFuncaoPrincipal.getValue());

        this.tblIntegrantes.setItems(FXCollections.observableArrayList(integranteController.findByParametrosPesquisa(parametrosPesquisa)));
        atualizarTabela();
    }

    private void removerIntegranteSelecionado(Integrante integranteSelecionado) {
        Dialogs.DialogResponse resposta;
        if (integranteSelecionado.getSexo().equals(Sexo.FEMININO)) {
            resposta = Dialogs.showConfirmDialog(HomeController.getInstancia().getStage(),
                    "Deseja realmente excluir a Integrante \"" + integranteSelecionado.getPrimeiroNome() + "\"?",
                    "Exclusão de Integrante", "Confirmação");
        } else {
            resposta = Dialogs.showConfirmDialog(HomeController.getInstancia().getStage(),
                    "Deseja realmente excluir o Integrante \"" + integranteSelecionado.getPrimeiroNome() + "\"?",
                    "Exclusão de Integrante", "Confirmação");
        }

        if (resposta.equals(Dialogs.DialogResponse.YES)) {
            IntegranteJpaController integranteController = IntegranteJpaController.getInstancia();
            try {
                integranteController.destroy(integranteSelecionado.getId());
                Dialogs.showInformationDialog(HomeController.getInstancia().getStage(),
                        "O(A) Integrante foi removido(a) com sucesso!", "Sucesso!", "Informação");
                this.tblIntegrantes.getItems().remove(integranteSelecionado);
                atualizarTabela();
            } catch (NonexistentEntityException ex) {
                log.error("Erro ao excluir o(a) Integrante", ex);
                Dialogs.showErrorDialog(HomeController.getInstancia().getStage(),
                        "Erro ao excluir o(a) Integrante.\nFavor entrar em contato com o Administrador.",
                        "Erro!", "Erro", ex);
            }
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
    private void onMouseClickedFromContentPesquisarIntegrante(MouseEvent event) {
        this.contentPesquisarIntegrante.requestFocus();
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
    private void onActionFromBtnCriarNovo(ActionEvent event) {
        carregarCadastroIntegrante();
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
    }

    public AnchorPane getContent() {
        return this.contentPesquisarIntegrante;
    }

    @FXML
    private void onMouseClickedFromImgInicio(MouseEvent event) {
        FXMLDocumentController.getInstancia().iniciarPaginaInicial();
    }

    public void atualizar() {
        pesquisarPorParametros();
    }

    private void atualizarTabela() {
        final List<Integrante> itens = this.tblIntegrantes.getItems();
        if (itens == null || itens.isEmpty()) {
            return;
        }

        final Integrante item = this.tblIntegrantes.getItems().get(0);
        itens.remove(0);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                itens.add(0, item);
            }
        });
    }
}
