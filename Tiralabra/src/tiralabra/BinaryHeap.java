package tiralabra;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;
import tiralabra.OmaTaulukko;

public class BinaryHeap {

    /**
     * Taulukko joka sisältää keon alkiot.
     */
    private OmaTaulukko taulukko;
    /**
     * Kertoo kuinka paljon keossa on alkioita.
     */
    private int heapSize;
    /**
     * Asettaa taulukon kooksi 10, jos käyttäjän antama koko on negatiivinen.
     */
    private static int defaultKoko = 10;

    /**
     * Konstruktori defaultKoko
     *
     * @param size Luo keon, johon mahtuu näin monta alkiota.
     */
    public BinaryHeap(int size) {
        if (size <= 0) {
            size = defaultKoko;
        }
        taulukko = new OmaTaulukko(size);
        heapSize = 0;
    }

    public int getDefaultKoko() {
        return defaultKoko;
    }

    public void setDefaultKoko(int defaultKoko) {
        this.defaultKoko = defaultKoko;
    }

    public OmaTaulukko getTaulukko() {
        return taulukko;
    }

    public void setTaulukko(OmaTaulukko taulukko) {
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
     *
     * @return
     */
    public boolean isEmpty() {
        return heapSize == 0;
    }

    /**
     * Lisää parametrina annettavan luvun kekoon.
     *
     * @param k lisättävä luku
     */
    public void heapInsert(int k) {
        if (heapSize == taulukko.getLength()) {
            taulukko.tuplaaJaKopioi();
        }
        heapSize++;
        taulukko.setLuku(heapSize - 1, k);
        siftUp(heapSize - 1);
    }

    private void siftUp(int k) {
        int vanhempi;
        if (k != 0) {
            vanhempi = parent(k);
            if (taulukko.getLuku(vanhempi) > taulukko.getLuku(k)) {
                swap(vanhempi, k);
                siftUp(vanhempi);

            }

        }
    }

    /**
     * Palauttaa keon pienimmän alkion.
     *
     * @return
     * @throws tiralabra.BinaryHeap.HeapException ilmoittaa virheestä, jos keko
     * on tyhjä.
     */
    public int heapMin() throws HeapException {
        if (isEmpty()) {
            throw new HeapException("tyhjä Keko");
        } else {
            return taulukko.getLuku(0);
        }
    }

    /**
     * Palauttaa keon pienimmän alkion ja poistaa sen keosta
     *
     * @return
     * @throws tiralabra.BinaryHeap.HeapException ilmoittaa virheestä, jos keko
     * on tyhjä.
     */
    public int heapDelMin() throws HeapException {
        if (isEmpty()) {
            throw new HeapException("tyhjä Keko");
        }
        int pienin = taulukko.getLuku(0);
        taulukko.setLuku(0, taulukko.getLuku(getHeapSize() - 1));
        heapSize--;
        heapify(0);
        return pienin;
    }

    /**
     * Palauttaa parametrina annetun indeksin kohdalla olevan solmun vasemman
     * lapsen indeksin.
     *
     * @param i
     * @return
     */
    public int left(int i) {
        return 2 * i + 1;
    }

    /**
     * Palauttaa paratmerina annetun indeksin kohdalla olevan solmun oikean
     * lapsen indeksin.
     *
     * @param i
     * @return
     */
    public int right(int i) {
        return 2 * (i + 1);
    }

    /**
     * Palauttaa parametrina annetun indeksin kohdalla olevan solmun vanhemman
     * indeksin.
     *
     * @param i
     * @return
     */
    public int parent(int i) {
        return (i - 1) / 2;
    }

    /**
     * Korjaa kekorakenteen, jos se on indeksin i kohdalta rikki.
     *
     * @param i
     */
    public void heapify(int i) {
        int l = left(i);
        int r = right(i);
        int pienin;
        if (r < heapSize) {
            if (taulukko.getLuku(l) < taulukko.getLuku(r)) {
                pienin = l;
            } else {
                pienin = r;
            }
            if (taulukko.getLuku(i) > taulukko.getLuku(pienin)) {
                swap(i, pienin);
                heapify(pienin);
            }

        } else if (l == heapSize - 1 && taulukko.getLuku(i) > taulukko.getLuku(l)) {
            swap(i, l);
        }
    }

    /**
     * Yhdistaa binaarikeon parametrina annettavan toisen kanssa yhdeksi keoksi.
     *
     * @param toinen Yhdistettava keko.
     */
    public void merge(BinaryHeap toinen) throws HeapException {
        while (taulukko.getLength() <= heapSize + toinen.heapSize) {
            taulukko.tuplaaJaKopioi();
        }
        int aloitusKohta = heapSize;
        heapSize = heapSize + toinen.heapSize;
        for (int i = aloitusKohta; i < heapSize; i++) {
            taulukko.setLuku(i, toinen.getTaulukko().getLuku(i - aloitusKohta));
        }
        buildHeap(taulukko);
    }

    /**
     * Tekee taulukkoon kekorakenteen.
     *
     * @param taulukko keonnettava taulukko.
     */
    public void buildHeap(OmaTaulukko taulukko) {
        for (int i = taulukko.getLength() / 2; i >= 0; i--) {
            heapify(i);
        }
    }

    /**
     * Vaihtaa taulukon kahden alkioiden paikkoja keskenään.
     *
     * @param i
     * @param j
     */
    private void swap(int i, int j) {
        int x = taulukko.getLuku(i);
        taulukko.setLuku(i, taulukko.getLuku(j));
        taulukko.setLuku(j, x);
    }

    public static void main(String[] args) throws HeapException {
        BinaryHeap keko = new BinaryHeap(100000000);
        BinaryHeap toinen = new BinaryHeap(10);
        int alkioita = 10000000;
        PriorityQueue<Integer> jono = new PriorityQueue<Integer>();
        Random generaattori = new Random(); 

        

        long alku = System.currentTimeMillis();        
        for (int i = alkioita - 1; i >= 0; i--) {
            keko.heapInsert(generaattori.nextInt(alkioita));
        }
        long loppu = System.currentTimeMillis();
        
        for (int i = 0; i < alkioita; i++) {
            keko.heapDelMin();
        }
        long omaAika = loppu - alku;
        
        alku = System.currentTimeMillis();        
        for (int i = alkioita - 1; i >= 0; i--) {
            jono.add(generaattori.nextInt(alkioita));
        }
        loppu = System.currentTimeMillis();
        
        for (int i = 0; i < alkioita; i++) {
            jono.poll();
        }
        

        
        long javanAika = loppu - alku;

        System.out.println("oma aika: " + omaAika);
        System.out.println("javan aika: " + javanAika);
        
        /*
        for (int i = 3; i > 0; i--) {
            keko.heapInsert(i);
        }
        
        for (int i = 0; i < 3; i++) {
            System.out.print(keko.taulukko.getLuku(i) + " ");
        }
        /*
        /*
        for (int i = 15; i > 0; i--) {
            keko.heapInsert(i);

            for (int j = 0; j < 15 - i; j++) {
                System.out.print(keko.taulukko.getLuku(j) + " ");
            }
            System.out.println();

        }

        for (int i = 0; i < 15; i++) {
            System.out.print(keko.taulukko.getLuku(i) + " vasen: " + keko.taulukko.getLuku(keko.left(i)) + " oikea: " + keko.taulukko.getLuku(keko.right(i)));
            System.out.println();
        }

        System.out.println();

        while (!keko.isEmpty()) {
            System.out.print(keko.heapDelMin() + " ");

        }
        */ 
        /*
         for (int i = 5; i < 8; i++) {
         keko.heapInsert(i);
         }
         for (int i = 0; i < 5; i++) {
         toinen.heapInsert(i);
         }
         for (int i = 0; i < 4; i++) {
         System.out.print(keko.taulukko.getLuku(i));
         }
         System.out.println();
         for (int i = 0; i < 5; i++) {
         System.out.print(toinen.taulukko.getLuku(i));
         }
         System.out.println();


         keko.merge(toinen);
         System.out.println("koko nyt: " + keko.heapSize);

         for (int i = 0; i < keko.getTaulukko().getLength(); i++) {
         System.out.print(keko.getTaulukko().getLuku(i));
            

         }
         */
        /*BinaryHeap keko = new BinaryHeap(5);
         BinaryHeap keko1 = new BinaryHeap(20);
         for (int i = 1; i < 16; i++) {
         System.out.println("taulukon koko: " + keko.taulukko.getLength());
         keko.heapInsert(i);
         keko1.heapInsert(16 - i);
         }
         for (int i = 0; i < 15; i++) {
         System.out.print(keko.getTaulukko().getLuku(i) + " ");
         }
         System.out.println();
         for (int i = 0; i < 15; i++) {
         System.out.print(keko1.getTaulukko().getLuku(i) + " ");
         */
    }

    public static class HeapException extends Exception {

        public HeapException(String keko_on_tyhja) {
        }
    }
}
