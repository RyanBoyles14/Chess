package main.java;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
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
            parseCmd();
        //}

        board.display();

        if(white.getKing().isAlive())
            System.out.println("White wins!");
        else
            System.out.println("Black wins!");
    }

    /*
    Accepted Commands:
    Move from 1 space to another (ie. A6 to A7)
    Save the game
    Help
    Restart
    Load
    Quit
     */
    private void parseCmd(){
        Scanner sc = new Scanner(System.in);
        boolean invalid = true;

        while(invalid) {
            System.out.println("\nInsert a command:");
            String cmd = sc.next();
            String[] parts = cmd.split("\\s");
            try {
                if(cmd.equalsIgnoreCase("HELP")){
                    help();
                    continue;
                } else if(cmd.equalsIgnoreCase("SAVE")){
                    saveGame(sc);
                } else if(cmd.equalsIgnoreCase("QUIT")){
                    quit(sc);
                    continue;
                } else if(cmd.equalsIgnoreCase("RESTART")) {
                    restart(sc);
                    continue;
                } else if(cmd.equalsIgnoreCase("LOAD")){

                } else if(parts.length != 2 || parts[0].length() != 2 || parts[1].length() != 2)
                    throw new MovementException("That is not a valid command.");
                board.move(cmd);
                invalid = false;
            } catch (MovementException e) {
                System.out.println("\n" + e + " Type \"help\" for assistance.\n");
            }
        }
    }

    private void saveGame(Scanner sc){
        //TODO: Parse file name for saving
        //TODO: Check if that file exists. Check if overwritting is okay.
        //TODO: save board and players to txt file
        //TODO: Allow player to either keep playing or quit
    }

    private void help(){
        System.out.println("\nTo move a piece from one space to another, " +
                "specify one piece's coordinates with the coordinates for where you want it to go.\n" +
                "For example, to move a piece from A7 to A6, type \"A7 A6\".");
        System.out.println("If you wish to save the game as it currently is, type \"save\".");
        System.out.println("Feel free to type \"help\" at any time.");
    }

    private void quit(Scanner sc){
        System.out.println("\nAre you sure you want to quit without saving? (Y|N)");

        boolean invalid = true;
        while(invalid) {
            String cmd = sc.next();
            if (cmd.equalsIgnoreCase("Y")) {
                System.out.println("\nSee you later!");
                System.exit(0);
            } else if (cmd.equalsIgnoreCase("N")) {
                invalid = false;
            } else {
                System.out.println("\nInvalid response. Please answer with Y or N.");
            }
        }
    }

    private void restart(Scanner sc){
        System.out.println("Are you sure you want to restart the game? (Y|N)");

        boolean invalid = true;
        while(invalid) {
            String cmd = sc.next();
            if (cmd.equalsIgnoreCase("Y")) {
                System.out.println("Restarting...");
                startGame();
            } else if (cmd.equalsIgnoreCase("N")) {
                invalid = false;
            } else {
                System.out.println("Invalid response. Please answer with Y or N.");
            }
        }
    }

}

class MovementException extends Exception{

    MovementException(String s){
        super(s);
    }
}
