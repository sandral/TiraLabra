/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

import java.util.PriorityQueue;
import tiralabra.Node;

public class BinomialHeap {

    private Node head;

    /**
     * Konstruktori
     */
    public BinomialHeap() {
        head = null;
    }

    public Node getHead() {
        return head;
    }

    /**
     * Lisää parametrina annetun solmun kekoon.
     *
     * @param n lisättävä solmu
     */
    public void insert(Node n) {
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
     *
     * @param h
     * @return
     */
    public int heapMin() {
        Node x = head;
        Node y = x.sibling;
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
     * @return
     */
    public int extractMin() {
        if (this.isEmpty()) {
            return -1;
        }
        if (head.sibling == null && head.degree == 0) {
            int avain = head.key;
            head = null;
            return avain;
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
        return x.key;
    }

    /**
     * Pienentää parametrina annetun solmun avaimen arvon k:ksi, jos k pienempi
     * kuin solmun x avain.
     *
     * @param x
     * @param k
     */
    public void decreaseKey(Node x, int k) {
        if (x.degree == 0 && k < x.key) {
            x.key = k;
        } else if (k < x.key) {
            int avain = x.key;
            Node z = x;
            while (z.parent != null && z.parent.key > z.key) {
                int temp = z.value;
                z.value = z.parent.value;
                z.parent.value = temp;
                z = z.parent;
            }
        }
    }

    /**
     * Poistaa parametrina annetun solmun keosta
     *
     * @param x
     */
    public void delete(Node x) {
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
     * asteen mukaan ja palauttaa uuden juurilistan pään.
     *
     * @param h1
     * @param h2
     * @return
     */
    public Node merge(BinomialHeap h1, BinomialHeap h2) {
        Node n1 = h1.head;
        Node n2 = h2.head;

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

        while (n2 != null) {
            if (n1.sibling == null) {
                n1.sibling = n2;
                n2 = n2.sibling;
            } else if (n1.sibling.degree < n2.degree) {
                n1 = n1.sibling;
            } else {
                Node n3 = n2.sibling;
                if (n2 == n1.sibling) {
                    n2.sibling = null;
                } 
                else 
                    n2.sibling = n1.sibling;
                    n1.sibling = n2;
                    n2 = n3;
                
            }
        }
        return h1.head;
    }

   
    /**
     * Yhdistää tämän keon ja keon h2 yhdeksi keoksi.
     *
     * @param h2 Yhdistettävä keko }
     * @return
     */
    public BinomialHeap union(BinomialHeap toinen) {
        BinomialHeap uusi = new BinomialHeap();
        uusi.head = merge(this, toinen);
        if (uusi.isEmpty()) {
            return this;
        }
        Node edellx = null;
        Node x = uusi.head;
        Node seurx = x.sibling;

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
        PriorityQueue<Node> jono = new PriorityQueue<Node>();
        int alkioita = 1000000;
        
        long alku = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            keko.insert(new Node(i,0));
        }
        long loppu = System.currentTimeMillis();
        long omaAika = loppu - alku;
        
        alku = System.currentTimeMillis();
        for (int i = 0; i < alkioita; i++) {
            jono.add(new Node(i, 0));
        }
        loppu = System.currentTimeMillis();
        long javanAika = loppu - alku;
        
        System.out.println("oma aika: " + omaAika);
        System.out.println("javan aika: " + javanAika);
        
       
    }
}
