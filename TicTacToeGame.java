import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeGame extends JFrame implements ActionListener {
    private JButton[] buttons;
    private boolean isPlayerX;
    private int moves;

    public TicTacToeGame() {
        super("Tic-Tac-Toe");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        buttons = new JButton[9];
        isPlayerX = true;
        moves = 0;

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 50));
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(this);
            add(buttons[i]);
        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();
        if (buttonClicked.getText().equals("") && moves < 9) {
            if (isPlayerX) {
                buttonClicked.setText("X");
            } else {
                buttonClicked.setText("O");
            }
            isPlayerX = !isPlayerX;
            moves++;
        }

        if (checkForWin()) {
            JOptionPane.showMessageDialog(this, (isPlayerX ? "O" : "X") + " wins!");
            resetBoard();
        } else if (moves == 9) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetBoard();
        }
    }

    private boolean checkForWin() {
        String[] board = new String[9];
        for (int i = 0; i < 9; i++) {
            board[i] = buttons[i].getText();
        }

        for (int i = 0; i < 8; i++) {
            String line = null;
            switch (i) {
                case 0:
                    line = board[0] + board[1] + board[2];
                    break;
                case 1:
                    line = board[3] + board[4] + board[5];
                    break;
                case 2:
                    line = board[6] + board[7] + board[8];
                    break;
                case 3:
                    line = board[0] + board[3] + board[6];
                    break;
                case 4:
                    line = board[1] + board[4] + board[7];
                    break;
                case 5:
                    line = board[2] + board[5] + board[8];
                    break;
                case 6:
                    line = board[0] + board[4] + board[8];
                    break;
                case 7:
                    line = board[2] + board[4] + board[6];
                    break;
            }

            if (line.equals("XXX") || line.equals("OOO")) {
                return true;
            }
        }
        return false;
    }

    private void resetBoard() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
        }
        isPlayerX = true;
        moves = 0;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TicTacToeGame();
        });
    }
}
