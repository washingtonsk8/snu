/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.bd;

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
public class BDDumpTest {

    public BDDumpTest() {
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
     * Test of doBakup method, of class BDDump.
     */
    @Test
    public void testDoBakup() {
        System.out.println("doBakup");
        String diretorioArquivo = "C:\\Users\\User\\Desktop";
        BDDump.doBakup(diretorioArquivo);
    }

    /**
     * Test of doRestore method, of class BDDump.
     *
     */
    @Test
    public void testDoRestore() {
        System.out.println("doRestore");
        String caminhoArquivo = "C:\\Users\\User\\Desktop\\bkp_snu_06-03-2014_11-43-29.sql";
        BDDump.doRestore(caminhoArquivo);
    }

}