package chess_multiplayer;

import java.io.Serializable;

public class King extends piece
{
	
	public King(String name, int type, int id, int value, int[] pos)
	{
		super(name, type, id, value,pos);
		moved_once = false;
 	}

	@Override
	public boolean move_piece(int[] new_pos, piece[][] board) 
	{ 
		//move only one square
		if((Math.abs(new_pos[0] - this.get_pos()[0] )>1) || (Math.abs(new_pos[1] - this.get_pos()[1]) > 1))
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
