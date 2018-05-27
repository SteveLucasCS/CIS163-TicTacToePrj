package package1;


/**********************************************************************
 * TicTacToeAI objects represent a cell on the game board, and are used
 * to easily save and access logic information for the AI.
 * 
 * Call the aiMove() method to have the AI take a turn. 
 *********************************************************************/
public class TicTacToeAI extends SuperTicTacToeGame{


	/******************************************************************
	 * Calling this gets the coordinates the AI wants to move
	 * @return int[] moveTo - The coordinates the AI wants to move
	 *****************************************************************/
	public int[] aiMove() {
		//Coordinates the AI will place an O. 
		int[] moveTo = {-1,-1};

		return moveTo;
	}

	/******************************************************************
	 * Checks the entire game board and assigns each cell a threat and
	 * a priority based on how close it is to being a winning
	 *****************************************************************/
	private void analyzeBoard() {
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
						b++;
					}
				}
			}
	}

	/**
	 * Holds the row of the cell
	 */
	private int row;

	/**
	 * Holds the column of the cell
	 */
	private int col;

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


	/**************************************************************
	 * Constructor with No Parameters. Everything set to 0
	 *************************************************************/
	private TicTacToeAI() {
		this.row = 0;
		this.col = 0;
		this.threat = 0;
		this.priority = 0;
	}

	/**************************************************************
	 * Constructor with 4 Parameters.
	 * @param int row, int col, int threat, int priority
	 *************************************************************/
	private TicTacToeAI(int row, int col, int threat, int priority) {
		this.row = row;
		this.col = col;
		this.threat = threat;
		this.priority = priority;
	}

	/**************************************************************
	 * Setter for AICell field 'threat'
	 * @param int threat - Lower number is a greater threat
	 *************************************************************/
	public void setThreat(int threat) {
		this.threat = threat;
	}

	/**************************************************************
	 * Setter for AICell field 'priority'
	 * @param int priority - Lower number is a higher priority
	 *************************************************************/
	public void setPriority(int Priority) {
		this.priority = priority;
	}

	/**************************************************************
	 * Setter for AICell field 'row'
	 * @param int row - Sets the row of the cell
	 *************************************************************/
	public void setRow(int row) {
		this.row = row;
	}

	/**************************************************************
	 * Setter for AICell field 'col'
	 * @param int col - Sets the column of the cell
	 *************************************************************/
	public void setCol(int col) {
		this.col = col;
	}

	/**************************************************************
	 * @return int threat - AICell's threat rating
	 *************************************************************/
	public int getThreat() {
		return this.threat;
	}

	/**************************************************************
	 * @return int priority - AICell's priority rating
	 *************************************************************/
	public int getPriority() {
		return this.priority;
	}

	/**************************************************************
	 * @return int row - AICells row on the game board
	 *************************************************************/
	public int getRow() {
		return this.row;
	}

	/**************************************************************
	 * @return int col - AICells column on the game board
	 *************************************************************/
	public int getCol() {
		return this.col;
	}
}
