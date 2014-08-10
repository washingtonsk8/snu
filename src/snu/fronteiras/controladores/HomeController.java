/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import snu.util.EfeitosUtil;

/**
 * Classe controladora do FXML
 *
 * @author Washington Luis
 */
public class HomeController implements Initializable {

    @FXML
    private AnchorPane contentHome;
    @FXML
    private ImageView logoSistema;
    
    private void initComponents(){
        this.logoSistema.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logoSistema.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.SKYBLUE, 15, 0.0, 0, 0));
            }
        });

        this.logoSistema.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logoSistema.setEffect(null);
            }
        });
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
}
