package a03a.e2;

import java.util.Random;

public class LogicImpl implements Logic {

    private final int size; 
    private Random rand;
    private Turn turn;
    private Coord humanCoord;
    private Coord computerCoord;

    public LogicImpl(final int size) {
        this.size = size;
        // all'inizio si posizionano in modo random le due torri
        rand = new Random();
    }

    private Coord randomPos() {
        return new Coord(
            rand.nextInt(size),
            rand.nextInt(size)
        );
    }

    @Override
    public Turn gameTurn() {
        return this.turn;
    }

    @Override
    public void resetGame() {
        this.turn = Turn.HUMAN; 
        this.humanCoord = randomPos();
        this.computerCoord = randomPos();   
    }

    @Override
    public boolean moveHuman(Coord pos) {
         if(!isValidMove(humanCoord, pos)) {
            return false;
        }
        humanCoord = pos;
       
        return true;
    }

    private boolean isValidMove(final Coord prevPos, final Coord newCord) {
        if(!validCell(newCord) || !validCell(prevPos)) {
            return false;
        }
        if(prevPos.equals(newCord)) {
            return false;
        }
        return prevPos.row() == newCord.row() || prevPos.col() == newCord.col();
    }


    @Override
    public void moveComputer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moveComputer'");
    }

    @Override
    public boolean validCell(Coord coord) {
        return coord.row() < size && coord.row() >= 0 &&
        coord.col() < size && coord.col() >= 0;
    }
}


