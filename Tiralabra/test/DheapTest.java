/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.Dheap;
import tiralabra.Dheap.DaryHeapException;

/**
 *
 * @author root
 */
public class DheapTest {
    private Dheap keko;
        
    public DheapTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        keko = new Dheap(10, 3);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void kekoOnAlussaTyhja() {
        assertTrue(keko.isEmpty());
    }
    @Test
    public void parametriDonKutenAnnettu() {
        assertEquals(3, keko.getD());
    }
    
    @Test 
    public void kekoonVoiLisataLuvunJaSeOnPienin() throws DaryHeapException {
        keko.insert(1);
        assertEquals(1, keko.heapMin());
    }
    
    @Test
    public void kekoonVoiLisataSolmunJaSeEiOleTyhja() {
        keko.insert(1);
        assertEquals(1, keko.getHeapSize());
        assertFalse(keko.isEmpty());
    }
    
    @Test
    public void kekoonVoiLisataKaksiSolmua() throws DaryHeapException {
        keko.insert(1);
        keko.insert(2);
        assertEquals(2, keko.getHeapSize());
        assertEquals(1, keko.heapMin());
        
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
