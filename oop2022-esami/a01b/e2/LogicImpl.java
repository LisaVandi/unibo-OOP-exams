package a01b.e2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class LogicImpl implements Logic {
    
    private final int size;
    private Set<Coord> clickedCells = new HashSet<>(); 
    private List<Coord> lastThreeRemoved = new LinkedList<>();

    public LogicImpl(final int size) {
        this.size = size;
    }

    @Override
    public boolean isEmpty(Coord coord) {
        return !isClicked(coord);
    }

    @Override
    public void star(Coord coord) {
        clickedCells.add(coord);
        lastThreeRemoved.remove(coord);
    }

    @Override
    public void unstar(Coord coord) {
        clickedCells.add(coord);
        lastThreeRemoved.add(coord);
        if (lastThreeRemoved.size() > 3) {
            lastThreeRemoved.remove(0);
        }
    }

    @Override
    public boolean isOver() {
        return lastThreeRemoved.size() == 3;     
    }

    @Override
    public boolean isClicked(Coord coord) {
        return clickedCells.contains(coord);
    }

    @Override
    public Set<Coord> getClickedCells() {
        return clickedCells;
    }

    @Override
    public List<Coord> getDiagonalCoords(Coord coord) {
        List<Coord> diagonals = new ArrayList<>();
        int[][] directions = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

        for (int[] dir : directions) {
            int x = coord.row() + dir[0];
            int y = coord.col() + dir[1];
            if (x >= 0 && x < size && y >= 0 && y < size) {
                diagonals.add(new Coord(x, y));
            }
        }

        return diagonals;
    }
      
}
