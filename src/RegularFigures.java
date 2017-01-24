/**
 * Class that represents regular figures
 * @author Mark Tselikov and Mirko Siljeg
 */
public class RegularFigures {

    private int coordX;
    private int coordY;
    private int teamInd;


    /**
     * Constructor for a regular figure
     * @param _coordX Initial coordinate X
     * @param _coordY Initial coordinate Y
     * @param _teamInd Index of a team of a figure
     */
    public RegularFigures(int _coordX, int _coordY, int _teamInd){

        coordX = _coordX;
        coordY = _coordY;
        teamInd = _teamInd;
        if(teamInd == MiniProjectGame.REGULAR_FIGURE_RED){MiniProjectGame.boardCells[coordX][coordY].setIndex(MiniProjectGame.REGULAR_FIGURE_RED);}
        else if(teamInd == MiniProjectGame.REGULAR_FIGURE_BLUE){MiniProjectGame.boardCells[coordX][coordY].setIndex(MiniProjectGame.REGULAR_FIGURE_BLUE);}
        MiniProjectGame.boardCells[coordX][coordY].displayFig();
    }


    /**
     * Accessor for a team index of a figure
     * @return Team index
     */
    public int getTeamInd(){
        return teamInd;
    }


    /**
     * Accessor for coordinate X
     * @return Coordinate X
     */
    public int getCoordinateX(){
        return coordX;
    }


    /**
     * Accessor for coordinate Y
     * @return Coordinate Y
     */
    public int getCoordinateY(){
        return coordY;
    }

}
