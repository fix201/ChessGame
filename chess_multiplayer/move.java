package chess_multiplayer;

import java.io.Serializable;

public class move implements Serializable{
	
	private int turn;
	private int id;

	private int  current_pos_x,  current_pos_y,  new_pos_x,  new_pos_y;
	

	public move(int turn, int current_pos_x, int current_pos_y, int new_pos_x, int new_pos_y)
	{
		this.turn = turn;
		//this.moved_piece = moved_piece; 
		this.new_pos_x = new_pos_x;
		this.new_pos_y = new_pos_y;
		this.current_pos_x = current_pos_x;
		this.current_pos_y = current_pos_y;
	}

	public int get_turn()
	{
		return this.turn;
	}
	public int get_id()
	{
		return this.id;
	}
	public int[] get_new_pos()
	{
		int[] temp = {new_pos_x, new_pos_y};
		return temp;
	}
	public int[] get_current_pos()
	{
		int[] temp = {current_pos_x, current_pos_y};
		return temp;
	}
	
}
