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
import snu.dto.QuantidadeAutoriaDTO;
import snu.entidades.musica.Autor;
import snu.entidades.musica.Musica;
import snu.util.StringUtil;

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
    private TableView<QuantidadeAutoriaDTO> tblAutores;
    @FXML
    private Button btnEditarAutor;
    @FXML
    private Button btnRemoverAutor;

    private ObservableList<QuantidadeAutoriaDTO> autores;
    @FXML
    private Font x1;
    @FXML
    private TableColumn<QuantidadeAutoriaDTO, String> clnNomeAutor;
    @FXML
    private TableColumn<QuantidadeAutoriaDTO, String> clnQuantidadeMusicas;

    private void initComponents() {
        AutorJpaController autorController = AutorJpaController.getInstancia();
        List<Autor> entidadesAutor = autorController.findAutorEntities();
        this.autores = FXCollections.observableArrayList();

        //Varre a lista de entidades preenchendo a lista DTO a ser apresentada
        for (Autor entidadeAutor : entidadesAutor) {
            QuantidadeAutoriaDTO quantidadeAutoriaDTO = new QuantidadeAutoriaDTO();
            quantidadeAutoriaDTO.setAutor(entidadeAutor);
            quantidadeAutoriaDTO.setQuantidadeMusicasDeAutoria(autorController.getMusicasAutoriaCount(entidadeAutor.getId()));
            this.autores.add(quantidadeAutoriaDTO);
        }

        this.clnNomeAutor.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<QuantidadeAutoriaDTO, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<QuantidadeAutoriaDTO, String> quantidadeAutoriaDTO) {
                return new SimpleStringProperty(quantidadeAutoriaDTO.getValue().getAutor().getNome());
            }
        });

        this.clnQuantidadeMusicas.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<QuantidadeAutoriaDTO, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<QuantidadeAutoriaDTO, String> quantidadeAutoriaDTO) {
                return new SimpleStringProperty(quantidadeAutoriaDTO.getValue().getQuantidadeMusicasDeAutoria().toString());
            }
        });

        Collections.sort(this.autores, new Comparator<QuantidadeAutoriaDTO>() {
            @Override
            public int compare(QuantidadeAutoriaDTO quantidadeAutoriaDTO1, QuantidadeAutoriaDTO quantidadeAutoriaDTO2) {
                return quantidadeAutoriaDTO1.getAutor().getNome().compareTo(quantidadeAutoriaDTO2.getAutor().getNome());
            }
        });
        this.tblAutores.setItems(this.autores);

        this.btnAdicionarAutor.setVisible(false);
        this.btnEditarAutor.setVisible(false);
        this.btnRemoverAutor.setVisible(false);
    }

    private void filtrarTabela(String textoPesquisa) {
        ObservableList<QuantidadeAutoriaDTO> autoresFiltrados = FXCollections.observableArrayList();

        for (QuantidadeAutoriaDTO quantidadeAutoriaDTO : this.autores) {
            String nomeAutor = quantidadeAutoriaDTO.getAutor().getNome().toLowerCase();
            if (nomeAutor.contains(textoPesquisa)) {
                autoresFiltrados.add(quantidadeAutoriaDTO);
            }
        }

        this.tblAutores.setItems(autoresFiltrados);
        atualizarTabela();
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
        if (StringUtil.hasAlgo(textoPesquisa)) {
            filtrarTabela(textoPesquisa);
            this.btnAdicionarAutor.setVisible(true);
        } else {
            this.tblAutores.setItems(this.autores);
            this.btnAdicionarAutor.setVisible(false);
            atualizarTabela();
        }
    }

    @FXML
    private void onMouseClickedFromFldPesquisarAutor(MouseEvent event) {
        this.btnAdicionarAutor.setVisible(StringUtil.hasAlgo(this.fldPesquisarAutor.getText()));
        this.btnEditarAutor.setVisible(false);
        this.btnRemoverAutor.setVisible(false);
    }

    @FXML
    private void onMouseClickedFromContentGerenciarAutores(MouseEvent event) {
    }

    @FXML
    private void onActionFromBtnAdicionarAutor(ActionEvent event) {
        String textoPesquisa = this.fldPesquisarAutor.getText();

        Autor novoAutor = new Autor();
        novoAutor.setNome(textoPesquisa);

        //Persiste no banco
        AutorJpaController.getInstancia().create(novoAutor);

        //Adicioná-lo à lista de autores filtrados
        QuantidadeAutoriaDTO quantidadeAutoriaDTO = new QuantidadeAutoriaDTO();
        quantidadeAutoriaDTO.setAutor(novoAutor);
        //Não possui músicas, pois é novo autor
        quantidadeAutoriaDTO.setQuantidadeMusicasDeAutoria(0);
        this.autores.add(quantidadeAutoriaDTO);

        //TODO: Mostrar mensagem de sucesso
        
        this.fldPesquisarAutor.clear();
        this.tblAutores.setItems(this.autores);
        atualizarTabela();
        this.btnAdicionarAutor.setVisible(false);
        this.fldPesquisarAutor.requestFocus();
    }

    @FXML
    private void onMouseClickedFromTblAutores(MouseEvent event) {
        if (this.tblAutores.getSelectionModel().getSelectedItem() != null) {
            this.btnAdicionarAutor.setVisible(false);
            this.btnEditarAutor.setVisible(true);
            this.btnRemoverAutor.setVisible(true);
        }
    }

    @FXML
    private void onActionFromBtnEditarAutor(ActionEvent event) {
        //TODO: Implementar janelinha popup (talvez o diálogo já usado)
    }

    @FXML
    private void onActionFromBtnRemoverAutor(ActionEvent event) {
        //TODO: Implementar remoção
    }

    private void atualizarTabela() {
        final List<QuantidadeAutoriaDTO> itens = this.tblAutores.getItems();
        if (itens == null || itens.isEmpty()) {
            return;
        }

        final QuantidadeAutoriaDTO item = this.tblAutores.getItems().get(0);
        itens.remove(0);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                itens.add(0, item);
            }
        });
    }
}
