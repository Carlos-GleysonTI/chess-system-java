package boardgame;

//Classe Perça
public class Piece {
	
	protected Position position;//um pra um
	private Board board;
	
	public Piece(Board board) {//aqui diz pra criar uma perça tenho q informar um tabuleiro (board)
		//super();
		this.board = board;
		position = null; // minha posição rescem criada vai ser null
	}

	protected Board getBoard() {//protect, ou seja, somente classes do mesmo pacotes e sunclasses podem acessar
		return board;
	}

	/*public void setBoard(Board board) {
		this.board = board;
	}*/ // retirei o set pq o meu tabuleiro não pode ser alterado
	
	
	
}
