package chess_multiplayer;

import java.io.Serializable;

public class Pawn extends piece{
	private boolean moved_once;
	public Pawn(String name, int type, int id, int value, int[] pos) {
		super(name, type, id, value, pos);
		
		moved_once = false;
		
	}

	@Override
	public boolean move_piece(int[] new_pos, piece[][] board) 
	{
		
		int pawn_step = 1;
		if(this.id == 0)
		{
			
			pawn_step = -1;
		}
		// if the piece moved once, it cant move two steps
		if(!moved_once)
		{
			if((new_pos[0] != pos[0] + pawn_step)||(Math.abs(new_pos[1] - pos[1]) > 1))
			{
				if(new_pos[0] != pos[0] + (pawn_step*2))
				{
					return false;
				}
				
			}
			
			
			if((new_pos[0] == this.get_pos()[0] ) && (new_pos[1] == this.get_pos()[1] ))
			{
				return false;
			}

		}
		else
		{
			
			if((new_pos[0] != pos[0] + pawn_step)||(Math.abs(new_pos[1] - pos[1]) > 1))
			{
				return false;
			}
			if((new_pos[0] == this.get_pos()[0] ) && (new_pos[1] == this.get_pos()[1] ))
			{
				return false;
			}

		}
		return true;
	
	}
	
	public boolean exists()
	{
		return true;
	}
}
