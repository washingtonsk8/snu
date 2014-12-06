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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import org.apache.derby.iapi.services.io.FileUtil;
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
     * @throws InterruptedException
     * @throws net.lingala.zip4j.exception.ZipException
     * @throws java.sql.SQLException
     */
    public static boolean doBakup(String nomeDiretorioExportacao) throws IOException, InterruptedException, ZipException, SQLException {

            Date hoje = new Date();
            SimpleDateFormat sdt = new SimpleDateFormat("dd-MM-yy_hh-mm-ss");
            nomeDiretorioExportacao += "\\bkp_snu " + sdt.format(hoje);
            String arquivoZip = nomeDiretorioExportacao + ".zip";
            nomeDiretorioExportacao += "\\";
            File diretorio = new File(nomeDiretorioExportacao);
            if (!diretorio.mkdir()) {
                throw new IOException("O diretório não pode ser criado, verifique as permissões da pasta!");
            }

            EntityManager em = GerenciadorDeEntidades.getInstancia().getFabrica().createEntityManager();
            em.getTransaction().begin();
            Connection conn = em.unwrap(SessionImpl.class).connection();

            ZipFile zipFile = new ZipFile(arquivoZip);

            ZipParameters parametros = new ZipParameters();
            parametros.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
            parametros.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
            parametros.setEncryptFiles(true);
            parametros.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
            parametros.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
            parametros.setPassword(SENHA_ARQUIVO_ZIP);

            String arquivoExportacao;
            for (String nomeTabela : nomesTabelas) {
                arquivoExportacao = nomeDiretorioExportacao + nomeTabela;
                exportarTabela(conn, nomeTabela, arquivoExportacao);
                zipFile.addFile(new File(arquivoExportacao), parametros);
            }
            em.getTransaction().commit();

            FileUtil.removeDirectory(diretorio);
        return true;
    }

    /**
     * Realiza o restore para o banco a partir do arquivo escolhido
     *
     * @param arquivoImportacao
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws net.lingala.zip4j.exception.ZipException
     */
    public static boolean doRestore(File arquivoImportacao) throws IOException, InterruptedException, ZipException, SQLException {

        ZipFile zipFile = new ZipFile(arquivoImportacao);
        zipFile.setPassword(SENHA_ARQUIVO_ZIP);
        String nomeDiretorioImportacao = arquivoImportacao.toString().replaceFirst(".zip", "");
        zipFile.extractAll(nomeDiretorioImportacao);

        EntityManager em = GerenciadorDeEntidades.getInstancia().getFabrica().createEntityManager();
        em.getTransaction().begin();
        Connection conn = em.unwrap(SessionImpl.class).connection();

        nomeDiretorioImportacao += "\\";

        String nomeArquivoImportacao;
        for (String nomeTabela : nomesTabelas) {
            nomeArquivoImportacao = nomeDiretorioImportacao + nomeTabela;
            importarTabela(conn, nomeTabela, nomeArquivoImportacao);
        }
        em.getTransaction().commit();
        return true;
    }

    private static void exportarTabela(Connection conn, String nomeTabela, String nomeArquivoExportacao) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
                "CALL SYSCS_UTIL.SYSCS_EXPORT_TABLE (?,?,?,?,?,?)");
        ps.setString(1, null);
        ps.setString(2, nomeTabela);
        ps.setString(3, nomeArquivoExportacao);
        ps.setString(4, "%");
        ps.setString(5, null);
        ps.setString(6, "UTF-8");
        ps.execute();
        ps.close();
    }

    private static void importarTabela(Connection conn, String nomeTabela, String nomeArquivoImportacao) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
                "CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE (?,?,?,?,?,?,?)");
        ps.setString(1, null);
        ps.setString(2, nomeTabela);
        ps.setString(3, nomeArquivoImportacao);
        ps.setString(4, "%");
        ps.setString(5, null);
        ps.setString(6, "UTF-8");
        //1 representa que todos os dados serão apagados
        ps.setInt(7, 1);
        ps.execute();
        ps.close();
    }
}
