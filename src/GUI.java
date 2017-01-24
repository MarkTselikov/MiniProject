import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Class that is responcible for GUI and displaying everything on it
 * @author Mark Tselikov and Mirko Siljeg
 */
public class GUI extends MiniProjectGame {

    //defining control buttons
    private JButton jbUp;
    private JButton jbDown;
    private JButton jbLeft;
    private JButton jbRight;
    private JButton jbEndT;

    //defining information labels and panels
    protected static JLabel scoreRed;
    protected static JLabel scoreBlue;
    protected static JLabel apLab;
    protected static JPanel turnInd;

    /**
     * Constructor for GUI
     */
    public GUI(){

        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());

        //creating control buttons
        jbUp = new JButton("^");
        jbDown = new JButton("v");
        jbLeft = new JButton("<");
        jbRight = new JButton(">");
        jbEndT = new JButton("End Turn");

        //adding control buttons to a panel
        JPanel ctrlButPan = new JPanel(new BorderLayout());
        ctrlButPan.add(jbUp, BorderLayout.NORTH);
        ctrlButPan.add(jbDown, BorderLayout.SOUTH);
        ctrlButPan.add(jbLeft, BorderLayout.WEST);
        ctrlButPan.add(jbRight, BorderLayout.EAST);

        //adding action listeners to buttons
        OptionsListener lisnBut = new OptionsListener();
        jbUp.addActionListener(lisnBut);
        jbDown.addActionListener(lisnBut);
        jbLeft.addActionListener(lisnBut);
        jbRight.addActionListener(lisnBut);
        jbEndT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {MiniProjectGame.changeTurn();}
        });

        JPanel gameCtrlPan = new JPanel(new FlowLayout());
        gameCtrlPan.add(jbEndT);

        //creating information panel
        JPanel infoPan = new JPanel(new GridLayout(4, 1));
        scoreRed = new JLabel("Red points: " + 0);
        scoreBlue = new JLabel("Blue points: " + 0);
        apLab = new JLabel("Action points: " + MiniProjectGame.getActionPoints());
        JPanel turnPan = new JPanel(new GridLayout(1, 2));
        JLabel turnLab = new JLabel("Turn: ");
        turnInd = new JPanel();
        turnInd.setBackground(Color.BLUE);

        turnPan.add(turnLab);
        turnPan.add(turnInd);
        infoPan.add(scoreRed);
        infoPan.add(scoreBlue);
        infoPan.add(turnPan);
        infoPan.add(apLab);

        //creating panel for info and commands and adding it to the main frame
        JPanel commandsPan = new JPanel(new GridLayout(3, 1));
        commandsPan.add(gameCtrlPan);
        commandsPan.add(infoPan);
        commandsPan.add(ctrlButPan);
        frame.add(commandsPan, BorderLayout.EAST);

        //creating board (cells) and setting mouse listeners
        CellsClicker cellsClickLis = new CellsClicker();
        MiniProjectGame.boardCells = new Cells[10][10];
        JPanel board = new JPanel(new GridLayout(10, 10));
        for(int i = 0; i < boardCells.length; i++){
            for(int j = 0; j < boardCells[i].length; j++){
                Cells cell = new Cells(j, i);
                cell.addMouseListener(cellsClickLis);
                if(((i % 2 == 0) && (j % 2 == 1)) || ((i % 2 == 1) && (j % 2 == 0))){cell.setBackground(Color.DARK_GRAY);}
                else{cell.setBackground(Color.LIGHT_GRAY);}
                cell.setOpaque(true);
                boardCells[i][j] = cell;
                board.add(boardCells[i][j]);
            }
        }
        frame.add(board, BorderLayout.CENTER);

        //creating red figures
        figures = new RegularFigures[10][10];
        for(int i = 0; i < 2; i++){
            for(int j = 1; j < 9; j++) {
                figures[i][j] = new RegularFigures(j, i, REGULAR_FIGURE_RED);
                MiniProjectGame.redFiguresCount++;
            }
        }

        //creating blue figures
        for(int i = 9; i > 7; i--){
            for(int j = 1; j < 9; j++) {
                figures[j][i] = new RegularFigures(j, i, REGULAR_FIGURE_BLUE);
                MiniProjectGame.blueFiguresCount++;
            }
        }

        //setting the main frame
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(900, 750);
        frame.setResizable(false);
        frame.setVisible(true);
    }


    /**
     * Class that represents action listener for move buttons
     */
    class OptionsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == jbUp) {MiniProjectGame.moveUp();}
            else if (e.getSource() == jbDown) {MiniProjectGame.moveDown();}
            else if (e.getSource() == jbLeft) {MiniProjectGame.moveLeft();}
            else if (e.getSource() == jbRight) {MiniProjectGame.moveRight();}
        }
    }


    /**
     * Class that represents mouse listener for cells
     * Empty methods are written in order to make the class functional (implement MouseListener)
     */
    class CellsClicker implements MouseListener{
        public void mousePressed(MouseEvent e) {}

        public void mouseClicked(MouseEvent e) {

            Cells figSelect = (Cells)e.getSource();
            if(MiniProjectGame.getTurn() == MiniProjectGame.RED_TURN){
                if(figSelect.getIndex() == REGULAR_FIGURE_RED){
                    selectFigure(figSelect.getCoordX(), figSelect.getCoordY(), figSelect.getIndex());
                }
            }
            else if(MiniProjectGame.getTurn() == MiniProjectGame.BLUE_TURN) {
                if (figSelect.getIndex() == REGULAR_FIGURE_BLUE) {
                    selectFigure(figSelect.getCoordX(), figSelect.getCoordY(), figSelect.getIndex());
                }
            }

        }

        public void mouseEntered(MouseEvent e) {}

        public void mouseReleased(MouseEvent e) {}

        public void mouseExited(MouseEvent e) {}
    }
}
