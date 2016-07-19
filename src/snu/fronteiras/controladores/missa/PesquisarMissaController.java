/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores.missa;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javafx.util.Duration;
import org.apache.log4j.Logger;
import org.controlsfx.control.PopOver;
import snu.controladores.MissaJpaController;
import snu.dto.ParametrosPesquisaMissa;
import snu.entidades.missa.Missa;
import snu.entidades.musica.Musica;
import snu.exceptions.NonexistentEntityException;
import snu.fronteiras.controladores.FXMLDocumentController;
import snu.fronteiras.controladores.HomeController;
import snu.fronteiras.controladores.missa.popups.MarcarImpressaoMassaController;
import snu.util.BotoesImagemUtil;
import snu.util.DataUtil;
import snu.util.Dialogs;
import snu.util.EfeitosUtil;
import snu.util.StringUtil;

/**
 * Classe controladora do FXML
 *
 * @author Washington Luis
 */
public class PesquisarMissaController implements Initializable {

    @FXML
    private AnchorPane contentPesquisarMissa;
    @FXML
    private Label lblPesquisarMissa;
    @FXML
    private TextField fldNomeMissa;
    @FXML
    private TableView<Missa> tblMissas;
    @FXML
    private TableColumn<Missa, String> clnNomeMissa;
    @FXML
    private TableColumn<Missa, String> clnDataAcontecimento;
    @FXML
    private TableColumn<Missa, String> clnCriacao;
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
    private Button btnLimpar;
    @FXML
    private ImageView imgInicio;
    @FXML
    private DatePicker dpDataAcontecimento;
    @FXML
    private ImageView iconeLimpar;
    @FXML
    private ImageView iconePesquisar;
    @FXML
    private Button btnMontarMissa;
    @FXML
    private Font x11;
    @FXML
    private ImageView iconeMontarMissa;
    @FXML
    private TextArea areaApresentacaoMissa;

    private PopOver popup;

    private ObservableList<Missa> missas = FXCollections.observableArrayList();

    //Inicializando o Logger
    private static final Logger log = Logger.getLogger(PesquisarMissaController.class.getName());

    private static final int LARGURA_POPUP = 550;
    private static final int ALTURA_POPUP = 350;
    private static final int DESLOCAMENTO_X_POPUP = 200;
    private static final int DESLOCAMENTO_Y_POPUP = 60;

