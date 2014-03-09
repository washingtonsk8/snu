/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores.missa;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.util.Callback;
import snu.controladores.MusicaJpaController;
import snu.dto.ParametrosPesquisaMusica;
import snu.entidades.musica.Musica;
import snu.entidades.musica.TipoMusica;
import snu.fronteiras.controladores.FXMLDocumentController;
import snu.util.EfeitosUtil;
import snu.util.ListaUtil;

/**
 * Classe controladora do FXML
 *
 * @author Washington Luis
 */
public class MontarMissaSelecaoController implements Initializable {

    @FXML
    private AnchorPane contentMontarMissaSelecao;
    @FXML
    private Label lblMontarMissaSelecao;
    @FXML
    private Label lblInformacaoPasso;
    @FXML
    private Font x1;
    @FXML
    private Button btnAvancar;
    @FXML
    private TableView<Musica> tblMusicas;
    @FXML
    private TableColumn<Musica, String> clnTituloMusica;
    @FXML
    private TableColumn<Musica, String> clnTipoMusica;
    @FXML
    private Button btnPesquisar;
    @FXML
    private Label lblTitulo;
    @FXML
    private Font x2;
    @FXML
    private Label lblTipo;
    @FXML
    private ImageView imgIgreja;
    @FXML
    private TextField fldTitulo;
    @FXML
    private ComboBox<TipoMusica> comboTipo;
    @FXML
    private Label lblInformacaoFuncionamento;

    private Set<Musica> musicasSelecionadas;

    private final ObservableList<TipoMusica> tiposMusica
            = FXCollections.observableList(Arrays.asList(TipoMusica.values()));

    private FXMLDocumentController controladorPrincipal;

    private void initComponents() {
        this.clnTituloMusica.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Musica, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Musica, String> musica) {
                return new SimpleStringProperty(musica.getValue().getTitulo());
            }
        });
        this.clnTipoMusica.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Musica, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Musica, String> musica) {
                String celula = ListaUtil.getListaSeparadaPorPontoVirgula(musica.getValue().getTipos());

                return new SimpleStringProperty(celula);
            }
        });

        this.musicasSelecionadas = new HashSet<>();

        this.comboTipo.setItems(this.tiposMusica);
    }

    private void pesquisarPorParametros() {
        try {
            ParametrosPesquisaMusica parametrosPesquisa = new ParametrosPesquisaMusica();

            List<TipoMusica> tipoMusica = new ArrayList<>();
            TipoMusica tipoSelecionado = this.comboTipo.getValue();
            if (tipoSelecionado != null) {
                tipoMusica.add(tipoSelecionado);
            }
            parametrosPesquisa.setTipos(tipoMusica);
            parametrosPesquisa.setNomeMusica(this.fldTitulo.getText());

            List<Musica> musicasEncontradas = MusicaJpaController.getInstancia()
                    .findMusicasByParametrosPesquisa(parametrosPesquisa);

            this.tblMusicas.setItems(FXCollections.observableArrayList(musicasEncontradas));
            atualizarTabela();
        } catch (IOException ex) {
            Logger.getLogger(MontarMissaSelecaoController.class.getName()).log(Level.SEVERE, null, ex);
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

    public void initData(FXMLDocumentController controladorPrincipal) {
        this.controladorPrincipal = controladorPrincipal;
    }

    @FXML
    private void onMouseClickedFromContentMontarMissaSelecao(MouseEvent event) {
        this.contentMontarMissaSelecao.requestFocus();
    }

    @FXML
    private void onActionFromBtnAvancar(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/missa/MontarMissaOrganizacao.fxml"));

        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(MontarMissaSelecaoController.class.getName()).log(Level.SEVERE, null, ex);
        }

        MontarMissaOrganizacaoController montarMissaOrganizacaoController = fxmlLoader.getController();

        //Limpa o conteúdo anterior e carrega a página
        AnchorPane pai = ((AnchorPane) this.contentMontarMissaSelecao.getParent());
        montarMissaOrganizacaoController.initData(this.musicasSelecionadas, this, this.controladorPrincipal);
        pai.getChildren().clear();
        pai.getChildren().add(root);
    }

    @FXML
    private void onActionFromBtnPesquisar(ActionEvent event) {
        pesquisarPorParametros();
    }

    @FXML
    private void onMouseClickedFromLblTitulo(MouseEvent event) {
        this.fldTitulo.requestFocus();
    }

    @FXML
    private void onActionFromFldTitulo(ActionEvent event) {
        pesquisarPorParametros();
    }

    @FXML
    private void onDragDetectedFromTblMusicas(MouseEvent event) {
        ClipboardContent content = new ClipboardContent();
        content.putString(this.tblMusicas.getSelectionModel().getSelectedItem().getTitulo());
        Dragboard db = this.tblMusicas.startDragAndDrop(TransferMode.ANY);
        db.setContent(content);
    }

    @FXML
    private void onDragExitedImgIgreja(DragEvent event) {
        this.imgIgreja.setEffect(null);
        this.imgIgreja.setScaleX(1);
        this.imgIgreja.setScaleY(1);

        event.consume();
    }

    @FXML
    private void onDragEnteredImgIgreja(DragEvent event) {
        /* show to the user that it is an actual gesture target */
        if (event.getGestureSource() != this.imgIgreja
                && event.getDragboard().hasString()) {
            this.imgIgreja.setEffect(EfeitosUtil.getEfeitoValido());
            this.imgIgreja.setScaleX(1.25);
            this.imgIgreja.setScaleY(1.25);
        }

        event.consume();
    }

    @FXML
    private void onDragDroppedImgIgreja(DragEvent event) {
        /* if there is a string data on dragboard, read it and use it */
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasString()) {//Ocorrendo o sucesso, adiciona
            if (!this.musicasSelecionadas.add(this.tblMusicas.getSelectionModel().getSelectedItem())) {
                //TODO: Reportar erro!
            }
            success = true;
        }
        /* let the source know whether the string was successfully 
         * transferred and used */
        event.setDropCompleted(success);

        event.consume();
    }

    @FXML
    private void onDragOverImgIgreja(DragEvent event) {

        /* accept it only if it is  not dragged from the same node 
         * and if it has a string data */
        if (event.getGestureSource() != this.imgIgreja
                && event.getDragboard().hasString()) {
            /* allow for both copying and moving, whatever user chooses */
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
        event.consume();
    }

    public AnchorPane getContent() {
        return this.contentMontarMissaSelecao;
    }

    private void atualizarTabela() {
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
