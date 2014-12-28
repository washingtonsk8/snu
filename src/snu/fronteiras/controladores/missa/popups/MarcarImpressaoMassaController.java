/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores.missa.popups;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import org.apache.log4j.Logger;
import snu.controladores.MusicaJpaController;
import snu.entidades.musica.Musica;
import snu.fronteiras.controladores.HomeController;
import snu.fronteiras.controladores.geral.ProgressoController;
import snu.util.Dialogs;
import snu.util.EfeitosUtil;

/**
 * FXML Controller class
 *
 * @author User
 */
public class MarcarImpressaoMassaController implements Initializable {

    @FXML
    private AnchorPane popupMarcarImpressaoMassa;
    @FXML
    private Font x1;
    @FXML
    private ListView<Musica> listMarcarImpressaoMassa;
    @FXML
    private Label lblMarcarImpressaoEmMassa;
    @FXML
    private Button btnCancelarSelecoes;
    @FXML
    private ImageView iconeCancelarSelecoes;
    @FXML
    private ImageView iconeImpressao;
    @FXML
    private Button btnOk;
    @FXML
    private ImageView iconeOk;

    private List<Musica> musicasSelecionadas;

    private List<Musica> musicasNaoImpressas;

    //Inicializando o Logger
    private static final Logger log = Logger.getLogger(MarcarImpressaoMassaController.class.getName());

    private void initComponents() {
        this.musicasSelecionadas = new ArrayList<>();
    }

    public void initData(List<Musica> musicasNaoImpressas) {
        this.musicasNaoImpressas = musicasNaoImpressas;

        this.listMarcarImpressaoMassa.setCellFactory(new Callback<ListView<Musica>, ListCell<Musica>>() {
            @Override
            public ListCell<Musica> call(ListView<Musica> autores) {
                ListCell<Musica> celulas = new ListCell<Musica>() {
                    @Override
                    protected void updateItem(Musica musica, boolean bln) {
                        super.updateItem(musica, bln);
                        if (musica != null) {
                            setText(musica.getTitulo());
                        } else {
                            setText(null);
                        }
                    }
                };
                return celulas;
            }
        });

        this.listMarcarImpressaoMassa.setItems(
                FXCollections.observableArrayList(this.musicasNaoImpressas));
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
    private void onMouseClickedFromListMarcarImpressaoMassa(MouseEvent event) {
        if (event.getClickCount() == 2) {
            final Musica musicaArrastada = this.listMarcarImpressaoMassa.getSelectionModel().getSelectedItem();
            this.listMarcarImpressaoMassa.getItems().remove(musicaArrastada);
            this.musicasSelecionadas.add(musicaArrastada);
            atualizarLista();
            this.listMarcarImpressaoMassa.getSelectionModel().select(-1);
        }
    }

    @FXML
    private void onActionFromBtnCancelarSelecoes(ActionEvent event) {
        this.musicasSelecionadas.clear();
        this.listMarcarImpressaoMassa.setItems(
                FXCollections.observableArrayList(this.musicasNaoImpressas));
        atualizarLista();
    }

    @FXML
    private void onActionFromBtnOk(ActionEvent event) {

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
        progressoController.initData("Realizando a atualização das músicas. Aguarde...");
        progressoController.setProgresso(-1.0);

        final Stage dialogStage = new Stage();
        dialogStage.setTitle("Por Favor Aguarde...");
        dialogStage.toFront();
        dialogStage.setResizable(false);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(this.popupMarcarImpressaoMassa.getScene().getWindow());
        dialogStage.setScene(new Scene(root));

        final Task task = new Task<Void>() {
            @Override
            public Void call() {
                MusicaJpaController musicaJpaController = MusicaJpaController.getInstancia();
                try {
                    musicaJpaController.marcarComoImpressas(musicasSelecionadas);
                } catch (Exception ex) {
                    log.error("Erro ao marcar músicas como impressas.");
                    Dialogs.showErrorDialog(HomeController.getInstancia().getStage(),
                            "Erro ao marcar músicas como impressas.",
                            "Erro!", "Erro", ex);
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
                                "As Músicas foram atualizadas com sucesso!", "Sucesso!", "Informação");
                        dialogStage.close();
                        popupMarcarImpressaoMassa.getScene().getWindow().hide();
                        break;
                    case CANCELLED:
                    case FAILED:
                        Dialogs.showErrorDialog(HomeController.getInstancia().getStage(),
                                "Erro ao marcar músicas como impressas.",
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

    @FXML
    private void onDragOverIconeImpressao(DragEvent event) {

        /* accept it only if it is  not dragged from the same node 
         * and if it has a string data */
        if (event.getGestureSource() != this.iconeImpressao
                && event.getDragboard().hasString()) {
            /* allow for both copying and moving, whatever user chooses */
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
        event.consume();
    }

    @FXML
    private void onDragDroppedIconeImpressao(DragEvent event) {
        /* if there is a string data on dragboard, read it and use it */
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasString()) {
            final Musica musicaArrastada = this.listMarcarImpressaoMassa.getSelectionModel().getSelectedItem();
            //Ocorrendo o sucesso, adiciona
            if (!this.musicasSelecionadas.add(musicaArrastada)) {
                Dialogs.showWarningDialog(HomeController.getInstancia().getStage(),
                        "A música arrastada não foi adicionada.", "Aviso!", "Aviso");
            }
            success = true;
            this.listMarcarImpressaoMassa.getItems().remove(musicaArrastada);
            this.musicasSelecionadas.add(musicaArrastada);

            atualizarLista();
        }
        this.listMarcarImpressaoMassa.getSelectionModel().select(-1);
        /* let the source know whether the string was successfully 
         * transferred and used */
        event.setDropCompleted(success);

        event.consume();
    }

    @FXML
    private void onDragExitedIconeImpressao(DragEvent event) {
        this.iconeImpressao.setEffect(null);
        this.iconeImpressao.setScaleX(1);
        this.iconeImpressao.setScaleY(1);

        event.consume();
    }

    @FXML
    private void onDragEnteredIconeImpressao(DragEvent event) {
        /* show to the user that it is an actual gesture target */
        if (event.getGestureSource() != this.iconeImpressao
                && event.getDragboard().hasString()) {
            this.iconeImpressao.setEffect(EfeitosUtil.getEfeitoValido());
            this.iconeImpressao.setScaleX(1.25);
            this.iconeImpressao.setScaleY(1.25);
        }

        event.consume();
    }

    @FXML
    private void onDragDetectedFromListMarcarImpressaoMassa(MouseEvent event) {
        ClipboardContent content = new ClipboardContent();
        Musica musicaArrastada = this.listMarcarImpressaoMassa.getSelectionModel().getSelectedItem();
        if (musicaArrastada != null) {
            content.putString(musicaArrastada.getTitulo());
        }
        Dragboard db = this.listMarcarImpressaoMassa.startDragAndDrop(TransferMode.ANY);
        db.setContent(content);
    }

    private void atualizarLista() {
        final List<Musica> itens = this.listMarcarImpressaoMassa.getItems();
        if (itens == null || itens.isEmpty()) {
            return;
        }

        final Musica item = this.listMarcarImpressaoMassa.getItems().get(0);
        itens.remove(0);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                itens.add(0, item);
            }
        });
    }
}
