package chess_client;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import chess_multiplayer.Bishop;
import chess_multiplayer.King;
import chess_multiplayer.Knight;
import chess_multiplayer.Pawn;
import chess_multiplayer.Queen;
import chess_multiplayer.Rook;
import chess_multiplayer.piece;

public class chessGUI extends JFrame {

	public static int mouseX, mouseY;
	private ChessClient client;
	private JLabel[][] labels = new JLabel[8][8];
	public int Old_pos[] = new int[2];
	@SuppressWarnings("rawtypes")
	Vector old_pos = new Vector();
	public int New_pos[] = new int[2];
	private int oldi, oldj = 0;
	public boolean isClicked = false;
	private boolean isClicked2 = false;
	private boolean[][] lblClicked = new boolean[8][8];
	private Thread thread;
	private chess_client.InitialPanel view1; //3 views
	private JPanel view2;
	private CreateAccount view3;
	private InitialPanel view4;
	private JPanel container;

	public chessGUI(String title) {

		this.setTitle("Chess");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CardLayout cl = new CardLayout();
		container = new JPanel(cl);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 800, 800);
		panel.setLayout(null);

		int Xaxis = 0, Yaxis = -100;

		this.client = new ChessClient(this, "127.0.0.1", 8300, cl, container);

		game_control gc = new game_control(labels, client, this);
		for (int i = 0; i < 8; i++) {
			Xaxis = 0;
			Yaxis = Yaxis + 100;
			for (int j = 0; j < 8; j++) {
				labels[i][j] = new JLabel("");
				labels[i][j].setBounds(Xaxis, Yaxis, 100, 100);
				labels[i][j].setOpaque(false);
				labels[i][j].setBackground(Color.red);

				Xaxis = Xaxis + 100;
				labels[i][j].addMouseListener(gc);

				panel.add(labels[i][j]);
			}
		}

		JLabel label = new JLabel("", JLabel.CENTER);
		label.setIcon(new ImageIcon(chessGUI.class.getResource("/chess_client/7344419_orig.png")));
		label.setBounds(0, 0, 800, 800);
		view1 = new InitialPanel(cl, container);
		view2 = new LoginPanel(cl, container, client);
		view3 = new CreateAccount(cl, container, client);
		container.add(view1, "1");
		container.add(view2, "2");
		container.add(view3, "3");
		panel.add(label);
		panel.repaint();
		container.add(panel, "4");

		this.add(container, BorderLayout.CENTER);
		cl.show(container, "4");

