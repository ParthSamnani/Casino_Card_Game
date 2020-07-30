//Parth Samnani
//2017A3PS0298P

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Player<T> implements Runnable, RunThread{

    //the name or id or the tag with which we identify a player
     private T playerTag;
    
    //the set of cards which the player has in his hand
     private Hand hand;
     
    //the score of the player
     private int score;
    
    //the thread for the player
     Thread playerThread;
    
    //the card which the moderator shows in every round. 
    //Currently initialized to a card with value -1
     private Card fromModerator = new Card(-1);

    //parameter for multithreading - thread sleep time
     private int parameterForThreadSleepTime;

    //output stream for player class
     PrintStream playerOut = new PrintStream(new FileOutputStream(FileDescriptor.out));     

    //the constructor for the player
    public Player(T tag, int i){
        playerTag = tag;
        String threadName = String.format("%s",playerTag.toString());
        playerThread = new Thread(this,threadName);
        hand = new Hand(10);
        playerThread.setPriority(6);
        playerOut.print(playerTag + " has been initialized\n");
        parameterForThreadSleepTime = i;
        getCardsinHand();
    }

    //setting the fromModerator card to the card which the moderator shows in each round
    public void setCardFromModerator(Card card){
        fromModerator = card;
    }

    //method to get player tag
    public T getPlayerTag(){
        return playerTag;
    }

    //method to print the player tag
     public String toString(){
         return getPlayerTag().toString();
     }
    
    //method to get the player score
     public int getScore(){
         return score;
     }

    //method to increment player score
     public void incrementScore(){
         score ++;
     }

    //method to see the cards in player's hand
     public void getCardsinHand(){
        playerOut.print("The cards for "+ playerTag + " are: \n"); 
        hand.showCards();
     }     

    //method to compare card shown by moderator with each card of player's hand
    //this happens in every round
     void checkModeratorCard(){
         playerOut.print(playerTag + " is checking moderator card\n");
         for (int i = 0;i<hand.getCardCount();i++){
            if (hand.getCard(i).getValue() == fromModerator.getValue()){
                hand.setCard(i, new Card(-1));
                incrementScore();
                break;
            }
         }
         playerOut.println(playerTag + " score is " + score);
     }

    //using this command in Game.java
     public void execute(){
        playerThread.start();
    }

    //run method of the player thread
    public void run(){
        try{
            for(int i = 0;i < 10;i++){
                Thread.sleep(12200+100*parameterForThreadSleepTime); 
                checkModeratorCard();
                if(getScore() == 3){
                    playerOut.print(playerTag + " is the winner !!!\n");
                    System.exit(0);
                }    
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

