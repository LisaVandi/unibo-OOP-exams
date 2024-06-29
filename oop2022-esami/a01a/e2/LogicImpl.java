package a01a.e2;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class LogicImpl implements Logic {
    private final int size; 
    private Set<Coord> clickedCoord = new HashSet<>();
    private List<Coord> lastThreeStars = new LinkedList<>();

    public LogicImpl(final int size) {
        this.size = size;
    }

    @Override
    public boolean isEmpty(Coord coord) {
        return !isClicked(coord); 
    }

    @Override
    public boolean isClicked(Coord coord) {
        return clickedCoord.contains(coord);
    }

    @Override
    public void star(Coord coord) {
        clickedCoord.add(coord);
        lastThreeStars.add(coord);
        if (lastThreeStars.size() > 3) {
            lastThreeStars.remove(0);
        }
    }

    @Override
    public void unstar(Coord coord) {
        clickedCoord.remove(coord);
        lastThreeStars.remove(coord);
    }

    @Override
    public boolean isOver() {
        if (lastThreeStars.size() == 3) {
            Coord c1 = lastThreeStars.get(0);
            Coord c2 = lastThreeStars.get(1);
            Coord c3 = lastThreeStars.get(2);
            return isDiagonal(c1, c2, c3);
        }    
        return false;
    }

    @Override
    public Set<Coord> getClickedCoords() {
        return clickedCoord;
    }

    @Override
    public boolean isDiagonal(Coord c1, Coord c2, Coord c3) {
        int dx1 = c2.row() - c1.row();
        int dy1 = c2.col() - c1.col();
        int dx2 = c3.row() - c2.row();
        int dy2 = c3.col() - c2.col();

        return (Math.abs(dx1) == 1 && Math.abs(dy1) == 1 && dx1 == dx2 && dy1 == dy2);
    
    }
}
