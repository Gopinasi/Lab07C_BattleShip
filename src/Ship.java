public class Ship {
    private int row;
    private int col;
    private int size;
    private boolean horizontal;

    public Ship(int row, int col, int size, boolean horizontal) {
        this.row = row;
        this.col = col;
        this.size = size;
        this.horizontal = horizontal;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getSize() {
        return size;
    }

    public boolean isHorizontal() {
        return horizontal;
    }
}