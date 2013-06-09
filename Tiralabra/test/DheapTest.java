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


/**
 *
 * @author root
 */
public class DheapTest {

    private Dheap keko;
    private Dheap keko1;

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
        keko = new Dheap(3);
        keko1 = new Dheap(4);
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
    public void kekoonVoiLisataLuvunJaSeOnPienin() throws Exception {
        keko.insert(1);
        assertEquals(1, keko.min());
    }

    @Test
    public void kekoonVoiLisataSolmunJaSeEiOleTyhja() {
        keko.insert(1);
        assertEquals(1, keko.getHeapSize());
        assertFalse(keko.isEmpty());
    }

    @Test
    public void solmunVoiLisataJaPoistaa() throws Exception {
        keko.insert(1);
        assertEquals(1, keko.deleteMin());
    }

    @Test
    public void kekoonVoiLisataKaksiSolmuaJaPoistaaNe() throws Exception {
        keko.insert(0);
        keko.insert(1);
        assertEquals(2, keko.getHeapSize());
        assertEquals(0, keko.min());
        assertEquals(0, keko.deleteMin());
        assertEquals(1, keko.getHeapSize());
        assertEquals(1, keko.deleteMin());

    }

    @Test
    public void kekoonVoiLisataKolmeSolmuaJaPoistaaNe() throws Exception {
        keko.insert(0);
        keko.insert(1);
        keko.insert(2);
        keko.insert(3);
        keko.insert(4);
        keko.insert(5);

        assertEquals(0, keko.deleteMin());
        //System.out.println("min: " + keko.min());
        assertEquals(1, keko.deleteMin());
        //System.out.println("min : " + keko.min());
        assertEquals(2, keko.deleteMin());
        assertEquals(3, keko.deleteMin());
        assertEquals(4, keko.deleteMin());
    }

    @Test
    public void kekoonVoiLisataMontaSolmuaJaPoistaaNe() throws Exception {
        for (int i = 0; i < 1000; i++) {
            keko.insert(i);
        }
        assertEquals(1000, keko.getHeapSize());
        for (int i = 0; i < 1000; i++) {
            assertEquals(i, keko.deleteMin());
            //System.out.print("poistetaan: " + keko.deleteMin());
            //System.out.println();
            //for (int j = 0; j < keko.getHeapSize() - 1; j++) {
            //System.out.print(keko.getTaulukko().getLuku(j) + " ");
            //assertEquals(i, keko.deleteMin());
            //}
            //System.out.println();
        }

    }

    @Test
    public void neljaKekoonVoiLisataMontaSolmuaJaPoistaaNe() throws Exception {
        for (int i = 0; i < 10000; i++) {
            keko1.insert(i);
        }
        assertEquals(10000, keko1.getHeapSize());
        for (int i = 0; i < 10000; i++) {
            assertEquals(i, keko1.deleteMin());
        }
    }
    
    @Test 
    public void LisataanMontaKaanteisessaJarjestyksessa() throws Exception {
        for (int i = 10000; i >= 0; i--) {
            keko.insert(i);
        }
        
        for (int i = 0; i < 10000; i++) {
            assertEquals(i, keko.deleteMin());
        }
    }

    @Test
    public void parentToimiiNiinKuinPitaa() {
        System.out.println("parent(0): " + keko.parent(0));
        System.out.println("parent(1): " + keko.parent(1));
        System.out.println("parent(2): " + keko.parent(2));
        System.out.println("parent(3): " + keko.parent(3));
        System.out.println("parent(4): " + keko.parent(4));

    }
    
    @Test
    public void testaaDecreaseKeyta() throws Exception {
        for (int i = 5; i < 20; i++) {
            keko.insert(i);
        }
        keko.decreaseKey(4, 0);
        assertEquals(0, keko.min());
        
        keko.deleteMin();
        
        keko.decreaseKey(10, 1);
        assertEquals(1, keko.min());
        
        keko.deleteMin();
        
        keko.decreaseKey(13, 2);
        assertEquals(2, keko.min());
    }
    
    @Test
    public void testaaIncreseKeyta() throws Exception {
        for (int i = 0; i < 15; i++) {
            keko.insert(i);
        }
        
        keko.increaseKey(0, 5);
        assertEquals(1, keko.min());
        keko.increaseKey(0, 13);
        assertEquals(2, keko.min());
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
