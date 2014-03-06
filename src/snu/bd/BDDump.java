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

/**
 * Classe necessária para realizar operações com o mysqldump (Backup e Restore).
 *
 * @author Washington Luis
 */
public class BDDump {

    private static final String usuarioBD = "snu";
    private static final String senhaBD = "snu#1.0rocks!";

    public static void doBakup(String diretorioArquivo) {
        Date dataAtual = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");

        String nomeArquivo = "bkp_snu_" + dateFormat.format(dataAtual);

        String comando = "\"C:\\Program Files\\MySQL\\MySQL Server 5.6\\bin\\mysqldump\" -u" + usuarioBD + " -p" + senhaBD + " --add-drop-database -B snu -r " + "\"" + diretorioArquivo + "\\" + nomeArquivo + ".sql\"";

        try {
            Process processo = Runtime.getRuntime().exec(comando);
            int resultadoProcesso = processo.waitFor();

            if (resultadoProcesso == 0) {
                System.out.println("Backup completo!");
            } else {
                //Reportar Erro!
                Logger.getLogger(BDDump.class.getName()).log(Level.SEVERE, "Erro na criação2");
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(BDDump.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void doRestore(String caminhoArquivo) {
        String[] comandos = new String[]{"C:\\Program Files\\MySQL\\MySQL Server 5.6\\bin\\mysql ", "--user=" + usuarioBD, "--password=" + senhaBD, "-e", "source " + caminhoArquivo};

        try {
            Process processo = Runtime.getRuntime().exec(comandos);
            int resultadoProcesso = processo.waitFor();

            if (resultadoProcesso == 0) {
                System.out.println("Restore completo!");
            } else {
                //Reportar Erro!
                Logger.getLogger(BDDump.class.getName()).log(Level.SEVERE, "Erro no Restore");
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(BDDump.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
