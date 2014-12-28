/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.util;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Washington Luis
 */
public class BotoesImagemUtil {

    private static final Double TAMANHO_ORIGINAL_IMAGEM = 1.0;
    private static final Double TAMANHO_AUMENTADO_IMAGEM = 1.15;
    private static final Double TAMANHO_AUMENTADO_IMAGEM_TELA_INICIAL = 1.05;

    private static boolean isMouseSobre(final ImageView imagem, MouseEvent evento) {
        return (evento.getX() > 0 && evento.getX() < (imagem.getFitWidth() * imagem.getScaleX()))
                && (evento.getY() > 0 && evento.getY() < (imagem.getFitHeight() * imagem.getScaleY()));
    }

    public static void definirComportamento(final ImageView imagem) {
        imagem.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                imagem.setScaleX(TAMANHO_AUMENTADO_IMAGEM);
                imagem.setScaleY(TAMANHO_AUMENTADO_IMAGEM);
                imagem.setEffect(EfeitosUtil.getEfeitoGeral());
            }
        });

        imagem.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                imagem.setScaleX(TAMANHO_ORIGINAL_IMAGEM);
                imagem.setScaleY(TAMANHO_ORIGINAL_IMAGEM);
                imagem.setEffect(null);
            }
        });

        imagem.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                imagem.setScaleX(TAMANHO_ORIGINAL_IMAGEM);
                imagem.setScaleY(TAMANHO_ORIGINAL_IMAGEM);
            }
        });

        imagem.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (isMouseSobre(imagem, event)) {
                    imagem.setScaleX(TAMANHO_AUMENTADO_IMAGEM);
                    imagem.setScaleY(TAMANHO_AUMENTADO_IMAGEM);
                }
            }
        });
    }

    public static void definirComportamentoTelaInicial(final ImageView imagem) {
        imagem.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                imagem.setScaleX(TAMANHO_AUMENTADO_IMAGEM_TELA_INICIAL);
                imagem.setScaleY(TAMANHO_AUMENTADO_IMAGEM_TELA_INICIAL);
                imagem.setEffect(EfeitosUtil.getEfeitoCeuAzul());
            }
        });

        imagem.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                imagem.setScaleX(TAMANHO_ORIGINAL_IMAGEM);
                imagem.setScaleY(TAMANHO_ORIGINAL_IMAGEM);
                imagem.setEffect(null);
            }
        });

        imagem.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                imagem.setScaleX(TAMANHO_ORIGINAL_IMAGEM);
                imagem.setScaleY(TAMANHO_ORIGINAL_IMAGEM);
            }
        });

        imagem.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (isMouseSobre(imagem, event)) {
                    imagem.setScaleX(TAMANHO_AUMENTADO_IMAGEM_TELA_INICIAL);
                    imagem.setScaleY(TAMANHO_AUMENTADO_IMAGEM_TELA_INICIAL);
                }
            }
        });
    }
}
