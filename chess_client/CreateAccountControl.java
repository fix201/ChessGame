package chess_client;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class CreateAccountControl implements ActionListener
{
  private CardLayout cl;
  private JPanel container;
  private ChessClient client;
  private JLabel label;

 
  public void setLabel(JLabel label)
  {
	  this.label = label;
  }
  public CreateAccountControl(CardLayout cl, JPanel container,ChessClient client, JLabel status)
  {
    this.cl = cl;
    this.container = container;
    this.client = client;
    this.label = status;
  }
  
  public void actionPerformed(ActionEvent ae)
  {
    //
    
    String command = ae.getActionCommand();
    int count = container.getComponentCount();
    CreateAccount lp = (CreateAccount)container.getComponent(2);
    
    if(command.equals("Submit"))
    {
    	
    	String username = lp.getUserName().getText();
    	String password = lp.getPassword().getText();
    	String repassword = lp.getrePassword().getText();
    	
    	if(username.equals(""))
    	{
    		label.setText("");
    		label.setText(label.getText() + "Username field is empty");
    	}
    	else if(password.equals(""))
    	{
    		label.setText("");
    		label.setText(label.getText() + "Password field is empty");
    	}
    	else if(password.length() < 6)
    	{
    		label.setText("");
    		label.setText(label.getText() + "Password should be atleast 6 characters");
    	}
    	else if(!password.equals(repassword))
    	{
    		label.setText("");
    		label.setText(label.getText() + "Passwords do not match");
    	}
    	else
    	{
    	CreateAccountData createAccountData = new CreateAccountData(username, password, repassword);
    	
    	try {
    		client.sendToServer(createAccountData);
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
   
    	}
    	
    	
    	
    }
    
    
   
    
    

    
    //ADD YOUR CODE HERE
    //1. Extract the form data
    
    //2. Create LoginData object using the extracted form data
    
    //3. Send LoginData object to Server
    
    //4. Server does not have to send an ack back to Client for this lab (but lab5out will)
   
    
    
    
    
    
    
  }
 
  public void checker(boolean bool)
  {
	  System.out.print("In checker " + bool);
	CreateAccount lp = (CreateAccount)container.getComponent(2);
	System.out.println("In checker");
  	if(bool)
  	{
  		label.setText("Username already exist");
  	}
  	else
  	{
  		System.out.print("in else");
  		this.cl = lp.getCL();
  		cl.show(container, "1");
  	}
  }
 
}
