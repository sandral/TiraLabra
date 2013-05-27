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
import tiralabra.FibonacciHeap;

/**
 *
 * @author root
 */
public class FibonacciHeapTest {
    FibonacciHeap keko1;
    public FibonacciHeapTest() {
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
    }
    
    @After
    public void tearDown() {
    }
    
    @Test 
    public void kekoOnAluksiTyhja() {
        assertTrue(keko1.isEmpty());
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
