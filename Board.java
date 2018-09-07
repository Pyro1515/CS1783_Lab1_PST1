/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkersmain;

/**
 *
 * @author aquat
 */
public class Board {

    char[][] board;
    char piece;
    
    //constructor to initialize and populate a 2d array as the game board.
    public Board() {
        this.board = new char[8][8];
        for (int i = 0; i < 8; i++) 
        {
            for (int j = 0; j < 8; j++) 
            {
                if (i < 3 && i%2 == 0) 
                {
                    if(j%2 == 0)
                    {
                        this.board[i][j] = ' ';
                    }else{
                        this.board[i][j] = 'W';
                    }
                }else if(i < 3){
                    if(j%2 == 0)
                    {
                        this.board[i][j] = 'W';
                    }else{
                        this.board[i][j] = ' ';
                    }
                }else if(i > 4 && i%2 == 0){
                    if(j%2 == 0)
                    {
                        this.board[i][j] = ' ';
                    }else{
                        this.board[i][j] = 'B';
                    }
                }else if(i > 4){
                    if(j%2 == 0)
                    {
                        this.board[i][j] = 'B';
                    }else{
                        this.board[i][j] = ' ';
                    }
                }else {
                    this.board[i][j] = ' ';
                }
            }
        }
    }  
    
    //check all rules and determine if the player has entered a valid move.
    public boolean validateMove(String input) {
       
        char player;
        int orig, intend;
        String[] inputArr;
        piece = ' ';
        
        inputArr = input.split(",");
        player = inputArr[0].charAt(0);
        orig = Integer.parseInt(inputArr[1]);
        intend = Integer.parseInt(inputArr[2]);
        
        
        //add space that space is black space on the board.
        CustomIndex fromMove = 
                new CustomIndex(getArrIndex(orig).getFirst()
                        ,getArrIndex(orig).getSecond());
        CustomIndex toMove = 
                new CustomIndex(getArrIndex(intend).getFirst()
                        ,getArrIndex(intend).getSecond());
        
        System.out.print("[" + fromMove.getFirst() + "]");
        System.out.print("[" + fromMove.getSecond() + "]");
        //set which pieces should be getting moved.
        if(player == 'B')
        {
            //Checks to see if space is occupied by a black piece
            if(board[fromMove.getFirst()][fromMove.getSecond()] != 'O' 
                    && board[fromMove.getFirst()][fromMove.getSecond()] != '@')
            {
                System.out.print("You do not have a piece in that location.");
                return false;
            }else if(true)
            {
                //check if a jump move is availble. Look at possible movese.
            }
        }else
        {
            piece = '+';
            return false;
        }
        return true;
    }
    
