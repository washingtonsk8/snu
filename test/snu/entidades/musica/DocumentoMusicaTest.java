/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.entidades.musica;

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
public class DocumentoMusicaTest {

    public DocumentoMusicaTest() {
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
     * Test of getId method, of class DocumentoMusica.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        DocumentoMusica instance = new DocumentoMusica();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class DocumentoMusica.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Long id = null;
        DocumentoMusica instance = new DocumentoMusica();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIntroducao method, of class DocumentoMusica.
     */
    @Test
    public void testGetIntroducao() {
        System.out.println("getIntroducao");
        DocumentoMusica instance = new DocumentoMusica();
        String expResult = "";
        String result = instance.getIntroducao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIntroducao method, of class DocumentoMusica.
     */
    @Test
    public void testSetIntroducao() {
        System.out.println("setIntroducao");
        String introducao = "";
        DocumentoMusica instance = new DocumentoMusica();
        instance.setIntroducao(introducao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConteudo method, of class DocumentoMusica.
     */
    @Test
    public void testGetConteudo() {
        System.out.println("getConteudo");
        DocumentoMusica instance = new DocumentoMusica();
        String expResult = "";
        String result = instance.getConteudo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setConteudo method, of class DocumentoMusica.
     */
    @Test
    public void testSetConteudo() {
        System.out.println("setConteudo");
        String conteudo = "";
        DocumentoMusica instance = new DocumentoMusica();
        instance.setConteudo(conteudo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuantidadeTokens method, of class DocumentoMusica.
     */
    @Test
    public void testGetQuantidadeTokens() {
        System.out.println("getQuantidadeTokens");
        DocumentoMusica instance = new DocumentoMusica();
        Integer expResult = null;
        Integer result = instance.getQuantidadeTokens();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setQuantidadeTokens method, of class DocumentoMusica.
     */
    @Test
    public void testSetQuantidadeTokens() {
        System.out.println("setQuantidadeTokens");
        Integer quantidadeTokens = null;
        DocumentoMusica instance = new DocumentoMusica();
        instance.setQuantidadeTokens(quantidadeTokens);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFrequenciaMaximaToken method, of class DocumentoMusica.
     */
    @Test
    public void testGetFrequenciaMaximaToken() {
        System.out.println("getFrequenciaMaximaToken");
        DocumentoMusica instance = new DocumentoMusica();
        Integer expResult = null;
        Integer result = instance.getFrequenciaMaximaToken();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFrequenciaMaximaToken method, of class DocumentoMusica.
     */
    @Test
    public void testSetFrequenciaMaximaToken() {
        System.out.println("setFrequenciaMaximaToken");
        Integer frequenciaMaximaToken = null;
        DocumentoMusica instance = new DocumentoMusica();
        instance.setFrequenciaMaximaToken(frequenciaMaximaToken);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class DocumentoMusica.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        DocumentoMusica instance = new DocumentoMusica();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class DocumentoMusica.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        DocumentoMusica instance = new DocumentoMusica();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class DocumentoMusica.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        DocumentoMusica instance = new DocumentoMusica();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
