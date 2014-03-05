/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores.musica.popups;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.util.StringConverter;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.sf.jasperreports.engine.JRException;
import snu.controladores.PDFController;
import snu.entidades.musica.AssociacaoIntegranteMusica;
import snu.entidades.musica.Musica;
import snu.entidades.musica.Tom;
import snu.util.EfeitosUtil;
import snu.util.ListaUtil;
import snu.util.MusicaUtil;

/**
 * Classe controladora do FXML
 *
 * @author Washington Luis
 */
public class GerarImpressaoMusicaController implements Initializable {

    @FXML
    private AnchorPane popupGerarImpressaoMusica;
    @FXML
    private Font x1;
    @FXML
    private Label lblCantor;
    @FXML
    private Font x2;
    @FXML
    private ComboBox<String> comboCantor;
    @FXML
    private ComboBox<Tom> comboTom;
    @FXML
    private Button btnGerar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Label lblTipoImpressao;
    @FXML
    private RadioButton radioTom;
    @FXML
    private RadioButton radioCantor;
    @FXML
    private Label lblTom;

    private HashMap<String, Tom> mapaIntegrantesAssociados;

    private Musica musicaSelecionada;

    private void initComponents() {
        final ToggleGroup grupo = new ToggleGroup();

        this.radioTom.setToggleGroup(grupo);
        this.radioCantor.setToggleGroup(grupo);
        this.mapaIntegrantesAssociados = new HashMap<>();
    }

