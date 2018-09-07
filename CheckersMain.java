/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkersmain;

import java.util.Scanner;

/**
 *
 * @author aquat
 */
public class CheckersMain {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        Board gameBoard = new Board();
        menu();
        boolean gameStatus = true;
        gameBoard.printBoard(); 
        
        while(gameStatus == true)
        {
            String input = getAndCheckInput();
            if(input != null)
            {
                gameBoard.validateMove(input);
            }
        }
        // TODO code application logic here
        
        gameBoard.printBoard();     
    }
    
    private static String getAndCheckInput(){
       Board tempBoard = new Board();
       Scanner readIn = new Scanner(System.in);
       String input;
       String[] stringArr;
       char player;
       int orig, intend;
       
       System.out.print("Enter You're move (Eg:B,12,16): ");
       input = readIn.nextLine();
       stringArr = input.split(",");
       player = stringArr[0].charAt(0);
       player = Character.toUpperCase(player);
       orig = Integer.parseInt(stringArr[1]);
       intend = Integer.parseInt(stringArr[2]);
       if(player != 'W' && player != 'B')
       {
           System.out.print("Player select error");
           return null;
       }
       if(!tempBoard.isBoardSpace(orig))
       {
           System.out.print("From location error");
           return null;
       }
       if(!tempBoard.isBoardSpace(intend))
       {
           System.out.print("to location error");
           return null;
       }
       return input;
    }
    
    private static void menu(){
        System.out.println("Welcoime to checkers");
        pressAnyKeyToContinue();
    }
    
    //pause method from https://stackoverflow.com/questions/19870467/how-do-i-get-press-any-key-to-continue-to-work-in-my-java-code
     private static void pressAnyKeyToContinue()
    { 
        System.out.println("Press Enter key to continue...");
        try
        {
            System.in.read();
        }  
        catch(Exception e)
        {}
    }
}