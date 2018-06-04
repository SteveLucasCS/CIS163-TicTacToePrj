/**
 *
 */
package package1;

/**
 * @author steve
 *
 */

/**********************************************************************
 * This class handles all game logic and back-end calculations.
 * The GUI classes pull information from this class to update the
 * game board.
 *********************************************************************/

public class SuperTicTacToeGame {

	/**
	 * The X and Y dimension of the game board. 3 is default.
	 */
	protected static int BDSIZE = 3;

	/**
	 * Number of cells in connection needed to to win. 3 is default.
	 */

	protected static int WINCON = 3;
	/**
	 * Status of each cell (as a 2D array) on the game board
	 */
	private CellStatus[][] board;

	/**
	 * Status of the game (X_WON, O_WON, CATS, or IN_PROGRESS)
	 */
	private GameStatus status;


	/******************************************************************
	 * Creates a game board of the size specified by BDSIZE
	 * @throws IllegalArgumentException if BDSIZE or WINCON are out
	 * 		   of range (3 to 9) or WINCON is > BDSIZE
	 *****************************************************************/
	public SuperTicTacToeGame() {
		if (BDSIZE < 3 || BDSIZE > 9 || WINCON < 3 || WINCON > BDSIZE)
			throw new IllegalArgumentException();
		status = GameStatus.IN_PROGRESS;
		board = new CellStatus[BDSIZE][BDSIZE];

		for (int row = 0; row < BDSIZE; row++)
			for (int col = 0; col < BDSIZE; col++)
				board[row][col] = CellStatus.EMPTY;
	}

	/******************************************************************
	 * Called when the user selects a cell in the GUI. Marks the cell
	 * as the user's token (X or O).
	 * @param int row - the row of the button the player clicked.
	 * 		  int col - the column of the button the player clicked.
	 * @throws IllegalArgumentException if row or col are < 0 or >=
	 * 	       BDSIZE.
	 * 		   IllegalArgumentException if selected cell is not empty.
	 *****************************************************************/
	public void select (int row, int col) {
		if (row < 0 || row >= BDSIZE || col < 0 || col >= BDSIZE)
			throw new IllegalArgumentException();
		if (getCell(row, col) != CellStatus.EMPTY)
			throw new IllegalArgumentException();
		//sets the selected cell to X.
		board[row][col] = CellStatus.X;
	}

	/******************************************************************
	 * Called by the GUI Panel class to reset the board to a new game.
	 *****************************************************************/
	public void reset() {
		status = GameStatus.IN_PROGRESS;
		board = new CellStatus[BDSIZE][BDSIZE];

		for (int row = 0; row < BDSIZE; row++)
			for (int col = 0; col < BDSIZE; col++)
				board[row][col] = CellStatus.EMPTY;
	}

	/******************************************************************
	 * Checks if the game has ended, and if so who won, or that the
	 * game is still in progress.
	 * @return GameStatus.X_WON if player X has winning connections.
	 * 		   GameStatus.O_Win if player O has winning connections.
	 * 		   GameStatus.Cats if board full and no winner.
	 * 		   GameStatus.IN_Progress if the game is still in progress.
	 * @throws NullPointerException if getCell() method returns null.
	 *****************************************************************/
	public GameStatus getGameStatus() {
		//number of empty cells on the game board.
		int numEmpty = 0;
		for (int row = 0; row < BDSIZE; row++)
			for (int col = 0; col < BDSIZE; col++) {
				//Cell is not empty and is valid, check for connection.
				if ((getCell(row,col) == CellStatus.X)
						|| (getCell(row,col) == CellStatus.O))
				{
					//Column of the cell being checked for connection.
					int tempCol = col;
					//number of iterations through column while loop
					int a = 0;
					//Checks all columns for connections of WINCON length
					while (getCell(row,tempCol) == getCell(row,col)
							&& a <= WINCON) {
						//Reached end of board, wrap to beginning.
						a++;
						if (tempCol == BDSIZE-1)
							tempCol = 0;
						//Bottom not reached, move down one column
						else
							tempCol++;
						//win condition achieved, return winner.
						if (a == WINCON) {
							if (getCell(0,0) == CellStatus.X)
								return GameStatus.X_WON;
							else if (getCell(0,0) == CellStatus.O)
								return GameStatus.O_WON;
						}
					}

					//Row of the cell being checked for connection.
					int tempRow = row;
					//number of iterations through row while loop.
					int b = 0;
					while (getCell(tempRow,col) == getCell(row,col)
							&& b <= WINCON) {
						b++;
						//Reached end of board, wrap to beginning.
						if (tempRow == BDSIZE-1)
							tempRow= 0;
						//Bottom not reached, move down one column
						else
							tempRow++;
						//win condition achieved, return winner.
						if (b == WINCON) {
							if (getCell(row,col) == CellStatus.X)
								return GameStatus.X_WON;
							else if (getCell(row,col) == CellStatus.O)
								return GameStatus.O_WON;
						}
					}
				}

				//Current cell is empty
				else if (getCell(row,col) == CellStatus.EMPTY)
					numEmpty++;

				//Error
				else if (getCell(row,col) == null)
					throw new NullPointerException();
				else
					throw new NullPointerException();
			}
		//all cells checked, no winner, no empty cells remain.
		if (numEmpty == 0)
			return GameStatus.CATS;
		//Reaching end of method means the game is still in progress.
		return GameStatus.IN_PROGRESS;
	}


