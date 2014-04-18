/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.util;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Washington Luis
 */
public class ListaUtilTest {

    public ListaUtilTest() {
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
     * Test of getListaSeparadaPorPontoVirgula method, of class ListaUtil.
     */
    @Test
    public void testGetListaSeparadaPorPontoVirgula() {
        System.out.println("getListaSeparadaPorPontoVirgula");
        List lista = null;
        String expResult = "";
        String result = ListaUtil.getListaSeparadaPorPontoVirgula(lista);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getListaSeparadaPorVirgula method, of class ListaUtil.
     */
    @Test
    public void testGetListaSeparadaPorVirgula() {
        System.out.println("getListaSeparadaPorVirgula");
        List lista = null;
        String expResult = "";
        String result = ListaUtil.getListaSeparadaPorVirgula(lista);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of converterListaParaListaDeStrings method, of class ListaUtil.
     */
    @Test
    public void testConverterListaParaListaDeStrings() {
        System.out.println("converterListaParaListaDeStrings");
        List lista = null;
        List<String> expResult = null;
        List<String> result = ListaUtil.converterListaParaListaDeStrings(lista);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
