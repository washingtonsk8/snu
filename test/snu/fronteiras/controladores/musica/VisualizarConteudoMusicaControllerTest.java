/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snu.fronteiras.controladores.musica;

import java.net.URL;
import java.util.ResourceBundle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import snu.entidades.musica.Musica;

/**
 *
 * @author Washington Luis
 */
public class VisualizarConteudoMusicaControllerTest {
    
    public VisualizarConteudoMusicaControllerTest() {
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
     * Test of initData method, of class VisualizarConteudoMusicaController.
     */
    @Test
    public void testInitData() {
        System.out.println("initData");
        Musica musica = null;
        VisualizarMusicaController controladorOrigem = null;
        VisualizarConteudoMusicaController instance = new VisualizarConteudoMusicaController();
        instance.initData(musica, controladorOrigem);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initialize method, of class VisualizarConteudoMusicaController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        URL url = null;
        ResourceBundle rb = null;
        VisualizarConteudoMusicaController instance = new VisualizarConteudoMusicaController();
        instance.initialize(url, rb);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}