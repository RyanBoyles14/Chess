package main.java;

class Board {

    private IPiece[][] board = new IPiece[8][8];
    private int initRow, initCol, finalRow, finalCol;
    private String moveType;

    Board(boolean isWhite){
        boolean player = isWhite;
        boolean opponent = !isWhite;
        //initialize the board setup.
        for(int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                int[] coord = {row, col};
                switch (row){
                    case 0:
                        // If the player wants to be white, set the opponent's pieces to black
                        isWhite = opponent;
                    case 7:
                        if(row == 7)
                            isWhite = player;

                        switch(col){
                            case 0:
                            case 7:
                                board[row][col] = new Rook(isWhite, coord);
                                break;
                            case 1:
                            case 6:
                                board[row][col] = new Knight(isWhite, coord);
                                break;
                            case 2:
                            case 5:
                                board[row][col] = new Bishop(isWhite, coord);
                                break;
                            case 3:
                                board[row][col] = new King(isWhite, coord);
                                break;
                            case 4:
                                board[row][col] = new Queen(isWhite, coord);
                                break;
                        }
                        break;
                    case 1:
                        isWhite = opponent;
                    case 6:
                        if(row == 6)
                            isWhite = player;

                        board[row][col] = new Pawn(isWhite, coord);
                        break;
                }
            }
        }
    }

    Player setPlayer(Player p){
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                IPiece piece = board[row][col];

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
            throw new MovementException("That is not a valid command.");

        char initCol = parts[0].toUpperCase().charAt(0);
        char initRow = parts[0].toUpperCase().charAt(1);
        char finalCol = parts[1].toUpperCase().charAt(0);
        char finalRow = parts[1].toUpperCase().charAt(1);

        //Check if the player's command is within the correct range
        if(initCol < 'A' || initCol > 'H' || finalCol < 'A' || finalCol > 'H'
            || initRow < '1' || initRow > '8' || finalRow < '1' || finalRow > '8')
            throw new MovementException("That is not a valid command.");

        // Convert A-H and 1-8 from their ASCII value to preferred array index values
        this.initRow = initRow -= 49;
        this.finalRow = finalRow -= 49;
        this.initCol = initCol -= 65;
        this.initCol = finalCol -= 65;

        try{
            validMovement();
        }catch(MovementException e){
            throw e;
        }


        updatePosition();
    }

    /*
     * Find movement based on the coordinates and the starting piece.
     */
    private boolean validMovement() throws MovementException {
        IPiece start = board[initRow][initCol];
        boolean movement = false; // The check for whether the movement pattern is acceptable
        boolean forward = false; //Necessary for pawns
        boolean up = false;
        boolean right = false;
        int range = 0;

        if(start == null)
            throw new MovementException("There is no piece at " + (char)(initCol+65)+""+initRow + ".");

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
            throw new MovementException("That is not a valid move.");

        if((moveType.equals("horizontal") || moveType.equals("diagonal")) && finalCol > initCol)
            right = true;

        if((moveType.equals("vertical") || moveType.equals("diagonal")) && initRow > finalRow)
            up = true;

        if((moveType.equals("vertical") || moveType.equals("diagonal"))
                && (range < 0 && start.isWhite() || range > 0 && !start.isWhite()))
            forward = true;

        range = Math.abs(range);

        //Check if the movement pattern calculated matches the starting piece
        //Returns true if the setup allows for a special move (ie en passant, castling)
        boolean specialCase = start.validMove(moveType, range, forward);

        checkSpaces(start, range, up, right);

        //TODO: Make sure pieces aren't in the path of travel
        //TODO: Make it so each movement method can work for either side

        return false;
    }

    private boolean isL(){
        return false;
    }

    private boolean checkSpaces(IPiece start, int range, boolean up, boolean right) throws MovementException{
        IPiece space = null;

        int x = 1, y = 1;
        if(up)
            y *= -1;

        if(right)
            x *= -1;

        for(int i = 1; i <= range; i++){
            switch(moveType){
                case "horizontal":
                    space = board[initRow][initCol + i*x];
                    break;
                case "vertical":
                    space = board[initRow + i*y][initCol];
                    break;
                case "diagonal":
                    space = board[initRow + i*y][initCol + i*x];

            }

            if (space != null){
                if(i < range)
                    throw new MovementException("There is a piece in the " + start.getType() + "'s way.");
                else if(i == range && start.isWhite() == space.isWhite())
                    throw new MovementException("The piece at " + (char)(finalCol + 65) + "" + finalRow + " cannot be captured.");

            }
        }

        return true;
    }

    private void updatePosition(){
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
