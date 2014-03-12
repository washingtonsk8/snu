/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores.missa;

import com.sun.javafx.scene.control.behavior.TextAreaBehavior;
import com.sun.javafx.scene.control.skin.SkinBase;
import eu.schudt.javafx.controls.calendar.DatePicker;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.util.Callback;
import org.apache.log4j.Logger;
import snu.entidades.missa.Missa;
import snu.entidades.musica.Musica;
import snu.entidades.musica.TipoMusica;
import snu.fronteiras.controladores.FXMLDocumentController;
import snu.util.DataUtil;
import snu.util.EfeitosUtil;

/**
 * Classe controladora do FXML
 *
 * @author Washington Luis
 */
public class MontarMissaOrganizacaoController implements Initializable {

    @FXML
    private Label lblMontarMissaOrganizacao;
    @FXML
    private Label lblInformacaoPasso;
    @FXML
    private Font x1;
    @FXML
    private ListView<Musica> listaMusicasSelecionadas;
    @FXML
    private Label lblInformacaoFuncionamento;
    @FXML
    private Font x2;
    @FXML
    private Label lblEntrada;
    @FXML
    private Label lblOfertorio;
    @FXML
    private Label lblAtoPenitencial;
    @FXML
    private Label lblGloria;
    @FXML
    private Label lblSanto;
    @FXML
    private Label lblAclamacao;
    @FXML
    private Label lblPaz;
    @FXML
    private Label lblComunhao;
    @FXML
    private Label lblFinal;
    @FXML
    private Label lblAcaoDeGracas;
    @FXML
    private Label lblEspeciais;
    @FXML
    private Button btnAvancar;
    @FXML
    private Button btnVoltar;
    @FXML
    private Label lblNomeMissa;
    @FXML
    private Label lblDataMissa;
    @FXML
    private TextField fldNomeMissa;
    @FXML
    private TextField fldMusicaEntrada;
    @FXML
    private TextField fldMusicaAtoPenitencial;
    @FXML
    private TextField fldMusicaGloria;
    @FXML
    private TextField fldMusicaAclamacao;
    @FXML
    private TextField fldMusicaOfertorio;
    @FXML
    private TextField fldMusicaSanto;
    @FXML
    private TextField fldMusicaPaz;
    @FXML
    private TextField fldMusicaComunhao;
    @FXML
    private TextField fldMusicaAcaoDeGracas;
    @FXML
    private TextField fldMusicaFinal;
    @FXML
    private TextArea areaMusicasEspeciais;
    @FXML
    private AnchorPane contentMontarMissaOrganizacao;
    @FXML
    private Button btnRemoverMusicaEntrada;
    @FXML
    private Button btnRemoverMusicaAtoPenitencial;
    @FXML
    private Button btnRemoverMusicaGloria;
    @FXML
    private Button btnRemoverMusicaOfertorio;
    @FXML
    private Button btnRemoverMusicaAclamacao;
    @FXML
    private Button btnRemoverMusicaSanto;
    @FXML
    private Button btnRemoverMusicaPaz;
    @FXML
    private Button btnRemoverMusicaComunhao;
    @FXML
    private Button btnRemoverMusicaAcaoDeGracas;
    @FXML
    private Button btnRemoverMusicaFinal;
    @FXML
    private Button btnRemoverMusicasEspeciais;

    private DatePicker dpDataMissa;

    /**
     * Mantém, até o momento do armazenamento, todas as músicas No momento do
     * armazenamento, as músicas não escolhidas são removidas.
     */
    private Set<Musica> musicasParaMissa;

    private MontarMissaSelecaoController controladorOrigem;

    private Missa missa;

    /**
     * Contém o conjunto de valores para as seções da missa em relação com as
     * músicas de cada seção.
     */
    private Map<TipoMusica, Object> mapaMusicasMissa;

    private Musica musicaArrastada;//Música que foi arrastada

    private FXMLDocumentController controladorPrincipal;

    //Inicializando o Logger
    private static final Logger log = Logger.getLogger(MontarMissaOrganizacaoController.class.getName());

