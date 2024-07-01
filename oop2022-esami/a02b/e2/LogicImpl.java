package a02b.e2;

import java.util.HashSet;
import java.util.Set;

public class LogicImpl implements Logic {
    private final int size; 
    private Set<Coord> selectedCells = new HashSet<>();
    private Set<Coord> lastThree = new HashSet<>();

    public LogicImpl(final int size) {
        this.size = size;
    }

    @Override
    public boolean star(Coord coord) {
        if (!selectedCells.contains(coord)) {
            selectedCells.add(coord);
            lastThree.add(coord);
            return true;
        } 
        return false;

    }

    @Override
    public boolean unstar(Coord coord) {
        if (selectedCells.contains(coord)) {
            selectedCells.remove(coord);
            return true;
        }
        return false;
    }

    @Override
    public boolean checkThreeStarsDiagonal(Coord c1, Coord c2, Coord c3) {
        return isOnSameDiagonal(c1, c2, c3);    
    }

    private boolean isOnSameDiagonal(Coord c1, Coord c2, Coord c3) {
        return (
            Math.abs(c1.row() - c2.row()) == Math.abs(c2.row() - c3.row()) &&
            Math.abs(c1.col() - c2.col()) == Math.abs(c2.col() - c3.col())
        );
    }

    @Override
    public boolean isOver() {
        return false;

    }

    @Override
    public void reset() {
       selectedCells.clear();
       lastThree.clear();
    }

    @Override
    public Set<Coord> getSelectedCells() {
        return this.selectedCells;
    }



}
