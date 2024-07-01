package a03b.e2;

import java.util.Random;

public class LogicImpl implements Logic {
    private final int size;
    private Random random = new Random();


    public LogicImpl(final int size) {
        this.size = size;
    }

}
