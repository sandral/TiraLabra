/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;




public class BinomialHeap {
    private Node head;

    public BinomialHeap() {
        head = null; 
    }
    
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
    
    public int minimum(BinomialHeap h) { //palauttaa keon pienimmän
        return 0;
    }
    
    public int delMinimum() { //palauttaa ja poistaa keon pienimmän
        return 0;
    }
    
    public void decreaseKey(Node x, int k) {
        //pienentää solmun x avaimen arvon k:ksi, jos k < x.key
    }
    
    public void delete(Node x) {
        //poistaa solmun x keosta;
    }
    
    public void link(Node x, Node y) { //linkittää kaksi solmua
    
    }
    
    public BinomialHeap merge(BinomialHeap h1, BinomialHeap h2) {
        return null;
    }
    
    public BinomialHeap union(BinomialHeap h2) {
        
        return null; //yhdistää kekoon toisen keon h2 ja tuhoaa alkuperäiset keot.
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
   
    
