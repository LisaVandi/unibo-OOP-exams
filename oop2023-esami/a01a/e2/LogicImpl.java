package a01a.e2;

import java.util.HashMap;
import java.util.Map;

public class LogicImpl implements Logic {

    private final Map<Coord, Integer> selectedCells = new HashMap<>();
    private final int size; 
    private int clickCounter = 0;

    public LogicImpl(final int size) {
        this.size = size;
    }
    @Override
    public boolean hasClicked(Coord cell) {
        return selectedCells.containsKey(cell);
    }
    
    @Override
    public int getCellNumber(Coord cell) {
        return selectedCells.getOrDefault(cell, -1);
    }

    @Override
    public void setCellNumber(Coord cell) {
        selectedCells.put(cell, clickCounter++);
    }

    @Override
    public boolean isAdjWithNumber(Coord coord) { 
        for (Coord c : selectedCells.keySet()) {
            if (Math.abs(c.row() - coord.row()) <= 1 && Math.abs(c.col() - coord.col()) <= 1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void movement() {
        Map<Coord, Integer> newPositions = new HashMap<>();
        for (var entry : selectedCells.entrySet()) {
            Coord newCoord = new Coord(entry.getKey().row() - 1, entry.getKey().col() - 1);
            newPositions.put(newCoord, entry.getValue());
        }
        selectedCells.clear();
        selectedCells.putAll(newPositions);
    }

    @Override
    public void exit() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'exit'");
    }

    @Override
    public boolean isOutOfBounds() {
        for (Coord coord : selectedCells.keySet()) {
            if (coord.row() < 0 || coord.col() < 0 || coord.row() >= size || coord.col() >= size) {
                return true;
            }
        }
        return false;    
    }
    
}
