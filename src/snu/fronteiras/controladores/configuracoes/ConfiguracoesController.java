/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores.configuracoes;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import net.lingala.zip4j.exception.ZipException;
import org.apache.log4j.Logger;
import snu.bd.BD;
import snu.fronteiras.controladores.FXMLDocumentController;
import snu.fronteiras.controladores.HomeController;
import snu.fronteiras.controladores.geral.ProgressoController;
import snu.util.BotoesImagemUtil;
import snu.util.Dialogs;
import snu.util.EfeitosUtil;
import snu.util.SeletorArquivosUtil;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ConfiguracoesController implements Initializable {

    @FXML
    private AnchorPane contentConfiguracoes;
    @FXML
    private ImageView logoSistema;
    @FXML
    private ImageView iconeExportarDados;
    @FXML
    private ImageView iconeTemplateEmail;
    @FXML
    private ImageView imgVoltar;
    @FXML
    private ImageView iconeImportarDados;
    @FXML
    private ImageView iconeDefinirPreferenciaTons;

    //Inicializando o Logger
    private static final Logger log = Logger.getLogger(ConfiguracoesController.class.getName());

    private HomeController controladorOrigem;

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

    private void initComponents() {
        BotoesImagemUtil.definirComportamento(this.imgVoltar);

        this.logoSistema.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logoSistema.setEffect(EfeitosUtil.getEfeitoCeuAzul());
            }
        });

        this.logoSistema.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logoSistema.setEffect(null);
            }
        });

        BotoesImagemUtil.definirComportamentoTelaInicial(this.iconeTemplateEmail);
        BotoesImagemUtil.definirComportamentoTelaInicial(this.iconeExportarDados);
        BotoesImagemUtil.definirComportamentoTelaInicial(this.iconeImportarDados);
        BotoesImagemUtil.definirComportamentoTelaInicial(this.iconeDefinirPreferenciaTons);
    }

    public void initData(HomeController controladorOrigem) {
        this.controladorOrigem = controladorOrigem;
    }

    @FXML
    private void onClickFromIconeExportarDados(MouseEvent event) {
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
                Dialogs.showErrorDialog(HomeController.getInstancia().getStage(), "Erro ao carregar popup de Progresso."
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
            dialogStage.initOwner(this.contentConfiguracoes.getScene().getWindow());
            dialogStage.setScene(new Scene(root));

            final Task task = new Task<Void>() {
                @Override
                public Void call() {
                    try {
                        if (!BD.doBakup(diretorioExportacao.getAbsolutePath())) {
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
                            Dialogs.showInformationDialog(HomeController.getInstancia().getStage(), "Dados exportados com sucesso!",
                                    "Sucesso!", "Informação");
                            dialogStage.close();
                            break;
                        case CANCELLED:
                        case FAILED:
                            Dialogs.showErrorDialog(HomeController.getInstancia().getStage(), "Erro ao exportar dados."
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
    private void onClickFromIconeTemplateEmail(MouseEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/configuracoes/ConfiguracaoTemplateEmail.fxml"));

        try {
            Parent root = (Parent) fxmlLoader.load();

            //Limpa o conteúdo anterior e carrega a página
            this.contentConfiguracoes.getChildren().add(root);
            EfeitosUtil.rodarEfeitoCarregamentoFadeIn(root);
        } catch (IOException ex) {
            log.error("Erro ao carregar tela de Configuração de Template de E-mail", ex);
            Dialogs.showErrorDialog(HomeController.getInstancia().getStage(), "Erro ao carregar tela de Configuração de Template de E-mail."
                    + "\nFavor entrar em contato com o Administrador.",
                    "Erro!", "Erro", ex);
        }
    }

    @FXML
    private void onClickFromIconeImportarDados(MouseEvent event) {
        String contextoUltimaSelecao = SeletorArquivosUtil.mapSeletores.get("importarDados");
        if (contextoUltimaSelecao == null) {
            contextoUltimaSelecao = System.getProperty("user.home") + "/Desktop";
        }
        final FileChooser seletorArquivo = new FileChooser();
        seletorArquivo.setInitialDirectory(new File(contextoUltimaSelecao));
        seletorArquivo.setTitle("Escolha do arquivo para importação");
        seletorArquivo.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivos de Script (*.sql)", "*.sql"));
        final File arquivoImportacao = seletorArquivo.showOpenDialog(HomeController.getInstancia().getStage());
        if (arquivoImportacao != null) {
            SeletorArquivosUtil.mapSeletores.put("importarDados", arquivoImportacao.getAbsolutePath());

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/geral/Progresso.fxml"));
            Parent root = null;
            try {
                root = (Parent) fxmlLoader.load();
            } catch (IOException ex) {
                log.error("Erro ao carregar popup de Progresso", ex);
                Dialogs.showErrorDialog(HomeController.getInstancia().getStage(), "Erro ao carregar popup de Progresso."
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
            dialogStage.initOwner(this.contentConfiguracoes.getScene().getWindow());
            dialogStage.setScene(new Scene(root));

            final Task task = new Task<Void>() {
                @Override
                public Void call() {
                    try {
                        if (!BD.doRestore(arquivoImportacao.getAbsolutePath())) {
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
                            Dialogs.showInformationDialog(HomeController.getInstancia().getStage(),
                                    "Dados importados com sucesso!", "Sucesso!", "Informação");
                            dialogStage.close();
                            break;
                        case CANCELLED:
                        case FAILED:
                            Dialogs.showErrorDialog(HomeController.getInstancia().getStage(), "Erro ao importar dados."
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
    private void onClickFromIconeDefinirPreferenciaTons(MouseEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/configuracoes/DefinirPreferenciaTons.fxml"));
        AnchorPane root;
        try {
            root = (AnchorPane) fxmlLoader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Definir Preferência de Tons");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(this.contentConfiguracoes.getScene().getWindow());
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
    private void onMouseClickedFromImgVoltar(MouseEvent event) {
        //Limpa o conteúdo anterior e carrega a página
        AnchorPane pai = ((AnchorPane) this.contentConfiguracoes.getParent());
        ObservableList<Node> filhos = pai.getChildren();

        //Roda o efeito de retorno
        TranslateTransition tt = new TranslateTransition(Duration.millis(250), filhos.get(filhos.size() - 1));

        tt.setFromX(0f);
        tt.setToX(800f);
        tt.play();

        tt.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                filhos.remove(filhos.size() - 1);
            }
        });
    }
}
