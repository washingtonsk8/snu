/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.entidades.musica;

import java.util.List;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import snu.entidades.missa.Missa;

/**
 *
 * @author Washington Luis
 */
public class MusicaTest {

    public MusicaTest() {
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
     * Test of getId method, of class Musica.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Musica instance = new Musica();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class Musica.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Long id = null;
        Musica instance = new Musica();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAutor method, of class Musica.
     */
    @Test
    public void testGetAutor() {
        System.out.println("getAutor");
        Musica instance = new Musica();
        Autor expResult = null;
        Autor result = instance.getAutor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAutor method, of class Musica.
     */
    @Test
    public void testSetAutor() {
        System.out.println("setAutor");
        Autor autor = null;
        Musica instance = new Musica();
        instance.setAutor(autor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNome method, of class Musica.
     */
    @Test
    public void testGetNome() {
        System.out.println("getNome");
        Musica instance = new Musica();
        String expResult = "";
        String result = instance.getNome();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNome method, of class Musica.
     */
    @Test
    public void testSetNome() {
        System.out.println("setNome");
        String nome = "";
        Musica instance = new Musica();
        instance.setNome(nome);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLeiturasAssociadas method, of class Musica.
     */
    @Test
    public void testGetLeiturasAssociadas() {
        System.out.println("getLeiturasAssociadas");
        Musica instance = new Musica();
        List<LeituraAssociada> expResult = null;
        List<LeituraAssociada> result = instance.getLeiturasAssociadas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLeiturasAssociadas method, of class Musica.
     */
    @Test
    public void testSetLeiturasAssociadas() {
        System.out.println("setLeiturasAssociadas");
        List<LeituraAssociada> leiturasAssociadas = null;
        Musica instance = new Musica();
        instance.setLeiturasAssociadas(leiturasAssociadas);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTom method, of class Musica.
     */
    @Test
    public void testGetTom() {
        System.out.println("getTom");
        Musica instance = new Musica();
        Tom expResult = null;
        Tom result = instance.getTom();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTom method, of class Musica.
     */
    @Test
    public void testSetTom() {
        System.out.println("setTom");
        Tom tom = null;
        Musica instance = new Musica();
        instance.setTom(tom);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAfinacao method, of class Musica.
     */
    @Test
    public void testGetAfinacao() {
        System.out.println("getAfinacao");
        Musica instance = new Musica();
        Afinacao expResult = null;
        Afinacao result = instance.getAfinacao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAfinacao method, of class Musica.
     */
    @Test
    public void testSetAfinacao() {
        System.out.println("setAfinacao");
        Afinacao afinacao = null;
        Musica instance = new Musica();
        instance.setAfinacao(afinacao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLinkVideo method, of class Musica.
     */
    @Test
    public void testGetLinkVideo() {
        System.out.println("getLinkVideo");
        Musica instance = new Musica();
        String expResult = "";
        String result = instance.getLinkVideo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLinkVideo method, of class Musica.
     */
    @Test
    public void testSetLinkVideo() {
        System.out.println("setLinkVideo");
        String linkVideo = "";
        Musica instance = new Musica();
        instance.setLinkVideo(linkVideo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTipos method, of class Musica.
     */
    @Test
    public void testGetTipos() {
        System.out.println("getTipos");
        Musica instance = new Musica();
        List<TipoMusica> expResult = null;
        List<TipoMusica> result = instance.getTipos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTipos method, of class Musica.
     */
    @Test
    public void testSetTipos() {
        System.out.println("setTipos");
        List<TipoMusica> tipos = null;
        Musica instance = new Musica();
        instance.setTipos(tipos);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of adicionarTipo method, of class Musica.
     */
    @Test
    public void testAdicionarTipo() {
        System.out.println("adicionarTipo");
        TipoMusica tipo = null;
        Musica instance = new Musica();
        instance.adicionarTipo(tipo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removerTipo method, of class Musica.
     */
    @Test
    public void testRemoverTipo() {
        System.out.println("removerTipo");
        TipoMusica tipo = null;
        Musica instance = new Musica();
        instance.removerTipo(tipo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAssociacoes method, of class Musica.
     */
    @Test
    public void testGetAssociacoes() {
        System.out.println("getAssociacoes");
        Musica instance = new Musica();
        List<AssociacaoIntegranteMusica> expResult = null;
        List<AssociacaoIntegranteMusica> result = instance.getAssociacoes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAssociacoes method, of class Musica.
     */
    @Test
    public void testSetAssociacoes() {
        System.out.println("setAssociacoes");
        List<AssociacaoIntegranteMusica> associacoes = null;
        Musica instance = new Musica();
        instance.setAssociacoes(associacoes);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDocumentoMusica method, of class Musica.
     */
    @Test
    public void testGetDocumentoMusica() {
        System.out.println("getDocumentoMusica");
        Musica instance = new Musica();
        DocumentoMusica expResult = null;
        DocumentoMusica result = instance.getDocumentoMusica();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDocumentoMusica method, of class Musica.
     */
    @Test
    public void testSetDocumentoMusica() {
        System.out.println("setDocumentoMusica");
        DocumentoMusica documentoMusica = null;
        Musica instance = new Musica();
        instance.setDocumentoMusica(documentoMusica);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMissasPresente method, of class Musica.
     */
    @Test
    public void testGetMissasPresente() {
        System.out.println("getMissasPresente");
        Musica instance = new Musica();
        Set<Missa> expResult = null;
        Set<Missa> result = instance.getMissasPresente();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMissasPresente method, of class Musica.
     */
    @Test
    public void testSetMissasPresente() {
        System.out.println("setMissasPresente");
        Set<Missa> missasPresente = null;
        Musica instance = new Musica();
        instance.setMissasPresente(missasPresente);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTitulo method, of class Musica.
     */
    @Test
    public void testGetTitulo() {
        System.out.println("getTitulo");
        Musica instance = new Musica();
        String expResult = "";
        String result = instance.getTitulo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Musica.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Musica instance = new Musica();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Musica.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        Musica instance = new Musica();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Musica.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Musica instance = new Musica();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
