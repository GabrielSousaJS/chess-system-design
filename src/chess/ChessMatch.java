package chess;

import boardgame.Board;
import boardgame.Piece;
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

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();

        validateSourcePosition(source);

        Piece capturedPiece = (ChessPiece)makeMove(source, target);

        return (ChessPiece)capturedPiece;
    }

    private Piece makeMove(Position source, Position target) {
        Piece piece = getBoard().removePiece(source);
        Piece capturedPiece = getBoard().removePiece(target);

        getBoard().placePiece(piece, target);

        return capturedPiece;
    }

    private void validateSourcePosition(Position position) {
        if (!getBoard().thereIsAPiece(position)) {
            throw new ChessException("This position does not exists");
        }

        if (!getBoard().piece(position).isThereAnyPossibleMove()) {
            throw new ChessException("There is no possible moves for the chosen piece");
        }
    }

    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }

    private void initialSetup() {
        placeNewPiece('a', 8, new Rook(getBoard(), Color.WHITE));
        placeNewPiece('b', 8, new King(getBoard(), Color.BLACK));
        placeNewPiece('c', 8, new King(getBoard(), Color.WHITE));
        placeNewPiece('d', 8, new King(getBoard(), Color.BLACK));

    }
}
