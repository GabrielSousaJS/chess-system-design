package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;
import enums.Color;

public class ChessMatch {

    private int turn;
    private Color currentPlayer;
    private Board board;

    public ChessMatch() {
        board = new Board(8, 8);
        turn = 1;
        currentPlayer = Color.WHITE;
        initialSetup();
    }

    public Board getBoard() {
        return board;
    }

    public int getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
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

    public boolean[][] possibleMoves(ChessPosition sourcePosition) {
        Position source = sourcePosition.toPosition();

        validateSourcePosition(source);

        return getBoard().piece(source).possibleMoves();
    }



    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();

        validateSourcePosition(source);
        validateTargetPosition(source, target);

        Piece capturedPiece = makeMove(source, target);
        nextTurn();

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
            throw new ChessException("There is no piece on source position");
        }

        if (currentPlayer != ((ChessPiece)board.piece(position)).getColor()) {
            throw new ChessException("The chosen piece is not yours");
        }

        if (!getBoard().piece(position).isThereAnyPossibleMove()) {
            throw new ChessException("There is no possible moves for the chosen piece");
        }
    }

    private void validateTargetPosition(Position source, Position target) {
        if (!getBoard().piece(source).possibleMove(target)) {
            throw new ChessException("The chosen piece can't move to target position");
        }
    }

    private void nextTurn() {
        turn++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private void placeNewPiece(char column, int row, ChessPiece piece) {
        getBoard().placePiece(piece, new ChessPosition(column, row).toPosition());
    }

    private void initialSetup() {
        placeNewPiece('b', 8, new King(getBoard(), Color.BLACK));
        placeNewPiece('c', 8, new King(getBoard(), Color.WHITE));
        placeNewPiece('d', 8, new King(getBoard(), Color.BLACK));
        placeNewPiece('a', 1, new Rook(getBoard(), Color.BLACK));

    }
}
