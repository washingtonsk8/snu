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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Pair;
import snu.controladores.IntegranteJpaController;
import snu.controladores.MusicaJpaController;
import snu.controladores.indexador.IndexadorController;
import snu.entidades.integrante.Integrante;
import snu.entidades.musica.Afinacao;
import snu.entidades.musica.AssociacaoIntegranteMusica;
import snu.entidades.musica.Musica;
import snu.entidades.musica.TipoMusica;
import snu.entidades.musica.Tom;
import snu.entidades.musica.Autor;
import snu.entidades.musica.LeituraAssociada;
import snu.fronteiras.interfaces.ControladorDeConteudoInterface;
import snu.fronteiras.controladores.musica.popups.SelecionarAutorController;
import snu.util.EfeitosUtil;
import snu.util.StringUtil;

/**
 * FXML Controller class
 *
 * @author Washington Luis
 */
public class CriarMusicaController implements Initializable, ControladorDeConteudoInterface {

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
    @FXML
    private AnchorPane contentCriarMusica;

    private List<Pair<TipoMusica, CheckBox>> parTiposMusicaCheckBoxes;

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
    @FXML
    private Button btnSelecionarAutor;

    @FXML
    private Button btnCriarNova;

    /**
     * Inicializa os componentes
     */
    private void initComponents() {
        ObservableList<String> nomesIntegrantes = FXCollections.observableArrayList();

        this.musica = new Musica();
        this.itensAssociacao = FXCollections.observableArrayList();
        this.integrantesAssociados = IntegranteJpaController.getInstancia().findIntegranteEntities();
        this.parTiposMusicaCheckBoxes = new ArrayList<>();

        this.comboTom.setItems(this.tonsMusica);//Coloca os tons na combo
        this.comboAfinacao.setItems(this.afinacoesMusica);//Coloca as afinações na combo
        this.comboAfinacao.getSelectionModel().select(Afinacao.EADGBE);//Define uma afinação padrão

        //Coloca os tons na combo para associação
        this.comboTomAssociacao.setItems(this.tonsMusica);

        //Definir impossibilidade de edição do campo autor
        this.fldAutor.setEditable(false);

        //Faz pesquisa no banco pelos integrantes presentes e pega o nome
        for (Integrante integrante : this.integrantesAssociados) {
            nomesIntegrantes.add(integrante.getNome());
        }

        //Coloca os nomes dos integrantes na combo
        this.comboNomeAssociacao.setItems(nomesIntegrantes);

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

        //Adicionando as checkboxes na lista
        this.parTiposMusicaCheckBoxes.add(new Pair<>(TipoMusica.ENTRADA, this.checkEntrada));
        this.parTiposMusicaCheckBoxes.add(new Pair<>(TipoMusica.PERDAO, this.checkPerdao));
        this.parTiposMusicaCheckBoxes.add(new Pair<>(TipoMusica.GLORIA, this.checkGloria));
        this.parTiposMusicaCheckBoxes.add(new Pair<>(TipoMusica.ACLAMACAO, this.checkAclamacao));
        this.parTiposMusicaCheckBoxes.add(new Pair<>(TipoMusica.OFERTORIO, this.checkOfertorio));
        this.parTiposMusicaCheckBoxes.add(new Pair<>(TipoMusica.PAZ, this.checkPaz));
        this.parTiposMusicaCheckBoxes.add(new Pair<>(TipoMusica.SANTO, this.checkSanto));
        this.parTiposMusicaCheckBoxes.add(new Pair<>(TipoMusica.COMUNHAO, this.checkComunhao));
        this.parTiposMusicaCheckBoxes.add(new Pair<>(TipoMusica.ACAO_DE_GRACAS, this.checkAcaoDeGracas));
        this.parTiposMusicaCheckBoxes.add(new Pair<>(TipoMusica.FINAL, this.checkFinal));
        this.parTiposMusicaCheckBoxes.add(new Pair<>(TipoMusica.REFLEXAO, this.checkReflexao));
        this.parTiposMusicaCheckBoxes.add(new Pair<>(TipoMusica.LOUVOR, this.checkLouvor));
        this.parTiposMusicaCheckBoxes.add(new Pair<>(TipoMusica.ADORACAO, this.checkAdoracao));
        this.parTiposMusicaCheckBoxes.add(new Pair<>(TipoMusica.PALESTRA, this.checkPalestra));
        this.parTiposMusicaCheckBoxes.add(new Pair<>(TipoMusica.RESPOSTA, this.checkResposta));
        this.parTiposMusicaCheckBoxes.add(new Pair<>(TipoMusica.ORACAO, this.checkOracao));
        this.parTiposMusicaCheckBoxes.add(new Pair<>(TipoMusica.ESPECIAL, this.checkEspecial));
        this.parTiposMusicaCheckBoxes.add(new Pair<>(TipoMusica.OUTRA, this.checkOutra));

        this.btnSelecionarAutor.requestFocus();
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initComponents();
    }

