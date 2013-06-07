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
    public void solmunVoiLisataJaPoistaa() throws DaryHeapException {
    keko.insert(1);
    assertEquals(1, keko.deleteMin());
    }
    
    @Test
    public void kekoonVoiLisataKaksiSolmuaJaPoistaaNe() throws DaryHeapException {
        keko.insert(1);
        keko.insert(2);
        assertEquals(2, keko.getHeapSize());
        assertEquals(1, keko.heapMin());
        assertEquals(1, keko.deleteMin());
        assertEquals(2, keko.deleteMin());
        
    }

    @Test
    public void kekoonVoiLisataMontaSolmua() {
        for (int i = 0; i < 10; i++) {
            keko.insert(i);
        }
        assertEquals(0, keko.parent(1));
        assertEquals(0, keko.parent(2));
        assertEquals(0, keko.parent(3));
        assertEquals(1, keko.parent(4));
        assertEquals(1, keko.parent(5));
        assertEquals(1, keko.parent(6));
        assertEquals(2, keko.parent(7));
        assertEquals(2, keko.parent(8));
        assertEquals(2, keko.parent(9));
        



    }

    @Test
    public void parentToimiiNiinKuinPitaa() {
        System.out.println("parent(0): " + keko.parent(0));
        System.out.println("parent(1): " + keko.parent(1));
        System.out.println("parent(2): " + keko.parent(2));
        System.out.println("parent(3): " + keko.parent(3));
        System.out.println("parent(4): " + keko.parent(4));

    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
