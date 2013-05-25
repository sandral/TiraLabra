/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

/**
 *
 * @author root
 */
class Fnode {

    Fnode child;
    Fnode parent;
    Fnode left;
    Fnode right;
    int key;
    int value;
    int degree;
    boolean mark;

    public Fnode(int value, int key) {
        right = this;
        left = this;
        this.key = key;
        this.value = value;
        mark = false;
        degree = 0;
    }
}

