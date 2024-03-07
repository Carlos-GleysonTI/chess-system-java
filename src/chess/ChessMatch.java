package chess;

import java.util.ArrayList;
import java.util.List;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
//import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

//Classe partida de xadrez
public class ChessMatch {

	private int turn;//vez
	private Color currentPlayer; //jogador atual
	private Board board;
							
	//private List<ChessPiece> piecesOnTheBoard = new ArrayList<>();// lista de peças no tabuleiros
	private List<Piece> piecesOnTheBoard = new ArrayList<>();// lista de peças no tabuleiros
	//private List<ChessPiece> piecesOnTheBoard ;
	private List<Piece> capturedPieces  = new ArrayList<>();// lista de Peças capturadas
	//private List<ChessPiece> capturedPieces  = new ArrayList<>();// lista de Peças capturadas

	//iniciando as variáveis
	public ChessMatch() {
		// TODO Auto-generated constructor stub
		board = new Board(8, 8);
		turn = 1;
		//piecesOnTheBoard = new ArrayList<>();//posso iniciar ela aqui tambem no Contrustor
		currentPlayer = Color.WHITE;
		initialSetup();
	}
	
	//Metodo Gets
	
	public int getTurn() {
		return turn;
	}
	
	public Color getCurrentPlayer() {
		return currentPlayer;
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
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition){
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();
	}
	
	//executar movimento de xadrez ou jogada
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		//converter
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		
		Piece capturedPiece = makeMove(source, target);
		//depois q eu executar uma jogada chamo metodo de jogador por vez 
		
		nextTurn();//jogador por vez 
		return (ChessPiece) capturedPiece;
	}
	
	//metodo mover 
	private Piece makeMove(Position source, Position target) {//recebendo posição de origem e destino
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		
		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		return capturedPiece;
	}
	
	//metodo de validação
	private void validateSourcePosition(Position position) {
		//se existe uma posição
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");
		}
		//se a cor da perça é dif do jogador atual
		if(currentPlayer != ((ChessPiece) board.piece(position)).getColor()) {
			throw new ChessException("The chosen piece is not yours");
		}
		//se existe movimentação
		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for the chosen piece");
		}
	}

	private void validateTargetPosition(Position source, Position target) {
		if(!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chosen piece can´t move to target position");
		}
	}
	
	//Metódo vez do jogador
	private void nextTurn() {
		turn ++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	//coloque nova peça
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece);
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
