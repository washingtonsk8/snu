/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snu.fronteiras.controladores.musica;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.layout.AnchorPane;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import snu.geral.TipoPagina;

/**
 *
 * @author Washington Luis
 */
public class TemplatePesquisaMusicaControllerTest {
    
    public TemplatePesquisaMusicaControllerTest() {
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
     * Test of initialize method, of class TemplatePesquisaMusicaController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        URL url = null;
        ResourceBundle rb = null;
        TemplatePesquisaMusicaController instance = new TemplatePesquisaMusicaController();
        instance.initialize(url, rb);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTipoPagina method, of class TemplatePesquisaMusicaController.
     */
    @Test
    public void testGetTipoPagina() {
        System.out.println("getTipoPagina");
        TemplatePesquisaMusicaController instance = new TemplatePesquisaMusicaController();
        TipoPagina expResult = null;
        TipoPagina result = instance.getTipoPagina();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTipoPagina method, of class TemplatePesquisaMusicaController.
     */
    @Test
    public void testSetTipoPagina() {
        System.out.println("setTipoPagina");
        TipoPagina tipoPagina = null;
        TemplatePesquisaMusicaController instance = new TemplatePesquisaMusicaController();
        instance.setTipoPagina(tipoPagina);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContent method, of class TemplatePesquisaMusicaController.
     */
    @Test
    public void testGetContent() {
        System.out.println("getContent");
        TemplatePesquisaMusicaController instance = new TemplatePesquisaMusicaController();
        AnchorPane expResult = null;
        AnchorPane result = instance.getContent();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
