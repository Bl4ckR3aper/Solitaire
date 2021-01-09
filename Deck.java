import java.util.ArrayList; 
import java.util.Random; 
import java.util.Scanner; 
public class Deck
{
    private ArrayList<Card> deckCards = new ArrayList<Card>(); 
    public Deck(ArrayList<Card> cards)
    {
        deckCards = cards; 
    } 
    public Deck(String str)
    {
        Scanner sc = new Scanner(str);
        ArrayList<Card> deck = new ArrayList<Card>(); 
        while (sc.hasNext()) 
        {
            String s = sc.next(); 
            int v = sc.nextInt(); 
            Card a = new Card(s,v); 
            deck.add(a); 
            sc.nextLine(); 
        }
        deckCards = deck; 
    }
    public void shuffle()
    {
        for(int i = 0; i< deckCards.size(); i++)
        {
            Random ran = new Random();
            Card a = deckCards.get(i); 
            int r = ran.nextInt(deckCards.size()); 
            Card b = deckCards.get(r); 
            deckCards.set(r,a);
            deckCards.set(i,b);
        }
    }
    // this method draws the topmost card and remove the cards from the deck 
    public Card drawCard() 
    {
        Card a = deckCards.get((deckCards.size()-1));
        deckCards.remove(a);
        return a; 
    }
    // this method draws a random card and remove the cards from the deck
    public Card drawRandom()
    {
        Random ran = new Random();
        int r = ran.nextInt(deckCards.size());
        Card a = deckCards.get(r);
        deckCards.remove(a);
        return a; 
    }
    //This method draws the topmost card from the deck but does not remove it. 
    public Card selectCard() 
    {
        if(deckCards.size() > 0)
            return deckCards.get((deckCards.size()-1));
        else 
            return null; 
    }
    //This method draws a random card from the deck but does not remove it, places back to its place.
    public Card selectRandom()
    {
        Random ran = new Random();
        int r = ran.nextInt(deckCards.size());
        Card a = deckCards.get(r);
        return a; 
    }
    // this method adds a card to the deck. 
    public void addCard(Card c)
    {
        deckCards.add(c); 
    }
    //This method sets akk the cards in a deck to face up. 
    public void setFaceUp()
    {
        for (int i = 0; i < deckCards.size(); i++)
        {
           Card a = deckCards.get(i);
           a.setFaceUp(true);
        }
    }
    //split the deck into equal halves. 
    public ArrayList<Card> split()
    {
        int a = (this.size()/2); 
        ArrayList<Card> deck2 = new ArrayList<Card>();
        for (int i = 0; i < a; i++)
        {
            Card tem1 = deckCards.get(i);
            Card tem = new Card(tem1.getSymbol(),tem1.getValue());
            deck2.add(tem); 
        }
        for (int l =a-1; l >=0; l--)
        {
            deckCards.remove(l);
        }
        return deck2; 
    }
    //splits the deck into two random halves. 
    public ArrayList<Card> splitRandom()
    {
        int a = this.size()/2; 
        shuffle();
        ArrayList<Card> deck2 = new ArrayList<Card>();
        for (int i = 0; i < a; i++)
        {
            deck2.add(deckCards.get(i));
        }
        for (int l =a-1; l > 0; l--)
        {
            deckCards.remove(l);
        }
        return deck2; 
    }
    //This method returns the number of cards in the deck. 
    public int size()
    {
        return deckCards.size();
    }
    //removes the top card completely
    public void discard()
    {
        deckCards.remove((deckCards.size()-1));
    }
    //returns they Card at Index(but doesnt remove it)
    public Card getCard(int index)
    {
        return deckCards.get(index);
    }
    //removes the card at index
    public void removeCard(int index)
    {
        deckCards.remove(index); 
    }
    // Sets the top card face up 
    public void faceUp()
    {
        deckCards.get((deckCards.size()-1)).setFaceUp(true); 
    }
    //Prints the current state of the deck as a string
    public String deckState()
    {
        String result = ""; 
        for(Card c : deckCards)
        {
            Card a = new Card(c.getSymbol(), c.getValue()); 
            a.setFaceUp(true); 
            if(c.isFaceUp())
                result += "up " + a.getSymbol() + " " + c.getValue() + "\n"; 
            else
                result += a.getSymbol() + " " + c.getValue() + "\n"; 
        }
        return result; 
    }
    @Override
    public String toString()
    {
        String str = "";
        str+="[";
        for (int i = 0; i < deckCards.size(); i++)
        {
           Card a = deckCards.get(i);
           if (i != (deckCards.size()-1))
               str += (a.toString()+ ", ") ;
           else 
               str += (a.toString());
        }  
        str += "]"; 
        return str;
    }
}
