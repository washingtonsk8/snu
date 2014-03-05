/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snu.entidades.configuracoes;

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
public class ConfiguracoesSistemaTest {
    
    public ConfiguracoesSistemaTest() {
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
     * Test of getId method, of class ConfiguracoesSistema.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        ConfiguracoesSistema instance = new ConfiguracoesSistema();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class ConfiguracoesSistema.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Long id = null;
        ConfiguracoesSistema instance = new ConfiguracoesSistema();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVersao method, of class ConfiguracoesSistema.
     */
    @Test
    public void testGetVersao() {
        System.out.println("getVersao");
        ConfiguracoesSistema instance = new ConfiguracoesSistema();
        Double expResult = null;
        Double result = instance.getVersao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setVersao method, of class ConfiguracoesSistema.
     */
    @Test
    public void testSetVersao() {
        System.out.println("setVersao");
        Double versao = null;
        ConfiguracoesSistema instance = new ConfiguracoesSistema();
        instance.setVersao(versao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTemplateDescricaoEmail method, of class ConfiguracoesSistema.
     */
    @Test
    public void testGetTemplateDescricaoEmail() {
        System.out.println("getTemplateDescricaoEmail");
        ConfiguracoesSistema instance = new ConfiguracoesSistema();
        String expResult = "";
        String result = instance.getTemplateDescricaoEmail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTemplateDescricaoEmail method, of class ConfiguracoesSistema.
     */
    @Test
    public void testSetTemplateDescricaoEmail() {
        System.out.println("setTemplateDescricaoEmail");
        String templateDescricaoEmail = "";
        ConfiguracoesSistema instance = new ConfiguracoesSistema();
        instance.setTemplateDescricaoEmail(templateDescricaoEmail);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class ConfiguracoesSistema.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        ConfiguracoesSistema instance = new ConfiguracoesSistema();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class ConfiguracoesSistema.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        ConfiguracoesSistema instance = new ConfiguracoesSistema();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class ConfiguracoesSistema.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ConfiguracoesSistema instance = new ConfiguracoesSistema();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
