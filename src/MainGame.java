/**
 * Class that sets up the game and has the main method
 */
public class MainGame extends MiniProjectGame{

    /**
     * Constructor for the game
     */
    public MainGame(){

        new GUI();
        MiniProjectGame.setTurn(BLUE_TURN);
        MiniProjectGame.changeTurn();
    }

    public static void main(String [] args){

        new MainGame();
    }
}
