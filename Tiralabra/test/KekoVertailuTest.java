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
import tiralabra.BinaryHeap;
import tiralabra.BinaryHeap.HeapException;
import tiralabra.BinomialHeap;
import tiralabra.Node;
import tiralabra.OmaTaulukko;

/**
 *
 * @author root
 */
public class KekoVertailuTest {
    private BinaryHeap binaarikeko;
    private BinomialHeap binomikeko;
    private int testinKoko;
    
    public KekoVertailuTest() {
        testinKoko = 1000;
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    binaarikeko = new BinaryHeap(100);
    binomikeko = new BinomialHeap();
    
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testaaLisaamista() {
        long alkuaika = System.currentTimeMillis();
        for (int i = 0; i < testinKoko * testinKoko; i++) {
            binaarikeko.insert(i);
        }
        long loppuaika = System.currentTimeMillis();
        long aika = loppuaika - alkuaika;
        
        alkuaika = System.currentTimeMillis();
        for (int i = 0; i < testinKoko * testinKoko; i++) {
            Node n = new Node(i, i);
            binomikeko.insert(n);
        }
        loppuaika = System.currentTimeMillis();
        
        long aika2 = loppuaika - alkuaika;
        
        assertTrue("binäärikeon aika: " + aika + " binomikeonaika " + aika2, 1 < 0);
    }
    
    @Test
    public void testaaPoistamista() throws HeapException {
         for (int i = 0; i < testinKoko * testinKoko; i++) {
            binaarikeko.insert(i);
        }
         long alkuaika = System.currentTimeMillis();
         for (int i = 0; i < testinKoko * testinKoko; i++) {
            binaarikeko.deleteMin();
        }
         long loppuaika = System.currentTimeMillis();
         long aika = loppuaika - alkuaika;
         
         for (int i = 0; i < testinKoko * testinKoko; i++) {
            Node n = new Node(i, i);
            binomikeko.insert(n);
        }
         
         alkuaika = System.currentTimeMillis();
         for (int i = 0; i < testinKoko * testinKoko; i++) {
            binomikeko.deleteMin();
        }
         
         loppuaika = System.currentTimeMillis();
         long aika2 = loppuaika - alkuaika;
         
         assertTrue("binäärikeon aika: " + aika + " binomikeonaika " + aika2, 1 < 0);
    }
    
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
