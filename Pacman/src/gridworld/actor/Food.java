package gridworld.actor;

import gridworld.grid.Grid;


/**
 * A food object can be eaten by pacman, eatAction is called when it is eaten
 */
public interface Food
{
    /**
     * called when the object is eaten
     * @param grid the grid in which the object is
     */
    void eatAction( Grid grid );
}
