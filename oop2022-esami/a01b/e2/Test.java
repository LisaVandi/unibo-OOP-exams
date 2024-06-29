package a01b.e2;

public class Test {

    /*
     * Scopo di questo esercizio è realizzare una GUI con l'aspetto mostrato nell'immagine fig1.png, fornita:
     * - all'inizio tutti i pulsanti sono "vuoti"
     * - alla pressione di un pulsante, ai quattro pulsanti vicini ad esso in diagonale 
     *   (alto-sx, alto-dx, basso-sx, basso-dx... se presenti in griglia) viene cambiato 
     *   stato: se è vuoto viene messa una '*', se invece c'è una '*' viente tolta
     * - l'applicazione termini quando alla pressione di un pulsante ci sono 3 vicini a cui viene tolta la '*'
     *   e uno a cui viene messa -- quindi non potrà essere un click su una cella del bordo
     * Con riferimento alla figura, clickando sul pulsante di seconda riga e seconda colonna, l'applicazione si chiude. 
     */

    public static void main(String[] args) throws java.io.IOException {
        new GUI(5); 
    }
}
