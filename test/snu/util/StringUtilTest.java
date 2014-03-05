/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snu.util;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Washington Luis
 */
public class StringUtilTest {
    
    public StringUtilTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of isVazia method, of class StringUtil.
     */
    @Test
    public void testIsVazia() {
        System.out.println("isVazia");
        String string = "";
        boolean expResult = false;
        boolean result = StringUtil.isVazia(string);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasAlgo method, of class StringUtil.
     */
    @Test
    public void testHasAlgo() {
        System.out.println("hasAlgo");
        String string = "";
        boolean expResult = false;
        boolean result = StringUtil.hasAlgo(string);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removerAcentos method, of class StringUtil.
     */
    @Test
    public void testRemoverAcentos() {
        System.out.println("removerAcentos");
        String entrada = "";
        String expResult = "";
        String result = StringUtil.removerAcentos(entrada);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of somenteLetras method, of class StringUtil.
     */
    @Test
    public void testSomenteLetras() {
        System.out.println("somenteLetras");
        String entrada = "";
        boolean manterDelimitadores = false;
        String expResult = "";
        String result = StringUtil.somenteLetras(entrada, manterDelimitadores);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
