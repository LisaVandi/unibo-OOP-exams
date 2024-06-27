package a01b.e2;

import java.util.LinkedHashMap;
import java.util.Map;

public class LogicImpl implements Logic {

    private final int size;
    private final Map<Coord, Integer> clickedCells;
    private int numberOfClick;
    private boolean movingRight;

    public LogicImpl(int size) {
        this.size = size;
        this.clickedCells = new LinkedHashMap<>();
        this.numberOfClick = 0;
        this.movingRight = false;
    }

    @Override
    public boolean isClicked(Coord coord) {
        return clickedCells.containsKey(coord);
    }

    @Override
    public int getNumberOfClick() {
        return this.numberOfClick;
    }

    @Override
    public void leftMovement() {
        if (!movingRight) {
            Map<Coord, Integer> newClickedCells = new LinkedHashMap<>();
            for (Coord c : clickedCells.keySet()) {
                Coord newCoord = new Coord(c.row() - 1, c.col());
                if (newCoord.row() < 0) {
                    movingRight = true;
                }
                newClickedCells.put(newCoord, clickedCells.get(c));
            }
            clickedCells.clear();
            clickedCells.putAll(newClickedCells);
        }    
    }

    @Override
    public boolean isBound() {
        for (Coord coord : clickedCells.keySet()) {
            if (movingRight && coord.row() >= size - 1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void rigthMovement() {
        if (movingRight) {
            Map<Coord, Integer> newClickedCells = new LinkedHashMap<>();
            for (Coord coord : clickedCells.keySet()) {
                Coord newCoord = new Coord(coord.row() + 1, coord.col());
                newClickedCells.put(newCoord, clickedCells.get(coord));
            }
            clickedCells.clear();
            clickedCells.putAll(newClickedCells);
        }
    }

    @Override
    public void clickCell(Coord coord) {
        if (numberOfClick < 5 && !clickedCells.containsKey(coord)) {
            clickedCells.put(coord, ++numberOfClick);
        } else if (numberOfClick >= 5) {
            if (!movingRight) {
                leftMovement();
            } else {
                rigthMovement();
            }
        }
    }

    @Override
    public Map<Coord, Integer> getClickedCells() {
        return this.clickedCells;
    }
}
