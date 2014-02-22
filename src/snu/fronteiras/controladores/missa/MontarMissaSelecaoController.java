/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores.missa;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
import snu.util.EfeitosUtil;
import snu.util.ListaUtil;

/**
 * FXML Controller class
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
        
        this.tblMusicas.setCursor(Cursor.CLOSED_HAND);
    }

    private void pesquisarPorParametros() {
        ParametrosPesquisaMusica parametrosPesquisa = new ParametrosPesquisaMusica();

        List<TipoMusica> tiposMusica = new ArrayList<>();
        TipoMusica tipoSelecionado = this.comboTipo.getValue();
        if (tipoSelecionado != null) {
            tiposMusica.add(tipoSelecionado);
        }
        parametrosPesquisa.setTipos(tiposMusica);
        parametrosPesquisa.setNomeMusica(this.fldTitulo.getText());

        List<Musica> musicasEncontradas = MusicaJpaController.getInstancia()
                .findMusicasByParametrosPesquisa(parametrosPesquisa);

        this.tblMusicas.setItems(FXCollections.observableArrayList(musicasEncontradas));
        atualizarTabela();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initComponents();
    }

    @FXML
    private void onMouseClickedFromContentMontarMissaSelecao(MouseEvent event) {
        this.contentMontarMissaSelecao.requestFocus();
    }

    @FXML
    private void onActionFromBtnAvancar(ActionEvent event) {
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
    private void onActionFromComboTipo(ActionEvent event) {
        pesquisarPorParametros();
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

    @FXML
    private void onDragDoneFromTblMusicas(DragEvent event) {   
    }

    @FXML
    private void onDragDetectedFromTblMusicas(MouseEvent event) {       
        ClipboardContent content = new ClipboardContent();
        content.putString(this.tblMusicas.getSelectionModel().getSelectedItem().getTitulo());
        content.putImage(new Image("/snu/fronteiras/images/igreja.jpg"));
        Dragboard db = tblMusicas.startDragAndDrop(TransferMode.ANY);
        db.setContent(content);
    }

    @FXML
    private void onDragExitedImgIgreja(DragEvent event) {   
        this.imgIgreja.setEffect(null);
        this.imgIgreja.setScaleX(1);
        this.imgIgreja.setScaleY(1);
    }

    @FXML
    private void onDragEnteredImgIgreja(DragEvent event) {        
        this.imgIgreja.setEffect(EfeitosUtil.getEfeitoValido());
        this.imgIgreja.setScaleX(1.25);
        this.imgIgreja.setScaleY(1.25);
    }
}
