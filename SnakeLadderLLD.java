/*
1. Requirements

Functional Requirements :

The game is played on an N × N board.
Players roll a dice (1-6) and move forward.
If a player lands on a ladder, they move up.
If a player lands on a snake, they move down.
The first player to reach the last cell wins.
If a roll exceeds the last cell, the move is ignored.

Non-Functional Requirements :
The system should be scalable to accommodate multiple players.
The design should be extensible to add features like power-ups.


2. Class Diagram
Main Classes
Snake

start: int → Position where the snake's mouth is.
end: int → Position where the snake's tail is.
Ladder

start: int → Position where the ladder starts.
end: int → Position where the ladder ends.
Board

size: int → N x N size of the board.
snakes: List<Snake> → List of all snakes on the board.
ladders: List<Ladder> → List of all ladders on the board.
players: Queue<Player> → Queue of players taking turns.
Player

name: String → Player's name.
position: int → Player's current position.
Dice

roll(): int → Returns a random number between 1-6.
Game

board: Board → The game board instance.
players: Queue<Player> → The players in the game.
play(): void → Runs the game loop.


3) Code Implentaition:

Check code in the end. :)

4. Explanation
Snake & Ladder classes store start and end positions.
Board manages the positions of players, snakes, and ladders.
Dice generates random values (1-6).
Player keeps track of each player's position.
Game runs the game loop, checking dice rolls, updating positions, and checking for winners.
Main Method initializes the game with a board, players, and game loop.

5. Future Enhancements
Multiplayer Online Mode (Use WebSockets).
Undo/Redo Move Feature.
Custom Board Sizes.
Power-Ups (Skip turns, double dice, etc.).

Note:->

Few Code Enhancements :

✅ Custom board size (e.g., 5x5, 10x10, etc.)
✅ Custom number of dice (e.g., 1 die, 2 dice, etc.)

*/

  
import java.util.*;

class Snake {
    int start, end;

    public Snake(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class Ladder {
    int start, end;

    public Ladder(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class Player {
    String name;
    int position;

    public Player(String name) {
        this.name = name;
        this.position = 1;  // Start at position 1
    }
}

class Dice {
    int numDice;
    Random random;

    public Dice(int numDice) {
        this.numDice = numDice;
        this.random = new Random();
    }

    public int roll() {
        int sum = 0;
        for (int i = 0; i < numDice; i++) {
            sum += random.nextInt(6) + 1;
        }
        return sum;
    }
}

class Board {
    int size;
    List<Snake> snakes;
    List<Ladder> ladders;
    Queue<Player> players;

    public Board(int size, List<Snake> snakes, List<Ladder> ladders, List<Player> players) {
        this.size = size;
        this.snakes = snakes;
        this.ladders = ladders;
        this.players = new LinkedList<>(players);
    }

    public int getNewPosition(int position) {
        for (Snake snake : snakes) {
            if (snake.start == position) {
                return snake.end;
            }
        }
        for (Ladder ladder : ladders) {
            if (ladder.start == position) {
                return ladder.end;
            }
        }
        return position;
    }

    public int getWinningPosition() {
        return size * size;
    }
}

class Game {
    Board board;
    Dice dice;

    public Game(Board board, int numDice) {
        this.board = board;
        this.dice = new Dice(numDice);
    }

    public void play() {
        while (true) {
            Player player = board.players.poll();
            int roll = dice.roll();
            int newPosition = (player != null ? player.position : 0) + roll;

            if (newPosition > board.getWinningPosition()) {
                board.players.offer(player); // Skip turn if exceeding board
                continue;
            }

            newPosition = board.getNewPosition(newPosition);
            player.position = newPosition;

            System.out.println(player.name + " rolled " + roll + " and moved to " + player.position);

            if (player.position == board.getWinningPosition()) {
                System.out.println(player.name + " wins the game!");
                break;
            }

            board.players.offer(player);
        }
    }
}

public class SnakeLadderLLD {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Custom board size
        System.out.print("Enter board size (NxN): ");
        int boardSize = scanner.nextInt();

        // Custom number of dice
        System.out.print("Enter number of dice: ");
        int numDice = scanner.nextInt();

        List<Snake> snakes = Arrays.asList(new Snake(14, 7), new Snake(31, 9), new Snake(37, 17));
        List<Ladder> ladders = Arrays.asList(new Ladder(2, 23), new Ladder(8, 29), new Ladder(22, 41));
        List<Player> players = Arrays.asList(new Player("Alice"), new Player("Bob"));

        Board board = new Board(boardSize, snakes, ladders, players);
        Game game = new Game(board, numDice);

        game.play();
    }
}
