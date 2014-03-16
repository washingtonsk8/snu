/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores.missa;

import eu.schudt.javafx.controls.calendar.DatePicker;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.log4j.Logger;
import snu.controladores.MissaJpaController;
import snu.fronteiras.controladores.FXMLDocumentController;
import snu.fronteiras.controladores.geral.ProgressoController;
import snu.util.DataUtil;

/**
 * FXML Controller class
 *
 * @author User
 */
public class LimparDadosMissasController implements Initializable {

    @FXML
    private AnchorPane contentLimparDadosMissa;
    @FXML
    private Label lblLimparDados;
    @FXML
    private Button btnOk;

    private DatePicker dpDataAcontecimento;

    //Inicializando o Logger
    private static final Logger log = Logger.getLogger(MontarMissaOrganizacaoController.class.getName());

    private void initComponents() {
        //Formatando o DatePicker de Nascimento
        this.dpDataAcontecimento = DataUtil.getDatePicker();
        this.dpDataAcontecimento.setLayoutX(300);
        this.dpDataAcontecimento.setLayoutY(61);
        this.dpDataAcontecimento.setMaxWidth(100);
        this.dpDataAcontecimento.setPromptText("DD/MM/AAAA");
        this.contentLimparDadosMissa.getChildren().add(this.dpDataAcontecimento);
    }

    /**
     * Troca o foco para o contentPane da tela
     */
    public void setFocusToContent() {
        this.contentLimparDadosMissa.requestFocus();
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
    private void onMouseClickedFromContentLimparDadosMissa(MouseEvent event) {
        this.contentLimparDadosMissa.requestFocus();
    }

    @FXML
    private void onActionFromBtnOk(ActionEvent event) {
        final MissaJpaController missaJpaController = MissaJpaController.getInstancia();
        final Date dataAcontecimento = this.dpDataAcontecimento.getSelectedDate();

        if (dataAcontecimento != null) {
            Dialogs.DialogResponse resposta = Dialogs.showConfirmDialog(FXMLDocumentController.getInstancia().getStage(),
                    "A ação irá excluir todos os dados das Missas antes de "
                    + DataUtil.formatarData(dataAcontecimento)
                    + " do sistema, inclusive a relação com as músicas.\n"
                    + "\nTem certeza que deseja excluir esses dados do sistema?",
                    "Exclusão de Missa", "Confirmação");

            if (resposta.equals(Dialogs.DialogResponse.YES)) {
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

                ProgressoController progressoController = fxmlLoader.getController();
                progressoController.initData("Excluindo os dados das Missas encontradas. Aguarde...");
                progressoController.setProgresso(-1.0);

                final Stage dialogStage = new Stage();
                dialogStage.setTitle("Por Favor Aguarde...");
                dialogStage.toFront();
                dialogStage.setResizable(false);
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(FXMLDocumentController.getInstancia().getStage());
                dialogStage.setScene(new Scene(root));

                final Task task = new Task<Void>() {
                    @Override
                    public Void call() {
                        excluirMissas(missaJpaController, dataAcontecimento);
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
                                contentLimparDadosMissa.getScene().getWindow().hide();
                                dialogStage.close();
                                break;
                            case CANCELLED:
                            case FAILED:
                                contentLimparDadosMissa.getScene().getWindow().hide();
                                dialogStage.close();
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
                    "Selecione uma data, por favor.", "A data não foi selecionada!", "Aviso");
        }
    }

    private void excluirMissas(MissaJpaController missaJpaController, Date dataAcontecimento) {
        try {
            //Destrói as Missas anteriores à data passada
            final int quantidadeMissasExcluidas = missaJpaController.destroyMissasBeforeDate(dataAcontecimento);

            VisualizarDadosMissaController visualizarDadosMissaController
                    = FXMLDocumentController.getInstancia().
                    getTemplatePesquisaMissaLoader().
                    getController();

            visualizarDadosMissaController.atualizar();

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    if (quantidadeMissasExcluidas == 1) {
                        Dialogs.showInformationDialog(FXMLDocumentController.getInstancia().getStage(),
                                "Foi excluída " + quantidadeMissasExcluidas + " Missa do sistema!",
                                "Sucesso!", "Informação");
                    } else {
                        Dialogs.showInformationDialog(FXMLDocumentController.getInstancia().getStage(),
                                "Foram excluídas " + quantidadeMissasExcluidas + " Missas do sistema!",
                                "Sucesso!", "Informação");
                    }
                }
            });
        } catch (final Exception ex) {
            log.error("Erro ao deletar Missas do banco", ex);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(),
                            "Erro ao excluir as Missas.\nFavor entrar em contato com o Administrador.",
                            "Erro!", "Erro", ex);
                }
            });
        }
    }
}
