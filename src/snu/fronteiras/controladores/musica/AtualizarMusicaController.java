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
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javafx.util.Pair;
import org.apache.log4j.Logger;
import snu.controladores.IntegranteJpaController;
import snu.controladores.MusicaJpaController;
import snu.controladores.ObjetoListaInvertidaJpaController;
import snu.exceptions.NonexistentEntityException;
import snu.controladores.indexador.IndexadorController;
import snu.dto.ParametrosPesquisaMusica;
import snu.entidades.integrante.Integrante;
import snu.entidades.musica.Afinacao;
import snu.entidades.musica.AssociacaoIntegranteMusica;
import snu.entidades.musica.Musica;
import snu.entidades.musica.TipoMusica;
import snu.entidades.musica.Tom;
import snu.entidades.musica.Autor;
import snu.entidades.musica.LeituraAssociada;
import snu.fronteiras.controladores.FXMLDocumentController;
import snu.fronteiras.controladores.geral.ProgressoController;
import snu.fronteiras.interfaces.ControladorDeConteudoInterface;
import snu.fronteiras.controladores.musica.popups.SelecionarAutorController;
import snu.util.EfeitosUtil;
import snu.util.ListaUtil;
import snu.util.StringUtil;

/**
 * Classe controladora do FXML
 *
 * @author Washington Luis
 */
public class AtualizarMusicaController implements Initializable, ControladorDeConteudoInterface {

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
    private CheckBox checkAtoPenitencial;
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
    private Label lblLinkVideo;
    @FXML
    private AnchorPane contentAtualizarMusica;
    @FXML
    private Button btnSelecionarAutor;
    @FXML
    private Button btnAtualizarConteudo;
    @FXML
    private Button btnSalvarAtleracoes;
    @FXML
    private TextField fldLinkVideo;
    @FXML
    private Label lblAtualizarMusica;

    private List<Pair<TipoMusica, CheckBox>> parTiposMusicaCheckBoxes;

    private ObservableList<AssociacaoIntegranteMusica> itensAssociacao;

    private List<Integrante> integrantesAssociados;

    private final ObservableList<Tom> tonsMusica = FXCollections.observableArrayList(Tom.values());

    private final ObservableList<Afinacao> afinacoesMusica = FXCollections.observableArrayList(Afinacao.values());

    private Musica musica;

    private TemplatePesquisaMusicaController controladorOrigem;

    private String conteudoAnterior;

    //Inicializando o Logger
    private static final Logger log = Logger.getLogger(AtualizarMusicaController.class.getName());

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
        this.parTiposMusicaCheckBoxes.add(new Pair<>(TipoMusica.ATO_PENITENCIAL, this.checkAtoPenitencial));
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

    public void initData(Musica musica, TemplatePesquisaMusicaController controladorOrigem) {
        this.musica = musica;
        this.controladorOrigem = controladorOrigem;

        this.comboAfinacao.setValue(musica.getAfinacao());
        this.fldAutor.setText(musica.getAutor().getNome());
        this.fldLinkVideo.setText(musica.getLinkVideo());
        this.fldLeituras.setText(ListaUtil.getListaSeparadaPorPontoVirgula(musica.getLeiturasAssociadas()));

        List<TipoMusica> tiposMusica = musica.getTipos();

        for (Pair<TipoMusica, CheckBox> tipoMusica : this.parTiposMusicaCheckBoxes) {
            if (tiposMusica.contains(tipoMusica.getKey())) {
                tipoMusica.getValue().setSelected(true);
            }
        }

        this.fldTitulo.setText(musica.getNome());
        this.comboTom.setValue(musica.getTom());
        this.itensAssociacao.addAll(musica.getAssociacoes());
        this.tblAssociacoes.setItems(FXCollections.observableArrayList(musica.getAssociacoes()));
        this.conteudoAnterior = this.musica.getDocumentoMusica().getConteudo();
    }

