package gridworld.actor;

import gridworld.grid.Location;
import project.Main;

import java.util.ArrayList;


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
        ArrayList<Location> actors = Main.grid.getOccupiedLocations();
        int count = 0;
        for ( Location loc : actors )
        {
            if ( Main.grid.get( loc ) instanceof Pellet )
            {
                count++;
            }
        }

        score = 250 - count;

    }


    @Override public void act()
    {
        removeSelfFromGrid();
        Score s = new Score();
        s.putSelfInGrid( Main.grid, new Location( 1, 23 ) );

    }


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
