package gridworld.actor;

import gridworld.grid.Grid;
import gridworld.grid.Location;
import project.Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * TODO: BRAD
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
        //Removes all instances of pacman and edible to setup for new level, should also be ghost and everything right?
        ArrayList<Location> locActors = grid.getOccupiedLocations();
        for ( Location loc : locActors )
        {
            if ( grid.get( loc ) instanceof Pacman || grid.get( loc ) instanceof Edible )
            {
                Actor actor = (Actor)( grid.get( loc ) );
                actor.removeSelfFromGrid();
            }
        }

        try
        {
            //File level = new File( "Pacman/src/Levels/" + levelName + ".txt" );
            //TODO: FOR SOME REASON THAT PATH DOESNT WORK ON MY COMPUTER, SO I PUT THE LINE BELOW FOR TESING
            File level = new File(
                            "/Users/Shams/Documents/GitHub/PACMAN/Pacman/src/Levels/level4.txt" );
            String line;
            BufferedReader br = new BufferedReader( new FileReader( level ) );

            for ( int i = 0; i < Main.ROW; i++ )
            {
                do
                {
                    //find next good line
                    line = br.readLine();
                    line = line.replaceAll( "[^A-Za-z0-9]",
                                    "" );//clean up string
                    // (remove commas or spaces and stuff)

                } while ( line.length() <= 0 );
                System.out.println( "*" + line );
                for ( int j = 0; j < Main.COL; j++ )

                {
                    if ( line.charAt( j ) == 'x' )
                    {
                        Wall w = new Wall();
                        w.putSelfInGrid( grid, new Location( i , j ) );
                        // Dont need i + 2 because the +2 in already incorppprated
                        // in the og map, we can change the og map and then  +2
                    }
                    else if ( line.charAt( j ) == 'o' )
                    {
                        Pellet p = new Pellet();
                        p.putSelfInGrid( grid, new Location( i , j ) );
                    }
                }
                //System.out.println();
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
        pacman.putSelfInGrid( grid, new Location( 19, 15 ) );

    }

}