		this.setSize(800, 800);
		this.setVisible(true);

	}

	public void drawBoard(String board[][], int[][] teams) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j].equals("King") && teams[i][j] == 0) {
					labels[i][j].setIcon(new ImageIcon(chessGUI.class.getResource("/chess_client/w5.png")));
				} else if (board[i][j].equals("Queen") && teams[i][j] == 0) {
					labels[i][j].setIcon(new ImageIcon(chessGUI.class.getResource("/chess_client/w4.png")));
				} else if (board[i][j].equals("Bishop") && teams[i][j] == 0) {
					labels[i][j].setIcon(new ImageIcon(chessGUI.class.getResource("/chess_client/w3.png")));
				} else if (board[i][j].equals("Knight") && teams[i][j] == 0) {
					labels[i][j].setIcon(new ImageIcon(chessGUI.class.getResource("/chess_client/w2.png")));
				} else if (board[i][j].equals("Pawn") && teams[i][j] == 0) {
					labels[i][j].setIcon(new ImageIcon(chessGUI.class.getResource("/chess_client/wp.png")));
				} else if (board[i][j].equals("Rook") && teams[i][j] == 0) {
					labels[i][j].setIcon(new ImageIcon(chessGUI.class.getResource("/chess_client/w1.png")));
				}
				/////////////////////////////////////////////////////////////////////////////////////////////

				else if (board[i][j].equals("King") && teams[i][j] == 1) {
					labels[i][j].setIcon(new ImageIcon(chessGUI.class.getResource("/chess_client/d5.png")));
				} else if (board[i][j].equals("Queen") && teams[i][j] == 1) {
					labels[i][j].setIcon(new ImageIcon(chessGUI.class.getResource("/chess_client/d4.png")));
				} else if (board[i][j].equals("Bishop") && teams[i][j] == 1) {
					labels[i][j].setIcon(new ImageIcon(chessGUI.class.getResource("/chess_client/d3.png")));
				} else if (board[i][j].equals("Knight") && teams[i][j] == 1) {
					labels[i][j].setIcon(new ImageIcon(chessGUI.class.getResource("/chess_client/d2.png")));
				} else if (board[i][j].equals("Pawn") && teams[i][j] == 1) {
					labels[i][j].setIcon(new ImageIcon(chessGUI.class.getResource("/chess_client/dp.png")));
				} else if (board[i][j].equals("Rook") && teams[i][j] == 1) {
					labels[i][j].setIcon(new ImageIcon(chessGUI.class.getResource("/chess_client/d1.png")));
				} else if (board[i][j].equals("empty slot")) {
					labels[i][j].setIcon(null);
				}
			}

		}

	}

	public void drawBoardReverse(String board[][], int[][] teams) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j].equals("King") && teams[i][j] == 0) {
					//labels[7-i][7-j] = new JButton(new ImageIcon(chessGUI.class.getResource("/chess_client/w5.png")));
					labels[7 - i][7 - j].setIcon(new ImageIcon(chessGUI.class.getResource("/chess_client/w5.png")));
				} else if (board[i][j].equals("Queen") && teams[i][j] == 0) {
					//labels[7-i][7-j] = new JButton(new ImageIcon(chessGUI.class.getResource("/chess_client/w4.png")));
					labels[7 - i][7 - j].setIcon(new ImageIcon(chessGUI.class.getResource("/chess_client/w4.png")));
				} else if (board[i][j].equals("Bishop") && teams[i][j] == 0) {
					//labels[7-i][7-j] = new JButton(new ImageIcon(chessGUI.class.getResource("/chess_client/w3.png")));
					labels[7 - i][7 - j].setIcon(new ImageIcon(chessGUI.class.getResource("/chess_client/w3.png")));
				} else if (board[i][j].equals("Knight") && teams[i][j] == 0) {
					//labels[7-i][7-j] = new JButton(new ImageIcon(chessGUI.class.getResource("/chess_client/w2.png")));
					labels[7 - i][7 - j].setIcon(new ImageIcon(chessGUI.class.getResource("/chess_client/w2.png")));
				} else if (board[i][j].equals("Pawn") && teams[i][j] == 0) {
					//labels[7-i][7-j] = new JButton(new ImageIcon(chessGUI.class.getResource("/chess_client/wp.png")));
					labels[7 - i][7 - j].setIcon(new ImageIcon(chessGUI.class.getResource("/chess_client/wp.png")));
				} else if (board[i][j].equals("Rook") && teams[i][j] == 0) {
					//labels[7-i][7-j]= new JButton(new ImageIcon(chessGUI.class.getResource("/chess_client/w1.png")));
					labels[7 - i][7 - j].setIcon(new ImageIcon(chessGUI.class.getResource("/chess_client/w1.png")));
				}
				/////////////////////////////////////////////////////////////////////////////////////////////

				else if (board[i][j].equals("King") && teams[i][j] == 1) {
					//labels[7-i][7-j]= new JButton(new ImageIcon(chessGUI.class.getResource("/chess_client/d5.png")));
					labels[7 - i][7 - j].setIcon(new ImageIcon(chessGUI.class.getResource("/chess_client/d5.png")));
				} else if (board[i][j].equals("Queen") && teams[i][j] == 1) {
					//labels[7-i][7-j]= new JButton(new ImageIcon(chessGUI.class.getResource("/chess_client/d4.png")));
					labels[7 - i][7 - j].setIcon(new ImageIcon(chessGUI.class.getResource("/chess_client/d4.png")));
				} else if (board[i][j].equals("Bishop") && teams[i][j] == 1) {
					//labels[7-i][7-j]= new JButton(new ImageIcon(chessGUI.class.getResource("/chess_client/d3.png")));
					labels[7 - i][7 - j].setIcon(new ImageIcon(chessGUI.class.getResource("/chess_client/d3.png")));
				} else if (board[i][j].equals("Knight") && teams[i][j] == 1) {
					//labels[7-i][7-j]= new JButton(new ImageIcon(chessGUI.class.getResource("/chess_client/d2.png")));
					labels[7 - i][7 - j].setIcon(new ImageIcon(chessGUI.class.getResource("/chess_client/d2.png")));
				} else if (board[i][j].equals("Pawn") && teams[i][j] == 1) {
					//labels[7-i][7-j]= new JButton(new ImageIcon(chessGUI.class.getResource("/chess_client/dp.png")));
					labels[7 - i][7 - j].setIcon(new ImageIcon(chessGUI.class.getResource("/chess_client/dp.png")));
				} else if (board[i][j].equals("Rook") && teams[i][j] == 1) {
					//labels[7-i][7-j]= new JButton(new ImageIcon(chessGUI.class.getResource("/chess_client/d1.png")));
					labels[7 - i][7 - j].setIcon(new ImageIcon(chessGUI.class.getResource("/chess_client/d1.png")));
				} else if (board[i][j].equals("empty slot")) {
					labels[7 - i][7 - j].setIcon(null);
				}
			}
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		chessGUI gui = new chessGUI("");

	}

}
