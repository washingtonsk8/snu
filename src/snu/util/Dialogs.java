/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 *
 * @author Washington Luis
 */
public class Dialogs {

    /**
     * An enumeration used to specify the response provided by the user when
     * interacting with a dialog.
     */
    public static enum DialogResponse {

        /**
         * Used to represent that the user has selected the option corresponding
         * with YES.
         */
        YES,
        /**
         * Used to represent that the user has selected the option corresponding
         * with NO.
         */
        NO,
        /**
         * Used to represent that the user has selected the option corresponding
         * with CANCEL.
         */
        CANCEL,
        /**
         * Used to represent that the user has selected the option corresponding
         * with OK.
         */
        OK,
        /**
         * Used to represent that the user has selected the option corresponding
         * with CLOSED.
         */
        CLOSED
    }

    /**
     * An enumeration used to specify which buttons to show to the user in a
     * dialog.
     */
    public static enum DialogOptions {

        /**
         * Used to specify that two buttons should be shown, with default labels
         * specified as 'Yes' and 'No'.
         */
        YES_NO,
        /**
         * Used to specify that three buttons should be shown, with default
         * labels specified as 'Yes', 'No', and 'Cancel'.
         */
        YES_NO_CANCEL,
        /**
         * Used to specify that one button should be shown, with the default
         * label specified as 'Ok'.
         */
        OK,
        /**
         * Used to specify that two buttons should be shown, with default labels
         * specified as 'Ok' and 'Cancel'.
         */
        OK_CANCEL;
    }
    
    public static void showInformationDialog(Stage stage, String mensagem, String cabecalho, String titulo) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecalho);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public static void showInformationDialog(Stage stage, String mensagem, String cabecalho) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(cabecalho);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public static void showWarningDialog(Stage stage, String mensagem, String cabecalho, String titulo) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecalho);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public static boolean showConfirmDialog(Stage stage, String mensagem, String cabecalho, String titulo) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecalho);
        alert.setContentText(mensagem);
        
        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }

    public static void showErrorDialog(Stage stage, String mensagem, String cabecalho, String titulo) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecalho);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public static void showErrorDialog(Stage stage, String mensagem, String cabecalho, String titulo, Exception excecao) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecalho);
        alert.setContentText(mensagem);

        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        excecao.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }

    public static String showInputDialog(Stage stage, String mensagem, String cabecalho, String titulo, String entrada) {
        TextInputDialog dialog = new TextInputDialog(entrada);
        dialog.setTitle(titulo);
        dialog.setHeaderText(cabecalho);
        dialog.setContentText(mensagem);

        Optional<String> result = dialog.showAndWait();
        return result.isPresent() ? result.get() : null;
    }
}
