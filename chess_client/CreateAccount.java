package chess_client;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.*;
import javax.swing.SwingConstants;

public class CreateAccount extends JPanel
{
  private JTextField username;
  private JPasswordField password;
  private JPasswordField repassword;
  private JLabel status;
  private CardLayout cl;
  private ChessClient client;
  public JTextField getUserName()
  {
    return username;
  }
  
  public JPasswordField getPassword()
  {
    return password;
  }
  public JPasswordField getrePassword()
  {
    return repassword;
  }
  public JLabel getlabel()
  {
    return status;
  }
  public CardLayout getCL()
  {
	  return cl;
  }
  
  public CreateAccount()
  {}
  public CreateAccount(CardLayout cl, JPanel container, ChessClient client)
  {

	this.cl = cl;
    JPanel inner = new JPanel(new GridLayout(5,3));
    status = new JLabel("");
    JLabel blank = new JLabel("");
    JLabel jlabel = new JLabel("Username: ");
    username = new JTextField(15);
    JLabel jlabel1 = new JLabel("Password: ");
    password = new JPasswordField(15);
    JLabel jlabel2 = new JLabel("Re-Enter Password: ");
    repassword = new JPasswordField(15);
    
    JButton submit = new JButton("Submit");
    submit.setPreferredSize(new Dimension(30, 30));

    CreateAccountControl lc = new CreateAccountControl(cl,container,client, status);
    submit.addActionListener(lc);
   

    
    inner.add(this.status);
    inner.add(blank);
    inner.add(jlabel);
    inner.add(username);
    inner.add(jlabel1);
    inner.add(password);
    inner.add(jlabel2);
    inner.add(repassword);
    inner.add(submit);
    this.add(inner);
    
    

  }

 

  
 
}
