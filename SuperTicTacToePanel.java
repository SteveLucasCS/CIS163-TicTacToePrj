package package1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;


/**
 * @author steve
 *
 */
public class SuperTicTacToePanel extends JPanel{

	private static int BD_MIN_SIZE = 3;
	private static int BD_MAX_SIZE = 9;
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

	private JSlider boardSizeSlider;

	private int x_wins;

	private int o_wins;

	private int cats;

	private JButton resetButton;

	private JPanel basePanel;

	private JPanel gameBoard;

	public SuperTicTacToePanel() {
		x_wins = 0;
		o_wins = 0;
		cats = 0;

		resetButton = new JButton("Reset");

		basePanel = new JPanel();
		gameBoard = new JPanel();

		listener = new ButtonListener();

		// create this panels game
		game = new SuperTicTacToeGame();

		// init boardSizeSlider with correct vals
		boardSizeSlider = new JSlider(JSlider.HORIZONTAL, BD_MIN_SIZE, BD_MAX_SIZE, SuperTicTacToeGame.BDSIZE);

		//initialize xIcon with image file path
		xIcon = new ImageIcon(getScaledImage(new ImageIcon("x.jpg").getImage(), 50, 50));

		//initialize oIcon with image file path
		oIcon = new ImageIcon(getScaledImage(new ImageIcon("o.jpg").getImage(), 50, 50));

		//initialize emptyIcon with image file path
		emptyIcon = new ImageIcon(getScaledImage(new ImageIcon("empty.jpg").getImage(), 50, 50));

		//initialize a game using the default constructor
		game = new SuperTicTacToeGame();


		// style slider
		boardSizeSlider.setPaintTicks(true);
		boardSizeSlider.setPaintLabels(true);
		boardSizeSlider.setMajorTickSpacing(1);
		boardSizeSlider.setSnapToTicks(true);


		// add components to frame
		add(basePanel);
		basePanel.add(resetButton);
		// basePanel.add(boardSizeSlider);
		basePanel.add(gameBoard);


		// layout
		basePanel.setLayout(new FlowLayout());
		gameBoard.setLayout(new GridLayout(SuperTicTacToeGame.BDSIZE, SuperTicTacToeGame.BDSIZE));


		// add action listeners to components
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.reset();
				displayBoard();
			}
		});

		boardSizeSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				basePanel.remove(gameBoard);
				JSlider source = (JSlider)e.getSource();
				game.setBDSIZE(source.getValue());
				game.reset();
				gameBoard.setLayout(new GridLayout(SuperTicTacToeGame.BDSIZE, SuperTicTacToeGame.BDSIZE));
				createGameBoard();
				gameBoard.revalidate();
				basePanel.add(gameBoard);
				basePanel.revalidate();
			}
		});

		createGameBoard();
	}

	private void createGameBoard() {
		board = new JButton[SuperTicTacToeGame.BDSIZE][SuperTicTacToeGame.BDSIZE];
		// create game board
		for (int row = 0; row < SuperTicTacToeGame.BDSIZE; ++row) {
			for (int col = 0; col < SuperTicTacToeGame.BDSIZE; ++col) {
				// create objects
				board[row][col] = new JButton("", emptyIcon);
				// register listeners
				board[row][col].addActionListener(listener);
				// add comoponents to frame
				gameBoard.add(board[row][col]);
				System.out.println("\n\nrow: " + row + "\t col: " + col);
				System.out.println(board[row][col].toString());
			}
		}
		displayBoard();
	}

	private void displayBoard() {
		for (int row = 0; row < SuperTicTacToeGame.BDSIZE; ++row) {
			for (int col = 0; col < SuperTicTacToeGame.BDSIZE; ++col) {
				iCell = game.getCell(row,col);
				ImageIcon icon;
				switch(iCell) {
					case EMPTY:
					icon = emptyIcon;
					break;
					case O:
					icon = oIcon;
					break;
					case X:
					icon = xIcon;
					break;
					default:
					icon = emptyIcon;
					break;
				}

				// STYLE BUTTONS HERE
				board[row][col].setIcon(icon);
				board[row][col].setPreferredSize(new Dimension(50, 50));
			}
		}
	}

	private Image getScaledImage(Image srcImg, int w, int h) {
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();

		return resizedImg;
	}

	private class ButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			// TODO: improve flow control logic
			GameStatus status = game.getGameStatus();
			if(status == GameStatus.O_WON) {
				System.out.println("o won");
				++o_wins;
			} else {
				for (int row = 0; row < SuperTicTacToeGame.BDSIZE; ++row) {
					for (int col = 0; col < SuperTicTacToeGame.BDSIZE; ++col) {
						if (board[row][col] == e.getSource()) {
							// tell the game which button was selected.
							game.select(row, col);
						}
					}
				}
			}
			status = game.getGameStatus();
			switch(status) {
				case IN_PROGRESS:
					System.out.println("Game in progress");
					game.aiMove();
					if(status == GameStatus.O_WON) {
						System.out.println("o won");
						++o_wins;
					}
					break;
				case X_WON:
					System.out.println("x won");
					++x_wins;
					break;
				case O_WON:
					System.out.println("o won");
					++o_wins;
					break;
				case CATS:
					System.out.println("cats");
					++cats;
					break;
				default:
					System.out.println("default... something must have went wrong");
					break;
			}
			displayBoard();
		}
	}


}
