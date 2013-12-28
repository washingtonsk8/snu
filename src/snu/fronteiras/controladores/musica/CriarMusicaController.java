/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores.musica;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.util.Callback;
import snu.controladores.IntegranteJpaController;
import snu.controladores.exceptions.NonexistentEntityException;
import snu.dto.EntidadeTom;
import snu.entidades.integrante.Integrante;
import snu.entidades.musica.Afinacao;
import snu.entidades.musica.AssociacaoIntegranteMusica;
import snu.entidades.musica.Musica;
import snu.entidades.musica.Tom;
import snu.fronteiras.controladores.integrante.RemoverIntegranteController;

/**
 * FXML Controller class
 *
 * @author Washington Luis
 */
public class CriarMusicaController implements Initializable {

    @FXML
    private Label lblLeituras;
    @FXML
    private Font x1;
    @FXML
    private Label lblAutor;
    @FXML
    private Label lblTitulo;
    @FXML
    private Label lblTom;
    @FXML
    private Label lblAfinacao;
    @FXML
    private Label lblAssociacoes;
    @FXML
    private TableView<AssociacaoIntegranteMusica> tblAssociacoes;
    @FXML
    private Label lblTipos;
    @FXML
    private Font x2;
    @FXML
    private Font x3;
    @FXML
    private TableColumn<AssociacaoIntegranteMusica, String> clnNome;
    @FXML
    private TableColumn<AssociacaoIntegranteMusica, String> clnTom;
    @FXML
    private TextField fldAutor;
    @FXML
    private TextField fldTitulo;
    @FXML
    private TextField fldLeituras;
    @FXML
    private ComboBox<Tom> comboTom;
    @FXML
    private ComboBox<Afinacao> comboAfinacao;
    @FXML
    private CheckBox checkEntrada;
    @FXML
    private CheckBox checkPerdao;
    @FXML
    private CheckBox checkGloria;
    @FXML
    private CheckBox checkAclamacao;
    @FXML
    private CheckBox checkOfertorio;
    @FXML
    private CheckBox checkPaz;
    @FXML
    private CheckBox checkSanto;
    @FXML
    private CheckBox checkComunhao;
    @FXML
    private CheckBox checkAcaoDeGracas;
    @FXML
    private CheckBox checkFinal;
    @FXML
    private CheckBox checkReflexao;
    @FXML
    private CheckBox checkLouvor;
    @FXML
    private CheckBox checkAdoracao;
    @FXML
    private CheckBox checkPalestra;
    @FXML
    private CheckBox checkResposta;
    @FXML
    private CheckBox checkOracao;
    @FXML
    private CheckBox checkEspecial;
    @FXML
    private CheckBox checkOutra;
    @FXML
    private Button btnEscreverConteudo;
    @FXML
    private Button btnAdicionarAssociacao;
    @FXML
    private Button btnRemoverAssociacao;

    @FXML
    private ComboBox<String> comboNomeAssociacao;
    @FXML
    private ComboBox<Tom> comboTomAssociacao;
    @FXML
    private Label lblNomeAssociacao;
    @FXML
    private Label lblTomAssociacao;

    private ObservableList<AssociacaoIntegranteMusica> itensAssociacao;

    private List<Integrante> integrantesAssociados;

    private Musica musica;

    private final ObservableList<Tom> tonsMusica
            = FXCollections.observableArrayList(Tom.C, Tom.C_SUSTENIDO, Tom.D,
                    Tom.D_SUSTENIDO, Tom.E, Tom.F, Tom.F_SUSTENIDO, Tom.G,
                    Tom.G_SUSTENIDO, Tom.A, Tom.A_SUSTENIDO, Tom.B);

    private final ObservableList<Afinacao> afinacoesMusica
            = FXCollections.observableArrayList(Afinacao.CFBbEbGC, Afinacao.DGCFAD,
                    Afinacao.DbGbBEAbDb, Afinacao.EADGBE, Afinacao.EbAbDbGbBbEb);

