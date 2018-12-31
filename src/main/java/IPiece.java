package main.java;

public interface IPiece {

    void kill();

    void setCoord(int[] coord);

    boolean move();

    boolean isWhite();

    boolean isAlive();

    int[] getCoord();
}
