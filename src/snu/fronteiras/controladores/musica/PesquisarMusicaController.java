/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores.musica;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javafx.util.Pair;
import org.apache.log4j.Logger;
import snu.controladores.MusicaJpaController;
import snu.dto.ParametrosPesquisaMusica;
import snu.entidades.musica.Musica;
import snu.entidades.musica.TipoMusica;
import snu.exceptions.NonexistentEntityException;
import snu.fronteiras.controladores.FXMLDocumentController;
import snu.fronteiras.controladores.HomeController;
import snu.fronteiras.controladores.musica.popups.GerarImpressaoMusicaController;
import snu.util.BotoesImagemUtil;
import snu.util.Dialogs;
import snu.util.EfeitosUtil;
import snu.util.ListaUtil;
import snu.util.StringUtil;

/**
 * Classe controladora do FXML
 *
 * @author Washington Luis
 */
public class PesquisarMusicaController implements Initializable {

    @FXML
    private AnchorPane contentPesquisarMusica;
    @FXML
    private TextField fldAutor;
    @FXML
    private Label lblTitulo;
    @FXML
    private Font x1;
    @FXML
    private TextField fldTitulo;
    @FXML
    private Label lblTrecho;
    @FXML
    private CheckBox checkEntrada;
    @FXML
    private Font x2;
    @FXML
    private CheckBox checkAtoPenitencial;
    @FXML
    private CheckBox checkGloria;
    @FXML
    private CheckBox checkAclamacao;
    @FXML
    private CheckBox checkOfertorio;
    @FXML
    private CheckBox checkPaz;
    @FXML
    private CheckBox checkSanto;
    @FXML
    private CheckBox checkComunhao;
    @FXML
    private CheckBox checkAcaoDeGracas;
    @FXML
    private CheckBox checkReflexao;
    @FXML
    private CheckBox checkFinal;
    @FXML
    private CheckBox checkLouvor;
    @FXML
    private CheckBox checkAdoracao;
    @FXML
    private CheckBox checkPalestra;
    @FXML
    private CheckBox checkResposta;
    @FXML
    private CheckBox checkOracao;
    @FXML
    private CheckBox checkEspecial;
    @FXML
    private CheckBox checkOutra;
    @FXML
    private Label lblLeitura;
    @FXML
    private Label lblAutor;
    @FXML
    private Label lblTipos;
    @FXML
    private TextField fldLeitura;
    @FXML
    private TextField fldTrecho;
    @FXML
    private TableView<Musica> tblMusicas;
    @FXML
    private TableColumn<Musica, String> clnAutor;
    @FXML
    private TableColumn<Musica, String> clnTitulo;
    @FXML
    private TableColumn<Musica, String> clnTipos;
    @FXML
    private TableColumn<Musica, String> clnLeituras;
    @FXML
    private TableColumn<Musica, String> clnEstaImpressa;
    @FXML
    private Label lblEstaImpressa;
    @FXML
    private RadioButton radioEstaImpressa;
    @FXML
    private RadioButton radioNaoEstaImpressa;
    @FXML
    private Button btnPesquisar;
    @FXML
    private Button btnLimpar;
    @FXML
    private ImageView imgInicio;
    @FXML
    private ImageView iconePesquisar;
    @FXML
    private ImageView iconeLimpar;

    private List<Pair<TipoMusica, CheckBox>> parTiposMusicaCheckBoxes;

    private List<TipoMusica> tiposMusica;

    //Inicializando o Logger
    private static final Logger log = Logger.getLogger(PesquisarMusicaController.class.getName());
    @FXML
    private Label lblPesquisarMusica;
    @FXML
    private Button btnCriarNova;
    @FXML
    private ImageView iconeCriarNova;