    /**
     * Retorna o contentPane principal da classe
     *
     * @return
     */
    @Override
    public AnchorPane getContentPane() {
        return contentCriarMusica;
    }

    @FXML
    private void onMouseClickedFromLblLeituras(MouseEvent event) {
        this.fldLeituras.requestFocus();
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
    private void onMouseClickedFromLblTom(MouseEvent event) {
        this.comboTom.requestFocus();
    }

    @FXML
    private void onMouseClickedFromLblAfinacao(MouseEvent event) {
        this.comboAfinacao.requestFocus();
    }

    @FXML
    private void onMouseClickedFromLblAssociacoes(MouseEvent event) {
        this.tblAssociacoes.requestFocus();
    }

    @FXML
    private void onMouseClickedFromLblTipos(MouseEvent event) {
        this.checkEntrada.requestFocus();
    }

    @FXML
    private void onMouseClickedFromContentCriarMusica(MouseEvent event) {
        this.contentCriarMusica.requestFocus();
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
        if (this.checkEntrada.isSelected()) {
            this.musica.adicionarTipo(TipoMusica.ENTRADA);
        } else {
            this.musica.removerTipo(TipoMusica.ENTRADA);
        }
    }

    @FXML
    private void onActionFromCheckPerdao(ActionEvent event) {
        if (this.checkPerdao.isSelected()) {
            this.musica.adicionarTipo(TipoMusica.PERDAO);
        } else {
            this.musica.removerTipo(TipoMusica.PERDAO);
        }
    }

    @FXML
    private void onActionFromCheckGloria(ActionEvent event) {
        if (this.checkGloria.isSelected()) {
            this.musica.adicionarTipo(TipoMusica.GLORIA);
        } else {
            this.musica.removerTipo(TipoMusica.GLORIA);
        }
    }

    @FXML
    private void onActionFromCheckAclamacao(ActionEvent event) {
        if (this.checkAclamacao.isSelected()) {
            this.musica.adicionarTipo(TipoMusica.ACLAMACAO);
        } else {
            this.musica.removerTipo(TipoMusica.ACLAMACAO);
        }
    }

    @FXML
    private void onActionFromCheckOfertorio(ActionEvent event) {
        if (this.checkOfertorio.isSelected()) {
            this.musica.adicionarTipo(TipoMusica.OFERTORIO);
        } else {
            this.musica.removerTipo(TipoMusica.OFERTORIO);
        }
    }

    @FXML
    private void onActionFromCheckPaz(ActionEvent event) {
        if (this.checkPaz.isSelected()) {
            this.musica.adicionarTipo(TipoMusica.PAZ);
        } else {
            this.musica.removerTipo(TipoMusica.PAZ);
        }
    }

    @FXML
    private void onActionFromCheckSanto(ActionEvent event) {
        if (this.checkSanto.isSelected()) {
            this.musica.adicionarTipo(TipoMusica.SANTO);
        } else {
            this.musica.removerTipo(TipoMusica.SANTO);
        }
    }

    @FXML
    private void onActionFromCheckComunhao(ActionEvent event) {
        if (this.checkComunhao.isSelected()) {
            this.musica.adicionarTipo(TipoMusica.COMUNHAO);
        } else {
            this.musica.removerTipo(TipoMusica.COMUNHAO);
        }
    }

    @FXML
    private void onActionFromCheckAcaoDeGracas(ActionEvent event) {
        if (this.checkAcaoDeGracas.isSelected()) {
            this.musica.adicionarTipo(TipoMusica.ACAO_DE_GRACAS);
        } else {
            this.musica.removerTipo(TipoMusica.ACAO_DE_GRACAS);
        }
    }

    @FXML
    private void onActionFromCheckFinal(ActionEvent event) {
        if (this.checkFinal.isSelected()) {
            this.musica.adicionarTipo(TipoMusica.FINAL);
        } else {
            this.musica.removerTipo(TipoMusica.FINAL);
        }
    }

    @FXML
    private void onActionFromCheckReflexao(ActionEvent event) {
        if (this.checkReflexao.isSelected()) {
            this.musica.adicionarTipo(TipoMusica.REFLEXAO);
        } else {
            this.musica.removerTipo(TipoMusica.REFLEXAO);
        }
    }

    @FXML
    private void onActionFromCheckLouvor(ActionEvent event) {
        if (this.checkLouvor.isSelected()) {
            this.musica.adicionarTipo(TipoMusica.LOUVOR);
        } else {
            this.musica.removerTipo(TipoMusica.LOUVOR);
        }
    }

    @FXML
    private void onActionFromCheckAdoracao(ActionEvent event) {
        if (this.checkAdoracao.isSelected()) {
            this.musica.adicionarTipo(TipoMusica.ADORACAO);
        } else {
            this.musica.removerTipo(TipoMusica.ADORACAO);
        }
    }

    @FXML
    private void onActionFromCheckPalestra(ActionEvent event) {
        if (this.checkPalestra.isSelected()) {
            this.musica.adicionarTipo(TipoMusica.PALESTRA);
        } else {
            this.musica.removerTipo(TipoMusica.PALESTRA);
        }
    }

    @FXML
    private void onActionFromCheckResposta(ActionEvent event) {
        if (this.checkResposta.isSelected()) {
            this.musica.adicionarTipo(TipoMusica.RESPOSTA);
        } else {
            this.musica.removerTipo(TipoMusica.RESPOSTA);
        }
    }

    @FXML
    private void onActionFromCheckOracao(ActionEvent event) {
        if (this.checkOracao.isSelected()) {
            this.musica.adicionarTipo(TipoMusica.ORACAO);
        } else {
            this.musica.removerTipo(TipoMusica.ORACAO);
        }
    }

    @FXML
    private void onActionFromCheckEspecial(ActionEvent event) {
        if (this.checkEspecial.isSelected()) {
            this.musica.adicionarTipo(TipoMusica.ESPECIAL);
        } else {
            this.musica.removerTipo(TipoMusica.ESPECIAL);
        }
    }

    @FXML
    private void onActionFromCheckOutra(ActionEvent event) {
        if (this.checkOutra.isSelected()) {
            this.musica.adicionarTipo(TipoMusica.OUTRA);
        } else {
            this.musica.removerTipo(TipoMusica.OUTRA);
        }
    }

    @FXML
    private void onActionFromBtnAdicionarAssociacao(ActionEvent event) {
        int indiceIntegrante = this.comboNomeAssociacao.getSelectionModel().getSelectedIndex();
        Tom tomSelecionado = this.comboTomAssociacao.getValue();
        if (tomSelecionado != null && indiceIntegrante >= 0) {
            //Cria uma nova associação
            AssociacaoIntegranteMusica novaAssociacao = new AssociacaoIntegranteMusica();
            novaAssociacao.setMusica(this.musica);
            novaAssociacao.setIntegrante(this.integrantesAssociados.get(indiceIntegrante));
            novaAssociacao.setTom(tomSelecionado);

            //Adiciona a nova associação na lista de associações desta classe
            this.itensAssociacao.add(novaAssociacao);

            //Define a lista de associações atual na tabela
            this.tblAssociacoes.setItems(this.itensAssociacao);

            //Troca a seleção para nenhum valor para serem adicionados novas associações
            this.comboNomeAssociacao.getSelectionModel().select(null);
            this.comboTomAssociacao.getSelectionModel().select(null);

            //Atualiza a tabela
            atualizarTabela();
        } else {
            Dialogs.showWarningDialog(null, "Favor selecionar um Integrante e um Tom para associar", "Valores inválidos", "Aviso");
        }
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
        this.comboNomeAssociacao.requestFocus();
    }

    @FXML
    private void onActionFromComboTomAssociacao(ActionEvent event) {
        this.comboTomAssociacao.requestFocus();
    }

    @FXML
    private void onActionFromBtnEscreverConteudo(ActionEvent event) {
        //Apaga efeitos anteriores
        this.btnEscreverConteudo.setEffect(null);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/musica/EscreverMusica.fxml"));

        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(CriarMusicaController.class.getName()).log(Level.SEVERE, null, ex);
        }

        EscreverMusicaController escreverMusicaController = fxmlLoader.getController();

        //Limpa o conteúdo anterior e carrega a página
        AnchorPane pai = ((AnchorPane) this.contentCriarMusica.getParent());
        escreverMusicaController.initData(musica, this);
        pai.getChildren().clear();
        pai.getChildren().add(root);
    }

