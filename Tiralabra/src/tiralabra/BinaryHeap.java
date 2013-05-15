package tiralabra;

import java.util.Arrays;

public class BinaryHeap {
/**
 * Taulukko joka sisältää keon alkiot.
 */
    private int[] taulukko;
/**
* Kertoo kuinka paljon keossa on alkioita. 
*/
    private int heapSize;
/**
 * 
 */
    private int defaultKoko = 10;

    public BinaryHeap(int size) {
        if (size <= 0) {
            size = defaultKoko;
        }
        taulukko = new int[size];
        heapSize = 0;
    }

    public int getDefaultKoko() {
        return defaultKoko;
    }

    public void setDefaultKoko(int defaultKoko) {
        this.defaultKoko = defaultKoko;
    }

    public int[] getTaulukko() {
        return taulukko;
    }

    public void setTaulukko(int[] taulukko) {
        this.taulukko = taulukko;
    }

    public int getHeapSize() {
        return heapSize;
    }

    public void setHeapSize(int heapSize) {
        this.heapSize = heapSize;
    }
/**
 * Tarkistaa on keko tyhjä
 * @return 
 */
    public boolean isEmpty() {
        return heapSize == 0;
    }
/**
 * Lisää parametrina annettavan luvun.
 * @param k lisättävä luku
 */
    public void heapInsert(int k) {
        heapSize++;
        int i = heapSize;
        while (i > 1 && parent(i) < k) {
            taulukko[i] = taulukko[parent(i)];
            i = parent(i);
        }
        taulukko[i] = k;
    }

    public int heapMin() throws HeapException {
        if (isEmpty()) {
            throw new HeapException("tyhjä Keko");
        } else {
            return taulukko[0];
        }
    }

    public int heapDelMin() throws HeapException {
        if (isEmpty()) {
            throw new HeapException("tyhjä Keko");
        }
        int pienin = taulukko[0];
        taulukko[0] = taulukko[getHeapSize()];
        heapSize--;
        heapify(0);
        return pienin;
    }

    public int left(int i) {
        return 2 * (i + 1);
    }

    public int right(int i) {
        return 2 * (i + 1) + 1;
    }

    public int parent(int i) {
        return i / 2 - 1;
    }

    public void heapify(int i) {
        int l = left(i);
        int r = right(i);
        int suurin = 0;
        if (r < heapSize) {
            if (taulukko[l] > taulukko[r]) {
                suurin = l;
            }
            else {
                suurin = r;
            }
            if (taulukko[i] < taulukko[suurin]) {
                
            }
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
    }

    private static class HeapException extends Exception {

        public HeapException(String keko_on_tyhjä) {
        }
    }
}
