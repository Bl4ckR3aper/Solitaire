/**
 * Card.java
 *
 * <code>Card</code> represents a basic playing card.
 */
public class Card implements Comparable<Card>
{
    /** String value that holds the symbol of the card.
    Examples: "A", "Ace", "10", "Ten", "Wild", "Pikachu"
     */
    private String symbol;
    /** int value that holds the value this card is worth */
    private int value; 
    /** boolean value that determines whether this card is face up or down */
    private boolean isFaceUp;  
    /**
     * Creates a new <code>Card</code> instance.
     *
     * @param symbol  a <code>String</code> value representing the symbol of the card
     * @param value an <code>int</code> value containing the point value of the card
     */    
    public Card(String lsymbol, int lvalue) {
       this.symbol = lsymbol; 
       this.value = lvalue; 
    }
    /**
     * Getter method to access this <code>Card</code>'s symbol.
     * 
     * @return this <code>Card</code>'s symbol.
     */
    public String getSymbol() {
        return symbol; 
    }
    /**
     * Getter method to access the <code>Cars</card>'s value
     */ 
    public int getValue() {
        return value; 
    }
    /**
     * returns weather or not <code>Card</code> is face up or not
     */
    public boolean isFaceUp() {
        return isFaceUp;
    }
    /**
     * Sets the isFaceUp Sybmol to the current state of the <code>Card</code>
     * 
     * True if up and false if down.
     */
    public void setFaceUp(boolean state) {
        isFaceUp = state;
    }
     /**
     * Returns whether or not this <code>Card</code> is equal to another
     *  
     *  @return whether or not this Card is equal to other.
      */
   
    public boolean equals(Card other) {
        if((this.value == other.getValue())&&(this.symbol.equals(other.getSymbol()))) 
            return true;
        else 
            return false; 
    }
    /** 
     * Method which needs to be included for the Comparable interface
     * 
     * This returns the difference between <code>Card</code> and another card passed in as a parameter
     */
    @Override
    public int compareTo(Card other)
    {
        return (this.getValue() - other.getValue());
    }
    /**
     * Returns this card as a String.  If the card is face down, "X"
     * is returned.  Otherwise the symbol of the card is returned.
     *
     * @return a <code>String</code> containing the symbol or and X,
     * depending on whether the card is face up or down.
     */
    @Override
    public String toString() 
    {
        return (this.isFaceUp()) ? this.getSymbol():"X" ;
    }
}
