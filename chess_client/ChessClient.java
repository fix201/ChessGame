package chess_client;

import chess_multiplayer.game_state;
import chess_multiplayer.move;
import chess_multiplayer.piece;

import java.awt.CardLayout;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ocsf.client.AbstractClient;
import chess_multiplayer.player;
import chess_multiplayer.response;

public class ChessClient extends AbstractClient {
	private chessGUI gui;
	private CardLayout cl;
	private JPanel container;
	private player user;
	private int id;

	public ChessClient(chessGUI clientGUI, String host_name, int port, CardLayout cl, JPanel container) {
		super(host_name, port);
		this.gui = clientGUI;
		this.cl = cl;
		this.container = container;

		try {
			this.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void set_user(player user) {
		this.user = user;
	}

	@Override
	public void handleMessageFromServer(Object arg0) {

		if (arg0 instanceof String) {
			if (arg0.equals("START")) {
				this.cl.show(this.container, "4");
			}
			if (arg0.equals("SUCCESS")) {
				this.cl.show(this.container, "1");
			}
		}
		//this will be recieved when logged in, server will return the player profile
		if (arg0 instanceof player) {

			String[][] pieces = new String[8][8];
			int[][] teams = new int[8][8];
			player p = (player) arg0;
			this.set_user(p);
			this.set_id(p.get_id());
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					pieces[i][j] = p.get_board()[i][j].get_name();
					teams[i][j] = p.get_board()[i][j].get_team();
				}
			}
			
			if (this.user.get_id() == 0) {
				this.gui.drawBoard(pieces, teams);

			}
			//change perspectove if different player
			else {
				this.gui.drawBoardReverse(pieces, teams);
			}

		}

		if (arg0 instanceof response) {

			response res = (response) arg0;
			//show board depending on perspective
			if (this.user.get_id() == 0) {
				this.gui.drawBoard(res.pieces, res.teams);
			}
			//
			else {
				this.gui.drawBoardReverse(res.pieces, res.teams);
			}

		}

	}

	public void set_id(int id) {
		this.id = id;
	}

	public int get_id() {
		return this.id;
	}

	public void send_move(int[] old_pos, int[] new_pos) {
		move mv = new move(this.user.get_id(), old_pos[0], old_pos[1], new_pos[0], new_pos[1]);

		try {
			this.sendToServer(mv);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void connectionException(Throwable exception) {
		//Add your code here
	}

	@Override
	public void connectionClosed() {
		try {
			this.closeConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void connectionEstablished() {

	}

}
