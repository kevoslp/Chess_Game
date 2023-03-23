// Written by Kevin Tran, tran1199
public class Queen {
    // instance variables
    private int row;
    private int col;
    private boolean isBlack;

    public Queen(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    } // constructor

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (!board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack)) {
            return false;
        }
        if (board.verifyVertical(this.row, this.col, endRow, endCol) && board.getPiece(endRow, endCol) == null) {
            // Queen moving vertically to empty cell
            return (this.col == endCol);
        } else if (board.verifyVertical(this.row, this.col, endRow, endCol) && board.getPiece(endRow, endCol) != null && board.getPiece(endRow, endCol).getIsBlack() != this.isBlack) {
            // Capturing piece vertically
            return (this.col == endCol);
        }
        if (board.verifyHorizontal(this.row, this.col, endRow, endCol) && board.getPiece(endRow, endCol) == null) {
            // Queen moving horizontally to empty cell
            return (endRow == this.row);
        } else if (board.verifyHorizontal(this.row, this.col, endRow, endCol) && board.getPiece(endRow, endCol) != null && board.getPiece(endRow, endCol).getIsBlack() != this.isBlack) {
            // Capturing piece horizontally
            return (endRow == this.row);
        }
        if (board.verifyAdjacent(this.row, this.col, endRow, endCol) && board.getPiece(endRow, endCol) == null) {
            // Queen moving to empty adjacent cell
            return (endRow <= this.row + 1 && endRow >= this.row - 1) && (endCol <= this.col + 1 && endCol >= this.col - 1);
        } else if (board.getPiece(endRow, endCol) != null && board.getPiece(endRow, endCol).getIsBlack() != this.isBlack && board.verifyAdjacent(this.row, this.col, endRow, endCol)) {
            // Queen capturing piece in adjacent cell
            return (endRow <= this.row + 1 && endRow >= this.row - 1) && (endCol <= this.col + 1 && endCol >= this.col - 1);
        }
        if (board.verifyDiagonal(this.row, this.col, endRow, endCol) && board.getPiece(endRow, endCol) == null) {
            // Queen moving to empty diagonal cell
            return (Math.abs(endRow - this.row) == Math.abs(this.col - endCol));
        } else if (board.getPiece(endRow, endCol) != null && board.getPiece(endRow, endCol).getIsBlack() != this.isBlack && board.verifyDiagonal(this.row, this.col, endRow, endCol)) {
            // Queen capturing piece in diagonal cell
            return (Math.abs(endRow - this.row) == Math.abs(this.col - endCol));
        }
        return false;
    }
}
