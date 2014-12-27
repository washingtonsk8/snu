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
public class LeituraAssociadaTest {

    public LeituraAssociadaTest() {
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
     * Test of getId method, of class Tag.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Tag instance = new Tag();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class Tag.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Long id = null;
        Tag instance = new Tag();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDescricao method, of class Tag.
     */
    @Test
    public void testGetDescricao() {
        System.out.println("getDescricao");
        Tag instance = new Tag();
        String expResult = "";
        String result = instance.getDescricao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDescricao method, of class Tag.
     */
    @Test
    public void testSetDescricao() {
        System.out.println("setDescricao");
        String descricao = "";
        Tag instance = new Tag();
        instance.setDescricao(descricao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMusica method, of class Tag.
     */
    @Test
    public void testGetMusica() {
        System.out.println("getMusica");
        Tag instance = new Tag();
        Musica expResult = null;
        Musica result = instance.getMusica();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMusica method, of class Tag.
     */
    @Test
    public void testSetMusica() {
        System.out.println("setMusica");
        Musica musica = null;
        Tag instance = new Tag();
        instance.setMusica(musica);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Tag.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Tag instance = new Tag();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Tag.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        Tag instance = new Tag();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Tag.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Tag instance = new Tag();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
