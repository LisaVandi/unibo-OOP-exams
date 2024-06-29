package a02a.e2;

import java.util.HashSet;
import java.util.Set;

public class LogicImpl implements Logic {

    private final int size;
    private Set<Coord> selectedCells = new HashSet<>();
    private Set<Coord> disabledCells = new HashSet<>();
    private Set<Coord> bishops = new HashSet<>();

    public LogicImpl(final int size) {
        this.size = size;
        this.clear();
    }

    @Override
    public boolean hit(Coord coord) {
        if(selectedCells.contains(coord) || disabledCells.contains(coord)) {
            return false;
        }
        bishops.add(coord);
        selectedCells.add(coord);
        return true;
    }

    @Override
    public void disableDiagonal(Coord coord) {
        for (int i = 1; i < size; i++) {
            disabledCells.add(new Coord(coord.row() + i, coord.col() + i)); // riga e colonna + 1 
            disabledCells.add(new Coord(coord.row() - i, coord.col() - i)); // riga e colonna - 1
            disabledCells.add(new Coord(coord.row() + i, coord.col() - i));  
            disabledCells.add(new Coord(coord.row() - i, coord.col() + i));
        }    
    }

    @Override
    public boolean isOver() {
        return size == bishops.size();
        
    }

    @Override
    public boolean isBishop(Coord coord) {
        return bishops.contains(coord);
    }

    @Override
    public void clear() {
        selectedCells = new HashSet<>();
        bishops = new HashSet<>();
        disabledCells = new HashSet<>();
    }

    @Override
    public boolean isAvailable(Coord coord) {
        return !disabledCells.contains(coord);
    }
}
