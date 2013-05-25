/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import tiralabra.BinaryHeap;
import tiralabra.OmaTaulukko;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.BinaryHeap.HeapException;

/**
 *
 * @author root
 */
public class BinaryHeapTest {
    private BinaryHeap keko;
    private BinaryHeap keko1;
    private BinaryHeap keko2;
    
    private int testinKoko;
    
    public BinaryHeapTest() {
        testinKoko = 200;
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        keko = new BinaryHeap(20);
        keko1 = new BinaryHeap(-5);
        keko2 = new BinaryHeap(16);
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test 
    public void keonTaulukonKokoOnKymmenenKunOnLuotuUusiKekoParametrillaKymmenen() {
        assertTrue(keko.getTaulukko().getLength() == 10);
    }
    
    @Test
    public void keonKokoOnAlussaNolla() {
        assertEquals(0, keko.getHeapSize());
        
    }
    @Test
    public void keonTaulukonKokoOnDefaultKunOLuotuUusiKekoParametrillaMiinusViisi() {
        assertTrue(keko1.getTaulukko().getLength() == keko.getDefaultKoko());
    }
    @Test
    public void tyhjaKekoOnAlussaTyhja() {
        assertTrue(keko.isEmpty());
    }
    
    @Test
    public void kekoMihinOnLisattyYksiAlkioEiOleTyhja() {
        keko.heapInsert(1);
        assertFalse(keko.isEmpty());
    }
    
    @Test
    public void kekoonLisaaminenOnnistuu() throws HeapException {
        keko.heapInsert(1);
        assertEquals(1, keko.heapMin());
    }
    
    
    @Test
    public void kekoonVoiLisataViisiAlkiotaJotkaKaikkiLoytyvat() throws HeapException {
        for (int i = 0; i < 5; i++) {
            keko.heapInsert(i);
        }
        for (int i = 0; i < 5; i++) {
            assertEquals(i, keko.heapDelMin());
        }
    }
    
    
    @Test 
    public void kekoonVoiLisataSolmunJaPoistaaSen() throws HeapException {
        keko.heapInsert(1);
        assertEquals(1, keko.heapDelMin());
    }
    
    @Test
    public void kekoonVoiLisataKolmeSolmuaJarjestyksessa() {
        keko.heapInsert(1);
        keko.heapInsert(2);
        keko.heapInsert(3);
        assertEquals(1, keko.getTaulukko().getLuku(0));
        assertEquals(2, keko.getTaulukko().getLuku(1));
        assertEquals(3, keko.getTaulukko().getLuku(2));
    }
    
    @Test
    public void kekoonVoiLisataKaksiSolmuaKaanteisessaJarjestyksessaJaKekoRakenneSailyy() {
        keko.heapInsert(2);
        keko.heapInsert(1);
        assertEquals(1, keko.getTaulukko().getLuku(0));
        assertEquals(2, keko.getTaulukko().getLuku(1));
    }
    
    @Test
    public void kekoonVoiLisataKolmeSolmuaKaanteisessaJarjestyksessaJaKekoRakenneSailyy() {
        keko.heapInsert(3);
        keko.heapInsert(2);
        keko.heapInsert(1);
        assertEquals(1, keko.getTaulukko().getLuku(0));
        assertEquals(2, keko.getTaulukko().getLuku(1));
        assertEquals(3, keko.getTaulukko().getLuku(2));
    }
    
   @Test 
   public void jarjestyksessaLisatytLuvutOvatTaulukossaJarjestyksessa() {
       for (int i = 1; i < 10; i++) {
           keko.heapInsert(i);
       }
       for (int i = 1; i < 10; i++) {
           assertEquals(i, keko.getTaulukko().getLuku(i-1));
       }
   }
   
   @Test 
   public void kaanteisessaJarjestyksessaLisatytLuvutOvatTaulukossaJarjestyksessa() {
       for (int i = 10; i > 0; i--) {
           keko.heapInsert(i);
       }
       for (int i = 1; i < 1; i++) {
           assertEquals(i, keko.getTaulukko().getLuku(i));
       }
   }
   
    
    @Test
    public void solmunVasenLapsi() {
        assertEquals(1, keko.left(0));
        assertEquals(3, keko.left(1));
        assertEquals(5, keko.left(2));
    }
    
    @Test
    public void solmunOikeaLapsi() {
        assertEquals(2, keko.right(0));
        assertEquals(4, keko.right(1));
        assertEquals(6, keko.right(2));
    }
    
    @Test
    public void solmunVanhempi() {
        assertEquals(0, keko.parent(1));
        assertEquals(0, keko.parent(2));
        assertEquals(8, keko.parent(17));
        
    }
    
    @Test 
    public void lisataanSuuriMaaraAlkioitaJarjestyksessa() throws HeapException {
        for (int i = 0; i < testinKoko; i++) {
            keko.heapInsert(i);
        }
        for (int i = 0; i < testinKoko; i++) {
            assertEquals(i, keko.heapDelMin());
        }
    }
    
    @Test
    public void lisataanSuuriMaaraAlkioitaKaanteisessaJarjestyksessa() throws HeapException {
        for (int i = 14; i > 0; i--) {
            keko.heapInsert(i);
        }
        for (int i = 1; i <= 14; i++) {
            assertEquals(i, keko.heapDelMin());
        }
    }
     
}
