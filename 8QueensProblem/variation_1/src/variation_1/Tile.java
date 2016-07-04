package variation_1;

public class Tile {
    
    private int x;
    private int y;
    private int conflicts;
    private boolean hasQueen;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        this.hasQueen = false;
        this.conflicts=0;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }


    public void setConflicts(int conflicts) {
        this.conflicts = conflicts;
    }

    public int getConflicts() {
        return conflicts;
    }

    public void setHasQueen(boolean hasQueen) {
        this.hasQueen = hasQueen;
    }

    public boolean hasQueen() {
        return hasQueen;
    }
}
