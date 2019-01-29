package chess_client;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.*;

public class LoginPanel extends JPanel
{
  private JTextField username;
  private JPasswordField password;
  private JLabel jlabel;
  public JTextField getUserName()
  {
    return username;
  }
  
  public JPasswordField getPassword()
  {
    return password;
  }
  public JLabel getlabel()
  {
    return jlabel;
  }
  
  public LoginPanel(CardLayout cl, JPanel container, ChessClient client)
  {
    
    JPanel inner = new JPanel(new GridLayout(5,2));
    
    jlabel = new JLabel("Login",JLabel.CENTER);
    username = new JTextField(15);
   

    password = new JPasswordField(15);
    JButton submit = new JButton("Submit");
    JButton previous = new JButton("Cancel");
    submit.setPreferredSize(new Dimension(30,30));
    previous.setPreferredSize(new Dimension(30,30));
    
    LoginControl lc = new LoginControl(cl,container,client);
    submit.addActionListener(lc);
    previous.addActionListener(lc);
    
    
    
    inner.add(jlabel);
    inner.add(username);
    inner.add(password);
    inner.add(submit);
    inner.add(previous);
    this.add(inner);
    
  }
  
 
}
