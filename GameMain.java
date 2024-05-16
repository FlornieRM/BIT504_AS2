package TicTacToe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class GameMain extends JPanel implements MouseListener{
	//Constants for game 
	// number of ROWS by COLS cell constants 
	public static final int ROWS = 3;     
	public static final int COLS = 3;  
	public static final String TITLE = "Tic Tac Toe";
	public static final int CELL_SIZE = 100; //constants for dimensions used for drawing
	public static final int CANVAS_WIDTH = CELL_SIZE * COLS; //cell width and height
	public static final int CANVAS_HEIGHT = CELL_SIZE * ROWS;
	public static final int CELL_PADDING = CELL_SIZE / 6;    //Noughts and Crosses are displayed inside a cell, with padding from border
	public static final int SYMBOL_SIZE = CELL_SIZE - CELL_PADDING * 2;    
	public static final int SYMBOL_STROKE_WIDTH = 8;
	
	// Declare game object variables
	private Board board;
	private GameState currentState;
	private Player currentPlayer; // the current player
	private JLabel statusBar;  // for displaying game status message 
	
	// Enum for game states
	public enum GameState 
	{
		    PLAYING, // The game is currently being played.
		    DRAW, // The game has ended in a draw.
		    CROSS_WON, // The game has been won by the cross-player.
		    NOUGHT_WON // The game has been won by the nought player.
	}
	

	/* Constructor to setup the UI and game components on the panel */
	public GameMain() 
	{
	/*
 	By adding a MouseListener to the GameMain JPanel, you can now handle MouseClicked events and update the game 
  	state accordingly. You can put the logic to handle the MouseClicked event inside the mouseClicked method. For 
   	example, you can get the coordinates of the click event and determine which cell was clicked, then update the game 
    	state and display the updated game board.
 	*/
		this.addMouseListener(new MouseAdapter() 
		{
		public void mouseClicked(MouseEvent e) 
		{
		// Handle the MouseClicked event here
		}
	});
	// Setup the status bar (JLabel) to display status message
		statusBar = new JLabel(" ");
		statusBar.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 14));
		statusBar.setBorder(BorderFactory.createEmptyBorder(2, 5, 4, 5));
		statusBar.setOpaque(true);
		statusBar.setBackground(Color.LIGHT_GRAY);
	}
		// Layout of the panel is in border layout
		setLayout(new BorderLayout());
		add(statusBar, BorderLayout.SOUTH);

		// Account for statusBar height in overall height
		setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT + 30));

		// Create a new instance of the game "Board" class
		board = new Board();

		// Call the method to initialise the game board
		board.inboard();
	}
	
	public static void main(String[] args) 
	{
		// Run GUI code in Event Dispatch thread for thread safety.
		javax.swing.SwingUtilities.invokeLater(new Runnable() 
		{
			public void run() 
			{
		// Create a main window to contain the panel
			JFrame frame = new JFrame(TITLE);

		// Create the new GameMain panel and add it to the frame
			GameMain gameMainPanel = new GameMain();
			frame.add(gameMainPanel);

		// Set the default close operation of the frame to exit_on_close
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*
		  This code creates a new JFrame (main window) and adds an instance of the GameMain panel to it. 
    		  It also sets the default close operation of the frame to exit_on_close, which means the program will 
		  exit when the window is closed. Finally, it sets the window to be visible and centres it on the screen.
  		*/
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			}
		});
	}

	/* Custom painting codes on this JPanel */
	public void paintComponent(Graphics g) 
	{
		// Fill background and set color to white
		super.paintComponent(g);
		setBackground(Color.WHITE);

		// Ask the game board to paint itself
		board.paint(g);

		// Set status bar message
		if (currentState == GameState.Playing) 
		{
			statusBar.setForeground(Color.BLACK);
			if (currentPlayer == Player.Cross) 
			{
			statusBar.setText("X's Turn"); // Display "X's Turn" message
		} 
			else 
			{
			statusBar.setText("O's Turn"); // Display "O's Turn" message
			}
		}
	    
				else if (currentState == GameState.Draw) 
				{          
				statusBar.setForeground(Color.RED);          
				statusBar.setText("It's a Draw! Click to play again.");       
				} 
				else if (currentState == GameState.Cross_won) 
				{          
				statusBar.setForeground(Color.RED);          
				statusBar.setText("'X' Won! Click to play again.");       
				} 
				else if (currentState == GameState.Nought_won) 
				{          
				statusBar.setForeground(Color.RED);          
				statusBar.setText("'O' Won! Click to play again.");       
				}
	}
		
	
	  /** Initialise the game-board contents and the current status of GameState and Player) */
		public void initGame() {
			for (int row = 0; row < ROWS; ++row) {          
				for (int col = 0; col < COLS; ++col) {  
					// all cells empty
					board.cells[row][col].content = Player.Empty;           
				}
			}
			 currentState = GameState.Playing;
			 currentPlayer = Player.Cross;
		}
		
		
		/* 
  		    After each turn, check to see if the current player has won by putting their symbol in that position.
			- If they have, the GameState is set to won for that player.
			- If no winner, then isDraw is called to see if deadlock; if not, GameState stays as PLAYING.
		*/
		public void updateGame(Player thePlayer, int row, int col) {
		// Check for win after play
			if (board.hasWon(thePlayer, row, col)) 
	{
			if (thePlayer == Player.Cross) 
		{
		currentState = GameState.CROSS_WON; // Set current state to CROSS_WON
		} 
			else 
		{
		currentState = GameState.NOUGHT_WON; // Set current state to NOUGHT_WON
		}
	} 
			else if (board.isDraw()) 
		{
		currentState = GameState.DRAW; // Set current state to DRAW
		}
		// Otherwise, no change to current state of PLAYING
	}
		/*
  		This code updates the game state based on the result of the current player's move. 
    		If the player has won, it sets the current state to the appropriate winning state (CROSS_WON or NOUGHT_WON). 
      		If the game is a draw, it sets the current state to DRAW. If neither of these conditions is met, the game 
		remains in the PLAYING state.
  		*/
				
	
		/* 
  		   Event handler for the mouse click on the JPanel. If selected cell is valid and Empty then current player is added to cell content.
		   UpdateGame is called which will call the methods to check for winner or Draw. if none then GameState remains playing.
		   If win or Draw then call is made to method that resets the game board.  Finally a call is made to refresh the canvas so 
     		   that new symbol appears
		*/
	
		public void mouseClicked(MouseEvent e) 
		{  
	    		// Get the coordinates of where the click event happened            
			int mouseX = e.getX();             
			int mouseY = e.getY();             
			// Get the row and column clicked             
			int rowSelected = mouseY / CELL_SIZE;             
			int colSelected = mouseX / CELL_SIZE;               			
			if (currentState == GameState.Playing) 
			{                
				if (rowSelected >= 0 && rowSelected < ROWS && colSelected >= 0 && colSelected < COLS && board.cells[rowSelected][colSelected].content == Player.Empty) {
				// move  
				board.cells[rowSelected][colSelected].content = currentPlayer; 
				// update currentState                  
				updateGame(currentPlayer, rowSelected, colSelected); 
				// Switch player
				if (currentPlayer == Player.Cross) 
				{
					currentPlayer =  Player.Nought;
				}
				else 
					{
					currentPlayer = Player.Cross;
					}
			}             
		} 
			else 
			{        
			// game over and restart              
			initGame();            
			}   
		
			/*
			The repaint() method is called to update the UI and reflect the changes made to the game board. 
			This ensures that the graphics are redrawn and the user can see the updated game state.
			*/
			repaint();
	}

		
	
	@Override
	public void mousePressed(MouseEvent e) {	
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
		
	@Override
	public void mouseEntered(MouseEvent e) {
	}
		
	@Override
	public void mouseExited(MouseEvent e) {
	}

}
