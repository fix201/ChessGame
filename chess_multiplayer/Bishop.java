package chess_multiplayer;

import java.io.Serializable;

public class Bishop extends piece {
	
	public Bishop(String name, int type, int id, int value, int[] pos)
	{
		super(name, type, id, value, pos);
		// TODO Auto-generated constructor stub
	}


	public boolean move_piece(int[] new_pos, piece[][] board) 
	{
			if((pos[0] == new_pos[0])||(pos[1] == new_pos[1]))
			{
				return false;
			}
			
			//check if diaognals same change
			int distance_x = Math.abs(pos[0] - new_pos[0]);
			int distance_y = Math.abs(pos[1] - new_pos[1]);
			if(distance_x != distance_y)
			{
				return false;
			}
			
			/*  CHECK LINE OF SIGHT  */
			
			//set the direction of line of sight to the target
			int direction_x = 1;
			int direction_y = 1;
			//if less, then direction will be backward in this axis
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
			for(int i = 1 ; i < distance_x; i++)
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


			return true;

	}

	public boolean exists()
	{
		return true;
	}

}
