/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

/**
 * Solmu binomikekoa varten.
 * @author root
 */
public class Node implements Comparable {

    public Node parent;
    public Node child;
    public Node sibling;
    public int key;
    public int degree;

    public Node(int key) {
        this.key = key;
        child = null;
        sibling = null;
        parent = null;
        degree = 0;
        
    }
    
    @Override
    public int compareTo(Object toinen) {
        Node verrattava = (Node) toinen;
        if (verrattava.key == this.key) {
            return 0;
        }
        else {
            return this.key - verrattava.key;
        }
    }
}

