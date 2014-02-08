/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores.musica;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.util.Callback;
import snu.controladores.MusicaJpaController;
import snu.dto.ParametrosPesquisaMusica;
import snu.entidades.musica.Musica;
import snu.entidades.musica.TipoMusica;
import snu.util.ListaUtil;

/**
 * FXML Controller class
 *
 * @author Washington Luis
 */
public class VisualizarDadosMusicaController implements Initializable {

    @FXML
    private AnchorPane contentVisualizarDadosMusica;
    @FXML
    private Label lblVisualizarDadosMusica;
    @FXML
    private TextField fldAutor;
    @FXML
    private Label lblTitulo;
    @FXML
    private TextField fldTitulo;
    @FXML
    private Label lblLeitura;
    @FXML
    private TextField fldLeitura;
    @FXML
    private Font x1;
    @FXML
    private Label lblTrecho;
    @FXML
    private CheckBox checkEntrada;
    @FXML
    private Font x2;
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
    private CheckBox checkReflexao;
    @FXML
    private CheckBox checkFinal;
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
    private Label lblAutor;
    @FXML
    private Label lblTipos;
    @FXML
    private TextField fldTrecho;
    @FXML
    private TableView<Musica> tblMusicas;
    @FXML
    private TableColumn<Musica, String> clnAutor;
    @FXML
    private TableColumn<Musica, String> clnTitulo;
    @FXML
    private TableColumn<Musica, String> clnTipos;
    @FXML
    private TableColumn<Musica, String> clnLeituras;
    @FXML
    private Button btnPesquisar;

    private List<TipoMusica> tiposMusica;

