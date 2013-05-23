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
import tiralabra.BinomialHeap;
import tiralabra.Node;


/**
 *
 * @author root
 */
public class BinomialHeapTest {
    private BinomialHeap keko1;
    private BinomialHeap keko2;
    private Node n1;
    private Node n2;
    private Node n3;
    private Node n4;
    private Node n5;
    
    public BinomialHeapTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        keko1 = new BinomialHeap();
        keko2 = new BinomialHeap();
        n1 = new Node(1,1);
        n2 = new Node(2,2);
        n3 = new Node(3,3);
        n4 = new Node(4,4);
        n5 = new Node(5,5);
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void kekoOnAluksiTyhja() {    
        assertTrue(keko1.isEmpty());
    }
        
    @Test
    public void kunKekoonOnLisattySolmuSeEiOleTyhja() {
        keko1.insert(n1);
        assertFalse(keko1.isEmpty());
    }
    
    @Test 
    public void kekoonLisattyAinoaSolmuOnSenHead() {
        keko1.insert(n1);
        assertEquals(n1, keko1.getHead());
    }
    
    @Test
    public void kekoonLisattyAinoaSolmuOnPienin() {
        keko1.insert(n1);
        assertEquals(1, keko1.heapMin());
    }
    
    @Test
    public void kekoonVoiLisataSolmunJaEkstraktoidaPienimman() {
        keko1.insert(n1);
        assertEquals(1, keko1.extractMin());
    }
    
    @Test
    public void kunAinoaSolmuOnEkstraktoituKekoOnTyhja() {
        keko1.insert(n1);
        keko1.extractMin();
        assertTrue(keko1.isEmpty());
    }
    
    @Test 
    public void solmunAvaintaVoiPienentää() {
        keko1.insert(n1);
        keko1.decreaseKey(n1, 0);
        assertEquals(0, n1.key);
    }
    @Test
    public void tyhjanJaYhdenSolmunSisaltavanKeonVoiMergeta() {
        keko1.insert(n1);
        assertEquals(n1, keko1.merge(keko1, keko2));
    }
    
    @Test 
    public void keotJoissaOnMolemmissaYksiSolmuVoiMergeta() {
        keko1.insert(n1);
        keko2.insert(n2);
        assertEquals(n1, keko1.merge(keko1, keko2));
    }
     
    
    @Test 
    public void tyhjatKeotVoiYhdistaa() {
        assertEquals(keko1, keko1.union(keko2));
    }
    
    @Test 
    public void keotJoissaOnMolemmissaYksiAlkioVoiYhdistaa() {
        keko1.insert(n1);
        keko2.insert(n2);
        BinomialHeap keko3 = keko1.union(keko2);
        assertEquals(n1, keko3.getHead());
    }
    
    
    @Test 
    public void kekoonVoiLisataKaksiSolmuaJaNiidenAsteetJaPerheSuhteetOvatOikein() {
        keko1.insert(n1);
        keko1.insert(n2);
        assertEquals(1, keko1.getHead().key);
        assertEquals(2, keko1.getHead().child.key);
        assertEquals(1, keko1.getHead().degree);
        assertEquals(0, keko1.getHead().child.degree);
        assertEquals(n2, keko1.getHead().child);
        assertEquals(null, keko1.getHead().sibling);
        assertEquals(n1, keko1.getHead().child.parent);
    }
    
    
    @Test 
    public void kaksiAlkiotaSisaltavatKeotVoiMergeta() {
        keko1.insert(n1);
        keko1.insert(n2);
        keko2.insert(n3);
        keko2.insert(n4);
        assertEquals(n1, keko1.merge(keko1, keko2));
        assertEquals(n3, n1.sibling);
        assertEquals(null, n3.sibling);
        assertEquals(null, n3.sibling);
        assertEquals(n4, n3.child);
        assertEquals(n2, n1.child);
        assertEquals(null, n4.child);
        assertEquals(null, n2.child);

    }
    
