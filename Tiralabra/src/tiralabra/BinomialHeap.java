/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

public class BinomialHeap {

    private Node head;

    /**
     * Konstruktori
     */
    public BinomialHeap() {
        head = null;
    }

    /**
     * Lisää parametrina annetun solmun kekoon.
     *
     * @param n lisättävä solmu
     */
    private void insert(Node n) {
        BinomialHeap h = new BinomialHeap();
        h.head = n;
        BinomialHeap yhdistetty = this.union(h);
        head = yhdistetty.head;

    }

    /**
     * Palauttaa keon pienimmän solmun avaimen.
     *
     * @param h
     * @return
     */
    public int heapMin() {
        Node x = head;
        Node y = x.sibling;
        while (x != null) {
            if (y.key < x.key) {
                x = y;
            }
        }
        return x.key;
    }

    /**
     * Palauttaa keon pienimmän solmun arvon ja poistaa solmun keosta.
     *
     * @return
     */
    public int extractMin() {
        if (head == null) {
            return -1;
        }
        Node x = head;
        Node y = x.sibling;
        Node edell = x;
        Node edellx = null;

        while (y != null) {
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

        Node z = x.child;
        while (z != null) {
            Node seur = z.sibling;
            z.sibling = uusi.head;
            uusi.head = z;
            z = seur;
        }
        BinomialHeap uusikeko = this.union(uusi);
        head = uusikeko.head;
        return x.value;
    }

    /**
     * Pienentää parametrina annetun solmun avaimen arvon k:ksi, jos k pienempi
     * kuin solmun x avain.
     *
     * @param x
     * @param k
     */
    private void decreaseKey(Node x, int k) {
        int avain = x.key;
        Node z = x;
        while (z.parent != null && z.parent.key > z.key) {
            int temp = z.value;
            z.value = z.parent.value;
            z.parent.value = temp;
            z = z.parent;
        }
    }

    /**
     * Poistaa parametrina annetun solmun keosta
     *
     * @param x
     */
    private void delete(Node x) {
        int min = heapMin();
        decreaseKey(x, min - 1);
        extractMin();
    }

    /**
     * Linkittää kaksi alipuuta toisiinsa.
     *
     * @param x toisen alipuun juuri, josta tulee vanhempi
     * @param y toisen alipuun juuri, josta tulee ensimmäisen lapsi.
     */
    private void link(Node x, Node y) {
        x.parent = y;
        x.sibling = y.child;
        y.child = x;
        y.degree++;
    }

    /**
     * Yhdistaa kekojen h1 ja h2 juurilistat kasvavaan jarjestykseen juurisolmun
     * asteen mukaan.
     *
     * @param h1
     * @param h2
     * @return
     */
    private Node merge(BinomialHeap h1, BinomialHeap h2) {
        if (h1 == null) {
            return h2.head;
        } else if (h2 == null) {
            return h1.head;
        } else {
            Node eka;
            Node vika;
            Node seuraajah1 = h1.head;
            Node seuraajah2 = h2.head;

            if (h1.head.degree <= h2.head.degree) {
                eka = h1.head;
                seuraajah1 = seuraajah1.sibling;
            } else {
                eka = h2.head;
                seuraajah2 = seuraajah2.sibling;
            }
            vika = eka;

            while (seuraajah1 != null && seuraajah2 != null) {
                if (seuraajah1.degree <= seuraajah2.degree) {
                    vika.sibling = seuraajah1;
                    seuraajah1 = seuraajah1.sibling;
                } else {
                    vika.sibling = seuraajah1;
                    seuraajah2 = seuraajah2.sibling;
                }
                vika = vika.sibling;
            }
            if (seuraajah1 != null) {
                vika.sibling = seuraajah1;
            } else {
                vika.sibling = seuraajah2;
            }
            return eka;

        }
    }

    /**
     * Yhdistää tämän keon ja keon h2 yhdeksi keoksi.
     *
     * @param h2 Yhdistettävä keko
     * @return
     */
    public BinomialHeap union(BinomialHeap toinen) {
        BinomialHeap uusi = new BinomialHeap();
        uusi.head = merge(this, toinen);
        if (uusi.head == null) {
            return uusi;
        }
        Node edellx = null;
        Node x = uusi.head;
        Node seurx = x.sibling;

        while (seurx != null) {
            if (x.degree != seurx.degree || 
                    (seurx.sibling != null && seurx.sibling.degree == x.degree)) {
                edellx = x;
                x = seurx;
            } else {
                if (x.key < seurx.key) {
                    x.sibling = seurx.sibling;
                    link(seurx, x);
                } else {
                    if (seurx == null) {
                        uusi.head = seurx;
                    } else {
                        edellx.sibling = seurx;
                    }

                    link(x, seurx);
                }
            }
            seurx = x.sibling;
        }
        return uusi;
    }

    public boolean isEmpty() {
        return head == null;
    }
}

class Node {

    public Node parent;
    public Node child;
    public Node sibling;
    public int key;
    public int value;
    public int degree;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
        child = null;
        sibling = null;
        parent = null;
        degree = 0;

    }
}
