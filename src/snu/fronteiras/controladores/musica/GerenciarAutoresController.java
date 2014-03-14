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
import java.util.logging.Level;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.util.Callback;
import org.apache.log4j.Logger;
import snu.controladores.AutorJpaController;
import snu.controladores.MusicaJpaController;
import snu.exceptions.NonexistentEntityException;
import snu.dto.QuantidadeAutoriaDTO;
import snu.entidades.musica.Autor;
import snu.fronteiras.controladores.FXMLDocumentController;
import snu.util.StringUtil;

/**
 * Classe controladora do FXML
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
    @FXML
    private Font x1;
    @FXML
    private TableColumn<QuantidadeAutoriaDTO, String> clnNomeAutor;
    @FXML
    private TableColumn<QuantidadeAutoriaDTO, String> clnQuantidadeMusicas;
    @FXML
    private Label lblInformacaoQuantidades;

    private ObservableList<QuantidadeAutoriaDTO> autores;

    //Inicializando o Logger
    private static final Logger log = Logger.getLogger(GerenciarAutoresController.class.getName());

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

        this.btnEditarAutor.setVisible(false);
        this.btnRemoverAutor.setVisible(false);
        this.lblInformacaoQuantidades.setText("O sistema contém " + entidadesAutor.size() + " autor(es) e "
                + MusicaJpaController.getInstancia().getMusicaCount() + " música(s).");
    }

    private void adicionarAutor() {
        String textoPesquisa = this.fldPesquisarAutor.getText();

        if (StringUtil.hasAlgo(textoPesquisa)) {
            Dialogs.DialogResponse resposta;
            resposta = Dialogs.showConfirmDialog(FXMLDocumentController.getInstancia().getStage(),
                    "Deseja realmente adicionar o(a) Autor(a) \"" + textoPesquisa + "\" ao sistema?",
                    "Adição de Autor", "Confirmação");

            if (resposta.equals(Dialogs.DialogResponse.YES)) {
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

                this.fldPesquisarAutor.clear();
                this.tblAutores.setItems(this.autores);
                atualizarTabela();

                Dialogs.showInformationDialog(FXMLDocumentController.getInstancia().getStage(),
                        "O(A) Autor(a) foi salvo(a) com sucesso!", "Sucesso!", "Informação");

                this.fldPesquisarAutor.requestFocus();
                this.lblInformacaoQuantidades.setText("O sistema contém " + AutorJpaController.getInstancia().getAutorCount() + " autor(es) e "
                        + MusicaJpaController.getInstancia().getMusicaCount() + " música(s).");
            }
        } else {
            Dialogs.showWarningDialog(FXMLDocumentController.getInstancia().getStage(),
                    "O nome do(a) Autor(a) deve ter pelo menos 1 caractere.", "Nome vazio!", "Aviso");
        }
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
    private void onActionFromFldPesquisarAutor(ActionEvent event) {
        if (this.btnAdicionarAutor.isVisible()) {
            adicionarAutor();
        }
    }

    @FXML
    private void onKeyReleasedFromFldPesquisarAutor(KeyEvent event) {
        String textoPesquisa = this.fldPesquisarAutor.getText().toLowerCase();
        if (StringUtil.hasAlgo(textoPesquisa)) {
            filtrarTabela(textoPesquisa);
        } else {
            this.tblAutores.setItems(this.autores);
            atualizarTabela();
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
        this.btnAdicionarAutor.setVisible(true);
        this.btnEditarAutor.setVisible(false);
        this.btnRemoverAutor.setVisible(false);
        this.contentGerenciarAutores.requestFocus();
    }

    @FXML
    private void onActionFromBtnAdicionarAutor(ActionEvent event) {
        adicionarAutor();
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
        QuantidadeAutoriaDTO quantidadeAutoriaDtoSelecionado = this.tblAutores.getSelectionModel().getSelectedItem();
        String nomeAutor = quantidadeAutoriaDtoSelecionado.getAutor().getNome();
        String novoNomeAutor = Dialogs.showInputDialog(FXMLDocumentController.getInstancia().getStage(),
                "Edite o nome do(a) Autor(a) e confirme para alterar."
                + "\nObs.: O nome não pode estar vazio.", "Edição de Autor", "Edição de Autor", nomeAutor);
        if (StringUtil.hasAlgo(novoNomeAutor) && !nomeAutor.equals(novoNomeAutor)) {
            quantidadeAutoriaDtoSelecionado.getAutor().setNome(novoNomeAutor);
            try {
                AutorJpaController.getInstancia().edit(quantidadeAutoriaDtoSelecionado.getAutor());
                atualizarTabela();
            } catch (Exception ex) {
                log.error("Erro ao atualizar o Autor", ex);
                Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(),
                        "Erro ao atualizar o(a) Autor(a).\nFavor entrar em contato com o Administrador.",
                        "Erro!", "Erro", ex);
            }
        } else {
            Dialogs.showWarningDialog(FXMLDocumentController.getInstancia().getStage(),
                    "O nome do(a) Autor(a) não pode estar vazio e deve ser diferente do atual.",
                    "Valor Inválido!", "Aviso");
        }
    }

    @FXML
    private void onActionFromBtnRemoverAutor(ActionEvent event) {
        QuantidadeAutoriaDTO quantidadeAutoriaDtoSelecionado = this.tblAutores.getSelectionModel().getSelectedItem();
        Dialogs.DialogResponse resposta;
        resposta = Dialogs.showConfirmDialog(FXMLDocumentController.getInstancia().getStage(),
                "Tem certeza que deseja remover o(a) Autor(a) \""
                + quantidadeAutoriaDtoSelecionado.getAutor().getNome() + "\" do sistema?"
                + "\n\nATENÇÃO: Ao removê-lo(a), todas as músicas de sua autoria serão apagadas!",
                "Remoção de Autor", "Confirmação");

        if (resposta.equals(Dialogs.DialogResponse.YES)) {
            try {
                AutorJpaController.getInstancia().destroy(quantidadeAutoriaDtoSelecionado.getAutor().getId());
                this.autores.remove(quantidadeAutoriaDtoSelecionado);
                this.lblInformacaoQuantidades.setText("O sistema contém "
                        + AutorJpaController.getInstancia().getAutorCount() + " autor(es) e "
                        + MusicaJpaController.getInstancia().getMusicaCount() + " música(s).");
                this.tblAutores.setItems(this.autores);
                atualizarTabela();
            } catch (NonexistentEntityException ex) {
                log.error("Erro ao remover o Autor", ex);
                Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(),
                        "Erro ao remover o Autor.\nFavor entrar em contato com o Administrador.",
                        "Erro!", "Erro", ex);
            } catch (Exception ex) {
                log.error("Erro ao remover o Autor", ex);
                Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(),
                        "Erro ao remover o Autor.\nFavor entrar em contato com o Administrador.",
                        "Erro!", "Erro", ex);
            }
        }
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
