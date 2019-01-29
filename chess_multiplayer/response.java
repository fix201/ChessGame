package chess_multiplayer;

import java.io.Serializable;

public class response implements Serializable
{
	public String[][] pieces;
	public int[][] teams;
	
	public response(String[][] pieces, int[][] teams)
	{
		this.pieces = pieces;
		this.teams = teams;
	}
	
	
}
