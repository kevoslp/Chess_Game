
// Written by Kevin Tran, tran1199
public class Board {

    // Instance variables
    private Piece[][] board;

    //TODO:
    // Construct an object of type Board using given arguments.
    public Board() {
        this.board = new Piece[8][8];
    }

    // Accessor Methods

    //TODO:
    // Return the Piece object stored at a given row and column
    public Piece getPiece(int row, int col) {
        return board[row][col];
    }

    //TODO:
    // Update a single cell of the board to the new piece.
    public void setPiece(int row, int col, Piece piece) {
        board[row][col] = piece;
    }

    // Game functionality methods

    //TODO:
    // Moves a Piece object from one cell in the board to another, provided that
    // this movement is legal. Returns a boolean to signify success or failure.
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        if (getPiece(startRow, startCol) != null) {
            if (getPiece(startRow, startCol).isMoveLegal(this, endRow, endCol)) {
                this.board[endRow][endCol] = getPiece(startRow, startCol);
                getPiece(startRow, startCol).setPosition(endRow, endCol);
                this.board[startRow][startCol] = null;
                return true;
            }
        }
        return false;
    }

    //TODO:
    // Determines whether the game has been ended, i.e., if one player's King
    // has been captured.
    public boolean isGameOver() {
        int count = 0;
        for (int i = 0; i < this.board.length; i++) { // checks every cell of the board
            for (int j = 0; j < this.board[i].length; j++) {
                if (this.board[i][j] != null) {
                    if (this.board[i][j].getCharacter() == '\u2654'){ // counts how many kings are on the board
                        count++;
                    }
                    if (this.board[i][j].getCharacter() == '\u265a') {
                        count++;
                    }
                }
            }
        }
        if (count == 1) { // if there is only one king, game is over
            return true;
        }
        return false;
    }

    //TODO:
    // Construct a String that represents the Board object's 2D array. Return
    // the fully constructed String.
    public String toString() {
        int count = 0;
        String str = "   0" + "\u2001" + "1" + "\u2001" + "2" + "\u2001" + "3" + "\u2001" + "4" + "\u2001" + "5" + "\u2001" + "6" + "\u2001" + "7" + "\n";
        for (int i = 0; i < this.board.length; i++) {
            str = str + count + " |";
            count++;
            for (int j = 0; j < this.board[i].length; j++) {
                if (this.board[i][j] == null) {
                    str = str + "\u2001" + "|";
                }
                if (this.board[i][j] != null) {
                    str = str + this.board[i][j].getCharacter() + "|";
                }
            }
            str = str + "\n";
        }
        return str;
    }

    //TODO:
    // Sets every cell of the array to null. For debugging and grading purposes.
    public void clear() {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                this.board[i][j] = null;
            }
        }
    }

    // Movement helper functions

    //TODO:
    // Ensure that the player's chosen move is even remotely legal.
    // Returns a boolean to signify whether:
    // - 'start' and 'end' fall within the array's bounds.
    // - 'start' contains a Piece object, i.e., not null.
    // - Player's color and color of 'start' Piece match.
    // - 'end' contains either no Piece or a Piece of the opposite color.
    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        if (startRow < 8 && startCol < 8 && endRow < 8 && endCol < 8) {
            if (getPiece(startRow, startCol) != null) {
                if (getPiece(startRow, startCol).getIsBlack() == isBlack) {
                    if (getPiece(endRow, endCol) == null || getPiece(endRow, endCol).getIsBlack() != isBlack) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //TODO:
    // Check whether the 'start' position and 'end' position are adjacent to each other
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        if (startRow == endRow && startCol == endCol) { // if 'start' is the same as 'end'
            return false;
        }
        if (endRow <= startRow + 1 && endRow >= startRow - 1) {
            if (endCol <= startCol + 1 && endCol >= startCol - 1) {
                return true;
            }
        }
        return false;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid horizontal move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one row.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        if (startRow == endRow && startCol == endCol) { // false if 'start' is same as 'end'
            return false;
        }
        int l = endCol - startCol; // determine if piece is moving left-to-right or right-to-left
        if (startRow == endRow) {
            if (l > 0) { // moving left-to-right
                for (int i = startCol + 1; i < endCol + 1; i++) {
                    if (this.board[startRow][i] != null) {
                        return false;
                    }
                }
                return true;
            }
            if (l < 0) { // moving right-to-left
                for (int i = startCol - 1; i > endCol - 1; i--) {
                    if (this.board[startRow][i] != null) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid vertical move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one column.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        if (startRow == endRow && startCol == endCol) { // false if 'start' is same as 'end'
            return false;
        }
        int l = endRow - startRow; // determine if piece is moving top-down or bottom-up
        if (startCol == endCol) {
            if (l > 0) { // moving top-down
                for (int i = startRow + 1; i < endRow + 1; i++) {
                    if (board[i][startCol] != null) {
                        return false;
                    }
                }
                return true;
            }
            if (l < 0) { // moving bottom-up
                for (int i = startRow - 1; i > endRow - 1; i--) {
                    if (board[i][startCol] != null) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid diagonal move.
    // Returns a boolean to signify whether:
    // - The path from 'start' to 'end' is diagonal... change in row and col.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        if (startRow == endRow && startCol == endCol) { // false if 'start' is same as 'end'
            return false;
        }
        if (Math.abs(endRow - startRow) == Math.abs(startCol - endCol)) { // if movement is diagonal
            // Check upper diagonal on left side
            for (int i = startRow, j = startCol; i >= 0 && j >= 0; i--, j--) {
                if (board[i][j] != null) {
                    return false;
                }
            }

            // Check lower diagonal on left side
            for (int i = startRow, j = startCol; j >= 0 && i < board.length; i++, j--) {
                if (board[i][j] != null) {
                    return false;
                }
            }

            // Check upper diagonal on right side
            for (int i = startRow, j = startCol; i >= 0 && j < endCol; i--, j++) {
                if (board[i][j] != null) {
                    return false;
                }
            }

            // Check lower diagonal on right side
            for (int i = startRow, j = startCol; j < endCol && i < endRow; i++, j++) {
                if (board[i][j] != null) {
                    return false;
                }
            }
        }
        return true;
    }

}

