/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import tiralabra.BinaryHeap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author root
 */
public class BinaryHeapTest {
    private BinaryHeap keko;
    private BinaryHeap keko1;
    
    public BinaryHeapTest() {
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
        assertTrue(keko.getTaulukko().length == 10);
    }
    
    @Test
    public void KeonKokoOnAlussaNolla() {
        assertEquals(0, keko.getHeapSize());
        
    }
    @Test
    public void KeonTaulukonKokoOnDefaultKunOLuotuUusiKekoParametrillaMiinusViisi() {
        assertTrue(keko1.getTaulukko().length == keko.getDefaultKoko());
    }
}
