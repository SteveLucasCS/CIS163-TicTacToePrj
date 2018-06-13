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
	private JButton newGame_btn;



	// panels
	private JPanel base_pnl;
	private JPanel game_pnl;
	private JPanel btn_pnl;
	private JPanel lbl_pnl;
	private JPanel newGame_pnl;
	private JPanel wins_pnl;



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
		status_lbl = new JLabel("Game In Progress");
		xWins_lbl = new JLabel("X Wins: 0");
		oWins_lbl = new JLabel("O Wins: 0");
		cats_lbl = new JLabel("Cats: 0");
		connections_lbl = new JLabel("Number of Connections for Win: 3");
		bdSize_lbl = new JLabel("Size of Board: 3");

		// init ui components
		connections_box = new JComboBox(BD_SIZES);
		bdSize_box = new JComboBox(BD_SIZES);
		goFirst_tgl = new JCheckBox("AI goes first?");


		// init buttons
		reset_btn = new JButton("Reset");
		undo_btn = new JButton("Undo");
		quit_btn = new JButton("Quit");
		newGame_btn = new JButton("New Game");

		// init panels
		base_pnl = new JPanel();
		game_pnl = new JPanel();
		btn_pnl = new JPanel();
		lbl_pnl = new JPanel();
		newGame_pnl = new JPanel();
		wins_pnl = new JPanel();

		// init game button listener
		listener = new ButtonListener();

		// init icons
		xIcon = new ImageIcon(getScaledImage(new ImageIcon("x.jpg").getImage(), 50, 50));
		oIcon = new ImageIcon(getScaledImage(new ImageIcon("o.jpg").getImage(), 50, 50));
		emptyIcon = new ImageIcon(getScaledImage(new ImageIcon("empty.jpg").getImage(), 50, 50));

		// init game
		game = new SuperTicTacToeGame();


		// add components to frame
		setLayout(new GridLayout(2,1));
		add(game_pnl);
		add(base_pnl);
		base_pnl.setLayout(new GridLayout(1,3));
		base_pnl.add(btn_pnl);
		base_pnl.add(lbl_pnl);
		lbl_pnl.setLayout(new BorderLayout());
		base_pnl.add(newGame_pnl);

		btn_pnl.add(reset_btn);
		btn_pnl.add(undo_btn);
		btn_pnl.add(quit_btn);

		lbl_pnl.add(status_lbl, BorderLayout.NORTH);
		lbl_pnl.add(wins_pnl, BorderLayout.SOUTH);
		wins_pnl.add(xWins_lbl);
		wins_pnl.add(oWins_lbl);
		wins_pnl.add(cats_lbl);

		newGame_pnl.add(connections_lbl);
		newGame_pnl.add(connections_box);
		newGame_pnl.add(bdSize_lbl);
		newGame_pnl.add(bdSize_box);
		newGame_pnl.add(goFirst_tgl);
		newGame_pnl.add(newGame_btn);




		// layout
		btn_pnl.setLayout(new FlowLayout());
		lbl_pnl.setLayout(new FlowLayout());
		newGame_pnl.setLayout(new FlowLayout());
		game_pnl.setLayout(new GridLayout(game.getBDSIZE(), game.getBDSIZE()));


		// ui setup
		goFirst_tgl.setSelected(false);


		// add action listeners to components
		reset_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.reset();
				displayBoard();
				status_lbl.setText("Game In Progress");
			}
		});
		undo_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(game.getGameStatus() == GameStatus.IN_PROGRESS) {
					game.undo();
					displayBoard();
					status_lbl.setText("Game In Progress");
				}
			}
		});
		quit_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		newGame_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resizeBoard(Integer.parseInt(bdSize_box.getSelectedItem().toString()));
				try {
					game.setWINCON(Integer.parseInt(connections_box.getSelectedItem().toString()));
				} catch(IllegalArgumentException error) {
					JOptionPane.showMessageDialog(null, "Connections must be less than board size");
				}
				if(goFirst_tgl.isSelected()) {
					game.aiMove();
					displayBoard();
				}
				connections_lbl.setText("Number of Connections for Win: " + game.getWINCON());
				bdSize_lbl.setText("Size of Board: " + game.getBDSIZE());
				status_lbl.setText("Game In Progress");
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
			}
		}
		game_pnl.revalidate();
		game_pnl.repaint();
	}

	private void gameOver() {
		GameStatus status = game.getGameStatus();
		switch(status) {
			case X_WON:
				status_lbl.setText("x won");
				++xWins;
				xWins_lbl.setText("X Wins: " + xWins);
				break;
			case O_WON:
				status_lbl.setText("o won");
				++oWins;
				oWins_lbl.setText("O Wins: " + oWins);
				break;
			case CATS:
				status_lbl.setText("cats");
				++cats;
				cats_lbl.setText("Cats: " + cats);
				break;
			case IN_PROGRESS:
				status_lbl.setText("Error: Game is still in progress... something must have went wrong.");
				break;
			default:
				status_lbl.setText("Error: Default... something must have went wrong.");
				break;
		}
	}

	private void resizeBoard(int sz) {
		emptyBoard();
		game.setBDSIZE(sz);
		game.reset();
		fillBoard();
		displayBoard();
	}

	private void displayBoard() {
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
				board[row][col].setMinimumSize(new Dimension(50, 50));
				board[row][col].setMaximumSize(new Dimension(50, 50));
				// board[row][col].setWidth(new Dimension(50, 50));
				// board[row][col].setHeight(new Dimension(50, 50));
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
			boolean validMove = true;
			GameStatus status = game.getGameStatus();
			if(status == GameStatus.IN_PROGRESS) {
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
							System.out.println("AI Error");
						}
					} else {
						gameOver();
					}
				}
			} else {
			}
			displayBoard();
		}
	}


}
