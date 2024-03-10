package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

//Classe peça de xadrez
public abstract class ChessPiece extends Piece{
	
	private Color color;
	private int moveCount;

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	public int getMoveCount() {
		return moveCount;
	}
	
	public void increaseMoveCount() {
		moveCount ++;
	}
	
	public void decreaseMoveCount() {
		moveCount --;
	}
	
	public ChessPosition getChessPosition() {
		//converte minha posição em chessPosition
		return ChessPosition.fromPosition(position);
	}

	/*public void setColor(Color color) {
		this.color = color;
	}*/// não quer a cor seja alterada
	
	//metodo - Existe peça oponente
	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p != null && p.getColor() != color;
	}

}
