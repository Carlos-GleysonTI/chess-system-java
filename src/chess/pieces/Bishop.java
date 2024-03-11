package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

//Classe Bispo
public class Bishop extends ChessPiece {

	public Bishop(Board board, Color color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "B";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];// to pegando a matriz do taboleiro
		Position p = new Position(0, 0);// iniciando com 0

		//northwest - noroeste
		p.setValues(position.getRow() - 1, position.getColumn() -1);
		
		// enquanto meu tabaleiro exite uma posição e no meu taboleira não há uma peça
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;// minha matriz de linhas e coluna é vdd
			p.setValues(p.getRow() -1, p.getColumn() -1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// North East = Nordeste
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		// enquanto meu tabaleiro exite uma posição e no meu taboleira não há uma peça
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;// minha matriz de linhas e coluna é vdd
			p.setValues(p.getRow() -1, p.getColumn() +1);
			;
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// southeast = suldeste
		p.setValues(position.getRow() + 1 , position.getColumn() + 1);
		// enquanto meu tabaleiro exite uma posição e no meu taboleira não há uma peça
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;// minha matriz de linhas e coluna é vdd
			p.setValues(p.getRow() + 1, p.getColumn() +1);;
			;
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		//south-west = suldoeste
		p.setValues(position.getRow() + 1, position.getColumn()-1);
		// enquanto meu tabaleiro exite uma posição e no meu taboleira não há uma peça
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;// minha matriz de linhas e coluna é vdd
			p.setValues(p.getRow() + 1, getMoveCount() -1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		return mat;
	}
}
