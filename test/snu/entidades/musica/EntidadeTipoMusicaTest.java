/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.entidades.musica;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Washington Luis
 */
public class EntidadeTipoMusicaTest {

    public EntidadeTipoMusicaTest() {
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
     * Test of getId method, of class EntidadeTipoMusica.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        EntidadeTipoMusica instance = new EntidadeTipoMusica();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class EntidadeTipoMusica.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Long id = null;
        EntidadeTipoMusica instance = new EntidadeTipoMusica();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValor method, of class EntidadeTipoMusica.
     */
    @Test
    public void testGetValor() {
        System.out.println("getValor");
        EntidadeTipoMusica instance = new EntidadeTipoMusica();
        TipoMusica expResult = null;
        TipoMusica result = instance.getValor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setValor method, of class EntidadeTipoMusica.
     */
    @Test
    public void testSetValor() {
        System.out.println("setValor");
        TipoMusica valor = null;
        EntidadeTipoMusica instance = new EntidadeTipoMusica();
        instance.setValor(valor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMusica method, of class EntidadeTipoMusica.
     */
    @Test
    public void testGetMusica() {
        System.out.println("getMusica");
        EntidadeTipoMusica instance = new EntidadeTipoMusica();
        Musica expResult = null;
        Musica result = instance.getMusica();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMusica method, of class EntidadeTipoMusica.
     */
    @Test
    public void testSetMusica() {
        System.out.println("setMusica");
        Musica musica = null;
        EntidadeTipoMusica instance = new EntidadeTipoMusica();
        instance.setMusica(musica);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class EntidadeTipoMusica.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        EntidadeTipoMusica instance = new EntidadeTipoMusica();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class EntidadeTipoMusica.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        EntidadeTipoMusica instance = new EntidadeTipoMusica();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class EntidadeTipoMusica.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        EntidadeTipoMusica instance = new EntidadeTipoMusica();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
