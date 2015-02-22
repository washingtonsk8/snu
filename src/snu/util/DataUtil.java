/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.apache.log4j.Logger;
import snu.fronteiras.controladores.HomeController;

/**
 * Utilitário para realizar operações com datas
 *
 * @author Washington Luis
 */
public class DataUtil {

    //Inicializando o Logger

    private static final Logger log = Logger.getLogger(DataUtil.class.getName());

    /**
     * Calcula a idade de acordo com uma data passada
     *
     * @param data
     * @return
     */
    public static Integer getIdade(Date data) {
        Calendar cData = Calendar.getInstance();
        Calendar cDataAtual = Calendar.getInstance();
        cData.setTime(data);
        cData.set(Calendar.YEAR, cDataAtual.get(Calendar.YEAR));

        Integer idade = cData.after(cDataAtual) ? -1 : 0;

        cData.setTime(data);

        idade += cDataAtual.get(Calendar.YEAR) - cData.get(Calendar.YEAR);

        return idade > 0 ? idade : 0;
    }

    /**
     * Realiza a formatação de data de acordo com o local atual (Brasil)
     *
     * @param data
     * @return
     */
    public static String formatarData(Date data) {
        if (data == null) {
            return null;
        }

        SimpleDateFormat sdt = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
        return sdt.format(data);
    }

    /**
     * Realiza a formatação de data e hora de acordo com o local atual (Brasil)
     *
     * @param data
     * @return
     */
    public static String formatarDataHora(Date data) {
        if (data == null) {
            return null;
        }

        SimpleDateFormat sdt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("pt", "BR"));
        return sdt.format(data);
    }

    /**
     * Converte a data passada para data do tipo LocalDate
     *
     * @param data
     * @return
     */
    public static LocalDate toLocalDate(Date data) {
        if (data == null) {
            return null;
        }
        Instant instant = Instant.ofEpochMilli(data.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Converte a data passada para data do tipo Date
     *
     * @param data
     * @return
     */
    public static Date toDate(LocalDate data) {
        if (data == null) {
            return null;
        }
        Instant instant = data.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    /**
     * Converte a data passada de string para data do tipo Date
     *
     * @param data
     * @return
     */
    public static Date stringToDate(String data) {
        if (data == null) {
            return null;
        }
        try {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            return format.parse(data);
        } catch (ParseException ex) {
            log.error("Erro ao converter data", ex);
            Dialogs.showErrorDialog(HomeController.getInstancia().getStage(), "Erro ao converter data."
                    + "\nFavor entrar em contato com o Administrador.",
                    "Erro!", "Erro", ex);
        }
        return null;
    }

    /**
     * Converte a data passada de string para data do tipo Date
     *
     * @param data
     * @return
     */
    public static Date stringToDateHour(String data) {
        if (data == null) {
            return null;
        }
        try {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return format.parse(data);
        } catch (ParseException ex) {
            log.error("Erro ao converter data", ex);
            Dialogs.showErrorDialog(HomeController.getInstancia().getStage(), "Erro ao converter data."
                    + "\nFavor entrar em contato com o Administrador.",
                    "Erro!", "Erro", ex);
        }
        return null;
    }
}
