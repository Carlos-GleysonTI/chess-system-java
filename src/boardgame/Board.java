package boardgame;

//Classe Taboleiro
public class Board {
	
	private int rows;//linhas
	private int columns;//colunas
	private Piece[][] pieces; // uma matriz de pecas
	
	//criando meu taboleiro
	public Board(int rows, int columns) {//meu tabeleiro vai receber apenas linhas e colunas
		//super();
		
		//vamos tratar isso 
		if (rows < 1 || columns < 1) {
			throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
			// lança uma excerção q nem linhas e nem colunas podem serem > 0
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece [rows][columns];//aqui eu informo minhas de peca de com linhas e colunas
	}

	public int getRows() {
		return rows;
	}

	/*public void setRows(int rows) {
		this.rows = rows;
	}*/ // retirar set uma vez criada a linha eu não quero q ela seja alterada

	public int getColumns() {
		return columns;
	}

	/*public void setColumns(int columns) {
		this.columns = columns;
	}*/// da mesam forma as colunas
	
	
	public Piece piece( int row, int column) {
		if (!positionExists(row,column)) {//! significa não p linha e coluna 
			throw new BoardException("Position not on the board!");
		}
		return pieces[row][column];
	}
	
	public Piece piece(Position position) {
		if (!positionExists(position)) {//! significa não p posição
			throw new BoardException("Position not on the board!");
									//Posição não no tabuleiro!
		}
		return pieces[position.getRow()][position.getColumn()];
	}
	
	//colocar a peça
	public void placePiece(Piece piece, Position position) {
		if (thereIsAPiece(position)) {
			throw new BoardException("There is already a piece on position " + position);
									//Já existe uma peça em posição
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}
	
	//método p remover uma peça
	public Piece removePiece(Position position) {
		if (!positionExists(position)) {//! significa não p posição
			throw new BoardException("Position not on the board!");
									//Posição não no tabuleiro!
		}
		
		if(piece(position) == null) {
			return null;
		}
		
		Piece aux = piece(position);
		aux.position = null;
		pieces[position.getRow()][position.getColumn()] = null;
		return aux;
	}
	
	//para saber se a posição existe
	private boolean positionExists(int row , int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}
	
	public boolean positionExists(Position position) {//boolean verdadeiro ou falso
		return positionExists(position.getRow(), position.getColumn());
	}
	
	//Há um peça
	public boolean thereIsAPiece(Position position) {
		if (!positionExists(position)) {//testar se a posição existe
			throw new BoardException("Position not on the board!");
		}
		return piece(position) != null;// se for diferente null existe uma peça
	}

}
