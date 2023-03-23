// Written by Kevin Tran (tran1199)
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Board b = new Board();
        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", b);
        System.out.println(b);
        int rand =(int) (Math.random() * 2);
        while (!b.isGameOver()) {
            Scanner s = new Scanner(System.in);
            if (rand % 2 == 0) {
                System.out.println("It is white's turn");
                System.out.println("Choose your move (format: [startrow][startcol] [endrow][endcol])");
                int startRow = s.nextInt();
                int startCol = s.nextInt();
                int endRow = s.nextInt();
                int endCol = s.nextInt();
                if (!b.getPiece(startRow, startCol).getIsBlack()) {
                    if (b.movePiece(startRow, startCol, endRow, endCol)) {
                        b.movePiece(startRow, startCol, endRow, endCol);
                        System.out.println(b);
                        System.out.print("Valid Move. ");
                    }
                } else {
                    System.out.println(b);
                    System.out.println("Invalid Move, try again");
                    rand--;
                }
            } else {
                System.out.println("It is black's turn");
                System.out.println("Choose your move (format: [startrow][startcol] [endrow][endcol])");
                int startRow = s.nextInt();
                int startCol = s.nextInt();
                int endRow = s.nextInt();
                int endCol = s.nextInt();
                if (b.getPiece(startRow, startCol).getIsBlack()) {
                    if (b.movePiece(startRow, startCol, endRow, endCol)) {
                        b.movePiece(startRow, startCol, endRow, endCol);
                        System.out.println(b);
                        System.out.print("Valid Move. ");
                    }
                } else {
                    System.out.println(b);
                    System.out.println("Invalid Move, try again");
                    rand--;
                }
            }
            rand++;
        }
        System.out.println("-------------");
        System.out.println("Game is Over");
    }
}
