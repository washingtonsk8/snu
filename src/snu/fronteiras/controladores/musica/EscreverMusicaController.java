/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores.musica;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.log4j.Logger;
import snu.entidades.musica.Musica;
import snu.fronteiras.controladores.FXMLDocumentController;
import snu.fronteiras.controladores.HomeController;
import snu.fronteiras.controladores.musica.popups.RemoverDeteccoesController;
import snu.fronteiras.interfaces.ControladorDeConteudoInterface;
import snu.util.BotoesImagemUtil;
import snu.util.Dialogs;
import snu.util.EfeitosUtil;
import snu.util.MusicaUtil;
import snu.util.StringUtil;

/**
 * Classe controladora do FXML
 *
 * @author Washington Luis
 */
public class EscreverMusicaController implements Initializable {

    @FXML
    private AnchorPane contentEscreverMusica;
    @FXML
    private Label lblEscreverMusica;
    @FXML
    private TextArea areaEscreverMusica;
    @FXML
    private Button btnOk;
    @FXML
    private Font x1;
    @FXML
    private Button btnDetectarAcordes;
    @FXML
    private Button btnRemoverDeteccoes;
    @FXML
    private Button btnPreVisualizar;
    @FXML
    private Label lblIntroducao;
    @FXML
    private TextField fldIntroducao;
    @FXML
    private TextField fldPreVisualizarIntroducao;
    @FXML
    private TextArea areaPreVisualizarEscreverMusica;
    @FXML
    private Button btnFecharPreVisualizacao;
    @FXML
    private Label lblInformacaoPaginas;
    @FXML
    private ImageView imgInicio;
    @FXML
    private ImageView iconeOk;
    @FXML
    private ImageView imgVoltar;
    @FXML
    private Label lblEspacamentoEntreAcordes;
    @FXML
    private ImageView iconeAumentoEspacamento;
    @FXML
    private ImageView iconeReducaoEspacamento;

    private FadeTransition fadeInPreVisualizarIntroducao;

    private FadeTransition fadeInPreVisualizarConteudo;

    private FadeTransition fadeInIntroducao;

    private FadeTransition fadeInConteudo;

    private FadeTransition fadeInBtnPreVisualizar;

    private FadeTransition fadeInBtnFecharPreVisualizacao;

    private ControladorDeConteudoInterface controladorOrigem;

    private Musica musica;

    private boolean acordesDetectados;

    //Inicializando o Logger
    private static final Logger log = Logger.getLogger(EscreverMusicaController.class.getName());
    
