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
	 *****************************************************************/
	public SuperTicTacToeGame() {
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
	 *****************************************************************/
	public void select (int row, int col) {
		//TODO mark cell based on which player clicks the button
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
					int a = 1;
					//Checks all columns for connections of WINCON length
					while (getCell(row,tempCol) == getCell(row,col)
							&& a <= WINCON) {
						//Reached end of board, wrap to beginning.
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
						a++;
					}

					//Row of the cell being checked for connection.
					int tempRow = row;
					//number of iterations through row while loop.
					int b = 1;
					while (getCell(tempRow,col) == getCell(row,col)
							&& b <= WINCON) {
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
						b++;
					}
				}

				//Current cell is empty
				else if (getCell(row,col) == CellStatus.EMPTY)
					numEmpty++;

				//Error
				else if (getCell(row,col) == null)
					throw new NullPointerException();
				else
					throw new IllegalArgumentException();
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
	 *****************************************************************/
	public CellStatus getCell(int row, int col) {
		return board[row][col];
	}
}
