/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snu.entidades.musica.indexador;

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
public class ObjetoListaInvertidaTest {
    
    public ObjetoListaInvertidaTest() {
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
     * Test of getId method, of class ObjetoListaInvertida.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        ObjetoListaInvertida instance = new ObjetoListaInvertida();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class ObjetoListaInvertida.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Long id = null;
        ObjetoListaInvertida instance = new ObjetoListaInvertida();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVocabulo method, of class ObjetoListaInvertida.
     */
    @Test
    public void testGetVocabulo() {
        System.out.println("getVocabulo");
        ObjetoListaInvertida instance = new ObjetoListaInvertida();
        Vocabulo expResult = null;
        Vocabulo result = instance.getVocabulo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setVocabulo method, of class ObjetoListaInvertida.
     */
    @Test
    public void testSetVocabulo() {
        System.out.println("setVocabulo");
        Vocabulo vocabulo = null;
        ObjetoListaInvertida instance = new ObjetoListaInvertida();
        instance.setVocabulo(vocabulo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIdMusica method, of class ObjetoListaInvertida.
     */
    @Test
    public void testGetIdMusica() {
        System.out.println("getIdMusica");
        ObjetoListaInvertida instance = new ObjetoListaInvertida();
        Long expResult = null;
        Long result = instance.getIdMusica();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIdMusica method, of class ObjetoListaInvertida.
     */
    @Test
    public void testSetIdMusica() {
        System.out.println("setIdMusica");
        Long idMusica = null;
        ObjetoListaInvertida instance = new ObjetoListaInvertida();
        instance.setIdMusica(idMusica);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIdDocumentoMusica method, of class ObjetoListaInvertida.
     */
    @Test
    public void testGetIdDocumentoMusica() {
        System.out.println("getIdDocumentoMusica");
        ObjetoListaInvertida instance = new ObjetoListaInvertida();
        Long expResult = null;
        Long result = instance.getIdDocumentoMusica();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIdDocumentoMusica method, of class ObjetoListaInvertida.
     */
    @Test
    public void testSetIdDocumentoMusica() {
        System.out.println("setIdDocumentoMusica");
        Long idDocumentoMusica = null;
        ObjetoListaInvertida instance = new ObjetoListaInvertida();
        instance.setIdDocumentoMusica(idDocumentoMusica);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFrequenciaToken method, of class ObjetoListaInvertida.
     */
    @Test
    public void testGetFrequenciaToken() {
        System.out.println("getFrequenciaToken");
        ObjetoListaInvertida instance = new ObjetoListaInvertida();
        Integer expResult = null;
        Integer result = instance.getFrequenciaToken();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFrequenciaToken method, of class ObjetoListaInvertida.
     */
    @Test
    public void testSetFrequenciaToken() {
        System.out.println("setFrequenciaToken");
        Integer frequenciaToken = null;
        ObjetoListaInvertida instance = new ObjetoListaInvertida();
        instance.setFrequenciaToken(frequenciaToken);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class ObjetoListaInvertida.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        ObjetoListaInvertida instance = new ObjetoListaInvertida();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class ObjetoListaInvertida.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        ObjetoListaInvertida instance = new ObjetoListaInvertida();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class ObjetoListaInvertida.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ObjetoListaInvertida instance = new ObjetoListaInvertida();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}