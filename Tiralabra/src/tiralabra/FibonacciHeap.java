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

    private final double phi = (1.0 + Math.sqrt(5.0)) / 2.0;
    
    private Fnode min;
    private int count; //solmujen lkm

    /**
     * Konstruktori, luo tyhjän keon.
     */
    public FibonacciHeap() {
        min = null;
        count = 0;
    }

    public int getCount() {
        return count;
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
            n.right = min;
            n.left = min.left;
            min.left = n;
            n.left.right = n;

            if (n.key < min.key) {
                min = n;
            }
        } else {
            min = n;
            min.left = min;
            min.right = min;
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
    public int extractMin() throws Exception {
        Fnode x = min;
        if (x == null) {
            return 0;
        }
        
        if (x != null) {
            int lapsia = x.degree;
            Fnode y = x.child;
            Fnode z;

            while (lapsia > 0) {
                z = y.right;
                y.left.right = y.right;
                y.right.left = y.left;
                
                y.left = min;
                y.right = min.right;
                min.right = y;
                y.right.left = y;
                
                y.parent = null;
                y = z;
                lapsia--;
            }
            
            x.left.right = x.right;
            x.right.left = x.left;
            
            if (x == x.right) {
                min = null;
            }
            else {
                min = x.right;
                consolidate();
            }
            count--;
            
        }
        return x.key;
    }
/**
 * Apumetodi extractMin-metodille. Yhdistää solmuja toisiinsa siten, että 
 * juurilistassa ei ole kahta samaa astetta olevaa solmua.
 */
    private void consolidate() {
        if (min == null) {
            return;
        }
        
        int dCount = (int) Math.floor(Math.log(count) / Math.log(phi));
        Fnode[] apu = new Fnode[dCount + 1];
        
        Fnode x = min;
        do {
            int d = x.degree;
            while (apu[d] != null) {
                Fnode y = apu[d];
                if(x.key > y.key) {
                    swap(x,y);
                }
                link(y,x);
                apu[d] = null;
                d = d + 1;
            }
            apu[d] = x;
            x = x.right;
        } while (x != min);

        min = null;
        
        for (int i = 0; i <= dCount; i++) {
            if (apu[i] == null)
                continue;
            if (min == null) {
                min = apu[i];
                min.left = min;
                min.right = min;
            } else {
                apu[i].left = min.left;
                apu[i].right = min;
                min.left.right = apu[i];
                min.left = apu[i];
                if (apu[i].key < min.key) {
                    min = apu[i];
                }
            }
        }
    }
    
    /**
     * Apumetodi consolidatelle, vaihtaa kahden solmun paikkaa keskenään siten,
     * että näiden alipuut seuraavat vaihdossa mukana.
     * @param x solmu jonka paikkaa vaihdetaan
     * @param y tämän solmun kanssa
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}

    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}

     */
    private void swap(Fnode x, Fnode y) {
        Fnode xoikea = x.right;
        Fnode xvasen = x.left;
        x.right = y.right;
        x.left = y.left;
        y.right = xoikea;
        y.left = xvasen;
        
    }

    /**
     * Apumetodi consolidate-metodille. Linkittää solmut toisiinsa ja asettaa toisen
     * solmun toisen lapseksi.
     * @param n1 
     * @param n2 
     */
    private void link(Fnode n1, Fnode n2) {
        Fnode oikea = n1.right;
        Fnode vasen = n1.left;
        oikea.left = vasen;
        vasen.right = oikea;
        n1.parent = n2;
                        
        if (n2.child == null) {
            n2.child = n1;
            n1.right = n1;
            n1.left = n1;
        } else {
            n1.left = n2.child;
            n1.right = n2.child.right;
            n2.child.right = n1;
            n1.right.left = n1;
        }

        n2.degree++;
        n1.mark = false;
    }
}