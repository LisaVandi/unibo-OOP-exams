package a01b.e2;

public interface Logic {
    boolean star(Coord coord);
    boolean unstar(Coord coord);
    boolean isOver();
    void neighbours(Coord coord);

}
