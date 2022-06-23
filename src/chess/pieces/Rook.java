package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import enums.Color;

public class Rook extends ChessPiece {

    public Rook(Board board, Color color) {
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0, 0);

        // above
        p.setValues(position.getRow() - 1, position.getColumn());
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[position.getRow()][position.getColumn()] = true;
            p.setValues(position.getRow() - 1, position.getColumn());
        }

        if (isThereOpponentPiece(p)) {
            mat[position.getRow()][position.getColumn()] = true;
        }

        // left
        p.setValues(position.getRow(), position.getColumn() - 1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[position.getRow()][position.getColumn()] = true;
            p.setValues(position.getRow(), position.getColumn() -1);
        }

        if (isThereOpponentPiece(p)) {
            mat[position.getRow()][position.getColumn()] = true;
        }

        // right
        p.setValues(position.getRow(), position.getColumn() + 1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[position.getRow()][position.getColumn()] = true;
            p.setValues(position.getRow(), position.getColumn() + 1);
        }

        if (isThereOpponentPiece(p)) {
            mat[position.getRow()][position.getColumn()] = true;
        }

        // below
        p.setValues(position.getRow() + 1, position.getColumn());
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[position.getRow()][position.getColumn()] = true;
            p.setValues(position.getRow() + 1, position.getColumn());
        }

        if (isThereOpponentPiece(p)) {
            mat[position.getRow()][position.getColumn()] = true;
        }

        return mat;
    }

    @Override
    public String toString() {
        return "R";
    }
}
