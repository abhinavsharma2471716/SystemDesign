package tictactoe;

import java.util.Scanner;

class Game {
    private Board board;
    private Player player1, player2;
    private Player currentPlayer;

    public Game(Player player1, Player player2, int boardSize) {
        this.board = new Board(boardSize);
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            board.printBoard();
            System.out.println(currentPlayer.getName() + "'s turn. Enter row and column: ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (!board.makeMove(row, col, currentPlayer.getSymbol())) {
                System.out.println("Invalid move! Try again.");
                continue;
            }

            if (GameValidator.checkWin(board, currentPlayer.getSymbol())) {
                board.printBoard();
                System.out.println(currentPlayer.getName() + " wins!");
                break;
            }

            if (GameValidator.isDraw(board)) {
                board.printBoard();
                System.out.println("It's a draw!");
                break;
            }

            // Switch turn
            currentPlayer = (currentPlayer == player1) ? player2 : player1;
        }

        scanner.close();
    }
}
