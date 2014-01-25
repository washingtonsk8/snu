/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores.musica;

import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.util.Callback;
import snu.controladores.AutorJpaController;
import snu.entidades.musica.Autor;
import snu.entidades.musica.Musica;

/**
 * FXML Controller class
 *
 * @author Washington Luis
 */
public class GerenciarAutoresController implements Initializable {

    @FXML
    private AnchorPane contentGerenciarAutores;
    @FXML
    private Label lblGerenciarAutores;
    @FXML
    private TextField fldPesquisarAutor;
    @FXML
    private ImageView imgPesquisaAutor;
    @FXML
    private Button btnAdicionarAutor;
    @FXML
    private TableView<Autor> tblAutores;
    @FXML
    private Button btnEditarAutor;
    @FXML
    private Button btnRemoverAutor;

    private ObservableList<Autor> autores;
    @FXML
    private Font x1;
    @FXML
    private TableColumn<Autor, String> clnNomeAutor;
    @FXML
    private TableColumn<Autor, String> clnQuantidadeMusicas;

    private void initComponents() {
        this.autores = FXCollections.observableArrayList(AutorJpaController.getInstancia().findAutorEntities());

        this.clnNomeAutor.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Autor, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Autor, String> autor) {
                return new SimpleStringProperty(autor.getValue().getNome());
            }
        });

        this.clnQuantidadeMusicas.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Autor, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Autor, String> autor) {
                return new SimpleStringProperty(
                        String.valueOf(AutorJpaController.getInstancia()
                                .getMusicasAutoriaCount(autor.getValue().getId())));
            }
        });

        Collections.sort(this.autores, new Comparator<Autor>() {
            @Override
            public int compare(Autor autor1, Autor autor2) {
                return autor1.getNome().compareTo(autor2.getNome());
            }
        });
        this.tblAutores.setItems(this.autores);

        this.btnEditarAutor.setVisible(false);
        this.btnRemoverAutor.setVisible(false);
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initComponents();
    }

    @FXML
    private void onActionFromFldPesquisarAutor(ActionEvent event) {
    }

    @FXML
    private void onKeyReleasedFromFldPesquisarAutor(KeyEvent event) {
        String textoPesquisa = this.fldPesquisarAutor.getText().toLowerCase();
        if (!textoPesquisa.isEmpty()) {
            ObservableList<Autor> autoresFiltrados = FXCollections.observableArrayList();

            for (Autor autor : this.autores) {
                String nomeAutor = autor.getNome().toLowerCase();
                if (nomeAutor.contains(textoPesquisa)) {
                    autoresFiltrados.add(autor);
                }
            }

            this.tblAutores.setItems(autoresFiltrados);
            atualizarTabela();
        } else {
            this.tblAutores.setItems(this.autores);
            this.btnAdicionarAutor.setVisible(false);
        }
    }

    @FXML
    private void onMouseClickedFromFldPesquisarAutor(MouseEvent event) {
        this.btnAdicionarAutor.setVisible(true);
        this.btnEditarAutor.setVisible(false);
        this.btnRemoverAutor.setVisible(false);

    }

    @FXML
    private void onMouseClickedFromContentGerenciarAutores(MouseEvent event) {
    }

    @FXML
    private void onActionFromBtnAdicionarAutor(ActionEvent event) {
    }

    @FXML
    private void onMouseClickedFromTblAutores(MouseEvent event) {
        this.btnAdicionarAutor.setVisible(false);
        this.btnEditarAutor.setVisible(true);
        this.btnRemoverAutor.setVisible(true);
    }

    @FXML
    private void onActionFromBtnEditarAutor(ActionEvent event) {
    }

    @FXML
    private void onActionFromBtnRemoverAutor(ActionEvent event) {
    }

    private void atualizarTabela() {
        final List<Autor> itens = this.tblAutores.getItems();
        if (itens == null || itens.isEmpty()) {
            return;
        }

        final Autor item = this.tblAutores.getItems().get(0);
        itens.remove(0);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                itens.add(0, item);
            }
        });
    }
}