    private void initComponents() {
        this.tiposMusica = new ArrayList<>();

        this.clnAutor.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Musica, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Musica, String> musica) {
                return new SimpleStringProperty(musica.getValue().getAutor().getNome());
            }
        });
        this.clnTitulo.setCellValueFactory(new PropertyValueFactory<Musica, String>("nome"));
        this.clnLeituras.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Musica, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Musica, String> musica) {
                String celula = ListaUtil.getListaSeparadaPorPontoVirgula(musica.getValue().getLeiturasAssociadas());

                if (!celula.isEmpty()) {
                    return new SimpleStringProperty(celula);
                } else {
                    return new SimpleStringProperty("Não há leituras associadas");
                }
            }
        });
        this.clnTipos.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Musica, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Musica, String> musica) {
                String celula = ListaUtil.getListaSeparadaPorPontoVirgula(musica.getValue().getTipos());

                return new SimpleStringProperty(celula);
            }
        });
    }

    private void pesquisarPorParametros() {
        ParametrosPesquisaMusica parametrosPesquisa = new ParametrosPesquisaMusica();
        parametrosPesquisa.setNomeAutor(this.fldAutor.getText());
        parametrosPesquisa.setDescricaoLeiturasAssociadas(this.fldLeitura.getText());
        parametrosPesquisa.setTipos(this.tiposMusica);
        parametrosPesquisa.setNomeMusica(this.fldTitulo.getText());
        parametrosPesquisa.setTrecho(this.fldTrecho.getText());

        List<Musica> musicasEncontradas = MusicaJpaController.getInstancia()
                .findMusicasByParametrosPesquisa(parametrosPesquisa);

        this.tblMusicas.setItems(FXCollections.observableArrayList(musicasEncontradas));
        atualizarTabela();
    }

    private void carregarVisualizacaoMusica(Musica musicaSelecionada) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/musica/VisualizarMusica.fxml"));

        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(VisualizarDadosMusicaController.class.getName()).log(Level.SEVERE, null, ex);
        }

        VisualizarMusicaController visualizarMusicaController = fxmlLoader.getController();

        //Limpa o conteúdo anterior e carrega a página
        AnchorPane pai = ((AnchorPane) this.contentVisualizarDadosMusica.getParent());
        visualizarMusicaController.initData(musicaSelecionada, this);
        pai.getChildren().clear();
        pai.getChildren().add(root);
    }

    public AnchorPane getContentVisualizarDadosMusica() {
        return contentVisualizarDadosMusica;
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initComponents();
    }

    @FXML
    private void onMouseClickedFromContentVisualizarDadosMusica(MouseEvent event) {
        this.contentVisualizarDadosMusica.requestFocus();
    }

    @FXML
    private void onActionFromFldAutor(ActionEvent event) {
        pesquisarPorParametros();
    }

    @FXML
    private void onActionFromFldTitulo(ActionEvent event) {
        pesquisarPorParametros();
    }

    @FXML
    private void onActionFromFldLeitura(ActionEvent event) {
        pesquisarPorParametros();
    }

    @FXML
    private void onActionFromFldTrecho(ActionEvent event) {
        pesquisarPorParametros();
    }

    @FXML
    private void onActionFromCheckEntrada(ActionEvent event) {
        if (this.checkEntrada.isSelected()) {
            this.tiposMusica.add(TipoMusica.ENTRADA);
        } else {
            this.tiposMusica.remove(TipoMusica.ENTRADA);
        }
    }

    @FXML
    private void onActionFromCheckPerdao(ActionEvent event) {
        if (this.checkPerdao.isSelected()) {
            this.tiposMusica.add(TipoMusica.PERDAO);
        } else {
            this.tiposMusica.remove(TipoMusica.PERDAO);
        }
    }

    @FXML
    private void onActionFromCheckGloria(ActionEvent event) {
        if (this.checkGloria.isSelected()) {
            this.tiposMusica.add(TipoMusica.GLORIA);
        } else {
            this.tiposMusica.remove(TipoMusica.GLORIA);
        }
    }

    @FXML
    private void onActionFromCheckAclamacao(ActionEvent event) {
        if (this.checkAclamacao.isSelected()) {
            this.tiposMusica.add(TipoMusica.ACLAMACAO);
        } else {
            this.tiposMusica.remove(TipoMusica.ACLAMACAO);
        }
    }

    @FXML
    private void onActionFromCheckOfertorio(ActionEvent event) {
        if (this.checkOfertorio.isSelected()) {
            this.tiposMusica.add(TipoMusica.OFERTORIO);
        } else {
            this.tiposMusica.remove(TipoMusica.OFERTORIO);
        }
    }

    @FXML
    private void onActionFromCheckPaz(ActionEvent event) {
        if (this.checkPaz.isSelected()) {
            this.tiposMusica.add(TipoMusica.PAZ);
        } else {
            this.tiposMusica.remove(TipoMusica.PAZ);
        }
    }

    @FXML
    private void onActionFromCheckSanto(ActionEvent event) {
        if (this.checkSanto.isSelected()) {
            this.tiposMusica.add(TipoMusica.SANTO);
        } else {
            this.tiposMusica.remove(TipoMusica.SANTO);
        }
    }

    @FXML
    private void onActionFromCheckComunhao(ActionEvent event) {
        if (this.checkComunhao.isSelected()) {
            this.tiposMusica.add(TipoMusica.COMUNHAO);
        } else {
            this.tiposMusica.remove(TipoMusica.COMUNHAO);
        }
    }

    @FXML
    private void onActionFromCheckAcaoDeGracas(ActionEvent event) {
        if (this.checkAcaoDeGracas.isSelected()) {
            this.tiposMusica.add(TipoMusica.ACAO_DE_GRACAS);
        } else {
            this.tiposMusica.remove(TipoMusica.ACAO_DE_GRACAS);
        }
    }

    @FXML
    private void onActionFromCheckFinal(ActionEvent event) {
        if (this.checkFinal.isSelected()) {
            this.tiposMusica.add(TipoMusica.FINAL);
        } else {
            this.tiposMusica.remove(TipoMusica.FINAL);
        }
    }

    @FXML
    private void onActionFromCheckReflexao(ActionEvent event) {
        if (this.checkReflexao.isSelected()) {
            this.tiposMusica.add(TipoMusica.REFLEXAO);
        } else {
            this.tiposMusica.remove(TipoMusica.REFLEXAO);
        }
    }

    @FXML
    private void onActionFromCheckLouvor(ActionEvent event) {
        if (this.checkLouvor.isSelected()) {
            this.tiposMusica.add(TipoMusica.LOUVOR);
        } else {
            this.tiposMusica.remove(TipoMusica.LOUVOR);
        }
    }

    @FXML
    private void onActionFromCheckAdoracao(ActionEvent event) {
        if (this.checkAdoracao.isSelected()) {
            this.tiposMusica.add(TipoMusica.ADORACAO);
        } else {
            this.tiposMusica.remove(TipoMusica.ADORACAO);
        }
    }

    @FXML
    private void onActionFromCheckPalestra(ActionEvent event) {
        if (this.checkPalestra.isSelected()) {
            this.tiposMusica.add(TipoMusica.PALESTRA);
        } else {
            this.tiposMusica.remove(TipoMusica.PALESTRA);
        }
    }

    @FXML
    private void onActionFromCheckResposta(ActionEvent event) {
        if (this.checkResposta.isSelected()) {
            this.tiposMusica.add(TipoMusica.RESPOSTA);
        } else {
            this.tiposMusica.remove(TipoMusica.RESPOSTA);
        }
    }

    @FXML
    private void onActionFromCheckOracao(ActionEvent event) {
        if (this.checkOracao.isSelected()) {
            this.tiposMusica.add(TipoMusica.ORACAO);
        } else {
            this.tiposMusica.remove(TipoMusica.ORACAO);
        }
    }

    @FXML
    private void onActionFromCheckEspecial(ActionEvent event) {
        if (this.checkEspecial.isSelected()) {
            this.tiposMusica.add(TipoMusica.ESPECIAL);
        } else {
            this.tiposMusica.remove(TipoMusica.ESPECIAL);
        }
    }

    @FXML
    private void onActionFromCheckOutra(ActionEvent event) {
        if (this.checkOutra.isSelected()) {
            this.tiposMusica.add(TipoMusica.OUTRA);
        } else {
            this.tiposMusica.remove(TipoMusica.OUTRA);
        }
    }

    @FXML
    private void onMouseClickedFromLblLeitura(MouseEvent event) {
        this.fldLeitura.requestFocus();
    }

    @FXML
    private void onMouseClickedFromLblAutor(MouseEvent event) {
        this.fldAutor.requestFocus();
    }

    @FXML
    private void onMouseClickedFromLblTitulo(MouseEvent event) {
        this.fldTitulo.requestFocus();
    }

    @FXML
    private void onMouseClickedFromLblTipos(MouseEvent event) {
        this.checkEntrada.requestFocus();
    }

    @FXML
    private void onMouseClickedFromLblTrecho(MouseEvent event) {
        this.fldTrecho.requestFocus();
    }

    @FXML
    private void onMouseClickedFromTblMusicas(MouseEvent event) {
        this.tblMusicas.requestFocus();
        Musica musicaSelecionada = this.tblMusicas.getSelectionModel().getSelectedItem();
        if (event.getClickCount() == 2 && musicaSelecionada != null) {
            carregarVisualizacaoMusica(musicaSelecionada);
        }
    }

    @FXML
    private void onActionFromBtnPesquisar(ActionEvent event) {
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
}
