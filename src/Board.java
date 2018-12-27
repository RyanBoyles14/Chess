public class Board {

    private Piece[][] board = new Piece[8][8];

    public Board(boolean playerStart){
        //initialize the board setup.
        for(int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                int[] coord = {row, col};
                switch (row){
                    case 0:
                        // If the player wants to be white, set the opponent's pieces to black
                        playerStart = !playerStart;
                    case 7:
                        switch(col){
                            case 0:
                            case 7:
                                board[row][col] = new Piece(playerStart, "Rook", coord);
                                break;
                            case 1:
                                playerStart = !playerStart;
                            case 6:
                                board[row][col] = new Piece(playerStart, "Knight", coord);
                                break;
                            case 2:
                            case 5:
                                board[row][col] = new Piece(playerStart, "Bishop", coord);
                                break;
                            case 3:
                                board[row][col] = new Piece(playerStart, "King", coord);
                                break;
                            case 4:
                                board[row][col] = new Piece(playerStart, "Queen", coord);
                                break;
                        }
                        break;
                    case 1:
                        playerStart = !playerStart;
                    case 6:
                        board[row][col] = new Piece(playerStart, "Pawn", coord);
                        break;
                }
            }
        }
    }

    public void move(String command) throws MovementException{

        String[] parts = command.split("\\s");
        if(parts.length != 2 || parts[0].length() != 2 || parts[1].length() != 2)
            throw new MovementException("Invalid Command");

        char initCol = parts[0].toUpperCase().charAt(0);
        char initRow = parts[0].toUpperCase().charAt(1);
        char finalCol = parts[1].toUpperCase().charAt(0);
        char finalRow = parts[1].toUpperCase().charAt(1);

        //Check if the player's command is within the correct range
        if(initCol < 'A' || initCol > 'H' || finalCol < 'A' || finalCol > 'H'
            || initRow < '1' || initRow > '8' || finalRow < '1' || finalRow > '8')
            throw new MovementException("Invalid Command");

        if(!validMovement(initRow, initCol, finalRow, finalCol))
            throw new MovementException("Invalid Movement");
    }

    private boolean validMovement(int initRow, int initCol, int finalRow, int finalCol) throws MovementException{
        // Convert A-H and 1-8 from their ASCII value to preferred array index values
        initRow -= 49;
        finalRow -= 49;
        initCol -= 65;
        finalCol -= 65;

        Piece start = board[initRow][initCol];
        Piece end = board[finalRow][finalCol];

        //TODO: Make sure certain pieces aren't in the path of travel
        //TODO: Make it so each movement method can work for either side
        switch(start.getType()){
            case "King":
                break;
            case "Queen":
                break;
            case "Rook":
                break;
            case "Bishop":
                break;
            case "Knight":
                break;
            case "Pawn":
                validPawn(start, end, finalRow, finalCol);
                break;
            default:
                return false;
        }

        return true;
    }

    private void validPawn(Piece start, Piece end, int finalRow, int finalCol) throws MovementException{
//        int range = 1;
//        if(start.atStart())
//            range = 2;

        board[finalRow][finalCol] = start;
        board[start.getCoord()[0]][start.getCoord()[1]] = null;
        int[] coord = {finalRow, finalCol};
        start.setCoord(coord);
    }

    void display(){
        System.out.println("    A | B | C | D | E | F | G | H");
        System.out.println("  |---|---|---|---|---|---|---|---");
        for(int row = 0; row < board.length; row++){
            System.out.print(row+1 + " |");
            for(int col = 0; col < board.length; col++){
                Piece piece = board[row][col];
                if(piece == null)
                    System.out.print("   ");
                else if(piece.getType().equals("Knight")){
                    System.out.print(" N ");
                } else{
                    System.out.print(" " + piece.getType().charAt(0) + " ");
                }

                if(col < 7)
                    System.out.print("|");
                else System.out.println();
            }
            System.out.println("  |---|---|---|---|---|---|---|---");
        }
        System.out.println();
    }

}
