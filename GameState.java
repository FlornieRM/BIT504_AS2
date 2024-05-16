package TicTacToe;

/*
  Enumeration for the different states of the Tic-Tac-Toe game.
  This enum is used to track the current state of the game.
 */
      public enum GameState 
    {
        Playing, // Game is currently in progress.
        Draw, // The game has ended in a draw.
        CrossWon, // The game ended with the crosses ('X') player winning.
        NoughtWon  // The game has ended with the noughts ('O') player winning.
    }
