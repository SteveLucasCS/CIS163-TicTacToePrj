package package1;

import org.junit.Test;

import static org.junit.Assert.*;


public class TestSuperTicTacToeGame {
	
	//private SuperTicTacToeGame game;
	
	/******************************************************************
	 * setBDSIZE testing STARTS
	 *****************************************************************/
	@Test (expected = IllegalArgumentException.class)
	public void setBDSIZEOutOfRange1() {
		SuperTicTacToeGame.setBDSIZE(10);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setBDSIZEOutOfRange2() {
		SuperTicTacToeGame.setBDSIZE(2);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setBDSIZEOutOfRange3() {
		SuperTicTacToeGame.setBDSIZE(0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setBDSIZEOutOfRange4() {
		SuperTicTacToeGame.setBDSIZE(-1);
	}
	
	@Test
	public void setBDSIZEvalid1() {
		SuperTicTacToeGame.setBDSIZE(3);
		assertEquals(SuperTicTacToeGame.BDSIZE, 3);
	}
	
	@Test
	public void setBDSIZEvalid2() {
		SuperTicTacToeGame.setBDSIZE(8);
		assertEquals(SuperTicTacToeGame.BDSIZE, 8);
	}
	
	/******************************************************************
	 * setBDSZIE method ENDS
	 *****************************************************************/
	
	/******************************************************************
	 * setWINCON method STARTS
	 *****************************************************************/
	@Test
	public void setWINCONvalid() {
		SuperTicTacToeGame.setBDSIZE(9);
		SuperTicTacToeGame.setWINCON(3);
		assertEquals(SuperTicTacToeGame.WINCON, 3);
		SuperTicTacToeGame.setWINCON(6);
		assertEquals(SuperTicTacToeGame.WINCON, 6);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setWINCONOutOfRange1() {
		SuperTicTacToeGame.setBDSIZE(4);
		SuperTicTacToeGame.setWINCON(6);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setWINCONOutOfRange2() {
		SuperTicTacToeGame.setBDSIZE(4);
		SuperTicTacToeGame.setWINCON(0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setWINCONOutOfRange3() {
		SuperTicTacToeGame.setBDSIZE(4);
		SuperTicTacToeGame.setWINCON(-1);
	}
	
	/******************************************************************
	 * setWINCON method testing ENDS
	 *****************************************************************/
	
	/******************************************************************
	 * getCell(row,col) method testing STARTS
	 *****************************************************************/
	@Test (expected = IllegalArgumentException.class)
	public void getCellNegative1() {
		SuperTicTacToeGame game = new SuperTicTacToeGame();
		game.getCell(-1, 0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void getCellNegative2() {
		SuperTicTacToeGame game = new SuperTicTacToeGame();
		game.getCell(0,-1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void getCellNegative3() {
		SuperTicTacToeGame game = new SuperTicTacToeGame();
		game.getCell(-1, -2);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void getCellOutOfRange1() {
		SuperTicTacToeGame game = new SuperTicTacToeGame();
		game.getCell(20,20);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void getCellOutOfRange2() {
		SuperTicTacToeGame game = new SuperTicTacToeGame();
		game.getCell(2,20);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void getCellOutOfRange3() {
		SuperTicTacToeGame game = new SuperTicTacToeGame();
		game.getCell(20,2);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void getCellOutOfRange4() {
		SuperTicTacToeGame.setBDSIZE(5);
		SuperTicTacToeGame game = new SuperTicTacToeGame();
		game.getCell(5, 3);		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void getCellOutOfRange5() {
		SuperTicTacToeGame.setBDSIZE(5);
		SuperTicTacToeGame game = new SuperTicTacToeGame();
		game.getCell(2, 5);		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void getCellOutOfRange6() {
		SuperTicTacToeGame.setBDSIZE(5);
		SuperTicTacToeGame game = new SuperTicTacToeGame();
		game.getCell(5, -1);		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void getCellOutOfRange7() {
		SuperTicTacToeGame.setBDSIZE(5);
		SuperTicTacToeGame game = new SuperTicTacToeGame();
		game.getCell(-1, 5);		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void getCellOutOfRange8() {
		SuperTicTacToeGame.setBDSIZE(5);
		SuperTicTacToeGame game = new SuperTicTacToeGame();
		game.getCell(-1, 3);		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void getCellOutOfRange9() {
		SuperTicTacToeGame.setBDSIZE(5);
		SuperTicTacToeGame game = new SuperTicTacToeGame();
		game.getCell(3, -1);		
	}

	@Test 
	public void getCelValid1() {
		SuperTicTacToeGame.setBDSIZE(5);
		SuperTicTacToeGame game = new SuperTicTacToeGame();
		assertEquals(game.getCell(3, 3), CellStatus.EMPTY);
	}
	
	@Test 
	public void getCelValid2() {
		SuperTicTacToeGame.setBDSIZE(5);
		SuperTicTacToeGame game = new SuperTicTacToeGame();
		assertEquals(game.getCell(0, 0), CellStatus.EMPTY);
	}

	@Test 
	public void getCelValid4() {
		SuperTicTacToeGame.setBDSIZE(5);
		SuperTicTacToeGame game = new SuperTicTacToeGame();
		assertEquals(game.getCell(4, 4), CellStatus.EMPTY);
	}
	
	@Test 
	public void getCelValid5() {
		SuperTicTacToeGame.setBDSIZE(9);
		SuperTicTacToeGame game = new SuperTicTacToeGame();
		assertEquals(game.getCell(8, 8), CellStatus.EMPTY);
	}
	
	@Test 
	public void getCelValid() {
		SuperTicTacToeGame.setBDSIZE(8);
		SuperTicTacToeGame game = new SuperTicTacToeGame();
		assertEquals(game.getCell(0, 3), CellStatus.EMPTY);
	}

	/******************************************************************
	 * getCell(row,col) method testing ENDS
	 *****************************************************************/
	/******************************************************************
	 * null game object testing STARTS
	 *****************************************************************/
	
	@Test (expected = NullPointerException.class)
	public void nullGameObject1() {
		SuperTicTacToeGame game = null;
		game.getGameStatus();
	}
	
	@Test (expected = NullPointerException.class)
	public void nullGameObject2() {
		SuperTicTacToeGame game = null;
		game.select(1,1);
	}
	
	@Test (expected = NullPointerException.class)
	public void nullGameObject3() {
		SuperTicTacToeGame game = null;
		game.getCell(1,1);
	}
	
	@Test (expected = NullPointerException.class)
	public void nullGameObject4() {
		SuperTicTacToeGame game = null;
		game.reset();
	}
	
	/******************************************************************
	 * null game object testing ENDS
	 *****************************************************************/
	
	/******************************************************************
	 * select() method testing STARTS
	 *****************************************************************/
	@Test
	public void selectValid() {
		SuperTicTacToeGame.setBDSIZE(5);
		SuperTicTacToeGame.setWINCON(5);
		SuperTicTacToeGame game = new SuperTicTacToeGame();
		assertEquals(game.getGameStatus(),GameStatus.IN_PROGRESS);
		assertEquals(game.getCell(0, 0),CellStatus.EMPTY);
		game.select(1,1);
		assertEquals(game.getCell(1,1),CellStatus.X);
		game.select(1,2);
		assertEquals(game.getCell(1, 2),CellStatus.X);
		assertEquals(game.getCell(0, 0),CellStatus.EMPTY);
		assertEquals(game.getGameStatus(),GameStatus.IN_PROGRESS);
		
	}
	
	@Test
	public void testGame() {
		SuperTicTacToeGame.setBDSIZE(5);
		SuperTicTacToeGame.setWINCON(5);
		SuperTicTacToeGame game = new SuperTicTacToeGame();
		assertEquals(game.getGameStatus(),GameStatus.IN_PROGRESS);
		assertEquals(game.getCell(0, 0),CellStatus.EMPTY);
		game.select(1,1);
		assertEquals(game.getCell(1,1),CellStatus.X);
		game.select(1,2);
		assertEquals(game.getCell(1, 2),CellStatus.X);
		assertEquals(game.getCell(0, 0),CellStatus.EMPTY);
		assertEquals(game.getGameStatus(),GameStatus.IN_PROGRESS);
		game.select(1,3);
		game.select(1,4);
		game.select(1,0);
		assertEquals(GameStatus.X_WON,game.getGameStatus());	
	}
	
	@Test
	public void testWinHorizontal() {
		SuperTicTacToeGame.setBDSIZE(3);
		SuperTicTacToeGame.setWINCON(3);
		SuperTicTacToeGame game = new SuperTicTacToeGame();
		game.select(0,0);
		game.select(1, 0);
		game.select(2, 0);
		assertEquals(game.getGameStatus(),GameStatus.X_WON);
	}
	
	@Test
	public void testWinVertical() {
		SuperTicTacToeGame.setBDSIZE(3);
		SuperTicTacToeGame.setWINCON(3);
		SuperTicTacToeGame game = new SuperTicTacToeGame();
		game.select(0,1);
		game.select(0, 0);
		game.select(0, 2);
		assertEquals(game.getGameStatus(),GameStatus.X_WON);
	}
	
}
