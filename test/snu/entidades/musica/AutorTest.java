/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snu.entidades.musica;

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
public class AutorTest {
    
    public AutorTest() {
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
     * Test of getId method, of class Autor.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Autor instance = new Autor();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class Autor.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Long id = null;
        Autor instance = new Autor();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNome method, of class Autor.
     */
    @Test
    public void testGetNome() {
        System.out.println("getNome");
        Autor instance = new Autor();
        String expResult = "";
        String result = instance.getNome();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNome method, of class Autor.
     */
    @Test
    public void testSetNome() {
        System.out.println("setNome");
        String nome = "";
        Autor instance = new Autor();
        instance.setNome(nome);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMusicasDeAutoria method, of class Autor.
     */
    @Test
    public void testGetMusicasDeAutoria() {
        System.out.println("getMusicasDeAutoria");
        Autor instance = new Autor();
        List<Musica> expResult = null;
        List<Musica> result = instance.getMusicasDeAutoria();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMusicasDeAutoria method, of class Autor.
     */
    @Test
    public void testSetMusicasDeAutoria() {
        System.out.println("setMusicasDeAutoria");
        List<Musica> musicasDeAutoria = null;
        Autor instance = new Autor();
        instance.setMusicasDeAutoria(musicasDeAutoria);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Autor.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Autor instance = new Autor();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Autor.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        Autor instance = new Autor();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Autor.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Autor instance = new Autor();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
