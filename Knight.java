// Written by Kevin Tran, tran1199
public class Knight {
    //instance variables
    private int row;
    private int col;
    private boolean isBlack;

    public Knight(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    } // constructor

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (!board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack)) {
            return false;
        }
        if (board.getPiece(endRow, endCol) == null) {
            // Knight moving to empty cell
            if (endRow == this.row - 2) { // knight moves up 2 rows and 1 col
                return (endCol == this.col - 1) || (endCol == this.col + 1);
            }
            if (endRow == this.row - 1) { // knight moves up 1 row and 2 col
                return (endCol == this.col - 2) || (endCol == this.col + 2);
            }
            if (endRow == this.row + 2) { // knight moves down 2 rows and 1 col
                return (endCol == this.col - 1) || (endCol == this.col + 1);
            }
            if (endRow == this.row + 1) { // knight moves down 1 row and 2 col
                return (endCol == this.col - 2) || (endCol == this.col + 2);
            }
        } else if (board.getPiece(endRow, endCol) != null && board.getPiece(endRow, endCol).getIsBlack() != this.isBlack) {
            // Knight captures a piece
            if (endRow == this.row - 2) { // knight moves up 2 rows and 1 col
                return (endCol == this.col - 1) || (endCol == this.col + 1);
            }
            if (endRow == this.row - 1) { // knight moves up 1 row and 2 col
                return (endCol == this.col - 2) || (endCol == this.col + 2);
            }
            if (endRow == this.row + 2) { // knight moves down 2 rows and 1 col
                return (endCol == this.col - 1) || (endCol == this.col + 1);
            }
            if (endRow == this.row + 1) { // knight moves down 1 row and 2 col
                return (endCol == this.col - 2) || (endCol == this.col + 2);
            }
        }
        return false;
    }

}
