/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.PriorityQueue;
import java.util.Random;
import tiralabra.BinaryHeap;
import tiralabra.OmaTaulukko;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.Bnode;

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
        testinKoko = 100;
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        keko = new BinaryHeap(10);
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
        keko.insert(1);
        assertFalse(keko.isEmpty());
    }

    @Test
    public void kekoonLisaaminenOnnistuu() throws Exception {
        keko.insert(1);
        assertEquals(1, keko.min());
    }

    @Test
    public void kekoonVoiLisataViisiAlkiotaJotkaKaikkiLoytyvat() throws Exception {
        for (int i = 0; i < 5; i++) {
            keko.insert(i);
        }
        for (int i = 0; i < 5; i++) {
            assertEquals(i, keko.deleteMin());
        }
    }

    @Test
    public void kekoonVoiLisataSolmunJaPoistaaSen() throws Exception {
        keko.insert(1);
        assertEquals(1, keko.deleteMin());
    }

    @Test
    public void kekoonVoiLisataKolmeSolmuaJarjestyksessa() {
        keko.insert(1);
        keko.insert(2);
        keko.insert(3);
        assertEquals(1, (int) keko.getTaulukko().get(0));
        assertEquals(2, (int) keko.getTaulukko().get(1));
        assertEquals(3, (int) keko.getTaulukko().get(2));
    }

    @Test
    public void kekoonVoiLisataKaksiSolmuaKaanteisessaJarjestyksessaJaKekoRakenneSailyy() {
        keko.insert(2);
        keko.insert(1);
        assertEquals(1, keko.getTaulukko().get(0));
        assertEquals(2, keko.getTaulukko().get(1));
    }

    @Test
    public void kekoonVoiLisataKolmeSolmuaKaanteisessaJarjestyksessaJaKekoRakenneSailyy() {
        keko.insert(3);
        keko.insert(2);
        keko.insert(1);
        assertEquals(1, keko.getTaulukko().get(0));
        assertEquals(3, keko.getTaulukko().get(1));
        assertEquals(2, keko.getTaulukko().get(2));
    }

    @Test
    public void jarjestyksessaLisatytLuvutOvatTaulukossaJarjestyksessa() {
        for (int i = 1; i < 10; i++) {
            keko.insert(i);
        }
        for (int i = 1; i < 10; i++) {
            assertEquals(i, keko.getTaulukko().get(i - 1));
        }
    }

    @Test
    public void kaanteisessaJarjestyksessaLisatytLuvutOvatTaulukossaJarjestyksessa() {
        for (int i = 10; i > 0; i--) {
            keko.insert(i);
        }
        for (int i = 1; i < 1; i++) {
            assertEquals(i, keko.getTaulukko().get(i));
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
    public void lisataanSuuriMaaraAlkioitaJarjestyksessa() throws Exception {
        for (int i = 0; i < testinKoko; i++) {
            keko.insert(i);
        }
        for (int i = 0; i < testinKoko; i++) {
            assertEquals(i, keko.deleteMin());
        }
    }

    @Test
    public void lisataanSuuriMaaraAlkioitaKaanteisessaJarjestyksessa() throws Exception {
        for (int i = testinKoko; i > 0; i--) {
            keko.insert(i);
        }
        for (int i = 1; i <= testinKoko; i++) {
            assertEquals(i, keko.deleteMin());
        }
    }

    @Test
    public void lisataanSuuriMaaraAlkioitaSatunnaisessaJarjestyksessa() throws Exception {
        Random generaattori = new Random();
        for (int i = 0; i < testinKoko; i++) {
            int satunnainen = generaattori.nextInt(testinKoko);
           // System.out.print(" satunnainen: " + satunnainen);
            keko.insert(generaattori.nextInt(testinKoko));
        }
        int edellinen = keko.deleteMin();
        for (int i = 0; i < testinKoko - 1; i++) {
            int seuraava = keko.deleteMin();
            assertTrue("edellinen oli: " + edellinen + " seuraava oli: " + seuraava, edellinen <= seuraava);
            edellinen = seuraava;
        }

    }
 
    
    @Test
    public void vertaaLisaamistaPriorityQueuehen() {
       PriorityQueue<Integer> jono = new PriorityQueue<Integer>();
        final int testeja = 10;
        long[] ajat = new long[testeja];
        long[] ajat2 = new long[testeja];
        for (int i = 0; i < testeja; i++) {
            long aika = System.currentTimeMillis();
            for (int j = 0; j < testinKoko; j++) {
                jono.add(j);
            }
            aika = System.currentTimeMillis() - aika;
            ajat[i] = aika;
        }
        for (int i = 0; i < testeja; i++) {
            long aika = System.currentTimeMillis();
            for (int j = 0; j < testinKoko; j++) {
                keko1.insert(j);
            }
            aika = System.currentTimeMillis() - aika;
            ajat2[i] = aika;
        }
        long summa = 0;
        for (int i = 0; i < testeja; i++) {
            summa += ajat[i];
        }
        long summa2 = 0;
        for (int i = 0; i < testeja; i++) {
            summa2 += ajat2[i];
        }
        System.out.println("javan aika: " + (summa * 1.0) / testeja);
        System.out.println("oma aika: " + (summa2 * 1.0) / testeja);
    }

    /*
    @Test 
    public void vertaaPoistamistaPriorityQueuehen() throws Exception {
        PriorityQueue<Integer> jono = new PriorityQueue<Integer>();
        for (int i = 0; i < testinKoko * testinKoko; i++) {
            jono.add(i);
        }
        
        long alkuaika = System.currentTimeMillis();
        for (int i = 0; i < testinKoko * testinKoko; i++) {
            jono.poll();
        }
        
        long loppuaika = System.currentTimeMillis();
        
        long aika = loppuaika - alkuaika;
        
        
        for (int i = 0; i < testinKoko * testinKoko; i++) {
            keko1.insert(i);
        }
        
        alkuaika = System.currentTimeMillis();
        for (int i = 0; i < testinKoko * testinKoko; i++) {
            keko1.deleteMin();
        }
       
        loppuaika = System.currentTimeMillis();
        long aika2 = loppuaika - alkuaika;
        
        System.out.println("oma aika: " + aika + " javan aika: " + aika2);
    }
    */
    
}

