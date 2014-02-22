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
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.util.Callback;
import javafx.util.Duration;
import snu.entidades.musica.Autor;
import snu.entidades.musica.Musica;
import snu.util.DataUtil;

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
    
    private DatePicker dpDataMissa;
    
    private List<Musica> listaMusicas;
    
    private MontarMissaSelecaoController controladorOrigem;
    
    private void initComponents(){
         //Formatando o DatePicker de Nascimento
        this.dpDataMissa = DataUtil.getDatePicker();
        this.dpDataMissa.setLayoutX(150);
        this.dpDataMissa.setLayoutY(95);
        this.dpDataMissa.setMaxWidth(150);
        this.dpDataMissa.setPromptText("DD/MM/AAAA");
        
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
    private void onActionFromBtnAvancar(ActionEvent event) {
    }

    @FXML
    private void onActionFromBtnVoltar(ActionEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblNomeMissa(MouseEvent event) {
    }

    @FXML
    private void onMouseClickedFromLblDataMissa(MouseEvent event) {
    }

    @FXML
    private void onDragExitedFldMusicaEntrada(DragEvent event) {
    }

    @FXML
    private void onDragEnteredFldMusicaEntrada(DragEvent event) {
    }

    @FXML
    private void onDragExitedFldMusicaAtoPenitencial(DragEvent event) {
    }

    @FXML
    private void onDragEnteredFldMusicaAtoPenitencial(DragEvent event) {
    }

    @FXML
    private void onDragExitedFldMusicaGloria(DragEvent event) {
    }

    @FXML
    private void onDragEnteredFldMusicaGloria(DragEvent event) {
    }

    @FXML
    private void onDragExitedFldMusicaAclamacao(DragEvent event) {
    }

    @FXML
    private void onDragEnteredFldMusicaAclamacao(DragEvent event) {
    }

    @FXML
    private void onDragExitedFldMusicaOfertorio(DragEvent event) {
    }

    @FXML
    private void onDragEnteredFldMusicaOfertorio(DragEvent event) {
    }

    @FXML
    private void onDragExitedFldMusicaSanto(DragEvent event) {
    }

    @FXML
    private void onDragEnteredFldMusicaSanto(DragEvent event) {
    }

    @FXML
    private void onDragExitedFldMusicaPaz(DragEvent event) {
    }

    @FXML
    private void onDragEnteredFldMusicaPaz(DragEvent event) {
    }

    @FXML
    private void onDragExitedFldMusicaComunhao(DragEvent event) {
    }

    @FXML
    private void onDragEnteredFldMusicaComunhao(DragEvent event) {
    }

    @FXML
    private void onDragExitedFldMusicaAcaoDeGracas(DragEvent event) {
    }

    @FXML
    private void onDragEnteredFldMusicaAcaoDeGracas(DragEvent event) {
    }

    @FXML
    private void onDragExitedFldMusicaFinal(DragEvent event) {
    }

    @FXML
    private void onDragEnteredFldMusicaFinal(DragEvent event) {
    }

    @FXML
    private void onDragExitedMusicasEspeciais(DragEvent event) {
    }

    @FXML
    private void onDragEnteredMusicasEspeciais(DragEvent event) {
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
