package gridworld.actor;

import gridworld.grid.Location;
import project.Main;
import project.Mechanics;

import java.awt.*;


/**
 * Inky is a simple Ghost, Inky goes anywhere
 */
public class Inky extends Ghost
{

    /**
     * The previous Actor
     */
    private Actor prevActor;
    /**
     * The next Actor
     */
    private Location prevLoc;

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
        while (true) {
            int rand = (int) (Math.random() * (4));

            switch (rand) {
                case 0:
                    if (canMove(new Location(location.getRow() + 1,
                                             location.getCol()))) {
                        moveHelper(new Location(location.getRow() + 1,
                                                location.getCol()));
                        return;
                    }
                    break;

                case 1:
                    if (canMove(new Location(location.getRow() - 1,
                                             location.getCol()))) {
                        moveHelper(new Location(location.getRow() - 1,
                                                location.getCol()));
                        return;
                    }
                    break;
                case 2:
                    if (canMove(new Location(location.getRow(),
                                             location.getCol() + 1))) {
                        moveHelper(new Location(location.getRow(),
                                                location.getCol() + 1));
                        return;
                    }
                    break;
                case 3:
                    if (canMove(new Location(location.getRow(),
                                             location.getCol() - 1))) {
                        moveHelper(new Location(location.getRow(),
                                                location.getCol() - 1));
                        return;
                    }
                    break;

            }
        }

    }

    /**
     * moves and Repopulates the previous empty spaces or normal dot with a
     * mixture of pellets, powerpellet or Pineapple
     *
     * also prevents eating pineapples, powerpellets, and other ghosts.
     *
     * @param next
     */
    private void moveHelper(Location next) {
        Actor pa = this.prevActor;
        this.prevActor = (Actor) Main.grid.get(next);
        if (this.prevActor != null) {
            this.prevActor.removeSelfFromGrid();
        }
        moveTo(next);
        if (this.prevLoc != null && (pa == null || pa instanceof Pellet)) {
            Mechanics.repopulate().putSelfInGrid(Main.grid, this.prevLoc);
        } else if (this.prevLoc != null) {
            pa.putSelfInGrid(Main.grid, this.prevLoc);
        }
        this.prevLoc = next;

    }

}
