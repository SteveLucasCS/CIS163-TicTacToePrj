package package1;

import org.junit.Test;

import static org.junit.Assert.*;


class SuperTicTacToeGameTest {
	
	private SuperTicTacToeGame game;
	
	@Test (expected = IllegalArgumentException.class)
	void getCellNegative1() {
		game = new SuperTicTacToeGame();
		game.getCell(-1, 0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	void getCellNegative2() {
		game = new SuperTicTacToeGame();
		game.getCell(0,-1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	void getCellNegative3() {
		game = new SuperTicTacToeGame();
		game.getCell(-1, -2);
	}
	
	@Test (expected = IllegalArgumentException.class)
	void getCellOutOfRange1() {
		game = new SuperTicTacToeGame();
		game.getCell(20,20);
	}
	
	@Test (expected = IllegalArgumentException.class)
	void getCellOutOfRange2() {
		game = new SuperTicTacToeGame();
		game.getCell(2,20);
	}
	
	@Test (expected = IllegalArgumentException.class)
	void getCellOutOfRange3() {
		game = new SuperTicTacToeGame();
		game.getCell(20,2);
	}
	
	@Test (expected = IllegalArgumentException.class)
	void getCellOutOfRange4() {
		SuperTicTacToeGame.setBDSIZE(5);
		game = new SuperTicTacToeGame();
		game.getCell(5, 3);		
	}
	
	@Test (expected = IllegalArgumentException.class)
	void getCellOutOfRange5() {
		SuperTicTacToeGame.setBDSIZE(5);
		game = new SuperTicTacToeGame();
		game.getCell(2, 5);		
	}
	
	@Test (expected = IllegalArgumentException.class)
	void getCellOutOfRange6() {
		SuperTicTacToeGame.setBDSIZE(5);
		game = new SuperTicTacToeGame();
		game.getCell(5, -1);		
	}
	
	@Test (expected = IllegalArgumentException.class)
	void getCellOutOfRange7() {
		SuperTicTacToeGame.setBDSIZE(5);
		game = new SuperTicTacToeGame();
		game.getCell(-1, 5);		
	}
	
	@Test (expected = IllegalArgumentException.class)
	void getCellOutOfRange8() {
		SuperTicTacToeGame.setBDSIZE(5);
		game = new SuperTicTacToeGame();
		game.getCell(-1, 3);		
	}
	
	@Test (expected = IllegalArgumentException.class)
	void getCellOutOfRange9() {
		SuperTicTacToeGame.setBDSIZE(5);
		game = new SuperTicTacToeGame();
		game.getCell(3, -1);		
	}

	@Test 
	void getCelValid1() {
		SuperTicTacToeGame.setBDSIZE(5);
		game = new SuperTicTacToeGame();
		assertEquals(game.getCell(3, 3), CellStatus.EMPTY);
	}
	
	@Test 
	void getCelValid2() {
		SuperTicTacToeGame.setBDSIZE(5);
		game = new SuperTicTacToeGame();
		assertEquals(game.getCell(0, 0), CellStatus.EMPTY);
	}

	@Test 
	void getCelValid4() {
		SuperTicTacToeGame.setBDSIZE(5);
		game = new SuperTicTacToeGame();
		assertEquals(game.getCell(4, 4), CellStatus.EMPTY);
	}
	
	@Test 
	void getCelValid5() {
		SuperTicTacToeGame.setBDSIZE(9);
		game = new SuperTicTacToeGame();
		assertEquals(game.getCell(8, 8), CellStatus.EMPTY);
	}
	
	@Test 
	void getCelValid() {
		SuperTicTacToeGame.setBDSIZE(8);
		game = new SuperTicTacToeGame();
		assertEquals(game.getCell(0, 3), CellStatus.EMPTY);
	}


}
