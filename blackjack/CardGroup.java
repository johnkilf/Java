import java.util.LinkedList;

class CardGroup{

    protected
        LinkedList <Card>cards;
        
    /**
     * Constructs an empty card group
     */
    CardGroup(){
        cards = new LinkedList<Card>();
    }  
        
    /**
     * Returns the top most card in the group
     */
    public Card getCard()
    {
        if(this.size() == 0)
            return null;
        else
            return cards.pop();
    } 
    
    /**
     * Adds a card to the top of the group
     */
    public void addCard(Card card)
    {
        cards.push(card);
    }
    
    /**
     * Returns the number of cards in the group
     */    
    public int size()
    {
        return cards.size();
    }
}
