/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

/**
 *
 * @author root
 */
public class Node {

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

