package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;
import enums.Color;

public class ChessMatch {

    private Board board;

    public ChessMatch() {
        board = new Board(8, 8);
        initialSetup();
    }

    public Board getBoard() {
        return board;
    }

    public ChessPiece[][] getPieces() {
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];

        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                mat[i][j] = (ChessPiece)board.piece(i, j);
            }
        }

        return mat;
    }

    private void initialSetup() {
        getBoard().placePiece(new Rook(getBoard(), Color.WHITE), new Position(0, 0));
        getBoard().placePiece(new King(getBoard(), Color.WHITE), new Position(0, 1));
        getBoard().placePiece(new Rook(getBoard(), Color.WHITE), new Position(0, 2));
        getBoard().placePiece(new King(getBoard(), Color.WHITE), new Position(0, 3));
    }
}
