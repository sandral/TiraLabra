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
    

    
    /*
    @Test
    public void kekoonVoiLisataKaksiSolmua() {
        Node n1 = new Node(1,1);
        Node n2 = new Node(2,2);
        keko1.insert(n1);
        keko1.insert(n2);
        assertEquals(1, keko1.extractMin());
    }
    */
     
    
    
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
