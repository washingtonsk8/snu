/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import net.lingala.zip4j.exception.ZipException;
import org.apache.log4j.Logger;
import snu.bd.BD;
import snu.controladores.ConfiguracoesSistemaJpaController;
import snu.controladores.SNU;
import snu.fronteiras.controladores.geral.ProgressoController;
import snu.fronteiras.controladores.integrante.CadastrarIntegranteController;
import snu.fronteiras.controladores.integrante.PesquisarIntegranteController;
import snu.fronteiras.controladores.missa.LimparDadosMissasController;
import snu.fronteiras.controladores.missa.MontarMissaSelecaoController;
import snu.fronteiras.controladores.musica.CriarMusicaController;
import snu.fronteiras.controladores.musica.PesquisarMusicaController;
import snu.geral.TipoPagina;
import snu.util.Dialogs;
import snu.util.EfeitosUtil;
import snu.util.SeletorArquivosUtil;

/**
 * Classe que controla a tela principal do programa
 *
 * @author Washington Luis
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private AnchorPane contentAnchorPane;
    @FXML
    private MenuBar menubar;
    @FXML
    private Menu menuIntegrante;
    @FXML
    private MenuItem itemCadastrarIntegrante;
    @FXML
    private MenuItem itemAtualizarIntegrante;
    @FXML
    private MenuItem itemRemoverIntegrante;
    @FXML
    private Menu menuMusica;
    @FXML
    private MenuItem itemSobre;
    @FXML
    private MenuItem itemVisualizarDados;
    @FXML
    private MenuItem itemRemoverMusica;
    @FXML
    private MenuItem itemGerenciarAutores;
    @FXML
    private MenuItem itemCriarMusica;
    @FXML
    private MenuItem itemVisualizarDadosMusica;
    @FXML
    private MenuItem itemAtualizarDadosMusica;
    @FXML
    private MenuItem itemGerarImpressao;
    @FXML
    private Menu menuMissa;
    @FXML
    private MenuItem itemMontarMissa;
    @FXML
    private MenuItem itemPesquisarMissa;
    @FXML
    private Menu menuConfiguracoes;
    @FXML
    private Menu menuBackup;
    @FXML
    private MenuItem itemImportarDados;
    @FXML
    private MenuItem itemExportarDados;
    @FXML
    private MenuItem itemTemplateEmail;
    @FXML
    private Menu menuBancoDados;
    @FXML
    private MenuItem itemEscolherDiretorioBancoDados;
    @FXML
    private MenuItem itemLimparDadosMissa;
    @FXML
    private MenuItem itemVerificarLog;
    @FXML
    private MenuItem itemDefinirPreferenciaTons;
    @FXML
    private Menu menuConfiguracoesMissa;

    private FXMLLoader templatePesquisaMissaLoader;

    /**
     * Fábrica Singleton do sistema
     */
    private static FXMLDocumentController instancia;

    //Inicializando o Logger
    private static final Logger log = Logger.getLogger(FXMLDocumentController.class.getName());

    private void iniciarControladorPesquisaMissa() {
        this.templatePesquisaMissaLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/missa/VisualizarDadosMissa.fxml"));

        try {
            this.templatePesquisaMissaLoader.load();
        } catch (IOException ex) {
            log.error("Erro ao carregar tela de Template de Pesquisa de Missa", ex);
            Dialogs.showErrorDialog(getStage(),
                    "Erro de processamento interno.\nFavor entrar em contato com o Administrador.", "Erro interno!", "Erro", ex);
        }
    }

    public void iniciarPaginaInicial() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/Home.fxml"));
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException ex) {
            log.error("Erro ao carregar tela inicial", ex);
            Dialogs.showErrorDialog(getStage(), "Erro ao carregar tela inicial."
                    + "\nFavor entrar em contato com o Administrador.", "Erro!", "Erro", ex);
        }

        //Limpa o conteúdo anterior e carrega a página
        this.contentAnchorPane.getChildren().clear();
        this.contentAnchorPane.getChildren().add(root);
        EfeitosUtil.rodarEfeitoCarregamentoFade(root);
    }

    private void initComponents() {
        instancia = this;
        iniciarControladorPesquisaMissa();
        iniciarPaginaInicial();
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
    
    public FXMLLoader getTemplatePesquisaMissaLoader() {
        return templatePesquisaMissaLoader;
    }

    private void onShowingFromMenuHome(Event event) {
        event.consume();
        iniciarPaginaInicial();
    }

    @FXML
    private void onActionFromItemGerenciarAutores(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/musica/GerenciarAutores.fxml"));
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException ex) {
            log.error("Erro ao carregar tela de Gerência de Autores", ex);
            Dialogs.showErrorDialog(getStage(), "Erro ao carregar tela de Gerência de Autores."
                    + "\nFavor entrar em contato com o Administrador.",
                    "Erro!", "Erro", ex);
        }

        //Limpa o conteúdo anterior e carrega a página
        this.contentAnchorPane.getChildren().clear();
        this.contentAnchorPane.getChildren().add(root);
        EfeitosUtil.rodarEfeitoCarregamentoFade(root);
    }

    @FXML
    private void onActionFromItemMontarMissa(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/missa/MontarMissaSelecao.fxml"));
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException ex) {
            log.error("Erro ao carregar tela de Montagem de Missa", ex);
            Dialogs.showErrorDialog(getStage(), "Erro ao carregar tela de Montagem de Missa."
                    + "\nFavor entrar em contato com o Administrador.",
                    "Erro!", "Erro", ex);
        }

        MontarMissaSelecaoController montarMissaSelecaoController = fxmlLoader.getController();
        montarMissaSelecaoController.initData(this);

        //Limpa o conteúdo anterior e carrega a página
        this.contentAnchorPane.getChildren().clear();
        this.contentAnchorPane.getChildren().add(root);
        EfeitosUtil.rodarEfeitoCarregamentoFade(root);
    }

    @FXML
    private void onActionFromPesquisarMissa(ActionEvent event) {
        final Parent root = (Parent) this.templatePesquisaMissaLoader.getRoot();

        //Limpa o conteúdo anterior e carrega a página
        this.contentAnchorPane.getChildren().clear();
        this.contentAnchorPane.getChildren().add(root);
        EfeitosUtil.rodarEfeitoCarregamentoFade(root);
    }

    @FXML
    private void onActionFromItemLimparDadosMissa(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/missa/LimparDadosMissas.fxml"));
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException ex) {
            log.error("Erro ao carregar tela de limpeza de dados de Missas", ex);
            Dialogs.showErrorDialog(getStage(), "Erro ao carregar tela de Limpeza de Dados de Missa."
                    + "\nFavor entrar em contato com o Administrador.",
                    "Erro!", "Erro", ex);
        }

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Limpeza de Dados de Missas");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(this.anchorPane.getScene().getWindow());
        dialogStage.setScene(new Scene(root));

        LimparDadosMissasController limparDadosMissaController = fxmlLoader.getController();
        limparDadosMissaController.setFocusToContent();

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();
    }

    @FXML
    private void onActionFromItemImportarDados(ActionEvent event) {
        String contextoUltimaSelecao = SeletorArquivosUtil.mapSeletores.get("importarDados");
        if (contextoUltimaSelecao == null) {
            contextoUltimaSelecao = System.getProperty("user.home") + "/Desktop";
        }
        final FileChooser seletorArquivo = new FileChooser();
        seletorArquivo.setInitialDirectory(new File(contextoUltimaSelecao));
        seletorArquivo.setTitle("Escolha do arquivo para importação");
        seletorArquivo.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivos Compactados (*.zip)", "*.zip"));
        final File arquivoImportacao = seletorArquivo.showOpenDialog(HomeController.getInstancia().getStage());
        if (arquivoImportacao != null) {
            SeletorArquivosUtil.mapSeletores.put("importarDados", arquivoImportacao.getAbsolutePath());

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/geral/Progresso.fxml"));
            Parent root = null;
            try {
                root = (Parent) fxmlLoader.load();
            } catch (IOException ex) {
                log.error("Erro ao carregar popup de Progresso", ex);
                Dialogs.showErrorDialog(getStage(), "Erro ao carregar popup de Progresso."
                        + "\nFavor entrar em contato com o Administrador.",
                        "Erro!", "Erro", ex);
            }

            ProgressoController progressoController = fxmlLoader.getController();
            progressoController.initData("Importando os dados do arquivo. Aguarde...");
            progressoController.setProgresso(-1.0);

            final Stage dialogStage = new Stage();
            dialogStage.setTitle("Por Favor Aguarde...");
            dialogStage.toFront();
            dialogStage.setResizable(false);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(this.anchorPane.getScene().getWindow());
            dialogStage.setScene(new Scene(root));

            final Task task = new Task<Void>() {
                @Override
                public Void call() {
                    try {
                        if (!BD.doRestore(arquivoImportacao)) {
                            cancel(true);
                        }
                    } catch (IOException | InterruptedException | ZipException | SQLException ex) {
                        log.error("Erro ao realizar restore para o banco", ex);
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
                            Dialogs.showInformationDialog(getStage(), "Dados importados com sucesso!",
                                    "Sucesso!", "Informação");
                            dialogStage.close();
                            initComponents();
                            break;
                        case CANCELLED:
                        case FAILED:
                            Dialogs.showErrorDialog(getStage(), "Erro ao importar dados."
                                    + "\nVerifique se o arquivo escolhido para importação"
                                    + "\né realmente um arquivo de backup deste sistema.",
                                    "Erro!", "Erro");
                            dialogStage.close();
                            break;
                        case SCHEDULED:
                        case READY:
                            break;
                        default:
                            dialogStage.close();
                            break;
                    }
                }
            });
            new Thread(task).start();
        }
    }

    @FXML
    private void onActionFromItemExportarDados(ActionEvent event) {
        String contextoUltimaSelecao = SeletorArquivosUtil.mapSeletores.get("exportarDados");
        if (contextoUltimaSelecao == null) {
            contextoUltimaSelecao = System.getProperty("user.home") + "/Desktop";
        }
        final DirectoryChooser seletorDiretorio = new DirectoryChooser();
        seletorDiretorio.setInitialDirectory(new File(contextoUltimaSelecao));
        seletorDiretorio.setTitle("Escolha do diretório para exportação");
        final File diretorioExportacao = seletorDiretorio.showDialog(HomeController.getInstancia().getStage());
        if (diretorioExportacao != null) {
            SeletorArquivosUtil.mapSeletores.put("exportarDados", diretorioExportacao.getAbsolutePath());

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/geral/Progresso.fxml"));
            Parent root = null;
            try {
                root = (Parent) fxmlLoader.load();
            } catch (IOException ex) {
                log.error("Erro ao carregar popup de Progresso", ex);
                Dialogs.showErrorDialog(getStage(), "Erro ao carregar popup de Progresso."
                        + "\nFavor entrar em contato com o Administrador.",
                        "Erro!", "Erro", ex);
            }

            ProgressoController progressoController = fxmlLoader.getController();
            progressoController.initData("Exportando os dados para o arquivo. Aguarde...");
            progressoController.setProgresso(-1.0);

            final Stage dialogStage = new Stage();
            dialogStage.setTitle("Por Favor Aguarde...");
            dialogStage.toFront();
            dialogStage.setResizable(false);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(this.anchorPane.getScene().getWindow());
            dialogStage.setScene(new Scene(root));

            final Task task = new Task<Void>() {
                @Override
                public Void call() {
                    try {
                        if (!BD.doBakup(diretorioExportacao.toString())) {
                            cancel(true);
                        }
                    } catch (IOException | InterruptedException | ZipException | SQLException ex) {
                        log.error("Erro ao realizar backup do banco", ex);
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
                            Dialogs.showInformationDialog(getStage(), "Dados exportados com sucesso!",
                                    "Sucesso!", "Informação");
                            dialogStage.close();
                            initComponents();
                            break;
                        case CANCELLED:
                        case FAILED:
                            Dialogs.showErrorDialog(getStage(), "Erro ao exportar dados."
                                    + "\nFavor entrar em contato com o Administrador.",
                                    "Erro!", "Erro");
                            dialogStage.close();
                            break;
                        case SCHEDULED:
                        case READY:
                            break;
                        default:
                            dialogStage.close();
                            break;
                    }
                }
            });
            new Thread(task).start();
        }
    }

    @FXML
    private void onActionFromItemDefinirPreferenciaTons(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/configuracoes/DefinirPreferenciaTons.fxml"));
        AnchorPane root;
        try {
            root = (AnchorPane) fxmlLoader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Definir Preferência de Tons");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(this.anchorPane.getScene().getWindow());
            dialogStage.setScene(new Scene(root));
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
        } catch (IOException ex) {
            log.error("Erro ao carregar tela para Definição de Preferência de Tons", ex);
            Dialogs.showErrorDialog(HomeController.getInstancia().getStage(),
                    "Erro ao carregar tela para Definição de Preferência de Tons."
                    + "\nFavor entrar em contato com o Administrador.",
                    "Erro!", "Erro", ex);
        }

    }

    @FXML
    private void onActionFromItemTemplateEmail(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/configuracoes/ConfiguracaoTemplateEmail.fxml"));
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException ex) {
            log.error("Erro ao carregar tela de Configuração de Template de E-mail", ex);
            Dialogs.showErrorDialog(getStage(), "Erro ao carregar tela de Configuração de Template de E-mail."
                    + "\nFavor entrar em contato com o Administrador.",
                    "Erro!", "Erro", ex);
        }

        //Limpa o conteúdo anterior e carrega a página
        this.contentAnchorPane.getChildren().clear();
        this.contentAnchorPane.getChildren().add(root);
        EfeitosUtil.rodarEfeitoCarregamentoFade(root);
    }

    @FXML
    @Deprecated
    private void onActionFromItemEscolherDiretorioBancoDados(ActionEvent event) {
        //SNU 2.0 BETA
    }

    @FXML
    private void onActionFromItemVerificarLog(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/ajuda/VerificarLog.fxml"));
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException ex) {
            log.error("Erro ao carregar tela de Verificação de Log", ex);
            Dialogs.showErrorDialog(getStage(), "Erro ao carregar tela de Verificação de Log."
                    + "\nFavor entrar em contato com o Administrador.",
                    "Erro!", "Erro", ex);
        }

        //Limpa o conteúdo anterior e carrega a página
        this.contentAnchorPane.getChildren().clear();
        this.contentAnchorPane.getChildren().add(root);
        EfeitosUtil.rodarEfeitoCarregamentoFade(root);
    }

    @FXML
    private void onActionFromItemSobre(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/ajuda/Sobre.fxml"));
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException ex) {
            log.error("Erro ao carregar tela de informações sobre o software", ex);
            Dialogs.showErrorDialog(getStage(), "Erro ao carregar tela de informações sobre o software."
                    + "\nFavor entrar em contato com o Administrador.",
                    "Erro!", "Erro", ex);
        }

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Sobre");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(this.anchorPane.getScene().getWindow());
        dialogStage.setScene(new Scene(root));
        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();
    }

    public Stage getStage() {
        return (Stage) this.contentAnchorPane.getScene().getWindow();
    }

    /**
     * Obtém a instância Singleton
     *
     * @return
     */
    public static FXMLDocumentController getInstancia() {
        return instancia;
    }

    @FXML
    private void onActionFromItemCadastrarIntegrante(ActionEvent event) {
    }

    @FXML
    private void onActionFromItemVisualizarDadosIntegrante(ActionEvent event) {
    }

    @FXML
    private void onActionFromItemAtualizarIntegrante(ActionEvent event) {
    }

    @FXML
    private void onActionFromItemRemoverIntegrante(ActionEvent event) {
    }
}
