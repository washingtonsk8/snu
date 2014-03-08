/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.entidades.musica.indexador;

import java.util.List;
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
public class VocabuloTest {

    public VocabuloTest() {
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
     * Test of getId method, of class Vocabulo.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Vocabulo instance = new Vocabulo();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class Vocabulo.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Long id = null;
        Vocabulo instance = new Vocabulo();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getToken method, of class Vocabulo.
     */
    @Test
    public void testGetToken() {
        System.out.println("getToken");
        Vocabulo instance = new Vocabulo();
        String expResult = "";
        String result = instance.getToken();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setToken method, of class Vocabulo.
     */
    @Test
    public void testSetToken() {
        System.out.println("setToken");
        String token = "";
        Vocabulo instance = new Vocabulo();
        instance.setToken(token);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getListaInvertida method, of class Vocabulo.
     */
    @Test
    public void testGetListaInvertida() {
        System.out.println("getListaInvertida");
        Vocabulo instance = new Vocabulo();
        List<ObjetoListaInvertida> expResult = null;
        List<ObjetoListaInvertida> result = instance.getListaInvertida();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setListaInvertida method, of class Vocabulo.
     */
    @Test
    public void testSetListaInvertida() {
        System.out.println("setListaInvertida");
        List<ObjetoListaInvertida> listaInvertida = null;
        Vocabulo instance = new Vocabulo();
        instance.setListaInvertida(listaInvertida);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Vocabulo.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Vocabulo instance = new Vocabulo();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Vocabulo.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        Vocabulo instance = new Vocabulo();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
