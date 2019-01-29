package chess_multiplayer;

import java.util.ArrayList;

public class chess_engine {

	// chessboard state
	private piece[][] board;
	private int[][] threat;
	private game_state current_state;

	//game booleans
	boolean check_mate;
	boolean stalemate;
	move mve;
	//check and the player who is in check 
	boolean check;
	int check_player;

	player player1;
	player player2;

	public chess_engine(piece[][] board, player player1, player player2) {
		this.board = board;
		this.player1 = player1;
		this.player2 = player2;
		current_state = new game_state(board, player1, player2);
	}

	@SuppressWarnings("unchecked")
	public boolean validate(move mv) {
		this.mve = mv;

		//store important variables
		player current_player = current_state.get_player(mv.get_turn());
		player current_opponent = current_state.get_player(get_opposite(mv.get_turn()));
		piece current_piece = board[mv.get_current_pos()[0]][mv.get_current_pos()[1]];

		// clone data to temp vars
		piece[][] backup_board = new piece[8][8];

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				backup_board[i][j] = this.board[i][j];
			}
		}
		ArrayList<piece> currentPlayerPieces = (ArrayList<piece>) current_player.get_pieces().clone();
		ArrayList<piece> opponentPlayerPieces = (ArrayList<piece>) current_opponent.get_pieces().clone();

		// for convinience
		int opx = current_piece.get_pos()[0];
		int opy = current_piece.get_pos()[1];
		int npx = mve.get_new_pos()[0];
		int npy = mve.get_new_pos()[1];

		//check if its the right player
		if (mve.get_turn() != current_state.get_turn()) {
			return false;
		}

		if (current_piece.move_piece(mve.get_new_pos(), this.board)) {
			//check if capture
			if (board[npx][npy].exists()) {
				//first check specifically for special case of Pawn capturing
				if (current_piece instanceof Pawn) {
					if (npx == current_piece.pos[0]) {
						return false;
					}
				}

				//if same team piece already present
				if (board[npx][npy].get_team() == mve.get_turn()) {
					return false;
				}

				//if not the same team, kill
				if (board[npx][npy].get_team() != mve.get_turn()) {
					current_opponent.get_pieces().remove(board[npx][npy]);
				}
				//move piece
				board[npx][npy] = current_piece;
				current_piece.set_pos(mv.get_new_pos());
				board[opx][opy] = new piece("empty slot", -1, -1, -1, mv.get_current_pos());
				current_piece.moved();

			} else {
				//check explicitly for special case of pawn
				if (current_piece instanceof Pawn) {
					if (npy != current_piece.pos[1]) {
						return false;
					}
				}

				//move piece
				board[npx][npy] = current_piece;
				current_piece.set_pos(mv.get_new_pos());
				board[opx][opy] = new piece("empty slot", -1, -1, -1, mv.get_current_pos());
				this.current_state.set_board(this.board);
				current_piece.moved();
			}

			//promoting
			if (current_piece instanceof Pawn) {
				if ((current_piece.get_team() == 0) && (mv.get_new_pos()[0] == 0)) {
					board[npx][npy] = new Queen("Queen", 12, current_piece.get_team(), 1, mv.get_new_pos());
				}
				if ((current_piece.get_team() == 1) && (mv.get_new_pos()[0] == 7)) {
					board[npx][npy] = new Queen("Queen", 12, current_piece.get_team(), 1, mv.get_new_pos());
				}

			}

			//if your king in check revert changes
			if (check_for_check(mve.get_turn(), board)) {
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						this.board[i][j] = backup_board[i][j];
					}
				}

				current_player.set_pieces(currentPlayerPieces);
				current_opponent.set_pieces(opponentPlayerPieces);
				current_piece.set_pos(mv.get_current_pos());
				current_piece.moved();

				return false;
			}

			//switch turn 
			current_state.set_turn(get_opposite(current_state.get_turn()));

			return true;

		} else {
			//castling
			if (current_piece instanceof King) {
				if (npy == opy - 2) {
					Rook rook = (Rook) board[opx][opy - 4];
					//both pieces didnt move
					if ((rook.is_moved_once()) || (current_piece.is_moved_once())) {
						return false;
					}
					int[] rook_new_pos = { npx, npy + 1 };

					if (rook.move_piece(rook_new_pos, board)) {
						board[rook_new_pos[0]][rook_new_pos[1]] = rook;
						board[npx][npy] = current_piece;
						board[opx][opy] = new piece("empty slot", -1, -1, -1, mv.get_current_pos());
						board[opx][opy - 4] = new piece("empty slot", -1, -1, -1, mv.get_current_pos());
						current_piece.set_pos(mv.get_new_pos());
						rook.set_pos(rook_new_pos);
						current_state.set_turn(get_opposite(current_state.get_turn()));
						return true;
					}

				}
				if (npy == opy + 2) {
					Rook rook = (Rook) board[opx][opy + 3];
					//both pieces didnt move
					if ((rook.is_moved_once()) || (current_piece.is_moved_once())) {
						return false;
					}
					int[] rook_new_pos = { npx, npy - 1 };

					if (rook.move_piece(rook_new_pos, board)) {
						board[rook_new_pos[0]][rook_new_pos[1]] = rook;
						board[npx][npy] = current_piece;
						board[opx][opy] = new piece("empty slot", -1, -1, -1, mv.get_current_pos());
						board[opx][opy + 3] = new piece("empty slot", -1, -1, -1, mv.get_current_pos());
						current_piece.set_pos(mv.get_new_pos());
						rook.set_pos(rook_new_pos);
						current_state.set_turn(get_opposite(current_state.get_turn()));
						return true;
					}

				}
			}

			return false;
		}

	}

	public piece[][] get_board() {
		return this.board;
	}

	//check if the king in check
	public boolean check_for_check(int i, piece[][] board) {

		ArrayList<piece> enemy_pieces = current_state.get_player(get_opposite(i)).get_pieces();

		piece my_king = current_state.get_player(i).get_king();
		int[] my_king_pos = my_king.get_pos();
		for (int j = 0; j < enemy_pieces.size(); j++) {
			boolean x = enemy_pieces.get(j).move_piece(my_king_pos, board);
			if (x) {
				return true;
			}
		}

		return false;
	}

	//function to switch team id's
	public int get_opposite(int i) {
		if (i == 1)
			return 0;
		else
			return 1;
	}

	public game_state get_current_state() {
		return this.current_state;
	}

}
