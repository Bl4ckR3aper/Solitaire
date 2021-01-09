import java.util.Scanner;

public class SpiderSolitaire
{
    /** Number of stacks on the board **/
    public final int NUM_STACKS = 6;

    /** Number of complete decks used in this game.  A 1-suit deck, which is the
     *  default for this lab, consists of 13 cards (Ace through King).
     */
    public final int NUM_DECKS = 4;

    /** A Board contains stacks and a draw pile **/
    private Board board;

    /** Used for keyboard input **/
    private Scanner input;

    public SpiderSolitaire()
    {
        // Start a new game with NUM_STACKS stacks and NUM_DECKS of cards
        board = new Board(NUM_STACKS, NUM_DECKS);
        
    }

    /** Main game loop that plays games until user wins or quits **/
    public void play() {

        board.printBoard();
        boolean gameOver = false;

        while(!gameOver) {
            System.out.println("\nCommands:");
            System.out.println("   move [card] [source_stack] [destination_stack]");
            System.out.println("   draw");
            System.out.println("   clear [source_stack]");
            System.out.println("   restart");
            System.out.println("   save");
            System.out.println("   load");
            System.out.println("   quit");
            System.out.print(">");
            input = new Scanner(System.in);
            String command = input.next();
            try
            {
                if (command.toLowerCase().equals("move")) {
                    /* *** TO BE MODIFIED IN ACTIVITY 5 *** */
                    String symbol = input.next();
                    int sourceStack = input.nextInt();
                    int destinationStack = input.nextInt();
                    board.makeMove(symbol, sourceStack, destinationStack);
                }
                else if (command.toLowerCase().equals("draw")) {
                    board.drawCards();
                }
                else if (command.toLowerCase().equals("clear")) {
                    /* *** TO BE MODIFIED IN ACTIVITY 5 *** */
                    int sourceStack = input.nextInt();
                    board.clear(sourceStack);
                }
                else if (command.toLowerCase().equals("restart")) {
                    board = new Board(NUM_STACKS, NUM_DECKS);
                }
                else if (command.toLowerCase().equals("save")) {
                   board.saveGame();  
                }
                else if (command.toLowerCase().equals("load")) {
                    board.loadGame();
                }
                else if (command.toLowerCase().equals("quit")) {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                else {
                    System.out.println("Invalid command.");
                }
    
                board.printBoard();
    
                // If all stacks and the draw pile are clear, you win!
                if (board.isEmpty()) {
                    gameOver = true;
                }
            }
            catch( Exception err) 
            {
                System.out.println("Invalid information was entered: " + err.getMessage()); 
            }
        }
        System.out.println("Congratulations!  You win!");
    }
}
