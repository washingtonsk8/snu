/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores.musica.popups;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.StringConverter;
import net.sf.jasperreports.engine.JRException;
import org.apache.log4j.Logger;
import snu.controladores.PDFController;
import snu.entidades.musica.AssociacaoIntegranteMusica;
import snu.entidades.musica.Musica;
import snu.entidades.musica.Tom;
import snu.fronteiras.controladores.FXMLDocumentController;
import snu.fronteiras.controladores.geral.ProgressoController;
import snu.util.Dialogs;
import snu.util.EfeitosUtil;
import snu.util.ListaUtil;
import snu.util.MusicaUtil;
import snu.util.SeletorArquivosUtil;

/**
 * Classe controladora do FXML
 *
 * @author Washington Luis
 */
public class GerarImpressaoMusicaController implements Initializable {

    @FXML
    private AnchorPane popupGerarImpressaoMusica;
    @FXML
    private Font x1;
    @FXML
    private Label lblCantor;
    @FXML
    private Font x2;
    @FXML
    private ComboBox<String> comboCantor;
    @FXML
    private ComboBox<Tom> comboTom;
    @FXML
    private Button btnGerar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Label lblTipoImpressao;
    @FXML
    private RadioButton radioTom;
    @FXML
    private RadioButton radioCantor;
    @FXML
    private Label lblTom;
    @FXML
    private Label lblConfiguracaoImpressao;
    @FXML
    private ImageView iconeCancelar;
    @FXML
    private ImageView iconeGerar;

    private HashMap<String, Tom> mapaIntegrantesAssociados;

    private Musica musicaSelecionada;

    //Inicializando o Logger
    private static final Logger log = Logger.getLogger(GerarImpressaoMusicaController.class.getName());

    private void initComponents() {
        final ToggleGroup grupo = new ToggleGroup();

        this.radioTom.setToggleGroup(grupo);
        this.radioCantor.setToggleGroup(grupo);
        this.mapaIntegrantesAssociados = new HashMap<>();
    }

