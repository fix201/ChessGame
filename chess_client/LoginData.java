package chess_client;

import java.io.Serializable;

public class LoginData implements Serializable 

{
  //Private data fields declared here
	private String username;
	private String password;
	
  //Public getters/setters go here
	public String  getUserName()
	{
		return this.username;
	}
	public void  setUserName(String username )
	{
		this.username = username;
	}
	
	public String  getPassword()
	{
		return this.password;
	}
	public void  setPassword(String password)
	{
		this.password = password;
	}
	
	
  public LoginData(String username, String password)
  {
    this.username = username;
    this.password = password;
  }

	

}
