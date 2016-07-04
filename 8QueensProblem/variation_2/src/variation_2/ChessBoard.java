package variation_2;

import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ChessBoard {
    
    private HashMap<Integer,Tile> board;
    private int globalConflicts;
    private int horizontalConflicts;
    private int diagonalConflicts;
    
    public ChessBoard() {
        board =  new HashMap<Integer,Tile>();
        globalConflicts = 0;
        horizontalConflicts=0;
        diagonalConflicts = 0;
    }//end of constructor
    
    /**
     *initializeBoard()
     * This function creates and 8X8 chessboard
     * @param key refers to the the 64 tiles in the board
     */
    
    public void initializeBoard(){
        int key = 1;
        for(int i=1;i<=8;i++){
            for(int j =1;j<=8;j++){
                this.board.put(key, new Tile(i,j));
                key++;
            }//end of iner loop
        }//end of outer loop    
    }//end of initializeBoard();
    
    /**
     * This method places the queens randomly on the board
     */
    
    public void placeQueens(){
    
        Random num = new Random();
        int qPosition = 0;
        int step = 8;
        
        for(int value =0; value<8; value++){
            qPosition += num.nextInt(7) + 1;
            board.get(qPosition).setHasQueen(true);
            qPosition =1;
            qPosition += step;
            step +=8;
        }//end of loop
        
    }//end of placeQueens();
    
    /**
     *Debuging method prints the conflicts
     * @return returns the number of conflicts
     */
    
    public int calculateConflicticsPrint(){
        
        int numOfDiagonalConflicts=0;
        int numOfHorizontalConflicts=0;
        
        /*
         * Horizontal Conflicts
         */

        /*
         * Clear Conflicts
         */

        for(Integer value:board.keySet()){
        if(board.get(value).hasQueen()){
            for(int i = value + 8;i<=64;i+=8){ //check y's
                if(board.get(i).hasQueen()){
                    numOfHorizontalConflicts++;
                    System.out.println("Horizontal Pairs: ");
                    System.out.println("X : " + board.get(value).getX()+ " Y: " + board.get(value).getY());
                    System.out.println("X : " + board.get(i).getX()+ " Y: " + board.get(i).getY());
                }//inner if
            }//end of inner loop
        }//outer if
        }//end of outer for loop

        /*
        * Diagonal Conflicts that have an increasing y axis
        */
        
            for(Integer value:board.keySet()){
               
                if(board.get(value).hasQueen()){
                    if(board.get(value).getY()!=1){
                        for(int i = value + 9 ;i<64;i+=9){ //check y's
                            if(board.get(i).hasQueen()){
                                if(board.get(i).getY()>board.get(value).getY()){
                                    System.out.println("Diagonal Pairs: ");
                                    System.out.println("X : " + board.get(value).getX()+ " Y: " + board.get(value).getY());
                                    System.out.println("X : " + board.get(i).getX()+ " Y: " + board.get(i).getY());
                                    numOfDiagonalConflicts++;
                                }else{
                                    break;
                                }
                            }
                            }//inner if
                        }//end of inner loop
                }//outer if
            }//end of outer for loop
        
            /*
             * Diagonal Conflicts that have decreasing y axis
             */
        
            for(Integer value:board.keySet()){
                if(board.get(value).hasQueen()){
                    if(board.get(value).getY()!=1){
                        for(int i = value + 7 ;i<64;i+=7){ //check y's
                            if(board.get(i).hasQueen()){
                                if(board.get(i).getY()<board.get(value).getY()){
                                    System.out.println("Diagonal Pairs: ");
                                    System.out.println("X : " + board.get(value).getX()+ " Y: " + board.get(value).getY());
                                    System.out.println("X : " + board.get(i).getX()+ " Y: " + board.get(i).getY());
                                    numOfDiagonalConflicts++;
                                }else{
                                    break;
                                }
                                
                            }
                        }//end of inner loop
                    }
                }//outer if
            }//end of outer for loop
            
            return numOfDiagonalConflicts + numOfHorizontalConflicts;
        
        }//end of calculateCOnflicts();
    
    /**
     * This method calculates the conflicts on the board, and updates current conflicts for each tile that has a queen
     */
    
    public void calculateConflicts(){
        
        /*
         * Horizontal Conflicts
         */
        
        globalConflicts = 0;
        int conflicts = 0;
        
        /*
         * Clear Conflicts
         */
        
        for(Integer value:board.keySet()){
            board.get(value).setConflicts(0);
        }
        
        for(Integer value:board.keySet()){
        conflicts=0;
        if(board.get(value).hasQueen()){
            for(int i = value + 8;i<=64;i+=8){ //check y's
            conflicts=0;
                if(board.get(i).hasQueen() && board.get(i).getY()==board.get(value).getY()){
                    conflicts++;
                    globalConflicts++;
                    board.get(value).setConflicts(board.get(value).getConflicts()+ conflicts);
                }//inner if
            }//end of inner loop
        }//outer if
        }//end of outer for loop

        /*
        * Diagonal Conflicts that have an increasing y axis
        */
        
            for(Integer value:board.keySet()){
                conflicts = 0;
                if(board.get(value).hasQueen()){
                    if(board.get(value).getY()!=8){
                        for(int i = value + 9 ;i<=64;i+=9){ //check y's
                        conflicts = 0;
                            if(board.get(i).hasQueen()){
                                if(board.get(i).getY()>board.get(value).getY()){
                                    conflicts++;
                                    globalConflicts++;
                                    board.get(value).setConflicts(board.get(value).getConflicts()+ conflicts);
                                }else{
                                    break;
                                }   
                            }//inner if
                        }//end of inner loop
                    }
                }//outer if
            }//end of outer for loop
        
            /*
             * Diagonal Conflicts that have decreasing y axis
             */
        
            for(Integer value:board.keySet()){
                conflicts=0;
                if(board.get(value).hasQueen()){
                    if(board.get(value).getY()!=1){
                        for(int i = value + 7 ;i<=64;i+=7){ //check y's
                            conflicts=0;
                            if(board.get(i).hasQueen()){
                                if(board.get(i).getY()<board.get(value).getY()){
                                    conflicts++;
                                    globalConflicts++;
                                    board.get(value).setConflicts(board.get(value).getConflicts()+ conflicts);
                                }else{
                                    break;
                                }  
                            }
                        }//end of inner loop
                    }
                }//outer if
            }//end of outer for loop
    }//end of calculateConflicts();
    
    /**
     * Core method of the problem combines all the method nessecery to find a solution and it runs until the @param globlac conflicts are 0 this is the second variation
     * of the problem that uses Simulated Anneling in order to find the solution
     */

    public void findSolution(){
        
        int propability = 4;
        int columnStart = 1;
        int step = 8;
        
        this.initializeBoard();
        this.placeQueens();
        this.calculateConflicts();
        this.printQueenPlaces();
        System.out.println("");
        
        //int running = 0;
        
        do{
            for(int i = 1;i<=8;i++){
                this.moveQueen(columnStart,step,propability);
                columnStart = step + 1;
                step +=8;
            }
            
            columnStart = 1;
            step = 8;
            
            if(propability!=10){
                propability++;
            }

            this.calculateConflicts();
        }while(globalConflicts!=0);
        
        //this.placeQueens(); Uncomment this line to see intermidiate steps
        
    }//end of findSolution();
    
    /**
     *This method searches a column and finds the tile that has the minimum amount of conflicts and moves the queen to that tile
     * @param columnStart the search start of the method
     * @param step the range of the search i.e columnStart 1 and step 8, searches the first column
     */
    
    public void moveQueen(int columnStart,int step,int upperBound){
         
        Random prob = new Random();
        
        int newQueenPosition = 0;
        int minConflicts = 0;
        int currentQueenPosition = 0;

        calculateConflicts();
        
        /*
         * The loop that follows finds the position of the current queen
         */
        
        for(int i = columnStart;i<=step;i++){
            if(board.get(i).hasQueen()){
                currentQueenPosition = i;
                minConflicts = board.get(i).getConflicts();
                newQueenPosition = i;
                board.get(i).setHasQueen(false);
                //System.out.println("in");
            }
        }

        for(int i = columnStart;i<=step;i++){
            if(i!=currentQueenPosition){
                
                board.get(i).setHasQueen(true);
                calculateConflicts();
               //System.out.println("in");
                if(board.get(i).getConflicts()<minConflicts){
                    minConflicts = board.get(i).getConflicts();
                    newQueenPosition = i;
                    //System.out.println("in");
                }
           }
            board.get(i).setHasQueen(false);
        }
        
        if((prob.nextInt(upperBound) + 1)==1){
            board.get(columnStart + (prob.nextInt(8))).setHasQueen(true);
        }else{
            //System.out.println(newQueenPosition);
            board.get(newQueenPosition).setHasQueen(true);
        }
        //board.get(newQueenPosition).setHasQueen(true);
        //this.printQueenPlaces();
    }//end of moveQueen();
    
    /**
     * Debuging function used to print the queens positions
     */
    
    public void printQueenPlaces(){
        
        for(Integer value:board.keySet()){
            if(board.get(value).hasQueen()){
                System.out.println("X: "+board.get(value).getX() + " " + "Y: " +board.get(value).getY() + " Conflicts : " + board.get(value).getConflicts());
            }
        }
        
    }//end of printQueenPlaces()
    
    /**
     * Debuging function used to print the board
     */
    
    public void printBoard(){
        
        for(Integer value:board.keySet()){
            System.out.println(board.get(value).getX());
            System.out.println(board.get(value).getY());
            System.out.println(board.get(value).hasQueen());
        }
        
    }//end of printBoard()
        
    public void setBoard(HashMap<Integer, Tile> board) {
        this.board = board;
    }

    public HashMap<Integer, Tile> getBoard() {
        return board;
    }

}//end of class

