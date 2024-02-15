package application;

import boardgame.Board;
import chess.ChessMatch;
import chess.ChessPiece;


public class Progam {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ChessMatch chessMatch = new ChessMatch();
		UI.printBoard(chessMatch.getPieces());
		
	}

}
