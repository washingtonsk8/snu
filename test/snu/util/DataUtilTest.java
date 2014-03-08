/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snu.util;

import eu.schudt.javafx.controls.calendar.DatePicker;
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
public class DataUtilTest {
    
    public DataUtilTest() {
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
     * Test of getDatePicker method, of class DataUtil.
     */
    @Test
    public void testGetDatePicker() {
        System.out.println("getDatePicker");
        DatePicker expResult = null;
        DatePicker result = DataUtil.getDatePicker();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdade method, of class DataUtil.
     */
    @Test
    public void testGetIdade() {
        System.out.println("getIdade");
        Date dataPassada = new Date();
        Integer expResult = 0;
        Integer result = DataUtil.getIdade(dataPassada);
        assertEquals(expResult, result);
    }

    /**
     * Test of formatarData method, of class DataUtil.
     */
    @Test
    public void testFormatarData() {
        System.out.println("formatarData");
        Date data = new Date();
        String expResult = "04/03/2014";
        String result = DataUtil.formatarData(data);
        assertEquals(expResult, result);
    }
    
}