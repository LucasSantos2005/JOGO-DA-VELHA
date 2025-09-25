import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class jogodavelha {
    int boardWidth = 600;
    int boardHeigth = 650;  

    JFrame frame = new JFrame("Jogo-Da-Velha");
    JLabel textLabel= new JLabel();
    JPanel textPanel= new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String PlayerX = "X";
    String PlayerO = "O";
    String currentPlayer = PlayerX;

    boolean perdeu = false;
    int turnos = 0;


    jogodavelha(){
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeigth);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial",Font.BOLD,50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Jogo Da Velha");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);

        for (int r = 0; r <3; r++) {
            for (int c = 0; c < 3; c++){
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont (new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);
                //tile.setText(currentPlayer);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        if (perdeu) return;
                        JButton tile=  (JButton)e.getSource();
                        if (tile.getText()==""){
                            tile.setText(currentPlayer);
                            turnos++;
                            checarvencedor();
                            if (!perdeu){
                                
                            currentPlayer = currentPlayer == PlayerX? PlayerO : PlayerX;
                            textLabel.setText("Vez do " + currentPlayer);
                            }

                        }
                        

                    }
                });
            }
        }

    }

    void checarvencedor(){
        //horizontal
        for (int r = 0; r <3; r++){
            if (board [r][0].getText() == "") continue;

            if (board [r][0].getText() == board [r][1].getText() &&
                board [r][1].getText() == board [r][2].getText()){
                    for (int i = 0; i <3; i++){
                        ganhador(board[r][i]);
                    }
                perdeu = true;
                return;
            }
        }

        //vertical
        for (int c =0; c<3; c++){
            if (board[0][c].getText() == "") continue;

            if (board[0][c].getText() == board[1][c].getText() &&
            board[1][c].getText() == board[2][c].getText()){
                for(int i = 0; i <3; i++){
                    ganhador(board[i][c]);
                }
                perdeu = true;
                return;
            }
        }

        //diagonal
        if (board[0][0].getText() == board[1][1].getText()&&
            board[1][1].getText() == board[2][2].getText()&&
            board[0][0].getText() != ""){
                for(int i = 0; i <3; i++){
                    ganhador(board[i][i]);
                }
                perdeu = true;
                return;
            }

            //outraDiagonal
            if (board[0][2].getText() == board[1][1].getText()&&
                board[1][1].getText() == board[2][0].getText()&&
                board[0][2].getText() != ""){
                ganhador(board[0][2]);
                ganhador(board[1][1]);
                ganhador(board[2][0]);
                perdeu = true;
                return;
                }

                if (turnos == 9) {
                    for (int r = 0; r <3; r++){
                        for(int c = 0; c <3; c++){
                            setEmpate(board[r][c]);
                        }
                    }
                    perdeu = true;
                }
    }

    void ganhador(JButton tile){
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
        textLabel.setText(currentPlayer + " é o Vencedor!");
    }

    void setEmpate (JButton tile){
        tile.setForeground(Color.magenta);
        tile.setBackground(Color.gray);
        textLabel.setText("Empate!");
    }
}