    //check for better move.
    private boolean bestMove(int pieceToMove){
        boolean canJump = false;
        char orig, opt1, opt2;
        
        CustomIndex origIndex = new CustomIndex(getArrIndex(pieceToMove).getFirst(),
                            getArrIndex(pieceToMove).getSecond());
        
        orig = board[origIndex.getFirst()][origIndex.getSecond()];
        
        
        //Left edge checks
        if(pieceToMove%4 == 0)
        {
            if(orig == '+' && pieceToMove - 9 >=1) // is this a white piece and will it stay on the board if jumps
            {
                CustomIndex checkIndex = new CustomIndex(getArrIndex(pieceToMove - 4).getFirst(),
                getArrIndex(pieceToMove - 4).getSecond());
            
                opt1 = board[checkIndex.getFirst()][checkIndex.getSecond()]; // check piece diagnal of current piece
                
                if(orig != opt1 && opt1 != ' ' && opt1 != toKing(orig)) //if the piece isn't the current piece or a space it must be enemy
                {
                    CustomIndex tempIndex = new CustomIndex(getArrIndex(pieceToMove - 9).getFirst(),
                    getArrIndex(pieceToMove - 9).getSecond());
                    
                    char temp = board[tempIndex.getFirst()][tempIndex.getSecond()];//the char in the space the piece needs to land on
                    
                    canJump = temp == ' '; //set boolean value as true or false based on weather the space can be jumped to.
                }else{
                    canJump = false; //if the piece diagnal of the moving piece is not an enemy, it can not jump.
                }
            }else if(orig == 'O' && pieceToMove + 7 <= 32){
                CustomIndex checkIndex = new CustomIndex(getArrIndex(pieceToMove + 4).getFirst(),
                getArrIndex(pieceToMove + 4).getSecond());
            
                opt1 = board[checkIndex.getFirst()][checkIndex.getSecond()];
                
                if(orig != opt1 && opt1 != ' ')
                {
                    CustomIndex tempIndex = new CustomIndex(getArrIndex(pieceToMove + 7).getFirst(),
                    getArrIndex(pieceToMove + 7).getSecond());
                    
                    char temp = board[tempIndex.getFirst()][tempIndex.getSecond()];
                    
                    return temp == ' ';
                }else{
                    return false;
                }
            }else if(orig == '#'){
                if(pieceToMove + 7 <= 32)
                {
                    CustomIndex checkIndex = new CustomIndex(getArrIndex(pieceToMove + 4).getFirst(),
                    getArrIndex(pieceToMove + 4).getSecond());
            
                    opt1 = board[checkIndex.getFirst()][checkIndex.getSecond()];
                
                    if(orig != opt1 && opt1 != ' ')
                    {
                        CustomIndex tempIndex = new CustomIndex(getArrIndex(pieceToMove + 7).getFirst(),
                        getArrIndex(pieceToMove + 7).getSecond());
                    
                        char temp = board[tempIndex.getFirst()][tempIndex.getSecond()];
                    
                        if(temp == ' ')
                        {
                            return true;
                        }
                    }
                }else if(pieceToMove - 9 >= 1){
                    CustomIndex checkIndex = new CustomIndex(getArrIndex(pieceToMove - 4).getFirst(),
                    getArrIndex(pieceToMove - 4).getSecond());
            
                    opt1 = board[checkIndex.getFirst()][checkIndex.getSecond()];
                
                    if(orig != opt1 && opt1 != ' ')
                    {
                        CustomIndex tempIndex = new CustomIndex(getArrIndex(pieceToMove - 9).getFirst(),
                        getArrIndex(pieceToMove - 9).getSecond());
                    
                        char temp = board[tempIndex.getFirst()][tempIndex.getSecond()];
                    
                        if(temp == ' ')
                        {
                            return true;
                        }
                    }else{
                        return false;
                    }
                }
            }
            
        }else if(pieceToMove + 4 <= 32 && pieceToMove + 5 <= 32){
            
            
            CustomIndex checkIndex = new CustomIndex(getArrIndex(pieceToMove + 4).getFirst(),
                            getArrIndex(pieceToMove + 4).getSecond());
            CustomIndex checkIndex2 = new CustomIndex(getArrIndex(pieceToMove + 5).getFirst(),
                            getArrIndex(pieceToMove + 5).getSecond());
            
            
            orig = board[origIndex.getFirst()][origIndex.getSecond()];
            opt1 = board[checkIndex.getFirst()][checkIndex.getSecond()]; // forward to the left
            opt2 = board[checkIndex2.getFirst()][checkIndex2.getSecond()]; // forward to the right
            
            
            if(orig != opt1 && opt1 != ' ')
            {
                return bestMove(pieceToMove + 4);
            }
        }
        
        return true;
    }
    
    //take a board space number and convert it to a 2d array index.
    private CustomIndex getArrIndex(int value)
    {
        
        int row = value / 4;
        if(value%4 == 0)
        {
            row = 7 - row + 1;
        }else{
            row = 7 - row;
        }
        int colVal = value % 4;
        if(colVal == 0)
        {
            if(row%2 == 0)
            {
                colVal = colVal + 1;
            }else{
                colVal = 0;
            }
        }else if(row%2 == 0){
            colVal = 7 - 2 * (colVal-1);
        }else{
            colVal = 7 - (colVal + (colVal-1));
        }
        CustomIndex temp = new CustomIndex(row,colVal);
        
        return temp; 
    }
    
    //formatted print of the 2d array.
    public void printBoard() {
        for (int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                System.out.print('|');
                System.out.print(this.board[i][j]);
            }
            System.out.print('|');
            System.out.println();
            
        }
    }
    
    public boolean isBoardSpace(int space){
        return space > 0 && space < 33;
    }
    
    
        //Convert Man to King
    public char toKing(char piece){
        if(piece == '+'){
            piece = '#';
        }
        if(piece == 'O'){
            piece = '@';
        }
        if(piece == ' '){
            piece = ' ';
        }
        
        return piece;
    }
    
    //Convert King back to man
    public char toMan(){
        if(piece == '@'){
            piece = 'O';
        }
        if(piece == '#'){
            piece = '+';
        }
        if(piece == ' '){
            piece = ' ';
        }
        return piece;
    }

}
