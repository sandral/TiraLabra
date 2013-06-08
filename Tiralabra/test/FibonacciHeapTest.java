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
        assertEquals(n1, keko1.heapMin()); 
        assertEquals(1, keko1.extractMin());
        assertTrue(keko1.isEmpty());
    } 
    
    @Test
    public void kekoonVoiLisataKaksiSolmuaJaNeLoytyvat() throws Exception { 
        keko1.insert(n1);
        keko1.insert(n2);
        assertEquals(2, keko1.getCount()); 
        assertEquals(n1, keko1.heapMin());
        assertEquals(1, keko1.extractMin());
        assertEquals(1, keko1.getCount());
        assertEquals(0, n1.degree);
        assertEquals(0, n2.degree);
        assertEquals(2, keko1.extractMin());
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
        assertEquals(n1, keko1.heapMin());
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
        assertEquals(1, keko1.extractMin());
        assertEquals(2, keko1.extractMin());
        assertEquals(3, keko1.extractMin());
        assertEquals(4, keko1.extractMin());
        assertEquals(5, keko1.extractMin());
        assertTrue(keko1.isEmpty());
    }
   
    
 
    @Test 
    public void kekoonVoiLisataNeljäAlkiotaJaPoistaaNe() throws Exception {
        keko1.insert(n1);
        keko1.insert(n2);
        keko1.insert(n3);
        
        
        assertEquals(3, keko1.getCount());
        assertEquals(n1, keko1.heapMin());        
        assertEquals(1, keko1.extractMin());
        assertEquals(n3, n2.child);
        assertEquals(n2, keko1.heapMin());
        assertEquals(2, keko1.extractMin());
        assertEquals(n3, keko1.heapMin());
        assertEquals(3, keko1.extractMin());
        
       
    }
    
    @Test
    public void kekoonVoiLisataMontaSolmuaJarjestyksessa() throws Exception {
        for (int i = 0; i < 666; i++) {
            keko1.insert(new Fnode(i));
        }
        System.out.println("count: " + keko1.getCount());
        for (int i = 0; i < 666; i++) {
            assertEquals(i, keko1.extractMin());
        }
    }
    
    @Test
    public void kekoonVoiLisataMontaSolmuaKaanteisessaJarjestyksessa() throws Exception {
        for (int i = koko; i >= 0; i--) {
            keko1.insert(new Fnode(i));
        }
        
        assertEquals(koko + 1, keko1.getCount());
        assertFalse(keko1.isEmpty());
        assertEquals(0, keko1.heapMin().key);
        for (int i = 0; i < koko + 1; i++) {
            assertEquals(i, keko1.extractMin());
        }
    }
    
    @Test
    public void kekoonVoiLisataMontaSolmuaSatunnaisessaJarjestyksessa() throws Exception {
        Random generaattori = new Random();
        for (int i = 0; i < koko; i++) {
            keko1.insert(new Fnode(generaattori.nextInt(koko)));
        }
        int edellinen = keko1.extractMin();
        for (int i = 0; i < koko - 1; i++) {
            int seuraava = keko1.extractMin();
            assertTrue(edellinen <= seuraava);
            edellinen = seuraava;
        }
    }
} 
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

