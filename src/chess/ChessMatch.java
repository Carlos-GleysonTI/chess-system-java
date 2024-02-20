package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
//import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

//Classe partida de xadrez
public class ChessMatch {

	private Board board;

	public ChessMatch() {
		// TODO Auto-generated constructor stub
		board = new Board(8, 8);
		initialSetup();
	}

	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		// vamos pecorrer essa matriz com o for

		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return mat;
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		//converter
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		
		validateSourcePosition(source);
		//validateTargetPosition(source, target);
		
		Piece capturedPiece = makeMove(source, target);
		return (ChessPiece) capturedPiece;
	}
	
	//metodo mover 
	private Piece makeMove(Position source, Position target) {//recebendo posição de origem e destino
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		return capturedPiece;
	}
	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");
		}
	}

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}

	private void initialSetup() {

		/*
		 * placeNewPiece('b', 6, new Rook(board, Color.WHITE)); placeNewPiece('e', 8,
		 * new King(board, Color.BLACK)); placeNewPiece('e', 1, new King(board,
		 * Color.WHITE));
		 */

		/*
		 * //board.placePiece(new Rook(board, Color.WHITE), new Position(9, 1));
		 * board.placePiece(new Rook(board, Color.WHITE), new Position(2, 1));
		 * //board.placePiece(new King(board, Color.BLACK), new Position(2, 1));
		 * board.placePiece(new King(board, Color.BLACK), new Position(0, 4));
		 * board.placePiece(new King(board, Color.WHITE), new Position(7, 4));
		 */

		// add mais peças

		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
		placeNewPiece('c', 2, new Rook(board, Color.WHITE));
		placeNewPiece('d', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new King(board, Color.WHITE));

		placeNewPiece('c', 7, new Rook(board, Color.BLACK));
		placeNewPiece('c', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 8, new King(board, Color.BLACK));
	}
}
