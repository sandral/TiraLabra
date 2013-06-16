/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

import java.util.PriorityQueue;
import tiralabra.Bnode;

public class BinomialHeap implements Heap {
/**
 * Keon juurilistan ensimmäinen solmu
 */
    private Bnode head;

    /**
     * Konstruktori
     */
    public BinomialHeap() {
        head = null;
    }

    public Bnode getHead() {
        return head;
    }

    /**
     * Lisää parametrina annetun solmun kekoon luomalla uuden keon,
     * jonka headiksi lisättävä solmu asetetaan, ja yhdistämällä 
     * uuden keon alkuperäisen kanssa.
     *
     * @param n lisättävä solmu
     */
    public void insert(Bnode n) {
        if (this.isEmpty()) {
            this.head = n;
        } else {
            BinomialHeap h = new BinomialHeap();
            h.head = n;
            BinomialHeap yhdistetty = this.union(h);
            head = yhdistetty.head;

        }
    }

    /**
     * Palauttaa keon pienimmän solmun avaimen.
     * @return keon pienimmän solmun avain
     */
    public int min() {
        Bnode x = head;
        Bnode y = x.sibling;
        if (y != null) {
            while (x != null) {
                if (y.key < x.key) {
                    x = y;
                    y = x.sibling;
                }
            }
        }
        return x.key;
    }

    /**
     * Palauttaa keon pienimmän solmun avaimen ja poistaa solmun keosta.
     *
     * @return keon pienimmän solmun avain
     */
    public int deleteMin() {
        if (this.isEmpty()) {
            return -1;
        }
        if (head.sibling == null && head.degree == 0) {
            int avain = head.key;
            head = null;
            return avain;
        }
        Bnode x = head;
        Bnode y = x.sibling;
        Bnode edell = x;
        Bnode edellx = null;

        while (y != null) { // log n
            if (y.key < x.key) {
                x = y;
                edellx = edell;
            }
            edell = y;
            y = y.sibling;
        }

        if (x == head) {
            head = x.sibling;
        } else {
            edellx.sibling = x.sibling;
        }

        BinomialHeap uusi = new BinomialHeap();

        Bnode z = x.child;
        while (z != null) {  // log n
            Bnode seur = z.sibling;
            z.sibling = uusi.head;
            uusi.head = z;
            z = seur;
        }
        BinomialHeap uusikeko = this.union(uusi);
        head = uusikeko.head;
        return x.key;
    }

    /**
     * Pienentää parametrina annetun solmun avaimen arvon k:ksi, jos k pienempi
     * kuin solmun x avain.
     *
     * @param x solmu, jonka avainta pienennetään
     * @param k lukuarvo, joksi k:n avaimen arvo pienennetään
     */
    public void decreaseKey(Bnode x, int k) {
        if (k >= x.key) {
            return;
        }

        x.key = k;
        Bnode z = x;
        while (z.parent != null && z.parent.key > z.key) {
            int temp = z.key;
            z.key = z.parent.key;
            z.parent.key = temp;
            z = z.parent;
        }
    }

    /**
     * Poistaa parametrina annetun solmun keosta
     *
     * @param x poistettava solmu
     */
    public void delete(Bnode x) {
        int min = min();
        decreaseKey(x, min - 1);
        deleteMin();
    }

    /**
     * Linkittää kaksi alipuuta toisiinsa siten, että toisesta
     * tulee toisen vanhempi.
     *
     * @param x toisen alipuun juuri, josta tulee vanhempi
     * @param y toisen alipuun juuri, josta tulee ensimmäisen lapsi.
     */
    private void link(Bnode x, Bnode y) {
        x.parent = y;
        x.sibling = y.child;
        y.child = x;
        y.degree++;
    }

    /**
     * Yhdistaa kekojen h1 ja h2 juurilistat kasvavaan jarjestykseen juurisolmun
     * asteen mukaan ja palauttaa uuden juurilistan pään.
     *
     * @param h1 yhdistettävä keko
     * @param h2 yhdistettävä keko
     * @return
     */
    public Bnode merge(BinomialHeap h1, BinomialHeap h2) {
        Bnode n1 = h1.head;
        Bnode n2 = h2.head;

        if (h1.isEmpty() && h2.isEmpty()) {
            return null;
        }
        if (h1.isEmpty()) {
            return h2.head;
        }
        if (h2.isEmpty()) {
            return h1.head;
        }

        if (n1.degree <= n2.degree) {
            h1.head = n1;
        } else {
            h1.head = n2;
        }
        if (h1.head == n2) {
            n2 = n1;
        }
        n1 = h1.head;

        while (n2 != null) { // log n + log m
            if (n1.sibling == null) {
                n1.sibling = n2;
                n2 = n2.sibling;
            } else if (n1.sibling.degree < n2.degree) {
                n1 = n1.sibling;
            } else {
                Bnode n3 = n2.sibling;
                if (n2 == n1.sibling) {
                    n2.sibling = null;
                } else {
                    n2.sibling = n1.sibling;
                }
                n1.sibling = n2;
                n2 = n3;

            }
        }
        return h1.head;
    }

    /**
     * Yhdistää tämän keon ja keon h2 yhdeksi keoksi.
     *
     * @param h2 keko, jonka kanssa tämä keko yhdistetään.
     * @return yhdistetty keko
     */
    public BinomialHeap union(BinomialHeap toinen) {
        BinomialHeap uusi = new BinomialHeap();
        uusi.head = merge(this, toinen);
        if (uusi.isEmpty()) {
            return this;
        }
        Bnode edellx = null;
        Bnode x = uusi.head;
        Bnode seurx = x.sibling;

        while (seurx != null) {
            if (x.degree != seurx.degree
                    || (seurx.sibling != null && seurx.sibling.degree == x.degree)) {
                edellx = x;
                x = seurx;
            } else {
                if (x.key < seurx.key) {
                    x.sibling = seurx.sibling;
                    link(seurx, x);
                } else {
                    if (edellx == null) {
                        uusi.head = seurx;
                    } else {
                        edellx.sibling = seurx;
                    }

                    link(x, seurx);
                    x = seurx;
                }
            }
            seurx = x.sibling;
        }
        return uusi;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public static void main(String[] args) {
        BinomialHeap keko = new BinomialHeap();
        PriorityQueue<Bnode> jono = new PriorityQueue<Bnode>();
        int alkioita = 1000000;

        long alku = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            keko.insert(i);
        }
        long loppu = System.currentTimeMillis();
        long omaAika = loppu - alku;

        alku = System.currentTimeMillis();
        for (int i = 0; i < alkioita; i++) {
            jono.add(new Bnode(i));
        }
        loppu = System.currentTimeMillis();
        long javanAika = loppu - alku;

        System.out.println("oma aika: " + omaAika);
        System.out.println("javan aika: " + javanAika);


    }

    public void insert(int key) {
        insert(new Bnode(key));
    }
}
