/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.bd;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManager;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import org.hibernate.internal.SessionImpl;

/**
 * Classe necessária para realizar operações com o mysqldump (Backup e Restore).
 *
 * @author Washington Luis
 */
public class BD {

    private static final String SENHA_ARQUIVO_ZIP = "snu#2.0rocks!";

    /*
     ATENÇÃO: NÃO ALTERAR A ORDEM DE IMPORTAÇÃO DAS TABELAS!
     As Foreign Keys definem qual deve ser a ordem
     */
    private static final String[] nomesTabelas = {"INTEGRANTE", "SYS", "MISSA", "MUSICA_AUTORES", "MUSICA_DOCUMENTO",
        "DOCUMENTOMUSICA_VOCABULARIO", "DOCUMENTOMUSICA_LISTAINVERTIDA", "MUSICA", "MISSAS_MUSICAS",
        "MUSICA_ASSOCIACOES", "MUSICA_LEITURASASSOCIADAS", "MUSICA_TIPOS"};

    /**
     * Realiza o backup do banco no diretório escolhido
     *
     * @param nomeDiretorioExportacao
     * @return
     * @throws IOException
     * @throws net.lingala.zip4j.exception.ZipException
     */
    public static boolean doBakup(String nomeDiretorioExportacao) throws IOException, ZipException {

        Date hoje = new Date();
        SimpleDateFormat sdt = new SimpleDateFormat("dd-MM-yy_hh-mm-ss");
        nomeDiretorioExportacao += "\\bkp_snu " + sdt.format(hoje);
        String arquivoZip = nomeDiretorioExportacao + ".zip";
        nomeDiretorioExportacao += "\\";
        File diretorio = new File(nomeDiretorioExportacao);
        if (!diretorio.mkdir()) {
            throw new IOException("O diretório não pode ser criado, verifique as permissões da pasta!");
        }

        ZipFile zipFile = new ZipFile(arquivoZip);

        ZipParameters parametros = new ZipParameters();
        parametros.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
        parametros.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
        parametros.setEncryptFiles(true);
        parametros.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
        parametros.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
        parametros.setPassword(SENHA_ARQUIVO_ZIP);

        zipFile.addFile(new File("snu/snu.script"), parametros);

        return true;
    }

    /**
     * Realiza o restore para o banco a partir do arquivo escolhido
     *
     * @param arquivoImportacao
     * @return
     * @throws IOException
     * @throws net.lingala.zip4j.exception.ZipException
     */
    public static boolean doRestore(File arquivoImportacao) throws IOException, ZipException {

        File arquivoScriptAtual = new File("snu/snu.script");
        File arquivoScriptBkp = new File("snu/snu.script.bkp");
        
        if(arquivoScriptBkp.exists()){
            arquivoScriptBkp.delete();
        }

        if (!arquivoScriptAtual.renameTo(arquivoScriptBkp)) {
            throw new IOException("Não foi possível criar o arquivo de backup das informações atuais");
        }

        ZipFile zipFile = new ZipFile(arquivoImportacao);
        zipFile.setPassword(SENHA_ARQUIVO_ZIP);

        //Extrai o arquivo de script para a pasta do banco
        zipFile.extractAll("snu");

        return true;
    }

    /**
     * Restaura a versão anterior do banco
     *
     * @return
     * @throws IOException
     */
    public static boolean restorePreviousVersion() throws IOException {
        File arquivoScriptVersaoAnterior = new File("snu/snu.script.bkp");

        if (!arquivoScriptVersaoAnterior.exists()) {
            throw new IOException("Não foi possível restaurar a versão anterior, pois o arquivo não existe");
        }

        if (!arquivoScriptVersaoAnterior.renameTo(new File("snu/snu.script"))) {
            throw new IOException("Não foi possível restaurar a versão anterior");
        }
        return true;
    }
}
