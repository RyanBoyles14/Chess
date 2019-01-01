package main.java;

class Board {

    private IPiece[][] board = new IPiece[8][8];

    Board(boolean playerStart){
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
                                board[row][col] = new Rook(playerStart, coord);
                                break;
                            case 1:
                            case 6:
                                board[row][col] = new Knight(playerStart, coord);
                                break;
                            case 2:
                            case 5:
                                board[row][col] = new Bishop(playerStart, coord);
                                break;
                            case 3:
                                board[row][col] = new King(playerStart, coord);
                                break;
                            case 4:
                                board[row][col] = new Queen(playerStart, coord);
                                break;
                        }
                        break;
                    case 1:
                        playerStart = !playerStart;
                    case 6:
                        board[row][col] = new Pawn(playerStart, coord);
                        break;
                }
            }
        }
    }

    Player setPlayer(Player p){
        for (IPiece[] aBoard : board) {
            for (int col = 0; col < board.length; col++) {
                IPiece piece = aBoard[col];

                if (piece == null)
                    continue;

                if ((piece.isWhite() && p.isWhite())
                        || (!piece.isWhite() && !p.isWhite()))
                    p.addPiece(piece);
            }
        }

        return p;
    }

    void move(String command) throws MovementException{

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

        // Convert A-H and 1-8 from their ASCII value to preferred array index values
        initRow -= 49;
        finalRow -= 49;
        initCol -= 65;
        finalCol -= 65;

        if(!validMovement(initRow, initCol, finalRow, finalCol))
            throw new MovementException("Invalid Movement");

        updatePosition(initRow, initCol, finalRow, finalCol);
    }

    /*
     * Find movement based on the coordinates and the starting piece.
     */
    private boolean validMovement(int initRow, int initCol, int finalRow, int finalCol) throws MovementException {
        IPiece start = board[initRow][initCol];
        boolean movement = false; // The check for whether the movement pattern is acceptable
        boolean forward = false; //Necessary for pawns
        String moveType = "";
        int range = 0;

        if (initRow == finalRow && initCol != finalCol){
            moveType = "horizontal";
            range = finalCol - initCol;
        }else if(initCol == finalCol && initRow != finalRow) {
            moveType = "vertical";
            range = finalRow - initRow;
        }else if(Math.abs(finalRow - initRow) == Math.abs(finalCol - initCol)) {
            moveType = "diagonal";
            range = finalRow - initRow;
        }else if(isL())
            moveType = "l-shape"; //TODO: define L-shape movement
        else
            return false;

        if(range > 0 && start.isWhite() || range < 0 && !start.isWhite())
            forward = true;

        range = Math.abs(range);

        //Check if the movement pattern calculated matches the starting piece
        switch(start.getClass().toString()){
            case "King":
            case "Queen":
            case "Rook":
            case "Bishop":
            case "Knight":
            case "Pawn":
                movement = start.validMove(moveType, range, forward);
                break;
            default:
        }

        if(!checkSpaces(initRow, initCol, finalRow, finalCol))
            return false;

        //TODO: Make sure pieces aren't in the path of travel
        //TODO: Make it so each movement method can work for either side

        return false;
    }

    private boolean isL(){
        return false;
    }
    
    private boolean checkSpaces(int initRow, int initCol, int finalRow, int finalCol){
        return false;
    }

    private void updatePosition(int initRow, int initCol, int finalRow, int finalCol){
        IPiece start = board[initRow][initCol];
        board[finalRow][finalCol] = start;
        board[initRow][initCol] = null;
        int[] coord = {finalRow, finalCol};
        start.setCoord(coord);
    }

    void display(){
        System.out.println("    A | B | C | D | E | F | G | H");
        System.out.println("  |---|---|---|---|---|---|---|---");
        for(int row = 0; row < board.length; row++){
            System.out.print(row+1 + " |");
            for(int col = 0; col < board.length; col++){
                IPiece piece = board[row][col];
                if(piece == null)
                    System.out.print("   ");
                else if(piece.getClass().toString().equals("Knight")){
                    System.out.print(" N ");
                } else{
                    System.out.print(" " + piece.getClass().toString().charAt(0) + " ");
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
