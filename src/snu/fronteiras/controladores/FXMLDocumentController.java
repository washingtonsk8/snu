/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import org.apache.log4j.Logger;
import snu.bd.BDDump;
import snu.controladores.ConfiguracoesSistemaJpaController;
import snu.controladores.SNU;
import snu.fronteiras.controladores.geral.ProgressoController;
import snu.fronteiras.controladores.integrante.CadastrarIntegranteController;
import snu.fronteiras.controladores.integrante.TemplatePesquisaIntegranteController;
import snu.fronteiras.controladores.missa.MontarMissaSelecaoController;
import snu.fronteiras.controladores.musica.CriarMusicaController;
import snu.fronteiras.controladores.musica.TemplatePesquisaMusicaController;
import snu.geral.TipoPagina;

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
    private Menu menuUtilitarios;
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
    private Menu menuConfiguracoesMusica;
    @FXML
    private MenuItem itemTemplateEmail;
    @FXML
    private Menu menuBancoDados;
    @FXML
    private MenuItem itemEscolhaDiretorioBancoDados;
    @FXML
    private Menu menuHome;
    @FXML
    private ImageView imgHome;
    @FXML
    private CustomMenuItem itemAuxiliarCarregamentoPaginaInicial;

    private FXMLLoader templatePesquisaIntegranteLoader;

    private FXMLLoader templatePesquisaMusicaLoader;

    private FXMLLoader templatePesquisaMissaLoader;

    /**
     * Fábrica Singleton do sistema
     */
    private static FXMLDocumentController instancia;

    //Inicializando o Logger
    private static final Logger log = Logger.getLogger(FXMLDocumentController.class.getName());

    private void iniciarControladorPesquisaIntegrante() {
        this.templatePesquisaIntegranteLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/integrante/TemplatePesquisaIntegrante.fxml"));

        try {
            this.templatePesquisaIntegranteLoader.load();
        } catch (IOException ex) {
            log.error("Erro ao carregar tela de Template de Pesquisa de Integrante", ex);
            Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(),
                    "Erro de processamento interno.\nFavor entrar em contato com o Administrador.", "Erro interno!", "Erro", ex);
        }
    }

    private void iniciarControladorPesquisaMusica() {
        this.templatePesquisaMusicaLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/musica/TemplatePesquisaMusica.fxml"));

        try {
            this.templatePesquisaMusicaLoader.load();
        } catch (IOException ex) {
            log.error("Erro ao carregar tela de Template de Pesquisa de Música", ex);
            Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(),
                    "Erro de processamento interno.\nFavor entrar em contato com o Administrador.", "Erro interno!", "Erro", ex);
        }
    }

    private void iniciarControladorPesquisaMissa() {
        this.templatePesquisaMissaLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/missa/VisualizarDadosMissa.fxml"));

        try {
            this.templatePesquisaMissaLoader.load();
        } catch (IOException ex) {
            log.error("Erro ao carregar tela de Template de Pesquisa de Missa", ex);
            Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(),
                    "Erro de processamento interno.\nFavor entrar em contato com o Administrador.", "Erro interno!", "Erro", ex);
        }
    }

    private void iniciarPaginaInicial() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/Home.fxml"));
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException ex) {
            log.error("Erro ao carregar tela inicial", ex);
            Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(),
                    "Erro ao carregar tela inicial!\nFavor entrar em contato com o Administrador.", "Erro!", "Erro", ex);
        }

        //Limpa o conteúdo anterior e carrega a página
        this.contentAnchorPane.getChildren().clear();
        this.contentAnchorPane.getChildren().add(root);
    }

    private void initComponents() {
        instancia = this;
        iniciarControladorPesquisaIntegrante();
        iniciarControladorPesquisaMusica();
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

    public FXMLLoader getTemplatePesquisaIntegranteLoader() {
        return templatePesquisaIntegranteLoader;
    }

    public FXMLLoader getTemplatePesquisaMusicaLoader() {
        return templatePesquisaMusicaLoader;
    }

    public FXMLLoader getTemplatePesquisaMissaLoader() {
        return templatePesquisaMissaLoader;
    }

    @FXML
    private void onShowingFromMenuHome(Event event) {
        event.consume();
        iniciarPaginaInicial();
    }

    @FXML
    private void onActionFromItemCadastrarIntegrante(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/integrante/CadastrarIntegrante.fxml"));
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException ex) {
            log.error("Erro ao carregar tela de Cadastro de Integrante", ex);
            Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(),
                    "Erro ao carregar tela de Cadastro de Integrante!\nFavor entrar em contato com o Administrador.",
                    "Erro!", "Erro", ex);
        }

        CadastrarIntegranteController cadastrarIntegranteController = fxmlLoader.getController();
        cadastrarIntegranteController.initData(this);

        //Limpa o conteúdo anterior e carrega a página
        this.contentAnchorPane.getChildren().clear();
        this.contentAnchorPane.getChildren().add(root);
    }

    @FXML
    private void onActionFromItemVisualizarDadosIntegrante(ActionEvent event) {
        TemplatePesquisaIntegranteController controlador = this.templatePesquisaIntegranteLoader.getController();
        controlador.setTipoPagina(TipoPagina.PESQUISA_VISUALIZACAO_DADOS);

        //Limpa o conteúdo anterior e carrega a página
        this.contentAnchorPane.getChildren().clear();
        this.contentAnchorPane.getChildren().add((Parent) this.templatePesquisaIntegranteLoader.getRoot());
    }

    @FXML
    private void onActionFromItemAtualizarIntegrante(ActionEvent event) {
        TemplatePesquisaIntegranteController controlador = this.templatePesquisaIntegranteLoader.getController();
        controlador.setTipoPagina(TipoPagina.PESQUISA_ATUALIZACAO_DADOS);

        //Limpa o conteúdo anterior e carrega a página
        this.contentAnchorPane.getChildren().clear();
        this.contentAnchorPane.getChildren().add((Parent) this.templatePesquisaIntegranteLoader.getRoot());
    }

    @FXML
    private void onActionFromItemRemoverIntegrante(ActionEvent event) {
        TemplatePesquisaIntegranteController controlador = this.templatePesquisaIntegranteLoader.getController();
        controlador.setTipoPagina(TipoPagina.PESQUISA_REMOCAO);

        //Limpa o conteúdo anterior e carrega a página
        this.contentAnchorPane.getChildren().clear();
        this.contentAnchorPane.getChildren().add((Parent) this.templatePesquisaIntegranteLoader.getRoot());
    }

    @FXML
    private void onActionFromItemCriarMusica(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/musica/CriarMusica.fxml"));
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException ex) {
            log.error("Erro ao carregar tela de Criação de Música", ex);
            Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(),
                    "Erro ao carregar tela de Criação de Música!\nFavor entrar em contato com o Administrador.",
                    "Erro!", "Erro", ex);
        }

        CriarMusicaController criarIntegranteController = fxmlLoader.getController();
        criarIntegranteController.initData(this);

        //Limpa o conteúdo anterior e carrega a página
        this.contentAnchorPane.getChildren().clear();
        this.contentAnchorPane.getChildren().add(root);
    }

    @FXML
    private void onActionFromItemVisualizarDadosMusica(ActionEvent event) {
        TemplatePesquisaMusicaController controlador = this.templatePesquisaMusicaLoader.getController();
        controlador.setTipoPagina(TipoPagina.PESQUISA_VISUALIZACAO_DADOS);

        //Limpa o conteúdo anterior e carrega a página
        this.contentAnchorPane.getChildren().clear();
        this.contentAnchorPane.getChildren().add((Parent) this.templatePesquisaMusicaLoader.getRoot());
    }

    @FXML
    private void onActionFromItemAtualizarDadosMusica(ActionEvent event) {
        TemplatePesquisaMusicaController controlador = this.templatePesquisaMusicaLoader.getController();
        controlador.setTipoPagina(TipoPagina.PESQUISA_ATUALIZACAO_DADOS);

        //Limpa o conteúdo anterior e carrega a página
        this.contentAnchorPane.getChildren().clear();
        this.contentAnchorPane.getChildren().add((Parent) this.templatePesquisaMusicaLoader.getRoot());
    }

    @FXML
    private void onActionFromItemRemoverMusica(ActionEvent event) {
        TemplatePesquisaMusicaController controlador = this.templatePesquisaMusicaLoader.getController();
        controlador.setTipoPagina(TipoPagina.PESQUISA_REMOCAO);

        //Limpa o conteúdo anterior e carrega a página
        this.contentAnchorPane.getChildren().clear();
        this.contentAnchorPane.getChildren().add((Parent) this.templatePesquisaMusicaLoader.getRoot());
    }

    @FXML
    private void onActionFromItemGerarImpressao(ActionEvent event) {
        TemplatePesquisaMusicaController controlador = this.templatePesquisaMusicaLoader.getController();
        controlador.setTipoPagina(TipoPagina.PESQUISA_GERACAO_IMPRESSAO);

        //Limpa o conteúdo anterior e carrega a página
        this.contentAnchorPane.getChildren().clear();
        this.contentAnchorPane.getChildren().add((Parent) this.templatePesquisaMusicaLoader.getRoot());
    }

    @FXML
    private void onActionFromItemGerenciarAutores(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/musica/GerenciarAutores.fxml"));
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException ex) {
            log.error("Erro ao carregar tela de Gerência de Autores", ex);
            Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(),
                    "Erro ao carregar tela de Gerência de Autores!\nFavor entrar em contato com o Administrador.",
                    "Erro!", "Erro", ex);
        }

        //Limpa o conteúdo anterior e carrega a página
        this.contentAnchorPane.getChildren().clear();
        this.contentAnchorPane.getChildren().add(root);
    }

    @FXML
    private void onActionFromItemMontarMissa(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/missa/MontarMissaSelecao.fxml"));
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException ex) {
            log.error("Erro ao carregar tela de Montagem de Missa", ex);
            Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(),
                    "Erro ao carregar tela de Montagem de Missa!\nFavor entrar em contato com o Administrador.",
                    "Erro!", "Erro", ex);
        }

        MontarMissaSelecaoController montarMissaSelecaoController = fxmlLoader.getController();
        montarMissaSelecaoController.initData(this);

        //Limpa o conteúdo anterior e carrega a página
        this.contentAnchorPane.getChildren().clear();
        this.contentAnchorPane.getChildren().add(root);
    }

    @FXML
    private void onActionFromPesquisarMissa(ActionEvent event) {
        //Limpa o conteúdo anterior e carrega a página
        this.contentAnchorPane.getChildren().clear();
        this.contentAnchorPane.getChildren().add((Parent) this.templatePesquisaMissaLoader.getRoot());
    }

    @FXML
    private void onActionFromItemImportarDados(ActionEvent event) {
        JFileChooser seletorArquivo = new JFileChooser(".");
        seletorArquivo.addChoosableFileFilter(new FileFilter() {

            @Override
            public boolean accept(File file) {
                String filename = file.getName();
                return filename.endsWith("sql");
            }

            @Override
            public String getDescription() {
                return "*.sql";
            }

        });
        seletorArquivo.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int returnVal = seletorArquivo.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            final File arquivoImportacao = seletorArquivo.getSelectedFile();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/geral/Progresso.fxml"));
            Parent root = null;
            try {
                root = (Parent) fxmlLoader.load();
            } catch (IOException ex) {
                log.error("Erro ao carregar popup de Progresso", ex);
                Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(),
                        "Erro ao carregar popup de Progresso!\nFavor entrar em contato com o Administrador.",
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
            dialogStage.initOwner(((Node) this.anchorPane).getScene().getWindow());
            dialogStage.setScene(new Scene(root));

            final Task task = new Task<Void>() {
                @Override
                public Void call() {
                    try {
                        if (!BDDump.doRestore(arquivoImportacao.toString())) {
                            cancel(true);
                        }
                    } catch (IOException | InterruptedException ex) {
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
                            dialogStage.close();
                            Dialogs.showInformationDialog(dialogStage, "Dados importados com sucesso!",
                                    "Sucesso!", "Informação");
                            initComponents();
                            break;
                        case CANCELLED:
                        case FAILED:
                            dialogStage.close();
                            Dialogs.showErrorDialog(dialogStage, "Erro ao importar dados!"
                                    + "\nFavor entrar em contato com o Administrador.", "Erro!", "Erro");
                            break;
                    }
                }
            });
            new Thread(task).start();
        }
    }

    @FXML
    private void onActionFromItemExportarDados(ActionEvent event) {
        JFileChooser seletorDiretorio = new JFileChooser(".");
        seletorDiretorio.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnVal = seletorDiretorio.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            final File diretorioExportacao = seletorDiretorio.getSelectedFile();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/geral/Progresso.fxml"));
            Parent root = null;
            try {
                root = (Parent) fxmlLoader.load();
            } catch (IOException ex) {
                log.error("Erro ao carregar popup de Progresso", ex);
                Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(),
                        "Erro ao carregar popup de Progresso!\nFavor entrar em contato com o Administrador.", "Erro!", "Erro", ex);
            }

            ProgressoController progressoController = fxmlLoader.getController();
            progressoController.initData("Exportando os dados para o arquivo. Aguarde...");
            progressoController.setProgresso(-1.0);

            final Stage dialogStage = new Stage();
            dialogStage.setTitle("Por Favor Aguarde...");
            dialogStage.toFront();
            dialogStage.setResizable(false);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(((Node) this.anchorPane).getScene().getWindow());
            dialogStage.setScene(new Scene(root));

            final Task task = new Task<Void>() {
                @Override
                public Void call() {
                    try {
                        if (!BDDump.doBakup(diretorioExportacao.toString())) {
                            cancel(true);
                        }
                    } catch (IOException | InterruptedException ex) {
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
                            dialogStage.close();
                            Dialogs.showInformationDialog(dialogStage, "Dados exportados com sucesso!", "Sucesso", "Informação");
                            initComponents();
                            break;
                        case CANCELLED:
                        case FAILED:
                            dialogStage.close();
                            Dialogs.showErrorDialog(dialogStage, "Erro ao exportar dados!"
                                    + "\nFavor entrar em contato com o Administrador.", "Erro!", "Erro");
                            break;
                    }
                }
            });
            new Thread(task).start();
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
            Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(),
                    "Erro ao carregar tela de Configuração de Template de E-mail!"
                    + "\nFavor entrar em contato com o Administrador.", "Erro!", "Erro", ex);
        }

        //Limpa o conteúdo anterior e carrega a página
        this.contentAnchorPane.getChildren().clear();
        this.contentAnchorPane.getChildren().add(root);
    }

    @FXML
    private void onActionFromItemEscolhaDiretorioBancoDados(ActionEvent event) {
        JFileChooser seletorDiretorio = new JFileChooser("C:");
        seletorDiretorio.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        seletorDiretorio.setDialogTitle("Escolha do diretório do SGBD");
        String diretorioSGBD = "";
        int returnVal = seletorDiretorio.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File arquivo = seletorDiretorio.getSelectedFile();
            diretorioSGBD = arquivo.toString();
        }
        SNU.configuracoesSistema.setDiretorioSGBD(diretorioSGBD);
        try {
            ConfiguracoesSistemaJpaController.getInstancia().edit(SNU.configuracoesSistema);
        } catch (Exception ex) {
            log.error("Erro ao atualizar as configurações do sistema", ex);
            Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(),
                    "Erro ao atualizar o diretório do Sistema de Gerenciamento de Banco de Dados!"
                    + "\nFavor entrar em contato com o Administrador.", "Erro!", "Erro", ex);
        }
    }

    @FXML
    private void onActionFromItemSobre(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/ajuda/Sobre.fxml"));
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException ex) {
            log.error("Erro ao carregar tela de informações sobre o software", ex);
            Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(),
                    "Erro ao carregar tela de informações sobre o software!"
                    + "\nFavor entrar em contato com o Administrador.", "Erro!", "Erro", ex);
        }

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Sobre");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(((Node) this.anchorPane).getScene().getWindow());
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
}