    /**
     * Inicializa os dados principais da classe
     *
     * @param musicaSelecionada
     */
    public void initData(final Musica musicaSelecionada) {
        this.musicaSelecionada = musicaSelecionada;

        for (AssociacaoIntegranteMusica associacao : this.musicaSelecionada.getAssociacoes()) {
            this.mapaIntegrantesAssociados.put(associacao.getIntegrante().getPrimeiroNome(), associacao.getTom());
        }

        this.comboCantor.setItems(FXCollections.observableList(new ArrayList<>(this.mapaIntegrantesAssociados.keySet())));
        this.comboTom.setItems(FXCollections.observableList(Arrays.asList(Tom.values())));

        //Define um conversor para verificar qual o correspondente de cada tom
        this.comboTom.setConverter(new StringConverter<Tom>() {
            @Override
            public String toString(Tom tom) {
                return tom.toString() + ((tom == musicaSelecionada.getTom()) ? " - Original" : "");
            }

            @Override
            public Tom fromString(String tom) {
                if (tom.endsWith("al")) {//Se termina com a palavra ORIGINAL, ignore-a
                    tom = tom.split(" ")[0];
                }
                return Tom.valueOf(tom);
            }
        });

        this.comboTom.getSelectionModel().select(this.musicaSelecionada.getTom());
        this.radioTom.setSelected(true);
        this.comboCantor.setDisable(true);

        //Não há sentido de haver seleção por autor se a lista estiver vazia
        if (this.comboCantor.getItems().isEmpty()) {
            this.radioCantor.setDisable(true);
        }
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
    private void onMouseClickedFromLblCantor(MouseEvent event) {
        this.comboCantor.requestFocus();
    }

    @FXML
    private void onMouseClickedFromLblTom(MouseEvent event) {
        this.comboTom.requestFocus();
    }

    @FXML
    private void onActionFromComboCantor(ActionEvent event) {
        this.comboTom.getSelectionModel().select(this.mapaIntegrantesAssociados.get(
                this.comboCantor.getSelectionModel().getSelectedItem()));
        this.comboCantor.setEffect(null);
    }

    @FXML
    private void onActionFromComboTom(ActionEvent event) {
        this.comboCantor.getSelectionModel().select(null);
    }

    @FXML
    private void onMouseClickedFromLblTipoImpressao(MouseEvent event) {
        if (this.radioTom.isSelected()) {
            this.radioTom.requestFocus();
        } else {
            this.radioCantor.requestFocus();
        }
    }

    @FXML
    private void onActionFromRadioTom(ActionEvent event) {
        this.comboCantor.setDisable(true);
        this.comboTom.setDisable(false);
        this.comboCantor.getSelectionModel().select(null);
        this.comboCantor.setEffect(null);
        this.comboTom.getSelectionModel().select(this.musicaSelecionada.getTom());
    }

    @FXML
    private void onActionFromRadioCantor(ActionEvent event) {
        this.comboCantor.setDisable(false);
        this.comboTom.setDisable(true);
    }

    private boolean validarGeracao() {
        if (this.radioCantor.isSelected() && this.comboCantor.getSelectionModel().getSelectedItem() == null) {
            this.comboCantor.setEffect(EfeitosUtil.getEfeitoInvalido());
            return false;
        }
        return true;
    }

    @FXML
    private void onActionFromBtnGerar(ActionEvent event) {
        if (validarGeracao()) {
            //Novas criações
            Map<String, Object> parametros = new HashMap<>();
            String nomeArquivoMusica = this.musicaSelecionada.getAutor().getNome() + " - "
                    + this.musicaSelecionada.getNome() + ".pdf";

            //Escolha do local para salvar o arquivo
            JFileChooser fileChooser = new JFileChooser(".");
            fileChooser.setSelectedFile(new File(nomeArquivoMusica));
            fileChooser.setFileFilter(new FileNameExtensionFilter("Arquivo PDF", "pdf"));

            int resposta = fileChooser.showSaveDialog(null);

            if (resposta == JFileChooser.APPROVE_OPTION) {
                //Pré-definições
                Tom tomSelecionado = this.comboTom.getSelectionModel().getSelectedItem();
                Tom tomOriginal = this.musicaSelecionada.getTom();
                String nomeCantorMusica = this.comboCantor.getSelectionModel().getSelectedItem();
                String introducaoMusica = this.musicaSelecionada.getDocumentoMusica().getIntroducao();
                String conteudoMusica = this.musicaSelecionada.getDocumentoMusica().getConteudo();

                //Definições para impressão
                parametros.put("tiposMusica", ListaUtil.getListaSeparadaPorVirgula(this.musicaSelecionada.getTipos()));
                parametros.put("tituloMusica", this.musicaSelecionada.getTitulo());
                parametros.put("cantorMusica", nomeCantorMusica == null ? "" : nomeCantorMusica);
                parametros.put("tomMusica", tomSelecionado.toString());
                if (tomOriginal.equals(tomSelecionado)) {
                    parametros.put("introducaoMusica", introducaoMusica == null ? ""
                            : MusicaUtil.limparParaImpressao(introducaoMusica));
                    parametros.put("conteudoMusica", conteudoMusica == null ? ""
                            : MusicaUtil.limparParaImpressao(conteudoMusica));
                } else {
                    parametros.put("introducaoMusica", introducaoMusica == null ? ""
                            : MusicaUtil.limparParaImpressao(
                                    MusicaUtil.converterTom(introducaoMusica,
                                            this.musicaSelecionada.getTom(),
                                            tomSelecionado)));
                    parametros.put("conteudoMusica", conteudoMusica == null ? ""
                            : MusicaUtil.limparParaImpressao(
                                    MusicaUtil.converterTom(conteudoMusica,
                                            this.musicaSelecionada.getTom(),
                                            tomSelecionado)));
                }

                //Instancia um novo controlador
                PDFController controladorPDF = new PDFController();

                try {
                    controladorPDF.gerarPDF("/snu/fronteiras/visao/pdfs/musica/impressao_musica.jasper", parametros, fileChooser.getSelectedFile().getAbsolutePath());
                    Dialogs.showInformationDialog(null, "O arquivo da Música foi gerada com sucesso!", "Sucesso", "Informação");
                } catch (JRException ex) {
                    Logger.getLogger(GerarImpressaoMusicaController.class.getName()).log(Level.SEVERE, null, ex);
                    Dialogs.showErrorDialog(null, "Erro na geração do arquivo", "Erro", "Erro");
                } finally {
                    this.popupGerarImpressaoMusica.getScene().getWindow().hide();
                }
            }
        } else {
            Dialogs.showWarningDialog(null, "Favor corrigir os campos assinalados!", "Campos Inválidos", "Aviso");
        }
    }

    @FXML
    private void onActionFromBtnCancelar(ActionEvent event) {
        this.popupGerarImpressaoMusica.getScene().getWindow().hide();
    }
}
