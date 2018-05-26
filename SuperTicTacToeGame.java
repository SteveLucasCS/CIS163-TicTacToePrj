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
	 * Number of cells in a row needed to to win. 3 is default.
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
		
		//Tracks the number of empty cells on the board.
		//If this is 0 after the for loop the board is full; game over.
		int numEmpty = 0;
		
		//Iterates through the entire game board, checking the status
		//of each cell.
		for (int row = 0; row < BDSIZE; row++)
			for (int col = 0; col < BDSIZE; col++) {
				
				if (getCell(row,col) == CellStatus.EMPTY)
					numEmpty++;
				
				//Check if there are enough columns below the
				//current cell for a possible winning connection
				if ((row + WINCON < BDSIZE) &&
						(getCell(row,col) != CellStatus.EMPTY)) {
					
					//iterates through the current column while 
					//there is a connection of matching cells until
					//a connection of WINCON is found or the connection
					//is broken. 
					int a = 1;
					while (getCell(row+a,col) == getCell(row,col)
							&& a <= WINCON) {
						
						if (a == WINCON)
							return GameStatus.X_WON;
						a++;
					}
				}
					
				
				
				//Check if there are enough cells to the right of the
				//current cell for a possible winning connection
				if ((col + WINCON < BDSIZE) &&
						(getCell(row,col) != CellStatus.EMPTY)) {
					
					//Iterates through the current row while 
					//there is a connection of matching cells until
					//a connection of WINCON is found or the connection
					//is broken. 
					int a = 1;
					while (getCell(row,col+a) == getCell(row,col)
							&& a <= WINCON) {
						
						if (a == WINCON)
							return GameStatus.X_WON;
						a++;
					}
				}
				
			}
		
		//All cells occupied but 3 in a row, CATS game (tie).
		if (numEmpty == 0)
			return GameStatus.CATS;
		//Empty cells remain and there is no 3 in a row yet.
		if (numEmpty > 0)
			return GameStatus.IN_PROGRESS;
		else
			throw new IllegalArgumentException();
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
	
	
	/******************************************************************
	 * This class simulates an AI to play against the user. 
	 * @author Steve
	 * TODO INCOMPLETE
	 */
	private class AI {
		
		/**
		 * Holds the coordinates for the cell.
		 */
		private int[] cell;
		
		/**
		 * The assigned threat level of a cell. Threat is how many
		 * more moves the player needs to complete a winning 
		 * connection. For example, a threat of 1 means if the user
		 * selects this cell, they will win. A threat of 2 means they
		 * need this cell, and one more adjacent cell to win. 
		 */
		private int threat;
		
		/**
		 * Priority is how many cells, including this one, would be
		 * needed for a winning connection with this cell.
		 * A priority of 1 wins the game for the AI,
		 * A priority of 2 means this cell and an adjacent cell are
		 * needed to form a winning connection. 
		 */
		private int priority;
		
		
		public void aiMove() {
			
			//If a cell is priority 1, move there (wins game)
			//If a cell is threat 1, move there (blocks connection)
			
		}
		
		public void assignThreats() {
			
		}
		
		public void assignPriority() {
		/**************************************************************
		 * The Threat class holds the values for a t
		 * @author Steve
		 */
		}
	}
}
