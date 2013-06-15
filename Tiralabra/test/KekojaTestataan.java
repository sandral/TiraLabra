/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.PriorityQueue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.BinaryHeap;
import tiralabra.BinaryHeapOlioilla;
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
        Heap keko4 = new Dheap(2);
        Heap keko5 = new BinaryHeapOlioilla();
        PriorityQueue<Integer> jono = new PriorityQueue<Integer>();

        System.out.println("binäärikeko: " + aputesti(keko1));
        System.out.println("binomikeko: " + aputesti(keko2));
        System.out.println("Fibonacci-keko: " + aputesti(keko3));
        System.out.println("d-keko: " + aputesti(keko4));
        System.out.println("PriorityQueue: " + jonotesti(jono));
        System.out.println("BinäärikekoOlioilla: " + aputesti(keko5));
    }

    final int testinkoko = 100000;
    
    private double jonotesti(PriorityQueue jono) {
        final int testeja = 10;
        long[] ajat = new long[testeja];

        for (int i = 0; i < testeja; i++) {
            long aika = System.currentTimeMillis();
            for (int j = 0; j < testinkoko; j++) {
                jono.add(j);
            }
            aika = System.currentTimeMillis() - aika;
            ajat[i] = aika;
            jono.clear();
        }
        long summa = 0;
        for (int i = 0; i < testeja; i++) {
            summa += ajat[i];
        }
        return (summa * 1.0) / testeja;
    }

    private double jonotesti2(PriorityQueue jono) {
        final int testeja = 1;
        long[] ajat = new long[testeja];

        for (int i = 0; i < testeja; i++) {

            for (int j = 0; j < testinkoko; j++) {
                jono.add(j);
            }
            long aika = System.currentTimeMillis();
            for (int j = 0; j < testinkoko; j++) {
                jono.poll();
            }
            aika = System.currentTimeMillis() - aika;
            ajat[i] = aika;
        }
        long summa = 0;
        for (int i = 0; i < testeja; i++) {
            summa += ajat[i];
        }
        return (summa * 1.0) / testeja;
    }

    private double aputesti(Heap keko) throws Exception {
        final int testeja = 10;
        
        long[] ajat = new long[testeja];

        for (int i = 0; i < testeja; i++) {
            long aika = System.currentTimeMillis();
            for (int j = 0; j < testinkoko; j++) {
                keko.insert(j);
            }
            aika = System.currentTimeMillis() - aika;
            for (int j = 0; j < testinkoko; j++) {
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

    private double aputesti2(Heap keko) throws Exception {
        final int testeja = 1;
        long[] ajat = new long[testeja];

        for (int i = 0; i < testeja; i++) {

            for (int j = 0; j < testinkoko; j++) {
                keko.insert(j);
            }
            long aika = System.currentTimeMillis();
            for (int j = 0; j < testinkoko; j++) {
                keko.deleteMin();
            }
            aika = System.currentTimeMillis() - aika;
            ajat[i] = aika;
        }
        long summa = 0;
        for (int i = 0; i < testeja; i++) {
            summa += ajat[i];
        }
        return (summa * 1.0) / testeja;
    }
/*
    private double jonotesti3(PriorityQueue jono) {
        final int testeja = 100;
        long[] ajat = new long[testeja];

        for (int i = 0; i < testeja; i++) {

            for (int j = 0; j < testinkoko; j++) {
                jono.add(j);
            }
            long aika = System.currentTimeMillis();
            for (int j = 0; j < testinkoko; j++) {
                jono.poll();
            }
            aika = System.currentTimeMillis() - aika;
            ajat[i] = aika;
        }
        long summa = 0;
        for (int i = 0; i < testeja; i++) {
            summa += ajat[i];
        }
        return (summa * 1.0) / testeja;
    }
    */ 
}
