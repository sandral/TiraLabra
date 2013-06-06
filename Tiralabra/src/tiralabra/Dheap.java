/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

/**
 *
 * @author root
 */
public class Dheap {

    private OmaTaulukko taulukko;
    private int heapSize;
    private int d;
    private static int defaultKoko = 10;

    public Dheap(int size, int d) {
        if (size < 0) {
            size = defaultKoko;
        }
        if (d < 2) {
            throw new IllegalArgumentException("d:n oltava vähintään 2");
        }
        taulukko = new OmaTaulukko(size);
        heapSize = 0;
    }

    public OmaTaulukko getTaulukko() {
        return taulukko;
    }

    public int getHeapSize() {
        return heapSize;
    }

    public int getD() {
        return d;
    }

    public static int getDefaultKoko() {
        return defaultKoko;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public void Insert(int lisattava) {
        if (heapSize == taulukko.getLength()) {
            taulukko.tuplaaJaKopioi();
        }

        heapSize++;
        taulukko.setLuku(heapSize - 1, lisattava);
        siftUp(heapSize - 1);
    }

    private void siftUp(int k) {
    }
    
    public int heapMin() throws DaryHeapException {
        if (isEmpty()) {
            throw new DaryHeapException("Tyhjä keko");
        }
        return taulukko.getLuku(0);
    }

    private static class DaryHeapException extends Exception {

        public DaryHeapException(String tyhjä_keko) {
        }
    }
}        
