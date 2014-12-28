/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.util;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * Utilitário para realizar operações com efeitos
 *
 * @author Washington Luis
 */
public class EfeitosUtil {

    private static final Effect efeitoInvalido = new DropShadow(BlurType.GAUSSIAN, Color.RED, 15, 0.0, 0, 0);
    private static final Effect efeitoValido = new DropShadow(BlurType.GAUSSIAN, Color.GREEN, 15, 0.0, 0, 0);
    private static final Effect efeitoAviso = new DropShadow(BlurType.GAUSSIAN, Color.YELLOW, 15, 0.0, 0, 0);
    private static final Effect efeitoGeral = new DropShadow(BlurType.GAUSSIAN, Color.BLUE, 15, 0.0, 0, 0);
    private static final Effect efeitoCeuAzul = new DropShadow(BlurType.GAUSSIAN, Color.SKYBLUE, 15, 0.0, 0, 0);
    private static final Effect efeitoGlow = new Glow();

    /**
     * Retorna o efeito para indicar que algo está inválido
     *
     * @return
     */
    public static Effect getEfeitoInvalido() {
        return efeitoInvalido;
    }

    /**
     * Retorna o efeito para indicar que algo está válido
     *
     * @return
     */
    public static Effect getEfeitoValido() {
        return efeitoValido;
    }

    /**
     * Retorna o efeito para indicar que algo está em advertência
     *
     * @return
     */
    public static Effect getEfeitoAviso() {
        return efeitoAviso;
    }

    /**
     * Retorna o efeito de cor padrão do sistema
     *
     * @return
     */
    public static Effect getEfeitoGeral() {
        return efeitoGeral;
    }

    /**
     * Retorna o efeito de Glow
     *
     * @return
     */
    public static Effect getEfeitoGlow() {
        return efeitoGlow;
    }

    /**
     * Retorna o efeito de céu azul
     *
     * @return
     */
    public static Effect getEfeitoCeuAzul() {
        return efeitoCeuAzul;
    }

    /**
     * Roda efeito FADE IN na tela passada por parâmetro
     *
     * @param tela
     */
    public static void rodarEfeitoCarregamentoFadeIn(Node tela) {
        for (Node filho : ((AnchorPane) tela).getChildren()) {
            final FadeTransition ft = new FadeTransition(Duration.millis(500), filho);
            ft.setFromValue(0.);
            ft.setToValue(1.);
            ft.play();
        }
    }

    /**
     * Roda efeito FADE OUT na tela passada por parâmetro e remove a tela atual
     * dos filhos do pai
     *
     * @param tela
     * @param componentes
     */
    public static void rodarEfeitoCarregamentoFadeOut(Node tela, ObservableList<Node> componentes) {
        final FadeTransition ft = new FadeTransition(Duration.millis(300), tela);
        ft.setFromValue(1.);
        ft.setToValue(0.);
        ft.play();

        //Remove esta tela do vetor de componentes
        ft.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                componentes.remove(tela);
            }
        });
    }

    /**
     * Roda efeito TRANSLATE na tela passada por parâmetro
     *
     * @param tela
     */
    public static void rodarEfeitoCarregamentoTranslate(Node tela) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(250), tela);

        tt.setFromX(800f);
        tt.setToX(0f);
        tt.play();
    }
}
