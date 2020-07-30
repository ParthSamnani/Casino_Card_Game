//Parth Samnani
//2017A3PS0298P

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

public class Hand {

    //hand is an arraylist of cardss
    private ArrayList<Card> hand;

    //output stream for hand class 
    PrintStream handOut = new PrintStream(new FileOutputStream(FileDescriptor.out));
    
    //initializing a hand of cards
    public Hand(int n){
        hand = new ArrayList<Card>(n);
        Random rand = new Random();
        for (int i = 0; i<n; i++){
            Card card = new Card(rand.nextInt(50));
            addCard(card);
        }
    }

    //method to add card in hand
    public void addCard(Card c){
        if (c == null){
            throw new NullPointerException("Can't add a null card to a hand");
        }
        hand.add(c);
    }

    //method to remove card from hand.
    //not using in our game
    public void removeCard(int position){
        if(position<0 || position> hand.size())
            throw new IllegalArgumentException("Position does not exist in hand: "+ position);
        hand.remove(position);
    }

    //method to get number of cards in hand
    public int getCardCount(){
        return hand.size();
    }

    //method to print all cards in hand
    public void showCards(){
        String cardListString = "";
       for (int i = 0; i< getCardCount();i++){
            if(i == 0){
                cardListString = cardListString + "[" + String.valueOf(getCard(i).getValue());    
            }else{
                cardListString = cardListString + " , " + String.valueOf(getCard(i).getValue());
            }
        }
            cardListString = cardListString + "]" ;    

         handOut.print(cardListString+"\n");
    }

    //Gets the card in a specified position in the hand.
    //Card is not removed from the hand.
    public Card getCard(int position) {
        if (position < 0 || position >= hand.size())
            throw new IllegalArgumentException("Position does not exist in hand: "
                    + position);
        return hand.get(position);
    }

    //Sets the card in a specified position in the hand.
    //Card is not added to the hand.
    public void setCard(int position, Card card){
        if (position < 0 || position >= hand.size())
        throw new IllegalArgumentException("Position does not exist in hand: "
                + position);
        hand.set(position, card);
    }
}