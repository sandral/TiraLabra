/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

/**
 *
 * @author root
 */
public class Dkeko {
    private OmaTaulukko taulukko;
    private int heapSize;
    private int d;
    private static int defaultKoko = 10;

    public Dkeko(int size, int d) {
        if (size < 0) {
            size = defaultKoko;
        }
        if (d < 2) {
            throw new IllegalArgumentException("d:n oltava vähintään 2");
        }
        taulukko = new OmaTaulukko(size);
        heapSize = 0;
    }
    
    
}