    @FXML
    private void onActionFromBtnCriarNova(ActionEvent event) {
        this.fldAutor.clear();
        this.fldLeituras.clear();
        this.fldTitulo.clear();
        this.comboAfinacao.setValue(Afinacao.EADGBE);
        this.comboTom.setValue(null);
        this.comboNomeAssociacao.setValue(null);
        this.comboTomAssociacao.setValue(null);
        this.itensAssociacao.clear();
        this.tblAssociacoes.setItems(itensAssociacao);

        for (Pair<TipoMusica, CheckBox> par : parTiposMusicaCheckBoxes) {
            par.getValue().setSelected(false);
        }

        this.musica = new Musica();

        this.fldTitulo.requestFocus();
    }

    @FXML
    private void onActionFromBtnSelecionarAutor(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/musica/popups/SelecionarAutor.fxml"));
        AnchorPane root = null;
        try {
            root = (AnchorPane) fxmlLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(CriarMusicaController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Inicializa os dados passando a música por parâmetro
        SelecionarAutorController selecionarAutorController = fxmlLoader.getController();
        selecionarAutorController.initData(this.musica);

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Seleção de Autor");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(((Node) (event.getSource())).getScene().getWindow());
        dialogStage.setScene(new Scene(root));
        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();

        //Após a seleção do autor, atualiza o campo
        Autor autorSelecionado = this.musica.getAutor();
        if (autorSelecionado != null) {
            this.fldAutor.setText(autorSelecionado.getNome());
        }
        this.fldTitulo.requestFocus();//Coloca o foco no campo de título
    }

    private boolean validarCampos() {
        boolean validadeDosCampos = true;
        if (StringUtil.isVazia(this.fldAutor.getText())) {
            this.fldAutor.setEffect(EfeitosUtil.getEfeitoInvalido());
            validadeDosCampos = false;
        } else {
            this.fldAutor.setEffect(null);
        }
        if (StringUtil.isVazia(this.fldTitulo.getText())) {
            this.fldTitulo.setEffect(EfeitosUtil.getEfeitoInvalido());
            validadeDosCampos = false;
        } else {
            this.fldTitulo.setEffect(null);
        }
        if (this.comboTom.getValue() == null) {
            this.comboTom.setEffect(EfeitosUtil.getEfeitoInvalido());
            validadeDosCampos = false;
        } else {
            this.comboTom.setEffect(null);
        }
        if (this.comboAfinacao.getValue() == null) {
            this.comboAfinacao.setEffect(EfeitosUtil.getEfeitoInvalido());
            validadeDosCampos = false;
        } else {
            this.comboAfinacao.setEffect(null);
        }
        if (this.musica.getTipos().isEmpty()) {
            for (Pair<TipoMusica, CheckBox> tipoMusica : parTiposMusicaCheckBoxes) {
                tipoMusica.getValue().setEffect(EfeitosUtil.getEfeitoInvalido());
            }
            validadeDosCampos = false;
        } else {
            for (Pair<TipoMusica, CheckBox> tipoMusica : parTiposMusicaCheckBoxes) {
                tipoMusica.getValue().setEffect(null);
            }
        }
        return validadeDosCampos;
    }

    @FXML
    private void onActionFromBtnSalvar(ActionEvent event) {
        if (validarCampos()) {
            this.musica.setTitulo(this.fldTitulo.getText());
            this.musica.setAssociacoes(this.itensAssociacao);
            this.musica.setTom(this.comboTom.getValue());
            this.musica.setAfinacao(this.comboAfinacao.getValue());

            String campoLeiturasAssociadas = this.fldLeituras.getText();

            if (!campoLeiturasAssociadas.isEmpty()) {
                List<LeituraAssociada> leiturasAssociadas = new ArrayList<>();

                for (String descricaoLeituraAssociada : Arrays.asList(campoLeiturasAssociadas.split(";"))) {
                    descricaoLeituraAssociada = descricaoLeituraAssociada.trim();
                    if (!StringUtil.isVazia(descricaoLeituraAssociada)) {
                        LeituraAssociada leituraAssociada = new LeituraAssociada();
                        leituraAssociada.setDescricao(descricaoLeituraAssociada);
                        leituraAssociada.setMusica(this.musica);
                        leiturasAssociadas.add(leituraAssociada);
                    }
                }
                this.musica.setLeiturasAssociadas(leiturasAssociadas);
            } else {
                this.musica.setLeiturasAssociadas(new ArrayList<LeituraAssociada>());
            }

            if (this.musica.getId() == null) {
                //Persistindo no banco
                MusicaJpaController.getInstancia().create(this.musica);
            } else {
                try {
                    //Atualizando no banco
                    MusicaJpaController.getInstancia().edit(this.musica);
                } catch (Exception ex) {
                    Logger.getLogger(CriarMusicaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (this.musica.getDocumentoMusica().getConteudo() == null || this.musica.getDocumentoMusica().getConteudo().isEmpty()) {
                Dialogs.showWarningDialog(null, "Não esqueça de escrever o conteúdo da música", "Conselho", "Informação");
                this.btnEscreverConteudo.setEffect(EfeitosUtil.getEfeitoAviso());
            }

            IndexadorController.getInstancia().indexar(musica);

            Dialogs.showInformationDialog(null, "A Música foi salva com sucesso!", "Sucesso", "Informação");
        } else {
            Dialogs.showWarningDialog(null, "Favor corrigir os campos assinalados!", "Campos Inválidos", "Aviso");
        }
    }

    private void atualizarTabela() {
        final List<AssociacaoIntegranteMusica> itens = this.tblAssociacoes.getItems();
        if (itens == null || itens.isEmpty()) {
            return;
        }

        final AssociacaoIntegranteMusica item = this.tblAssociacoes.getItems().get(0);
        itens.remove(0);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                itens.add(0, item);
            }
        });
    }

}
