/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snu.entidades.integrante;

import java.util.Date;
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
public class IntegranteTest {
    
    public IntegranteTest() {
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
     * Test of getId method, of class Integrante.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Integrante instance = new Integrante();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class Integrante.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Long id = null;
        Integrante instance = new Integrante();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNome method, of class Integrante.
     */
    @Test
    public void testGetNome() {
        System.out.println("getNome");
        Integrante instance = new Integrante();
        String expResult = "";
        String result = instance.getNome();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNome method, of class Integrante.
     */
    @Test
    public void testSetNome() {
        System.out.println("setNome");
        String nome = "";
        Integrante instance = new Integrante();
        instance.setNome(nome);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSexo method, of class Integrante.
     */
    @Test
    public void testGetSexo() {
        System.out.println("getSexo");
        Integrante instance = new Integrante();
        Sexo expResult = null;
        Sexo result = instance.getSexo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSexo method, of class Integrante.
     */
    @Test
    public void testSetSexo() {
        System.out.println("setSexo");
        Sexo sexo = null;
        Integrante instance = new Integrante();
        instance.setSexo(sexo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDataNascimento method, of class Integrante.
     */
    @Test
    public void testGetDataNascimento() {
        System.out.println("getDataNascimento");
        Integrante instance = new Integrante();
        Date expResult = null;
        Date result = instance.getDataNascimento();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDataNascimento method, of class Integrante.
     */
    @Test
    public void testSetDataNascimento() {
        System.out.println("setDataNascimento");
        Date dataNascimento = null;
        Integrante instance = new Integrante();
        instance.setDataNascimento(dataNascimento);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTelefoneResidencial method, of class Integrante.
     */
    @Test
    public void testGetTelefoneResidencial() {
        System.out.println("getTelefoneResidencial");
        Integrante instance = new Integrante();
        String expResult = "";
        String result = instance.getTelefoneResidencial();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTelefoneResidencial method, of class Integrante.
     */
    @Test
    public void testSetTelefoneResidencial() {
        System.out.println("setTelefoneResidencial");
        String telefoneResidencial = "";
        Integrante instance = new Integrante();
        instance.setTelefoneResidencial(telefoneResidencial);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTelefoneCelular method, of class Integrante.
     */
    @Test
    public void testGetTelefoneCelular() {
        System.out.println("getTelefoneCelular");
        Integrante instance = new Integrante();
        String expResult = "";
        String result = instance.getTelefoneCelular();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTelefoneCelular method, of class Integrante.
     */
    @Test
    public void testSetTelefoneCelular() {
        System.out.println("setTelefoneCelular");
        String telefoneCelular = "";
        Integrante instance = new Integrante();
        instance.setTelefoneCelular(telefoneCelular);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTelefoneComercial method, of class Integrante.
     */
    @Test
    public void testGetTelefoneComercial() {
        System.out.println("getTelefoneComercial");
        Integrante instance = new Integrante();
        String expResult = "";
        String result = instance.getTelefoneComercial();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTelefoneComercial method, of class Integrante.
     */
    @Test
    public void testSetTelefoneComercial() {
        System.out.println("setTelefoneComercial");
        String telefoneComercial = "";
        Integrante instance = new Integrante();
        instance.setTelefoneComercial(telefoneComercial);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEndereco method, of class Integrante.
     */
    @Test
    public void testGetEndereco() {
        System.out.println("getEndereco");
        Integrante instance = new Integrante();
        String expResult = "";
        String result = instance.getEndereco();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEndereco method, of class Integrante.
     */
    @Test
    public void testSetEndereco() {
        System.out.println("setEndereco");
        String endereco = "";
        Integrante instance = new Integrante();
        instance.setEndereco(endereco);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFuncaoPrimaria method, of class Integrante.
     */
    @Test
    public void testGetFuncaoPrimaria() {
        System.out.println("getFuncaoPrimaria");
        Integrante instance = new Integrante();
        FuncaoIntegrante expResult = null;
        FuncaoIntegrante result = instance.getFuncaoPrimaria();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFuncaoPrimaria method, of class Integrante.
     */
    @Test
    public void testSetFuncaoPrimaria() {
        System.out.println("setFuncaoPrimaria");
        FuncaoIntegrante funcaoPrimaria = null;
        Integrante instance = new Integrante();
        instance.setFuncaoPrimaria(funcaoPrimaria);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFuncaoSecundaria method, of class Integrante.
     */
    @Test
    public void testGetFuncaoSecundaria() {
        System.out.println("getFuncaoSecundaria");
        Integrante instance = new Integrante();
        FuncaoIntegrante expResult = null;
        FuncaoIntegrante result = instance.getFuncaoSecundaria();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFuncaoSecundaria method, of class Integrante.
     */
    @Test
    public void testSetFuncaoSecundaria() {
        System.out.println("setFuncaoSecundaria");
        FuncaoIntegrante funcaoSecundaria = null;
        Integrante instance = new Integrante();
        instance.setFuncaoSecundaria(funcaoSecundaria);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmail method, of class Integrante.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        Integrante instance = new Integrante();
        String expResult = "";
        String result = instance.getEmail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEmail method, of class Integrante.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "";
        Integrante instance = new Integrante();
        instance.setEmail(email);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDataEntrada method, of class Integrante.
     */
    @Test
    public void testGetDataEntrada() {
        System.out.println("getDataEntrada");
        Integrante instance = new Integrante();
        Date expResult = null;
        Date result = instance.getDataEntrada();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDataEntrada method, of class Integrante.
     */
    @Test
    public void testSetDataEntrada() {
        System.out.println("setDataEntrada");
        Date dataEntrada = null;
        Integrante instance = new Integrante();
        instance.setDataEntrada(dataEntrada);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPrimeiroNome method, of class Integrante.
     */
    @Test
    public void testGetPrimeiroNome() {
        System.out.println("getPrimeiroNome");
        Integrante instance = new Integrante();
        String expResult = "";
        String result = instance.getPrimeiroNome();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Integrante.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Integrante instance = new Integrante();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Integrante.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        Integrante instance = new Integrante();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Integrante.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Integrante instance = new Integrante();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}