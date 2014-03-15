/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.util;

import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Utilitário para realizar operações com expressões regulares
 *
 * @author Washington Luis
 */
public class RegexUtil {

    private static Pattern padrao = null;

    private static final String regexAcordes
            = "(\\b[\\(\\/]{0,2})" //spaces, opening parenthesis, /
            + "(([ABCDEFG])([b\u266D#\u266F])?)" //note name + accidental
            //\u266D = flat, \u266E = natural, \u266F = sharp
            + "([#mM1234567890(dim)(min)(sus)(maj)(aug)(add)øØ°\u00D8\u00F8\u00B0\u0394\u2206\\-\\+]*)"
            //\u00F8 = slashed o, \u00D8 = slashed O, \u00B0 = degree
            //(html &oslash;, &Oslash;, &deg;)
            //delta = Maj7, maths=\u2206, greek=\u0394
            + "((\\/)(([ABCDEFG])([b\u266D#\u266F])?)?([#mbM1234567890(dim)(min)(sus)(maj)(aug)(add)øØ°\u00D8\u00F8\u00B0\u0394\u2206\\-\\+]*))?" // /bass
            + "(\\)?\\b)" //closing parenthesis, spaces
            ;

    /**
     * Verifica a validade de um e-mail
     *
     * @param email
     * @return
     */
    public static boolean validarEmail(String email) {
        padrao = Pattern.compile("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}");
        return padrao.matcher(email.toLowerCase(new Locale("pt", "BR"))).matches();
    }

    /**
     * Retorna a RegEx para acordes musicais
     *
     * @return
     */
    public static String getRegexAcordes() {
        return regexAcordes;
    }

    /**
     * Casa o telefone passado com a expressão regular de telefone.
     * (xx)xxxx-xxxx
     *
     * @param telefone
     * @return
     */
    public static boolean validarTelefone(String telefone) {
        padrao = Pattern.compile("(\\d\\d)[0-9]{4,5}-[0-9]{4}");
        return padrao.matcher(telefone.toLowerCase(new Locale("pt", "BR"))).matches();
    }
}