    /**
     * Inicializa os dados principais da classe
     *
     * @param musicaSelecionada
     */
    public void initData(final Musica musicaSelecionada) {
        this.musicaSelecionada = musicaSelecionada;

        for (AssociacaoIntegranteMusica associacao : this.musicaSelecionada.getAssociacoes()) {
            this.mapaIntegrantesAssociados.put(associacao.getIntegrante().getPrimeiroNome(), associacao.getTom());
        }

        this.comboCantor.setItems(FXCollections.observableList(new ArrayList<>(this.mapaIntegrantesAssociados.keySet())));
        this.comboTom.setItems(FXCollections.observableList(Arrays.asList(Tom.values())));

        //Define um conversor para verificar qual o correspondente de cada tom
        this.comboTom.setConverter(new StringConverter<Tom>() {
            @Override
            public String toString(Tom tom) {
                return tom.toString() + ((tom == musicaSelecionada.getTom()) ? " - Original" : "");
            }

            @Override
            public Tom fromString(String tom) {
                if (tom.endsWith("al")) {//Se termina com a palavra ORIGINAL, ignore-a
                    tom = tom.split(" ")[0];
                }
                return Tom.valueOf(tom);
            }
        });

        this.comboTom.getSelectionModel().select(this.musicaSelecionada.getTom());
        this.radioTom.setSelected(true);
        this.comboCantor.setDisable(true);

        //Não há sentido de haver seleção por autor se a lista estiver vazia
        if (this.comboCantor.getItems().isEmpty()) {
            this.radioCantor.setDisable(true);
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
    private void onMouseClickedFromLblCantor(MouseEvent event) {
        this.comboCantor.requestFocus();
    }

    @FXML
    private void onMouseClickedFromLblTom(MouseEvent event) {
        this.comboTom.requestFocus();
    }

    @FXML
    private void onActionFromComboCantor(ActionEvent event) {
        this.comboCantor.setEffect(null);
    }

    @FXML
    private void onActionFromComboTom(ActionEvent event) {
        this.comboCantor.getSelectionModel().select(null);
    }

    @FXML
    private void onMouseClickedFromLblTipoImpressao(MouseEvent event) {
        if (this.radioTom.isSelected()) {
            this.radioTom.requestFocus();
        } else {
            this.radioCantor.requestFocus();
        }
    }

    @FXML
    private void onActionFromRadioTom(ActionEvent event) {
        this.comboCantor.setDisable(true);
        this.comboTom.setDisable(false);
        this.comboCantor.getSelectionModel().select(null);
        this.comboCantor.setEffect(null);
        this.comboTom.getSelectionModel().select(this.musicaSelecionada.getTom());
    }

    @FXML
    private void onActionFromRadioCantor(ActionEvent event) {
        this.comboCantor.setDisable(false);
        this.comboTom.setDisable(true);
    }

    private boolean validarGeracao() {
        if (this.radioCantor.isSelected() && this.comboCantor.getSelectionModel().getSelectedItem() == null) {
            this.comboCantor.setEffect(EfeitosUtil.getEfeitoInvalido());
            return false;
        }
        return true;
    }

    @FXML
    private void onActionFromBtnGerar(ActionEvent event) {
        if (validarGeracao()) {
            //Novas criações
            final Map<String, Object> parametros = new HashMap<>();
            String infoTom = "";

            if (this.radioTom.isSelected()) {
                Tom tomSelecionado = this.comboTom.getSelectionModel().getSelectedItem();
                if (!tomSelecionado.equals(this.musicaSelecionada.getTom())) {
                    infoTom = " (" + tomSelecionado.toString() + ")";
                }
            } else {
                infoTom = " (" + this.comboCantor.getSelectionModel().getSelectedItem() + ")";
            }

            String nomeArquivoMusica = this.musicaSelecionada.getAutor().getNome() + " - "
                    + this.musicaSelecionada.getNome() + infoTom + ".pdf";

            //Escolha do local para salvar o arquivo
            String contextoUltimaSelecao = SeletorArquivosUtil.mapSeletores.get("gerarImpressao");
            if (contextoUltimaSelecao == null) {
                contextoUltimaSelecao = System.getProperty("user.home") + "/Desktop";
            }
            final FileChooser seletorArquivo = new FileChooser();
            seletorArquivo.setInitialDirectory(new File(contextoUltimaSelecao));
            seletorArquivo.setInitialFileName(nomeArquivoMusica);
            seletorArquivo.setTitle("Salvar arquivo de impressão");
            seletorArquivo.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF", "*.pdf"));

            final File arquivoGeracao = seletorArquivo.showSaveDialog(FXMLDocumentController.getInstancia().getStage());
            if (arquivoGeracao != null) {
                SeletorArquivosUtil.mapSeletores.put("gerarImpressao", arquivoGeracao.getParent());
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/geral/Progresso.fxml"));
                Parent root = null;
                try {
                    root = (Parent) fxmlLoader.load();
                } catch (IOException ex) {
                    log.error("Erro ao carregar popup de Progresso", ex);
                    Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(),
                            "Erro ao carregar popup de Progresso.\nFavor entrar em contato com o Administrador.",
                            "Erro!", "Erro", ex);
                }

                final ProgressoController progressoController = fxmlLoader.getController();
                progressoController.initData("Gerando a impressão da Música. Aguarde...");
                progressoController.setProgresso(-1.0);

                final Stage dialogStage = new Stage();
                dialogStage.setTitle("Por Favor Aguarde...");
                dialogStage.toFront();
                dialogStage.setResizable(false);
                dialogStage.initModality(Modality.WINDOW_MODAL);
                //A janela que é a proprietária não é a principal
                dialogStage.initOwner(((Node) event.getSource()).getScene().getWindow());
                dialogStage.setScene(new Scene(root));

                final Task task = new Task<Void>() {
                    @Override
                    public Void call() {
                        gerarImpressao(parametros);
                        //Instancia um novo controlador
                        PDFController controladorPDF = new PDFController();
                        try {
                            controladorPDF.gerarPDF("/snu/fronteiras/visao/pdfs/musica/impressao_musica.jasper",
                                    parametros, arquivoGeracao.getAbsolutePath());
                        } catch (final JRException ex) {
                            log.error("Erro ao gerar impressão de Música", ex);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(),
                                            "Erro ao gerar a impressão da Música."
                                            + "\nÉ provável que o arquivo esteja aberto em outra aplicação."
                                            + "\nCaso não esteja, favor entrar em contato com o Administrador.",
                                            "Erro!", "Erro", ex);
                                }
                            });
                            cancel(true);
                        }
                        return null;
                    }
                };

                dialogStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

                    @Override
                    public void handle(WindowEvent event) {
                        if (task.isRunning()) {
                            event.consume();
                        }
                    }
                });

                task.stateProperty().addListener(new ChangeListener<Task.State>() {
                    @Override
                    public void changed(ObservableValue<? extends Task.State> observableValue, Task.State oldState, Task.State state) {
                        switch (state) {
                            case RUNNING:
                                dialogStage.showAndWait();
                                break;
                            case SUCCEEDED:
                                Dialogs.showInformationDialog(FXMLDocumentController.getInstancia().getStage(),
                                        "O arquivo da Música foi gerado com sucesso!", "Sucesso!", "Informação");
                                popupGerarImpressaoMusica.getScene().getWindow().hide();
                                dialogStage.close();
                                break;
                            case CANCELLED:
                            case FAILED:
                                popupGerarImpressaoMusica.getScene().getWindow().hide();
                                dialogStage.close();
                                log.error("Erro ao gerar impressão de Música");
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(),
                                                "Erro ao gerar a impressão da Música."
                                                + "\nÉ provável que o arquivo esteja aberto em outra aplicação."
                                                + "\nCaso não esteja, favor entrar em contato com o Administrador.",
                                                "Erro!", "Erro");
                                    }
                                });
                                break;
                            case SCHEDULED:
                            case READY:
                                break;
                            default:
                                log.fatal("Caiu em DEFAULT CASE");
                                dialogStage.close();
                                break;
                        }
                    }
                });

                new Thread(task).start();
            }
        } else {
            Dialogs.showWarningDialog(FXMLDocumentController.getInstancia().getStage(),
                    "Favor corrigir os campos assinalados.", "Campos Inválidos!", "Aviso");
        }
    }

    protected final void gerarImpressao(Map<String, Object> parametros) {
        //Pré-definições
        Tom tomSelecionado = this.comboTom.getSelectionModel().getSelectedItem();
        Tom tomOriginal = this.musicaSelecionada.getTom();
        String nomeCantorMusica = this.comboCantor.getSelectionModel().getSelectedItem();
        String introducaoMusica = this.musicaSelecionada.getDocumentoMusica().getIntroducao();
        String conteudoMusica = this.musicaSelecionada.getDocumentoMusica().getConteudo();
        String caminhoImagem = getClass().getResource("/snu/fronteiras/images/logoSemDescricao.jpg").toExternalForm();

        //Definições para impressão
        parametros.put("tiposMusica", ListaUtil.getListaSeparadaPorVirgula(this.musicaSelecionada.getTipos()));
        parametros.put("tituloMusica", this.musicaSelecionada.getTitulo());
        parametros.put("cantorMusica", nomeCantorMusica == null ? "" : nomeCantorMusica);
        parametros.put("tomMusica", tomSelecionado.toString());
        parametros.put("caminhoImagem", caminhoImagem);
        parametros.put("autorMusica", this.musicaSelecionada.getAutor().getNome());
        parametros.put("nomeMusica", this.musicaSelecionada.getNome());

        if (tomOriginal.equals(tomSelecionado)) {
            parametros.put("introducaoMusica", introducaoMusica == null ? ""
                    : MusicaUtil.limparParaImpressao(introducaoMusica));
            parametros.put("conteudoMusica", conteudoMusica == null ? ""
                    : MusicaUtil.limparParaImpressao(conteudoMusica));
        } else {
            parametros.put("introducaoMusica", introducaoMusica == null ? ""
                    : MusicaUtil.limparParaImpressao(
                            MusicaUtil.converterTom(introducaoMusica,
                                    this.musicaSelecionada.getTom(),
                                    tomSelecionado)));
            parametros.put("conteudoMusica", conteudoMusica == null ? ""
                    : MusicaUtil.limparParaImpressao(
                            MusicaUtil.converterTom(conteudoMusica,
                                    this.musicaSelecionada.getTom(),
                                    tomSelecionado)));
        }
    }

    @FXML
    private void onActionFromBtnCancelar(ActionEvent event) {
        this.popupGerarImpressaoMusica.getScene().getWindow().hide();
    }
}
