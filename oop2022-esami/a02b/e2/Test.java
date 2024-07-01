package a02b.e2;

public class Test {

    /*
     * L'applicazione si comporti complessivamente come segue:
     * - all'inizio tutti i pulsanti sono "vuoti"
     * - alla pressione di un pulsante, se è vuoto viene messa una '*'', se invece c'è una '*' viene tolta
     * - alla prima pressione del pulsante "Check > Restart", si controlli se 3 (non più di 3) celle selezionate
     *   stanno sulla stessa diagonale (alto-sx/basso-dx, come in fig 1)
     *      - se non è così, allora si ignori la pressione di "Check > Restart"
     *      - se se ne trova una (di linea diagonale), allora si disabilitino tutte le celle di quella linea diagonale (come in fig 1)
     *      - se ce ne fosse più d'una (di linea diagonale), se ne disabilitino le cell di una scelta arbitrariamente
     * - in caso si siano disabilitate delle celle, alla prossima pressione di "Check > Restart" l'applicazione riparta
     *   dall'inizio, ossia con tutti i pulsanti vuoti e abilitati, pronti per una nuova esecuzione
     */

    public static void main(String[] args) throws java.io.IOException {
        new GUI(7); 
    }
}
