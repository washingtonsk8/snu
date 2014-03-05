/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snu.entidades.missa;

import java.util.Date;
import java.util.Set;
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
public class MissaTest {
    
    public MissaTest() {
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
     * Test of getId method, of class Missa.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Missa instance = new Missa();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class Missa.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Long id = null;
        Missa instance = new Missa();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNome method, of class Missa.
     */
    @Test
    public void testGetNome() {
        System.out.println("getNome");
        Missa instance = new Missa();
        String expResult = "";
        String result = instance.getNome();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNome method, of class Missa.
     */
    @Test
    public void testSetNome() {
        System.out.println("setNome");
        String nome = "";
        Missa instance = new Missa();
        instance.setNome(nome);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDataAcontecimento method, of class Missa.
     */
    @Test
    public void testGetDataAcontecimento() {
        System.out.println("getDataAcontecimento");
        Missa instance = new Missa();
        Date expResult = null;
        Date result = instance.getDataAcontecimento();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDataAcontecimento method, of class Missa.
     */
    @Test
    public void testSetDataAcontecimento() {
        System.out.println("setDataAcontecimento");
        Date dataAcontecimento = null;
        Missa instance = new Missa();
        instance.setDataAcontecimento(dataAcontecimento);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMusicasUtilizadas method, of class Missa.
     */
    @Test
    public void testGetMusicasUtilizadas() {
        System.out.println("getMusicasUtilizadas");
        Missa instance = new Missa();
        Set<Musica> expResult = null;
        Set<Musica> result = instance.getMusicasUtilizadas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMusicasUtilizadas method, of class Missa.
     */
    @Test
    public void testSetMusicasUtilizadas() {
        System.out.println("setMusicasUtilizadas");
        Set<Musica> musicasUtilizadas = null;
        Missa instance = new Missa();
        instance.setMusicasUtilizadas(musicasUtilizadas);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDescricaoEmail method, of class Missa.
     */
    @Test
    public void testGetDescricaoEmail() {
        System.out.println("getDescricaoEmail");
        Missa instance = new Missa();
        String expResult = "";
        String result = instance.getDescricaoEmail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDescricaoEmail method, of class Missa.
     */
    @Test
    public void testSetDescricaoEmail() {
        System.out.println("setDescricaoEmail");
        String descricaoEmail = "";
        Missa instance = new Missa();
        instance.setDescricaoEmail(descricaoEmail);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Missa.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Missa instance = new Missa();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Missa.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        Missa instance = new Missa();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Missa.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Missa instance = new Missa();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
