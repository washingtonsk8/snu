/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.controladores;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * Gerencia a geração de PDFs
 *
 * @author Washington Luis
 */
public class PDFController {

    /**
     * Carrega o jasper report passado por parâmetro. Insere os parâmetros
     * passados para o report e salva o arquivo pelo caminho do arquivo
     * especificado
     *
     * @param caminhoReport
     * @param parametros
     * @param caminhoArquivo
     * @throws JRException
     */
    public void gerarPDF(final String caminhoReport, final Map<String, Object> parametros, String caminhoArquivo) throws JRException {
        final JasperReport jasperReport = carregarReport(caminhoReport);
        final JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, new JREmptyDataSource());
        JasperExportManager.exportReportToPdfFile(jasperPrint, caminhoArquivo);
    }

    /**
     * Realiza o carregameto do jasper report e o retorna
     *
     * @param caminhoReport
     * @return
     * @throws JRException
     */
    private JasperReport carregarReport(final String caminhoReport) throws JRException {
        final InputStream is = getClass().getResourceAsStream(caminhoReport);
        final JasperReport jasperReport = (JasperReport) JRLoader.loadObject(is);

        return jasperReport;
    }
}
