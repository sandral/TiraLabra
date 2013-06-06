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
        this.d = d;
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

    public void insert(int lisattava) {
        if (heapSize == taulukko.getLength()) {
            taulukko.tuplaaJaKopioi();
        }

        heapSize++;
        int indeksi = heapSize - 1;
        int vanhempi = parent(indeksi);
        while (indeksi != 0 && lisattava < parent(indeksi)) {
            taulukko.setLuku(indeksi, taulukko.getLuku(vanhempi));
            indeksi = vanhempi;
            vanhempi = parent(indeksi);
        }
        taulukko.setLuku(indeksi, lisattava);
    }

    private void siftUp(int k) {
    }
    
    public int heapMin() throws DaryHeapException {
        if (isEmpty()) {
            throw new DaryHeapException("Tyhjä keko");
        }
        return taulukko.getLuku(0);
    }
    
public int parent(int indeksi) {
    return (indeksi - 1 / d);
}


    
    

    public static class DaryHeapException extends Exception {

        public DaryHeapException(String tyhjä_keko) {
        }
    }
}        
