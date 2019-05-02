import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.*;


public class Ghost extends Actor
{

    public Ghost( Color bugColor )
    {
        this.setColor( bugColor );
    }


    public void act()
    {
        if ( this.canMove() )
        {
            this.move();
        }
        else
        {
            this.turn();
        }

    }


    public void turn()
    {
        this.setDirection( this.getDirection() + 45 );
    }


    public void move()
    {
        Grid<Actor> gr = this.getGrid();
        if ( gr != null )
        {
            Location loc = this.getLocation();
            Location next = loc.getAdjacentLocation( this.getDirection() );
            if ( gr.isValid( next ) )
            {
                this.moveTo( next );
            }
            else
            {
                this.removeSelfFromGrid();
            }

        }
    }


    public boolean canMove()
    {
        Grid<Actor> gr = this.getGrid();
        if ( gr == null )
        {
            return false;
        }
        else
        {
            Location loc = this.getLocation();
            Location next = loc.getAdjacentLocation( this.getDirection() );
            if ( !gr.isValid( next ) )
            {
                return false;
            }
            else
            {
                Actor neighbor = (Actor)gr.get( next );
                return neighbor == null || neighbor instanceof Ghost;
            }
        }
    }

}