    private void initComponents() {
        this.tiposMusica = new ArrayList<>();

        this.tblMusicas.setRowFactory(
                new Callback<TableView<Musica>, TableRow<Musica>>() {
                    @Override
                    public TableRow<Musica> call(TableView<Musica> tableView) {
                        final TableRow<Musica> linha = new TableRow<>();
                        final ContextMenu menuContexto = new ContextMenu();

                        ImageView iconeVisualizar = new ImageView("/snu/fronteiras/images/icons/iconeVisualizar.png");
                        iconeVisualizar.setFitHeight(10.);
                        iconeVisualizar.setFitWidth(15.);
                        MenuItem itemVisualizarMusica = new MenuItem("Visualizar", iconeVisualizar);
                        itemVisualizarMusica.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
                                carregarVisualizacaoMusica(linha.itemProperty().get());
                            }
                        });

                        ImageView iconeAtualizar = new ImageView("/snu/fronteiras/images/icons/iconeEditar.png");
                        iconeAtualizar.setFitHeight(12.);
                        iconeAtualizar.setFitWidth(12.);
                        MenuItem itemAtualizarMusica = new MenuItem("Atualizar", iconeAtualizar);
                        itemAtualizarMusica.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
                                carregarAtualizacaoMusica(linha.itemProperty().get());
                            }
                        });

                        ImageView iconeRemover = new ImageView("/snu/fronteiras/images/icons/iconeRemover.png");
                        iconeRemover.setFitHeight(12.);
                        iconeRemover.setFitWidth(12.);
                        MenuItem itemRemoverMusica = new MenuItem("Remover", iconeRemover);
                        itemRemoverMusica.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
                                removerMusicaSelecionada(linha.itemProperty().get());
                            }
                        });

                        ImageView iconeImpressao = new ImageView("/snu/fronteiras/images/icons/iconeImpressao.png");
                        iconeImpressao.setFitHeight(12.);
                        iconeImpressao.setFitWidth(12.);
                        MenuItem itemGerarImpressao = new MenuItem("Gerar Impressão", iconeImpressao);
                        itemGerarImpressao.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
                                gerarImpressaoMusicaSelecionada(linha.itemProperty().get());
                            }
                        });

                        menuContexto.getItems().addAll(itemVisualizarMusica,
                                itemAtualizarMusica, itemRemoverMusica, itemGerarImpressao);

                        //Seleciona antes de mostrar o menu de contexto
                        menuContexto.setOnShowing(new EventHandler<WindowEvent>() {
                            @Override
                            public void handle(WindowEvent event) {
                                tblMusicas.getSelectionModel().select(linha.getItem());
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

        this.clnAutor.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Musica, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Musica, String> musica) {
                return new SimpleStringProperty(musica.getValue().getAutor().getNome());
            }
        });

        this.clnTitulo.setCellValueFactory(new PropertyValueFactory<>("nome"));
        this.clnLeituras.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Musica, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Musica, String> musica) {
                String celula = ListaUtil.getListaSeparadaPorPontoVirgula(musica.getValue().getLeiturasAssociadas());

                if (!celula.isEmpty()) {
                    return new SimpleStringProperty(celula);
                } else {
                    return new SimpleStringProperty("Não há leituras associadas");
                }
            }
        });

        this.clnTipos.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Musica, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Musica, String> musica) {
                String celula = ListaUtil.getListaSeparadaPorPontoVirgula(musica.getValue().getTipos());

                return new SimpleStringProperty(celula);
            }
        });

        this.clnEstaImpressa.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Musica, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Musica, String> musica) {
                return new SimpleStringProperty(musica.getValue().isImpressa() ? "Sim" : "Não");
            }
        });

        //Adicionando as checkboxes na lista
        this.parTiposMusicaCheckBoxes = new ArrayList<>();
        this.parTiposMusicaCheckBoxes.add(new Pair<>(TipoMusica.ENTRADA, this.checkEntrada));
        this.parTiposMusicaCheckBoxes.add(new Pair<>(TipoMusica.ATO_PENITENCIAL, this.checkAtoPenitencial));
        this.parTiposMusicaCheckBoxes.add(new Pair<>(TipoMusica.GLORIA, this.checkGloria));
        this.parTiposMusicaCheckBoxes.add(new Pair<>(TipoMusica.ACLAMACAO, this.checkAclamacao));
        this.parTiposMusicaCheckBoxes.add(new Pair<>(TipoMusica.OFERTORIO, this.checkOfertorio));
        this.parTiposMusicaCheckBoxes.add(new Pair<>(TipoMusica.PAZ, this.checkPaz));
        this.parTiposMusicaCheckBoxes.add(new Pair<>(TipoMusica.SANTO, this.checkSanto));
        this.parTiposMusicaCheckBoxes.add(new Pair<>(TipoMusica.COMUNHAO, this.checkComunhao));
        this.parTiposMusicaCheckBoxes.add(new Pair<>(TipoMusica.ACAO_DE_GRACAS, this.checkAcaoDeGracas));
        this.parTiposMusicaCheckBoxes.add(new Pair<>(TipoMusica.FINAL, this.checkFinal));
        this.parTiposMusicaCheckBoxes.add(new Pair<>(TipoMusica.REFLEXAO, this.checkReflexao));
        this.parTiposMusicaCheckBoxes.add(new Pair<>(TipoMusica.LOUVOR, this.checkLouvor));
        this.parTiposMusicaCheckBoxes.add(new Pair<>(TipoMusica.ADORACAO, this.checkAdoracao));
        this.parTiposMusicaCheckBoxes.add(new Pair<>(TipoMusica.PALESTRA, this.checkPalestra));
        this.parTiposMusicaCheckBoxes.add(new Pair<>(TipoMusica.RESPOSTA, this.checkResposta));
        this.parTiposMusicaCheckBoxes.add(new Pair<>(TipoMusica.ORACAO, this.checkOracao));
        this.parTiposMusicaCheckBoxes.add(new Pair<>(TipoMusica.ESPECIAL, this.checkEspecial));
        this.parTiposMusicaCheckBoxes.add(new Pair<>(TipoMusica.OUTRA, this.checkOutra));

        final ToggleGroup grupo = new ToggleGroup();

        this.radioEstaImpressa.setToggleGroup(grupo);
        this.radioNaoEstaImpressa.setToggleGroup(grupo);

        BotoesImagemUtil.definirComportamento(this.imgInicio);
    }

    private void pesquisarPorParametros() {
        ParametrosPesquisaMusica parametrosPesquisa = new ParametrosPesquisaMusica();
        parametrosPesquisa.setNomeAutor(this.fldAutor.getText());
        parametrosPesquisa.setDescricaoLeiturasAssociadas(this.fldLeitura.getText());
        parametrosPesquisa.setTipos(this.tiposMusica);
        parametrosPesquisa.setNomeMusica(this.fldTitulo.getText());
        parametrosPesquisa.setTrecho(this.fldTrecho.getText());

        if (this.radioEstaImpressa.isSelected()) {
            parametrosPesquisa.setImpressa(Boolean.TRUE);
        } else if (this.radioNaoEstaImpressa.isSelected()) {
            parametrosPesquisa.setImpressa(Boolean.FALSE);
        }

        try {
            List<Musica> musicasEncontradas = MusicaJpaController.getInstancia()
                    .findMusicasByParametrosPesquisa(parametrosPesquisa);
            this.tblMusicas.setItems(FXCollections.observableArrayList(musicasEncontradas));
            atualizarTabela();
        } catch (IOException ex) {
            log.error("Erro ao pesquisar músicas por parâmetros", ex);
            Dialogs.showErrorDialog(HomeController.getInstancia().getStage(),
                    "Erro ao realizar a pesquisa.\nFavor entrar em contato com o Administrador.",
                    "Erro!", "Erro", ex);
        }
    }

    private void carregarCriacaoMusica() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/musica/CriarMusica.fxml"));
        try {
            Parent root = (Parent) fxmlLoader.load();

            CriarMusicaController criarMusicaController = fxmlLoader.getController();
            criarMusicaController.initData(this);

            //Limpa o conteúdo anterior e carrega a página
            AnchorPane pai = ((AnchorPane) this.contentPesquisarMusica.getParent());
            criarMusicaController.initData(this);
            pai.getChildren().clear();
            pai.getChildren().add(root);
            EfeitosUtil.rodarEfeitoCarregamentoFadeIn(root);
        } catch (IOException ex) {
            log.error("Erro ao carregar tela de Criação de Música", ex);
            Dialogs.showErrorDialog(HomeController.getInstancia().getStage(), "Erro ao carregar tela de Criação de Música."
                    + "\nFavor entrar em contato com o Administrador.",
                    "Erro!", "Erro", ex);
        }
    }

    private void carregarAtualizacaoMusica(Musica musicaSelecionada) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/musica/AtualizarMusica.fxml"));

        try {
            Parent root = (Parent) fxmlLoader.load();

            AtualizarMusicaController atualizarMusicaController = fxmlLoader.getController();

            //Limpa o conteúdo anterior e carrega a página
            AnchorPane pai = ((AnchorPane) this.contentPesquisarMusica.getParent());
            atualizarMusicaController.initData(musicaSelecionada, this);
            pai.getChildren().clear();
            pai.getChildren().add(root);
            EfeitosUtil.rodarEfeitoCarregamentoFadeIn(root);
        } catch (IOException ex) {
            log.error("Erro ao carregar tela de Atualização de Música", ex);
            Dialogs.showErrorDialog(HomeController.getInstancia().getStage(),
                    "Erro ao carregar tela de Atualização de Música.\nFavor entrar em contato com o Administrador.",
                    "Erro!", "Erro", ex);
        }
    }

    private void carregarVisualizacaoMusica(Musica musicaSelecionada) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/musica/VisualizarMusica.fxml"));

        try {
            Parent root = (Parent) fxmlLoader.load();

            VisualizarMusicaController visualizarMusicaController = fxmlLoader.getController();

            //Limpa o conteúdo anterior e carrega a página
            AnchorPane pai = ((AnchorPane) this.contentPesquisarMusica.getParent());
            visualizarMusicaController.initData(musicaSelecionada, this);
            pai.getChildren().clear();
            pai.getChildren().add(root);
            EfeitosUtil.rodarEfeitoCarregamentoFadeIn(root);
        } catch (IOException ex) {
            log.error("Erro ao carregar tela de Visualização de Música", ex);
            Dialogs.showErrorDialog(HomeController.getInstancia().getStage(),
                    "Erro ao carregar tela de Visualização de Música."
                    + "\nFavor entrar em contato com o Administrador.", "Erro!", "Erro", ex);
        }
    }

    private void removerMusicaSelecionada(Musica musicaSelecionada) {
        Dialogs.DialogResponse resposta;
        resposta = Dialogs.showConfirmDialog(HomeController.getInstancia().getStage(),
                "Deseja realmente excluir a Música \"" + musicaSelecionada.getTitulo() + "\"?",
                "Exclusão de Música", "Confirmação");

        if (resposta.equals(Dialogs.DialogResponse.YES)) {
            try {
                MusicaJpaController.getInstancia().destroy(musicaSelecionada.getId());
                Dialogs.showInformationDialog(HomeController.getInstancia().getStage(),
                        "A Música foi removida com sucesso!", "Sucesso!", "Informação");
                this.tblMusicas.getItems().remove(musicaSelecionada);
                atualizarTabela();
            } catch (NonexistentEntityException ex) {
                log.error("Erro ao excluir Música", ex);
                Dialogs.showErrorDialog(HomeController.getInstancia().getStage(),
                        "Erro ao excluir a Música selecionada."
                        + "\nFavor entrar em contato com o Administrador.", "Erro!", "Erro", ex);
            }
        }
    }

    private void gerarImpressaoMusicaSelecionada(Musica musicaSelecionada) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/musica/popups/GerarImpressaoMusica.fxml"));

        try {
            AnchorPane root = (AnchorPane) fxmlLoader.load();

            //Inicializa os dados passando a música por parâmetro
            GerarImpressaoMusicaController gerarImpressaoMusicaController = fxmlLoader.getController();
            gerarImpressaoMusicaController.initData(musicaSelecionada);

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Gerar Impressão de Música");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(HomeController.getInstancia().getStage());
            dialogStage.setScene(new Scene(root));
        dialogStage.getIcons().add(new Image("/snu/fronteiras/images/icons/iconeImpressao.png"));
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
        } catch (IOException ex) {
            log.error("Erro ao excluir Música", ex);
            Dialogs.showErrorDialog(HomeController.getInstancia().getStage(),
                    "Erro ao excluir a Música selecionada."
                    + "\nFavor entrar em contato com o Administrador.", "Erro!", "Erro", ex);
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
    private void onMouseClickedFromContentPesquisarMusica(MouseEvent event) {
        this.contentPesquisarMusica.requestFocus();
    }

    @FXML
    private void onActionFromFldAutor(ActionEvent event) {
        pesquisarPorParametros();
    }

    @FXML
    private void onActionFromFldTitulo(ActionEvent event) {
        pesquisarPorParametros();
    }

    @FXML
    private void onActionFromFldLeitura(ActionEvent event) {
        pesquisarPorParametros();
    }

    @FXML
    private void onActionFromFldTrecho(ActionEvent event) {
        pesquisarPorParametros();
    }

    @FXML
    private void onActionFromCheckEntrada(ActionEvent event) {
        if (this.checkEntrada.isSelected()) {
            this.tiposMusica.add(TipoMusica.ENTRADA);
        } else {
            this.tiposMusica.remove(TipoMusica.ENTRADA);
        }
    }

    @FXML
    private void onActionFromCheckAtoPenitencial(ActionEvent event) {
        if (this.checkAtoPenitencial.isSelected()) {
            this.tiposMusica.add(TipoMusica.ATO_PENITENCIAL);
        } else {
            this.tiposMusica.remove(TipoMusica.ATO_PENITENCIAL);
        }
    }

    @FXML
    private void onActionFromCheckGloria(ActionEvent event) {
        if (this.checkGloria.isSelected()) {
            this.tiposMusica.add(TipoMusica.GLORIA);
        } else {
            this.tiposMusica.remove(TipoMusica.GLORIA);
        }
    }

    @FXML
    private void onActionFromCheckAclamacao(ActionEvent event) {
        if (this.checkAclamacao.isSelected()) {
            this.tiposMusica.add(TipoMusica.ACLAMACAO);
        } else {
            this.tiposMusica.remove(TipoMusica.ACLAMACAO);
        }
    }

    @FXML
    private void onActionFromCheckOfertorio(ActionEvent event) {
        if (this.checkOfertorio.isSelected()) {
            this.tiposMusica.add(TipoMusica.OFERTORIO);
        } else {
            this.tiposMusica.remove(TipoMusica.OFERTORIO);
        }
    }

    @FXML
    private void onActionFromCheckPaz(ActionEvent event) {
        if (this.checkPaz.isSelected()) {
            this.tiposMusica.add(TipoMusica.PAZ);
        } else {
            this.tiposMusica.remove(TipoMusica.PAZ);
        }
    }

    @FXML
    private void onActionFromCheckSanto(ActionEvent event) {
        if (this.checkSanto.isSelected()) {
            this.tiposMusica.add(TipoMusica.SANTO);
        } else {
            this.tiposMusica.remove(TipoMusica.SANTO);
        }
    }

    @FXML
    private void onActionFromCheckComunhao(ActionEvent event) {
        if (this.checkComunhao.isSelected()) {
            this.tiposMusica.add(TipoMusica.COMUNHAO);
        } else {
            this.tiposMusica.remove(TipoMusica.COMUNHAO);
        }
    }

    @FXML
    private void onActionFromCheckAcaoDeGracas(ActionEvent event) {
        if (this.checkAcaoDeGracas.isSelected()) {
            this.tiposMusica.add(TipoMusica.ACAO_DE_GRACAS);
        } else {
            this.tiposMusica.remove(TipoMusica.ACAO_DE_GRACAS);
        }
    }

    @FXML
    private void onActionFromCheckFinal(ActionEvent event) {
        if (this.checkFinal.isSelected()) {
            this.tiposMusica.add(TipoMusica.FINAL);
        } else {
            this.tiposMusica.remove(TipoMusica.FINAL);
        }
    }

    @FXML
    private void onActionFromCheckReflexao(ActionEvent event) {
        if (this.checkReflexao.isSelected()) {
            this.tiposMusica.add(TipoMusica.REFLEXAO);
        } else {
            this.tiposMusica.remove(TipoMusica.REFLEXAO);
        }
    }

    @FXML
    private void onActionFromCheckLouvor(ActionEvent event) {
        if (this.checkLouvor.isSelected()) {
            this.tiposMusica.add(TipoMusica.LOUVOR);
        } else {
            this.tiposMusica.remove(TipoMusica.LOUVOR);
        }
    }

    @FXML
    private void onActionFromCheckAdoracao(ActionEvent event) {
        if (this.checkAdoracao.isSelected()) {
            this.tiposMusica.add(TipoMusica.ADORACAO);
        } else {
            this.tiposMusica.remove(TipoMusica.ADORACAO);
        }
    }

    @FXML
    private void onActionFromCheckPalestra(ActionEvent event) {
        if (this.checkPalestra.isSelected()) {
            this.tiposMusica.add(TipoMusica.PALESTRA);
        } else {
            this.tiposMusica.remove(TipoMusica.PALESTRA);
        }
    }

    @FXML
    private void onActionFromCheckResposta(ActionEvent event) {
        if (this.checkResposta.isSelected()) {
            this.tiposMusica.add(TipoMusica.RESPOSTA);
        } else {
            this.tiposMusica.remove(TipoMusica.RESPOSTA);
        }
    }

    @FXML
    private void onActionFromCheckOracao(ActionEvent event) {
        if (this.checkOracao.isSelected()) {
            this.tiposMusica.add(TipoMusica.ORACAO);
        } else {
            this.tiposMusica.remove(TipoMusica.ORACAO);
        }
    }

    @FXML
    private void onActionFromCheckEspecial(ActionEvent event) {
        if (this.checkEspecial.isSelected()) {
            this.tiposMusica.add(TipoMusica.ESPECIAL);
        } else {
            this.tiposMusica.remove(TipoMusica.ESPECIAL);
        }
    }

    @FXML
    private void onActionFromCheckOutra(ActionEvent event) {
        if (this.checkOutra.isSelected()) {
            this.tiposMusica.add(TipoMusica.OUTRA);
        } else {
            this.tiposMusica.remove(TipoMusica.OUTRA);
        }
    }

    @FXML
    private void onMouseClickedFromLblLeitura(MouseEvent event) {
        this.fldLeitura.requestFocus();
    }

    @FXML
    private void onMouseClickedFromLblAutor(MouseEvent event) {
        this.fldAutor.requestFocus();
    }

    @FXML
    private void onMouseClickedFromLblTitulo(MouseEvent event) {
        this.fldTitulo.requestFocus();
    }

    @FXML
    private void onMouseClickedFromLblTipos(MouseEvent event) {
        this.checkEntrada.requestFocus();
    }

    @FXML
    private void onMouseClickedFromLblTrecho(MouseEvent event) {
        this.fldTrecho.requestFocus();
    }

    @FXML
    private void onActionFromBtnCriarNova(ActionEvent event) {
        carregarCriacaoMusica();
    }

    @FXML
    private void onActionFromBtnLimpar(ActionEvent event) {
        this.fldAutor.setText(null);
        this.fldLeitura.setText(null);
        this.fldTitulo.setText(null);
        this.fldTrecho.setText(null);
        this.radioEstaImpressa.setSelected(false);
        this.radioNaoEstaImpressa.setSelected(false);
        this.tiposMusica.clear();
        definirPesquisaTrecho(false);
        for (Pair<TipoMusica, CheckBox> parTipoMusicaCheckBox : parTiposMusicaCheckBoxes) {
            parTipoMusicaCheckBox.getValue().setSelected(false);
        }
    }

    @FXML
    private void onActionFromBtnPesquisar(ActionEvent event) {
        pesquisarPorParametros();
    }

    @FXML
    private void onActionFromRadioEstaImpressa(ActionEvent event) {
    }

    @FXML
    private void onActionFromRadioNaoEstaImpressa(ActionEvent event) {
    }

    @FXML
    private void onMouseClickedFromTblMusicas(MouseEvent event) {
        this.tblMusicas.requestFocus();
    }

    @FXML
    private void onMouseClickedFromImgInicio(MouseEvent event) {
        FXMLDocumentController.getInstancia().iniciarPaginaInicial();
    }

    @FXML
    private void onKeyReleasedFromFldTrecho(KeyEvent event) {
        if (StringUtil.isVazia(this.fldTrecho.getText())) {
            definirPesquisaTrecho(false);
        } else {
            definirPesquisaTrecho(true);
        }
    }

    /**
     * A pesquisa por trecho desconsidera os outros parâmetros
     *
     * @param ligada
     */
    private void definirPesquisaTrecho(boolean ligada) {
        if (ligada) {
            this.lblAutor.setOpacity(0.6);
            this.lblTitulo.setOpacity(0.6);
            this.lblLeitura.setOpacity(0.6);
            this.lblTipos.setOpacity(0.6);
            this.lblEstaImpressa.setOpacity(0.6);
            this.fldAutor.setEditable(false);
            this.fldLeitura.setEditable(false);
            this.fldTitulo.setEditable(false);
            this.fldAutor.setOpacity(0.6);
            this.fldLeitura.setOpacity(0.6);
            this.fldTitulo.setOpacity(0.6);
            this.radioEstaImpressa.setOpacity(0.6);
            this.radioNaoEstaImpressa.setOpacity(0.6);
            for (Pair<TipoMusica, CheckBox> parTipoMusicaCheckBox : parTiposMusicaCheckBoxes) {
                parTipoMusicaCheckBox.getValue().setOpacity(0.6);
            }
        } else {
            this.lblAutor.setOpacity(1);
            this.lblTitulo.setOpacity(1);
            this.lblLeitura.setOpacity(1);
            this.lblTipos.setOpacity(1);
            this.lblEstaImpressa.setOpacity(1);
            this.fldAutor.setEditable(true);
            this.fldLeitura.setEditable(true);
            this.fldTitulo.setEditable(true);
            this.fldAutor.setOpacity(1);
            this.fldLeitura.setOpacity(1);
            this.fldTitulo.setOpacity(1);
            this.radioEstaImpressa.setOpacity(1);
            this.radioNaoEstaImpressa.setOpacity(1);
            for (Pair<TipoMusica, CheckBox> parTipoMusicaCheckBox : parTiposMusicaCheckBoxes) {
                parTipoMusicaCheckBox.getValue().setOpacity(1);
            }
        }
    }

    public AnchorPane getContent() {
        return this.contentPesquisarMusica;
    }

    public void atualizar() {
        pesquisarPorParametros();
    }

    public void atualizarTabela() {
        final List<Musica> itens = this.tblMusicas.getItems();
        if (itens == null || itens.isEmpty()) {
            return;
        }

        final Musica item = this.tblMusicas.getItems().get(0);
        itens.remove(0);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                itens.add(0, item);
            }
        });
    }
}