    private boolean validarMusicaComMesmoNome(ActionEvent event) {
        if (this.fldTitulo.getText().equals(this.musica.getNome())) {
            return true;//Retorna verdadeiro caso o nome não tenha sido alterado!
        }
        try {
            ParametrosPesquisaMusica parametrosPesquisa = new ParametrosPesquisaMusica();
            parametrosPesquisa.setNomeMusica(this.fldTitulo.getText());
            List<Musica> musicasEncontradas = MusicaJpaController.getInstancia()
                    .findMusicasByParametrosPesquisa(parametrosPesquisa);

            if (!musicasEncontradas.isEmpty()) {
                Dialogs.DialogResponse resposta;
                if (musicasEncontradas.size() == 1) {
                    resposta = Dialogs.showConfirmDialog(FXMLDocumentController.getInstancia().getStage(),
                            "Foi encontrada a seguinte Música com o mesmo nome:\n"
                            + "\t" + musicasEncontradas.get(0).getTitulo() + "\n"
                            + "\nDeseja atualizar a Música mesmo assim?",
                            "Criação de Música com mesmo nome", "Confirmação");
                } else {
                    String textoQuestionamento = "Foram encontradas as seguintes Músicas com o mesmo nome:\n";

                    for (Musica musicaEncontrada : musicasEncontradas) {
                        textoQuestionamento += "\t" + musicaEncontrada.getTitulo() + "\n";
                    }

                    textoQuestionamento += "\nDeseja atualizar a Música mesmo assim?";

                    resposta = Dialogs.showConfirmDialog(FXMLDocumentController.getInstancia().getStage(), textoQuestionamento,
                            "Criação de Música com mesmo nome", "Confirmação");
                }

                return resposta.equals(Dialogs.DialogResponse.YES);
            }
            return true;
        } catch (IOException ex) {
            log.error("Erro ao pesquisar músicas por parâmetros para atualizar Música", ex);
            Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(),
                    "Erro ao atualizar Música.\nFavor entrar em contato com o Administrador.",
                    "Erro!", "Erro", ex);
        }
        return false;
    }

    private void salvarAlteracoes() {
        this.musica.setNome(this.fldTitulo.getText());
        this.musica.setAssociacoes(this.itensAssociacao);
        this.musica.setTom(this.comboTom.getValue());
        this.musica.setAfinacao(this.comboAfinacao.getValue());
        this.musica.setLinkVideo(this.fldLinkVideo.getText());
        String novoConteudo = this.musica.getDocumentoMusica().getConteudo();

        if (StringUtil.diferentes(this.conteudoAnterior, novoConteudo)) {
            this.musica.getDocumentoMusica().setQuantidadeTokens(0);
            this.musica.getDocumentoMusica().setFrequenciaMaximaToken(0);
            this.musica.getDocumentoMusica().setConteudo(novoConteudo);
            try {
                //Remove as indexações anteriores
                ObjetoListaInvertidaJpaController.getInstancia().destroyByMusicaId(this.musica.getId());
                IndexadorController.getInstancia().indexar(this.musica);
            } catch (NonexistentEntityException ex) {
                log.error("Erro ao remover Música", ex);
                Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(),
                        "Erro de processamento interno.\nFavor entrar em contato com o Administrador.", "Erro interno!", "Erro", ex);
            } catch (Exception ex) {
                log.error("Erro ao indexar Música", ex);
                Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(),
                        "Erro ao realizar a indexação da Música.\nFavor entrar em contato com o Administrador.",
                        "Erro!", "Erro", ex);
            }
        }

        String campoLeiturasAssociadas = this.fldLeituras.getText();

        if (!campoLeiturasAssociadas.isEmpty()) {
            List<LeituraAssociada> leiturasAssociadas = new ArrayList<>();

            for (String descricaoLeituraAssociada : Arrays.asList(campoLeiturasAssociadas.split(";"))) {
                if (StringUtil.hasAlgo(descricaoLeituraAssociada = descricaoLeituraAssociada.trim())) {
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

        try {
            //Atualizando no banco
            MusicaJpaController.getInstancia().edit(this.musica);
        } catch (Exception ex) {
            log.error("Erro ao atualizar Música", ex);
            Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(),
                    "Erro ao atualizar a Música!\nFavor entrar em contato com o Administrador.", "Erro!", "Erro", ex);
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

    /**
     * Retorna o contentPane principal da classe
     *
     * @return
     */
    @Override
    public AnchorPane getContentPane() {
        return contentAtualizarMusica;
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
    private void onMouseClickedFromLblLinkVideo(MouseEvent event) {
        this.fldLinkVideo.requestFocus();
    }

    @FXML
    private void onMouseClickedFromContentAtualizarMusica(MouseEvent event) {
        this.contentAtualizarMusica.requestFocus();
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
    private void onActionFromFldLinkVideo(ActionEvent event) {
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
    private void onActionFromCheckAtoPenitencial(ActionEvent event) {
        if (this.checkAtoPenitencial.isSelected()) {
            this.musica.adicionarTipo(TipoMusica.ATO_PENITENCIAL);
        } else {
            this.musica.removerTipo(TipoMusica.ATO_PENITENCIAL);
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
            Dialogs.showWarningDialog(FXMLDocumentController.getInstancia().getStage(),
                    "Favor selecionar um Integrante e um Tom para associar.", "Valores inválidos!", "Aviso");
        }
    }

    @FXML
    private void onActionFromBtnRemoverAssociacao(ActionEvent event) {
        int indiceSelecionado = this.tblAssociacoes.getSelectionModel().getSelectedIndex();

        if (indiceSelecionado >= 0) {
            Dialogs.DialogResponse resposta = Dialogs.showConfirmDialog(FXMLDocumentController.getInstancia().getStage(),
                    "Tem certeza que deseja excluir a Associação?", "Exclusão de Associação", "Confirmação");

            if (resposta.equals(Dialogs.DialogResponse.YES)) {
                this.itensAssociacao.remove(indiceSelecionado);
                //Define a lista de associações atual na tabela
                this.tblAssociacoes.setItems(this.itensAssociacao);
                atualizarTabela();
            }
        } else {
            Dialogs.showWarningDialog(FXMLDocumentController.getInstancia().getStage(),
                    "Favor selecionar uma Associação para a exclusão.", "Associação não selecionada!", "Aviso");
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
    private void onActionFromBtnAtualizarConteudo(ActionEvent event) {
        //Apaga efeitos anteriores
        this.btnAtualizarConteudo.setEffect(null);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/musica/EscreverMusica.fxml"));

        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException ex) {
            log.error("Erro ao carregar tela para Escrever o Conteúdo", ex);
            Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(),
                    "Erro ao carregar tela para Escrever o Conteúdo.\nFavor entrar em contato com o Administrador.",
                    "Erro!", "Erro", ex);
        }

        EscreverMusicaController escreverMusicaController = fxmlLoader.getController();

        //Limpa o conteúdo anterior e carrega a página
        AnchorPane pai = ((AnchorPane) this.contentAtualizarMusica.getParent());
        escreverMusicaController.initData(musica, this);
        pai.getChildren().clear();
        pai.getChildren().add(root);
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
    private void onActionFromBtnSelecionarAutor(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/musica/popups/SelecionarAutor.fxml"));
        AnchorPane root = null;
        try {
            root = (AnchorPane) fxmlLoader.load();
        } catch (IOException ex) {
            log.error("Erro ao carregar tela para Seleção de Autor", ex);
            Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(),
                    "Erro ao carregar tela para Seleção de Autor.\nFavor entrar em contato com o Administrador.",
                    "Erro!", "Erro", ex);
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

    @FXML
    private void onActionFromBtnSalvarAtleracoes(ActionEvent event) {
        if (validarCampos()) {
            if (validarMusicaComMesmoNome(event)) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/snu/fronteiras/visao/geral/Progresso.fxml"));
                Parent root = null;
                try {
                    root = (Parent) fxmlLoader.load();
                } catch (IOException ex) {
                    log.error("Erro ao carregar popup de Progresso", ex);
                    Dialogs.showErrorDialog(FXMLDocumentController.getInstancia().getStage(),
                            "Erro ao carregar popup de Progresso.\nFavor entrar em contato com o Administrador.",
                            "Erro!", "Erro", ex);
                }

                ProgressoController progressoController = fxmlLoader.getController();
                progressoController.initData("Reindexando e atualizando a Música. Aguarde...");
                progressoController.setProgresso(-1.0);

                final Stage dialogStage = new Stage();
                dialogStage.setTitle("Por Favor Aguarde...");
                dialogStage.toFront();
                dialogStage.setResizable(false);
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(FXMLDocumentController.getInstancia().getStage());
                dialogStage.setScene(new Scene(root));

                final Task task = new Task<Void>() {
                    @Override
                    public Void call() {
                        salvarAlteracoes();
                        return null;
                    }
                };

                dialogStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        if (task.isRunning()) {
                            event.consume();
                        }
                    }
                });

                task.stateProperty().addListener(new ChangeListener<Task.State>() {
                    @Override
                    public void changed(ObservableValue<? extends Task.State> observableValue, Task.State oldState, Task.State state) {
                        switch (state) {
                            case RUNNING:
                                dialogStage.showAndWait();
                                break;
                            case SUCCEEDED:
                                Dialogs.showInformationDialog(FXMLDocumentController.getInstancia().getStage(),
                                        "A Música foi atualizada com sucesso!", "Sucesso!", "Informação");
                                dialogStage.close();
                                break;
                            case CANCELLED:
                            case FAILED:
                                dialogStage.close();
                                break;
                        }
                    }
                });
                new Thread(task).start();
            }
        } else {
            Dialogs.showWarningDialog(FXMLDocumentController.getInstancia().getStage(),
                    "Favor corrigir os campos assinalados.", "Campos Inválidos!", "Aviso");
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
