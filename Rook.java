// Written by Kevin Tran, tran1199
public class Rook {
    //instance variables
    private int row;
    private int col;
    private boolean isBlack;

    public Rook(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    } // constructor

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (!board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack)) {
            return false;
        }
        if (board.verifyVertical(this.row, this.col, endRow, endCol) && board.getPiece(endRow, endCol) == null) {
            // Vertical movement of Rook to empty square
            return true;
        } else if (board.getPiece(endRow, endCol) != null && board.getPiece(endRow,endCol).getIsBlack() != this.isBlack) {
            // Vertically capturing a piece
            if (board.verifyVertical(this.row, this.col, endRow, endCol)) {
                return true;
            }
        }
        if (board.verifyHorizontal(this.row, this.col, endRow, endCol) && board.getPiece(endRow, endCol) == null) {
            // Horizontal movement
            return true;
        } else if (board.getPiece(endRow, endCol) != null && board.getPiece(endRow, endCol).getIsBlack() != this.isBlack) {
            // Horizontally capturing a piece
            if (board.verifyHorizontal(this.row, this.col, endRow, endCol)) {
                return true;
            }
        }
        return false;
    }

}
