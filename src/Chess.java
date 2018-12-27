//TODO: Set up players based on initial start
//TODO; Allow swapping board view
//TODO: Accept/check commands
//TODO: Save/load game

public class Chess {
    public static void main(String[] args) {
        Board board = new Board(true);
        startGame(board);
    }

    private static void startGame(Board board){
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

        board.display();
    }

}

class MovementException extends Exception{

    public MovementException(String s){
        super(s);
    }
}
