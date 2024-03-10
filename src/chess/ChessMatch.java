package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	private boolean check;//verificar//boolean por padrão começa com falso
	private boolean checkMate;
							
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
		//check = false;// seqiser iniciar aqui 
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
	
	public boolean getCheck() {
		return check;// pq faço os metodos gets das minhas variáveis 
					// para poder pegar elas em outras classes de outros pacotes 
	}
	
	public boolean getCheckMate() {
		return checkMate;
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
		
		//se jogador atual ficou em cheque
		if(testCheck(currentPlayer)) {
			//desmove uma peça
			undoMove(source, target, capturedPiece);
			throw new ChessException("You can´t put yourself in check");//Você não pode se colocar em cheque
		}
		
		//se o oponente do jogador atual estiver em cheque verdadeiro se não falso
		check = (testCheck(opponent(currentPlayer))) ? true : false;
		
		//testar se jogo acabou
		//se o oponente da peça q mexeu ficou em cheque mate 
		if (testCheckMate(opponent(currentPlayer))) {
			checkMate = true;
		}
		else {
			nextTurn();//jogador da vez
		}
		return (ChessPiece) capturedPiece;
	}
	
	//metodo mover 
	private Piece makeMove(Position source, Position target) {//recebendo posição de origem e destino
		
		//Piece p = board.removePiece(source);//tirando a peça de origem
		ChessPiece p = (ChessPiece)board.removePiece(source);//tirando a peça de origem
		
		p.increaseMoveCount();
		
		Piece capturedPiece = board.removePiece(target);//colocando peça destino
		board.placePiece(p, target);
		
		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		return capturedPiece;
	}
	
	//Metodo desfazer movimento é ao contrário mover
	private void  undoMove(Position source, Position target, Piece captured) {
		
		//Piece p = board.removePiece(target);//tirando a peça de destino
		ChessPiece p = (ChessPiece)board.removePiece(target);//tirando a peça de destino
		
		p.decreaseMoveCount();
		
		Piece capturedPiece = board.removePiece(source);//colocando peça origem
		
		if (capturedPiece != null) {
			board.placePiece(capturedPiece, target);//
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
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
	//adivesário
	private Color opponent(Color color) {
		//se
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private ChessPiece king(Color color) {
		//pq eu usei dowlcast (ChessPiece) pq a color esta na ChessPiece
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color).collect(Collectors.toList());
		
		//vamos percorrer a lista
		for(Piece p : list) {
			if(p instanceof King) {
				return (ChessPiece)p;
			}
		}
		throw new IllegalStateException("There is no" + color + " king on the board");
	}
	
	//Verificação de teste
	private boolean testCheck(Color color) {
		Position kingPosition = king(color).getChessPosition().toPosition();
		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == opponent(color)).collect(Collectors.toList());
	
		for (Piece p : opponentPieces) {
			boolean[][] mat = p.possibleMoves();//possilveis movimentos
			if(mat[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;
			}
		}
		return false;
	}
	
	//chqeu-mate teste
	private boolean testCheckMate(Color color) {
		//testa se não esta em cheque
		if(!testCheck(color)) {
			return false;
		}
		
		//lista todos cores agora para filtrar tudo
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color).collect(Collectors.toList());
		
		//vamos percorrer nossa lista usando for
		//pecorre todas as peças da minha lista
		for(Piece p : list) {
			//se exitir alguma peca em cheque vai retornar false
			boolean mat[][] = p.possibleMoves();//saber possiveis movimentos
			//vamos pecorrer a matriz
			for(int i = 0; i < board.getRows(); i++) {//pecorre as linhas
				for(int j = 0; j < board.getColumns(); j++ ) {//percorre minhas colunas
					if(mat[i][j]) {
						//pegar o position
						Position  source = ((ChessPiece)p).getChessPosition().toPosition();//converti um chess em position
						Position target = new Position(i, j);
						
						//fazer o movimento
						Piece capturedPiece = makeMove(source, target);
						
						//vamos testar agora
						boolean testCheck = testCheck(color);
						
						//desfazer movimento 
						undoMove(source, target, capturedPiece);
						
						//testar
						if(!testCheck) {//se não esta em movimento 
							return false;
						}
					}
				}
				
			}
		}
		
		return true;
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

		/*placeNewPiece('c', 1, new Rook(board, Color.WHITE));
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
		placeNewPiece('d', 8, new King(board, Color.BLACK));*/
		
		placeNewPiece('h', 7, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE));

        placeNewPiece('b', 8, new Rook(board, Color.BLACK));
        placeNewPiece('a', 8, new King(board, Color.BLACK));
	}
}
