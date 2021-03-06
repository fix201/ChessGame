package chess_multiplayer;

import java.io.Serializable;

public class Rook extends piece  {

	public Rook(String name, int type, int id, int value, int[] pos) 
	{
		super(name, type, id, value, pos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean move_piece(int[] new_pos, piece[][] board) 
	{
		int distance = 0;
		//rook basic rules (moves straight right or north) -> only x or y axis move 
		if((this.get_pos()[0] - new_pos[0] != 0)&&(this.get_pos()[1] - new_pos[1] != 0))
		{
			return false;
		}

		
		int direction_x = 1;
		int direction_y = 1;
		if(pos[0] == new_pos[0])
		{
			direction_x = 0;
			distance = Math.abs(pos[1] - new_pos[1]);
		}
		if(pos[1] == new_pos[1])
		{
			direction_y = 0;
			distance = Math.abs(pos[0] - new_pos[0]);
		}
		if(pos[0] > new_pos[0])
		{
			direction_x = -1;
		}
		if(pos[1] > new_pos[1])
		{
			direction_y = -1;
		}
		int current_pos_x = pos[0];
		int current_pos_y = pos[1]; 
		//walk in the direction of line of sight
		for(int i =1 ; i < distance; i++)
		{
			current_pos_x = pos[0] + (i * direction_x);
			current_pos_y = pos[1] + (i * direction_y);	
			if(board[current_pos_x][current_pos_y].exists())
			{
				return false;
			}	
		}
		if((new_pos[0] == this.get_pos()[0] ) && (new_pos[1] == this.get_pos()[1] ))
		{
			return false;
		}


		//check if piece is moved
		
		return true;
	}
	
	public boolean exists()
	{
		return true;
	}


}
