import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;

import java.awt.*;


public class Main
{

    public static void main( String[] args )
    {

        BoundedGrid grid = new BoundedGrid( 15, 15 );
        ActorWorld world = new ActorWorld( grid );
        Ghost ghost = new Ghost( new Color( 255, 200, 1 ) );
        world.add( new Location( 7, 8 ), ghost );
        world.show();

    }

}
