/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tictactoe;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author macbook
 */
public class TicTacToe extends JFrame implements ActionListener  {

    private JButton[][] buttons;
    private JLabel label;
    private boolean xTurn = true;

    public TicTacToe() {  }

    public void runGame() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new BorderLayout());

        label = new JLabel("X's turn");
        add(label, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));

        buttons = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
                buttons[i][j].addActionListener(this);
                panel.add(buttons[i][j]);
            }
        }
        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();

        if (buttonClicked.getText().equals("")) {
            if (xTurn) {
                buttonClicked.setText("X");
                label.setText("O's turn");
            } else {
                buttonClicked.setText("O");
                label.setText("X's turn");
            }

            if (checkWin()) {
                String winner = xTurn ? "X" : "O";
                JOptionPane.showMessageDialog(this, "Player " + winner + " wins!");
                resetGame();
            } else if (checkTie()) {
                JOptionPane.showMessageDialog(this, "It's a tie!");
                resetGame();
            } else {
                xTurn = !xTurn;
            }
        }
    }

    private boolean checkWin() {
        String[][] board = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = buttons[i][j].getText();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][0].equals(board[i][2]) && !board[i][0].equals("")) {
                return true;
            }
            if (board[0][i].equals(board[1][i]) && board[0][i].equals(board[2][i]) && !board[0][i].equals("")) {
                return true;
            }
        }

        if (board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2]) && !board[0][0].equals("")) {
            return true;
        }
        return board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0]) && !board[0][2].equals("");
    }

    private boolean checkTie() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        xTurn = true;
        label.setText("X's turn");
    }
    
    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.runGame();
    }
}
