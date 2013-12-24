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

    public static boolean validarEmail(String email) {
        padrao = Pattern.compile("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}");
        return padrao.matcher(email.toLowerCase()).matches();
    }
    
}
