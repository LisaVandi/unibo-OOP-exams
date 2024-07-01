package a04.e2;

import java.util.Random;

public class LogicImpl implements Logic {
    private final int size; 
    private final Random rnd;
    private Coord rook;
    private Coord king;

    public LogicImpl(final int size) {
        this.size = size;
        rnd = new Random();
    } 

    private Coord randomPos() {
        return new Coord(
            rnd.nextInt(size),
            rnd.nextInt(size)
        );
    }

    @Override
    public void resetGame() {
        this.king = randomPos();
        this.rook = randomPos();
    }

    @Override
    public boolean isValidMove(Coord src, Coord dst) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isValidMove'");
    }

    @Override
    public boolean moveRook(Coord coord) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moveRook'");
    }

    @Override
    public void moveKing() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moveKing'");
    }
}
