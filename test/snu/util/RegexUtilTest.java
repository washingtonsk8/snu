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
public class RegexUtilTest {
    
    public RegexUtilTest() {
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
     * Test of validarEmail method, of class RegexUtil.
     */
    @Test
    public void testValidarEmail() {
        System.out.println("validarEmail");
        String email = "";
        boolean expResult = false;
        boolean result = RegexUtil.validarEmail(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRegexAcordes method, of class RegexUtil.
     */
    @Test
    public void testGetRegexAcordes() {
        System.out.println("getRegexAcordes");
        String expResult = "";
        String result = RegexUtil.getRegexAcordes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validarTelefone method, of class RegexUtil.
     */
    @Test
    public void testValidarTelefone() {
        System.out.println("validarTelefone");
        String telefone = "";
        boolean expResult = false;
        boolean result = RegexUtil.validarTelefone(telefone);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
