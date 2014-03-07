/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.bd;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Dialogs;
import javafx.stage.Stage;
import snu.controladores.SNU;

/**
 * Classe necessária para realizar operações com o mysqldump (Backup e Restore).
 *
 * @author Washington Luis
 */
public class BDDump {

    private static final String usuarioBD = "snu";
    private static final String senhaBD = "snu#1.0rocks!";

    public static boolean doBakup(String diretorioArquivo) {
        Date dataAtual = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");

        String nomeArquivo = "bkp_snu_" + dateFormat.format(dataAtual);

        String comando = "\"" + SNU.configuracoesSistema.getDiretorioSGBD() + "\\mysqldump\" -u" + usuarioBD + " -p" + senhaBD + " --add-drop-database -B snu -r " + "\"" + diretorioArquivo + "\\" + nomeArquivo + ".sql\"";

        try {
            Process processo = Runtime.getRuntime().exec(comando);
            int resultadoProcesso = processo.waitFor();

            if (resultadoProcesso != 0) {
                Logger.getLogger(BDDump.class.getName()).log(Level.SEVERE, "Erro no Backup");
                return false;
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(BDDump.class.getName()).log(Level.SEVERE, "Erro no Backup", ex);
            return false;
        }
        return true;
    }

    public static boolean doRestore(String caminhoArquivo) {
        String[] comandos = new String[]{SNU.configuracoesSistema.getDiretorioSGBD() + "\\mysql ", "--user=" + usuarioBD, "--password=" + senhaBD, "-e", "source " + caminhoArquivo};

        try {
            Process processo = Runtime.getRuntime().exec(comandos);
            int resultadoProcesso = processo.waitFor();

            if (resultadoProcesso != 0) {
                Logger.getLogger(BDDump.class.getName()).log(Level.SEVERE, "Erro no Restore");
                return false;
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(BDDump.class.getName()).log(Level.SEVERE, "Erro no Restore", ex);
            return false;
        }
        return true;
    }
}
