import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainBoard extends JPanel {
    private static final int GRID_SIZE = 10;
    private JButton[][] boardButtons = new JButton[GRID_SIZE][GRID_SIZE];
    private List<Ship> ships = new ArrayList<>();
    private Random random = new Random();
    private MainGame game;

    public MainBoard(MainGame game) {
        this.game = game;
        setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
        initializeBoard();
        placeShips();
    }

    private void initializeBoard() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                JButton button = new JButton();
                button.setBackground(Color.decode("#90cafc"));
                final int row = i, col = j;
                button.addActionListener(e -> MainGame.handleCellClick(row, col));
                boardButtons[i][j] = button;
                add(button);
            }
        }
    }

    private void placeShips() {
        int[] shipSizes = {5, 4, 3, 3, 2};
        for (int size : shipSizes) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(GRID_SIZE);
                int col = random.nextInt(GRID_SIZE);
                boolean horizontal = random.nextBoolean();
                if (canPlaceShip(row, col, size, horizontal)) {
                    Ship ship = new Ship(row, col, size, horizontal);
                    ships.add(ship);
                    for (int i = 0; i < size; i++) {
                        if (horizontal) boardButtons[row][col + i].setName("ship");
                        else boardButtons[row + i][col].setName("ship");
                    }
                    placed = true;
                }
            }
        }
    }

    private boolean canPlaceShip(int row, int col, int size, boolean horizontal) {
        if (horizontal) {
            if (col + size > GRID_SIZE) return false;
            for (int i = 0; i < size; i++) if (boardButtons[row][col + i].getName() != null) return false;
        } else {
            if (row + size > GRID_SIZE) return false;
            for (int i = 0; i < size; i++) if (boardButtons[row + i][col].getName() != null) return false;
        }
        return true;
    }

    public boolean handlePlayerClick(int row, int col) {
        JButton button = boardButtons[row][col];
        button.setEnabled(false);
        if ("ship".equals(button.getName())) {
            button.setText("X");
            button.setBackground(Color.RED);
            return true;
        } else {
            button.setText("M");
            button.setBackground(Color.YELLOW);
            return false;
        }
    }

    public boolean allShipsSunk() {
        return ships.stream().allMatch(this::isShipSunk);
    }

    private boolean isShipSunk(Ship ship) {
        for (int i = 0; i < ship.getSize(); i++) {
            JButton button = ship.isHorizontal() ? boardButtons[ship.getRow()][ship.getCol() + i] : boardButtons[ship.getRow() + i][ship.getCol()];
            if (!"X".equals(button.getText())) return false;
        }
        return true;
    }

    public void resetBoard() {
        removeAll();
        ships.clear();
        initializeBoard();
        placeShips();
        revalidate();
        repaint();
    }
}