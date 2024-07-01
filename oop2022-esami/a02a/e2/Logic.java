package a02a.e2;

import java.util.Set;

public interface Logic {
    /*
     * - all'inizio tutti i pulsanti sono "vuoti"
     * - alla pressione di un pulsante, ci si scriva una "B" (bishop), e si disabilitino tutti pulsanti a lui in diagonale (vedi fig.1)
     * - se si preme un pulsante che già presenta una "B" non dve succedere nulla
     * - si proceda iterativamente piazzando le varie "B" e disabilitando le diagonali (in fig.2 si è piazzata una nuova "B")
     * - quando non c'è più nessuna "B" piazzabile, perché è tutto disabilitato, allora premendo una "B" l'applicazione riparta
     *   dall'inizio, ossia con tutti i pulsanti vuoti e abilitati, pronti per una nuova esecuzione
     */

    boolean putBishop(Coord coord);
    void disableDiagonal(Coord coord);
    boolean reset();
    Set<Coord> getSelectedCells();
    Set<Coord> getBishops();
}