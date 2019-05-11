package gridworld.actor;

import gridworld.grid.Location;
import project.Main;


/**
 * The Score of the Game, max is 250.
 */
public class Score extends Actor
{
    //250 pellets at start
    public static int score = 0;

    static
    {
        TextCell score = new TextCell( "SCORE:" );
        score.putSelfInGrid( Main.grid, new Location( 1, 22 ) );
    }

    public Score()
    {
        super();
    }


    /**
     * Increments the score by 1.
     */
    public static void inc()
    {
        score++;
    }


    /**
     * Deletes the old score and puts the new score
     */
    @Override public void act()
    {
        removeSelfFromGrid();
        Score s = new Score();
        s.putSelfInGrid( Main.grid, new Location( 1, 23 ) );

    }


    /**
     * Displays the score at an int, to be called when the object is created.
     *
     * @return String representation of score.
     */
    @Override public String toString()
    {
        String scr = String.valueOf( score );
        if ( scr.length() == 1 )
            return "  " + scr + " ";
        else if ( scr.length() == 2 )
            return " " + scr + " ";
        else
            return scr;
    }
}
