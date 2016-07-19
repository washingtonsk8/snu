/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.util;

import java.util.Optional;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;

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

    public static void showInformationDialog(Stage stage, String mensagem, String cabecalho) {
        showInformationDialog(stage, mensagem, cabecalho, null);
    }

    public static void showInformationDialog(Stage stage, String mensagem, String cabecalho, String titulo) {
        org.controlsfx.dialog.Dialogs.create()
                .owner(stage)
                .title(titulo)
                .masthead(cabecalho)
                .message(mensagem)
                .showInformation();
    }

    public static void showWarningDialog(Stage stage, String mensagem, String cabecalho, String titulo) {
        org.controlsfx.dialog.Dialogs.create()
                .owner(stage)
                .title(titulo)
                .masthead(cabecalho)
                .message(mensagem)
                .showWarning();
    }

    public static DialogResponse showConfirmDialog(Stage stage, String mensagem, String cabecalho, String titulo) {
        Action resposta = org.controlsfx.dialog.Dialogs.create()
                .owner(stage)
                .title(titulo)
                .masthead(cabecalho)
                .message(mensagem)
                .actions(Dialog.ACTION_YES, Dialog.ACTION_NO)
                .showConfirm();
        return resposta == Dialog.ACTION_YES ? DialogResponse.YES : DialogResponse.NO;
    }

    public static void showErrorDialog(Stage stage, String mensagem, String cabecalho, String titulo) {
        org.controlsfx.dialog.Dialogs.create()
                .owner(stage)
                .title(titulo)
                .masthead(cabecalho)
                .message(mensagem)
                .showError();
    }

    public static void showErrorDialog(Stage stage, String mensagem, String cabecalho, String titulo, Exception excecao) {
        org.controlsfx.dialog.Dialogs.create()
                .owner(stage)
                .title(titulo)
                .masthead(cabecalho)
                .message(mensagem)
                .showException(excecao);
    }

    public static String showInputDialog(Stage stage, String mensagem, String cabecalho, String titulo, String entrada) {
        Optional<String> resposta = org.controlsfx.dialog.Dialogs.create()
                .owner(stage)
                .title(titulo)
                .masthead(cabecalho)
                .message(mensagem)
                .showTextInput(entrada);
        return resposta.isPresent() ? resposta.get() : null;
    }
}
