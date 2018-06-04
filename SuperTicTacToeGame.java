/**
 *
 */
package package1;
import java.util.Random;
/**
 * @author Steve Lucas
 * Version: 6/4/2018
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
							if (getCell(row,col) == CellStatus.X)
								return GameStatus.X_WON;
							else if (getCell(row,col) == CellStatus.O)
								return GameStatus.O_WON;
						}
					}// end if getCell == X or O

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
	 * Gets the dimensions of the game board.
	 * @returns int BDSIZE - Dimensions of the square board.
	 *****************************************************************/
	public static int getBDSIZE() {
		return BDSIZE;
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
	 * Gets the number of connections required to win the game.
	 * @returns int WINCON - # of connections required to win.
	 *****************************************************************/
	public static int getWINCON() {
		return WINCON;
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
		int a = 0;
		int b = 0;
		int c = 0;

		while(c <= WINCON) {
			c++;
			for (int row = 0; row < BDSIZE; row++)
				for (int col = 0; col < BDSIZE; col++) {
					//Cell is not empty and is valid,
					//check for connection.
					if (getCell(row,col) == CellStatus.X ||
							getCell(row,col) == CellStatus.O)
					{
						//Col of the cell being checked for connection.
						int tempCol = col;
						//number of iterations through col while loop
						a = 0;
						//Checks columns for connections 'a' length
						while (getCell(row,tempCol) == getCell(row,col)
								&& a <= WINCON) {
							a++;

							//Reached end of board, wrap to beginning.
							if (tempCol == BDSIZE-1)
								tempCol = 0;
							//Bottom not reached, move down one column
							else
								tempCol++;

							//c is how many a is away from winning
							if (a == WINCON-c)
								//if enough adjacent cells are open
								if (checkAdjacentColumns(row,tempCol,
										c, getCell(row,col)))
									return AIStatus.MOVED;
						} //end of columns while loop

						//Vertical connections checked
						//Row of the cell being checked for connection.
						int tempRow = row;
						//number of iterations through row while loop.
						b = 0;
						while (getCell(tempRow,col) == getCell(row,col)
								&& b <= WINCON) {
							b++;
							//Reached end of board, wrap to beginning.
							if (tempRow == BDSIZE-1)
								tempRow= 0;
							//Bottom not reached, move down one column
							else
								tempRow++;

							if (b == WINCON-c) {
								if (checkAdjacentRows(tempRow,col,c,
										getCell(row,col)))
									return AIStatus.MOVED;
							}		
						} //end of rows while loop

					} //end of if (cell != EMPTY) within for loops
				} //end of inner for loop (columns)
		}// end outer While loop (c < WINCON)

		//board is empty, move to random cell.
		if (a == 0 && b == 0) {
			Random ran = new Random();
			int ranRow = ran.nextInt(BDSIZE);
			int ranCol = ran.nextInt(BDSIZE);
			if (checkMove(ranRow, ranCol) == AIStatus.VALID){
				board[ranRow][ranCol] = CellStatus.O;
				return AIStatus.MOVED;
			}
			else {
				return AIStatus.ERROR;
			}	
		}
		//Method ended without AI finding any available moves.
		return AIStatus.ERROR;
	} //end oF method

	/******************************************************************
	 * This method checks if the given coordinates are valid for a move
	 * by the AI.
	 * @param row - row of the cell on the board.
	 * @param col - column of the cell on the board.
	 * @return VALID - Cell exists and is empty. OCCUPIED cell is 
	 * occupied already. INVALID cell is not on the game board.
	 *****************************************************************/
	public AIStatus checkMove(int row, int col) {
		if ((row < BDSIZE && row >= 0) && (col < BDSIZE && col >= 0)
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

	/******************************************************************
	 * Checks if there are enough available connected cells in the row
	 * to form a winning connection.If there are, it loops through 
	 * adjacent cells and places an O in the nearest empty cell.
	 * @param row 
	 * @param col
	 * @param c
	 * @return true - enough connected cells to win
	 * 		   false - not enough to win
	 *****************************************************************/
	private boolean checkAdjacentColumns(int row, int col, int c,
			CellStatus side) {
		int x = 0;
		int i = 0;
		//Count to c
		while (x <= c) {
			x++;
			i++;
			if (col+i >= BDSIZE) {
				col = 0;
				i = 0;
			}
			if (getCell(row,col+i) != side && getCell(row,col+i)
					!= CellStatus.EMPTY)
				return false;
		}
		x = 0;
		i = 0;
		while (x <= c) {
			x++;
			i++;
			if (col+i >= BDSIZE) {
				col = 0;
				i = 0;
			}
			if (getCell(row,col+i) == CellStatus.EMPTY) {
				if (checkMove(row,col+i) == AIStatus.VALID) {
					board[row][col+i] = CellStatus.O;
					return true;
				}
			}
		}
		return false;
	}

	/******************************************************************
	 * Checks if there are enough available connected cells in the col
	 * to form a winning connection.If there are, it loops through 
	 * adjacent cells and places an O in the nearest empty cell.
	 * @param row 
	 * @param col
	 * @param c - How many more connections are needed to win
	 * @return true - enough connected cells to win
	 * 		   false - not enough to win
	 *****************************************************************/
	private boolean checkAdjacentRows(int row, int col, int c, 
			CellStatus side) {
		int x = 0;
		int i = 0;
		//Count to c
		while (x <= c) {
			x++;
			i++;
			if (row+i >= BDSIZE) {
				row = 0;
				i = 0;
			}
			if (getCell(row+i,col) != side && getCell(row+i,col)
					!= CellStatus.EMPTY)
				return false;
		}
		x = 0;
		i = 0;
		while (x <= c) {
			x++;
			i++;
			if (row+i >= BDSIZE) {
				row = 0;
				i = 0;
			}
			if (getCell(row+i,col) == CellStatus.EMPTY) {
				if (checkMove(row+i,col) == AIStatus.VALID) {
					board[row+i][col] = CellStatus.O;
					return true;
				}
			}
		}
		return false;
	}
} //end of class
