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
public class FibonacciHeap implements Heap {

    private final double phi = (1.0 + Math.sqrt(5.0)) / 2.0; // juurilistan pituus enintään
    private Fnode min; // keon pienin solmu
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
     * @return palauttaa true, jos keon pienin on null
     */
    public boolean isEmpty() {
        return min == null;
    }

    /**
     * Lisaa kekoon parametrina annetun solmun.
     *
     * @param n lisättävä solmu
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
     * @return keon pienin solmu
     */
    public Fnode minNode() {
        return min;
    }

    /**
     * Palauttaa ja poistaa keon pienimman avaimen omaavan solmun avaimen.
     *
     * @return keon pienimmän solmun avain.
     */
    public int deleteMin() throws Exception {
        Fnode pienin = min;
        if (pienin == null) {
            throw new Exception("keko on tyhjä");
        }

        int lapsia = pienin.degree;
        Fnode y = pienin.child;
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

        pienin.left.right = pienin.right;
        pienin.right.left = pienin.left;

        if (pienin == pienin.right) {
            min = null;
        } else {
            min = pienin.right;
            consolidate();
        }
        count--;
        return pienin.key;
    }

    /**
     * Apumetodi deleteMin-metodille. Yhdistää solmuja toisiinsa siten, että
     * juurilistassa ei ole kahta samaa astetta olevaa solmua.
     */
    private void consolidate() {
        if (min == null) {
            return;
        }

        int dCount = (int) Math.floor(Math.log(count) / Math.log(phi)) + 1;
        Fnode[] apu = new Fnode[dCount + 1];

        // Lasketaan root listin koko
        int rootListSize = 0;
        Fnode x = min;
        do {
            x = x.right;
            rootListSize++;
        } while (x != min);

        Fnode[] taulu = new Fnode[rootListSize];

        x = min;
        for (int i = 0; i < rootListSize; i++) {
            taulu[i] = x;
            x = x.right;
        }

        for (int i = 0; i < taulu.length; i++) {
            x = taulu[i];
            int d = x.degree;
            while (apu[d] != null) {
                Fnode y = apu[d];
                if (x.key > y.key) {
                    Fnode _x = x;
                    x = y;
                    y = _x;
                }
                link(y, x);
                apu[d] = null;
                d = d + 1;
            }
            apu[d] = x;
        }

        min = null;

        for (int i = 0; i <= dCount; i++) {
            if (apu[i] == null) {
                continue;
            }
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
     * Apumetodi consolidate-metodille. Linkittää solmut toisiinsa ja asettaa
     * toisen solmun toisen lapseksi.
     *
     * @param n1 solmu, josta tulee toisen lapsi
     * @param n2 solmu, josta tulee toisen vanhempi
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

    /**
     * Yhdistaa tämän keon toisen keon kanssa yhdeksi keoksi
     * @param toinen tämän keon kanssa yhdistettävä keko
     * @return yhdistetty keko
     */
    public FibonacciHeap union(FibonacciHeap toinen) {
        if (isEmpty()) {
            return toinen;
        } else if (toinen.isEmpty()) {
            return this;
        } else {

            FibonacciHeap uusi = new FibonacciHeap();
            if (min() <= toinen.min()) {
                uusi.min = min;
                Fnode vasen = uusi.min.left;
                uusi.min.left.right = toinen.min;
                uusi.min.left = toinen.min.left;
                toinen.min.left.right = uusi.min;
                toinen.min.left = vasen;
                uusi.count = count + toinen.count;
            } else {
                uusi.min = toinen.min;
                Fnode vasen = uusi.min.left;
                uusi.min.left.right = min;
                uusi.min.left = min.left;
                min.left.right = uusi.min;
                min.left = vasen;
                uusi.count = count + toinen.count;
            }
            return uusi;
        }
        
    }
    
    

    public void insert(int key) {
        insert(new Fnode(key));
    }

    public int min() {
        return min.key;
    }
}