    private void initComponents() {
        //Formatando o DatePicker de Acontecimento
        this.dpDataMissa = DataUtil.getDatePicker();
        this.dpDataMissa.setLayoutX(150);
        this.dpDataMissa.setLayoutY(95);
        this.dpDataMissa.setMaxWidth(150);
        this.dpDataMissa.setPromptText("DD/MM/AAAA");
        this.contentMontarMissaOrganizacao.getChildren().add(this.dpDataMissa);

        this.musicasParaMissa = new HashSet<>();
        this.missa = new Missa();
        this.mapaMusicasMissa = new HashMap<>();

        this.listaMusicasSelecionadas.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        this.listaMusicasSelecionadas.setCellFactory(new Callback<ListView<Musica>, ListCell<Musica>>() {
            @Override
            public ListCell<Musica> call(ListView<Musica> musicas) {
                ListCell<Musica> celulas = new ListCell<Musica>() {
                    @Override
                    protected void updateItem(Musica musica, boolean bln) {
                        super.updateItem(musica, bln);
                        if (musica != null) {
                            setText(musica.getTitulo());
                        }
                    }
                };
                return celulas;
            }
        });

        this.areaMusicasEspeciais.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.TAB) {
                    SkinBase skin = (SkinBase) areaMusicasEspeciais.getSkin();
                    if (skin.getBehavior() instanceof TextAreaBehavior) {
                        TextAreaBehavior behavior = (TextAreaBehavior) skin.getBehavior();
                        behavior.callAction("TraverseNext");
                        event.consume();
                    }
                }
            }
        });
    }

    public void initData(Set<Musica> musicasSelecionadas,
            MontarMissaSelecaoController controladorOrigem,
            FXMLDocumentController controladorPrincipal) {
        this.musicasParaMissa = musicasSelecionadas;
        this.controladorOrigem = controladorOrigem;
        this.controladorPrincipal = controladorPrincipal;

        Collections.sort(new ArrayList<>(this.musicasParaMissa), new Comparator<Musica>() {
            @Override
            public int compare(Musica musica1, Musica musica2) {
                return musica1.getTitulo().compareTo(musica2.getTitulo());
            }
        });

        //Define a lista de músicas selecionadas
        this.listaMusicasSelecionadas.setItems(FXCollections.observableList(new ArrayList<>(this.musicasParaMissa)));
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
    private void onMouseClickedFromLblEntrada(MouseEvent event) {
        this.fldMusicaEntrada.requestFocus();
    }

    @FXML
    private void onMouseClickedFromLblOfertorio(MouseEvent event) {
        this.fldMusicaOfertorio.requestFocus();
    }

    @FXML
    private void onMouseClickedFromLblAtoPenitencial(MouseEvent event) {
        this.fldMusicaAtoPenitencial.requestFocus();
    }

    @FXML
    private void onMouseClickedFromLblGloria(MouseEvent event) {
        this.fldMusicaGloria.requestFocus();
    }

    @FXML
    private void onMouseClickedFromLblSanto(MouseEvent event) {
        this.fldMusicaSanto.requestFocus();
    }

    @FXML
    private void onMouseClickedFromLblAclamacao(MouseEvent event) {
        this.fldMusicaAclamacao.requestFocus();
    }

    @FXML
    private void onMouseClickedFromLblPaz(MouseEvent event) {
        this.fldMusicaPaz.requestFocus();
    }

    @FXML
    private void onMouseClickedFromLblComunhao(MouseEvent event) {
        this.fldMusicaComunhao.requestFocus();
    }

    @FXML
    private void onMouseClickedFromLblFinal(MouseEvent event) {
        this.fldMusicaFinal.requestFocus();
    }

    @FXML
    private void onMouseClickedFromLblAcaoDeGracas(MouseEvent event) {
        this.fldMusicaAcaoDeGracas.requestFocus();
    }

    @FXML
    private void onMouseClickedFromLblEspeciais(MouseEvent event) {
        this.areaMusicasEspeciais.requestFocus();
    }

    @FXML
    private void onMouseClickedFromLblNomeMissa(MouseEvent event) {
        this.fldNomeMissa.requestFocus();
    }

    @FXML
    private void onMouseClickedFromLblDataMissa(MouseEvent event) {
        this.dpDataMissa.requestFocus();
    }

    @FXML
    private void onDragExitedFldMusicaEntrada(DragEvent event) {
        this.fldMusicaEntrada.setEffect(null);
    }

    @FXML
    private void onDragEnteredFldMusicaEntrada(DragEvent event) {
        this.fldMusicaEntrada.setEffect(EfeitosUtil.getEfeitoValido());
    }

    @FXML
    private void onDragExitedFldMusicaAtoPenitencial(DragEvent event) {
        this.fldMusicaAtoPenitencial.setEffect(null);
    }

    @FXML
    private void onDragEnteredFldMusicaAtoPenitencial(DragEvent event) {
        this.fldMusicaAtoPenitencial.setEffect(EfeitosUtil.getEfeitoValido());
    }

    @FXML
    private void onDragExitedFldMusicaGloria(DragEvent event) {
        this.fldMusicaGloria.setEffect(null);
    }

    @FXML
    private void onDragEnteredFldMusicaGloria(DragEvent event) {
        this.fldMusicaGloria.setEffect(EfeitosUtil.getEfeitoValido());
    }

    @FXML
    private void onDragExitedFldMusicaAclamacao(DragEvent event) {
        this.fldMusicaAclamacao.setEffect(null);
    }

    @FXML
    private void onDragEnteredFldMusicaAclamacao(DragEvent event) {
        this.fldMusicaAclamacao.setEffect(EfeitosUtil.getEfeitoValido());
    }

    @FXML
    private void onDragExitedFldMusicaOfertorio(DragEvent event) {
        this.fldMusicaOfertorio.setEffect(null);
    }

    @FXML
    private void onDragEnteredFldMusicaOfertorio(DragEvent event) {
        this.fldMusicaOfertorio.setEffect(EfeitosUtil.getEfeitoValido());
    }

    @FXML
    private void onDragExitedFldMusicaSanto(DragEvent event) {
        this.fldMusicaSanto.setEffect(null);
    }

    @FXML
    private void onDragEnteredFldMusicaSanto(DragEvent event) {
        this.fldMusicaSanto.setEffect(EfeitosUtil.getEfeitoValido());
    }

    @FXML
    private void onDragExitedFldMusicaPaz(DragEvent event) {
        this.fldMusicaPaz.setEffect(null);
    }

    @FXML
    private void onDragEnteredFldMusicaPaz(DragEvent event) {
        this.fldMusicaPaz.setEffect(EfeitosUtil.getEfeitoValido());
    }

    @FXML
    private void onDragExitedFldMusicaComunhao(DragEvent event) {
        this.fldMusicaComunhao.setEffect(null);
    }

    @FXML
    private void onDragEnteredFldMusicaComunhao(DragEvent event) {
        this.fldMusicaComunhao.setEffect(EfeitosUtil.getEfeitoValido());
    }

    @FXML
    private void onDragExitedFldMusicaAcaoDeGracas(DragEvent event) {
        this.fldMusicaAcaoDeGracas.setEffect(null);
    }

    @FXML
    private void onDragEnteredFldMusicaAcaoDeGracas(DragEvent event) {
        this.fldMusicaAcaoDeGracas.setEffect(EfeitosUtil.getEfeitoValido());
    }

    @FXML
    private void onDragExitedFldMusicaFinal(DragEvent event) {
        this.fldMusicaFinal.setEffect(null);
    }

    @FXML
    private void onDragEnteredFldMusicaFinal(DragEvent event) {
        this.fldMusicaFinal.setEffect(EfeitosUtil.getEfeitoValido());
    }

    @FXML
    private void onDragExitedAreaMusicasEspeciais(DragEvent event) {
        this.areaMusicasEspeciais.setEffect(null);
    }

    @FXML
    private void onDragEnteredAreaMusicasEspeciais(DragEvent event) {
        this.areaMusicasEspeciais.setEffect(EfeitosUtil.getEfeitoValido());
    }

    @FXML
    private void onDragDroppedFldMusicaEntrada(DragEvent event) {
        /* if there is a string data on dragboard, read it and use it */
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasString()) {//Ocorrendo o sucesso, adiciona
            this.fldMusicaEntrada.setText(db.getString());
            if (!this.mapaMusicasMissa.containsKey(TipoMusica.ENTRADA)) {
                this.listaMusicasSelecionadas.getItems().remove(this.musicaArrastada);
                this.mapaMusicasMissa.put(TipoMusica.ENTRADA, this.musicaArrastada);
            } else {
                this.listaMusicasSelecionadas.getItems().remove(this.musicaArrastada);
                atualizarLista();
                this.listaMusicasSelecionadas.getItems().add((Musica) this.mapaMusicasMissa.remove(TipoMusica.ENTRADA));
                this.mapaMusicasMissa.put(TipoMusica.ENTRADA, this.musicaArrastada);
            }
            this.listaMusicasSelecionadas.getSelectionModel().select(-1);
            atualizarLista();
            success = true;
        }
        /* let the source know whether the string was successfully
         * transferred and used */
        event.setDropCompleted(success);

        event.consume();
    }

    @FXML
    private void onDragDroppedFldMusicaAtoPenitencial(DragEvent event) {
        /* if there is a string data on dragboard, read it and use it */
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasString()) {//Ocorrendo o sucesso, adiciona
            this.fldMusicaAtoPenitencial.setText(db.getString());
            if (!this.mapaMusicasMissa.containsKey(TipoMusica.ATO_PENITENCIAL)) {
                this.listaMusicasSelecionadas.getItems().remove(this.musicaArrastada);
                this.mapaMusicasMissa.put(TipoMusica.ATO_PENITENCIAL, this.musicaArrastada);
            } else {
                this.listaMusicasSelecionadas.getItems().remove(this.musicaArrastada);
                atualizarLista();
                this.listaMusicasSelecionadas.getItems().add((Musica) this.mapaMusicasMissa.remove(TipoMusica.ATO_PENITENCIAL));
                this.mapaMusicasMissa.put(TipoMusica.ATO_PENITENCIAL, this.musicaArrastada);
            }
            this.listaMusicasSelecionadas.getSelectionModel().select(-1);
            atualizarLista();
            success = true;
        }
        /* let the source know whether the string was successfully
         * transferred and used */
        event.setDropCompleted(success);

        event.consume();
    }

    @FXML
    private void onDragDroppedFldMusicaGloria(DragEvent event) {
        /* if there is a string data on dragboard, read it and use it */
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasString()) {//Ocorrendo o sucesso, adiciona
            this.fldMusicaGloria.setText(db.getString());
            if (!this.mapaMusicasMissa.containsKey(TipoMusica.GLORIA)) {
                this.listaMusicasSelecionadas.getItems().remove(this.musicaArrastada);
                this.mapaMusicasMissa.put(TipoMusica.GLORIA, this.musicaArrastada);
            } else {
                this.listaMusicasSelecionadas.getItems().remove(this.musicaArrastada);
                atualizarLista();
                this.listaMusicasSelecionadas.getItems().add((Musica) this.mapaMusicasMissa.remove(TipoMusica.GLORIA));
                this.mapaMusicasMissa.put(TipoMusica.GLORIA, this.musicaArrastada);
            }
            this.listaMusicasSelecionadas.getSelectionModel().select(-1);
            atualizarLista();
            success = true;
        }
        /* let the source know whether the string was successfully
         * transferred and used */
        event.setDropCompleted(success);

        event.consume();
    }

    @FXML
    private void onDragDroppedFldMusicaAclamacao(DragEvent event) {
        /* if there is a string data on dragboard, read it and use it */
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasString()) {//Ocorrendo o sucesso, adiciona
            this.fldMusicaAclamacao.setText(db.getString());
            if (!this.mapaMusicasMissa.containsKey(TipoMusica.ACLAMACAO)) {
                this.listaMusicasSelecionadas.getItems().remove(this.musicaArrastada);
                this.mapaMusicasMissa.put(TipoMusica.ACLAMACAO, this.musicaArrastada);
            } else {
                this.listaMusicasSelecionadas.getItems().remove(this.musicaArrastada);
                atualizarLista();
                this.listaMusicasSelecionadas.getItems().add((Musica) this.mapaMusicasMissa.remove(TipoMusica.ACLAMACAO));
                this.mapaMusicasMissa.put(TipoMusica.ACLAMACAO, this.musicaArrastada);
            }
            this.listaMusicasSelecionadas.getSelectionModel().select(-1);
            atualizarLista();
            success = true;
        }
        /* let the source know whether the string was successfully
         * transferred and used */
        event.setDropCompleted(success);

        event.consume();
    }

    @FXML
    private void onDragDroppedFldMusicaOfertorio(DragEvent event) {
        /* if there is a string data on dragboard, read it and use it */
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasString()) {//Ocorrendo o sucesso, adiciona
            this.fldMusicaOfertorio.setText(db.getString());
            if (!this.mapaMusicasMissa.containsKey(TipoMusica.OFERTORIO)) {
                this.listaMusicasSelecionadas.getItems().remove(this.musicaArrastada);
                this.mapaMusicasMissa.put(TipoMusica.OFERTORIO, this.musicaArrastada);
            } else {
                this.listaMusicasSelecionadas.getItems().remove(this.musicaArrastada);
                atualizarLista();
                this.listaMusicasSelecionadas.getItems().add((Musica) this.mapaMusicasMissa.remove(TipoMusica.OFERTORIO));
                this.mapaMusicasMissa.put(TipoMusica.OFERTORIO, this.musicaArrastada);
            }
            this.listaMusicasSelecionadas.getSelectionModel().select(-1);
            atualizarLista();
            success = true;
        }
        /* let the source know whether the string was successfully
         * transferred and used */
        event.setDropCompleted(success);

        event.consume();
    }

    @FXML
    private void onDragDroppedFldMusicaSanto(DragEvent event) {
        /* if there is a string data on dragboard, read it and use it */
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasString()) {//Ocorrendo o sucesso, adiciona
            this.fldMusicaSanto.setText(db.getString());
            if (!this.mapaMusicasMissa.containsKey(TipoMusica.SANTO)) {
                this.listaMusicasSelecionadas.getItems().remove(this.musicaArrastada);
                this.mapaMusicasMissa.put(TipoMusica.SANTO, this.musicaArrastada);
            } else {
                this.listaMusicasSelecionadas.getItems().remove(this.musicaArrastada);
                atualizarLista();
                this.listaMusicasSelecionadas.getItems().add((Musica) this.mapaMusicasMissa.remove(TipoMusica.SANTO));
                this.mapaMusicasMissa.put(TipoMusica.SANTO, this.musicaArrastada);
            }
            this.listaMusicasSelecionadas.getSelectionModel().select(-1);
            atualizarLista();
            success = true;
        }
        /* let the source know whether the string was successfully
         * transferred and used */
        event.setDropCompleted(success);

        event.consume();
    }

    @FXML
    private void onDragDroppedFldMusicaPaz(DragEvent event) {
        /* if there is a string data on dragboard, read it and use it */
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasString()) {//Ocorrendo o sucesso, adiciona
            this.fldMusicaPaz.setText(db.getString());
            if (!this.mapaMusicasMissa.containsKey(TipoMusica.PAZ)) {
                this.listaMusicasSelecionadas.getItems().remove(this.musicaArrastada);
                this.mapaMusicasMissa.put(TipoMusica.PAZ, this.musicaArrastada);
            } else {
                this.listaMusicasSelecionadas.getItems().remove(this.musicaArrastada);
                atualizarLista();
                this.listaMusicasSelecionadas.getItems().add((Musica) this.mapaMusicasMissa.remove(TipoMusica.PAZ));
                this.mapaMusicasMissa.put(TipoMusica.PAZ, this.musicaArrastada);
            }
            this.listaMusicasSelecionadas.getSelectionModel().select(-1);
            atualizarLista();
            success = true;
        }
        /* let the source know whether the string was successfully
         * transferred and used */
        event.setDropCompleted(success);

        event.consume();
    }

    @FXML
    private void onDragDroppedFldMusicaComunhao(DragEvent event) {
        /* if there is a string data on dragboard, read it and use it */
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasString()) {//Ocorrendo o sucesso, adiciona
            this.fldMusicaComunhao.setText(db.getString());
            if (!this.mapaMusicasMissa.containsKey(TipoMusica.COMUNHAO)) {
                this.listaMusicasSelecionadas.getItems().remove(this.musicaArrastada);
                this.mapaMusicasMissa.put(TipoMusica.COMUNHAO, this.musicaArrastada);
            } else {
                this.listaMusicasSelecionadas.getItems().remove(this.musicaArrastada);
                atualizarLista();
                this.listaMusicasSelecionadas.getItems().add((Musica) this.mapaMusicasMissa.remove(TipoMusica.COMUNHAO));
                this.mapaMusicasMissa.put(TipoMusica.COMUNHAO, this.musicaArrastada);
            }
            this.listaMusicasSelecionadas.getSelectionModel().select(-1);
            atualizarLista();
            success = true;
        }
        /* let the source know whether the string was successfully
         * transferred and used */
        event.setDropCompleted(success);

        event.consume();
    }

    @FXML
    private void onDragDroppedFldMusicaAcaoDeGracas(DragEvent event) {
        /* if there is a string data on dragboard, read it and use it */
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasString()) {//Ocorrendo o sucesso, adiciona
            this.fldMusicaAcaoDeGracas.setText(db.getString());
            if (!this.mapaMusicasMissa.containsKey(TipoMusica.ACAO_DE_GRACAS)) {
                this.listaMusicasSelecionadas.getItems().remove(this.musicaArrastada);
                this.mapaMusicasMissa.put(TipoMusica.ACAO_DE_GRACAS, this.musicaArrastada);
            } else {
                this.listaMusicasSelecionadas.getItems().remove(this.musicaArrastada);
                atualizarLista();
                this.listaMusicasSelecionadas.getItems().add((Musica) this.mapaMusicasMissa.remove(TipoMusica.ACAO_DE_GRACAS));
                this.mapaMusicasMissa.put(TipoMusica.ACAO_DE_GRACAS, this.musicaArrastada);
            }
            this.listaMusicasSelecionadas.getSelectionModel().select(-1);
            atualizarLista();
            success = true;
        }
        /* let the source know whether the string was successfully
         * transferred and used */
        event.setDropCompleted(success);

        event.consume();
    }

    @FXML
    private void onDragDroppedFldMusicaFinal(DragEvent event) {
        /* if there is a string data on dragboard, read it and use it */
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasString()) {//Ocorrendo o sucesso, adiciona
            this.fldMusicaFinal.setText(db.getString());
            if (!this.mapaMusicasMissa.containsKey(TipoMusica.FINAL)) {
                this.listaMusicasSelecionadas.getItems().remove(this.musicaArrastada);
                this.mapaMusicasMissa.put(TipoMusica.FINAL, this.musicaArrastada);
            } else {
                this.listaMusicasSelecionadas.getItems().remove(this.musicaArrastada);
                atualizarLista();
                this.listaMusicasSelecionadas.getItems().add((Musica) this.mapaMusicasMissa.remove(TipoMusica.FINAL));
                this.mapaMusicasMissa.put(TipoMusica.FINAL, this.musicaArrastada);
            }
            this.listaMusicasSelecionadas.getSelectionModel().select(-1);
            atualizarLista();
            success = true;
        }
        /* let the source know whether the string was successfully
         * transferred and used */
        event.setDropCompleted(success);

        event.consume();
    }

    @FXML
    private void onDragDroppedAreaMusicasEspeciais(DragEvent event) {
        /* if there is a string data on dragboard, read it and use it */
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasString()) {//Ocorrendo o sucesso, adiciona
            this.areaMusicasEspeciais.setText(this.areaMusicasEspeciais.getText() + db.getString() + "\n");
            if (!this.mapaMusicasMissa.containsKey(TipoMusica.ESPECIAL)) {
                this.listaMusicasSelecionadas.getItems().remove(this.musicaArrastada);
                List<Musica> listaEspeciais = new ArrayList<>();
                listaEspeciais.add(this.musicaArrastada);
                this.mapaMusicasMissa.put(TipoMusica.ESPECIAL, listaEspeciais);
            } else {
                this.listaMusicasSelecionadas.getItems().remove(this.musicaArrastada);
                ((ArrayList<Musica>) this.mapaMusicasMissa.get(TipoMusica.ESPECIAL)).add(this.musicaArrastada);
            }
            this.listaMusicasSelecionadas.getSelectionModel().select(-1);
            atualizarLista();
            success = true;
        }
        /* let the source know whether the string was successfully
         * transferred and used */
        event.setDropCompleted(success);

        event.consume();
    }

    @FXML
    private void onDragDetectedFromListaMusicasSelecionadas(MouseEvent event) {
        ClipboardContent content = new ClipboardContent();
        this.musicaArrastada = this.listaMusicasSelecionadas.getSelectionModel().getSelectedItem();
        if (this.musicaArrastada != null) {
            content.putString(this.musicaArrastada.getTitulo());
        }
        Dragboard db = this.listaMusicasSelecionadas.startDragAndDrop(TransferMode.ANY);
        db.setContent(content);
    }

    @FXML
    private void onDragOverFldMusicaEntrada(DragEvent event) {
        /* accept it only if it is  not dragged from the same node
         * and if it has a string data */
        if (event.getGestureSource() != this.fldMusicaEntrada
                && event.getDragboard().hasString()) {
            /* allow for both copying and moving, whatever user chooses */
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }

        event.consume();
    }

    @FXML
    private void onDragOverFldMusicaAtoPenitencial(DragEvent event) {
        /* accept it only if it is  not dragged from the same node
         * and if it has a string data */
        if (event.getGestureSource() != this.fldMusicaAtoPenitencial
                && event.getDragboard().hasString()) {
            /* allow for both copying and moving, whatever user chooses */
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }

        event.consume();
    }

    @FXML
    private void onDragOverFldMusicaGloria(DragEvent event) {
        /* accept it only if it is  not dragged from the same node
         * and if it has a string data */
        if (event.getGestureSource() != this.fldMusicaGloria
                && event.getDragboard().hasString()) {
            /* allow for both copying and moving, whatever user chooses */
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }

        event.consume();
    }

    @FXML
    private void onDragOverFldMusicaAclamacao(DragEvent event) {
        /* accept it only if it is  not dragged from the same node
         * and if it has a string data */
        if (event.getGestureSource() != this.fldMusicaAclamacao
                && event.getDragboard().hasString()) {
            /* allow for both copying and moving, whatever user chooses */
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }

        event.consume();
    }

    @FXML
    private void onDragOverFldMusicaOfertorio(DragEvent event) {
        /* accept it only if it is  not dragged from the same node
         * and if it has a string data */
        if (event.getGestureSource() != this.fldMusicaOfertorio
                && event.getDragboard().hasString()) {
            /* allow for both copying and moving, whatever user chooses */
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }

        event.consume();
    }

    @FXML
    private void onDragOverFldMusicaSanto(DragEvent event) {
        /* accept it only if it is  not dragged from the same node
         * and if it has a string data */
        if (event.getGestureSource() != this.fldMusicaSanto
                && event.getDragboard().hasString()) {
            /* allow for both copying and moving, whatever user chooses */
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }

        event.consume();
    }

    @FXML
    private void onDragOverFldMusicaPaz(DragEvent event) {
        /* accept it only if it is  not dragged from the same node
         * and if it has a string data */
        if (event.getGestureSource() != this.fldMusicaPaz
                && event.getDragboard().hasString()) {
            /* allow for both copying and moving, whatever user chooses */
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }

        event.consume();
    }

    @FXML
    private void onDragOverFldMusicaComunhao(DragEvent event) {
        /* accept it only if it is  not dragged from the same node
         * and if it has a string data */
        if (event.getGestureSource() != this.fldMusicaComunhao
                && event.getDragboard().hasString()) {
            /* allow for both copying and moving, whatever user chooses */
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }

        event.consume();
    }

    @FXML
    private void onDragOverFldMusicaAcaoDeGracas(DragEvent event) {
        /* accept it only if it is  not dragged from the same node
         * and if it has a string data */
        if (event.getGestureSource() != this.fldMusicaAcaoDeGracas
                && event.getDragboard().hasString()) {
            /* allow for both copying and moving, whatever user chooses */
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }

        event.consume();
    }

    @FXML
    private void onDragOverFldMusicaFinal(DragEvent event) {
        /* accept it only if it is  not dragged from the same node
         * and if it has a string data */
        if (event.getGestureSource() != this.fldMusicaFinal
                && event.getDragboard().hasString()) {
            /* allow for both copying and moving, whatever user chooses */
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }

        event.consume();
    }

    @FXML
    private void onDragOverAreaMusicasEspeciais(DragEvent event) {
        /* accept it only if it is  not dragged from the same node
         * and if it has a string data */
        if (event.getGestureSource() != this.areaMusicasEspeciais
                && event.getDragboard().hasString()) {
            /* allow for both copying and moving, whatever user chooses */
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }

        event.consume();
    }

    @FXML
    private void onActionFromBtnRemoverMusicaEntrada(ActionEvent event) {
        if (this.mapaMusicasMissa.containsKey(TipoMusica.ENTRADA)) {
            this.listaMusicasSelecionadas.getItems().add((Musica) this.mapaMusicasMissa.remove(TipoMusica.ENTRADA));
            this.fldMusicaEntrada.setText(null);
        } else {
            Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(), "Não há música para remover.", "Música inexistente!", "Erro");
        }
        atualizarLista();
    }

    @FXML
    private void onActionFromBtnRemoverMusicaAtoPenitencial(ActionEvent event) {
        if (this.mapaMusicasMissa.containsKey(TipoMusica.ATO_PENITENCIAL)) {
            this.listaMusicasSelecionadas.getItems().add((Musica) this.mapaMusicasMissa.remove(TipoMusica.ATO_PENITENCIAL));
            this.fldMusicaAtoPenitencial.setText(null);
        } else {
            Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(), "Não há música para remover.", "Música inexistente!", "Erro");
        }
        atualizarLista();
    }

    @FXML
    private void onActionFromBtnRemoverMusicaGloria(ActionEvent event) {
        if (this.mapaMusicasMissa.containsKey(TipoMusica.GLORIA)) {
            this.listaMusicasSelecionadas.getItems().add((Musica) this.mapaMusicasMissa.remove(TipoMusica.GLORIA));
            this.fldMusicaGloria.setText(null);
        } else {
            Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(), "Não há música para remover.", "Música inexistente!", "Erro");
        }
        atualizarLista();
    }

    @FXML
    private void onActionFromBtnRemoverMusicaOfertorio(ActionEvent event) {
        if (this.mapaMusicasMissa.containsKey(TipoMusica.OFERTORIO)) {
            this.listaMusicasSelecionadas.getItems().add((Musica) this.mapaMusicasMissa.remove(TipoMusica.OFERTORIO));
            this.fldMusicaOfertorio.setText(null);
        } else {
            Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(), "Não há música para remover.", "Música inexistente!", "Erro");
        }
        atualizarLista();
    }

    @FXML
    private void onActionFromBtnRemoverMusicaAclamacao(ActionEvent event) {
        if (this.mapaMusicasMissa.containsKey(TipoMusica.ACLAMACAO)) {
            this.listaMusicasSelecionadas.getItems().add((Musica) this.mapaMusicasMissa.remove(TipoMusica.ACLAMACAO));
            this.fldMusicaAclamacao.setText(null);
        } else {
            Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(), "Não há música para remover.", "Música inexistente!", "Erro");
        }
        atualizarLista();
    }

    @FXML
    private void onActionFromBtnRemoverMusicaSanto(ActionEvent event) {
        if (this.mapaMusicasMissa.containsKey(TipoMusica.SANTO)) {
            this.listaMusicasSelecionadas.getItems().add((Musica) this.mapaMusicasMissa.remove(TipoMusica.SANTO));
            this.fldMusicaSanto.setText(null);
        } else {
            Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(), "Não há música para remover.", "Música inexistente!", "Erro");
        }
        atualizarLista();
    }

    @FXML
    private void onActionFromBtnRemoverMusicaPaz(ActionEvent event) {
        if (this.mapaMusicasMissa.containsKey(TipoMusica.PAZ)) {
            this.listaMusicasSelecionadas.getItems().add((Musica) this.mapaMusicasMissa.remove(TipoMusica.PAZ));
            this.fldMusicaPaz.setText(null);
        } else {
            Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(), "Não há música para remover.", "Música inexistente!", "Erro");
        }
        atualizarLista();
    }

    @FXML
    private void onActionFromBtnRemoverMusicaComunhao(ActionEvent event) {
        if (this.mapaMusicasMissa.containsKey(TipoMusica.COMUNHAO)) {
            this.listaMusicasSelecionadas.getItems().add((Musica) this.mapaMusicasMissa.remove(TipoMusica.COMUNHAO));
            this.fldMusicaComunhao.setText(null);
        } else {
            Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(), "Não há música para remover.", "Música inexistente!", "Erro");
        }
        atualizarLista();
    }

    @FXML
    private void onActionFromBtnRemoverMusicaAcaoDeGracas(ActionEvent event) {
        if (this.mapaMusicasMissa.containsKey(TipoMusica.ACAO_DE_GRACAS)) {
            this.listaMusicasSelecionadas.getItems().add((Musica) this.mapaMusicasMissa.remove(TipoMusica.ACAO_DE_GRACAS));
            this.fldMusicaAcaoDeGracas.setText(null);
        } else {
            Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(), "Não há música para remover.", "Música inexistente!", "Erro");
        }
        atualizarLista();
    }

    @FXML
    private void onActionFromBtnRemoverMusicaFinal(ActionEvent event) {
        if (this.mapaMusicasMissa.containsKey(TipoMusica.FINAL)) {
            this.listaMusicasSelecionadas.getItems().add((Musica) this.mapaMusicasMissa.remove(TipoMusica.FINAL));
            this.fldMusicaFinal.setText(null);
        } else {
            Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(), "Não há música para remover.", "Música inexistente!", "Erro");
        }
        atualizarLista();
    }

    @FXML
    private void onActionFromBtnRemoverMusicasEspeciais(ActionEvent event) {
        if (this.mapaMusicasMissa.containsKey(TipoMusica.ESPECIAL)) {
            List<Musica> musicasRemovidas = (ArrayList<Musica>) this.mapaMusicasMissa.remove(TipoMusica.ESPECIAL);
            for (Musica musicaRemovida : musicasRemovidas) {
                this.listaMusicasSelecionadas.getItems().add(musicaRemovida);
            }
            this.areaMusicasEspeciais.setText(null);
        } else {
            Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(), "Não há música para remover.", "Música inexistente!", "Erro");
        }
        atualizarLista();
    }

    @FXML
    private void onActionFromBtnAvancar(ActionEvent event) {
        this.missa.setNome(this.fldNomeMissa.getText());

        if (this.dpDataMissa.getSelectedDate() == null) {
            this.dpDataMissa.setEffect(EfeitosUtil.getEfeitoInvalido());
            Dialogs.showWarningDialog(FXMLDocumentController.getInstancia().getStage(),
                    "Favor corrigir os campos assinalados.", "Campos Inválidos!", "Aviso");
        } else {
            this.dpDataMissa.setEffect(null);

            //Remove da lista total aquelas que não foram selecionadas
            for (Musica musica : this.listaMusicasSelecionadas.getItems()) {
                this.musicasParaMissa.remove(musica);
            }

            this.missa.setMusicasUtilizadas(this.musicasParaMissa);
            this.missa.setDataAcontecimento(this.dpDataMissa.getSelectedDate());

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/missa/MontarMissaFinalizacao.fxml"));

            Parent root = null;
            try {
                root = (Parent) fxmlLoader.load();
            } catch (IOException ex) {
                log.error("Erro ao carregar tela de Finalização de Montagem de Missa", ex);
                Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(),
                        "Erro ao carregar tela de Finalização de Montagem de Missa."
                        + "\nFavor entrar em contato com o Administrador.", "Erro!", "Erro", ex);
            }

            MontarMissaFinalizacaoController montarMissaFinalizacaoController = fxmlLoader.getController();

            //Limpa o conteúdo anterior e carrega a página
            AnchorPane pai = ((AnchorPane) this.contentMontarMissaOrganizacao.getParent());
            montarMissaFinalizacaoController.initData(this.missa, this.mapaMusicasMissa, this, this.controladorPrincipal);
            pai.getChildren().clear();
            pai.getChildren().add(root);
        }
    }

    @FXML
    private void onActionFromBtnVoltar(ActionEvent event) {
        //Limpa o conteúdo anterior e carrega a página
        AnchorPane pai = ((AnchorPane) this.contentMontarMissaOrganizacao.getParent());
        pai.getChildren().clear();
        pai.getChildren().add(this.controladorOrigem.getContent());
    }

    public AnchorPane getContent() {
        return this.contentMontarMissaOrganizacao;
    }

    private void atualizarLista() {
        final List<Musica> itens = this.listaMusicasSelecionadas.getItems();
        if (itens == null || itens.isEmpty()) {
            return;
        }

        final Musica item = this.listaMusicasSelecionadas.getItems().get(0);
        itens.remove(0);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                itens.add(0, item);
            }
        });
    }
}
