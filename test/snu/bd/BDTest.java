/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.bd;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class BDTest {

    public BDTest() {
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
     * Test of doBakup method, of class BD.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testDoBakup() throws Exception {
        System.out.println("doBakup");
        String nomeDiretorioExportacao = "";
        boolean expResult = false;
        boolean result = BD.doBakup(nomeDiretorioExportacao);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of doRestore method, of class BD.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testDoRestore() throws Exception {
        System.out.println("doRestore");
        File arquivoImportacao = null;
        boolean expResult = false;
        boolean result = BD.doRestore(arquivoImportacao);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
