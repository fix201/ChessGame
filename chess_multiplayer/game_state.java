package chess_multiplayer;

import java.io.Serializable;

public class game_state implements Serializable{
	
	private int turn;
	private player[] players;
	private piece[][] board;
	
	
	public game_state(piece[][] board, player player1, player player2)
	{
		this.turn = 0;
		this.players = new player[2];
		players[0] = player1;
		players[1] = player2;
	}
	
	public int get_turn()
	{
		return turn;
	}
	public void set_board(piece[][] board)
	{
		this.board = board;
	}
	public player get_player(int i)
	{
		return players[i];
	}
	public void set_turn(int turn)
	{
		this.turn = turn;
	}
	public piece[][] get_board()
	{
		return this.board;
	}

}
