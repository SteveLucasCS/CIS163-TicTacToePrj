/**
 * @author David Whynot
 * Version: 1.0.0 June 4, 2018
 */
package package1;

import javax.swing.*;

/**********************************************************************
 * Creates a GUI for Super Tic Tac Toe by creating a JPanel object
 * (SuperTicTacToePanel) in the main method.
 *
 *********************************************************************/
public class SuperTicTacToe {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Super TicTacToe");
		SuperTicTacToePanel gamePanel = new SuperTicTacToePanel();
		frame.getContentPane().add(gamePanel);

		frame.setSize(1000, 1000);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
