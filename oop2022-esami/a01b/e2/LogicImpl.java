package a01b.e2;

import java.util.HashSet;
import java.util.Set;

public class LogicImpl implements Logic {
    private final int size;
    private Set<Coord> selectedCells = new HashSet<>();
    private Set<Coord> lastThreeRemoved = new HashSet<>(); 
    private Set<Coord> lastAdded = new HashSet<>();

    public LogicImpl(final int size) {
        this.size = size;
    }

    @Override
    public boolean star(Coord coord) {
        if (!selectedCells.contains(coord)) {
            selectedCells.add(coord);
            lastAdded.add(coord);
            return true;
        }
        return false;
    }

    @Override
    public boolean unstar(Coord coord) {
        if (selectedCells.contains(coord)) {
            selectedCells.remove(coord);
            lastThreeRemoved.add(coord);
            return true;
        }
        return false;
    }

    
    @Override
    public void neighbours(Coord coord) {
        int[][] deltas = { {-1, -1}, {-1, 1}, {1, -1}, {1, 1} };
        for (int[] delta : deltas) {
            int newRow = coord.row() + delta[0];
            int newCol = coord.col() + delta[1];

            //controllo se sono nei bordi
            if (newRow >= 0 && newRow < size && newCol >= 0 && newCol < size) {
                Coord neighbor = new Coord(newRow, newCol);
                if (selectedCells.contains(neighbor)) {
                    unstar(neighbor);
                } else {
                    star(neighbor);
                }
            }
        }
    }

    @Override
    public boolean isOver() {
        return lastThreeRemoved.size() == 3 && lastAdded.size() == 1;
    }
}
