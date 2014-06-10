/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores.musica;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
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
import javafx.scene.control.Dialogs;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javafx.util.Duration;
import org.apache.log4j.Logger;
import snu.entidades.missa.Missa;
import snu.entidades.musica.AssociacaoIntegranteMusica;
import snu.entidades.musica.Musica;
import snu.fronteiras.controladores.FXMLDocumentController;
import snu.util.DataUtil;
import snu.util.EfeitosUtil;
import snu.util.ListaUtil;
import snu.util.StringUtil;

/**
 * Classe controladora do FXML
 *
 * @author Washington Luis
 */
public class VisualizarMusicaController implements Initializable {

    //Inicializando o Logger
    private static final Logger log = Logger.getLogger(VisualizarMusicaController.class.getName());

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
    @FXML
    private Label lblEstaImpressa;
    @FXML
    private Label lblResultadoEstaImpressa;

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
        this.popup.setAutoHide(true);
        this.popup.setConsumeAutoHidingEvents(false);
        this.popup.setOnHiding(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent t) {
                btnVisualizaMissasPresente.setText("Visualizar...");
            }
        });

        TableColumn<Missa, String> clnNomeMissa = new TableColumn<>("Nome da Missa");
        clnNomeMissa.setPrefWidth(350);
        clnNomeMissa.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Missa, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Missa, String> associacao) {
                String nomeMissa = associacao.getValue().getNome();
                return new SimpleStringProperty(StringUtil.hasAlgo(nomeMissa) ? nomeMissa : "Indefinido");
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
        this.tblMissasPresente.setEffect(EfeitosUtil.getEfeitoGeral());

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
        
        this.lblResultadoEstaImpressa.setText(this.musica.isImpressa()? "Sim" : "Não");
    }

    private void carregarConteudoMusica() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/musica/VisualizarConteudoMusica.fxml"));

        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException ex) {
            log.error("Erro ao carregar a tela de Visualização de Conteúdo de Música", ex);
            Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(),
                    "Erro ao carregar a tela de Visualização de Conteúdo de Música."
                    + "\nFavor entrar em contato com o Administrador.", "Erro!", "Erro", ex);
        }

        VisualizarConteudoMusicaController visualizarConteudoMusicaController = fxmlLoader.getController();

        //Limpa o conteúdo anterior e carrega a página
        AnchorPane pai = ((AnchorPane) this.contentVisualizarMusica.getParent());
        visualizarConteudoMusicaController.initData(this.musica, this);
        pai.getChildren().clear();
        pai.getChildren().add(root);
        EfeitosUtil.rodarEfeitoCarregamento(root);
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
        this.hplResultadoLinkVideo.requestFocus();
    }

    @FXML
    private void onMouseClickedFromLblAssociacoes(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromContentVisualizarMusica(MouseEvent event) {
        this.contentVisualizarMusica.requestFocus();
    }

    @FXML
    private void onActionFromHplResultadoLinkVideo(ActionEvent event) {
        String linkParaVideo = this.hplResultadoLinkVideo.getText();
        if (StringUtil.hasAlgo(linkParaVideo)) {
            try {
                //Inicia a variável desktop, recebendo a area de trabalho do navegador
                Desktop desktop = Desktop.getDesktop();
                //Inicia a variável URI com uma endereço http
                URI uri = new URI(linkParaVideo);
                //Manda o desktop abrir a url criada acima
                desktop.browse(uri);
            } catch (URISyntaxException ex) {
                log.error("Erro de sintaxe de URL de vídeo de música", ex);
                Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(),
                        "Erro ao carregar a URL do vídeo. A sintaxe está incorreta.",
                        "Erro!", "Erro", ex);
            } catch (IOException ex) {
                log.error("Erro ao abrir o navegador com o endereço informado.", ex);
                Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(),
                        "Erro ao abrir o navegador com a URL do vídeo."
                        + "\nFavor entrar em contato com o Administrador.",
                        "Erro!", "Erro", ex);
            }
        }
    }

    @FXML
    private void onActionFromBtnVisualizarMissasPresente(ActionEvent event) {
        if (this.popup.isShowing()) {
            this.btnVisualizaMissasPresente.setText("Visualizar...");
            this.popup.hide();
        } else {
            Window proprietaria = ((Node) (event.getSource())).getScene().getWindow();
            //this.popup.setX(proprietaria.getX() + proprietaria.getWidth() / 2 - 250);
            //this.popup.setY(proprietaria.getY() + proprietaria.getHeight() / 2 - 150);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.6), popup.getContent().get(0));
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.playFromStart();

            this.popup.show(this.btnVisualizaMissasPresente,
                    proprietaria.getX() + proprietaria.getWidth() / 2 - 250,
                    proprietaria.getY() + proprietaria.getHeight() / 2 - 150);
            this.btnVisualizaMissasPresente.setText("Fechar");
        }
    }

    @FXML
    private void onActionFromBtnVisualizarConteudo(ActionEvent event) {
        carregarConteudoMusica();
    }

    @FXML
    private void onActionFromBtnVoltar(ActionEvent event) {
        final AnchorPane content = this.controladorOrigem.getContent();

        //Limpa o conteúdo anterior e carrega a página
        AnchorPane pai = ((AnchorPane) this.contentVisualizarMusica.getParent());
        pai.getChildren().clear();
        pai.getChildren().add(content);
        EfeitosUtil.rodarEfeitoCarregamento(content);
    }
}
