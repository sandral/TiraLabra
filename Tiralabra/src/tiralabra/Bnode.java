/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

/**
 * Solmu binomikekoa varten.
 * @author root
 */
public class Bnode implements Comparable {

    public Bnode parent;
    public Bnode child;
    public Bnode sibling;
    public int key;
    public int degree;

    public Bnode(int key) {
        this.key = key;
        child = null;
        sibling = null;
        parent = null;
        degree = 0;
        
    }
    
    @Override
    public int compareTo(Object toinen) {
        Bnode verrattava = (Bnode) toinen;
        if (verrattava.key == this.key) {
            return 0;
        }
        else {
            return this.key - verrattava.key;
        }
    }
}

