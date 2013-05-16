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
     * @param n lisättävä solmu
     */
    public void insert(Node n) {
        BinomialHeap h = new BinomialHeap();
        h.head = n;
        n.parent = null;
        n.child = null;
        n.sibling = null;
        n.degree = 0;
        h.head = n;
        
        //Sitten union(h)
    }
    
    /**
     * Palauttaa keon pienimmän solmun avaimen.
     * @param h
     * @return 
     */
    public int minimum(BinomialHeap h) {
        return 0;
    }
    
    /**
     * Palauttaa keon pienimmän solmun avaimen ja poistaa solmun keosta.
     * @return 
     */
    public int delMinimum() { 
        return 0;
    }
    
    /**
     * Pienentää parametrina annetun solmun avaimen arvon k:ksi, jos
     * k pienempi kuin solmun x avain.
     * @param x 
     * @param k 
     */
    public void decreaseKey(Node x, int k) {
    }
    
    
    /**
     * Poistaa parametrina annetun solmun keosta
     * @param x 
     */
    public void delete(Node x) {
    }
    
    /**
     * Linkittää kaksi alipuuta toisiinsa.
     * @param x toisen alipuun juuri, josta tulee vanhempi
     * @param y toisen alipuun juuri, josta tulee ensimmäisen lapsi.
     */
    public void link(Node x, Node y) { 
        x.parent = y;
        x.sibling = y.child;
        y.child = x;
        y.degree++;
    }
    
    /**
     * 
     * @param h1
     * @param h2
     * @return 
     */
    public BinomialHeap merge(BinomialHeap h1, BinomialHeap h2) {
        return null;
    }
    /**
     * Yhdistää tämän keon ja keon h2 yhdeksi keoksi.
     * @param h2 Yhdistettävä keko
     * @return 
     */
    public BinomialHeap union(BinomialHeap h2) {
        
        return null; 
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
   
    
