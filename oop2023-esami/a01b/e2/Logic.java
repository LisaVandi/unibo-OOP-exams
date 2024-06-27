package a01b.e2;

import java.util.Map;

public interface Logic {
    /*    
    * 1 - all'inizio l'utente clicka su 5 celle (diverse tra loro), che si numerano incrementalmente (fig 1)
    * 2 - alla sesta pressione di un pulsante qualsiasi, tutte le celle si spostano a sinistra di una posizione (fig 2)
    * 3 - si procede come sopra finchè una cella numerata non finisce nel bordo (fig 3)...
    * 4 - a quel punto ogni altra successiva pressione di qualunque cella porta le celle a spostarsi a destra di una posizione (fig 4, poi 5...)
    * 5 - non appena una pressione causerebbe l'uscita dalla griglia di una cella numerata (fig 6), l'applicazione si chiuda
    */
    boolean isClicked(Coord coord);
    int getNumberOfClick();
    void leftMovement();
    boolean isBound();
    void rigthMovement();
    void clickCell(Coord coord);
    Map<Coord, Integer> getClickedCells();
}
