package application;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        ChessMatch chessMatch = new ChessMatch();
        Scanner scan = new Scanner(System.in);

        while(true) {
            UI.printBoard(chessMatch.getPieces());
            System.out.println();
            System.out.print("Source: ");
            ChessPosition source = UI.readChessPosition(scan);

            System.out.println();
            System.out.print("Target: ");
            ChessPosition target = UI.readChessPosition(scan);

            ChessPiece chessPiece = chessMatch.performChessMove(source, target);
        }

    }

}
