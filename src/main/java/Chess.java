package main.java;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.*;

public class Chess implements Callable<Void> {

    @Option(names = {"--player", "-p"}, description = "Play the game with a friend. Enabled by default.")
    private boolean twoPlayer = true;

    @Option(names = {"--cpu", "-c"}, description = "Play the game against an AI.")
    private boolean cpu = false;

    @Option(names = {"--load", "-l"}, description = "Load a game from a file.")
    private File file;

    @Option(names = { "-h", "--help" }, usageHelp = true, description = "display a help message")
    private boolean helpRequested = false;

    private Board board;

    public static void main(String[] args) {
        CommandLine.call(new Chess(), args);
    }

    @Override
    public Void call() throws Exception{

        //TODO: implement 2P or CPU
        if (file == null) {
            board = new Board(true);
            startGame();
        } else {
            loadGame();
        }

        return null;
    }

    private void loadGame() throws IOException {

    }

    private void startGame(){
        Player white = board.setPlayer(new Player(true));
        Player black = board.setPlayer(new Player(false));

        //TODO: get command from user
        //TODO: Allow saving

        //Run the game so long as both kings are alive
        //while(white.getKing().isAlive() && black.getKing().isAlive()){
            board.display();
        //}

        board.display();

        if(white.getKing().isAlive())
            System.out.println("White wins!");
        else
            System.out.println("Black wins!");
    }

    private void parseCmd(){
        boolean invalid = true;

        while(invalid) {
            board.display();
            try {
                board.move("A7 A6");
                invalid = false;
            } catch (MovementException e) {
                System.out.println(e);
            }
        }
    }

    private void saveGame(){
        //TODO: Parse file name for saving
        //TODO: save board and players to txt file
        //TODO: Allow player to either keep playing or quit
    }

}

class MovementException extends Exception{

    MovementException(String s){
        super(s);
    }
}
