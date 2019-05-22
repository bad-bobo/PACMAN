package gridworld.actor;

import gridworld.grid.Grid;
import gridworld.grid.Location;
import project.Main;


/**
 * Extends MovableActor. A Pacman eats pellets and tries to avoid Ghosts.
 */
public class Pacman extends MovableActor
{

    /**
     * If the count is even that the pacman in moveTo should be a closedPacman.
     * If the count is odd it should be a openPacman (regular)
     */
    protected static int count = 0;

    public static int currentDirection = 0;

    public static int newDirection = 0;


    /**
     * Default Constructor
     */
    public Pacman()
    {
        super();
    }


    /**
     * Moves this actor to a new location. If there is another actor at the
     * given location, it is removed. <br />
     * Precondition: (1) This actor is contained in a grid (2)
     * <code>newLocation</code> is valid in the grid of this actor
     *
     * @param newLocation the new location
     */
    public void moveTo( Location newLocation )
    {
        if ( newLocation.equals( new Location( 12, 1 ) ) )//pipes
        {
            grid.remove( location );
            location = new Location( 12, 22 );
            Pacman p = new Pacman();
            p.setDirection( this.getDirection() );
            p.putSelfInGrid( grid, location );
            return;
        }

        if ( newLocation.equals( new Location( 12, 22 ) ) )//pipes
        {
            grid.remove( location );
            location = new Location( 12, 1 );
            Pacman p = new Pacman();
            p.setDirection( this.getDirection() );
            p.putSelfInGrid( grid, location );
            return;

        }


        if ( grid == null )
            throw new IllegalStateException( "This actor is not in a grid." );

        if ( !grid.isValid( newLocation ) )
        {
            throw new IllegalArgumentException( "Location " + newLocation + " is not valid." );
        }

        if ( newLocation.equals( location ) )
            return;

        Actor other = grid.get( newLocation );
        if ( other instanceof Ghost )
        {
            this.removeSelfFromGrid();
            return;
        }
        if ( other != null )
            other.removeSelfFromGrid();

        if ( count % 2 == 0 )
        {
            Pacman p = new Pacman();
            p.setDirection( this.getDirection() );
            p.putSelfInGrid( grid, newLocation );

            //            grid.put( newLocation, p); AVOID THIS
        }
        else
        {
            PacmanClosed p = new PacmanClosed();
            p.setDirection( this.getDirection() );
            p.putSelfInGrid( grid, newLocation );
            //            grid.put( newLocation,  p); AVOID THIS
        }

        grid.remove( location );
        location = newLocation;
        count++;

    }


    /**
     * If the Pacman can move, moves the pacman. See PacmanWorld keyPressed for more info
     */
    public void act()
    {
        if ( canMove() )
        {
            move();
        }

    }


    /**
     * A Pacman can move if the if the grid Location is valid and the Actor is an instance of edible. Cannot turn if
     * the Actor is an instance of Wall
     *
     * @return true if can move, false otherwise.
     */
    public boolean canMove()
    {
        Location loc = getLocation();
        Location afterTurn = loc.getAdjacentLocation( newDirection );
        Grid<Actor> gr = getGrid();
        if ( gr == null )
            return false;
        //TODO: BRAD: comment the afterTurn
        if ( gr.isValid( afterTurn ) && !( gr.get( afterTurn ) instanceof Wall ) )
        {
            currentDirection = newDirection;
        }
        Location next = loc.getAdjacentLocation( getDirection() );

        if ( gr.isValid( next ) && gr.get( next ) instanceof Edible )
        {
            ( (Edible)gr.get( next ) ).action( gr );
        }
        if ( !gr.isValid( next ) )
            return false;
        Actor neighbor = gr.get( next );
        // return ( neighbor == null ) || ( neighbor instanceof Flower );
        return ( !( neighbor instanceof Wall ) );

    }


    /**
     * Gets the Direction
     *
     * @return the currentDirection
     */
    public int getDirection()
    {
        return currentDirection;
    }


    /**
     * Draws a "P A C M A N" at the top of the grid
     */
    public static void drawPacmanName()
    {
        String[] pacman = { "P", "A", "C", "-","M", "A", "N",  };
        int i = 9;

        for ( String m : pacman )
        {
            TextCell t = new TextCell( m );
            t.putSelfInGrid( Main.grid, new Location( 0, i ) );
            i++;
        }
    }
}
