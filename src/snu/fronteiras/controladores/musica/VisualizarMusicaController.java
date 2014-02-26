/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores.musica;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import snu.entidades.missa.Missa;
import snu.entidades.musica.AssociacaoIntegranteMusica;
import snu.entidades.musica.Musica;
import snu.util.DataUtil;
import snu.util.ListaUtil;

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
    @FXML
    private Label lblVisualizarMusica;
    @FXML
    private Label lblLinkVideo;
    @FXML
    private Hyperlink hplResultadoLinkVideo;
    @FXML
    private Label lblMissasPresente;
    @FXML
    private Button btnVisualizaMissasPresente;

    private TemplatePesquisaMusicaController controladorOrigem;

    private Musica musica;

    private Popup popup;

    private TableView<Missa> tblMissasPresente;

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

        /**
         * Define a abertura da Popup de visualização de missas presente
         */
        this.popup = new Popup();
        TableColumn<Missa, String> clnNomeMissa = new TableColumn<>("Nome da Missa");
        clnNomeMissa.setPrefWidth(350);
        clnNomeMissa.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Missa, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Missa, String> associacao) {
                return new SimpleStringProperty(associacao.getValue().getNome());
            }
        });

        TableColumn<Missa, String> clnDataMissa = new TableColumn<>("Data de Acontecimento");
        clnDataMissa.setPrefWidth(150);
        clnDataMissa.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Missa, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Missa, String> associacao) {
                return new SimpleStringProperty(DataUtil.formatarData(associacao.getValue().getDataAcontecimento()));
            }
        });

        this.tblMissasPresente = new TableView();
        this.tblMissasPresente.setPrefWidth(500);
        this.tblMissasPresente.setPrefHeight(300);
        this.tblMissasPresente.getColumns().add(clnNomeMissa);
        this.tblMissasPresente.getColumns().add(clnDataMissa);
                
        this.popup.getContent().addAll(this.tblMissasPresente);
    }

    public void initData(Musica musica, TemplatePesquisaMusicaController controladorOrigem) {
        this.musica = musica;
        this.controladorOrigem = controladorOrigem;

        String leiturasAssociadas = ListaUtil.getListaSeparadaPorPontoVirgula(musica.getLeiturasAssociadas());

        this.lblResultadoAfinacao.setText(musica.getAfinacao().toString());
        this.lblResultadoAutor.setText(musica.getAutor().getNome());
        this.lblResultadoLeituras.setText((leiturasAssociadas != null && !leiturasAssociadas.isEmpty()) ? leiturasAssociadas : "Não há leituras associadas");
        this.lblResultadoTipos.setText(ListaUtil.getListaSeparadaPorPontoVirgula(musica.getTipos()));
        this.lblResultadoTitulo.setText(musica.getNome());
        this.hplResultadoLinkVideo.setText(musica.getLinkVideo());
        this.lblResultadoTom.setText(musica.getTom().toString());
        this.tblResultadoAssociacoes.setItems(FXCollections.observableArrayList(musica.getAssociacoes()));
        this.tblResultadoAssociacoes.setEditable(false);

        ObservableList<Missa> missasPresente = FXCollections.observableArrayList(new ArrayList<>(this.musica.getMissasPresente()));
        this.tblMissasPresente.setItems(missasPresente);
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
    private void onMouseClickedFromLblLinkVideo(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblAssociacoes(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromContentVisualizarMusica(MouseEvent event) {
        if (this.popup.isShowing()) {
            this.popup.hide();
            this.btnVisualizaMissasPresente.setText("Visualizar...");
        }
        this.contentVisualizarMusica.requestFocus();
    }

    @FXML
    private void onActionFromHplResultadoLinkVideo(ActionEvent event) {
        //TODO: Abrir navegador
    }

    @FXML
    private void onActionFromBtnVisualizarMissasPresente(ActionEvent event) {
        if (this.popup.isShowing()) {
            this.popup.hide();
            this.btnVisualizaMissasPresente.setText("Visualizar...");
        } else {
            Window proprietaria = ((Node) (event.getSource())).getScene().getWindow();
            this.popup.setX(proprietaria.getX() + proprietaria.getWidth() / 2 - 250);
            this.popup.setY(proprietaria.getY() + proprietaria.getHeight() / 2 - 150);
            this.popup.show(proprietaria);
            this.btnVisualizaMissasPresente.setText("Fechar");
        }
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
        pai.getChildren().add(this.controladorOrigem.getContent());
    }
}
