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
     * Test of getId method, of class LeituraAssociada.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        LeituraAssociada instance = new LeituraAssociada();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class LeituraAssociada.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Long id = null;
        LeituraAssociada instance = new LeituraAssociada();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDescricao method, of class LeituraAssociada.
     */
    @Test
    public void testGetDescricao() {
        System.out.println("getDescricao");
        LeituraAssociada instance = new LeituraAssociada();
        String expResult = "";
        String result = instance.getDescricao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDescricao method, of class LeituraAssociada.
     */
    @Test
    public void testSetDescricao() {
        System.out.println("setDescricao");
        String descricao = "";
        LeituraAssociada instance = new LeituraAssociada();
        instance.setDescricao(descricao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMusica method, of class LeituraAssociada.
     */
    @Test
    public void testGetMusica() {
        System.out.println("getMusica");
        LeituraAssociada instance = new LeituraAssociada();
        Musica expResult = null;
        Musica result = instance.getMusica();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMusica method, of class LeituraAssociada.
     */
    @Test
    public void testSetMusica() {
        System.out.println("setMusica");
        Musica musica = null;
        LeituraAssociada instance = new LeituraAssociada();
        instance.setMusica(musica);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class LeituraAssociada.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        LeituraAssociada instance = new LeituraAssociada();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class LeituraAssociada.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        LeituraAssociada instance = new LeituraAssociada();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class LeituraAssociada.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        LeituraAssociada instance = new LeituraAssociada();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}