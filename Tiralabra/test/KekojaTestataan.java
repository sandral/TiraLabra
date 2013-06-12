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
import tiralabra.BinomialHeap;
import tiralabra.Dheap;
import tiralabra.FibonacciHeap;
import tiralabra.Heap;

/**
 *
 * @author root
 */
public class KekojaTestataan {

    public KekojaTestataan() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
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
    public void testi() throws Exception {
        Heap keko1 = new BinaryHeap();
        Heap keko2 = new BinomialHeap();
        Heap keko3 = new FibonacciHeap();
        Heap keko4 = new Dheap(3);
        System.out.println(aputesti(keko1));
        System.out.println(aputesti(keko2));
        System.out.println(aputesti(keko3));
        System.out.println(aputesti(keko4));
    }

    private double aputesti(Heap keko) throws Exception {
        final int testeja = 100;
        long[] ajat = new long[testeja];

        for (int i = 0; i < testeja; i++) {
            long aika = System.currentTimeMillis();
            for (int j = 0; j < 10000; j++) {
                keko.insert(j);
            }
            aika = System.currentTimeMillis() - aika;
            for (int j = 0; j < 10000; j++) {
                keko.deleteMin();
            }
            ajat[i] = aika;
        }
        long summa = 0;
        for (int i = 0; i < testeja; i++) {
            summa += ajat[i];
        }
        return (summa * 1.0) / testeja;
    }
    
   /*private double aputesti2(Heap keko) {
       final int testeja = 100;
       long[] ajat = new long[testeja];
       
       for (int i = 0; i < testeja; i++) {
           long aika = System.currentTimeMillis();
       }
   }
     */   
    }

