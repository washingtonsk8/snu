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
import snu.entidades.musica.Musica;

/**
 *
 * @author Washington Luis
 */
public class AtualizarMusicaControllerTest {

    public AtualizarMusicaControllerTest() {
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
     * Test of initData method, of class AtualizarMusicaController.
     */
    @Test
    public void testInitData() {
        System.out.println("initData");
        Musica musica = null;
        TemplatePesquisaMusicaController controladorOrigem = null;
        AtualizarMusicaController instance = new AtualizarMusicaController();
        instance.initData(musica, controladorOrigem);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initialize method, of class AtualizarMusicaController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        URL url = null;
        ResourceBundle rb = null;
        AtualizarMusicaController instance = new AtualizarMusicaController();
        instance.initialize(url, rb);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContentPane method, of class AtualizarMusicaController.
     */
    @Test
    public void testGetContentPane() {
        System.out.println("getContentPane");
        AtualizarMusicaController instance = new AtualizarMusicaController();
        AnchorPane expResult = null;
        AnchorPane result = instance.getContentPane();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
