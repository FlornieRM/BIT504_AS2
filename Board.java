package TicTacToe;

import java.awt.*;

public class Board {
	// grid line width
	public static final int GRID_WIDTH = 8;
	// grid line half width
	public static final int GRID_WIDHT_HALF = GRID_WIDTH / 2;
	
	//2D array of ROWS-by-COLS Cell instances
	Cell [][] cells;
	
	/* Constructor to create the game board */
	public Board() {

	/* 
 	   This code first declares and initializes the game board array with the desired size 
 	   (3x3 in this case). Then, it uses nested loops to populate each cell of the array with 
     	   a new Cell object, passing the row and column indices to the Cell constructor. 
	 */	
		private static final int ROWS = 3;
		private static final int COLS = 3;
		private Cell[][] cells = new Cell[ROWS][COLS];
		
		for (int row = 0; row < GameMain.ROWS; ++row) {
			for (int col = 0; col < GameMain.COLS; ++col) {
				cells[row][col] = new Cell(row, col);
			}
		}
	}
	

	 /* Return true if it is a draw (i.e., no more EMPTY cells) */ 
	public boolean isDraw()
	{
	/* 
 	This method uses a nested loop to iterate over each cell in the game board. If it finds an empty cell 
 	(i.e., its content is Player.Empty), it immediately returns false, indicating that the game has not ended in a draw. 
 	*/
		for (int row = 0; row
		for (int col = 0; col
		if (cells[row][col] == Player.Empty) {
		return false; // If an empty cell is found, it's not a draw
		}

		return true; // If no empty cells are found, it's a draw
	/* 
 	If the loop completes without finding any empty cells, the method returns true, indicating that the game has ended in 
 	a draw (since all cells are filled and no player has won).
 	*/
		
	}
	
	/* Return true if the current player "thePlayer" has won after making their move  */
	public boolean hasWon(Player thePlayer, int playerRow, int playerCol) 
	{
	// Check if the player has 3-in-that-row
		if(cells[playerRow][0].content == thePlayer && cells[playerRow][1].content == thePlayer && cells[playerRow][2].content == thePlayer ) 
		{
			return true;
		}
	// Check if the player has 3-in-that-column
		if(cells[0][playerCol].content == thePlayer && cells[1][playerCol].content == thePlayer && cells[2][playerCol].content == thePlayer ) 
		{
			return true;
		}

	// Check if the player has 3-in-the-diagonal
      	        if (cells[0][0].content == thePlayer && cells[1][1].content == thePlayer && cells[2][2].content == thePlayer) 
		{
           		 return true;
        	}

        // Check the diagonal in the other direction
       		 if (cells[0][2].content == thePlayer && cells[1][1].content == thePlayer && cells[2][0].content == thePlayer) 
		 {
            		return true;
        	}
		
	// If none of the above conditions are met, the player has not won
			return false; 
	}
		
	
	/**
	 * Draws the grid (rows and then columns) using constant sizes, then call on the
	 * Cells to paint themselves into the grid
	 */
	public void paint(Graphics g) {
		//draw the grid
		g.setColor(Color.gray);
		for (int row = 1; row < GameMain.ROWS; ++row) {          
			g.fillRoundRect(0, GameMain.CELL_SIZE * row - GRID_WIDHT_HALF,                
					GameMain.CANVAS_WIDTH - 1, GRID_WIDTH,                
					GRID_WIDTH, GRID_WIDTH);       
			}
		for (int col = 1; col < GameMain.COLS; ++col) {          
			g.fillRoundRect(GameMain.CELL_SIZE * col - GRID_WIDHT_HALF, 0,                
					GRID_WIDTH, GameMain.CANVAS_HEIGHT - 1,                
					GRID_WIDTH, GRID_WIDTH);
		}
		
		//Draw the cells
		for (int row = 0; row < GameMain.ROWS; ++row) {          
			for (int col = 0; col < GameMain.COLS; ++col) {  
				cells[row][col].paint(g);
			}
		}
	}
	

}
