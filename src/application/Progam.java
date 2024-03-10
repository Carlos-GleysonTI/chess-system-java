package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import boardgame.Board;
import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;


public class Progam {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		List<ChessPiece> captured = new ArrayList<>();
		
		//while(true) {
		//enquanto a partida não estiver em cheque marte
		while(!chessMatch.getCheckMate()) {
			
			try {
		
				UI.clearScreen();//metodo limpar a tela
				
				//UI.printBoard(chessMatch.getPieces());
				UI.printMatch(chessMatch, captured);
				
				//posição de origem
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc);
				
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();// limpar a tela
				UI.printBoard(chessMatch.getPieces(), possibleMoves);
				
				//posi de destino
				System.out.println();
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(sc);
				
				//chamada
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
				
				if(capturedPiece != null) {
					captured.add(capturedPiece);
				}
			
			}
			catch (ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();//para esperar eu dar enter
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();//para esperar eu dar enter
			}
		
		}
		
		//limpar a tela
		UI.clearScreen();
		//mostra resultado
		UI.printMatch(chessMatch, captured);
	}

}
