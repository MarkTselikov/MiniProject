import javax.swing.*;

/**
 * Class that represents cells and what is currently positioned on them
 * @author Mark Tselikov and Mirko Siljeg
 */
public class Cells extends JPanel{

    //attributes
    private int coordX;
    private int coordY;
    private int indexOnCell;

    //icons that will be displayed on a cell
    private ImageIcon iconRed;
    private ImageIcon iconBlue;
    private ImageIcon iconEmpty;

    //JLabel for holding an icon
    private JLabel icPlace;


    /**
     * Constructor for a cell
     * @param _coordX Coordinate X of a cell
     * @param _coordY Coordinate Y of a cell
     */
    public Cells(int _coordX, int _coordY){
        coordX = _coordX;
        coordY = _coordY;
        indexOnCell = 0;

        //creating icons and setting an empty one to a cell
        iconRed = new ImageIcon(MiniProjectGame.ICON_FIG_RED);
        iconBlue = new ImageIcon(MiniProjectGame.ICON_FIG_BLUE);
        iconEmpty = new ImageIcon(MiniProjectGame.ICON_FIG_EMPTY);
        icPlace = new JLabel("", iconEmpty, JLabel.CENTER);
        this.add(icPlace);
    }

    /**
     * Accessor for coordinate X
     * @return Coordinate X of a cell
     */
    public int getCoordX(){return coordX;}

    /**
     * Accessor for coordinate Y
     * @return Coordinate Y of a cell
     */
    public int getCoordY(){return coordY;}

    /**
     * Accessor for index of an object that is located on a cell
     * @return Index of an object that is currently located on a cell
     */
    public int getIndex(){return indexOnCell;}

    /**
     * Setter of an index of object on a cell
     * @param ind New value of index
     */
    public void setIndex(int ind){indexOnCell = ind;}

    /**
     * Method that displays an icon on a cell depending on an index
     */
    public void displayFig(){
        if(indexOnCell == MiniProjectGame.REGULAR_FIGURE_RED){icPlace.setIcon(iconRed);}
        else if(indexOnCell == MiniProjectGame.REGULAR_FIGURE_BLUE){icPlace.setIcon(iconBlue);}
        else{icPlace.setIcon(iconEmpty);}
    }

}
