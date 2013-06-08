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
        if (isEmpty()) {
            taulukko.setLuku(0, lisattava);
            heapSize++;
        } else {
            heapSize++;

            int indeksi = heapSize - 1;
            int vanhempi = taulukko.getLuku(parent(indeksi));
            while (indeksi != 0 && lisattava < vanhempi) {
                taulukko.setLuku(indeksi, taulukko.getLuku(vanhempi));
                indeksi = vanhempi;
                vanhempi = parent(indeksi);
            }
            taulukko.setLuku(indeksi, lisattava);
        }
    }

    public int heapMin() throws DaryHeapException {
        if (isEmpty()) {
            throw new DaryHeapException("Tyhjä keko");
        }
        return taulukko.getLuku(0);
    }

    public int parent(int indeksi) {
        return (indeksi - 1) / d;
    }

    /**
     * Palauttaa listan parametrina annetun indeksin lapsista.
     *
     * @param indeksi
     * @return
     */
    public int[] children(int indeksi) {
        int[] lapset = new int[d];
        for (int i = 1;  i <= d;  i++) {
            lapset[i - 1] = indeksi * d + i;
        }
        return lapset;
    }

    public int deleteMin() throws DaryHeapException {
        if (isEmpty()) {
            throw new DaryHeapException("keko on tyhja");
        }
        int pienin = heapMin();
        taulukko.setLuku(0, taulukko.getLuku(heapSize - 1));
        heapSize--;
        kuljetaAlas(0);

        return pienin;
    }
    private void vaihdaKeskenaan(int i, int j) {
         int x = taulukko.getLuku(i);
        taulukko.setLuku(i, taulukko.getLuku(j));
        taulukko.setLuku(j, x);
    }
    
    private void kuljetaAlas(int i) {
        int pienin = taulukko.getLuku(i);
        while (i < heapSize) {
            int pieninLapsi = i;
            int[] lapset = children(i);
            for (int j = 0; j < lapset.length; j++) {
                if (lapset[j] < heapSize && taulukko.getLuku(pieninLapsi) > taulukko.getLuku(lapset[j])) {
                    pieninLapsi = lapset[j];
                }
            }
            vaihdaKeskenaan(pieninLapsi, i);
            i++;
        }
        
    }

    public static class DaryHeapException extends Exception {

        public DaryHeapException(String tyhjä_keko) {
        }
    }
}
