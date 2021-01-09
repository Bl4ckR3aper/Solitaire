import java.util.ArrayList; 
import java.io.File; 
import java.io.FileWriter; 
import java.awt.EventQueue; 
import javax.swing.JFileChooser; 
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;  
public class Board
{   
  
    /* *** TO BE IMPLEMENTED IN ACTIVITY 4 *** */
    // Attributes
    ArrayList<Deck> stacks = new ArrayList<Deck>();
    ArrayList<Deck> completedStacks = new ArrayList<Deck>();
    Deck drawPile; 
    /**
     *  Sets up the Board and fills the stacks and draw pile from a Deck
     *  consisting of numDecks Decks.  Here are examples:
     *  
     *  # numDecks     #cards in overall Deck
     *      1          13 (all same suit)
     *      2          26 (all same suit)
     *      3          39 (all same suit)
     *      4          52 (all same suit)
     *      
     *  Once the overall Deck is built, it is shuffled and half the cards
     *  are placed as evenly as possible into the stacks.  The other half
     *  of the cards remain in the draw pile.
     */
    
    public Board(int numStacks, int numDecks) {
        String [] symbols ={"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
        int [] value = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        ArrayList<Card>deck = new ArrayList<Card>();
        for(int i = 0; i < numDecks; i++)
        {
            for (int l = 0; l < 13; l++)
            {
                deck.add(new Card(symbols[l],value[l]));   
            }
        }
        Deck stack = new Deck(deck);
        stack.shuffle();
        drawPile = new Deck(stack.split());
        
        int n = numStacks;
        for(int i = 0; i < numStacks; i++)
        {
            ArrayList<Card> temp = new ArrayList<Card>();
            int size = stack.size();
            for(int l = size-1; l >=0; l-=n)
            {
                temp.add(stack.getCard(l)); 
            }
            for(int l = size-1; l >=0; l-=n)
            {
                stack.removeCard(l);
            }
            Deck d = new Deck(temp); 
            stacks.add(d);
            n--; 
        }
    }
    public Board(ArrayList<Deck> stack, ArrayList<Card> draw, ArrayList<Deck> completed)
    {
        this.stacks = stack; 
        Deck d = new Deck(draw); 
        this.drawPile = d; 
        this.completedStacks = completed; 
    }
    /**
     *  Moves a run of cards from src to dest (if possible) and flips the
     *  next card if one is available.
     */
    public void makeMove(String symbol, int src, int dest) {
        Deck deck = this.stacks.get(src-1);//-1
        int found = 0; 
        for (int i = deck.size()-1; i >=0; i--)
        {
            Card card = deck.getCard(i);
            if(card.isFaceUp() && card.getSymbol().equals(symbol))
            {
                found ++; 
                int check = 0; 
                for(int l = i; l < deck.size()-1; l++)
                {
                    if (deck.getCard(l).getValue()+1 != deck.getCard(l+1).getValue())
                    {
                        check = 0; 
                        l+= deck.size();
                    }
                    else
                        check ++; 
                }
                Deck temp = stacks.get(dest-1); 
                if(check == (deck.size()-i-1) && temp.size() > 0)
                { 
                    Card top_des = temp.getCard(temp.size()-1);
                    if (top_des.getValue()+1 == card.getValue() )
                    {
                        if( i > 0)
                            deck.getCard(i-1).setFaceUp(true);
                        for (int l = i; l < deck.size(); i+=0)
                        {
                            temp.addCard(deck.getCard(l));
                            deck.removeCard(l);
                        }
                        System.out.println("\n\nValid Move!\n\n");
                    }
                    else
                        System.out.println("Invalid card on Destination!");
                }
                else if(temp.size() == 0)
                {
                    if( i > 0)
                            deck.getCard(i-1).setFaceUp(true);
                    for (int l = i; l < deck.size(); i+=0)
                    {
                        temp.addCard(deck.getCard(l));
                        deck.removeCard(l);
                    }
                    System.out.println("\n\n Valid Move!\n\n");
                }
                else
                    System.out.println("\n\nInvalid run!\n\n");
            }
        }
        if( found == 0) 
            System.out.println("\n\nCard not Found!\n\n"); 
    }

    /** 
     *  Moves one card onto each stack, or as many as are available
     */
    public void drawCards() {
        int stack_size = stacks.size();
        boolean empty = false; 
        for(int i =0; i < stack_size; i++)
        {
            if(drawPile.size() > 0)
            {
                boolean check = true; 
                for(Deck d: stacks)
                {
                    if(d.size() < 1)
                        check = false; 
                }
                if(check)
                {
                    Card draw_card = drawPile.drawCard();
                    draw_card.setFaceUp(true);
                    Deck temp_stack = stacks.get(i);
                    temp_stack.addCard(draw_card);
                }
                else
                {
                    empty = true; 
                }
            }
        }
        if (empty)
            System.out.println("\n\nCan not Draw, Empty Stack\n\n");
    }

    /**
     *  Returns true if all stacks and the draw pile are all empty
     */ 
    public boolean isEmpty() {
        boolean r = true; 
        for(Deck d: stacks) 
        {
            if (d.size()> 0)
            {
                r = false; 
                if (drawPile.size()> 0)
                    r = false; 
            }
        }
        return r; 
    }
    /**
     *  If there is a run of A through K starting at the end of sourceStack
     *  then the run is removed from the game or placed into a completed
     *  stacks area.
     *  
     *  If there is not a run of A through K starting at the end of sourceStack
     *  then an invalid move message is displayed and the Board is not changed.
     */
    public void clear(int sourceStack) {
        if(sourceStack < stacks.size()+1)
        {
            Deck deck = stacks.get(sourceStack-1); 
            int num = 0;
            boolean run = false; 
            if (deck.size()>12)
            {
                Card tem2 = deck.getCard(deck.size()-1);
                if (tem2.getValue() == 13)
                {
                    for (int i = deck.size()-1; i >=0; i--)
                    {
                        if (i != 0)
                        {
                            Card tem = deck.getCard(i-1); 
                            Card temp = deck.getCard(i);
                            if (tem.isFaceUp() && temp.isFaceUp())
                            {
                                if( temp.getValue() == tem.getValue() +1)
                                {
                                    num++;
                                    run = true;
                                }
                                if (temp.getValue() != tem.getValue() +1)
                                {
                                    i -= deck.size(); 
                                }
                            }
                        }
                        
                    }
                    if (num == 12)
                    {
                        System.out.println("\n\nValid Move!\n\n");
                        completedStacks.add(deck);
                        //stacks.remove(sourceStack-1);
                        int size = deck.size();
                        for(int i = size-1; i >=size-13; i--)
                        {
                            deck.removeCard(i);
                        }
                    }
                    else
                        System.out.println("\n\nInvalid Move!\n\n");
                }
                else
                    System.out.println("\n\nInvalid Move!\n\n");
            }
            else
            {
                System.out.println("\n\nInvalid Move!\n\n");
            }
        }
        else 
        {
            System.out.println(" Error: Source Stack out of bounds");
        }
        
    }
    //Saves the game in a text file
    public void saveGame()
    {
        try
        {
            EventQueue.invokeAndWait(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        JFileChooser chooser = new JFileChooser(".");
                        chooser.showSaveDialog(null);
                        File f = chooser.getSelectedFile();
                        FileWriter file = new FileWriter(f);
                        
                        for( int i = 0; i < stacks.size(); i++)
                        {
                            Deck d = stacks.get(i);
                            int size = d.size(); 
                            file.write("deck " + size + "\n");
                            String s = ""; 
                            s = d.deckState(); 
                            file.write(s); 
                        }
                        String s = ""; 
                        drawPile.setFaceUp(); 
                        s = drawPile.deckState(); 
                        file.write("drawpile " + drawPile.size()+ "\n"); 
                        file.write(s); 
                        for(int i = 0; i < completedStacks.size(); i++)
                        {
                            Deck d = completedStacks.get(i); 
                            d.setFaceUp(); 
                            file.write("completedStack " + d.size() + "\n");
                            String str = d.deckState(); 
                            file.write(str); 
                        }
                        file.close();
                        System.out.println("Game Saved!");
                    }
                    catch(Exception err)
                    {
                        System.out.println("Exception: " + err.getMessage()); 
                    }
                }
            });
       } 
        catch(InterruptedException err)
        {
            System.out.println("Exception: " + err.getMessage());
        }
        catch (InvocationTargetException err)
        {
            System.out.println("Exception: " + err.getMessage());
        }
    }
    //loads game 
    public void loadGame()
    {
        try
        {
            EventQueue.invokeAndWait(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        JFileChooser chooser = new JFileChooser(".");
                        chooser.showOpenDialog(null);
                        File file = chooser.getSelectedFile();
                        Scanner sc = new Scanner(file);
                        ArrayList<Deck> stack = new ArrayList<Deck>();
                        ArrayList<Deck> completedStack = new ArrayList<Deck>();
                        ArrayList<Card> draw = new ArrayList<Card>();
                        while(sc.hasNext())
                        {
                            String start = sc.next(); 
                            if (start.equals("deck"))
                            {
                                ArrayList<Card> deck = new ArrayList<Card>(); 
                                int size = sc.nextInt();
                                Card c; 
                                for(int i = 0; i< size; i++)
                                {
                                    String symbol = sc.next();
                                    if(symbol.equals("up"))
                                    {
                                        symbol = sc.next();
                                        int value = sc.nextInt();
                                        c = new Card(symbol,value); 
                                        c.setFaceUp(true);
                                    }
                                    else
                                    {
                                        int value = sc.nextInt();
                                        c = new Card(symbol,value); 
                                    }
                                    deck.add(c); 
                                }
                                Deck d = new Deck(deck); 
                                stack.add(d); 
                            }
                            if (start.equals("drawpile"))
                            {
                                ArrayList<Card> deck = new ArrayList<Card>(); 
                                int size = sc.nextInt(); 
                                Card c; 
                                for (int i = 0; i < size; i++)
                                {
                                    String symbol = sc.next();
                                    if(symbol.equals("up"))
                                    {
                                        symbol = sc.next();
                                        int value = sc.nextInt();
                                        c = new Card(symbol,value); 
                                    }
                                    else
                                    {
                                        int value = sc.nextInt();
                                        c = new Card(symbol,value); 
                                    }
                                    deck.add(c); 
                                }
                                draw = deck; 
                            }
                            if (start.equals("completedStack"))
                            {
                                ArrayList<Card> deck = new ArrayList<Card>(); 
                                int size = sc.nextInt(); 
                                Card c; 
                                for(int i = 0; i< size; i++)
                                {
                                    String symbol = sc.next();
                                    if(symbol.equals("up"))
                                    {
                                        symbol = sc.next();
                                        int value = sc.nextInt();
                                        c = new Card(symbol,value); 
                                        c.setFaceUp(true);
                                    }
                                    else
                                    {
                                        int value = sc.nextInt();
                                        c = new Card(symbol,value); 
                                    }
                                    deck.add(c); 
                                }
                                Deck d = new Deck(deck); 
                                completedStack.add(d);
                            }
                        }
                        stacks = stack;
                        drawPile = new Deck(draw); 
                        completedStacks = completedStack; 
                        
                        
                    }
                    catch(Exception err)
                    {
                        System.out.println("Exception file might not be found: " + err.getMessage()); 
                    }
                }
            });
       } 
        catch(InterruptedException err)
        {
            System.out.println("Exception: " + err.getMessage());
        }
        catch (InvocationTargetException err)
        {
            System.out.println("Exception: " + err.getMessage());
        }
    }
    /**
     * Prints the board to the terminal window by displaying the stacks, draw
     * pile, and done stacks (if you chose to have them)
     */
    public void printBoard() {
        System.out.println("CURRENT BOARD\n\n");
        System.out.println("Stacks: "); 
        for (int i =0; i < stacks.size(); i ++)
        {
            Deck d = stacks.get(i);
            Card a = d.selectCard();
            if (d.size() > 0)
               a.setFaceUp(true);
            System.out.print(i+1 + ":  " + d.toString() + "\n");
        }
        System.out.println("\nDraw Pile: ");
        System.out.println(drawPile.toString()); 
        if(completedStacks.size() > 0)
        {
            System.out.println("\nCompleted Stacks: "); 
            System.out.print("{");
            for (int i = completedStacks.size(); i >0; i--)
            {
                System.out.print("[K]");
                if (i != 1)
                    System.out.print(" ");
            }
            System.out.println("}");
        }
    }
}