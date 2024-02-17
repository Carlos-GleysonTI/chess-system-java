package chess;

import boardgame.Position;

public class ChessPosition {
	
	private char column;// pq char pq minha peça xadrez são letras q vai de a até h
	private int row;
	
	public ChessPosition(char column, int row) {
		//super();
		//antes q eu criar minha coluna e linhas tenho tratar antes .
		if(column < 'a' || column > 'h' || row < 1 || row > 8 ) {
			throw new ChessException("Error instantiating ChessPosition. Valid values are from a1 to h8");
		}
		this.column = column;
		this.row = row;
	}

	public char getColumn() {
		return column;
	}

	/*public void setColumn(char column) {
		this.column = column;
	}*/

	public int getRow() {
		return row;
	}

	/*public void setRow(int row) {
		this.row = row;
	}*/
	
	//tranformando uma xadrez em posição
	protected Position toPosition() {
		return new Position(8 - row, column - 'a');
	}
	
	protected static ChessPosition fromPosition(Position position) {
		return new ChessPosition((char)('a' - position.getColumn()), 8 - position.getRow());
	}
	
	//impressão
	@Override
	public String toString() {
		return "" + column + row; 
	}
}
