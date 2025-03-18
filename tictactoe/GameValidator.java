package tictactoe;

class GameValidator {
    public static boolean checkWin(Board board, char symbol) {
        int size = board.getSize();
        Cell[][] grid = board.getBoardState();

        // Check rows and columns
        for (int i = 0; i < size; i++) {
            if (checkRow(grid, i, symbol) || checkColumn(grid, i, symbol)) return true;
        }

        // Check diagonals
        return checkMainDiagonal(grid, symbol) || checkAntiDiagonal(grid, symbol);
    }

    private static boolean checkRow(Cell[][] grid, int row, char symbol) {
        for (int j = 0; j < grid.length; j++) {
            if (grid[row][j].getSymbol() != symbol) return false;
        }
        return true;
    }

    private static boolean checkColumn(Cell[][] grid, int col, char symbol) {
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][col].getSymbol() != symbol) return false;
        }
        return true;
    }

    private static boolean checkMainDiagonal(Cell[][] grid, char symbol) {
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][i].getSymbol() != symbol) return false;
        }
        return true;
    }

    private static boolean checkAntiDiagonal(Cell[][] grid, char symbol) {
        int size = grid.length;
        for (int i = 0; i < size; i++) {
            if (grid[i][size - i - 1].getSymbol() != symbol) return false;
        }
        return true;
    }

    public static boolean isDraw(Board board) {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getBoardState()[i][j].isEmpty()) return false;
            }
        }
        return true;
    }
}
