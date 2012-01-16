import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


public class Game{

    private
    BufferedReader reader; 

    /**
     * Main method creates an instance of the game and runs it
     */
    public static void main(String args[]){
    
        Game game = new Game();
        game.run();
    }
    
    /**
     * Constructor initialises stream for player input
     */
    Game(){
        reader = new BufferedReader(new InputStreamReader(System.in)); 
    }
    
    /**
     * Runs game, taking wagers and playing rounds of blackjack as appropriate
     */
    private void run(){
        int playerFunds = 1000;
        int bet = 10;
        
        while(playerFunds > 0){
        
            //retrieve player input (valid bet or quit command)
            bet = 0;
            while(bet == 0)
            {
                System.out.println("Player funds are " + Integer.toString(playerFunds));
                System.out.println("How much would you like to bet?");
                System.out.println("(input Q to exit)");

                String input = "";
                
                //get line
                try{ input = reader.readLine(); } 
                catch (IOException ioe){
                    System.out.println("Incorrect input error.");
                    bet = 0;
                }
                
                
                //check for quit command
                char quit = ' ';
                try{
                    quit = Character.toUpperCase(input.charAt(0));            
                }
                catch (java.lang.StringIndexOutOfBoundsException se){
                    System.out.println("You should have typed something there you big goof"); 
                }
                if(quit == 'Q')
                    System.exit(0);
                
                //parse bet
                try{
                    bet = Integer.parseInt(input);
                }
                catch (NumberFormatException nfe){
                    System.out.println("Invalid number");
                    bet = 0;                    
                }
                
                if(bet > playerFunds){
                    System.out.println("Insufficient funds available");
                    bet = 0;
                }
                if(bet < 0){
                    System.out.println("Negative bets not possible");
                    bet = 0;
                }
            }
            //play round
            playerFunds = playerFunds + (playRound() * bet); 
        }   
        
               
    }
    
    
    /**
     * Plays a round of blackjack
                     
    @return -1 if player loses, 0 if draw, 1 if player wins
     */
    private int playRound(){
    
        //setup hand and deal initial cards
        Deck deck = new Deck();
    
        BjHand player = new BjHand();
        BjHand dealer = new BjHand();
        
        dealCard(deck, player);
        dealCard(deck, dealer);
        System.out.println("\nDealer's first card:\n" + dealer);
        
        dealCard(deck, player);
        dealCard(deck, dealer);
        
        char input = ' ';
        
        //get player to play their hand
        while(input != 'S' && player.getValue()<21){
        
            System.out.println("Player's hand:\n" + player);
            System.out.println("H = hit, S = stand, Q = quit");
            input = ' ';
            try{ input = (reader.readLine()).charAt(0); } 
            catch (IOException ioe){
                System.out.println("Incorrect input error.");
            }
            catch (java.lang.StringIndexOutOfBoundsException se){
                System.out.println("You should have typed something there you big goof"); 
            }
            
            input = Character.toUpperCase(input);
            
            if(input == 'Q')
                System.exit(0);
            if(input == 'H')
                dealCard(deck, player);  
        }   
        System.out.println("Player's hand:\n" + player);
        
        //check for bust (automatic loss)
        if(player.getValue()>21)
        {
            System.out.println("Player busts");
            return -1;
        }
        else
        {      
            //play dealers hand
            System.out.println("Dealer's hand:\n" + dealer);        
            //dealer stands on 17 or higher
            while(dealer.getValue()<17)
            {
                dealCard(deck, dealer);
                System.out.println("Dealer's hand:\n" + dealer);
            }
            
            //test winning/losing conditions
            if(dealer.getValue() < player.getValue() || dealer.getValue() > 21 || 
            (player.getValue() == 21 && player.numCards() == 2 && dealer.numCards() > 2))
            {
                System.out.println("Player wins");
                return 1;
            }
            else if(dealer.getValue() == player.getValue())
            {
               System.out.println("Draw");
               return 0;
            }
            else
            {
                System.out.println("Player loses");
                return -1;
            }
          
        }
    }
    
    /**
     * removes card from deck and adds it to hand
     */
    void dealCard(Deck deck, BjHand hand){
        hand.addCard(deck.getCard());
    }

    
}
