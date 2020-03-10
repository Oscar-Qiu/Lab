import java.util.Arrays;
import java.util.Scanner;

public class ConnectFour {
    public static void printBoard(char[][] array){
        // reverse the row
        // keep the column
        for(int i= array.length-1; i>=0;i--){
            for(int j=0; j<array[i].length;j++){
                System.out.print(array[i][j]);
                System.out.print(" ");

            }
            System.out.println("");
        }


    }
    public static void initializeBoard(char[][]array){
        for(char[]row: array){
            Arrays.fill(row,'-');

        }



    }
    public static int insertChip(char[][]array, int col, char chipType){
        // loop through the row
        // return the row that the token is placed
        int row=0;

        for(int i=0; i<array.length; i++){
            if(array[i][col]=='-'){
                // the place is available, insert the chip
                array[i][col]=chipType;
                return i;


            }
            // return the row of the placed chess

        }
return -1;
    }


//    enum GamingResult{
//        player1Win,
//        player2Win,
//        tie;
//
//
//    }

    public static boolean checkIfWinner(char[][]array, int col, int row, char chipType){
// check the column
        int count=0;
        for(int i=0; i<array.length;i++) {
           if(array[i][col]==chipType){
               count++;
           }
           else{
              break;
           }

        }
        // win situation: count>4
        if(count>=4){
            return  true;

        }
    count=0;
        // fix row
        // going right
        // check row
        for(int j=col; j<array[row].length;j++) {
           if( array[row][j]==chipType){
               count++;
           }
           // going left
            else{
                break;
           }

        }
        for(int j=col-1; j>=0; j--){
            if( array[row][j]==chipType){
                count++;
            }
            // going left
            else{
                break;
            }

        }
        if(count>=4){
            return  true;

        }


         // else situation, the game keep going or no one wins
   else{
       if(ifFilled(array)){
           System.out.println("Draw. Nobody wins.");
           return false;
       }
        }
        return false;
    }
    static boolean ifFilled(char[][]array){
        // iterate through row and column, if array[i][j]= chipType
        // return false, otherwise return true
       for(int i=0; i<array.length;i++){
           for(int j=0; j<array[0].length;j++){
               if(array[i][j]=='-')
                   // still have some space
                   return false;
           }
       }
       return true;
    }


    public static void main(String[]args){
        Scanner sc= new Scanner(System.in);
        System.out.print("What would you like the height of the board to be? ");
        //
        int height=sc.nextInt();
        System.out.print("What would you like the length of the board to be? ");
        int length=sc.nextInt();
        // create a 2d array
        char[][] board= new char[height][length];
        initializeBoard(board);
        printBoard(board);
        System.out.println("Player 1: x ");
        System.out.println("Player 2: o ");
        System.out.println();
        while(true) {
            System.out.print("Player 1: Which column would you like to choose? ");
            int col = sc.nextInt();
            char chipType = 'x';
            int rowChessPlaced = insertChip(board, col, chipType);
            printBoard(board);
            if (checkIfWinner(board, col, rowChessPlaced, chipType)) {
                // player1 wins
                System.out.println();

                System.out.println("Player 1 won the game! ");
                break;

            }
            else {
                // not win yet, player 2 keep playing
                // or it's tie situation
                if(ifFilled(board)){
                    return;
                }

                System.out.println("");
                System.out.print("Player 2: Which column would you like to choose? ");
                col = sc.nextInt();
                chipType = 'o';
                rowChessPlaced = insertChip(board, col, chipType);
                printBoard(board);
                if (checkIfWinner(board, col, rowChessPlaced, chipType)) {
                    // player2 wins
                    System.out.println();
                    System.out.println("Player 2 won the game! ");
                    break;

                }
                else{
                    if(ifFilled(board)){
                        return;
                    }

                }



            }
        }

    }
}
