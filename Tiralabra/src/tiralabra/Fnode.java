/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

/**
 *
 * @author root
 */
public class Fnode {

    public Fnode child;
    public Fnode parent;
    public Fnode left;
    public Fnode right;
    public int key;
    public int value;
    public int degree;
    public boolean mark;

    public Fnode(int value, int key) {
        right = this;
        left = this;
        this.key = key;
        this.value = value;
        mark = false;
        degree = 0;
    }
}

