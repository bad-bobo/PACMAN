package gridworld.actor;

import gridworld.grid.Location;

import java.awt.*;


/**
 * Inky is a simple Ghost, Inky goes anywhere
 */
public class Inky extends Ghost
{

    public Inky()
    {
        super( Color.yellow );

    }


    /**
     * Randomly chooses a location to move
     */
    @Override public void act()
    {
        if ( isScared() )
        {
            setColor( Color.BLUE );
            return;
        }
        else
        {
            setColor( Color.YELLOW );
        }
        while ( true )
        {
            int rand = (int)( Math.random() * ( 4 ) );

            switch ( rand )
            {
                case 0:
                    if ( canMove( new Location( location.getRow() + 1, location.getCol() ) ) )
                    {
                        moveTo( new Location( location.getRow() + 1, location.getCol() ) );
                        return;
                    }
                    break;

                case 1:
                    if ( canMove( new Location( location.getRow() - 1, location.getCol() ) ) )
                    {
                        moveTo( new Location( location.getRow() - 1, location.getCol() ) );
                        return;
                    }
                    break;
                case 2:
                    if ( canMove( new Location( location.getRow(), location.getCol() + 1 ) ) )
                    {
                        moveTo( new Location( location.getRow(), location.getCol() + 1 ) );
                        return;
                    }
                    break;
                case 3:
                    if ( canMove( new Location( location.getRow(), location.getCol() - 1 ) ) )
                    {
                        moveTo( new Location( location.getRow(), location.getCol() - 1 ) );
                        return;
                    }
                    break;

            }
        }

    }

}
