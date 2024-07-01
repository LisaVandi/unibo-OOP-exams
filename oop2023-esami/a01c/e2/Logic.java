package a01c.e2;

import java.util.List;

public interface Logic {
    /**
     * 1 - con i primi due click (su celle diverse), si individuano due celle numerate 1 e 2
     * 2 - effettuato un terzo click (su qualunque cella), tutte le celle del rettangolo individuato dai vertici opposti 1 e 2 vengono numerati 0 (fig2)
     * 3 - al quarto click, vengo numerate 0 anche le celle confinanti con l'attuale rettangolo, e così via ad ogni click
     * 4 - non appena un click porterebbe a coprire tutta la griglia (fig3), l'applicazione si chiuda
     */
    boolean isClicked();
    List<Coord> selectCell(Coord cell);
}
