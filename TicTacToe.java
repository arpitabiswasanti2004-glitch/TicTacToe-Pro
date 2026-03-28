import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame implements ActionListener {
    JButton[] buttons = new JButton[9];
    JLabel statusLabel;
    boolean xTurn = true; 

    public TicTacToe() {
        setTitle("Tic Tac Toe");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        
        statusLabel = new JLabel("X's Turn");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(statusLabel, BorderLayout.NORTH);

        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 60));
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }
        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (!clicked.getText().equals("")) return; 

        clicked.setText(xTurn ? "X" : "O");
        if (checkWinner()) {
            statusLabel.setText((xTurn ? "X" : "O") + " Wins!");
            disableButtons();
        } else if (isDraw()) {
            statusLabel.setText("Draw!");
        } else {
            xTurn = !xTurn;
            statusLabel.setText(xTurn ? "X's Turn" : "O's Turn");
        }
    }

    private boolean checkWinner() {
        String[][] board = new String[3][3];
        for (int i = 0; i < 9; i++) board[i / 3][i % 3] = buttons[i].getText();

        for (int i = 0; i < 3; i++) {
            if (!board[i][0].equals("") && board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]))
                return true;
            if (!board[0][i].equals("") && board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]))
                return true;
        }
        if (!board[0][0].equals("") && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]))
            return true;
        if (!board[0][2].equals("") && board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]))
            return true;

        return false;
    }

    private boolean isDraw() {
        for (JButton b : buttons) if (b.getText().equals("")) return false;
        return true;
    }

    private void disableButtons() {
        for (JButton b : buttons) b.setEnabled(false);
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
