package gridworld.actor;

import gridworld.grid.Location;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;


/**
 * Inky is a simple Ghost, Inky goes anywhere
 * TODO: Soham
 */
public class Inky extends Ghost
{

    /**
     * Keeps a list of upto 13 old locations, these location the ghost cannot go to. See check old loc for more
     */
    private ArrayList<Location> listOldLocs;
    private final int OLD_LOC_CAPACITY = 10;


    public Inky()
    {
        super( Color.BLUE );
        listOldLocs = new ArrayList<Location>( );
    }


    @Override public void act()
    {
        inkyMove();
        System.out.println( "Inly.act: " + oldLocation );


    }

    public void inkyMove()
    {
        //Down
        Location loc = new Location( location.getRow() + 1, location.getCol() );
        if ( canMove( loc ) && !listOldLocs.contains( loc ) )
        {
            moveTo( loc );
            checkOldList();
            return;
        }
        //Left
        loc = new Location( location.getRow() , location.getCol()  - 1);
        if ( canMove( loc ) && !listOldLocs.contains( loc ) )
        {
            moveTo( loc );
            listOldLocs.add( loc );
            checkOldList();
            return;

        }
        //Right
        loc = new Location( location.getRow(), location.getCol() + 1 );
        if ( canMove( loc ) && !listOldLocs.contains( loc ) )
        {
            moveTo( loc );
            listOldLocs.add( loc );
            checkOldList();
            return;
        }
        //Up
        loc = new Location( location.getRow() - 1, location.getCol()  );
        if ( canMove( loc ) && !listOldLocs.contains( loc ) )
        {
            moveTo( loc );
            listOldLocs.add( loc );
            checkOldList();
            return;
        }
    }


    /**
     * resizes the old Loc list
     */
    public void checkOldList()
    {
        while ( listOldLocs.size() > OLD_LOC_CAPACITY )
        {

           Location loc = listOldLocs.remove( 0 );


        }
    }



}
