package chess_multiplayer;

import java.io.Serializable;

public class Error implements Serializable
{
	private String Err_message;
	private int status_code;
	
	public Error(String err_message, int i ) 
	{
		this.Err_message = err_message;
		this.status_code = i;
	}

	public String get_message()
	{
		return this.Err_message;
		
		
	}
	public int get_status_code()
	{
		return this.status_code;
	}



}

