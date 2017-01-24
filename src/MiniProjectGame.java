import javax.swing.*;
import java.awt.*;

/**
 * The main class of the game with the set of rules and methods that determine the game
 * @author Mark Tselikov and Mirko Siljeg
 * @version 1.3
 */
public class MiniProjectGame {

    final static int EMPTY = 0;

    //indexes for turns
    final static int RED_TURN = 5;
    final static int BLUE_TURN = 6;

    //indexes of a red team
    final static int INDEX_TEAM_RED = 20;
    final static int REGULAR_FIGURE_RED = 21;

    //indexes of a blue team
    final static int INDEX_TEAM_BLUE = -20;
    final static int REGULAR_FIGURE_BLUE = -21;

    final static int MAX_Action_Points = 3;

    //arrays that represent figures and cells on the board
    static RegularFigures [][] figures;
    static Cells [][] boardCells;

    //attributes
    private static int actionPoints;
    private static int turnPlayer;
    private static int selectedX;
    private static int selectedY;
    private static int selectedIndex;
    private static int pointsRed;
    private static int pointsBlue;
    protected static int redFiguresCount;
    protected static int blueFiguresCount;

    //paths to icons
    final static String ICON_FIG_RED = "../icons/red_fig.png";
    final static String ICON_FIG_BLUE = "../icons/blue_fig.png";
    final static String ICON_FIG_EMPTY = "../icons/empty.png";


    /**
     * Accessor for action points
     * @return Returns current action points
     */
    public static int getActionPoints(){
        return actionPoints;
    }


    /**
     * Setter for the turn
     * @param turn Index of the new turn (RED_TURN or BLUE_TURN)
     */
    public static void setTurn(int turn){
        turnPlayer = turn;
        actionPoints = MAX_Action_Points;
    }


    /**
     * Accessor for the current turn
     * @return Returns the current turn
     */
    public static int getTurn(){
        return turnPlayer;
    }


    /**
     * Method that takes one action point
     * If no action points left, it changes turn
     */
    public static void apUsed(){
        if(actionPoints > 1){actionPoints--;}
        else{changeTurn();}
    }


    /**
     * Method that changes turn
     * It also unselects any figures, maxes out action points and displays a message
     */
    public static void changeTurn(){
        if(turnPlayer == RED_TURN){
            turnPlayer = BLUE_TURN;
            unselectFigure();
            JOptionPane.showMessageDialog(null, "It's turn of the Blue player");
            GUI.turnInd.setBackground(Color.BLUE);
        }
        else{
            turnPlayer = RED_TURN;
            unselectFigure();
            JOptionPane.showMessageDialog(null, "It's turn of the Red player");
            GUI.turnInd.setBackground(Color.RED);
        }
        actionPoints = MAX_Action_Points;
        GUI.apLab.setText("Action points: " + actionPoints);
    }


    /**
     * Method for selecting a figure
     * It stores key values in MiniProjectGame class atributes
     * @param coordX Coordinate X of selected figure
     * @param coordY Coordinate Y of selected figure
     * @param sInd Index of selected figure
     */
    public void selectFigure(int coordX, int coordY, int sInd){
        selectedX = coordX;
        selectedY = coordY;
        selectedIndex = sInd;
    }


    /**
     * Unselects any figures
     */
    public static void unselectFigure(){
        selectedX = -1;
        selectedY = -1;
        selectedIndex = -1;
    }


