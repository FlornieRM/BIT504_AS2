package TicTacToe;
	// Enumeration for the players move
	public enum Player 
	{
		Empty(" "), // Representing an empty cell
		Cross("X"), // Representing player Cross's move
		Nought("O"); // Representing player Nought's move

	private final String symbol;

		Player(String symbol) 
		{
			this.symbol = symbol;
		}

	public String getSymbol() 
		{
		return symbol;
		}
	}
