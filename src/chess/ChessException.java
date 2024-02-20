package chess;

import boardgame.BoardException;

//public class ChessException extends RuntimeException{
public class ChessException extends BoardException{

	private static final long serialVersionUID = 1L;
	
	public ChessException(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
	}

}
