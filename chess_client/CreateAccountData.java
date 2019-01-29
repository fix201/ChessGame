package chess_client;

import java.io.Serializable;

import javax.swing.JLabel;

public class CreateAccountData implements Serializable 

{
  //Private data fields declared here
	private String username;
	private String password;
	private String repassword;
	private boolean check;
	
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
	public String  getrePassword()
	{
		return this.repassword;
	}
	public void  setrePassword(String repassword)
	{
		this.repassword = repassword;
	}
	public void  setCheck(boolean bool)
	{
		this.check = bool;
	}
	public boolean getCheck()
	{
		return check;
	}
	
	public CreateAccountData()
	{}
  public CreateAccountData(String username, String password, String repassword)
  {
    this.username = username;
    this.password = password;
    this.repassword = repassword;
  }
 

	

}
