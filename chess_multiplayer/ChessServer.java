package chess_multiplayer;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;
import javax.swing.*;
import chess_client.*;


import java.awt.*;
import java.io.IOException;
import java.lang.Runnable;

import java.util.*;

public class ChessServer extends AbstractServer {
	private JTextArea log;
	private JLabel status;
	private DatabaseFile databasefile;
	private boolean x;
	private chess_engine ce;
	private player player1;
	private player player2;
	private int num_players;
	private piece[][] board;

	public ChessServer() {
		super(8300);

		num_players = 0;

		try {
			databasefile = new DatabaseFile(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ChessServer(int port, int timeout) {

		super(port);
		num_players = 0;
		this.setTimeout(timeout);

		ArrayList<piece> player_1_pieces = new ArrayList<piece>();
		ArrayList<piece> player_2_pieces = new ArrayList<piece>();

		//build the standard board
		this.board = new piece[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				int[] pos = new int[2];
				pos[0] = i;
				pos[1] = j;
				//WHITE ROOk
				if ((i == 7) && (j == 0)) {
					board[i][j] = new Rook("Rook", 1, 0, 4, pos);
					player_1_pieces.add(board[i][j]);
					continue;
				}
				//WHITE KNIGHT
				if ((i == 7) && (j == 1)) {
					board[i][j] = new Knight("Knight", 2, 0, 3, pos);
					player_1_pieces.add(board[i][j]);
					continue;
				}
				//WHITE BISHOP
				if ((i == 7) && (j == 2)) {
					board[i][j] = new Bishop("Bishop", 3, 0, 3, pos);
					player_1_pieces.add(board[i][j]);
					continue;
				}
				//WHITE KING
				if ((i == 7) && (j == 3)) {
					board[i][j] = new Queen("Queen", 3, 0, 1, pos);
					player_1_pieces.add(board[i][j]);
					continue;
				}
				//WHITE QUEEN
				if ((i == 7) && (j == 4)) {
					board[i][j] = new King("King", 4, 0, 6, pos);
					player_1_pieces.add(board[i][j]);
					continue;
				}
				//WHITE BISHOP
				if ((i == 7) && (j == 5)) {
					board[i][j] = new Bishop("Bishop", 5, 0, 3, pos);
					player_1_pieces.add(board[i][j]);
					continue;
				}
				//WHITE KNIGHT
				if ((i == 7) && (j == 6)) {
					board[i][j] = new Knight("Knight", 6, 0, 3, pos);
					player_1_pieces.add(board[i][j]);
					continue;
				}
				//WHITE ROOk
				if ((i == 7) && (j == 7)) {
					board[i][j] = new Rook("Rook", 7, 0, 4, pos);
					player_1_pieces.add(board[i][j]);
					continue;
				}
				// WHITE PAWN 
				if (i == 6) {
					board[i][j] = new Pawn("Pawn", 8, 0, 1, pos);
					player_1_pieces.add(board[i][j]);
					continue;
				}

				//BLACK ROOk
				if ((i == 0) && (j == 0)) {
					board[i][j] = new Rook("Rook", 9, 1, 4, pos);
					player_2_pieces.add(board[i][j]);
					continue;
				}
				//BLACK KNIGHT
				if ((i == 0) && (j == 1)) {
					board[i][j] = new Knight("Knight", 10, 1, 3, pos);
					player_2_pieces.add(board[i][j]);

					continue;
				}
				//BLACK BISHOP
				if ((i == 0) && (j == 2)) {
					board[i][j] = new Bishop("Bishop", 11, 1, 3, pos);
					player_2_pieces.add(board[i][j]);

					continue;
				}
				//BLACK KING
				if ((i == 0) && (j == 3)) {
					board[i][j] = new Queen("Queen", 12, 1, 1, pos);
					player_2_pieces.add(board[i][j]);
					continue;

				}
				//BLACK QUEEN
				if ((i == 0) && (j == 4)) {
					board[i][j] = new King("King", 13, 1, 6, pos);
					player_2_pieces.add(board[i][j]);
					continue;
				}
				//BLACK BISHOP
				if ((i == 0) && (j == 5)) {
					board[i][j] = new Bishop("Bishop", 14, 1, 3, pos);
					player_2_pieces.add(board[i][j]);

					continue;
				}
				//BLACK KNIGHT
				if ((i == 0) && (j == 6)) {
					board[i][j] = new Knight("Knight", 15, 1, 3, pos);
					player_2_pieces.add(board[i][j]);

					continue;
				}
				//BLACK ROOk
				if ((i == 0) && (j == 7)) {
					board[i][j] = new Rook("Rook", 16, 1, 4, pos);
					player_2_pieces.add(board[i][j]);

					continue;
				}
				// Black PAWN 
				if (i == 1) {
					board[i][j] = new Pawn("Pawn", 17, 1, 1, pos);
					player_2_pieces.add(board[i][j]);

					continue;
				}
				board[i][j] = new piece("empty slot", -1, -1, -1, pos);
			}

		}

		//instantiate two players 
		player1 = new player("player1", 0, board, player_1_pieces);
		player2 = new player("player2", 1, board, player_2_pieces);

		this.ce = new chess_engine(board, player1, player2);

		try {
			this.listen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setLog(JTextArea log) {
		this.log = log;
	}

	public JTextArea getLog() {
		return log;
	}

	public void setStatus(JLabel status) {
		this.status = status;
	}

	public JLabel getStatus() {
		return status;
	}

	@Override
	protected void clientDisconnected(ConnectionToClient arg0) {
		this.stopListening();
	}

	@Override
	protected void handleMessageFromClient(Object arg0, ConnectionToClient arg1) {
		if (arg0 instanceof move) {
			String[][] pieces = new String[8][8];
			int[][] teams = new int[8][8];

			move mv = (move) arg0;
			if (ce.validate(mv)) {
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						pieces[i][j] = ce.get_board()[i][j].get_name();
						teams[i][j] = ce.get_board()[i][j].get_team();
					}
				}

				this.sendToAllClients(new response(pieces, teams));

			}

		}

		 if (arg0 instanceof LoginData)
		    {
			   try {
				arg1.sendToClient("START");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		       LoginData loginData = (LoginData)arg0;
		       System.out.print("dfasdfrfrf");
				//databasefile.checkLogin(loginData.getUserName(), loginData.getPassword());
				//boolean x = databasefile.checkLogin(loginData.getUserName(), loginData.getPassword());
				String[] login_data = databasefile.load_data();
				System.out.println("in login "+ x );
				
				 for(int i = 0; i < login_data.length; i++)
				  {
					 System.out.println(login_data[i]);
					  String[] temp = login_data[i].split("/");
					  System.out.println(temp[0]);
					  System.out.println(temp[1]);

					  if(!temp[1].contains(""+loginData.getUserName()) )
					  {
						 
						 if(!temp[1].contains(""+loginData.getPassword()) )
						 {
							 Error new_message = new Error("Password not exist", 2);
							 try
							{
								arg1.sendToClient(new_message);
							} catch (IOException e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						 }
						 else
						 {
							Error new_message = new Error("Welcome", 6);
							try
							{
								arg1.sendToClient(new_message);
							} catch (IOException e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						 }
					  }
					  else
						 {
							Error new_message = new Error("Welcomme", 6);
							try
							{
								arg1.sendToClient(new_message);
							} catch (IOException e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						 }
					  
				  }
		       
		    }
		        else if (arg0 instanceof CreateAccountData)
		        {
		           
		           CreateAccountData creatAccountData = (CreateAccountData)arg0;
		         
		           try {
		        	   
		        	 
					databasefile.writeCredentials(creatAccountData.getUserName(), creatAccountData.getPassword());
					x = databasefile.checkCredentials(creatAccountData.getUserName(), creatAccountData.getPassword() + "/n");
					creatAccountData.setCheck(x);
					if(x)
					{
						arg1.sendToClient(new Error("Username Exists", 1));
								
						return;
					}
					else
					{
						arg1.sendToClient(new Error(" ", 5));
						
					}
					
					System.out.println("in server "+ x );
					//arg1.sendToClient(creatAccountData);
		           } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		           System.out.println("Username/Password    " + creatAccountData.getUserName() + "/" + creatAccountData.getPassword() + "/" + creatAccountData.getrePassword());
		           
		        }
		}
	

	@Override
	protected void listeningException(Throwable exception) {

	}

	@Override
	protected void clientConnected(ConnectionToClient client) {

		if (num_players == 0) {
			try {
				client.sendToClient(player1);
				num_players = 1;
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (num_players == 1) {
			try {
				client.sendToClient(player2);
				num_players = 2;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	
	}

	public static void main(String[] args) {

		//chess_engine ce = new chess_engine();
		ChessServer server = new ChessServer(8300, 500);
	}

}
