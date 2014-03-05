/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.util;

import eu.schudt.javafx.controls.calendar.DatePicker;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Utilitário para realizar operações com datas
 *
 * @author Washington Luis
 */
public class DataUtil {

    /**
     * Retorna um componente DatePicker com as configurações para o local atual.
     *
     * @return
     */
    public static DatePicker getDatePicker() {
        DatePicker dp = new DatePicker();
        dp.setDateFormat(DateFormat.getDateInstance(DateFormat.MEDIUM, new Locale("pt", "BR")));
        dp.getCalendarView().todayButtonTextProperty().set("Hoje");
        dp.getCalendarView().setShowWeeks(false);
        dp.getStylesheets().add("/snu/fronteiras/css/datePicker.css");
        return dp;
    }

    /**
     * Calcula a idade de acordo com uma data passada
     *
     * @param dataPassada
     * @return
     */
    public static Integer getIdade(Date dataPassada) {
        Calendar cData = Calendar.getInstance();
        Calendar cDataAtual = Calendar.getInstance();
        cData.setTime(dataPassada);
        cData.set(Calendar.YEAR, cDataAtual.get(Calendar.YEAR));

        Integer idade = cData.after(cDataAtual) ? -1 : 0;

        cData.setTime(dataPassada);

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
}
