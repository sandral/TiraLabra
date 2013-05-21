/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

/**
 *
 * @author root
 */
public class FibonacciHeap {

    private Fnode min;
    private int count; //solmujen lkm

    public FibonacciHeap(Fnode min, int count) {
        min = null;
        count = 0;
    }

    public void insert(Fnode n) {
        if (min != null) {
            n.left = min;
            n.right = min.right;
            min.right = n;
            n.right.left = n;

            if (n.key < min.key) {
                min = n;
            }
        } else {
            min = n;
        }
        count++;
    }
    
    

    public Fnode heapMin() {
        return min;
    }
}

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