    /**
     * Method that moves the selected figure to the left
     * If there is an enemy figure, it will be destroyed
     * Movement uses action points
     */
    public static void moveLeft(){
        if(selectedX != -1 && selectedY != -1){
            if(selectedX != 0 && boardCells[selectedY][selectedX - 1].getIndex() != selectedIndex){
                int figOp = -selectedIndex;
                if(boardCells[selectedY][selectedX - 1].getIndex() == figOp || boardCells[selectedY][selectedX - 1].getIndex() == EMPTY){
                    if(boardCells[selectedY][selectedX - 1].getIndex() == REGULAR_FIGURE_RED){redFiguresCount--;}
                    else if(boardCells[selectedY][selectedX - 1].getIndex() == REGULAR_FIGURE_BLUE){blueFiguresCount--;}
                    boardCells[selectedY][selectedX - 1].setIndex(selectedIndex);
                    boardCells[selectedY][selectedX - 1].displayFig();
                    boardCells[selectedY][selectedX].setIndex(EMPTY);
                    boardCells[selectedY][selectedX].displayFig();
                    figures[selectedY][selectedX - 1] = figures[selectedY][selectedX];
                    figures[selectedY][selectedX] = null;
                    selectedX--;
                    if(turnPlayer == RED_TURN && selectedX == 9){scorePoint(INDEX_TEAM_RED);}
                    else if(turnPlayer == BLUE_TURN && selectedX == 0){scorePoint(INDEX_TEAM_BLUE);}
                    checkWin();
                    apUsed();
                    GUI.apLab.setText("Action points: " + actionPoints);
                }
            }
        }
    }


    /**
     * Method that moves the selected figure to the right
     * If there is an enemy figure, it will be destroyed
     * Movement uses action points
     */
    public static void moveRight(){
        if(selectedX != -1 && selectedY != -1){
            if(selectedX != 9 && boardCells[selectedY][selectedX + 1].getIndex() != selectedIndex){
                int figOp = -selectedIndex;
                if(boardCells[selectedY][selectedX + 1].getIndex() == figOp || boardCells[selectedY][selectedX + 1].getIndex() == EMPTY){
                    if(boardCells[selectedY][selectedX + 1].getIndex() == REGULAR_FIGURE_RED){redFiguresCount--;}
                    else if(boardCells[selectedY][selectedX + 1].getIndex() == REGULAR_FIGURE_BLUE){blueFiguresCount--;}
                    boardCells[selectedY][selectedX + 1].setIndex(selectedIndex);
                    boardCells[selectedY][selectedX + 1].displayFig();
                    boardCells[selectedY][selectedX].setIndex(EMPTY);
                    boardCells[selectedY][selectedX].displayFig();
                    figures[selectedY][selectedX + 1] = figures[selectedY][selectedX];
                    figures[selectedY][selectedX] = null;
                    selectedX++;
                    if(turnPlayer == RED_TURN && selectedX == 9){scorePoint(INDEX_TEAM_RED);}
                    else if(turnPlayer == BLUE_TURN && selectedX == 0){scorePoint(INDEX_TEAM_BLUE);}
                    checkWin();
                    apUsed();
                    GUI.apLab.setText("Action points: " + actionPoints);
                }
            }
        }
    }


    /**
     * Method that moves the selected figure up
     * If there is an enemy figure, it will be destroyed
     * Movement uses action points
     */
    public static void moveUp(){
        if(selectedX != -1 && selectedY != -1){
            if(selectedY != 0 && boardCells[selectedY - 1][selectedX].getIndex() != selectedIndex){
                int figOp = -selectedIndex;
                if(boardCells[selectedY - 1][selectedX].getIndex() == figOp || boardCells[selectedY - 1][selectedX].getIndex() == EMPTY){
                    if(boardCells[selectedY - 1][selectedX].getIndex() == REGULAR_FIGURE_RED){redFiguresCount--;}
                    else if(boardCells[selectedY - 1][selectedX].getIndex() == REGULAR_FIGURE_BLUE){blueFiguresCount--;}
                    boardCells[selectedY - 1][selectedX].setIndex(selectedIndex);
                    boardCells[selectedY - 1][selectedX].displayFig();
                    boardCells[selectedY][selectedX].setIndex(EMPTY);
                    boardCells[selectedY][selectedX].displayFig();
                    figures[selectedY - 1][selectedX] = figures[selectedY][selectedX];
                    figures[selectedY][selectedX] = null;
                    selectedY--;
                    if(turnPlayer == RED_TURN && selectedX == 9){scorePoint(INDEX_TEAM_RED);}
                    else if(turnPlayer == BLUE_TURN && selectedX == 0){scorePoint(INDEX_TEAM_BLUE);}
                    checkWin();
                    apUsed();
                    GUI.apLab.setText("Action points: " + actionPoints);
                }
            }
        }
    }


