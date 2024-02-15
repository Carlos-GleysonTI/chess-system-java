package chess;

import boardgame.Board;

//Classe partida de xadrez
public class ChessMatch {
	
	private Board board;
	
	public ChessMatch() {
		// TODO Auto-generated constructor stub
		board = new Board(8, 8);
	}

	public ChessPiece[][] getPieces(){
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		//vamos pecorrer essa matriz com o for
		
		for(int i = 0; i < board.getRows(); i++) {
			for(int j = 0; j < board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return mat;
	}
}