    private void initComponents() {
        this.popup = new PopOver();
        this.popup.setAutoHide(true);

        BotoesImagemUtil.definirComportamento(this.imgInicio);

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

        this.clnCriacao.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Missa, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Missa, String> missa) {
                return new SimpleStringProperty(DataUtil.formatarDataHora(missa.getValue().getDataCriacao()));
            }
        });

        this.tblMissas.setRowFactory(
                new Callback<TableView<Missa>, TableRow<Missa>>() {
                    @Override
                    public TableRow<Missa> call(TableView<Missa> tableView) {
                        final TableRow<Missa> linha = new TableRow<>();
                        final ContextMenu menuContexto = new ContextMenu();

                        ImageView iconeVisualizar = new ImageView("/snu/fronteiras/images/icons/iconeVisualizar.png");
                        iconeVisualizar.setFitHeight(10.);
                        iconeVisualizar.setFitWidth(15.);
                        MenuItem itemVisualizarMissa = new MenuItem("Visualizar", iconeVisualizar);
                        itemVisualizarMissa.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
                                carregarVisualizacaoMissa(linha.itemProperty().get(),
                                        linha.getLayoutX(),
                                        linha.getLayoutY());
                            }
                        });

                        ImageView iconeOk = new ImageView("/snu/fronteiras/images/icons/iconeOk.png");
                        iconeOk.setFitHeight(10.);
                        iconeOk.setFitWidth(15.);
                        MenuItem itemMarcarEmLote = new MenuItem("Marcar Impressões em Lote", iconeOk);
                        itemMarcarEmLote.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {

                                List<Musica> musicasNaoImpressas
                                = new ArrayList<>(linha.itemProperty().get().getMusicasUtilizadas());
                                int i = 0;
                                while (i < musicasNaoImpressas.size()) {
                                    if (musicasNaoImpressas.get(i).isImpressa()) {
                                        musicasNaoImpressas.remove(i);
                                    } else {
                                        i++;
                                    }
                                }

                                if (!musicasNaoImpressas.isEmpty()) {
                                    carregarMarcacaoImpressaoEmMassa(musicasNaoImpressas);
                                } else {
                                    Dialogs.showWarningDialog(HomeController.getInstancia().getStage(),
                                            "Todas as músicas da Missa selecionada já estão marcadas como impressas.",
                                            "Aviso!", "Aviso");
                                }
                            }
                        });

                        ImageView iconeRemover = new ImageView("/snu/fronteiras/images/icons/iconeRemover.png");
                        iconeRemover.setFitHeight(12.);
                        iconeRemover.setFitWidth(12.);
                        MenuItem itemRemoverMissa = new MenuItem("Remover", iconeRemover);
                        itemRemoverMissa.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
                                removerMissaSelecionada(linha.itemProperty().get());
                            }
                        });

                        menuContexto.getItems().addAll(itemVisualizarMissa, itemRemoverMissa, itemMarcarEmLote);

                        //Seleciona antes de mostrar o menu de contexto
                        menuContexto.setOnShowing(new EventHandler<WindowEvent>() {
                            @Override
                            public void handle(WindowEvent event) {
                                tblMissas.getSelectionModel().select(linha.getItem());
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
    }

    private void pesquisarPorParametros() {
        ParametrosPesquisaMissa parametrosPesquisa = new ParametrosPesquisaMissa();

        parametrosPesquisa.setNomeMissa(this.fldNomeMissa.getText());
        parametrosPesquisa.setDataAcontecimento(DataUtil.toDate(this.dpDataAcontecimento.getValue()));

        this.missas = FXCollections.observableArrayList(MissaJpaController.getInstancia().
                findByParametrosPesquisa(parametrosPesquisa)).sorted(new Comparator<Missa>() {

                    @Override
                    public int compare(Missa m1, Missa m2) {
                        return m2.getDataAcontecimento().compareTo(m1.getDataAcontecimento());
                    }
                });
        this.tblMissas.setItems(this.missas);
    }

    private void carregarVisualizacaoMissa(Missa missaSelecionada, double posicaoLinhaX, double posicaoLinhaY) {

        if (StringUtil.hasAlgo(missaSelecionada.getDescricaoEmail())) {
            this.areaApresentacaoMissa.setText(missaSelecionada.getDescricaoEmail());
            this.areaApresentacaoMissa.setPrefWidth(LARGURA_POPUP);
            this.areaApresentacaoMissa.setPrefHeight(ALTURA_POPUP);
            this.areaApresentacaoMissa.setEditable(true);
            this.areaApresentacaoMissa.setVisible(true);

            Window proprietaria = HomeController.getInstancia().getStage().getScene().getWindow();

            this.popup.setContentNode(this.areaApresentacaoMissa);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.6), areaApresentacaoMissa);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.playFromStart();

            double posicaoX = posicaoLinhaX + proprietaria.getX() + this.tblMissas.getLayoutX() + DESLOCAMENTO_X_POPUP;
            double posicaoY = posicaoLinhaY + proprietaria.getY() + this.tblMissas.getLayoutY() + DESLOCAMENTO_Y_POPUP;

            if (posicaoY + ALTURA_POPUP > proprietaria.getY() + proprietaria.getHeight()) {
                this.areaApresentacaoMissa.setPrefHeight(proprietaria.getY() + proprietaria.getHeight()
                        + DESLOCAMENTO_Y_POPUP / 2 - posicaoY);
            }

            this.popup.show(this.tblMissas, posicaoX, posicaoY);
        } else {
            Dialogs.showWarningDialog(HomeController.getInstancia().getStage(),
                    "A descrição de e-mail desta Missa está vazia.", "Descrição Vazia!", "Aviso");
        }
    }

    private void carregarMarcacaoImpressaoEmMassa(List<Musica> musicasNaoImpressas) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/missa/popups/MarcarImpressaoMassa.fxml"));
        AnchorPane root = null;
        try {
            root = (AnchorPane) fxmlLoader.load();
        } catch (IOException ex) {
            log.error("Erro ao carregar tela para Seleção de Autor", ex);
            Dialogs.showErrorDialog(HomeController.getInstancia().getStage(),
                    "Erro ao carregar tela para Seleção de Autor."
                    + "\nFavor entrar em contato com o Administrador.",
                    "Erro!", "Erro", ex);
        }

        //Inicializa os dados passando a música por parâmetro
        MarcarImpressaoMassaController marcarImpressaoMassa = fxmlLoader.getController();

        marcarImpressaoMassa.initData(musicasNaoImpressas);

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Marcação de Impressão em Lote");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(HomeController.getInstancia().getStage());
        dialogStage.setScene(new Scene(root));
        dialogStage.getIcons().add(new Image("/snu/fronteiras/images/icons/iconeImpressao.png"));
        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();
    }

    private void removerMissaSelecionada(Missa missaSelecionada) {
        boolean resposta;
        resposta = Dialogs.showConfirmDialog(HomeController.getInstancia().getStage(),
                "Deseja realmente excluir a Missa \"" + missaSelecionada.getNome() + "\"?",
                "Exclusão de Missa", "Confirmação");

        if (resposta) {
            try {
                MissaJpaController.getInstancia().destroy(missaSelecionada.getId());
                Dialogs.showInformationDialog(HomeController.getInstancia().getStage(),
                        "A Missa foi removida com sucesso!", "Sucesso!", "Informação");
                this.tblMissas.getItems().remove(missaSelecionada);
                atualizarTabela();
            } catch (NonexistentEntityException ex) {
                log.error("Erro ao excluir Missa", ex);
                Dialogs.showErrorDialog(HomeController.getInstancia().getStage(),
                        "Erro ao excluir a Missa selecionada."
                        + "\nFavor entrar em contato com o Administrador.", "Erro!", "Erro", ex);
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
    private void onMouseClickedFromLblNomeMissa(MouseEvent event) {
        this.fldNomeMissa.requestFocus();
    }

    @FXML
    private void onMouseClickedFromLblDataAcontecimentoMissa(MouseEvent event) {
        this.dpDataAcontecimento.requestFocus();
    }

    @FXML
    private void onActionFromDpDataAcontecimento(ActionEvent event) {
    }

    @FXML
    private void onMouseClickedFromContentPesquisarMissa(MouseEvent event) {
        this.contentPesquisarMissa.requestFocus();
    }

    @FXML
    private void onActionFromFldNomeMissa(ActionEvent event) {
        pesquisarPorParametros();
    }

    @FXML
    private void onMouseClickedFromTblMissas(MouseEvent event) {
        this.tblMissas.requestFocus();
    }

    @FXML
    private void onMouseClickedFromImgInicio(MouseEvent event) {
        FXMLDocumentController.getInstancia().iniciarPaginaInicial();
    }

    @FXML
    private void onActionFromBtnLimpar(ActionEvent event) {
        this.fldNomeMissa.setText(null);
        this.dpDataAcontecimento.setValue(null);
    }

    @FXML
    private void onActionFromBtnPesquisar(ActionEvent event) {
        pesquisarPorParametros();
    }

    @FXML
    private void onActionFromBtnMontarMissa(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/missa/MontarMissaSelecao.fxml"));

        try {
            Parent root = (Parent) fxmlLoader.load();

            MontarMissaSelecaoController montarMissaSelecaoController = fxmlLoader.getController();

            //Limpa o conteúdo anterior e carrega a página
            AnchorPane pai = ((AnchorPane) this.contentPesquisarMissa.getParent());
            montarMissaSelecaoController.initData(this);
            pai.getChildren().add(root);
            EfeitosUtil.rodarEfeitoCarregamentoFadeIn(root);
        } catch (IOException ex) {
            log.error("Erro ao carregar tela de Montagem de Missa", ex);
            Dialogs.showErrorDialog(HomeController.getInstancia().getStage(), "Erro ao carregar tela de Montagem de Missa."
                    + "\nFavor entrar em contato com o Administrador.",
                    "Erro!", "Erro", ex);
        }
    }

    public AnchorPane getContent() {
        return this.contentPesquisarMissa;
    }

    public void atualizar() {
        if (!tblMissas.getItems().isEmpty()) {
            pesquisarPorParametros();
        }
    }

    public void atualizarTabela() {
        final List<Missa> itens = this.tblMissas.getItems();
        if (itens == null || itens.isEmpty()) {
            return;
        }

        final Missa item = this.tblMissas.getItems().get(0);
        itens.remove(0);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                itens.add(0, item);
            }
        });
    }
}