    /**
     * Method that moves the selected figure down
     * If there is an enemy figure, it will be destroyed
     * Movement uses action points
     */
    public static void moveDown(){
        if(selectedX != -1 && selectedY != -1){
            if(selectedY != 9 && boardCells[selectedY + 1][selectedX].getIndex() != selectedIndex){
                int figOp = -selectedIndex;
                if(boardCells[selectedY + 1][selectedX].getIndex() == figOp || boardCells[selectedY + 1][selectedX].getIndex() == EMPTY){
                    if(boardCells[selectedY + 1][selectedX].getIndex() == REGULAR_FIGURE_RED){redFiguresCount--;}
                    else if(boardCells[selectedY + 1][selectedX].getIndex() == REGULAR_FIGURE_BLUE){blueFiguresCount--;}
                    boardCells[selectedY + 1][selectedX].setIndex(selectedIndex);
                    boardCells[selectedY + 1][selectedX].displayFig();
                    boardCells[selectedY][selectedX].setIndex(EMPTY);
                    boardCells[selectedY][selectedX].displayFig();
                    figures[selectedY + 1][selectedX] = figures[selectedY][selectedX];
                    figures[selectedY][selectedX] = null;
                    selectedY++;
                    if(turnPlayer == RED_TURN && selectedX == 9){scorePoint(INDEX_TEAM_RED);}
                    else if(turnPlayer == BLUE_TURN && selectedX == 0){scorePoint(INDEX_TEAM_BLUE);}
                    checkWin();
                    apUsed();
                    GUI.apLab.setText("Action points: " + actionPoints);
                }
            }
        }
    }


    /**
     * Method that asks user if he would like to exchange the figure for a point
     * It is invoked when a figure reaches the last line
     * @param indexT Index of the team that should be given a point
     */
    public static void scorePoint(int indexT){
        Object[] options = {"Yes", "No"};
        int o = JOptionPane.showOptionDialog(null, "Would you like to exchange the figure for a point?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
        System.out.println(o);
        if(o == 0 && indexT == INDEX_TEAM_RED){
            pointsRed++;
            figures[selectedY][selectedX] = null;
            boardCells[selectedY][selectedX].setIndex(EMPTY);
            boardCells[selectedY][selectedX].displayFig();
            redFiguresCount--;
            unselectFigure();
            GUI.scoreRed.setText("Red points: " + pointsRed);
        }
        else if(o == 0 && indexT == INDEX_TEAM_BLUE){
            pointsBlue++;
            figures[selectedY][selectedX] = null;
            boardCells[selectedY][selectedX].setIndex(EMPTY);
            boardCells[selectedY][selectedX].displayFig();
            blueFiguresCount--;
            unselectFigure();
            GUI.scoreBlue.setText("Blue points: " + pointsBlue);
        }
    }


    /**
     * Method that checks whether conditions for winning/losing are accomplished
     */
    public static void checkWin(){
        if(redFiguresCount == 0 || blueFiguresCount == 0){
            if(pointsBlue > pointsRed){
                JOptionPane.showMessageDialog(null, "Blue team is a winner!\nPoints: " + blueFiguresCount);
            }
            else if(pointsRed > pointsBlue){
                JOptionPane.showMessageDialog(null, "Red team is a winner!\nPoints: " + pointsRed);
            }
            else if(pointsRed == pointsBlue){
                JOptionPane.showMessageDialog(null, "It's a tie!\nNo one is a winner");
            }
        }
    }

}
