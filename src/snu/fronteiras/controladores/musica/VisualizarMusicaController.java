/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores.musica;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.util.Callback;
import snu.entidades.musica.AssociacaoIntegranteMusica;
import snu.entidades.musica.Musica;
import snu.entidades.musica.TipoMusica;

/**
 * FXML Controller class
 *
 * @author Washington Luis
 */
public class VisualizarMusicaController implements Initializable {

    @FXML
    private AnchorPane contentVisualizarMusica;
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
    private Label lblTipos;
    @FXML
    private Label lblAssociacoes;
    @FXML
    private Font x2;
    @FXML
    private TableColumn<AssociacaoIntegranteMusica, String> clnNome;
    @FXML
    private TableColumn<AssociacaoIntegranteMusica, String> clnTom;
    @FXML
    private TableView<AssociacaoIntegranteMusica> tblResultadoAssociacoes;
    @FXML
    private Button btnVisualizarConteudo;
    @FXML
    private Button btnVoltar;
    @FXML
    private Label lblResultadoAutor;
    @FXML
    private Label lblResultadoTitulo;
    @FXML
    private Label lblResultadoTom;
    @FXML
    private Label lblResultadoAfinacao;
    @FXML
    private Label lblResultadoLeituras;
    @FXML
    private Label lblResultadoTipos;

    private VisualizarDadosMusicaController controladorOrigem;

    private Musica musica;

    private void initComponents() {
        //Organiza a apresentação dos dados nas colunas da tabela de associação
        this.clnNome.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AssociacaoIntegranteMusica, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<AssociacaoIntegranteMusica, String> associacao) {
                return new SimpleStringProperty(associacao.getValue().getIntegrante().getNome());
            }
        });
        this.clnTom.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AssociacaoIntegranteMusica, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<AssociacaoIntegranteMusica, String> associacao) {
                return new SimpleStringProperty(associacao.getValue().getTom().toString());
            }
        });

        this.contentVisualizarMusica.requestFocus();
    }

    public void initData(Musica musica, VisualizarDadosMusicaController controladorOrigem) {
        this.musica = musica;
        this.controladorOrigem = controladorOrigem;

        this.lblResultadoAfinacao.setText(musica.getAfinacao().toString());
        this.lblResultadoAutor.setText(musica.getAutor().getNome());

        List<String> leiturasAssociadas = musica.getLeiturasAssociadas();
        String apresentacao = "";

        for (String leituraAssociada : leiturasAssociadas) {
            apresentacao += leituraAssociada + ", ";
        }

        if (!leiturasAssociadas.isEmpty()) {
            this.lblResultadoLeituras.setText(apresentacao.substring(0, apresentacao.length() - 2));
        } else {
            this.lblResultadoLeituras.setText("Não há leituras associadas");
        }

        List<TipoMusica> tiposMusica = musica.getTipos();
        apresentacao = "";

        for (TipoMusica tipoMusica : tiposMusica) {
            apresentacao += tipoMusica + ", ";
        }

        this.lblResultadoTipos.setText(apresentacao.substring(0, apresentacao.length() - 2));
        this.lblResultadoTitulo.setText(musica.getTitulo());
        this.lblResultadoTom.setText(musica.getTom().toString());
        this.tblResultadoAssociacoes.setItems(FXCollections.observableArrayList(musica.getAssociacoes()));
    }

    private void carregarConteudoMusica() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/musica/VisualizarConteudoMusica.fxml"));

        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(VisualizarMusicaController.class.getName()).log(Level.SEVERE, null, ex);
        }

        VisualizarConteudoMusicaController visualizarConteudoMusicaController = fxmlLoader.getController();

        //Limpa o conteúdo anterior e carrega a página
        AnchorPane pai = ((AnchorPane) this.contentVisualizarMusica.getParent());
        visualizarConteudoMusicaController.initData(this.musica, this);
        pai.getChildren().clear();
        pai.getChildren().add(root);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initComponents();
    }

    public AnchorPane getContentVisualizarMusica() {
        return contentVisualizarMusica;
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
    private void onMouseClickedFromLblTipos(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblAssociacoes(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromContentVisualizarMusica(MouseEvent event) {
    }

    @FXML
    private void onActionFromBtnVisualizarConteudo(ActionEvent event) {
        carregarConteudoMusica();
    }

    @FXML
    private void onActionFromBtnVoltar(ActionEvent event) {
        //Limpa o conteúdo anterior e carrega a página
        AnchorPane pai = ((AnchorPane) this.contentVisualizarMusica.getParent());
        pai.getChildren().clear();
        pai.getChildren().add(this.controladorOrigem.getContentVisualizarDadosMusica());
    }

}
