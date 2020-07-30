//Parth Samnani
//2017A3PS0298P

import static java.lang.Thread.sleep;

import java.io.*;

public class Game {
 
    public static void main(String[] args) throws IOException {
        
        //thread for main 
        Thread mainThread = Thread.currentThread();

        //setting priority to 7 which by default is 5
        mainThread.setPriority(7);

        //output stream for main file
        PrintStream mainOut = new PrintStream(new FileOutputStream(FileDescriptor.out));        

        mainOut.println("Please enter the total number of players in the game");
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        //n is the number of players in the game
        int n = Integer.parseInt(br.readLine());
        
        /**initializing an array list of players of size n
        
        Compiler warning is present because of how generics work.
        In java, we can't directly create an array of generic type.
        So we have to cast the raw Player type array to Player<String>        
        
        **/ 
        Player<String>[] playerList = (Player<String>[])new Player[n];

        //instantiating a moderator with 10 cards in hand
        Moderator moderator = Moderator.getModerator(10);

        //adding the n players in playerList
        for (int i = 0; i < n; i++) {
            String playerTag = String.format("Player %d", i + 1);
            playerList[i] = new Player<String>(playerTag, i);
            // playerList.add(i, new Player<String>(playerTag, i));
        }

        //calling the run() method of moderator thread to start rounds
        moderator.execute();

        //calling the run() method of individual players to start rounds
        for (int i = 0; i < n; i++) {
            playerList[i].execute();
        }


        //for each round - get card from moderator generated for that round
        //set card in players to check in their respective hands
        for (int i = 0; i < 10; i++) {
            try {
                sleep(12100);
            }
            catch(InterruptedException e){
                mainOut.print("The Main thread is interrupted\n");
            }
            Card cardToReveal = moderator.getCardToBeRevealed();

            mainOut.print("Number received by main in game is : " + cardToReveal.getValue()+"\n");

            for(int playerIndex = 0; playerIndex < playerList.length;playerIndex++){
                playerList[playerIndex].setCardFromModerator(cardToReveal);
            }
        }

        //Exit main thread after all other threads exit
        try{
            sleep(12000);
        }
        catch(InterruptedException e){
            mainOut.print("The Main thread is interrupted\n");
        } 
        mainOut.print("Exiting the Main thread");
        mainOut.close();
    }
}

