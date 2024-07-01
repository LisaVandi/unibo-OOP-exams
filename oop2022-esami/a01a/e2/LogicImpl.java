package a01a.e2;

import java.util.HashSet;
import java.util.Set;

public class LogicImpl implements Logic {
    private final int size;

    public LogicImpl(final int size) {
        this.size = size;
    }

    Set<Coord> selectedCells = new HashSet<>();
    Set<Coord> lastThreeStars = new HashSet<>();

    @Override
    public boolean star(Coord coord) {
        if (!selectedCells.contains(coord)) {
            selectedCells.add(coord);
            lastThreeStars.add(coord);
            return true;
        }
        return false;
    }

    @Override
    public boolean unstar(Coord coord) {
        if (selectedCells.contains(coord)) {
            selectedCells.remove(coord);
            lastThreeStars.remove(coord);
            return true;
        }
        return false;
    }

    @Override
    
    public boolean isDiagonal(Coord c1, Coord c2, Coord c3) {
        return (
            Math.abs(c1.row() - c2.row()) == Math.abs(c2.row() - c3.row()) && 
            Math.abs(c1.col() - c2.col()) == Math.abs(c2.col() - c3.col())
        );
    }

    @Override
    /* l'applicazione termini quando valgono entrambe le condizioni:
     *      1) le ultime 3 pressioni hanno messo una '*'
     *      2) le ultime 3 pressioni sono relative ad una fila di 3 pulsanti consecutivi in diagonale
     * */
    public boolean isOver() {
        if (lastThreeStars.size() == 3) {
            var coords = lastThreeStars.toArray(new Coord[0]);
            if (isDiagonal(coords[0], coords[1], coords[2])) {
                return true;
            }
        } 
        return false;
    }
}
