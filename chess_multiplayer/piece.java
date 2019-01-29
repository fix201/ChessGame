package chess_multiplayer;

import java.io.Serializable;

public class piece implements Serializable{
	
	String name;
	int[] pos;
	int id;
	int value;
	boolean moved_once;

	public piece(String name, int type, int id, int value, int[] pos)
	{
		this.name = name;
		this.id = id; 
		this.value = value;
		this.pos = pos;
		this.moved_once = false;
	}
	public boolean exists()
	{
		return false;
	}
	public int get_team()
	{
		return this.id;
	}
	public String get_name()
	{
		return this.name;
	}
	public int[] get_pos()
	{
		return this.pos;
	}
	public void set_pos(int[] pos)
	{
		this.pos = pos;
	}
	public boolean move_piece(int[] new_pos, piece[][] board)
	{
		return false;
	}
	public boolean is_moved_once()
	{
		return moved_once;
	}
	public int get_id()
	{
		return this.id;
	}
	public void moved()
	{
		this.moved_once = true;
	}
		}
