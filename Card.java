//Parth Samnani
//2017A3PS0298P

public class Card{

    //the value of the card
    //i have let it stay final and hence am allowing -1
    
    private final int value;

    //the constructor for the card class
    public Card(int newValue){
        if(newValue<-1 || newValue>50){
        throw new IllegalArgumentException("Illegal Card Integer Value");
        }
        value = newValue;
    }

    //method to get the value of the card
    public int getValue(){
        return value;
    }
}   