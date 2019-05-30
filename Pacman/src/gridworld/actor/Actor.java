/*
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * @author Cay Horstmann
 */

package gridworld.actor;

import gridworld.grid.Grid;
import gridworld.grid.Location;

import java.awt.*;


/**
 * An <code>Actor</code> is an entity with a color and direction that can act.
 * <br />
 */
public abstract class Actor
{
    protected Grid<Actor> grid;

    protected Location location;

    protected int direction;

    protected Color color;

    protected Color textColor;




    /**
     * Constructs a Yellow actor that is facing north.
     */
    public Actor()
    {
        color = Color.YELLOW;
        direction = Location.NORTH;
        grid = null;
        location = null;
    }


    /**
     * Gets the color of this actor.
     *
     * @return the color of this actor
     */
    public Color getColor()
    {
        return color;
    }


    /**
     * Sets the color of this actor.
     *
     * @param newColor the new color
     */
    public void setColor( Color newColor )
    {
        color = newColor;
    }


    /**
     * Gets the grid in which this actor is located.
     *
     * @return the grid of this actor, or <code>null</code> if this actor is
     * not contained in a grid
     */
    public Grid<Actor> getGrid()
    {
        return grid;
    }


    /**
     * Gets the location of this actor.
     *
     * @return the location of this actor, or <code>null</code> if this actor is
     * not contained in a grid
     */
    public Location getLocation()
    {
        return location;
    }


    /**
     * Puts this actor into a grid. If there is another actor at the given
     * location, it is removed. <br />
     * Precondition: (1) This actor is not contained in a grid (2)
     * <code>loc</code> is valid in <code>gr</code>
     *
     * @param gr  the grid into which this actor should be placed
     * @param loc the location into which the actor should be placed
     */
    public void putSelfInGrid( Grid<Actor> gr, Location loc )
    {
        if ( grid != null )
            throw new IllegalStateException( "This actor is already contained in a grid." );

        Actor actor = gr.get( loc );
        if ( actor != null )
            actor.removeSelfFromGrid();
        gr.put( loc, this );
        grid = gr;
        location = loc;
    }


    /**
     * Removes this actor from its grid. <br />
     * Precondition: This actor is contained in a grid
     */
    public void removeSelfFromGrid()
    {
        if ( grid == null )
            throw new IllegalStateException( "This actor is not contained in a grid." );
        if ( grid.get( location ) != this )
            throw new IllegalStateException( "The grid contains a different actor at location " + location + "." );

        grid.remove( location );
        grid = null;
        location = null;
    }


    /**
     * To be overrode in subclasses
     */
    public abstract void act();


    /**
     * Creates a string that describes this actor.
     *
     * @return a string with the location, direction, and color of this actor
     */
    public String toString()
    {
        return getClass().getName() + "[location=" + location + ",direction=" + direction + ",color=" + color + "]";
    }
}