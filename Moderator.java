//Parth Samnani
//2017A3PS0298P

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Moderator implements Runnable, RunThread{

    //part of singleton design to ensure only 1 instance of the moderator exists
    private static Moderator uniqueModeratorInstance;

    //the hand of cards which the moderator generates for each game
    private Hand moderatorHand;

    //the thread for the moderator
    Thread moderatorThread;

    //the card which the moderator will show to all players in a round
    private Card moderatorSelectedCard;

    //output stream for moderator class   
    PrintStream moderatorOut = new PrintStream(new FileOutputStream(FileDescriptor.out));
    
    //constructor of moderator class
    private Moderator(int n){
        moderatorHand = new Hand(n);
        moderatorThread = new Thread(this, "Moderator");
        moderatorThread.setPriority(8);
        moderatorOut.print("The card which the moderator has generated(not visible to players) are\n");
        moderatorHand.showCards();
        moderatorOut.print("Moderator has been initialized\n");
    }

    public static Moderator getModerator(int n){
        if (uniqueModeratorInstance == null){
            uniqueModeratorInstance = new Moderator(n);
        }
        return uniqueModeratorInstance;
    }

    //method to show moderator card to players in 1 round
    public Card getCardToBeRevealed(){
        return moderatorSelectedCard;
    }
    
    //using this command in Game.java
    public void execute(){
        moderatorThread.start();
    }    

    //run method of the moderator thread
    public void run(){
        try{
            Thread.sleep(12000);
            for(int i = 0; i< moderatorHand.getCardCount(); i++){
                moderatorSelectedCard = moderatorHand.getCard(i);
                moderatorOut.print("Moderator is showing the number - " + moderatorSelectedCard.getValue() +"\n");
                Thread.sleep(12000);
            }   
        }
        catch(InterruptedException e){
            moderatorOut.print("Moderator interrupted!\n");
        }
        moderatorOut.print("Exiting the moderator thread\n");
    }
}