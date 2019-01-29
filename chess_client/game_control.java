package chess_client;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

public class game_control implements MouseListener {

	private JLabel[][] labels;
	private ChessClient client;
	private chessGUI gui;

	public game_control(JLabel[][] labels2, ChessClient client, chessGUI gui) {
		this.labels = labels2;
		this.client = client;
		this.gui = gui;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
			

				if (e.getSource() == labels[i][j]) {
					
					System.out.println(i + "," + j);

					if (gui.isClicked == false) {
						if (this.client.get_id() == 0) {
							gui.Old_pos[0] = i;
							gui.Old_pos[1] = j;
							gui.isClicked = true;
							labels[i][j].setBorder(BorderFactory.createDashedBorder(Color.red));
						} else {
							gui.Old_pos[0] = 7 - i;
							gui.Old_pos[1] = 7 - j;
							labels[i][j].setBorder(BorderFactory.createDashedBorder(Color.red));
							gui.isClicked = true;
						}

					} else {
						if (this.client.get_id() == 0) {
							gui.New_pos[0] = i;
							gui.New_pos[1] = j;
							labels[gui.Old_pos[0]][gui.Old_pos[1]].setBorder(BorderFactory.createEmptyBorder());							gui.isClicked = false;
							this.client.send_move(gui.Old_pos, gui.New_pos);

						} else {
							gui.New_pos[0] = 7 - i;
							gui.New_pos[1] = 7 - j;
							labels[gui.Old_pos[0]][gui.Old_pos[1]].setBorder(BorderFactory.createEmptyBorder());
							gui.isClicked = false;
							this.client.send_move(gui.Old_pos, gui.New_pos);

						}

					}
				}
			}
	}

	private void setMousePosition(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		//System.out.print("In pressed");
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
			

				if (e.getSource() == labels[i][j]) {
					labels[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
					
				}
			}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
			}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
