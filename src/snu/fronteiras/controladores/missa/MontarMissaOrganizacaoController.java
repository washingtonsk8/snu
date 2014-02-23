/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores.missa;

import eu.schudt.javafx.controls.calendar.DatePicker;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.util.Callback;
import snu.entidades.missa.Missa;
import snu.entidades.musica.Musica;
import snu.util.DataUtil;
import snu.util.EfeitosUtil;

/**
 * FXML Controller class
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

    private DatePicker dpDataMissa;

    private List<Musica> listaMusicas;

    private MontarMissaSelecaoController controladorOrigem;

    private Missa missa;

    private Set<Musica> musicasParaMissa;

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
    }

    public void initData(List<Musica> musicasSelecionadas, MontarMissaSelecaoController controladorOrigem) {
        this.listaMusicas = musicasSelecionadas;
        this.controladorOrigem = controladorOrigem;

        Collections.sort(this.listaMusicas, new Comparator<Musica>() {
            @Override
            public int compare(Musica musica1, Musica musica2) {
                return musica1.getTitulo().compareTo(musica2.getTitulo());
            }
        });

        //Define a lista de músicas selecionadas
        this.listaMusicasSelecionadas.setItems(FXCollections.observableList(this.listaMusicas));
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initComponents();
    }

    @FXML
    private void onMouseClickedFromLblEntrada(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblOfertorio(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblAtoPenitencial(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblGloria(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblSanto(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblAclamacao(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblPaz(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblComunhao(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblFinal(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblAcaoDeGracas(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblEspeciais(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblNomeMissa(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblDataMissa(MouseEvent event) {
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
        content.putString(this.listaMusicasSelecionadas.getSelectionModel().getSelectedItem().getTitulo());
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
    private void onActionFromBtnAvancar(ActionEvent event) {
        this.missa.setNome(this.fldNomeMissa.getText());
        this.missa.setDataAcontecimento(this.dpDataMissa.getSelectedDate());
    }

    @FXML
    private void onActionFromBtnVoltar(ActionEvent event) {
        //Limpa o conteúdo anterior e carrega a página
        AnchorPane pai = ((AnchorPane) this.contentMontarMissaOrganizacao.getParent());
        pai.getChildren().clear();
        pai.getChildren().add(this.controladorOrigem.getContent());
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
