// Written by Kevin Tran, tran1199
public class King {
    // instance variables
    private int row;
    private int col;
    private boolean isBlack;

    public King(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    } // constructor

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (!board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack)) {
            return false;
        }
        if (board.verifyAdjacent(this.row, this.col, endRow, endCol) && board.getPiece(endRow, endCol) == null) {
            // King moving to empty adjacent cell
            return (endRow <= this.row + 1 && endRow >= this.row - 1) && (endCol <= this.col + 1 && endCol >= this.col - 1);
        } else if (board.getPiece(endRow,endCol) != null && board.getPiece(endRow,endCol).getIsBlack() != this.isBlack) {
            // King capturing piece
            if (board.verifyAdjacent(this.row, this.col, endRow, endCol)) {
                return (endRow <= this.row + 1 && endRow >= this.row - 1) && (endCol <= this.col + 1 && endCol >= this.col - 1);
            }
        }
        return false;
    }

}
