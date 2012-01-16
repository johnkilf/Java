import java.util.Random;

class Deck extends CardGroup{

    private
    Random generator = new Random();    
    
    
    /**
     * Constructs a desk with 52 cards randomly ordered
     */
    Deck(){
           
        for(Suit suit : Suit.values()){
            for(Rank rank : Rank.values()){
                Card c = new Card(suit, rank);
                addCard(c);
            }
        }
        shuffle();
    }  
    
    /**
     * Randomly reorders cards
     */
    private void shuffle(){

        int numCards = size();
        
        for(int i = 0; i<numCards; i++){
            int index = generator.nextInt(numCards);
            Card temp = cards.get(index);
            cards.set(index, cards.get(i));
            cards.set(i, temp);
        }
    }
}
