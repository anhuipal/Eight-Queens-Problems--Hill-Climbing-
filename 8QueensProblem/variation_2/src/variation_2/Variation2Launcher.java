package variation_2;

public class Variation2Launcher {
    public static void main(String[] args) {
        ChessBoard board = new ChessBoard();
        System.out.println("Starting Board : \n");
        board.findSolution();
        //System.out.println(board.calculateConflicticsPrint());
        //board.calculateConflicts();
        System.out.println("Final Board : \n");
        board.printQueenPlaces();
    }//end of main
}//end of class
