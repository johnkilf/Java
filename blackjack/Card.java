class Card{
    
    private
    Suit suit;
    Rank rank;

    /**
     * Constructs a card with Suit = suit and Rank = rank
     */
    Card(Suit suit, Rank rank){
        this.suit = suit;
        this.rank = rank;
    }
    
    /**
     * Reader for suit
     */
    public Suit suit(){ return suit;}
    
    /**
     * Reader for rank
     */
    public Rank rank(){ return rank;}
    
    @Override public String toString(){
        return(rank + " of " + suit);
    }
    
}
