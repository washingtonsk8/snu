/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores.integrante;

import java.net.URL;
import java.util.ResourceBundle;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import snu.entidades.integrante.Integrante;

/**
 *
 * @author Washington Luis
 */
public class VisualizarIntegranteControllerTest {

    public VisualizarIntegranteControllerTest() {
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
     * Test of initialize method, of class VisualizarIntegranteController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        URL url = null;
        ResourceBundle rb = null;
        VisualizarIntegranteController instance = new VisualizarIntegranteController();
        instance.initialize(url, rb);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initData method, of class VisualizarIntegranteController.
     */
    @Test
    public void testInitData() {
        System.out.println("initData");
        Integrante integranteSelecionado = null;
        PesquisarIntegranteController controladorOrigem = null;
        VisualizarIntegranteController instance = new VisualizarIntegranteController();
        instance.initData(integranteSelecionado, controladorOrigem);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
