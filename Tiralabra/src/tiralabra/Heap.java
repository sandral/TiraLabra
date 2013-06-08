/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

/**
 *
 * @author root
 */
public interface Heap {
    
    public void insert(int key);
    public int min() throws Exception;
    public int deleteMin() throws Exception;
    public boolean isEmpty();
}
