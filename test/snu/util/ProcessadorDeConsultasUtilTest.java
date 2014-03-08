/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.util;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import snu.entidades.musica.indexador.ObjetoListaInvertida;
import snu.entidades.musica.indexador.Vocabulo;

/**
 *
 * @author Washington Luis
 */
public class ProcessadorDeConsultasUtilTest {

    public ProcessadorDeConsultasUtilTest() {
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
     * Test of calcularPeso method, of class ProcessadorDeConsultasUtil.
     */
    @Test
    public void testCalcularPeso_ObjetoListaInvertida_Integer() {
        System.out.println("calcularPeso");
        ObjetoListaInvertida objetoListaInvertida = null;
        Integer quantidadeDocumentos = null;
        Double expResult = null;
        Double result = ProcessadorDeConsultasUtil.calcularPeso(objetoListaInvertida, quantidadeDocumentos);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calcularPeso method, of class ProcessadorDeConsultasUtil.
     */
    @Test
    public void testCalcularPeso_3args() {
        System.out.println("calcularPeso");
        Vocabulo vocabulo = null;
        List<String> tokensConsulta = null;
        Integer quantidadeDocumentos = null;
        Double expResult = null;
        Double result = ProcessadorDeConsultasUtil.calcularPeso(vocabulo, tokensConsulta, quantidadeDocumentos);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
