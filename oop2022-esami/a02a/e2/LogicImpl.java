package a02a.e2;

import java.util.HashSet;
import java.util.Set;

public class LogicImpl implements Logic {

    private final int size;

    public LogicImpl(final int size) {
        this.size = size;
    }

    Set<Coord> selectedCells = new HashSet<>();
    Set<Coord> bishops = new HashSet<>();

    @Override
    public boolean putBishop(Coord coord) {
        if (!selectedCells.contains(coord)) {
            selectedCells.add(coord);
            bishops.add(coord);
            disableDiagonal(coord);
            return true;
        }
        return false;
    }

    @Override
    public void disableDiagonal(Coord coord) {
        int[] deltas = {-1, 1};
        for (int deltaRow : deltas) {
            for (int deltaCol : deltas) {
                int row = coord.row() + deltaRow;
                int col = coord.col() + deltaCol;
                while (row >= 0 && row < size && col >= 0 && col < size) {
                    selectedCells.add(new Coord(row, col));
                    row += deltaRow;
                    col += deltaCol;
                }
            }
        }    
    
    }

    @Override
    public boolean reset() {
        /*
         * quando non c'è più nessuna "B" piazzabile, perché è tutto disabilitato, allora premendo una "B" l'applicazione riparta
         * dall'inizio, ossia con tutti i pulsanti vuoti e abilitati, pronti per una nuova esecuzione
         */
        selectedCells.clear();
        bishops.clear();
        return true;
    }

    @Override
    public Set<Coord> getSelectedCells() {
        return selectedCells;    
    }

    @Override
    public Set<Coord> getBishops() {
        return bishops; 
    }
}
