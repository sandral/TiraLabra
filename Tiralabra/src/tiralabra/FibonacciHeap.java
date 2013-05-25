/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

import tiralabra.Fnode;

/**
 *
 * @author root
 */
public class FibonacciHeap {

    private Fnode min;
    private int count; //solmujen lkm

    public FibonacciHeap(Fnode min, int count) {
        min = null;
        count = 0;
    }

    /**
     * Tarkistaa, onko keko tyhja.
     *
     * @return
     */
    public boolean isEmpty() {
        return min == null;
    }

    /**
     * Lisaa kekoon parametrina annetun solmun.
     *
     * @param n parametrina annettava solmu.
     */
    public void insert(Fnode n) {
        if (min != null) {
            n.left = min;
            n.right = min.right;
            min.right = n;
            n.right.left = n;

            if (n.key < min.key) {
                min = n;
            }
        } else {
            min = n;
        }
        count++;
    }

    /**
     * Palauttaa keon pienimmän solmun.
     *
     * @return
     */
    public Fnode heapMin() {
        return min;
    }

    /**
     * Palauttaa ja poistaa keon pienimman avaimen omaavan solmun avaimen.
     *
     * @return
     */
    public int extractMin() {
        Fnode x = min;
        if (x != null) {
            Fnode y = x.child;
            Fnode z;

            for (int i = x.degree; i > 0; i--) {
                z = y.right;
                y.left.right = y.right;
                y.right.left = y.left;

                y.left = min;
                y.right = min.right;
                min.right = y;
                y.right.left = y;

                y.parent = null;
                y = z;
            }

            x.left.right = x.right;
            x.right.left = x.left;

            if (x == x.right) {
                min = null;
            } else {
                min = x.right;
                consolidate();
            }
            count--;
        }
        return x.key;
    }

    public void consolidate() {
        if (min == null) {
            return;
        }
        double phi = (1.0 + Math.sqrt(5.0)) / 2.0;
        int dCount = (int) Math.floor(Math.log(count) / Math.log(phi));

        Fnode[] dIndex = new Fnode[dCount + 1];

        for (int i = 0; i < dCount; i++) {
            dIndex[i] = null;
        }

        int rCount = 0;
        Fnode x = min;

        if (x != null) {
            rCount++;
            x = x.right;

            while (x != min) {
                rCount++;
                x = x.right;
            }
        }
        while (rCount > 0) {
            int d = x.degree;
            Fnode seuraava = x.right;

            while (dIndex[d] != null) {
                Fnode y = dIndex[d];

                if (x.key > y.key) {
                    Fnode z = y;
                    y = x;
                    x = z;
                }

                link(y, x);

                dIndex[d] = null;
                d++;
            }

            dIndex[d] = x;
            x = seuraava;
            rCount--;
        }
        min = null;

        for (int i = 0; i < count; i++) {
            Fnode y = dIndex[i];
            if (y != null) {
                if (min != null) {
                    y.left.right = y.right;
                    y.right.left = y.left;

                    y.left = min;
                    y.right = min.right;
                    min.right = y;
                    y.right.left = y;


                    if (y.key < min.key) {
                        min = y;
                    } else {
                        min = y;
                    }
                }
            }
        }
    }

    public void link(Fnode n1, Fnode n2) {
        n1.left.right = n1.right;
        n1.right.left = n1.left;
        n1.parent = n2;
        
        if (n2.child == null) {
            n2.child = n1;
            n1.right = n1;
            n1.left = n1;
        }
        else {
            n1.left = n2.child;
            n1.right = n2.child.right;
            n2.child.right = n1;
            n1.right.left = n1;
        }
        
        n2.degree++;
        n1.mark = false;
    }
    
}
