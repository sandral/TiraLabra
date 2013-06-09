/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.FibonacciHeap;
import tiralabra.Fnode;

/**
 *
 * @author root
 */
public class FibonacciHeapTest {
    FibonacciHeap keko1;
    FibonacciHeap keko2;
    Fnode n1;
    Fnode n2;
    Fnode n3;
    Fnode n4;
    Fnode n5;
    int koko;
            
    public FibonacciHeapTest() {
    koko = 1000;
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        keko1 = new FibonacciHeap();
        keko2 = new FibonacciHeap();
        n1 = new Fnode(1);
        n2 = new Fnode(2);
        n3 = new Fnode(3);
        n4 = new Fnode(4);
        n5 = new Fnode(5);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test 
    public void kekoOnAluksiTyhja() {
        assertTrue(keko1.isEmpty());
    }
    
    @Test
    public void kekoonVoiLisataSolmunJaSeLoytyy() throws Exception {
        keko1.insert(n1);
        assertFalse(keko1.isEmpty());
        assertEquals(n1, keko1.minNode()); 
        assertEquals(1, keko1.deleteMin());
        assertTrue(keko1.isEmpty());
    } 
    
    @Test
    public void kekoonVoiLisataKaksiSolmuaJaNeLoytyvat() throws Exception { 
        keko1.insert(n1);
        keko1.insert(n2);
        assertEquals(2, keko1.getCount()); 
        assertEquals(n1, keko1.minNode());
        assertEquals(1, keko1.deleteMin());
        assertEquals(1, keko1.getCount());
        assertEquals(0, n1.degree);
        assertEquals(0, n2.degree);
        assertEquals(2, keko1.deleteMin());
        assertTrue(keko1.isEmpty());
    }
    
    @Test
    public void kekoonVoiLisataViisiSolmuaJaPerheSuhteetOvatOikein() throws Exception {
        keko1.insert(n1);
        keko1.insert(n2);
        keko1.insert(n3);
        keko1.insert(n4);
        keko1.insert(n5);
        assertEquals(5, keko1.getCount());
        assertEquals(n1, keko1.minNode());
        assertEquals(n2, n1.right);
        assertEquals(n5, n1.left);
        assertEquals(n1, n2.left);
        assertEquals(n3, n2.right);
        assertEquals(n2, n3.left);
        assertEquals(n4, n3.right);
        assertEquals(n3, n4.left);
        assertEquals(n5, n4.right);
        assertEquals(n4, n5.left);
        assertEquals(n1, n5.right);
        assertEquals(1, keko1.deleteMin());
        assertEquals(2, keko1.deleteMin());
        assertEquals(3, keko1.deleteMin());
        assertEquals(4, keko1.deleteMin());
        assertEquals(5, keko1.deleteMin());
        assertTrue(keko1.isEmpty());
    }
   
    
 
    @Test 
    public void kekoonVoiLisataNeljäAlkiotaJaPoistaaNe() throws Exception {
        keko1.insert(n1);
        keko1.insert(n2);
        keko1.insert(n3);
        
        
        assertEquals(3, keko1.getCount());
        assertEquals(n1, keko1.minNode());        
        assertEquals(1, keko1.deleteMin());
        assertEquals(n3, n2.child);
        assertEquals(n2, keko1.minNode());
        assertEquals(2, keko1.deleteMin());
        assertEquals(n3, keko1.minNode());
        assertEquals(3, keko1.deleteMin());
        
       
    }
    
    @Test
    public void kekoonVoiLisataMontaSolmuaJarjestyksessa() throws Exception {
        for (int i = 0; i < 666; i++) {
            keko1.insert(new Fnode(i));
        }
        System.out.println("count: " + keko1.getCount());
        for (int i = 0; i < 666; i++) {
            assertEquals(i, keko1.deleteMin());
        }
    }
    
    @Test
    public void kekoonVoiLisataMontaSolmuaKaanteisessaJarjestyksessa() throws Exception {
        for (int i = koko; i >= 0; i--) {
            keko1.insert(new Fnode(i));
        }
        
        assertEquals(koko + 1, keko1.getCount());
        assertFalse(keko1.isEmpty());
        assertEquals(0, keko1.min());
        for (int i = 0; i < koko + 1; i++) {
            assertEquals(i, keko1.deleteMin());
        }
    }
    
    @Test
    public void kekoonVoiLisataMontaSolmuaSatunnaisessaJarjestyksessa() throws Exception {
        Random generaattori = new Random();
        for (int i = 0; i < koko; i++) {
            keko1.insert(new Fnode(generaattori.nextInt(koko)));
        }
        int edellinen = keko1.deleteMin();
        for (int i = 0; i < koko - 1; i++) {
            int seuraava = keko1.deleteMin();
            assertTrue(edellinen <= seuraava);
            edellinen = seuraava;
        }
    }
    
    @Test
    public void testaaYhdistamista() throws Exception {
        keko1.insert(n1);
        keko2.insert(n2);
        keko2.insert(n3);
        
        FibonacciHeap keko3 = keko1.union(keko2);
        assertFalse(keko3.isEmpty());
        assertEquals(n1, keko3.minNode());
        assertEquals(1, keko3.deleteMin());
        assertEquals(2, keko3.deleteMin());
        assertEquals(3, keko3.deleteMin());
    }
    
    @Test 
    public void testaaYhdistamistaLisaa() throws Exception {
        for (int i = 0; i < 10; i++) {
            keko1.insert(new Fnode(i));
        }
        
        
        keko1.deleteMin();
        int c1 = keko1.getCount();        
        
        for (int i = 10; i < 20; i++) {
            keko2.insert(new Fnode(i));
        }
        keko2.deleteMin();
        int c2 = keko2.getCount();
        
        FibonacciHeap keko3 = keko1.union(keko2);
        
        assertEquals(1, keko3.min());
        assertEquals(c1 + c2, keko3.getCount());
        
        
    }
} 
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

