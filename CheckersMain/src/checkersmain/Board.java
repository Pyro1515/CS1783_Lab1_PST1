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
                        this.board[i][j] = '+';
                    }
                }else if(i < 3){
                    if(j%2 == 0)
                    {
                        this.board[i][j] = '+';
                    }else{
                        this.board[i][j] = ' ';
                    }
                }else if(i > 4 && i%2 == 0){
                    if(j%2 == 0)
                    {
                        this.board[i][j] = ' ';
                    }else{
                        this.board[i][j] = 'O';
                    }
                }else if(i > 4){
                    if(j%2 == 0)
                    {
                        this.board[i][j] = 'O';
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
        
        System.out.println(bestMove(orig));
        //add space that space is black space on the board.
//        CustomIndex fromMove = 
//                new CustomIndex(getArrIndex(orig).getFirst()
//                        ,getArrIndex(orig).getSecond());
//        CustomIndex toMove = 
//                new CustomIndex(getArrIndex(intend).getFirst()
//                        ,getArrIndex(intend).getSecond());
//        
//        System.out.print("[" + fromMove.getFirst() + "]");
//        System.out.print("[" + fromMove.getSecond() + "]");
//        //set which pieces should be getting moved.
//        if(player == 'B')
//        {
//            //Checks to see if space is occupied by a black piece
//            if(board[fromMove.getFirst()][fromMove.getSecond()] != 'O' 
//                    && board[fromMove.getFirst()][fromMove.getSecond()] != '@')
//            {
//                System.out.print("You do not have a piece in that location.");
//                return false;
//            }else if(true)
//            {
//                //check if a jump move is availble. Look at possible movese.
//            }
//        }else
//        {
//            piece = '+';
//            return false;
//        }
        return true;
    }
    
    //check for better move.
    private boolean bestMove(int pieceToMove){
        
        //create an object to store the 2d array index for the piece location passed in.
        CustomIndex origIndex = new CustomIndex(getArrIndex(pieceToMove).getFirst(),
                            getArrIndex(pieceToMove).getSecond());
        
        //get the char that is stored in the array
        char currentPiece = board[origIndex.getFirst()][origIndex.getSecond()];
        
        if(origIndex.getFirst()%2 == 0)
        {
            if(currentPiece == '+' || currentPiece == '#')
            {
                if(pieceToMove - 9 >= 1) 
                {
                    //get index of first diagnal move a white man can make.
                    CustomIndex firstMove = new CustomIndex(getArrIndex(pieceToMove - 4).getFirst(),
                                    getArrIndex(pieceToMove - 4).getSecond());
                    //get index of first space a white man can jump to.
                    CustomIndex secondMove = new CustomIndex(getArrIndex(pieceToMove - 7).getFirst(),
                                    getArrIndex(pieceToMove - 7).getSecond());

                    //get index of first alt diagnal white man can move.
                    CustomIndex altMove1 = new CustomIndex(getArrIndex(pieceToMove - 5).getFirst(),
                                    getArrIndex(pieceToMove - 5).getSecond());

                    //get index of first  alt jump white man can make.
                    CustomIndex altMove2 = new CustomIndex(getArrIndex(pieceToMove - 9).getFirst(),
                                    getArrIndex(pieceToMove - 9).getSecond());

                    //get characters from the customIndex objects.
                    char move1 = board[firstMove.getFirst()][firstMove.getSecond()];
                    char move2 = board[secondMove.getFirst()][secondMove.getSecond()];
                    char alt1 = board[altMove1.getFirst()][altMove1.getSecond()];
                    char alt2 = board[altMove2.getFirst()][altMove2.getSecond()];


                    //checks to see if 1) piece will not jump off board 2) the piece is an enemy piece.
                     if(move1 != ' ' && (move1 != toMan(currentPiece) && move1 != toKing(currentPiece)) 
                            && origIndex.getSecond() != 1)
                     {
                         //is there an open spot to land on.
                         if(move2 == ' '){
                             return true;
                         }
                     }else if(alt1 != ' ' && (alt1 != toMan(currentPiece) && alt1 != toKing(currentPiece))
                             && origIndex.getSecond() != 7)
                     {
                         if(alt2 == ' ')
                         {
                             return true;
                         }
                     }
                }

                if(currentPiece == '#' && pieceToMove + 9 <= 32)
                {
                    //get index of first diagnal move a white man can make.
                    CustomIndex wKing1 = new CustomIndex(getArrIndex(pieceToMove + 3).getFirst(),
                                    getArrIndex(pieceToMove + 3).getSecond());
                    //get index of first space a white man can jump to.
                    CustomIndex wKing2 = new CustomIndex(getArrIndex(pieceToMove + 7).getFirst(),
                                    getArrIndex(pieceToMove + 7).getSecond());

                    //get index of first alt diagnal white man can move.
                    CustomIndex wKingAlt1 = new CustomIndex(getArrIndex(pieceToMove + 4).getFirst(),
                                    getArrIndex(pieceToMove + 4).getSecond());

                    //get index of first  alt jump white man can make.
                    CustomIndex wKingAlt2 = new CustomIndex(getArrIndex(pieceToMove + 9).getFirst(),
                                    getArrIndex(pieceToMove + 9).getSecond());

                    //get characters from the customIndex objects.
                    char wKingM1 = board[wKing1.getFirst()][wKing1.getSecond()];
                    char wKingM2 = board[wKing2.getFirst()][wKing2.getSecond()];
                    char wKingA1 = board[wKingAlt1.getFirst()][wKingAlt1.getSecond()];
                    char wKingA2 = board[wKingAlt2.getFirst()][wKingAlt2.getSecond()];

                    if(wKingM1 != ' ' && (wKingM1 != toMan(currentPiece) && wKingM1 != toKing(currentPiece))
                            && origIndex.getSecond() != 7)
                     {
                         //is there an open spot to land on.
                         if(wKingM2 == ' '){
                             return true;
                         }
                     }else if(wKingA1 != ' ' && (wKingA1 != toMan(currentPiece) && wKingA1 != toKing(currentPiece))
                             && origIndex.getSecond() != 1)
                     {
                         if(wKingA2 == ' ')
                         {
                             return true;
                         }
                     }
                }

            }else if(currentPiece == 'O' || currentPiece == '@')
            {

            if(pieceToMove + 9 <= 32)
            {
                //get index of first diagnal move a black man can make.
                CustomIndex firstMove = new CustomIndex(getArrIndex(pieceToMove + 3).getFirst(),
                                getArrIndex(pieceToMove + 3).getSecond());
                //get index of first space a black man can jump to.
                CustomIndex secondMove = new CustomIndex(getArrIndex(pieceToMove + 7).getFirst(),
                                getArrIndex(pieceToMove + 7).getSecond());

                //get index of first alt diagnal black man can move.
                CustomIndex altMove1 = new CustomIndex(getArrIndex(pieceToMove + 4).getFirst(),
                                getArrIndex(pieceToMove + 4).getSecond());

                //get index of first  alt jump black man can make.
                CustomIndex altMove2 = new CustomIndex(getArrIndex(pieceToMove + 9).getFirst(),
                                getArrIndex(pieceToMove + 9).getSecond());

                //get characters from the customIndex objects.
                char bMove1 = board[firstMove.getFirst()][firstMove.getSecond()];
                char bMove2 = board[secondMove.getFirst()][secondMove.getSecond()];
                char bAlt1 = board[altMove1.getFirst()][altMove1.getSecond()];
                char bAlt2 = board[altMove2.getFirst()][altMove2.getSecond()];

                if(bMove1 != ' ' && (bMove1 != toMan(currentPiece) && bMove1 != toKing(currentPiece))
                        && origIndex.getSecond() != 7)
                 {
                     //is there an open spot to land on.
                     if(bMove2 == ' '){
                         return true;
                     }
                 }else if(bAlt1 != ' ' && (bAlt1 != toMan(currentPiece) && bAlt1 != toKing(currentPiece))
                         && origIndex.getSecond() != 1)
                 {
                     if(bAlt2 == ' ')
                     {
                         return true;
                     }
                 }
            }

            if(currentPiece == '@')
            {
                if(pieceToMove - 9 >= 1)
                {

                    //get index of first diagnal move a black king can make.
                    CustomIndex bKing1 = new CustomIndex(getArrIndex(pieceToMove - 4).getFirst(),
                                    getArrIndex(pieceToMove - 4).getSecond());
                    //get index of first space a black king can jump to.
                    CustomIndex bKing2 = new CustomIndex(getArrIndex(pieceToMove - 7).getFirst(),
                                    getArrIndex(pieceToMove - 7).getSecond());

                    //get index of first alt diagnal black king can move.
                    CustomIndex bKingAlt1 = new CustomIndex(getArrIndex(pieceToMove - 5).getFirst(),
                                    getArrIndex(pieceToMove - 5).getSecond());

                    //get index of first  alt jump black king can make.
                    CustomIndex bKingAlt2 = new CustomIndex(getArrIndex(pieceToMove - 9).getFirst(),
                                    getArrIndex(pieceToMove - 9).getSecond());

                    //get characters from the customIndex objects.
                    char bKingM1 = board[bKing1.getFirst()][bKing1.getSecond()];
                    char bKingM2 = board[bKing2.getFirst()][bKing2.getSecond()];
                    char bKingA1 = board[bKingAlt1.getFirst()][bKingAlt1.getSecond()];
                    char bKingA2 = board[bKingAlt2.getFirst()][bKingAlt2.getSecond()];


                    //checks to see if 1) piece will not jump off board 2) the piece is an enemy piece.
                    if(bKingM1 != ' ' && (bKingM1 != toMan(currentPiece) && bKingM1 != toKing(currentPiece))
                            && origIndex.getSecond() != 1)
                    {
                    //is there an open spot to land on.
                    if(bKingM2 == ' '){
                     return true;
                    }
                    }else if(bKingA1 != ' ' && (bKingA1 != toMan(currentPiece) && bKingA1 != toKing(currentPiece))
                            && origIndex.getSecond() != 7)
                    {
                        if(bKingA2 == ' ')
                        {
                         return true;
                        }
                    }
                }
            }
        }
        //determine if the row is odd or even. affecting the diagnal offset
        }else{
            if(currentPiece == '+' || currentPiece == '#')
            {
                if(pieceToMove - 9 >= 1) 
                {
                    //get index of first diagnal move a white man can make.
                    CustomIndex firstMove = new CustomIndex(getArrIndex(pieceToMove - 3).getFirst(),
                                    getArrIndex(pieceToMove - 3).getSecond());
                    //get index of first space a white man can jump to.
                    CustomIndex secondMove = new CustomIndex(getArrIndex(pieceToMove - 7).getFirst(),
                                    getArrIndex(pieceToMove - 7).getSecond());

                    //get index of first alt diagnal white man can move.
                    CustomIndex altMove1 = new CustomIndex(getArrIndex(pieceToMove - 4).getFirst(),
                                    getArrIndex(pieceToMove - 4).getSecond());

                    //get index of first  alt jump white man can make.
                    CustomIndex altMove2 = new CustomIndex(getArrIndex(pieceToMove - 9).getFirst(),
                                    getArrIndex(pieceToMove - 9).getSecond());

                    //get characters from the customIndex objects.
                    char move1 = board[firstMove.getFirst()][firstMove.getSecond()];
                    char move2 = board[secondMove.getFirst()][secondMove.getSecond()];
                    char alt1 = board[altMove1.getFirst()][altMove1.getSecond()];
                    char alt2 = board[altMove2.getFirst()][altMove2.getSecond()];


                    //checks to see if 1) piece will not jump off board 2) the piece is an enemy piece.
                     if(move1 != ' ' && (move1 != toMan(currentPiece) && move1 != toKing(currentPiece)) 
                             && origIndex.getSecond() != 0)
                     {
                         //is there an open spot to land on.
                         if(move2 == ' '){
                             return true;
                         }
                     }else if(alt1 != ' ' && (alt1 != toMan(currentPiece) && alt1 != toKing(currentPiece))
                             && origIndex.getSecond() != 6)
                     {
                         if(alt2 == ' ')
                         {
                             return true;
                         }
                     }
                }

                if(currentPiece == '#' && pieceToMove + 9 <= 32)
                {
                    //get index of first diagnal move a white man can make.
                    CustomIndex wKing1 = new CustomIndex(getArrIndex(pieceToMove + 4).getFirst(),
                                    getArrIndex(pieceToMove + 4).getSecond());
                    //get index of first space a white man can jump to.
                    CustomIndex wKing2 = new CustomIndex(getArrIndex(pieceToMove + 7).getFirst(),
                                    getArrIndex(pieceToMove + 7).getSecond());

                    //get index of first alt diagnal white man can move.
                    CustomIndex wKingAlt1 = new CustomIndex(getArrIndex(pieceToMove + 5).getFirst(),
                                    getArrIndex(pieceToMove + 5).getSecond());

                    //get index of first  alt jump white man can make.
                    CustomIndex wKingAlt2 = new CustomIndex(getArrIndex(pieceToMove + 9).getFirst(),
                                    getArrIndex(pieceToMove + 9).getSecond());

                    //get characters from the customIndex objects.
                    char wKingM1 = board[wKing1.getFirst()][wKing1.getSecond()];
                    char wKingM2 = board[wKing2.getFirst()][wKing2.getSecond()];
                    char wKingA1 = board[wKingAlt1.getFirst()][wKingAlt1.getSecond()];
                    char wKingA2 = board[wKingAlt2.getFirst()][wKingAlt2.getSecond()];

                    if(wKingM1 != ' ' && (wKingM1 != toMan(currentPiece) && wKingM1 != toKing(currentPiece))
                            && origIndex.getSecond() != 6)
                     {
                         //is there an open spot to land on.
                         if(wKingM2 == ' '){
                             return true;
                         }
                     }else if(wKingA1 != ' ' && (wKingA1 != toMan(currentPiece) && wKingA1 != toKing(currentPiece)) 
                             && origIndex.getSecond() != 0)
                     {
                         if(wKingA2 == ' ')
                         {
                             return true;
                         }
                     }
                }
            }else if(currentPiece == 'O' || currentPiece == '@')
            {

            if(pieceToMove + 9 <= 32)
            {
                //get index of first diagnal move a black man can make.
                CustomIndex firstMove = new CustomIndex(getArrIndex(pieceToMove + 4).getFirst(),
                                getArrIndex(pieceToMove + 4).getSecond());
                //get index of first space a black man can jump to.
                CustomIndex secondMove = new CustomIndex(getArrIndex(pieceToMove + 7).getFirst(),
                                getArrIndex(pieceToMove + 7).getSecond());

                //get index of first alt diagnal black man can move.
                CustomIndex altMove1 = new CustomIndex(getArrIndex(pieceToMove + 5).getFirst(),
                                getArrIndex(pieceToMove + 5).getSecond());

                //get index of first  alt jump black man can make.
                CustomIndex altMove2 = new CustomIndex(getArrIndex(pieceToMove + 9).getFirst(),
                                getArrIndex(pieceToMove + 9).getSecond());

                //get characters from the customIndex objects.
                char bMove1 = board[firstMove.getFirst()][firstMove.getSecond()];
                char bMove2 = board[secondMove.getFirst()][secondMove.getSecond()];
                char bAlt1 = board[altMove1.getFirst()][altMove1.getSecond()];
                char bAlt2 = board[altMove2.getFirst()][altMove2.getSecond()];

                if(bMove1 != ' ' && (bMove1 != toMan(currentPiece) && bMove1 != toKing(currentPiece))
                        && origIndex.getSecond() != 6)
                 {
                     //is there an open spot to land on.
                     if(bMove2 == ' '){
                         return true;
                     }
                 }else if(bAlt1 != ' ' && (bAlt1 != toMan(currentPiece) && bAlt1 != toKing(currentPiece)) 
                         && origIndex.getSecond() != 0)
                 {
                     if(bAlt2 == ' ')
                     {
                         return true;
                     }
                 }
            }

            if(currentPiece == '@')
            {
                if(pieceToMove - 9 >= 1)
                {

                    //get index of first diagnal move a black king can make.
                    CustomIndex bKing1 = new CustomIndex(getArrIndex(pieceToMove - 3).getFirst(),
                                    getArrIndex(pieceToMove - 3).getSecond());
                    //get index of first space a black king can jump to.
                    CustomIndex bKing2 = new CustomIndex(getArrIndex(pieceToMove - 7).getFirst(),
                                    getArrIndex(pieceToMove - 7).getSecond());

                    //get index of first alt diagnal black king can move.
                    CustomIndex bKingAlt1 = new CustomIndex(getArrIndex(pieceToMove - 4).getFirst(),
                                    getArrIndex(pieceToMove - 4).getSecond());

                    //get index of first  alt jump black king can make.
                    CustomIndex bKingAlt2 = new CustomIndex(getArrIndex(pieceToMove - 9).getFirst(),
                                    getArrIndex(pieceToMove - 9).getSecond());

                    //get characters from the customIndex objects.
                    char bKingM1 = board[bKing1.getFirst()][bKing1.getSecond()];
                    char bKingM2 = board[bKing2.getFirst()][bKing2.getSecond()];
                    char bKingA1 = board[bKingAlt1.getFirst()][bKingAlt1.getSecond()];
                    char bKingA2 = board[bKingAlt2.getFirst()][bKingAlt2.getSecond()];


                    //checks to see if 1) piece will not jump off board 2) the piece is an enemy piece.
                    if(bKingM1 != ' ' && (bKingM1 != toMan(currentPiece) && bKingM1 != toKing(currentPiece)) 
                            && origIndex.getSecond() != 0)
                    {
                    //is there an open spot to land on.
                    if(bKingM2 == ' '){
                     return true;
                    }
                    }else if(bKingA1 != ' ' && (bKingA1 != toMan(currentPiece) && bKingA1 != toKing(currentPiece))
                            && origIndex.getSecond() != 6)
                    {
                        if(bKingA2 == ' ')
                        {
                         return true;
                        }
                    }
                }
            
            }
            }
        }
        return false;
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
    public char toMan(char piece){
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
