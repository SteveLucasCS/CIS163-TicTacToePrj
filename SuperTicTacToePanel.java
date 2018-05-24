package package1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * @author steve
 *
 */
public class SuperTicTacToePanel extends JPanel{

	protected static int BD_SIZE = 3;
	/**
	 * board is a 2D array of buttons. These buttons represent
	 * the cells on the game board, making up what the user
	 * sees and interacts with
	 */
	private JButton[][] board;

	private CellStatus iCell;

	/**
	 * Quits the game, closing the GUI and ending the game.
	 */
	private JButton quitButton;

	/**
	 * A game object that handles the back end tasks of
	 * scoring and math methods.
	 */
	private SuperTicTacToeGame game;

	/**
	 * Icon for the 'X' marker in a cell
	 */
	private ImageIcon xIcon;

	/**
	 * Icon for the 'O' marker in a cell
	 */
	private ImageIcon oIcon;

	/**
	 * Icon representing an empty cell
	 */
	private ImageIcon emptyIcon;

	/**
	 * ActionListener for the main panel
	 */
	private ButtonListener listener;

	public SuperTicTacToePanel() {
		//initialize xIcon with image file path
		xIcon = new ImageIcon("x.jpg");

		//initialize oIcon with image file path
		oIcon = new ImageIcon("o.jpg");

		//initialize emptyIcon with image file path
		emptyIcon = new ImageIcon("empty.jpg");

		//initialize a game using the default constructor
		game = new SuperTicTacToeGame();

		for (int row = 0; row < BD_SIZE; row++) {
			for (int col = 0; col < BD_SIZE; col++) {
				board[row][col] = new JButton("", emptyIcon);
				board[row][col].addActionListener(listener);
				//gamePanel.add(board[row][col]);
			}
		}
	}

//	private void displayBoard() {
//		for (int row = 0; row < BD_SIZE; row++)
//			for (int col = 0; col < BD_SIZE; col++) {
//				iCell = game.getCell(row,col);
//				// TODO: ImageIcon icon = icon for iCell
//				board[row][col].setIcon(icon);
//			}
//	}

	private class ButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent event) {

		}
	}


}