	/******************************************************************
	 * Returns the CellStatus value of the cell at the given coordinate
	 * @param int row - row of the button being checked.
	 * 		  int col - column of the button being checked.
	 * @return CellStatus (X, O, or EMPTY)
	 * @throws IllegalArgumentException if row or col is < 0 or >=
	 * 		   BDSIZE (x and y dimensions of the board)
	 *****************************************************************/
	public CellStatus getCell(int row, int col) {
		if (row < 0 || row >= BDSIZE || col < 0 || col >= BDSIZE)
			throw new IllegalArgumentException();
		return board[row][col];
	}

	/******************************************************************
	 * Sets the dimensions of the game board.
	 * @param int dim is the x and y dimension that the board will have
	 * @throws IllegalArgumentException if 'dim' is < 2 or > 9
	 *****************************************************************/
	public static void setBDSIZE(int dim) {
		if (dim <= 2 || dim > 9) {
			BDSIZE = 3;
			throw new IllegalArgumentException();
		}
		BDSIZE = dim;
	}

	/******************************************************************
	 * Sets the number of connected cells required to win the game.
	 * @param int win is the number of connections to win.
	 * @throws IllegalArgumentException if 'win' is < 2 or > BDSIZE
	 *****************************************************************/
	public static void setWINCON(int win) {
		if (win <= 2 || win > BDSIZE) {
			WINCON = 3;
			throw new IllegalArgumentException();
		}
		WINCON = win;
	}
	
