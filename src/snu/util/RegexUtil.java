/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.util;

import java.util.regex.Pattern;

/**
 * Classe utilitária para realizar operações de expressão regular
 *
 * @author Washington Luis
 */
public class RegexUtil {

    private static Pattern padrao = null;

    private static String regexAcordes
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

    public static boolean validarEmail(String email) {
        padrao = Pattern.compile("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}");
        return padrao.matcher(email.toLowerCase()).matches();
    }

    public static String getRegexAcordes(){
        return regexAcordes;
    }
}
