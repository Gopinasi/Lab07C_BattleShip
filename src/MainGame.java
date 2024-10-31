import javax.swing.*;
import java.awt.*;

public class MainGame extends JFrame {
        static MainBoard gameBoard;
        private Status gameStatus;
        private JButton playAgainButton;
        private JButton quitButton;
        private String[][] game;
        private int row =10;
        private int col =10;

        public MainGame() {
            game = new String[row][col];
            BattleshipGame(); // Initialize BattleshipGame here
        }
        public void BattleshipGame() {
            setTitle("Battleship Game");
            setSize(600, 700);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new BorderLayout());

            gameBoard = new MainBoard(this);
            gameStatus = new Status();

            JPanel controlPanel = new JPanel();
            playAgainButton = new JButton("Play Again");
            quitButton = new JButton("Quit");

            playAgainButton.addActionListener(e ->
            {
                int response = JOptionPane.showConfirmDialog(null, "Do you want to restart?", "Play Again", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    MainGame.resetGame();
                }
            });


            quitButton.addActionListener(e ->
            { int response = JOptionPane.showConfirmDialog(null, "Do you want to quit?", "Quit Game", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            });

            controlPanel.add(playAgainButton);
            controlPanel.add(quitButton);

            add(gameBoard, BorderLayout.CENTER);
            add(gameStatus, BorderLayout.NORTH);
            add(controlPanel, BorderLayout.SOUTH);

            setVisible(true);
        }

        public static Component handleCellClick(int row, int col) {
            boolean isHit = gameBoard.handlePlayerClick(row, col);
            Status.updateCounters(isHit);

            if (isHit && gameBoard.allShipsSunk()) {
                int response = JOptionPane.showConfirmDialog(null, "Congratulations! You Won! Would you like to play again?", "Game Over",JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    MainGame.resetGame();
                }
                else{
                    System.exit(0);
                }
                resetGame();
            } else if (Status.getStrikeCounter() >= 3) {
                int response = JOptionPane.showConfirmDialog(null, "3 Strikes - Game Over! Do you want to play again?", "Game Over",JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    MainGame.resetGame();
                }
                else{
                    System.exit(0);
                }
            }
            return null;
        }

        static void resetGame() {
            gameBoard.resetBoard();
            Status.reset();
        }
}
