/*

PLAYER CLASS (THIS IS THE USERS IN THE SOFTWARE)
ONLY TWO ALLOWED FOR ONE GAME

*/

package chess_multiplayer;

import java.util.ArrayList;

import java.io.Serializable;

public class player implements Serializable {
	// DATA FIELDS
	private int id;
	private int score;
	private String name;
	private int color; // 0 - red, 1 -black 
	private piece[][] chess_board;
	private ArrayList<piece> pieces;
	private boolean check;
	private boolean check_mate;
	private boolean stale_mate;

	// CONSTRUCTOR
	public player(String name, int id, piece[][] chess_board, ArrayList<piece> pieces) {
		this.name = name;
		this.id = id;
		this.chess_board = chess_board;
		this.score = 0;
		this.check_mate = false;
		this.stale_mate = false;
		this.check = false;
		this.pieces = pieces;
	}

	// SETTERS
	public void set_id(int id) {
		this.id = id;
	}

	public void set_score(int score) {
		this.score = score;
	}

	public void set_name(String name) {
		this.name = name;
	}

	public void set_pieces(ArrayList<piece> pieces) {
		this.pieces = pieces;
	}

	public void checked() {
		this.check = true;
	}

	public void unchecked() {
		this.check = false;
	}

	public void stalemated() {
		this.stale_mate = true;
	}

	public void checkmated() {
		this.check_mate = true;
	}

	public piece[][] get_board() {
		return this.chess_board;
	}

	// GETTERS
	public int get_id() {
		return this.id;
	}

	public int get_score() {
		return this.score;
	}

	public String get_name() {
		return this.name;
	}

	public ArrayList<piece> get_pieces() {
		return this.pieces;
	}

	public boolean is_checkmate() {
		return this.check_mate;
	}

	public boolean is_stalemate() {
		return this.stale_mate;
	}

	public boolean is_check() {
		return this.check;
	}

	public King get_king() {
		for (int i = 0; i < this.pieces.size(); i++) {
			if (this.pieces.get(i) instanceof King) {
				return (King) this.pieces.get(i);
			}
		}
		return null;
	}

}
