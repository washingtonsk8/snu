/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores.musica.popups;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import snu.util.StringUtil;

/**
 * Classe controladora do FXML
 *
 * @author Washington Luis
 */
public class RemoverDeteccoesController implements Initializable {

    @FXML
    private AnchorPane popupRemoverDeteccoes;
    @FXML
    private Font x1;
    @FXML
    private Button btnRemoverDosSelecionados;
    @FXML
    private ListView<CheckBox> listaRemocaoDeteccoes;

    private Set<String> setRemocaoDeteccoes;

    private String conteudoMusica;

    private void initComponents() {
        this.setRemocaoDeteccoes = new HashSet<>();
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
     * Inicializa os dados principais da classe
     *
     * @param conteudoMusica
     */
    public void initData(String conteudoMusica) {
        this.conteudoMusica = conteudoMusica;
        detectarAnomalias();
    }

    /**
     * Detecta as anomalias de uma música. Anomalias são detecções de acordes
     * feitas de modo errado fazendo com que palavras normais sejam consideradas
     * acordes.
     */
    private void detectarAnomalias() {
        String[] linhas = this.conteudoMusica.split("\n");
        Pattern p = Pattern.compile("[a-zA-Z]");

        for (String linha : linhas) {
            if (!linha.contains("@")) {
                continue;
            }
            String[] palavras = linha.split(" ");
            int i = 0;
            boolean linhaDeAcordes = true;

            /*
             Se a linha conter pelo menos uma palavra que não seja um acorde,
             provavelmente essa linha não é uma linha composta de acordes.
             */
            while (i < palavras.length && linhaDeAcordes) {
                Matcher m = p.matcher(palavras[i]);
                if (m.find() && !palavras[i].contains("@")) {
                    javafx.scene.control.CheckBox novaAnomalia = new CheckBox(linha);
                    novaAnomalia.setSelected(true);
                    if (this.setRemocaoDeteccoes.add(linha)) {
                        this.listaRemocaoDeteccoes.getItems().add(novaAnomalia);
                    }
                    linhaDeAcordes = false;
                }
                i++;
            }
        }
    }

    @FXML
    private void onActionFromBtnRemoverDosSelecionados(ActionEvent event) {
        for (CheckBox checkboxRemocaoDeteccao : this.listaRemocaoDeteccoes.getItems()) {
            if (checkboxRemocaoDeteccao.isSelected()) {
                this.conteudoMusica = this.conteudoMusica.replaceAll(checkboxRemocaoDeteccao.getText(),
                        checkboxRemocaoDeteccao.getText().replaceAll("@", ""));
            }
        }
        this.popupRemoverDeteccoes.getScene().getWindow().hide();
    }

    public String getConteudoMusica() {
        return this.conteudoMusica;
    }

}
