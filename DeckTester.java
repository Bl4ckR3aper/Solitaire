import java.util.ArrayList; 
import java.util.Random;
public class DeckTester
{
    public static void main(String[] args) {
        DeckTester out = new DeckTester();
        ArrayList<Card> deck = out.createRandomDeck(21);
        Deck de = new Deck(deck);
        de.setFaceUp();
        //System.out.println(de.toString());
        String [] symbols ={"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
        int [] value = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        ArrayList<Card>deck2 = new ArrayList<Card>();
        for (int i = 0; i < 13; i++)
        {
            deck2.add(new Card(symbols[i],value[i]));
        }
        for (int i = 0; i < 13; i++)
        {
            deck2.add(new Card(symbols[i],value[i]));
        }
        
        Deck d = new Deck(deck2);
        
        d.setFaceUp();
        d.shuffle();
        System.out.println(d.toString());
        System.out.println(d.deckState());
        Deck e = new Deck(d.deckState()); 
        e.setFaceUp(); 
        System.out.println(e.toString());
        //System.out.println(d.toString()); 
       
        
        
        
        
        
         
        
    }
    public ArrayList<Card> createRandomDeck(int size)
    {
        String [] symbols ={"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
        int [] value = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        ArrayList<Card>deck = new ArrayList<Card>();
        for (int i = 0; i < size; i++)
        {
            Random ran = new Random();
            int r = ran.nextInt(13);
            deck.add(new Card(symbols[r],value[r]));
        }
        return deck;
    }
}