    @Test
    public void kaksiAlkiotaSisaltavatKeotVoiMergetaToisinPain() {
        keko1.insert(n1);
        keko1.insert(n2);
        keko2.insert(n3);
        keko2.insert(n4);
        assertEquals(n3, keko1.merge(keko2, keko1));
        assertEquals(n1, n3.sibling);
        assertEquals(null, n1.sibling);
        assertEquals(null, n2.sibling);
        assertEquals(n4, n3.child);
        assertEquals(n2, n1.child);
        assertEquals(null, n4.child);
        assertEquals(null, n2.child);

    }
    
    
    @Test
    public void kolmeJaYksiAlkiotaSisaltavatKeotVoiMergeta() {
        keko1.insert(n1);
        keko1.insert(n2);
        keko1.insert(n3);
        keko2.insert(n4);
        assertEquals(n3, keko1.merge(keko1, keko2));
        assertEquals(n4, n3.sibling);
        assertEquals(n1, n4.sibling);
        assertEquals(null, n1.sibling);
        assertEquals(n2, n1.child);
    }
    
    @Test
    public void yksiJaKolmeAlkiotaSisaltavatKeotVoiMergeta() {
        keko1.insert(n1);
        keko1.insert(n2);
        keko1.insert(n3);
        keko2.insert(n4);
        assertEquals(n4, keko1.merge(keko2, keko1));
        assertEquals(n3, n4.sibling);
        assertEquals(n1, n3.sibling);
        assertEquals(null, n1.sibling);
        assertEquals(n2, n1.child);
    }
    @Test
    public void kolmeJaYksiAlkiotaSisaltavaKeotVoiYhdistaaJaPerheSuhteetSailyvat() {
        keko1.insert(n1);
        keko1.insert(n2);
        keko1.insert(n3);
        keko2.insert(n4);
        assertEquals(n1, keko2.union(keko1).getHead());
        assertEquals(n3, n1.child);
        assertEquals(n1, n3.parent);
        assertEquals(n1, n2.parent);
        assertEquals(null, n1.sibling);
        assertEquals(n2, n3.sibling);
        assertEquals(n4, n3.child);
        assertEquals(n3, n4.parent);
        assertEquals(null, n4.child);
        assertEquals(null, n4.sibling);    
    }
    
    @Test
    public void yksiJaKolmeAlkiotaSisaltavatKeotVoiYhdistaaJaPerheSuhteetSailyvat() {
        keko1.insert(n1);
        keko1.insert(n2);
        keko1.insert(n3);
        keko2.insert(n4);
        assertEquals(n1, keko2.union(keko1).getHead());
        assertEquals(n3, n1.child);
        assertEquals(n1, n3.parent);
        assertEquals(n1, n2.parent);
        assertEquals(null, n1.sibling);
        assertEquals(n2, n3.sibling);
        assertEquals(n4, n3.child);
        assertEquals(n3, n4.parent);
        assertEquals(null, n4.child);
        assertEquals(null, n4.sibling);
    }
   
    
    
    /*
    @Test 
    public void kekoonVoiLisataKolmeSolmua() {
        keko1.insert(n1);
        keko1.insert(n2);
        keko1.insert(n3);
        keko1.insert(n4);
        assertEquals(n1, keko1.getHead());
        assertEquals(n1, n3.sibling);
        assertEquals(n2, n1.child);
        assertEquals(1, n1.degree);
        assertEquals(0, n3.degree);
        assertEquals(0, n2.degree);
        assertEquals(null, n2.sibling);
        assertEquals(n1, n2.parent);
        assertEquals(null, n1.sibling);
        
    }
     */
    
    
            
    @Test
    public void solmunVoiPoistaaKeostaJaKekoOnSenJalkeenTyhja() {
        keko1.insert(n1);
        keko1.delete(n1);
        assertTrue(keko1.isEmpty());
    }
     
    
    
    
    
     
    
    
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
