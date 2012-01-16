import java.util.EnumMap;
import java.util.Map; 

class BjHand extends CardGroup{

    //mappig between card rank and it's value in black jack
    //(Aces assigned value == 11)
    static Map<Rank, Integer> rankValue = new EnumMap<Rank, Integer>(Rank.class);
    static {
        int rvals[] = {2,3,4,5,6,7,8,9,10,10,10,10,11};
        for (Rank r : Rank.values()){
            rankValue.put(r, rvals[r.ordinal()]);  
        }
    }
       
    /**
     * Returns value of hand
     * Aces are assumed to be 11 unless this causes the hand to be >21
     */ 
    public int getValue()
    { 
        int total_value = 0;
        int num_aces = 0;
        
        for (Card c : cards){
            int value = rankValue.get(c.rank);
            if(value == 11)
                num_aces++;
            total_value += value;
        }

        while(num_aces > 0 && total_value > 21){
            total_value -= 10;
            num_aces--;
        }
        return total_value;
    }  
    
    /**
     * Returns the number of cards in the hand
     */ 
    public int numCards()
    {
        return cards.size();
    }

    /**
     * Prints each card in the hand and the hand's value
     */     
    @Override public String toString(){
        String str = "";
        for(Card c : cards)
            str = str + c + "\n";
        str = str + "Value is " + Integer.toString(getValue()) + "\n";
        return str;
    }
}