    private void initComponents() {
        this.fldPreVisualizarIntroducao.toBack();
        this.areaPreVisualizarEscreverMusica.toBack();
        this.btnFecharPreVisualizacao.setText("Fechar\nPré-Visualização");
        this.btnFecharPreVisualizacao.toBack();

        //Inicializa os objetos de transição visual
        this.fadeInIntroducao = new FadeTransition(Duration.seconds(0.6), fldIntroducao);
        this.fadeInConteudo = new FadeTransition(Duration.seconds(0.6), areaEscreverMusica);
        this.fadeInPreVisualizarIntroducao = new FadeTransition(Duration.seconds(0.6), fldPreVisualizarIntroducao);
        this.fadeInPreVisualizarConteudo = new FadeTransition(Duration.seconds(0.6), areaPreVisualizarEscreverMusica);
        this.fadeInBtnPreVisualizar = new FadeTransition(Duration.seconds(0.6), btnFecharPreVisualizacao);
        this.fadeInBtnFecharPreVisualizacao = new FadeTransition(Duration.seconds(0.6), btnFecharPreVisualizacao);

        //Define valor de início da transição
        this.fadeInIntroducao.setFromValue(0);
        this.fadeInConteudo.setFromValue(0);
        this.fadeInPreVisualizarIntroducao.setFromValue(0);
        this.fadeInPreVisualizarConteudo.setFromValue(0);
        this.fadeInBtnPreVisualizar.setFromValue(0);
        this.fadeInBtnFecharPreVisualizacao.setFromValue(0);

        //Define valor de fim da transição
        this.fadeInIntroducao.setToValue(1);
        this.fadeInConteudo.setToValue(1);
        this.fadeInPreVisualizarIntroducao.setToValue(1);
        this.fadeInPreVisualizarConteudo.setToValue(1);
        this.fadeInBtnPreVisualizar.setToValue(1);
        this.fadeInBtnFecharPreVisualizacao.setToValue(1);

        this.lblInformacaoPaginas.setText("A música está ocupando 1 página(s) no momento.");

        this.areaEscreverMusica.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
                lblInformacaoPaginas.setText("A música está ocupando "
                        + MusicaUtil.contarPaginas(newValue)
                        + " página(s) no momento.");
            }
        });

        BotoesImagemUtil.definirComportamento(this.imgInicio);
        BotoesImagemUtil.definirComportamento(this.imgVoltar);
        BotoesImagemUtil.definirComportamento(this.iconeAumentoEspacamento);
        BotoesImagemUtil.definirComportamento(this.iconeReducaoEspacamento);
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

    public void initData(Musica musica, ControladorDeConteudoInterface controladorOrigem) {
        final String introducaoMusica = musica.getDocumentoMusica().getIntroducao();
        final String conteudoMusica = musica.getDocumentoMusica().getConteudo();

        this.musica = musica;
        this.controladorOrigem = controladorOrigem;

        this.fldIntroducao.setText(introducaoMusica);
        this.areaEscreverMusica.setText(conteudoMusica);

        this.lblInformacaoPaginas.setText("A música está ocupando "
                + MusicaUtil.contarPaginas(musica.getDocumentoMusica().getConteudo()) + " página(s) no momento.");

        //Definindo habilitação do botão de detecção
        if (introducaoMusica != null) {
            this.acordesDetectados = introducaoMusica.contains("@");
        }
        if (conteudoMusica != null && !this.acordesDetectados) {
            this.acordesDetectados = conteudoMusica.contains("@");
        }
        this.btnDetectarAcordes.setDisable(this.acordesDetectados);
        this.iconeAumentoEspacamento.setMouseTransparent(!this.acordesDetectados);
        this.iconeReducaoEspacamento.setMouseTransparent(!this.acordesDetectados);
    }

    @FXML
    private void onMouseClickedFromLblIntroducao(MouseEvent event) {
        this.fldIntroducao.requestFocus();
    }

    @FXML
    private void onMouseClickedFromImgInicio(MouseEvent event) {
        FXMLDocumentController.getInstancia().iniciarPaginaInicial();
    }

    @FXML
    private void onActionFromBtnDetectarAcordes(ActionEvent event) {
        String introducaoMusica = this.fldIntroducao.getText();
        String introducaoMusicaComAcordesDetectados = MusicaUtil.detectarAcordes(introducaoMusica);
        this.fldIntroducao.setText(introducaoMusicaComAcordesDetectados);

        String conteudoMusica = this.areaEscreverMusica.getText();
        String conteudoMusicaComAcordesDetectados = MusicaUtil.detectarAcordes(conteudoMusica);
        this.areaEscreverMusica.setText(conteudoMusicaComAcordesDetectados);

        this.iconeAumentoEspacamento.setMouseTransparent(false);
        this.iconeReducaoEspacamento.setMouseTransparent(false);

        //Desabilitar botão para eliminar possível recursão de detecção
        this.btnDetectarAcordes.setDisable(true);
        this.acordesDetectados = true;

        abrirPopupRemocaoDeteccoes();
    }

    @FXML
    private void onActionFromBtnOk(ActionEvent event) {
        this.musica.getDocumentoMusica().setIntroducao(this.fldIntroducao.getText());
        this.musica.getDocumentoMusica().setConteudo(this.areaEscreverMusica.getText());

        //Carrega a página anterior
        AnchorPane pai = ((AnchorPane) this.contentEscreverMusica.getParent());
        EfeitosUtil.rodarEfeitoCarregamentoFadeOut(this.contentEscreverMusica, pai.getChildren());
    }

    @FXML
    private void onActionFromBtnRemoverDeteccoes(ActionEvent event) {
        //Remover os acordes
        String introducaoMusica = this.fldIntroducao.getText();
        if (StringUtil.hasAlgo(introducaoMusica)) {
            this.fldIntroducao.setText(introducaoMusica.replace("@", ""));
        }

        String conteudoMusica = this.areaEscreverMusica.getText();
        if (StringUtil.hasAlgo(conteudoMusica)) {
            this.areaEscreverMusica.setText(conteudoMusica.replace("@", ""));
        }

        this.iconeAumentoEspacamento.setMouseTransparent(true);
        this.iconeReducaoEspacamento.setMouseTransparent(true);

        //Reabilitar o botão de detecção de acordes
        this.btnDetectarAcordes.setDisable(false);
        this.acordesDetectados = false;
    }

    @FXML
    private void onActionFromBtnPreVisualizar(ActionEvent event) {
        this.fldPreVisualizarIntroducao.setText(
                MusicaUtil.limparParaImpressao(this.fldIntroducao.getText()));

        this.areaPreVisualizarEscreverMusica.setText(
                MusicaUtil.limparParaImpressao(this.areaEscreverMusica.getText()));

        this.fldIntroducao.toBack();
        this.areaEscreverMusica.toBack();
        this.btnPreVisualizar.toBack();

        this.fldPreVisualizarIntroducao.toFront();
        this.areaPreVisualizarEscreverMusica.toFront();
        this.btnFecharPreVisualizacao.toFront();

        this.fadeInPreVisualizarIntroducao.playFromStart();
        this.fadeInPreVisualizarConteudo.playFromStart();
        this.fadeInBtnFecharPreVisualizacao.playFromStart();

        this.btnDetectarAcordes.setDisable(true);
        this.btnOk.setDisable(true);
        this.btnRemoverDeteccoes.setDisable(true);
    }

    @FXML
    private void onActionFromBtnFecharPreVisualizacao(ActionEvent event) {
        this.fldPreVisualizarIntroducao.toBack();
        this.areaPreVisualizarEscreverMusica.toBack();
        this.btnFecharPreVisualizacao.toBack();

        this.fldIntroducao.toFront();
        this.areaEscreverMusica.toFront();
        this.btnPreVisualizar.toFront();

        this.fadeInIntroducao.playFromStart();
        this.fadeInConteudo.playFromStart();
        this.fadeInBtnPreVisualizar.playFromStart();

        this.btnDetectarAcordes.setDisable(this.acordesDetectados);
        this.btnOk.setDisable(false);
        this.btnRemoverDeteccoes.setDisable(false);
    }

    @FXML
    private void onMouseClickedFromIconeAumentoEspacamento(MouseEvent event) {
        String conteudo = this.areaEscreverMusica.getText();
        conteudo = conteudo.replaceAll("@", " @");
        this.areaEscreverMusica.setText(conteudo);
        this.areaPreVisualizarEscreverMusica.setText(MusicaUtil.limparParaImpressao(conteudo));
    }

    @FXML
    private void onMouseClickedFromIconeReducaoEspacamento(MouseEvent event) {
        String conteudo = this.areaEscreverMusica.getText();
        conteudo = conteudo.replaceAll("  @", " @");
        this.areaEscreverMusica.setText(conteudo);
        this.areaPreVisualizarEscreverMusica.setText(MusicaUtil.limparParaImpressao(conteudo));
    }

    @FXML
    private void onMouseClickedFromImgVoltar(MouseEvent event) {
        boolean conteudoIdentico = MusicaUtil.isConteudoIdentico(this.musica,
                this.fldIntroducao.getText(), this.areaEscreverMusica.getText());

        Dialogs.DialogResponse resposta = conteudoIdentico ? Dialogs.DialogResponse.YES
                : Dialogs.showConfirmDialog(HomeController.getInstancia().getStage(),
                        "Deseja realmente voltar?\nAo voltar o conteúdo não salvo será perdido.",
                        "Cancelamento", "Confirmação");

        if (resposta.equals(Dialogs.DialogResponse.YES)) {
            //Carrega a página anterior
            AnchorPane pai = ((AnchorPane) this.contentEscreverMusica.getParent());
            EfeitosUtil.rodarEfeitoCarregamentoFadeOut(this.contentEscreverMusica, pai.getChildren());
        }
    }

    private void abrirPopupRemocaoDeteccoes() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/musica/popups/RemoverDeteccoes.fxml"));
        AnchorPane root = null;
        try {
            root = (AnchorPane) fxmlLoader.load();
        } catch (IOException ex) {
            log.error("Erro ao carregar tela para Remoção de Detecções", ex);
            Dialogs.showErrorDialog(HomeController.getInstancia().getStage(),
                    "Erro ao carregar tela para Remoção de Detecções."
                    + "\nFavor entrar em contato com o Administrador.",
                    "Erro!", "Erro", ex);
        }

        //Inicializa os dados passando a música por parâmetro
        RemoverDeteccoesController removerDeteccoesController = fxmlLoader.getController();
        removerDeteccoesController.initData(this.areaEscreverMusica.getText());

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Remoção de Detecções");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(HomeController.getInstancia().getStage().getScene().getWindow());
        dialogStage.setScene(new Scene(root));
        dialogStage.getIcons().add(new Image("/snu/fronteiras/images/icons/iconeConfiguracao.png"));
        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();
        
        this.areaEscreverMusica.setText(removerDeteccoesController.getConteudoMusica());        
    }
}