	/******************************************************************
	 * Logic for the AI. Selects the cell the AI wants to move to as O.
	 * Call the getGameStatus() method before this method each time.
	 * This method modifies the board, placing an O where the AI wants 
	 * to move, if possible.
	 * @return AIStatus MOVED if the AI successfully moved, ERROR if
	 * the AI could not make a move for any reason.
	 *****************************************************************/
	public AIStatus aiMove() {
		final int VERTICAL = 20;
		final int HORIZONTAL = 40;
		//highest number of horizontal connections on the board.
		int aMax = 1;
		//highest number of vertical connections on the board.
		int bMax = 1;
		//coordinates of the optimal cell to move to.
		int[] target = {0,0};
		//Starting point of X's largest connection
		//length of connection, column, row.
		int[] foeMaxStart = {0,0,0};
		//end row, end col
		int[] foeMaxEnd = {0,0};
		//Starting point of O's largest connection.
		int[] friendMaxStart = {0,0,0};
		//end row, end col
		int[] friendMaxEnd = {0,0};
		//Stance of the AI's move; BLOCK if blocking user's connection,
		//AGGRO is trying to form a winning connection.
		AIStatus stance = AIStatus.ERROR;
		//If the connection is X, conOwner = FOE. If the connection is
		// O, the conOwner is FRIEND
		AIStatus conOwner = AIStatus.ERROR;
		for (int row = 0; row < BDSIZE; row++)
			for (int col = 0; col < BDSIZE; col++) {

				//Cell is not empty and is valid, check for connection.
				if (getCell(row,col) == CellStatus.X ||
						getCell(row,col) == CellStatus.O)
				{
					if (getCell(row,col) == CellStatus.X)
						conOwner = AIStatus.FOE;
					if (getCell(row,col) == CellStatus.O)
						conOwner = AIStatus.FRIEND;
					//Column of the cell being checked for connection.
					int tempCol = col;
					//number of iterations through column while loop
					int a = 1;
					//Checks columns for connections of WINCON length
					while (getCell(row,tempCol) == getCell(row,col)
							&& a <= WINCON) {
						a++;
						
						//Reached end of board, wrap to beginning.
						if (tempCol == BDSIZE-1)
							tempCol = 0;
						//Bottom not reached, move down one column
						else
							tempCol++;
						
						//1 away from winning
						if (a == WINCON-1) {
							//if the next cell on the board is empty, 
							if (getCell(row,tempCol) == 
									CellStatus.EMPTY) {
								board[row][tempCol] = CellStatus.O;
								return AIStatus.MOVED;
							}
							//place O before the first X in connection.
							else if (col > 0 && getCell(row,col-1) ==
									CellStatus.EMPTY) {
								board[row][col-1] = CellStatus.O;
								return AIStatus.MOVED;
							}
						}
						
					} //end of columns while loop
					
					//Most horizontal connections either player has.
					if (a > aMax)
						aMax = a;
					
					//Vertical connections checked
					//Row of the cell being checked for connection.
					int tempRow = row;
					//number of iterations through row while loop.
					int b = 0;
					while (getCell(tempRow,col) == getCell(row,col)
							&& b <= WINCON) {
						b++;
						//Reached end of board, wrap to beginning.
						if (tempRow == BDSIZE-1)
							tempRow= 0;
						//Bottom not reached, move down one column
						else
							tempRow++;
						
						//1 away from winning
						if (b == WINCON-1) {
							//if the next cell on the board is empty, 
							if (checkMove(tempRow, col) == 
									AIStatus.VALID) {
								board[tempRow][col] = CellStatus.O;
								return AIStatus.MOVED;
							}
							//place O before the first X in connection.
							else if (row > 0 && checkMove(row-1,col) ==
									AIStatus.VALID) {
								board[row-1][col] = CellStatus.O;
								return AIStatus.MOVED;
							}
						}
					} //end of rows while loop
					
					//Most vertical connections a player has.
					if (b > bMax)
						bMax = b;
					//largest connection is vertical
					if (bMax > aMax) {
						//connection is of X cells
						if (conOwner== AIStatus.FOE) {
							foeMaxStart[0] = bMax;
							foeMaxStart[1] = row;
							foeMaxStart[2] = col;
							foeMaxStart[3] = VERTICAL;
							if (tempRow > 0)
								foeMaxEnd[0] = tempRow-1;
							if (tempRow == 0)
								foeMaxEnd[0] = BDSIZE-1;
							foeMaxEnd[1] = col;
						}
						//connection is of O cells
						if (conOwner == AIStatus.FRIEND) {
							friendMaxStart[0] = bMax;
							friendMaxStart[1] = row;
							friendMaxStart[2] = col;
							friendMaxStart[3] = VERTICAL;
							if (tempRow > 0)
								friendMaxEnd[0] = tempRow-1;
							if (tempRow == 0)
								friendMaxEnd[0] = BDSIZE-1;
							friendMaxEnd[1] = col;
						}
					}
					//largest connection is horizontal
					else if (aMax > bMax) {
						//connection is of X cells
						if (conOwner == AIStatus.FOE) {
							foeMaxStart[0] = aMax;
							foeMaxStart[1] = row;
							foeMaxStart[2] = col;
							foeMaxStart[3] = HORIZONTAL;
							foeMaxEnd[0] = row;
							if (tempCol > 0)
								foeMaxEnd[1] = tempCol-1;
							if (tempCol == 0)
								foeMaxEnd[1] = BDSIZE-1;
						}
						//connection is of O cells
						if (conOwner == AIStatus.FRIEND) {
							friendMaxStart[0] = aMax;
							friendMaxStart[1] = row;
							friendMaxStart[2] = col;
							friendMaxStart[3] = HORIZONTAL;
							friendMaxEnd[0] = row;
							if (tempCol > 0)
								friendMaxEnd[1] = tempCol-1;
							if (tempCol == 0)
								friendMaxEnd[1] = BDSIZE-1;
						}
					}
					//Largest friend/foe connections established,
					//begin logic/decision making. 
					
					//If the friendly connection is larger, 
					if (friendMaxStart[0] >= foeMaxStart[0]) {
						//check if it's horizontal or vertical
						if (friendMaxStart[3] == VERTICAL) {
							//check the next cell in the row
							if (checkMove(friendMaxEnd[0] + 1,
									friendMaxEnd[1]) == AIStatus.VALID)
							{
								//Sets AI target to the cell above
								//the first cell in the connection.
								target[0] = friendMaxEnd[0] + 1;
								target[1] = friendMaxEnd[1];
							}
							else if (checkMove(friendMaxStart[1] - 1,
									friendMaxStart[2]) == AIStatus.
									VALID) {
								//Sets the AI target to the cell below
								//the last cell in the connection.
								target[0] = friendMaxStart[1] - 1;
								target[1] = friendMaxStart[2];
							}
						} //end if VERTICAL
						if (friendMaxStart[3] == HORIZONTAL) {
							//check the next cell in the row
							if (checkMove(friendMaxEnd[0],
									friendMaxEnd[1] + 1) 
									== AIStatus.VALID) {
								//Sets AI target to the cell right of
								//the last cell in the connection.
								target[0] = friendMaxEnd[0];
								target[1] = friendMaxEnd[1] + 1;
							}
							else if (checkMove(friendMaxStart[1],
									friendMaxStart[2] - 1) == AIStatus.
									VALID) {
								//Sets AI target to the cell left of
								//the first cell in the connection.
								target[0] = friendMaxStart[1];
								target[1] = friendMaxStart[2] - 1;
							}
						} //end if HORIZONTAL
					} // end if friend connection > foe connection
					else if (foeMaxStart[0] > friendMaxStart[0]) {
						//check if it's horizontal or vertical
						if (foeMaxStart[3] == VERTICAL) {
							//check the next cell in the row
							if (checkMove(foeMaxEnd[0] + 1,
									foeMaxEnd[1]) == AIStatus.VALID)
							{
								//Sets AI target to the cell above
								//the first cell in the connection.
								target[0] = foeMaxEnd[0] + 1;
								target[1] = foeMaxEnd[1];
							}
							else if (checkMove(foeMaxStart[1] - 1,
									foeMaxStart[2]) == AIStatus.
									VALID) {
								//Sets the AI target to the cell below
								//the last cell in the connection.
								target[0] = foeMaxStart[1] - 1;
								target[1] = foeMaxStart[2];
							}
						} //end if VERTICAL
						if (foeMaxStart[3] == HORIZONTAL) {
							//check the next cell in the row
							if (checkMove(foeMaxEnd[0],
									foeMaxEnd[1] + 1) 
									== AIStatus.VALID) {
								//Sets AI target to the cell right of
								//the last cell in the connection.
								target[0] = foeMaxEnd[0];
								target[1] = foeMaxEnd[1] + 1;
							}
							else if (checkMove(foeMaxStart[1],
									foeMaxStart[2] - 1) == AIStatus.
									VALID) {
								//Sets AI target to the cell left of
								//the first cell in the connection.
								target[0] = foeMaxStart[1];
								target[1] = foeMaxStart[2] - 1;
							}
						} //end if HORIZONTAL
					} //end if foe > friend connection
				} //end of if (cell != EMPTY) within for loops
			} //end of inner for loop (columns)
		//AI attempts to move to target cell
		if (checkMove(target[0],target[1]) == AIStatus.VALID) {
			board[target[0]][target[1]] = CellStatus.O;
			return AIStatus.MOVED;
		}
		//Method ended without AI finding any available moves.
		return AIStatus.ERROR;
	} //end oF method
	
	/******************************************************************
	 * 
	 * @param row - row of the cell on the board.
	 * @param col - column of the cell on the board.
	 * @return VALID - Cell exists and is empty. OCCUPIED cell is 
	 * occupied already. INVALID cell is not on the game board.
	 *****************************************************************/
	private AIStatus checkMove(int row, int col) {
		if ((row < BDSIZE && row > 0) && (col < BDSIZE && col > 0)
				&& (getCell(row,col) == CellStatus.EMPTY)) {
			return AIStatus.VALID;
		}
		else if (row >= BDSIZE || row < 0 || col >= BDSIZE || col < 0)
			return AIStatus.INVALID;
		else if (getCell(row,col) == CellStatus.X || getCell(row,col)
				== CellStatus.O)
			return AIStatus.OCCUPIED;
		else
			return AIStatus.ERROR;
	}
} //end of class
