package gridworld.actor;

import gridworld.grid.Grid;
import gridworld.grid.Location;
import project.Main;

import java.io.*;
import java.util.ArrayList;


/**
 * TODO: Brad
 */
public class LevelPellet extends Pellet implements Edible
{

    private String levelName;

    private int levelNumber = 0;


    public LevelPellet( String lvlName )
    {
        super();
        levelName = lvlName;
    }


    /**
     * I think this is called when the thing is eaten?
     *
     * @param grid
     */
    public void action( Grid grid )
    {
        ArrayList<Location> locActors = grid.getOccupiedLocations();
        for ( Location loc : locActors )
        {
                Actor actor = (Actor)( grid.get( loc ) );
                actor.removeSelfFromGrid();
        }

        try
        {
            Main m = new Main();
            InputStream in = m.getClass().getResourceAsStream("/" + levelName + ".txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;

            for ( int i = 0; i < Main.ROW; i++ )
            {
                do
                {
                    line = br.readLine();
                    line = line.replaceAll( "[^A-Za-z0-9]",
                                    "" );

                } while ( line.length() <= 0 );
                for ( int j = 0; j < Main.COL; j++ )

                {
                    if ( line.charAt( j ) == 'x' )
                    {
                        Wall w = new Wall();
                        w.putSelfInGrid( grid, new Location( i , j ) );

                    }
                    else if ( line.charAt( j ) == 'o' )
                    {
                        Pellet p = new Pellet();
                        p.putSelfInGrid( grid, new Location( i , j ) );
                    }
                }
            }
        }
        catch ( IOException e )
        {
            e.printStackTrace();
            System.err.println( "Brad says \"shet\"" );
            System.exit( -1 );
        }

        //After new level
        Pacman pacman = new Pacman();
        pacman.putSelfInGrid( grid, new Location( 5, 5 ) );

        Inky inky = new Inky();
        inky.putSelfInGrid( grid, new Location(10,10) );

        Pacman.drawPacmanName();

    }

}
