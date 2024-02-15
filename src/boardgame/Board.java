package boardgame;

//Classe Taboleiro
public class Board {
	
	private int rows;//linhas
	private int columns;//colunas
	private Piece[][] pieces; // uma matriz de pecas
	
	public Board(int rows, int columns) {//meu tabeleiro vai receber apenas linhas e colunas
		//super();
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece [rows][columns];//aqui eu informo minhas de peca de com linhas e colunas
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	public Piece piece( int row, int column) {
		return pieces[row][column];
	}
	
	public Piece piece(Position position) {
		return pieces[position.getRow()][position.getColumn()];
	}
	
	//colocar a peça
	public void placePiece(Piece piece, Position position) {
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}

}
