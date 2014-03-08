/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.fronteiras.controladores.missa;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.scene.layout.AnchorPane;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import snu.entidades.musica.Musica;
import snu.fronteiras.controladores.FXMLDocumentController;

/**
 *
 * @author Washington Luis
 */
public class MontarMissaOrganizacaoControllerTest {

    public MontarMissaOrganizacaoControllerTest() {
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
     * Test of initData method, of class MontarMissaOrganizacaoController.
     */
    @Test
    public void testInitData() {
        System.out.println("initData");
        Set<Musica> musicasSelecionadas = null;
        MontarMissaSelecaoController controladorOrigem = null;
        FXMLDocumentController controladorPrincipal = null;
        MontarMissaOrganizacaoController instance = new MontarMissaOrganizacaoController();
        instance.initData(musicasSelecionadas, controladorOrigem, controladorPrincipal);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initialize method, of class MontarMissaOrganizacaoController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        URL url = null;
        ResourceBundle rb = null;
        MontarMissaOrganizacaoController instance = new MontarMissaOrganizacaoController();
        instance.initialize(url, rb);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContent method, of class MontarMissaOrganizacaoController.
     */
    @Test
    public void testGetContent() {
        System.out.println("getContent");
        MontarMissaOrganizacaoController instance = new MontarMissaOrganizacaoController();
        AnchorPane expResult = null;
        AnchorPane result = instance.getContent();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