    /**
     * Inicializa os componentes
     */
    private void initComponents() {
        ObservableList<String> nomesIntegrantes = FXCollections.observableArrayList();

        this.musica = new Musica();
        this.itensAssociacao = FXCollections.observableArrayList();
        this.integrantesAssociados = IntegranteJpaController.getInstancia().findIntegranteEntities();

        this.comboTom.setItems(this.tonsMusica);
        this.comboAfinacao.setItems(this.afinacoesMusica);
        this.comboAfinacao.getSelectionModel().select(Afinacao.EADGBE);

        this.comboTomAssociacao.setItems(this.tonsMusica);

        //Faz pesquisa no banco pelos integrantes presentes e pega o nome
        for (Integrante integrante : this.integrantesAssociados) {
            nomesIntegrantes.add(integrante.getNome());
        }

        this.comboNomeAssociacao.setItems(nomesIntegrantes);

        this.clnNome.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AssociacaoIntegranteMusica, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<AssociacaoIntegranteMusica, String> associacao) {
                return new SimpleStringProperty(associacao.getValue().getIntegrante().getNome());
            }
        });
        this.clnTom.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AssociacaoIntegranteMusica, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<AssociacaoIntegranteMusica, String> associacao) {
                return new SimpleStringProperty(associacao.getValue().getTom().getTom().toString());
            }
        });
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initComponents();
    }

    @FXML
    private void onMouseClickedFromLblLeituras(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblAutor(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblTitulo(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblTom(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblAfinacao(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblAssociacoes(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblTipos(MouseEvent event) {
    }

    @FXML
    private void onActionFromFldAutor(ActionEvent event) {
    }

    @FXML
    private void onActionFromFldTitulo(ActionEvent event) {
    }

    @FXML
    private void onActionFromFldLeituras(ActionEvent event) {
    }

    @FXML
    private void onActionFromComboTom(ActionEvent event) {
    }

    @FXML
    private void onActionFromComboAfinacao(ActionEvent event) {
    }

    @FXML
    private void onActionFromCheckEntrada(ActionEvent event) {
    }

    @FXML
    private void onActionFromCheckPerdao(ActionEvent event) {
    }

    @FXML
    private void onActionFromCheckGloria(ActionEvent event) {
    }

    @FXML
    private void onActionFromCheckAclamacao(ActionEvent event) {
    }

    @FXML
    private void onActionFromCheckOfertorio(ActionEvent event) {
    }

    @FXML
    private void onActionFromCheckPaz(ActionEvent event) {
    }

    @FXML
    private void onActionFromCheckSanto(ActionEvent event) {
    }

    @FXML
    private void onActionFromCheckComunhao(ActionEvent event) {
    }

    @FXML
    private void onActionFromCheckAcaoDeGracas(ActionEvent event) {
    }

    @FXML
    private void onActionFromCheckFinal(ActionEvent event) {
    }

    @FXML
    private void onActionFromCheckReflexao(ActionEvent event) {
    }

    @FXML
    private void onActionFromCheckLouvor(ActionEvent event) {
    }

    @FXML
    private void onActionFromCheckAdoracao(ActionEvent event) {
    }

    @FXML
    private void onActionFromCheckPalestra(ActionEvent event) {
    }

    @FXML
    private void onActionFromCheckResposta(ActionEvent event) {
    }

    @FXML
    private void onActionFromCheckOracao(ActionEvent event) {
    }

    @FXML
    private void onActionFromCheckEspecial(ActionEvent event) {
    }

    @FXML
    private void onActionFromCheckOutra(ActionEvent event) {
    }

    @FXML
    private void onActionFromBtnEscreverConteudo(ActionEvent event) {
    }

    @FXML
    private void onActionFromBtnAdicionarAssociacao(ActionEvent event) {
        //Cria uma nova associação
        AssociacaoIntegranteMusica novaAssociacao = new AssociacaoIntegranteMusica();
        novaAssociacao.setMusica(this.musica);
        novaAssociacao.setIntegrante(
                this.integrantesAssociados.get(
                        this.comboNomeAssociacao.getSelectionModel().getSelectedIndex()));
        novaAssociacao.setTom(new EntidadeTom(this.comboTomAssociacao.getSelectionModel().getSelectedItem()));

        //Adiciona a nova associação na lista de associações desta classe
        this.itensAssociacao.add(novaAssociacao);

        //Define a lista de associações atual na tabela
        this.tblAssociacoes.setItems(this.itensAssociacao);

        //Troca a seleção para nenhum valor para serem adicionados novas associações
        this.comboNomeAssociacao.getSelectionModel().select(null);
        this.comboTomAssociacao.getSelectionModel().select(null);

        //Atualiza a tabela
        atualizarTabela();
    }

    @FXML
    private void onActionFromBtnRemoverAssociacao(ActionEvent event) {
        int indiceSelecionado = this.tblAssociacoes.getSelectionModel().getSelectedIndex();

        if (indiceSelecionado >= 0) {
            Dialogs.DialogResponse resposta = Dialogs.showConfirmDialog(null, "Tem certeza que deseja excluir a Associação?", "Exclusão de Associação", "Confirmação");

            if (resposta.equals(Dialogs.DialogResponse.YES)) {
                
                this.itensAssociacao.remove(indiceSelecionado);

                //Define a lista de associações atual na tabela
                this.tblAssociacoes.setItems(this.itensAssociacao);
                
                atualizarTabela();
            }
        } else {
            Dialogs.showWarningDialog(null, "Favor selecionar uma Associação para a exclusão", "Associação não selecionada", "Aviso");
        }
    }

    @FXML
    private void onActionFromComboNomeAssociacao(ActionEvent event) {
    }

    @FXML
    private void onActionFromComboTomAssociacao(ActionEvent event) {
    }

    @FXML
    private void onActionFromBtnSalvar(ActionEvent event) {
    }

    private void atualizarTabela() {
        final List<AssociacaoIntegranteMusica> itens = this.tblAssociacoes.getItems();
        if (itens == null || itens.isEmpty()) {
            return;
        }

        final AssociacaoIntegranteMusica item = this.tblAssociacoes.getItems().get(0);
        itens.remove(0);
        try {//Dorme um pouco para visualizarmos as alterações
            Thread.sleep(300);
        } catch (InterruptedException ex) {
            Logger.getLogger(CriarMusicaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                itens.add(0, item);
            }
        });
    }

}
