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
 * @author David Whynot
 *
 */
public class SuperTicTacToePanel extends JPanel{

	// constants
	// private static final int BD_MIN_SIZE = 3;
	// private static final int BD_MAX_SIZE = 9;
	private static final String[] BD_SIZES = {"3", "4", "5", "6", "7", "8", "9"};


	// labels
	private JLabel title_lbl;
	private JLabel status_lbl;
	private JLabel xWins_lbl;
	private JLabel oWins_lbl;
	private JLabel cats_lbl;
	private JLabel connections_lbl;
	private JLabel bdSize_lbl;



	// ui components
	JComboBox connections_box;
	JComboBox bdSize_box;
	JCheckBox goFirst_tgl;



	// icons
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



	// counters
	private int xWins;
	private int oWins;
	private int cats;



	// buttons
	/**
	 *
	 */
	private JButton reset_btn;
	/**
	 * Quits the game, closing the GUI and ending the game.
	 */
	private JButton quit_btn;
	private JButton undo_btn;
	private JButton newGamebtn;



	// panels
	private JPanel base_pnl;
	private JPanel game_pnl;
	private JPanel btn_pnl;
	private JPanel lbl_pnl;
	private JPanel newGame_pnl;



	// misc
	/**
	 * ActionListener for the main panel
	 */
	private ButtonListener listener;

	/**
	 * A game object that handles the back end tasks of
	 * scoring and math methods.
	 */
	private SuperTicTacToeGame game;

	/**
	 * board is a 2D array of buttons. These buttons represent
	 * the cells on the game board, making up what the user
	 * sees and interacts with
	 */
	private JButton[][] board;

	/**
	 *
	 */
	private CellStatus iCell;




	public SuperTicTacToePanel() {
		// init counters
		xWins = 0;
		oWins = 0;
		cats = 0;

		// init labels
		title_lbl = new JLabel("SuperTicTacToe");
		status_lbl = new JLabel("Game In Progress");
		xWins_lbl = new JLabel("X Wins: ");
		oWins_lbl = new JLabel("O Wins: ");
		cats_lbl = new JLabel("Cats: ");
		connections_lbl = new JLabel("Number of Connections for Win:");
		bdSize_lbl = new JLabel("Size of Board:");

		// init ui components
		connections_box = new JComboBox(BD_SIZES);
		bdSize_box = new JComboBox(BD_SIZES);
		goFirst_tgl = new JCheckBox("AI goes first?");


		// init buttons
		reset_btn = new JButton("Reset");
		undo_btn = new JButton("Undo");

		// init panels
		base_pnl = new JPanel();
		game_pnl = new JPanel();

		// init game button listener
		listener = new ButtonListener();

		// init icons
		xIcon = new ImageIcon(getScaledImage(new ImageIcon("x.jpg").getImage(), 50, 50));
		oIcon = new ImageIcon(getScaledImage(new ImageIcon("o.jpg").getImage(), 50, 50));
		emptyIcon = new ImageIcon(getScaledImage(new ImageIcon("empty.jpg").getImage(), 50, 50));

		// init game
		game = new SuperTicTacToeGame();


		// add components to frame
		add(title_lbl);
		add(base_pnl);
		base_pnl.add(reset_btn);
		base_pnl.add(game_pnl);


		// layout
		setLayout(new FlowLayout());
		base_pnl.setLayout(new FlowLayout());
		game_pnl.setLayout(new GridLayout(game.getBDSIZE(), game.getBDSIZE()));


		// ui setup
		goFirst_tgl.setSelected(false);


		// add action listeners to components
		reset_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.reset();
				displayBoard();
			}
		});
		undo_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		// create game board
		fillBoard();
		displayBoard();
	}

	private void emptyBoard() {
		for (int row = 0; row < game.getBDSIZE(); ++row) {
			for (int col = 0; col < game.getBDSIZE(); ++col) {
				game_pnl.remove(board[row][col]);
			}
		}
		game_pnl.revalidate();
		game_pnl.repaint();
	}

	private void fillBoard() {
		game_pnl.setLayout(new GridLayout(game.getBDSIZE(), game.getBDSIZE()));
		board = new JButton[game.getBDSIZE()][game.getBDSIZE()];
		for (int row = 0; row < game.getBDSIZE(); ++row) {
			for (int col = 0; col < game.getBDSIZE(); ++col) {
				// create objects
				board[row][col] = new JButton("", emptyIcon);
				// register listeners
				board[row][col].addActionListener(listener);
				// add comoponents to frame
				game_pnl.add(board[row][col]);
				// System.out.println("\n\nrow: " + row + "\t col: " + col);
				// System.out.println(board[row][col].toString());
			}
		}
		game_pnl.revalidate();
		game_pnl.repaint();
	}

	private void gameOver() {
		System.out.println("Game Over");
		GameStatus status = game.getGameStatus();
		switch(status) {
			case X_WON:
				System.out.println("x won");
				++xWins;
				break;
			case O_WON:
				System.out.println("o won");
				++oWins;
				break;
			case CATS:
				System.out.println("cats");
				++cats;
				break;
			case IN_PROGRESS:
				System.out.println("Error: Game is still in progress... something must have went wrong.");
				break;
			default:
				System.out.println("Error: Default... something must have went wrong.");
				break;
		}
	}

	private void resizeBoard(int sz) {
		System.out.println("Resize Board");
		emptyBoard();
		game.setBDSIZE(sz);
		game.reset();
		fillBoard();
		displayBoard();
	}

	private void displayBoard() {
		System.out.println("Display Board");
		for (int row = 0; row < game.getBDSIZE(); ++row) {
			for (int col = 0; col < game.getBDSIZE(); ++col) {
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
		game_pnl.revalidate();
		game_pnl.repaint();
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
			System.out.println("ButtonListener Action Performed");
			boolean validMove = true;
			GameStatus status = game.getGameStatus();
			if(status == GameStatus.IN_PROGRESS) {
				System.out.println("Game in progress");
				for (int row = 0; row < game.getBDSIZE(); ++row) {
					for (int col = 0; col < game.getBDSIZE(); ++col) {
						if (board[row][col] == e.getSource()) {
							// tell the game which button was selected.
							try {
								game.select(row, col);
							} catch(IllegalArgumentException error) {
								validMove = false;
							}
						}
					}
				}
				if(validMove) {
					displayBoard();
					status = game.getGameStatus();
					if(status == GameStatus.IN_PROGRESS) {
						if(game.aiMove() != AIStatus.ERROR) {
							displayBoard();
							status = game.getGameStatus();
							if(status != GameStatus.IN_PROGRESS) {
								gameOver();
							}
						} else {
							System.out.println("AI error");
						}
					} else {
						gameOver();
					}
				}
			} else {
				System.out.println("Game is already over... moves not allowed");
			}
			displayBoard();
		}
	}


}
