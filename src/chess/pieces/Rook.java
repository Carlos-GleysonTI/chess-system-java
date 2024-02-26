package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

//Classe Torre
public class Rook extends ChessPiece{

	public Rook(Board board, Color color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "R";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];//to pegando a matriz do taboleiro
		Position p = new Position(0, 0);//iniciando com 0
		
		
		//solução above, ou seja, mover para cima 
		//esse position é a posição da peça
		p.setValues(position.getRow() - 1, position.getColumn());
		// enquanto meu tabaleiro exite uma posição e no  meu taboleira não há uma peça  
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;// minha matriz de linhas e coluna é vdd
			p.setRow(p.getRow()-1);
		}
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		//left = esquerda
		p.setValues(position.getRow(), position.getColumn() - 1 );
		// enquanto meu tabaleiro exite uma posição e no  meu taboleira não há uma peça  
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;// minha matriz de linhas e coluna é vdd
			p.setColumn(p.getColumn() - 1);;
		}
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		//right = direita
		p.setValues(position.getRow(), position.getColumn() + 1 );
		// enquanto meu tabaleiro exite uma posição e no  meu taboleira não há uma peça  
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;// minha matriz de linhas e coluna é vdd
			p.setColumn(p.getColumn() + 1);;
		}
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		//below = abaixo
		p.setValues(position.getRow() + 1, position.getColumn());
		// enquanto meu tabaleiro exite uma posição e no  meu taboleira não há uma peça  
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;// minha matriz de linhas e coluna é vdd
			p.setRow(p.getRow() + 1);
		}
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		return mat;
	}
}
