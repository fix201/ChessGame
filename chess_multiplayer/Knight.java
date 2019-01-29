package chess_multiplayer;

import java.io.Serializable;

public class Knight extends piece 
{

	public Knight(String name, int type, int id, int value, int[] pos)
	{
		super(name, type, id, value, pos);
	}

	@Override
	public boolean move_piece(int[] new_pos, piece[][] board) 
	{
		//if x axis not moved by 1 unit and not moved by 2 units
		if((Math.abs(this.get_pos()[0] - new_pos[0]) != 1)&&(Math.abs(this.get_pos()[0] - new_pos[0])) != 2)
		{
			return false; 
		}
		//if y axis not moved by 1 unit and not moved by 2 units
		if((Math.abs(this.get_pos()[1] - new_pos[1]) != 1)&&(this.get_pos()[1] - new_pos[1]) != 2)
		{
			return false; 
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
