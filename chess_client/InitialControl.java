package chess_client;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class InitialControl implements ActionListener
{
  private CardLayout cl;
  private JPanel container;
  
  public InitialControl(CardLayout cl, JPanel container)
  {
    this.cl = cl;
    this.container = container;
  }
  
  public void actionPerformed(ActionEvent ae)
  {
    String command = ae.getActionCommand();
    
    if (command.equals("Login"))
    {
      cl.show(container, "2");
    }
    else if (command.equals("Create"))
    {
      cl.show(container, "3");
    }
    
  }

}
