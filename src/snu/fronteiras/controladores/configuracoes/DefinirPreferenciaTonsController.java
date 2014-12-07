/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores.configuracoes;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import org.apache.log4j.Logger;
import snu.controladores.ConfiguracoesSistemaJpaController;
import snu.controladores.SNU;
import snu.entidades.configuracoes.ConfiguracoesSistema;
import snu.fronteiras.controladores.FXMLDocumentController;
import snu.fronteiras.controladores.HomeController;
import snu.util.Dialogs;

/**
 * Classe controladora do FXML
 *
 * @author Washington Luis
 */
public class DefinirPreferenciaTonsController implements Initializable {

    @FXML
    private AnchorPane popupDefinirPreferenciaTons;
    @FXML
    private Label lblDefinirPreferenciaTons;
    @FXML
    private Font x1;
    @FXML
    private Button btnOk;
    @FXML
    private Font x2;
    @FXML
    private Label lblTomCsus;
    @FXML
    private Label lblTomDbem;
    @FXML
    private Slider sldTonsCD;
    @FXML
    private Label lblTomDsus;
    @FXML
    private Label lblTomEbem;
    @FXML
    private Slider sldTonsDE;
    @FXML
    private Label lblTomFsus;
    @FXML
    private Label lblTomGbem;
    @FXML
    private Slider sldTonsFG;
    @FXML
    private Label lblTomGsus;
    @FXML
    private Label lblTomAbem;
    @FXML
    private Slider sldTonsGA;
    @FXML
    private Label lblTomAsus;
    @FXML
    private Label lblTomBbem;
    @FXML
    private Slider sldTonsAB;
    @FXML
    private ImageView iconeOk;

    private final ConfiguracoesSistema configuracoesSistema = SNU.configuracoesSistema;

    //Inicializando o Logger
    private static final org.apache.log4j.Logger log
            = Logger.getLogger(DefinirPreferenciaTonsController.class.getName());

    /**
     * Inicializa as ações do controlador
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        sldTonsCD.setValue(configuracoesSistema.isPreferenciaCsusDbem() ? 100. : 0.);
        sldTonsDE.setValue(configuracoesSistema.isPreferenciaDsusEbem() ? 100. : 0.);
        sldTonsFG.setValue(configuracoesSistema.isPreferenciaFsusGbem() ? 100. : 0.);
        sldTonsGA.setValue(configuracoesSistema.isPreferenciaGsusAbem() ? 100. : 0.);
        sldTonsAB.setValue(configuracoesSistema.isPreferenciaAsusBbem() ? 100. : 0.);

        sldTonsCD.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                if (newValue.floatValue() >= 50.f) {
                    sldTonsCD.setValue(100.);
                    configuracoesSistema.setPreferenciaCsusDbem(Boolean.TRUE);
                } else {
                    sldTonsCD.setValue(0.);
                    configuracoesSistema.setPreferenciaCsusDbem(Boolean.FALSE);
                }
            }
        });

        sldTonsDE.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                if (newValue.floatValue() >= 50.f) {
                    sldTonsDE.setValue(100.);
                    configuracoesSistema.setPreferenciaDsusEbem(Boolean.TRUE);
                } else {
                    sldTonsDE.setValue(0.);
                    configuracoesSistema.setPreferenciaDsusEbem(Boolean.FALSE);
                }
            }
        });

        sldTonsFG.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                if (newValue.floatValue() >= 50.f) {
                    sldTonsFG.setValue(100.);
                    configuracoesSistema.setPreferenciaFsusGbem(Boolean.TRUE);
                } else {
                    sldTonsFG.setValue(0.);
                    configuracoesSistema.setPreferenciaFsusGbem(Boolean.FALSE);
                }
            }
        });

        sldTonsGA.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                if (newValue.floatValue() >= 50.f) {
                    sldTonsGA.setValue(100.);
                    configuracoesSistema.setPreferenciaGsusAbem(Boolean.TRUE);
                } else {
                    sldTonsGA.setValue(0.);
                    configuracoesSistema.setPreferenciaGsusAbem(Boolean.FALSE);
                }
            }
        });

        sldTonsAB.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                if (newValue.floatValue() >= 50.f) {
                    sldTonsAB.setValue(100.);
                    configuracoesSistema.setPreferenciaAsusBbem(Boolean.TRUE);
                } else {
                    sldTonsAB.setValue(0.);
                    configuracoesSistema.setPreferenciaAsusBbem(Boolean.FALSE);
                }
            }
        });
    }

    @FXML
    private void onActionFromBtnOk(ActionEvent event) {
        try {
            ConfiguracoesSistemaJpaController.getInstancia().edit(configuracoesSistema);
            Dialogs.showInformationDialog(HomeController.getInstancia().getStage(),
                    "Alterações realizadas com sucesso!", "Sucesso!", "Informação");
            this.popupDefinirPreferenciaTons.getScene().getWindow().hide();
        } catch (Exception ex) {
            log.error("Erro ao carregar salvar as Configurações do Sistema", ex);
            Dialogs.showErrorDialog(HomeController.getInstancia().getStage(),
                    "Erro de processamento interno.\nFavor entrar em contato com o Administrador.", "Erro interno!", "Erro", ex);
        }
    }
}
